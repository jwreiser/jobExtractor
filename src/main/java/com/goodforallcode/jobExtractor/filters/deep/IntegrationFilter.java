package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class IntegrationFilter implements JobFilter {

    List<String> bothPhrases =List.of("Mulesoft","Boomi ");

    public boolean include(Preferences preferences, Job job){
        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            if (bothPhrases.stream().anyMatch(p -> description.contains(p.toLowerCase()))) {
                System.err.println("Integration->reject: " + job);
                return false;
            }
        }
     return true;

 }
}
