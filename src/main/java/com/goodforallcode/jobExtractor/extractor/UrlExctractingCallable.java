package com.goodforallcode.jobExtractor.extractor;

import com.goodforallcode.jobExtractor.cache.Cache;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.mongodb.client.MongoClient;
import org.openqa.selenium.Cookie;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

public class UrlExctractingCallable implements Callable<JobResult> {
    Extractor extractor;
    List<String> urls;
    Set<Cookie> cookies;
    Preferences preferences;
    Cache cache;
    MongoClient mongoClient;

    public UrlExctractingCallable(Extractor extractor, List<String> urls, Set<Cookie> cookies, Preferences preferences, Cache cache, MongoClient mongoClient) {
        this.urls = urls;
        this.cookies = cookies;
        this.preferences = preferences;
        this.cache = cache;
        this.extractor = extractor;
        this.mongoClient=mongoClient;
    }

    @Override
    public JobResult call() throws Exception {

        List<JobResult> jobResults=new ArrayList<>();
        for(String url:urls) {
            jobResults.add(extractor.getJobs(cookies, preferences, url, cache, mongoClient));
        }
        List<Job> acceptedJobs=new ArrayList<>();
        List<Job> rejectedJobs=new ArrayList<>();
        List<Job> shallowCachedJobs=new ArrayList<>();
        List<Job> deepCachedJobs=new ArrayList<>();
        int totalJobs=0,totalHidden=0,totalSkipped=0,totalCached=0,totalPages=0;
        for(JobResult result:jobResults){
            totalJobs+= result.totalJobs();
            totalHidden+= result.hiddenJobs();
            totalSkipped+= result.skippedJobs();
            totalPages+= result.numPages();
            acceptedJobs.addAll(result.acceptedJobs());
            rejectedJobs.addAll(result.rejectedJobs());
            shallowCachedJobs.addAll(result.shallowCachedJobs());
            deepCachedJobs.addAll(result.deepCachedJobs());
        }
        return new JobResult(acceptedJobs,rejectedJobs,shallowCachedJobs,deepCachedJobs,totalJobs,totalHidden,totalSkipped,totalPages);
    }

}