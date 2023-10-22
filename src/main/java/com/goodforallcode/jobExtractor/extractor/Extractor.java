package com.goodforallcode.jobExtractor.extractor;

import com.goodforallcode.jobExtractor.cache.DefaultJobCache;
import com.goodforallcode.jobExtractor.cache.JobCache;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.goodforallcode.jobExtractor.streams.Predicates.distinctByKeys;

public abstract class Extractor {


    public List<String> getSharedSkillPhrases() {
        return sharedSkillPhrases;
    }

    WebDriver login(String userName, String password){
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






    public   List<Job> getJobs(WebDriver driver, Preferences preferences, List<String> urls){
        List<Job> jobs = new ArrayList<>();
        JobCache cache=new DefaultJobCache();
        /*
        int mid=urls.size()/2;
        Collection<List<String>> urlLists = urls.stream().collect(Collectors.partitioningBy(u -> urls.indexOf(u) > mid)).values();
        ExecutorService executorService= Executors.newFixedThreadPool(2);
        Collection<UrlExctractingCallable> tasks=new ArrayList<>();
        for(List<String> urlList:urlLists){
            tasks.add(new UrlExctractingCallable(this,urlList,driver,preferences,cache));
        }
        List<Future<List<Job>>> futures = null;
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
                    try {
                        Thread.sleep(10_000);
                    } catch (InterruptedException e) {
                        //not a big deal as long as we can eventually sleep long enough to wait
                    }
                    continue;
                }
            }
            if(done){
                for(Future<List<Job>> future:futures){
                    try {
                        jobs.addAll(future.get());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } catch (ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                }
                break;
            }
        }
        executorService.shutdown();

         */
        jobs=urls.stream().flatMap(url -> getJobs(driver, preferences, url, cache)).collect(Collectors.toList());
        return jobs;
    }

    abstract Stream<Job> getJobs(WebDriver driver, Preferences preferences, String url, JobCache cache);
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