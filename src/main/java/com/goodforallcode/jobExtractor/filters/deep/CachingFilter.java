package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class CachingFilter implements JobFilter {
    List<String>keywords=List.of("ehcache", "memcache", "redis ");

    @Override
    public boolean include(Preferences preferences, Job job) {
        String text =job.getDescription().toLowerCase();

        if(keywords.stream().filter(k->text.contains(k.toLowerCase())).count()>1){
            System.err.println("caching ->reject: "+job);
            return false;
        }
        return true;
    }
}
