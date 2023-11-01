package com.goodforallcode.jobExtractor.filters.both;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class DataExchangeFilter implements JobFilter {
    List<String> titlePhrases =List.of("EDI ");
    List<String> descriptionPhrases =List.of(" EDI ");
    @Override
    public boolean include(Preferences preferences, Job job) {
        //case sensitive
        if (titlePhrases.stream().anyMatch(k -> job.getTitle().contains(k))) {
            System.err.println("data exchange title ->reject: " + job);
            return false;
        }
        if(job.getDescription()!=null) {
            String text = job.getDescription().toLowerCase();
            if (descriptionPhrases.stream().anyMatch(k -> text.contains(k.toLowerCase()))) {
                System.err.println("data exchange ->reject: " + job);
                return false;
            }
        }
        return true;
    }
}
