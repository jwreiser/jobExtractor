package com.goodforallcode.jobExtractor.extractor;

import com.goodforallcode.jobExtractor.cache.JobCache;
import com.goodforallcode.jobExtractor.cache.MongoDbJobCache;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.google.common.collect.Lists;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

import static com.goodforallcode.jobExtractor.cache.MongoDbJobCache.uri;

public abstract class Extractor {



    public List<String> getSharedSkillPhrases() {
        return sharedSkillPhrases;
    }

    public WebDriver login(String userName, String password) {
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

    public static WebDriver getWebDriver() {
        System.setProperty("webdriver.gecko.driver", "D:/development/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
//        WebDriver driver=new HtmlUnitDriver(true);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        return driver;
    }

    public JobResult getJobs(Set<Cookie> cookies, Preferences preferences, List<String> urls) {
        List<Job> acceptedJobs = new ArrayList<>();
        List<Job> rejectedJobs = new ArrayList<>();
        List<Job> shallowCachedJobs = new ArrayList<>();
        List<Job> deepCachedJobs = new ArrayList<>();
        JobCache cache = new MongoDbJobCache();
        int totalJobs=0,totalHidden=0,totalSkipped=0,totalPages=0;

        int numThreads = 8;

        Collection<List<String>> urlLists = breakListIntoThreadSizeChunks(urls, numThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        Collection<UrlExctractingCallable> tasks = new ArrayList<>();
        try (MongoClient mongoClient = MongoClients.create(uri)) {

            for (List<String> urlList : urlLists) {
                tasks.add(new UrlExctractingCallable(this, urlList, cookies, preferences, cache,mongoClient));
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

    abstract JobResult getJobs(Set<Cookie> cookies, Preferences preferences, String url, JobCache cache, MongoClient mongoClient);

    public void includeJob(WebDriver driver, List<Job> jobs, Job currentJob) {
        //I had been clicking the save button here but it gives stale element exceptions
        jobs.add(currentJob);
    }

    public void doubleClickOnElement(WebDriver driver, WebElement element) {
        doubleClickElement(driver, element);
    }

    public static void doubleClickElement(WebDriver driver, WebElement element) {
        if (element == null) {
            System.err.println("Element was null");
            return;
        }
        Actions act = new Actions(driver);

        try {
            //        in FF: we need to scroll the page until the item we need is visible
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
            Thread.sleep(3_000);
            act.moveToElement(element).doubleClick(element).perform();
            Thread.sleep(2_000);
        }catch (StaleElementReferenceException ex) {
            //once there is a stale element there is nothing we can do. We just swallow and move on
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
