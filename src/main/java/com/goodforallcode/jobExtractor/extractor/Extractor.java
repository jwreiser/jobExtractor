package com.goodforallcode.jobExtractor.extractor;

import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CacheUtil;
import com.google.common.collect.Lists;
import com.mongodb.client.MongoClient;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;


public abstract class Extractor {
    CacheUtil cacheUtil;
    public  void setCacheUtil(CacheUtil cacheUtil){
        this.cacheUtil = cacheUtil;
    }

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

    public static WebDriver getWebDriver(String url) throws SessionNotCreatedException{
        WebDriver driver = null;

        Capabilities capabilities = new FirefoxOptions();
        String remoteUrl = "http://hub:4444/wd/hub";
        URL dockerUrl =null;
        try {
            dockerUrl =new URL(remoteUrl);
        }catch (MalformedURLException malformedURLException){
            return null;
        }

        int trialNum=1,cutOff=20;
        while (driver == null && trialNum++ < cutOff) {
            try {
                driver = new RemoteWebDriver(dockerUrl, capabilities);
//                System.setProperty("webdriver.gecko.driver", "D:/development/geckodriver.exe");
//                driver = new FirefoxDriver();

            } catch (SessionNotCreatedException sessionNotCreatedException) {
                System.err.println("SessionNotCreatedException for" + url + ": Trial: " + trialNum);
                trialNum++;
                if(trialNum>cutOff){
                    throw sessionNotCreatedException;
                }
                try{
                    Thread.sleep(240_000);//4 minutes
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
//        WebDriver driver=new HtmlUnitDriver(true);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        return driver;
    }

    public JobResult getJobs(Set<Cookie> cookies, Preferences preferences, List<String> urls) {
        List<Job> acceptedJobs = new ArrayList<>();
        List<Job> rejectedJobs = new ArrayList<>();
        List<Job> shallowCachedJobs = new ArrayList<>();
        List<Job> deepCachedJobs = new ArrayList<>();

        int totalJobs = 0, totalHidden = 0, totalSkipped = 0, totalPages = 0;

        int numThreads = 6;

        Collection<List<String>> urlLists = breakListIntoThreadSizeChunks(urls, numThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        Collection<UrlExctractingCallable> tasks = new ArrayList<>();

        for (List<String> urlList : urlLists) {
            tasks.add(new UrlExctractingCallable(this, urlList, cookies, preferences));
        }
        System.err.println("Starting " + tasks.size() + " tasks");
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
                        totalJobs += future.get().totalJobs();
                        totalHidden += future.get().hiddenJobs();
                        totalSkipped += future.get().skippedJobs();
                        totalPages += future.get().numPages();
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
        cacheUtil.addRemainingJobs();
        System.out.println("Adding remaining summaries");
        cacheUtil.addRemainingSummaries();
        System.out.println("Added remaining summaries");

        executorService.shutdown();
        System.out.println("ExecutorService shutdown");

        return new JobResult(acceptedJobs, rejectedJobs, shallowCachedJobs, deepCachedJobs, totalJobs, totalHidden, totalSkipped, totalPages);
    }

    private static Collection<List<String>> breakListIntoThreadSizeChunks(List<String> urls, int numThreads) {
        int desiredListSize = 1;
        if (urls.size() > numThreads) {
            desiredListSize = urls.size() / numThreads;
        }
        Collection<List<String>> urlLists = Lists.partition(urls, desiredListSize);
        return urlLists;
    }

    abstract JobResult getJobs(Set<Cookie> cookies, Preferences preferences, String url, MongoClient mongoClient);


    public void doubleClickOnElement(WebDriver driver, WebElement element,boolean exclude) {
        doubleClickElement(driver, element,exclude);
    }

    public static void doubleClickElement(WebDriver driver, WebElement element,boolean exclude) {
        if (!exclude && element == null) {
            return;//TODO not sure what to do here, figure it out
        }
        Actions act = new Actions(driver);

        try {
            //        in FF: we need to scroll the page until the item we need is visible
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
            Thread.sleep(3_000);

            act.moveToElement(element).doubleClick(element).perform();
            Thread.sleep(2_000);
        } catch (StaleElementReferenceException ex) {
            //once there is a stale element there is nothing we can do. We just swallow and move on
        } catch (MoveTargetOutOfBoundsException ex) {
          //we've moved out of bounds. I think this means we've scrolled the page and then some
        } catch (JavascriptException ex) {
            //this is on the page, it's probably not our fault
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
/*
    public static void main(String[] args) {
        try {
            WebDriver driver = getWebDriver();
            driver.get("https://the-internet.herokuapp.com/");
            System.out.println(driver.getTitle());
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


 */
}
