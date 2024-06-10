package com.goodforallcode.jobExtractor.filters.include;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class ModernizingMicroservicesFilter implements JobFilter {
    List<String> phrases =List.of("microservice","microservices","modernization"," API "," APIs ",
            "RestFul","webservice","web service");
    public boolean include(Preferences preferences, Job job){
        if (job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            if (phrases.stream().anyMatch(p -> description.contains(p.toLowerCase()))) {
                return true;
            }
        }
        return false;

    }
}
