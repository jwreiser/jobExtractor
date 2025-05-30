package com.goodforallcode.jobExtractor.extractor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goodforallcode.jobExtractor.cache.Cache;
import com.goodforallcode.jobExtractor.driver.DriverInitializer;
import com.goodforallcode.jobExtractor.driver.Scroller;
import com.goodforallcode.jobExtractor.filters.ExcludeJobFilter;
import com.goodforallcode.jobExtractor.filters.FilterFactory;
import com.goodforallcode.jobExtractor.filters.IncludeOrSkipJobFilter;
import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.job.populate.DeepJobPopulator;
import com.goodforallcode.jobExtractor.job.populate.LinkedInDeepJobPopulator;
import com.goodforallcode.jobExtractor.job.populate.LinkedInShallowJobPopulator;
import com.goodforallcode.jobExtractor.job.populate.ShallowJobPopulator;
import com.goodforallcode.jobExtractor.model.CompanySummary;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyUtil;
import com.goodforallcode.jobExtractor.util.RESTUtil;
import com.goodforallcode.jobExtractor.util.WebdriverUtil;
import com.mongodb.client.MongoClient;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;


@Getter
@Setter
public class LinkedInExtractor extends Extractor {

    static DeepJobPopulator deepJobPopulator = new LinkedInDeepJobPopulator();
    private static int RESULTS_PER_PAGE = 25;

    @Override
    public WebDriver login(String userName, String password) {
        WebDriver driver = DriverInitializer.getWebDriver();
        driver.get("https://www.linkedin.com/login");
        WebElement nameField = driver.findElement(By.id("username"));
        if (nameField != null) {
            nameField.sendKeys(userName);
        }
        WebElement passwordField = driver.findElement(By.id("password"));
        if (passwordField != null) {
            passwordField.sendKeys(password);
        }

        WebElement sign = driver.findElement(By.xpath("//button[@type='submit' and contains(., 'Sign in')]"));
        if (sign != null) {
            doubleClickOnElement(driver, sign);
        }
        return driver;

    }


    public boolean doubleClickOnElement(WebDriver driver, WebElement element) {
        boolean success = super.doubleClickOnElement(driver, element);

        if (driver != null && driver.getTitle().equals("Security Verification | LinkedIn")) {
            System.err.println("You need to pass verification to login");
        }
        return success;
    }


    @Override
    public JobResult getJobs(Set<Cookie> cookies, Preferences preferences, String url, Cache cache, MongoClient mongoClient) {
        List<Job> acceptedJobs = new ArrayList<>();
        List<Job> rejectedJobs = new ArrayList<>();
        List<Job> deepCachedJobs = new ArrayList<>();
        List<Job> shallowCachedJobs = new ArrayList<>();

        int totalHidden = 0, totalJobs = 0, numJobs = 0, totalSkipped = 0, totalCached = 0, currentPageNum = 0;
        Optional<Integer> numResultsOption = Optional.empty();
        WebDriver driver = null;
        Scroller scroller = new Scroller();
        boolean driverSuccess = false;
        int numAttempts=1;
        while (!driverSuccess && numAttempts<=3) {
            try {
                driver = DriverInitializer.getInitializedDriver(cookies, url);
                if (WebdriverUtil.notOnJobsPage(driver)) {
                    return new JobResult(acceptedJobs, rejectedJobs, shallowCachedJobs, deepCachedJobs, totalJobs, totalHidden, totalSkipped, currentPageNum);
                }
                driverSuccess=true;
            } catch (NoSuchSessionException | StaleElementReferenceException ex) {
                System.err.println("Could not get driver- attempt "+numAttempts+": " + ex.getMessage());
                numAttempts++;
            }
        }
        if(!driverSuccess){
            return new JobResult(acceptedJobs, rejectedJobs, shallowCachedJobs, deepCachedJobs, totalJobs, totalHidden, totalSkipped, currentPageNum);
        }
        int hiddenJobs = 0, skippedJobs = 0, cachedJobs = 0;
        boolean everyJobHiddenCachedOrSkipped = false, justSkipped = false;
        List<WebElement> nextPageButtons = new ArrayList<>();
        List<Integer> pageValues;

        do {
            currentPageNum++;
            justSkipped = false;
                /*
                the current number of results can change as the page loads so we
                don't want to JUST read it right away as it may get lowered
                 */
            try {
                Thread.sleep(5_000);
            } catch (InterruptedException ex) {

            }
            if (noMatchingJobs(driver)) {
                break;
            }

            waitForPageToLoad(driver);
            if (noMatchingJobs(driver)) {
                numResultsOption = Optional.of(0);
            } else {
                numResultsOption = getCurrentNumberOfResults(driver);
            }

            if (WebdriverUtil.notOnJobsPage(driver)) {
                return new JobResult(acceptedJobs, rejectedJobs, shallowCachedJobs, deepCachedJobs, totalJobs, totalHidden, totalSkipped, currentPageNum);
            }

            nextPageButtons = getNextPageButtons(driver, currentPageNum);
            if (preferences.getSkipFirstPages() != null && preferences.getSkipFirstPages() > currentPageNum) {
                justSkipped = true;
                moveToNextPage(nextPageButtons, driver, currentPageNum);
                continue;
            }

            scroller.scrollResultsIntoView(url, driver);

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
                    job = shallowPopulator.populateJob(item, driver,preferences);
                } catch (TimeoutException e) {
                    job = null;
                }

                if (job == null) {
                    break;
                } else if (job.isHidden()) {
                    hiddenJobs++;
                    totalHidden++;
                    if (hiddenJobs + skippedJobs == numJobs) {
                        everyJobHiddenCachedOrSkipped = true;
                    }
                    continue;
                }
                job.setSourceUrl(url);
                if (cache.containsJobNoDescription(job, mongoClient)) {
                    cachedJobs++;
                    shallowCachedJobs.add(job);
                    totalCached++;
                    runDeepPopulatedFilters(driver, job);
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
            if (morePagesLeft(nextPageButtons, numJobs, justSkipped)) {
                moveToNextPage(nextPageButtons, driver, currentPageNum);
            }
        } while (morePagesLeft(nextPageButtons, numJobs, justSkipped));


        if (driver != null) {
            try {
                driver.close();
            } catch (Exception ex) {

            }
        }
        JobResult result=new JobResult(acceptedJobs, rejectedJobs, shallowCachedJobs, deepCachedJobs, totalJobs, totalHidden, totalSkipped, currentPageNum);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(result);
            System.err.println(json);
        } catch (Exception e) {

        }
        return result;
    }

    /**
     * This will run filters that  shallow populated jobs have enough information to run.
     * These filters will be run again when the job is deep populated.
     *
     * @param preferences
     * @param cache
     * @param mongoClient
     * @param finalJob
     * @param job
     * @param driver
     * @param rejectedJobs
     * @param acceptedJobs
     * @return true if the job was excluded or included, false otherwise. This indicates that we are done with the job and should move on to the next one.
     */
    private boolean runFiltersThatMakeSenseForShallowPopulatedJobs(Preferences preferences, Cache cache, MongoClient mongoClient, Job finalJob, Job job, WebDriver driver, List<Job> rejectedJobs, List<Job> acceptedJobs) {
        Optional<ExcludeJobFilter> excludeJobFilter = FilterFactory.getAlwaysExcludeFilters(preferences).stream().filter(f -> f.exclude(finalJob) != null).findFirst();
        if (excludeJobFilter.isPresent()) {
            job.setExcludeFilter(excludeJobFilter.get());
            job.setReason(excludeJobFilter.get().exclude(job));
            cache.addJob(job, false, mongoClient);
            runDeepPopulatedFilters(driver, job);
            rejectedJobs.add(job);
            return true;
        }

        Optional<JobFilter> jobFilter = FilterFactory.getAlwaysExcludeCustomFilters(preferences).stream().filter(f -> !f.include(preferences, finalJob)).findFirst();
        if (jobFilter.isPresent()) {
            job.setCustomExcludeFilter(jobFilter.get());
            cache.addJob(job, false, mongoClient);
            runDeepPopulatedFilters(driver, job);
            rejectedJobs.add(job);
            return true;
        }

        IncludeOrExcludeJobResults includeJobResults = alwaysIncludeJob(job, preferences,
                driver, cache, mongoClient);
        if (includeJobResults != null && includeJobResults.includeFilter() != null) {
            job.setIncludeFilter(includeJobResults.includeFilter());
            job.setReason(includeJobResults.includeFilter().include(preferences, job));
            cache.addJob(job, true, mongoClient);
            includeJob(driver, acceptedJobs, job);
            return true;
        }
        return false;
    }

    private static boolean morePagesLeft(List<WebElement> nextPageButtons, int numJobs, boolean justSkipped) {
        return nextPageButtons != null && !nextPageButtons.isEmpty() && (justSkipped || numJobs == 25);
    }

    private void moveToNextPage(List<WebElement> nextPageButtons, WebDriver driver, int currentPageNum) {
        System.err.println("Moving to page: " + (currentPageNum + 1));
        for (WebElement nextPageButton : nextPageButtons) {
            if (doubleClickOnElement(driver, nextPageButton)) {
                break;
            }
        }
    }


    private void handleNonSkipJobResults(Cache cache, MongoClient mongoClient, List<Job> acceptedJobs, List<Job> rejectedJobs, WebDriver newDriver, Job job, IncludeOrExcludeJobResults includeOrExcludeJobResults
            , Preferences preferences) {
        if (includeOrExcludeJobResults.excludeJob()) {
            job.setExcludeFilter(includeOrExcludeJobResults.excludeFilter());
            cache.addJob(job, false, mongoClient);
            runDeepPopulatedFilters(newDriver, job);
            rejectedJobs.add(job);
        } else if (includeOrExcludeJobResults.includeJob()) {
            job.setIncludeFilter(includeOrExcludeJobResults.includeFilter());
            if(includeOrExcludeJobResults.includeFilter()!= null) {
                job.setReason(includeOrExcludeJobResults.includeFilter().include(preferences, job));
            }

            cache.addJob(job, true, mongoClient);
            includeJob(newDriver, acceptedJobs, job);
        }
    }

    private void hideMessages(WebDriver newDriver) {
        try {
            WebElement svg = newDriver.findElement(By.cssSelector("svg[data-test-icon='chevron-down-small']"));
            WebElement button = svg.findElement(By.xpath(".."));
            if (button != null && button.getTagName().equals("button")) {
                button.click();
            }
        } catch (NoSuchElementException nse) {
            //I don't like how this does not just return false. If the item is there don't click it
        }
        try {
            List<WebElement> closeImages = newDriver.findElements(By.cssSelector("svg[data-test-icon='close-small']"));
            WebElement button;
            for (WebElement image : closeImages) {
                if (image.isDisplayed()) {
                    button = image.findElement(By.xpath(".."));
                    if (button != null && button.getTagName().equals("button")) {
                        button.click();
                    }
                }
            }
        } catch (NoSuchElementException nse) {
            //I don't like how this does not just return false. If the item is there don't click it
        }
    }

    public IncludeOrExcludeJobResults alwaysIncludeJob(Job job, Preferences preferences, WebDriver driver, Cache cache, MongoClient client) {
        List<IncludeOrSkipJobFilter> includeOrSkipFilters = new ArrayList<>();

        Optional<IncludeOrSkipJobFilter> alwaysIncludeFilter = FilterFactory.getAlwaysIncludeFilters(preferences).stream().filter(f -> f.include(preferences, job) != null).findFirst();
        if (alwaysIncludeFilter.isPresent()) {
            return new IncludeOrExcludeJobResults(true, false, false, alwaysIncludeFilter.get(), null);
        } else {
            return null;
        }

    }

    /**
     * These filters either don't have enough shallow data or are not important enough to run until after we have ran
     * all always include/exclude filters.
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

        includeOrSkipFilter = FilterFactory.getIncludeFilters(preferences).stream().filter(f -> f.include(preferences, job) != null).findFirst();
        if (includeOrSkipFilter.isPresent()) {
            includeOrSkipFilters.addAll(includeOrSkipFilter.stream().filter(f -> f.include(preferences, job) != null).collect(Collectors.toList()));
            return new IncludeOrExcludeJobResults(true, false, false, includeOrSkipFilter.get(), null);
        }

        Optional<ExcludeJobFilter> firstExcludeFilter = FilterFactory.getExcludeFilters(preferences).stream().filter(f -> f.exclude( job) != null).findFirst();
        if (firstExcludeFilter.isPresent()) {
            doubleClickOnElement(driver, job.getHideButton());
            return new IncludeOrExcludeJobResults(false, true, false, null, firstExcludeFilter.get());
        }

        //default state is to include the job but with no reasons why
        return new IncludeOrExcludeJobResults(true, false, false, null, null);
    }

    private static String buildCompanySummaryURL(Job job) {

        String url = null;
        //TODO get summarizing working again and readd this
/*
        if (CompanyUtil.isRecruiting(job.getCompanyName(), job.getIndustries()) && job.getRecruiterClient() != null) {
            url = "http://localhost:5000/company/summarize/" + addValueToUrl(url, null, job.getRecruiterClient(), true);
        } else if (!CompanyUtil.isRecruiting(job.getCompanyName(), job.getIndustries())) {
            url = "http://localhost:5000/company/summarize/" + addValueToUrl(url, null, job.getCompanyName(), true);
        }

        //if this is a recruiter don't use the number of employees, industry as they don't apply to recruiting client
        if (!CompanyUtil.isRecruiting(job.getCompanyName(), job.getIndustries())) {
            url = addValueToUrl(url, "industry", job.getJobIndustry(), true);
            url = addValueToUrl(url, "location", job.getLocation(), true);
            url = addValueToUrl(url, "minEmployees", job.getMinimumNumEmployees(), false);
            if (job.getMaximumNumEmployees() != null && job.getMinimumNumEmployees() != null && !job.getMaximumNumEmployees().equals(job.getMinimumNumEmployees())) {
                url = addValueToUrl(url, "maxEmployees", job.getMaximumNumEmployees(), false);
            }
        }

 */
        return url;
    }

    private static String addValueToUrl(String url, String newValueName, Object newValue, boolean isString) {
        StringBuilder builder = new StringBuilder();
        if (newValue != null) {
            if (newValueName != null) {
                if (url.contains("?")) {
                    builder.append("&");
                } else {
                    builder.append("?");
                }
                builder.append(newValueName + "=");
            }
            if (isString) {
                try {
                    builder.append(URLEncoder.encode((String) newValue, StandardCharsets.UTF_8.toString()).replaceAll("\\+", "%20"));
                } catch (UnsupportedEncodingException e) {
                    builder.append(newValue.toString());
                }
            } else {
                builder.append(newValue.toString());
            }
        }
        if (url == null) {
            url = builder.toString();
        } else {
            url += builder.toString();
        }
        return url;
    }


    private void incorporateSummary(Job job, CompanySummary summary) {
        if (summary.getSector() != null) {
            job.getIndustries().add(summary.getSector());
        }
        if (!CompanyUtil.isRecruiting(job.getCompanyName(), job.getIndustries())) {
            summary.setName(job.getCompanyName());
        } else if (job.getRecruiterClient() != null) {
            summary.setName(job.getRecruiterClient());
        }
        job.setCompany(summary);
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
                    success = deepJobPopulator.populateJob(job, driver,preferences);
                    numAttempts++;
                    if (!success && numAttempts < maxAttempts) {
                        if (job.getJobDetailsLink() != null) {
                            doubleClickOnElement(driver, job.getJobDetailsLink());
                            Thread.sleep(5_000);
                        }
                    }
                } while (!success && numAttempts < maxAttempts);
            }

        } catch (java.util.concurrent.TimeoutException | InterruptedException te) {
            success = false;
        }
        if (success) {
            CompanySummary summary = cache.getCompanySummary(job, mongoClient);
            if (cache.containsJob(job, mongoClient)) {
                deepCachedJobs.add(job);
                runDeepPopulatedFilters(driver, job);
                cached = true;
            }

            if (summary == null) {
                String companySummaryURL = buildCompanySummaryURL(job);
                if (companySummaryURL != null) {
                    summary = RESTUtil.callUrl(companySummaryURL);
                }
            }
            if (summary != null) {
                incorporateSummary(job, summary);
                cache.addCompanySummary(summary, job, mongoClient);
            }
        }
        return cached;
    }

    private void runDeepPopulatedFilters(WebDriver driver, Job currentJob) {
        try {
            doubleClickOnElement(driver, currentJob.getHideButton());
        } catch (MoveTargetOutOfBoundsException ex) {
            //swallow this so we continue to remaining work
            //it is not a big deal if we don't hide one job
            System.err.println("out of bounds: " + currentJob);
            /*
            System.err.println(STR."out of bounds: \{currentJob}");
            String name = "Duke";
            String info = STR."My name is \{name}";
            System.out.println(info);

             */
        }
    }


    protected ShallowJobPopulator getShallowJobPopulator() {
        return new LinkedInShallowJobPopulator();
    }


    private Elements getJobItems(WebDriver driver) {
        WebElement ul = null;
        Elements items = new Elements();
        try {
            ul = driver.findElement(By.className("scaffold-layout__list-detail-container"));
        } catch (NoSuchElementException nse) {
            throw nse;
        }

        if (ul != null) {
            Document doc = Jsoup.parse(ul.getAttribute("innerHTML"));
            items = doc.select("li.scaffold-layout__list-item");
        }
        return items;
    }

    private static String getNumResultsTextFromElement(Element span) {
        return span.text().replaceAll("<!---->", "").split(" ")[0];
    }


    private String getNumResultsStringCollections(WebDriver driver) {
        WebElement resultsDiv = driver.findElement(By.className("jobs-search-results-list__title-heading"));
        Document doc = Jsoup.parse(resultsDiv.getAttribute("innerHTML"));
        String numberText = "";
        for (Element small : doc.getElementsByTag("small")) {
            if (small.text().contains("results")) {
                numberText = getNumResultsTextFromElement(small);
            }
        }

        return numberText;
    }


    /**
     * I would like to rely on the simple page source to have this step move quickly and without worrying about DOM
     * problems that being said you can't rely on:
     * Are these results helpful?
     * Help Center
     * Accessibility
     * <p>
     * consistently being there
     *
     * @param driver
     * @return
     */
    private boolean isPageFullyLoaded(WebDriver driver) {
        String pageSource = driver.getPageSource();
        if (pageSource.length() < 1000) {
            return false;
        }
        if (pageSource.contains("There was a problem loading your filters")) {
            return false;
        }

        WebElement resultsDiv = null;
        try {
            resultsDiv = driver.findElement(By.className("jobs-search-results-list__subtitle"));
        } catch (NoSuchElementException nse) {
            return false;
        }
        Optional<Integer> currentNumberOfResults = getCurrentNumberOfResults(driver, resultsDiv);
        if (currentNumberOfResults.isPresent() && currentNumberOfResults.get() > RESULTS_PER_PAGE) {
            WebElement paginationDiv = null;
            try {
                paginationDiv = driver.findElement(By.className("jobs-search-results-list__pagination"));
            } catch (NoSuchElementException nse) {
                driver.navigate().refresh();
                try {
                    Thread.sleep(4_000);
                } catch (Exception ex) {

                }
            }
            if (paginationDiv == null) {
                return false;
            }
        }
        return true;
    }

    private boolean noMatchingJobs(WebDriver driver) {
        String pageSource = driver.getPageSource();
        if (pageSource.contains("No matching jobs found")) {
            return true;
        } else {
            return false;
        }
    }

    private void waitForPageToLoad(WebDriver driver) {
        int maxTries = 10, numTries = 0;

        while ((noMatchingJobs(driver) || !isPageFullyLoaded(driver))
                && numTries < maxTries) {
            driver.navigate().refresh();//sometimes the page has been loaded wrong
            try {
                Thread.sleep(12_000);
            } catch (InterruptedException ex) {

            }
            numTries++;
        }
    }

    private Optional<Integer> getCurrentNumberOfResults(WebDriver driver) {
        WebElement resultsDiv = null;
        try {
            resultsDiv = driver.findElement(By.className("jobs-search-results-list__subtitle"));
        } catch (NoSuchElementException nse) {
            return Optional.empty();
        }
        return getCurrentNumberOfResults(driver, resultsDiv);
    }

    private Optional<Integer> getCurrentNumberOfResults(WebDriver driver, WebElement resultsDiv) {
        if (resultsDiv != null) {
            String innerHTML = resultsDiv.getAttribute("innerHTML");
            int resultsLoc = innerHTML.indexOf(" results");
            if (resultsLoc > 0) {
                int start = innerHTML.indexOf("<!---->") + "<!---->".length();
                if (start < resultsLoc) {
                    String results = innerHTML.substring(start, resultsLoc).replaceAll(",", "");
                    if (NumberUtils.isCreatable(results)) {
                        return Optional.of(Integer.parseInt(results));
                    }
                } else if (innerHTML.indexOf("0 results") > 0) {
                    return Optional.of(0);
                }
            } else if (innerHTML.indexOf(" result") > 0) {
                return Optional.of(1);
            } else {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    private List<WebElement> getNextPageButtons(WebDriver driver, Integer currentPage) {
        List<WebElement> nextPageButtons = new ArrayList<>();
        try {

            WebElement nextPageButton = driver.findElement(By.className("jobs-search-pagination__button--next"));
            nextPageButtons.add(nextPageButton);
        } catch (NoSuchElementException nse) {
            //this usually  happens when we look for a next page that is not there
        }

        try {

            WebElement pageDiv = driver.findElement(By.className("jobs-search-results-list__pagination"));
            Document pageDoc = Jsoup.parse(pageDiv.getAttribute("innerHTML"));
            String attributeVal = "Page " + ++currentPage;
            WebElement pageNumberButton = driver.findElement(By.cssSelector("button[aria-label='" + attributeVal + "']"));
            nextPageButtons.add(pageNumberButton);
        } catch (NoSuchElementException nse) {
            //this usually  happens when we look for a next page that is not there
        }

        return nextPageButtons;
    }


    public static void main(String[] args) {
        LinkedInExtractor e = new LinkedInExtractor();


    }


}
