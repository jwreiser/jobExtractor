package com.goodforallcode.jobExtractor.extractor;

import com.goodforallcode.jobExtractor.cache.DefaultJobCache;
import com.goodforallcode.jobExtractor.cache.JobCache;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.google.common.collect.Lists;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Extractor {


    public List<String> getSharedSkillPhrases() {
        return sharedSkillPhrases;
    }

    public WebDriver login(String userName, String password){
        return null;
    }


    public List<String>sharedSkillPhrases=List.of("Agile","Java","integration",
            "sharepoint","SOAP",
            "linux","unix",
            "JPA","Hibernate",
            "android","mobile",
            "nosql","cassandra","mongodb","mongo",
            "rdbms","SQL","relational",
            "test driven development","TDD",
            "docker","kubernetes",
            "Kafka","JMS","RabbitMQ",
            "PeopleSoft","Appian","GIS",
            "Electrical");
    public static List<String> standAloneSkillPhrases =List.of(
            "TMS","embedded", "mulesoft");






    public   JobResult getJobs(Set<Cookie> cookies , Preferences preferences, List<String> urls){
        List<Job> acceptedJobs = new ArrayList<>();
        List<Job> rejectedJobs = new ArrayList<>();
        JobCache cache=new DefaultJobCache();
        int numThreads=8;
        int size=1;
        if(urls.size()>numThreads){
            size=urls.size()/numThreads;
        }
        Collection<List<String>> urlLists =  Lists.partition(urls, size);
        ExecutorService executorService= Executors.newFixedThreadPool(numThreads);
        Collection<UrlExctractingCallable> tasks=new ArrayList<>();
        for(List<String> urlList:urlLists){
            tasks.add(new UrlExctractingCallable(this,urlList,cookies,preferences,cache));
        }
        List<Future<JobResult>> futures = null;
        try {
            futures = executorService.invokeAll(tasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        boolean done;
        while(true)
        {
            done=true;
            for(Future future:futures){
                if(!future.isDone()){
                    done=false;
                    try {
                        Thread.sleep(10_000);
                    } catch (InterruptedException e) {
                        //not a big deal as long as we can eventually sleep long enough to wait
                    }
                    break;
                }
            }
            if(done){
                for(Future<JobResult> future:futures){
                    try {
                        acceptedJobs.addAll(future.get().acceptedJobs());
                        rejectedJobs.addAll(future.get().rejectedJobs());
                    } catch (InterruptedException|ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                }
                break;
            }
        }
        executorService.shutdown();

        return new JobResult(acceptedJobs,rejectedJobs);
    }

    abstract JobResult getJobs( Set<Cookie> cookies , Preferences preferences, String url, JobCache cache);
    public  void includeJob(WebDriver driver, List<Job> jobs, Job currentJob) {
        //I had been clicking the save button here but it gives stale element exceptions
        jobs.add(currentJob);
    }

    public  void doubleClickOnElement(WebDriver driver, WebElement element) {
        if(element==null){
            System.err.println("Element was null");
            return;
        }
        Actions act=new Actions(driver);

        //        in FF: we need to scroll the page until the item we need is visible
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        try{
            Thread.sleep(1_000);
        }catch (Exception ex){

        }
        act.moveToElement(element).doubleClick().perform();
        try{
            Thread.sleep(1_000);
        }catch (Exception ex){

        }
    }
}
