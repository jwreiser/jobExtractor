package com.goodforallcode.jobExtractor.filters.deep.always;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class MainframeFilter implements JobFilter {
    List<String> phrases =List.of("Mainframe","AS400","RPG","z/OS","Adabas");

    @Override
    public boolean include(Preferences preferences, Job job) {
        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();

            if (phrases.stream().anyMatch(k -> description.contains(k.toLowerCase()))) {
                System.err.println("Mainframe ->reject: " + job);
                return false;
            }
        }
        return true;
    }
}
