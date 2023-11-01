package com.goodforallcode.jobExtractor.filters.include;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class SpringFilter implements JobFilter {
    List<String> phrases =List.of("Spring","Spring Boot","Spring Data","Spring MVC");
    public boolean include(Preferences preferences, Job job){
        if (job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            if (phrases.stream().filter(p -> description.contains(p)).count() > 1) {
                System.err.println("spring -> include: " + job);
                return true;
            }
        }
        return false;

    }
}
