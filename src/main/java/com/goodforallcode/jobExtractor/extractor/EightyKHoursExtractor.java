package com.goodforallcode.jobExtractor.extractor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goodforallcode.jobExtractor.cache.Cache;
import com.goodforallcode.jobExtractor.driver.DriverInitializer;
import com.goodforallcode.jobExtractor.filters.FilterFactory;
import com.goodforallcode.jobExtractor.filters.IncludeOrSkipJobFilter;
import com.goodforallcode.jobExtractor.job.populate.job.shallow.EightyKShallowJobPopulator;
import com.goodforallcode.jobExtractor.job.populate.job.shallow.ShallowJobPopulator;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.mongodb.client.MongoClient;
import lombok.Getter;
import lombok.Setter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeoutException;


@Getter
@Setter
public class EightyKHoursExtractor extends Extractor {


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

        if(preferences.isRemoteOnly()) {
            List<WebElement> locationDivs = driver.findElements(By.id("tags_location_80k-container"));
            WebElement cityDiv = locationDivs.get(1);
            List<WebElement> cityOptions = cityDiv.findElements(By.cssSelector("input[type='checkbox']"));
            WebElement remoteUsa=cityOptions.get(4);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", remoteUsa);
        }
        if(preferences.isSoftwareSearch()) {
            try {
                Thread.sleep(2_000);//time to load the changes
            } catch (InterruptedException ex) {

            }
            WebElement skillDiv = driver.findElement(By.id("tags_skill-container"));
            List<WebElement> skillOptions = skillDiv.findElements(By.cssSelector("input[type='checkbox']"));
            WebElement software=skillOptions.get(1);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", software);
        }

        boolean staleElement = false;
        ShallowJobPopulator shallowPopulator = getShallowJobPopulator();
        int numTries = 0;
        List<WebElement> items;
        do {
            items = getJobWebElements(driver);
            numTries++;
            totalJobs= 0;
            staleElement = false;
            Element element = null;
            for (WebElement item : items) {

                totalJobs++;

                try {
                    item.click();
                    Document doc = Jsoup.parse(item.getAttribute("innerHTML"));
                    element = doc.firstElementChild();
                } catch (StaleElementReferenceException e) {
                    staleElement = true;//looks like it is always null the first time (and first time only)
                    break;
                }

                boolean continueToNextJob = handleElement(element, shallowPopulator, driver, preferences, cache, mongoClient, url, acceptedJobs, rejectedJobs, shallowCachedJobs, totalJobs);
                item.click();//minimize it to return to default state
            }
        }while (staleElement && numTries<=10);



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

    private boolean handleElement(Element element, ShallowJobPopulator shallowPopulator, WebDriver driver, Preferences preferences, Cache cache, MongoClient mongoClient
                                  , String url, List<Job> acceptedJobs, List<Job> rejectedJobs, List<Job> shallowCachedJobs, int jobIndex) {
        Job job;
        boolean continueToNextJob=false;
        try {
            job = shallowPopulator.populateJob(null,element, driver, preferences,jobIndex);
            if(preferences.isRemoteOnly()) {
                job.setFullyRemote(true);//We are only searching for remote jobs and it's not worth the effort to check if it is remote
            }
        } catch (TimeoutException e) {
            job = null;
        }

        if (job != null) {
            if (job.isHidden() && preferences.isExcludeHiddenJobs()) {
                continueToNextJob = true;
            }
            job.setSourceUrl(url);
            if (preferences.isUsingCache() && cache.containsJob(job, mongoClient)) {
                shallowCachedJobs.add(job);
                continueToNextJob = true;
            }

            final Job finalJob = job;
            Optional<IncludeOrSkipJobFilter> includeOrSkipJobFilter = FilterFactory.getShallowFiltersSkip(preferences).stream().filter(f -> f.include(preferences, finalJob) != null).findFirst();
            if (includeOrSkipJobFilter.isPresent()) {
                continueToNextJob= true;
            }

            if (!runFiltersThatMakeSenseForShallowPopulatedJobs(preferences, cache, mongoClient, finalJob, job, driver, rejectedJobs, acceptedJobs)) {
                IncludeOrExcludeJobResults includeOrExcludeJobResults = runFinalFilters(job, preferences, driver, new ArrayList<>());
                handleNonSkipJobResults(cache, mongoClient, acceptedJobs, rejectedJobs, driver, job, includeOrExcludeJobResults, preferences);
            }
        }
        return continueToNextJob;
    }

    protected ShallowJobPopulator getShallowJobPopulator() {
        return new EightyKShallowJobPopulator();
    }


    private List<WebElement> getJobWebElements(WebDriver driver) {
        WebElement div = null;
        List<WebElement> items= new ArrayList<>();
        try {
            div = driver.findElement(By.className("ais-InfiniteHits"));
        } catch (NoSuchElementException nse) {
            throw nse;
        }

        if (div != null) {
            items=div.findElements(By.tagName("button"));
        }
        return items;
    }




    public static void main(String[] args) {
        EightyKHoursExtractor e = new EightyKHoursExtractor();


    }


}
