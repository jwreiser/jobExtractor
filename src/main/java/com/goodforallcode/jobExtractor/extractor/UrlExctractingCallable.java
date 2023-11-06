package com.goodforallcode.jobExtractor.extractor;

import com.goodforallcode.jobExtractor.cache.JobCache;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
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

    public UrlExctractingCallable(Extractor extractor, List<String> urls, Set<Cookie> cookies, Preferences preferences, JobCache cache) {
        this.urls = urls;
        this.cookies = cookies;
        this.preferences = preferences;
        this.cache = cache;
        this.extractor = extractor;
    }

    @Override
    public JobResult call() throws Exception {
        List<JobResult> jobResults= urls.stream().map(url -> extractor.getJobs(cookies, preferences, url, cache)).collect(Collectors.toList());
        List<Job> acceptedJobs=new ArrayList<>();
        List<Job> rejectedJobs=new ArrayList<>();
        for(JobResult result:jobResults){
            acceptedJobs.addAll(result.acceptedJobs());
            rejectedJobs.addAll(result.rejectedJobs());
        }
        return new JobResult(acceptedJobs,rejectedJobs);
    }

}