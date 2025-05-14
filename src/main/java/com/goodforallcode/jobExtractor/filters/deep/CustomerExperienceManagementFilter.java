package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class CustomerExperienceManagementFilter implements JobFilter {

    List<String> bothPhrases =List.of("Quadient");

    public boolean include(Preferences preferences, Job job){
        String title = job.getTitle().toLowerCase();
        if (bothPhrases.stream().anyMatch(p -> title.contains(p.toLowerCase()))) {
            return false;
        }
        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            if (bothPhrases.stream().anyMatch(p -> description.contains(p.toLowerCase()))) {
                return false;
            }
        }
     return true;

 }
}
