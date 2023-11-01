package com.goodforallcode.jobExtractor.filters.both;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class ComputerVisionFilter implements JobFilter {
    List<String>
            bothPhrases =List.of("Computer Vision","computer-vision");
    @Override
    public boolean include(Preferences preferences, Job job) {
        final String title= job.getTitle().toLowerCase();
        if (bothPhrases.stream().anyMatch(k -> job.getTitle().contains(k.toLowerCase()))) {
            System.err.println("computer vision title ->reject: " + job);
            return false;
        }
        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            if (bothPhrases.stream().anyMatch(k -> description.contains(k.toLowerCase()))) {
                System.err.println("computer vision description ->reject: " + job);
                return false;
            }
        }
        return true;
    }
}
