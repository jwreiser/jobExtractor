package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

/**
 * This is to screen for jobs that have rigorous functional requirements like performance, latency, etc.
 */
public class ComplexFilter implements JobFilter {
    /**
     * Exceptions
     * Strong: strong designing
     * infrastructure: spent last 10 years building infrastructure
     * scalable:        responsible for designing and implementing testable and scalable code.
     * Scalability       might show up in a mon senior position so this probably should not auto exclude
     * scaling          scaling our company
     */
    List<String> titles =List.of("HPC");
    List<String> phrases =List.of("latency");
    public boolean include(Preferences preferences, Job job){
        if(!preferences.isExcludeComplexJobs()){
            return true;
        }
        String title = job.getTitle().toLowerCase();
        if (titles.stream().anyMatch(p -> title.contains(p))) {
            System.err.println("complex title ->reject: " + job);
            return false;
        }
        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            if (phrases.stream().anyMatch(p -> description.contains(p))) {
                System.err.println("complex ->reject: " + job);
                return false;
            }
        }
        return true;

    }
}
