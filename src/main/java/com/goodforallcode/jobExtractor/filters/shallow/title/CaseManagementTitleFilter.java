package com.goodforallcode.jobExtractor.filters.shallow.title;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class CaseManagementTitleFilter implements JobFilter {

    List<String> phrases = List.of("Entellitrak", "Documentum");

    public boolean include(Preferences preferences, Job job) {
        final String title = job.getTitle().toLowerCase();
        if (phrases.stream().anyMatch(p -> title.contains(p.toLowerCase()))) {
            System.err.println("Case Management title ->reject: " + job);
            return false;
        }
        return true;

    }
}
