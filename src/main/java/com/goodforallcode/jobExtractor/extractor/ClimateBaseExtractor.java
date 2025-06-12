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
import com.goodforallcode.jobExtractor.job.populate.job.shallow.ClimatebaseShallowJobPopulator;
import com.goodforallcode.jobExtractor.job.populate.job.shallow.EightyKShallowJobPopulator;
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
public class ClimateBaseExtractor extends Extractor {


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

        int hiddenJobs = 0, skippedJobs = 0, cachedJobs = 0;
        boolean everyJobHiddenCachedOrSkipped = false, justSkipped = false;
        List<WebElement> nextPageButtons = new ArrayList<>();
        List<Integer> pageValues;
        driver=getDriver(null, null);
        driver.get(url);
        try {
            Thread.sleep(2_000);//time to load the page and not overwhelm the server
        } catch (InterruptedException ex) {

        }


        ShallowJobPopulator shallowPopulator = getShallowJobPopulator();
        int numTries = 0;
        List<WebElement> items = getJobWebElements(driver);
        Job job;

        hiddenJobs = 0;
        skippedJobs = 0;
        cachedJobs = 0;
        everyJobHiddenCachedOrSkipped = false;
        numJobs = items.size();
        for (WebElement item : items) {

            totalJobs++;

            try {
                job = shallowPopulator.populateJob(item,null, driver,preferences,null);
            } catch (TimeoutException e) {
                job = null;
            }

            if (job == null) {
                break;
            } else if (job.isHidden()&&preferences.isExcludeHiddenJobs()) {
                hiddenJobs++;
                totalHidden++;
                if (hiddenJobs + skippedJobs == numJobs) {
                    everyJobHiddenCachedOrSkipped = true;
                }
                continue;
            }
            job.setSourceUrl(url);
            if (cache.containsJob(job, mongoClient)) {
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

            if(!runFiltersThatMakeSenseForShallowPopulatedJobs(preferences, cache, mongoClient, finalJob, job, driver, rejectedJobs, acceptedJobs)) {
                IncludeOrExcludeJobResults includeOrExcludeJobResults = runFinalFilters(job, preferences, driver, new ArrayList<>());
                handleNonSkipJobResults(cache, mongoClient, acceptedJobs, rejectedJobs, driver, job, includeOrExcludeJobResults, preferences);
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
    protected ShallowJobPopulator getShallowJobPopulator() {
        return new ClimatebaseShallowJobPopulator();
    }


    private List<WebElement> getJobWebElements(WebDriver driver) {
        return driver.findElements(By.className("job__card"));
    }




    public static void main(String[] args) {
        EightyKHoursExtractor e = new EightyKHoursExtractor();


    }


}
