package com.goodforallcode.jobExtractor.extractor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goodforallcode.jobExtractor.cache.Cache;
import com.goodforallcode.jobExtractor.driver.DriverInitializer;
import com.goodforallcode.jobExtractor.filters.FilterFactory;
import com.goodforallcode.jobExtractor.filters.IncludeOrSkipJobFilter;
import com.goodforallcode.jobExtractor.job.populate.job.shallow.NyStateJobsShallowJobPopulator;
import com.goodforallcode.jobExtractor.job.populate.job.shallow.ShallowJobPopulator;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.mongodb.client.MongoClient;
import lombok.Getter;
import lombok.Setter;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeoutException;


@Getter
@Setter
public class NyStateJobsExtractor extends Extractor {

    private static int RESULTS_PER_PAGE = 100;



    @Override
    public WebDriver getDriver(String userName, String password) {
        WebDriver driver = DriverInitializer.getWebDriver();
        return driver;

    }

    @Override
    public JobResult getJobs(Set<Cookie> cookies, Preferences preferences, String url, Cache cache, MongoClient mongoClient,WebDriver driver) {
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
        do {
            currentPageNum++;
            justSkipped = false;
                /*
                the current number of results can change as the page loads so we
                don't want to JUST read it right away as it may get lowered
                 */
            try {
                Thread.sleep(10_000);//time to load the page and not overwhelm the server
            } catch (InterruptedException ex) {

            }

            WebElement itemsDropDown=driver.findElement(By.name("vacancyTable_length"));
            itemsDropDown.sendKeys(String.valueOf(RESULTS_PER_PAGE));
            nextPageButtons = getNextPageButtons(driver, currentPageNum);


            List<WebElement> items = getJobItems(driver);

            ShallowJobPopulator shallowPopulator = getShallowJobPopulator();
            hiddenJobs = 0;
            skippedJobs = 0;
            cachedJobs = 0;
            everyJobHiddenCachedOrSkipped = false;
            numJobs = items.size();
            Job job;
            for (WebElement item : items) {

                totalJobs++;

                try {
                    job = shallowPopulator.populateJob(item, null,driver,preferences,null);
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
            if (morePagesLeft(nextPageButtons, numJobs, justSkipped,RESULTS_PER_PAGE)) {
                moveToNextPage(nextPageButtons, driver, currentPageNum);
            }
        } while (morePagesLeft(nextPageButtons, numJobs, justSkipped,RESULTS_PER_PAGE));


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


    protected ShallowJobPopulator getShallowJobPopulator() {
        return new NyStateJobsShallowJobPopulator();
    }


    private List<WebElement> getJobItems(WebDriver driver) {
        WebElement div = null;
        List<WebElement> items = new ArrayList<>();
        try {
            div = driver.findElement(By.id("vacancyTable"));
        } catch (NoSuchElementException nse) {
            throw nse;
        }

        Element element = null;

        if (div != null) {
            WebElement tbody = div.findElement(By.tagName("tbody"));
            if(tbody!=null){
                items = tbody.findElements(By.tagName("tr"));
            }
        }
        return items;
    }


    private List<WebElement> getNextPageButtons(WebDriver driver, Integer currentPage) {
        List<WebElement> nextPageButtons = new ArrayList<>();
        try {

            WebElement nextPageButton = driver.findElement(By.xpath("//button[text()='"+(currentPage + 1) + "']"));
            nextPageButtons.add(nextPageButton);
        } catch (NoSuchElementException nse) {
            //this usually  happens when we look for a next page that is not there
        }


        return nextPageButtons;
    }



}
