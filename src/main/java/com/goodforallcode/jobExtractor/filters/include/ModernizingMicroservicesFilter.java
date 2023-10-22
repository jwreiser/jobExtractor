package com.goodforallcode.jobExtractor.filters.include;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class ModernizingMicroservicesFilter implements JobFilter {
    List<String> phrases =List.of("microservice","microservices","modernization"," API "," APIs ",
            "RestFul","webservice");
    public boolean include(Preferences preferences, Job job){
        String description =job.getDescription().toLowerCase();
        if (phrases.stream().anyMatch(p->description.contains(p.toLowerCase()))) {
            System.err.println("microservice or modernization -> include: " + job);
            return true;
        }
        return false;

    }
}
