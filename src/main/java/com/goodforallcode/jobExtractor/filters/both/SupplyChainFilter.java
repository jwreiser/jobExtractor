package com.goodforallcode.jobExtractor.filters.both;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class SupplyChainFilter implements JobFilter {
    List<String> titlePhrases =List.of("HighJump");
    @Override
    public boolean include(Preferences preferences, Job job) {
        //case sensitive
        if (titlePhrases.stream().anyMatch(k -> job.getTitle().contains(k))) {
            System.err.println("supply chain title ->reject: " + job);
            return false;
        }
        if(job.getDescription()!=null) {
            String text = job.getDescription().toLowerCase();
        }
        return true;
    }
}
