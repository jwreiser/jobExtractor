package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

/**
 * Scalability might show up in a mon senior position so this probably should not auto exclude
 */
public class ComplexFilter implements JobFilter {
    /**
     * Exceptions
     * Strong: strong designing
     * infrastructure: spent last 10 years building infrastructure
     */
    List<String> phrases =List.of("scaling","scalable","latency");
    public boolean include(Preferences preferences, Job job){
        if(!preferences.isExcludeComplexJobs()){
            return true;
        }
        String description =job.getDescription().toLowerCase();
        if (phrases.stream().anyMatch(p->description.contains(p))) {
            System.err.println("complex ->reject: " + job);
            return false;
        }
        return true;

    }
}
