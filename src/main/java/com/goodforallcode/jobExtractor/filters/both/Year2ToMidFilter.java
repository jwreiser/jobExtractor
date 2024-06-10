package com.goodforallcode.jobExtractor.filters.both;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

/**
 * Does not include fresher
 */
public class Year2ToMidFilter implements JobFilter {
    static List<String> both =List.of(
            "Early career","Entry level",
    "associate","junior","jr","entry level","intermediate");

    static List<String> titleKeywords =List.of("entry"," early","mid");

    @Override
    public boolean include(Preferences preferences, Job job) {
        String title= job.getTitle().toLowerCase();


        if(titleKeywords.stream().anyMatch(k->title.contains(k.toLowerCase()))){
            System.err.println("middle title ->include: "+job);
            return true;
        }

        if(both.stream().anyMatch(k->title.contains(k.toLowerCase()))){
            System.err.println("middle both title ->include: "+job);
            return true;
        }
        if(job.getDescription()!=null) {
            String description= job.getDescription().toLowerCase();
            if (both.stream().anyMatch(k -> description.contains(k.toLowerCase()))) {
                return true;
            }
        }

        return false;
    }
}
