package com.goodforallcode.jobExtractor.filters.both;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class OffshoreFilter implements JobFilter {
    List<String> companyNames =List.of( "Rockwell Automation","Equinix","Banner Health",
            "Blue Cross NC","Ascension");
    List<String> offShorePhrases =List.of("IT services and outsourcing company");
    @Override
    public boolean include(Preferences preferences, Job job) {


        if(companyNames.stream().anyMatch(k->job.getCompanyName().equals(k))){
            System.err.println("offshore ->reject: "+job);
            return false;
        }

        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            if (offShorePhrases.stream().anyMatch(p -> description.contains(p))) {
                System.err.println("Job offshore->reject: " + job);
                return false;
            }
        }
        return true;
    }
}