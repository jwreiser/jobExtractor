package com.goodforallcode.jobExtractor.filters.shallow.title;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

/**
 * Titles here should be involved with responding to customer issues and have
 * common characteristics (rotation/on call expected at least on occasion)
 */
public class SupportTitleFilter implements JobFilter {
    List<String> jobTitlePhrases=List.of("Support Developer","Support Engineer");

    @Override
    public boolean include(Preferences preferences, Job job) {
        String title =job.getTitle().toLowerCase();

        //if this is a job title we are not qualified for
        if(jobTitlePhrases.stream().anyMatch(t->title.contains(t.toLowerCase()))){
            System.err.println("Sales job title->reject: "+job);
            return false;
        }
        return true;
    }


}