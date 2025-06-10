package com.goodforallcode.jobExtractor.extractor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goodforallcode.jobExtractor.cache.Cache;
import com.goodforallcode.jobExtractor.driver.DriverInitializer;
import com.goodforallcode.jobExtractor.driver.Scroller;
import com.goodforallcode.jobExtractor.filters.ExcludeJobFilter;
import com.goodforallcode.jobExtractor.filters.FilterFactory;
import com.goodforallcode.jobExtractor.filters.IncludeOrSkipJobFilter;
import com.goodforallcode.jobExtractor.job.populate.job.deep.DeepJobPopulator;
import com.goodforallcode.jobExtractor.job.populate.job.deep.GlassdoorDeepJobPopulator;
import com.goodforallcode.jobExtractor.job.populate.job.shallow.GlassdoorShallowJobPopulator;
import com.goodforallcode.jobExtractor.job.populate.job.shallow.ShallowJobPopulator;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.mongodb.client.MongoClient;
import lombok.Getter;
import lombok.Setter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;


@Getter
@Setter
public class GlassdoorExtractor extends Extractor {

    static DeepJobPopulator deepJobPopulator = new GlassdoorDeepJobPopulator();
    private static int RESULTS_PER_PAGE = 25;

    @Override
    public WebDriver getDriver(String userName, String password) {
        WebDriver driver = DriverInitializer.getWebDriver();
        return driver;

    }


    @Override
    public JobResult getJobs(Set<Cookie> cookies, Preferences preferences, String url, Cache cache, MongoClient mongoClient, WebDriver driver) {
        List<Job> acceptedJobs = new ArrayList<>();
        List<Job> rejectedJobs = new ArrayList<>();
        List<Job> deepCachedJobs = new ArrayList<>();
        List<Job> shallowCachedJobs = new ArrayList<>();

        int totalHidden = 0, totalJobs = 0, numJobs = 0, totalSkipped = 0, totalCached = 0, currentPageNum = 0;
        Optional<Integer> numResultsOption = Optional.empty();

        Scroller scroller = new Scroller();

        int hiddenJobs = 0, skippedJobs = 0, cachedJobs = 0;
        boolean everyJobHiddenCachedOrSkipped = false, justSkipped = false;

        List<Integer> pageValues;
        driver=getDriver(null, null);
        driver.get(url);
        try {
            Thread.sleep(5_000);
        } catch (InterruptedException ex) {

        }


        Elements items = getJobItems(driver);

        ShallowJobPopulator shallowPopulator = getShallowJobPopulator();
        hiddenJobs = 0;
        skippedJobs = 0;
        cachedJobs = 0;
        everyJobHiddenCachedOrSkipped = false;
        numJobs = items.size();
        Job job;
        for (Element item : items) {

            totalJobs++;

            try {
                job = shallowPopulator.populateJob(null,item, driver, preferences, null);
            } catch (TimeoutException e) {
                job = null;
            }

            if (job == null) {
                break;
            }

            job.setSourceUrl(url);
            if (preferences.isUsingCache() && cache.containsJobNoDescription(job, mongoClient)) {
                cachedJobs++;
                shallowCachedJobs.add(job);
                totalCached++;
                continue;
            }

            final Job finalJob = job;
            Optional<IncludeOrSkipJobFilter> includeOrSkipJobFilter = FilterFactory.getShallowFiltersSkip(preferences).stream().filter(f -> f.include(preferences, finalJob) != null).findFirst();
            if (includeOrSkipJobFilter.isPresent()) {
                        /*we don't want to cache acceptedJobs that are too new otherwise we may
                        not see them again in other future searches
                         */
                skippedJobs++;
                totalSkipped++;
                if (hiddenJobs + cachedJobs + skippedJobs == numJobs) {
                    everyJobHiddenCachedOrSkipped = true;
                }
                continue;
            }

            if (runFiltersThatMakeSenseForShallowPopulatedJobs(preferences, cache, mongoClient, finalJob, job, driver, rejectedJobs, acceptedJobs)) {
                continue;
            }
            Optional<ExcludeJobFilter> excludeJobFilter;

            boolean cached = deepLoadJob(job, driver, cache, mongoClient, deepCachedJobs, preferences);
            if (cached) {
                cachedJobs++;
                totalCached++;
                continue;
            }

            if (runFiltersThatMakeSenseForShallowPopulatedJobs(preferences, cache, mongoClient, finalJob, job, driver, rejectedJobs, acceptedJobs)) {
                continue;
            }


            IncludeOrExcludeJobResults includeOrExcludeJobResults = runDeepPopulatedFilters(job, preferences,
                    driver, cache, mongoClient);
            handleNonSkipJobResults(cache, mongoClient, acceptedJobs, rejectedJobs, driver, job, includeOrExcludeJobResults, preferences);

            if (includeOrExcludeJobResults.skipRemainingJobs()) {
                        /*
                        this should be true when there is an indication that the rest of the acceptedJobs on this page
                         will have issues
                         */
                break;
            }


        }


        if (driver != null) {
            try {
                driver.close();
            } catch (Exception ex) {

            }
        }
        JobResult result = new JobResult(acceptedJobs, rejectedJobs, shallowCachedJobs, deepCachedJobs, totalJobs, totalHidden, totalSkipped, currentPageNum);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(result);
            System.err.println(json);
        } catch (Exception e) {

        }
        return result;
    }



    /**
     * These filters either don't have enough shallow data or are not important enough to run until after we have ran
     * all always include/exclude filters.
     *
     * @param job
     * @param preferences
     * @param driver
     * @param cache
     * @param client
     * @return
     */
    public IncludeOrExcludeJobResults runDeepPopulatedFilters(Job job, Preferences preferences, WebDriver driver, Cache cache, MongoClient client) {

        boolean alreadyInCache = false;
        List<IncludeOrSkipJobFilter> includeOrSkipFilters = new ArrayList<>();

        Optional<IncludeOrSkipJobFilter> includeOrSkipFilter = FilterFactory.getDeepFiltersSkip(preferences).stream().filter(f -> f.include(preferences, job) != null).findFirst();
        if (includeOrSkipFilter.isPresent()) {
            includeOrSkipFilters.addAll(includeOrSkipFilter.stream().filter(f -> f.include(preferences, job) != null).collect(Collectors.toList()));
            return new IncludeOrExcludeJobResults(false, false, false, null, null);
        }

        IncludeOrExcludeJobResults results = runFinalFilters(job, preferences, driver, includeOrSkipFilters);
        if (results != null) {
            return results;
        }

        //default state is to include the job but with no reasons why
        return new IncludeOrExcludeJobResults(true, false, false, null, null);
    }


    public boolean deepLoadJob(Job job, WebDriver driver, Cache cache, MongoClient mongoClient, List<Job> deepCachedJobs, Preferences preferences) {
        boolean success = false;
        boolean cached = false;
        int numAttempts = 0, maxAttempts = 10;
        if (job.getJobDetailsLink() != null) {
            doubleClickOnElement(driver, job.getJobDetailsLink());
        }
        /*
        we need a way to test load pages.
        doing it this way will bring us to a new page which will mess up loading
        so this is only for testing.
         */
        //TODO do away with this path and make other path work with testing (it should be possible temporarily but the same goes for the URL)
        else {
            driver.get(job.getUrl());//TODO move back to main when done
        }
        try {
            if (driver != null) {
                do {
                    success = deepJobPopulator.populateJob(job, driver, preferences);
                    numAttempts++;
                    if (!success && numAttempts < maxAttempts) {
                        if (job.getJobDetailsLink() != null) {
                            doubleClickOnElement(driver, job.getJobDetailsLink());
                            Thread.sleep(5_000);
                        }
                    }
                } while (!success && numAttempts < maxAttempts);
            }

        } catch (TimeoutException | InterruptedException te) {
            success = false;
        }

        return cached;
    }


    protected ShallowJobPopulator getShallowJobPopulator() {
        return new GlassdoorShallowJobPopulator();
    }


    private Elements getJobItems(WebDriver driver) {
        WebElement ul = null;
        Elements items = new Elements();
        try {
            ul = driver.findElement(By.className("JobsList_jobsList__lqjTr"));
        } catch (NoSuchElementException nse) {
            throw nse;
        }

        if (ul != null) {
            Document doc = Jsoup.parse(ul.getAttribute("innerHTML"));
            items = doc.select("li.JobsList_jobListItem__wjTHv");
        }
        return items;
    }


    public static void main(String[] args) {
        GlassdoorExtractor e = new GlassdoorExtractor();


    }


}
