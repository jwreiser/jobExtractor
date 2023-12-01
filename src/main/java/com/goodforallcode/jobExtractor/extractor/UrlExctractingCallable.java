package com.goodforallcode.jobExtractor.extractor;

import com.goodforallcode.jobExtractor.cache.JobCache;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.openqa.selenium.Cookie;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class UrlExctractingCallable implements Callable<JobResult> {
    Extractor extractor;
    List<String> urls;
    Set<Cookie> cookies;
    Preferences preferences;
    JobCache cache;
    MongoClient mongoClient;

    public UrlExctractingCallable(Extractor extractor, List<String> urls, Set<Cookie> cookies, Preferences preferences, JobCache cache,MongoClient mongoClient) {
        this.urls = urls;
        this.cookies = cookies;
        this.preferences = preferences;
        this.cache = cache;
        this.extractor = extractor;
        this.mongoClient=mongoClient;
    }

    @Override
    public JobResult call() throws Exception {
        List<JobResult> jobResults= urls.stream().map(url ->
                extractor.getJobs(cookies, preferences, url, cache,mongoClient)).collect(Collectors.toList());
        List<Job> acceptedJobs=new ArrayList<>();
        List<Job> rejectedJobs=new ArrayList<>();
        int totalJobs=0,totalHidden=0,totalSkipped=0,totalCached=0;
        for(JobResult result:jobResults){
            totalJobs+= result.totalJobs();
            totalHidden+= result.hiddenJobs();
            totalSkipped+= result.skippedJobs();
            totalCached+= result.cachedJobs();
            acceptedJobs.addAll(result.acceptedJobs());
            rejectedJobs.addAll(result.rejectedJobs());
        }
        return new JobResult(acceptedJobs,rejectedJobs,totalJobs,totalHidden,totalSkipped,totalCached);
    }

}