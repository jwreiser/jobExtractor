package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class CaseManagementFilter implements JobFilter {

    List<String> phrases = List.of("Entellitrak", "Documentum");

    public boolean include(Preferences preferences, Job job) {
        if (job.getDescription() != null) {
            String description = job.getDescription().toLowerCase();
            if (phrases.stream().anyMatch(p -> description.contains(p.toLowerCase()))) {
                System.err.println("Case Management->reject: " + job);
                return false;
            }
        }

        return true;

    }
}
