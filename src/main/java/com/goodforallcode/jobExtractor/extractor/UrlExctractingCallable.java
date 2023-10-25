package com.goodforallcode.jobExtractor.extractor;

import com.goodforallcode.jobExtractor.cache.JobCache;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import org.openqa.selenium.Cookie;

import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class UrlExctractingCallable implements Callable<List<Job>> {
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
    public List<Job> call() throws Exception {
        List<Job> jobs= urls.stream().flatMap(url -> extractor.getJobs(cookies, preferences, url, cache)).collect(Collectors.toList());
        return jobs;
    }

}