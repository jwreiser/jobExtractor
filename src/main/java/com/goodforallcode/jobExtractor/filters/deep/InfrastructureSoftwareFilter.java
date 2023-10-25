package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class InfrastructureSoftwareFilter implements JobFilter {

    List<String> phrases = List.of("Tibco");

    public boolean include(Preferences preferences, Job job) {
        String title = job.getTitle().toLowerCase();
        if (phrases.stream().anyMatch(p -> title.contains(p.toLowerCase()))) {
            System.err.println("Infrastructure Software title ->reject: " + job);
            return false;
        }
        if (job.getDescription() != null) {
            String description = job.getDescription().toLowerCase();
            if (phrases.stream().anyMatch(p -> description.contains(p.toLowerCase()))) {
                System.err.println("Infrastructure Software ->reject: " + job);
                return false;
            }
        }

        return true;

    }
}
