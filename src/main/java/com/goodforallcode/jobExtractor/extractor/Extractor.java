package com.goodforallcode.jobExtractor.extractor;

import com.goodforallcode.jobExtractor.cache.Cache;
import com.goodforallcode.jobExtractor.cache.MongoDbCache;
import com.goodforallcode.jobExtractor.filters.ExcludeJobFilter;
import com.goodforallcode.jobExtractor.filters.FilterFactory;
import com.goodforallcode.jobExtractor.filters.IncludeOrSkipJobFilter;
import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.google.common.collect.Lists;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static com.goodforallcode.jobExtractor.cache.MongoDbCache.uri;

public abstract class Extractor {


    public List<String> getSharedSkillPhrases() {
        return sharedSkillPhrases;
    }

    /**
     * Login if necessary to get a useful driver.
     * @param userName
     * @param password
     * @return
     */
    public WebDriver getDriver(String userName, String password) {
        return null;
    }


    public List<String> sharedSkillPhrases = List.of("Agile", "Java", "integration",
            "sharepoint", "SOAP",
            "linux", "unix",
            "JPA", "Hibernate",
            "android", "mobile",
            "nosql", "cassandra", "mongodb", "mongo",
            "rdbms", "SQL", "relational",
            "test driven development", "TDD",
            "docker", "kubernetes",
            "Kafka", "JMS", "RabbitMQ",
            "PeopleSoft", "Appian", "GIS",
            "Electrical");
    public static List<String> standAloneSkillPhrases = List.of(
            "TMS", "embedded", "mulesoft");



    public JobResult getJobs(Set<Cookie> cookies, Preferences preferences, List<String> urls,WebDriver driver) {
        List<Job> acceptedJobs = new ArrayList<>();
        List<Job> rejectedJobs = new ArrayList<>();
        List<Job> shallowCachedJobs = new ArrayList<>();
        List<Job> deepCachedJobs = new ArrayList<>();
        Cache cache = new MongoDbCache();
        int totalJobs=0,totalHidden=0,totalSkipped=0,totalPages=0;

        int numThreads = 3;

        Collection<List<String>> urlLists = breakListIntoThreadSizeChunks(urls, numThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        Collection<UrlExctractingCallable> tasks = new ArrayList<>();
        try (MongoClient mongoClient = MongoClients.create(uri)) {

            for (List<String> urlList : urlLists) {
                tasks.add(new UrlExctractingCallable(this, urlList, cookies, preferences, cache,mongoClient,driver));
            }
            List<Future<JobResult>> futures = new ArrayList<>();
            try {
                futures.addAll(executorService.invokeAll(tasks));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            boolean done;
            while (true) {
                done = true;
                for (Future future : futures) {
                    if (!future.isDone()) {
                        done = false;
                        try {
                            Thread.sleep(10_000);
                        } catch (InterruptedException e) {
                            //not a big deal as long as we can eventually sleep long enough to wait
                        }
                        break;
                    }
                }
                if (done) {
                    for (Future<JobResult> future : futures) {
                        try {
                            totalJobs+=future.get().totalJobs();
                            totalHidden+=future.get().hiddenJobs();
                            totalSkipped+=future.get().skippedJobs();
                            totalPages+=future.get().numPages();
                            acceptedJobs.addAll(future.get().acceptedJobs());
                            rejectedJobs.addAll(future.get().rejectedJobs());
                            shallowCachedJobs.addAll(future.get().shallowCachedJobs());
                            deepCachedJobs.addAll(future.get().deepCachedJobs());
                        } catch (InterruptedException | ExecutionException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                }
            }
            cache.addRemainingJobs(mongoClient);
            cache.addRemainingCompanySummaries(mongoClient);
        }

        executorService.shutdown();

        return new JobResult(acceptedJobs, rejectedJobs,shallowCachedJobs,deepCachedJobs,totalJobs,totalHidden,totalSkipped,totalPages);
    }

    private static Collection<List<String>> breakListIntoThreadSizeChunks(List<String> urls, int numThreads) {
        int desiredListSize = 1;
        if (urls.size() > numThreads) {
            desiredListSize = urls.size() / numThreads;
        }
        Collection<List<String>> urlLists = Lists.partition(urls, desiredListSize);
        return urlLists;
    }

    abstract JobResult getJobs(Set<Cookie> cookies, Preferences preferences, String url, Cache cache, MongoClient mongoClient,WebDriver driver);

    public void includeJob(WebDriver driver, List<Job> jobs, Job currentJob) {
        //TODO I had been clicking the save button here but it gives stale element exceptions; fix
        jobs.add(currentJob);
    }

    public boolean doubleClickOnElement(WebDriver driver, WebElement element) {
        return doubleClickElement(driver, element);
    }

    public static boolean doubleClickElement(WebDriver driver, WebElement element) {
        boolean success = false;
        if (element == null) {
            return success;
        }
        Actions act = new Actions(driver);

        try {
            //        in FF: we need to scroll the page until the item we need is visible
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
            Thread.sleep(3_000);
            act.moveToElement(element).doubleClick(element).perform();
            Thread.sleep(2_000);
            success= true;
        }catch (StaleElementReferenceException | MoveTargetOutOfBoundsException ex) {
            //there is nothing we can do. We just swallow and move on, hopefully there is another button to try
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        if (success && driver != null && driver.getTitle().equals("Security Verification | LinkedIn")) {
            System.err.println("You need to pass verification to login");
            success=false;
        }
        return success;
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
    protected boolean runFiltersThatMakeSenseForShallowPopulatedJobs(Preferences preferences, Cache cache, MongoClient mongoClient, Job finalJob, Job job, WebDriver driver, List<Job> rejectedJobs, List<Job> acceptedJobs) {
        Optional<ExcludeJobFilter> excludeJobFilter = FilterFactory.getAlwaysExcludeFilters(preferences).stream().filter(f -> f.exclude(finalJob) != null).findFirst();
        if (excludeJobFilter.isPresent()) {
            job.setExcludeFilter(excludeJobFilter.get());
            job.setReason(excludeJobFilter.get().exclude(job));
            cache.addJob(job, false, mongoClient);
            rejectedJobs.add(job);
            return true;
        }

        Optional<JobFilter> jobFilter = FilterFactory.getAlwaysExcludeCustomFilters(preferences).stream().filter(f -> !f.include(preferences, finalJob)).findFirst();
        if (jobFilter.isPresent()) {
            job.setCustomExcludeFilter(jobFilter.get());
            cache.addJob(job, false, mongoClient);
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
     * These filters must be run after the job is fully populated as they are only run when the job won't "always" be included or excluded.
     * @param job
     * @param preferences
     * @param driver
     * @param includeOrSkipFilters
     * @return
     */
    protected IncludeOrExcludeJobResults runFinalFilters(Job job, Preferences preferences, WebDriver driver, List<IncludeOrSkipJobFilter> includeOrSkipFilters) {
        Optional<IncludeOrSkipJobFilter> includeOrSkipFilter;
        includeOrSkipFilter = FilterFactory.getIncludeFilters(preferences).stream().filter(f -> f.include(preferences, job) != null).findFirst();
        if (includeOrSkipFilter.isPresent()) {
            includeOrSkipFilters.addAll(includeOrSkipFilter.stream().filter(f -> f.include(preferences, job) != null).collect(Collectors.toList()));
            return new IncludeOrExcludeJobResults(true, false, false, includeOrSkipFilter.get(), null);
        }

        Optional<ExcludeJobFilter> firstExcludeFilter = FilterFactory.getExcludeFilters(preferences).stream().filter(f -> f.exclude(job) != null).findFirst();
        if (firstExcludeFilter.isPresent()) {
            doubleClickOnElement(driver, job.getHideButton());
            return new IncludeOrExcludeJobResults(false, true, false, null, firstExcludeFilter.get());
        }
        return null;
    }

    protected static boolean morePagesLeft(List<WebElement> nextPageButtons, int numJobs, boolean justSkipped, int resultsPerPage) {
        return nextPageButtons != null && !nextPageButtons.isEmpty() && (justSkipped || numJobs == resultsPerPage);
    }

    protected void moveToNextPage(List<WebElement> nextPageButtons, WebDriver driver, int currentPageNum) {
        System.err.println("Moving to page: " + (currentPageNum + 1));
        for (WebElement nextPageButton : nextPageButtons) {
            if (doubleClickOnElement(driver, nextPageButton)) {
                break;
            }
        }
    }

    protected void handleNonSkipJobResults(Cache cache, MongoClient mongoClient, List<Job> acceptedJobs, List<Job> rejectedJobs, WebDriver newDriver, Job job, IncludeOrExcludeJobResults includeOrExcludeJobResults
            , Preferences preferences) {
        if(includeOrExcludeJobResults==null){//if we don't know what to do with the job include it by default
            cache.addJob(job, true, mongoClient);
            includeJob(newDriver, acceptedJobs, job);
        }else if (includeOrExcludeJobResults.excludeJob()) {
            job.setExcludeFilter(includeOrExcludeJobResults.excludeFilter());
            cache.addJob(job, false, mongoClient);
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

}
