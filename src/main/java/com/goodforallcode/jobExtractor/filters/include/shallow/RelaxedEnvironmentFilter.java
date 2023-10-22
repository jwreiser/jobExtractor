package com.goodforallcode.jobExtractor.filters.include.shallow;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class RelaxedEnvironmentFilter implements JobFilter {
    List<String> companies =List.of("State Farm");
    public boolean include(Preferences preferences, Job job){
        if (companies.stream().anyMatch(c->job.getCompanyName().equals(c))) {
            System.err.println("Relaxed -> include: " + job);
            return true;
        }

        return false;

    }
}
