package com.goodforallcode.jobExtractor.filters.shallow.title;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class CoordinationAndManagementTitleFilter implements JobFilter {
    List<String> jobTitlePhrases=List.of(
            "Manager","Systems engineer","System engineer","System Analyst"
            ,"Systems Analyst","developer advocate","developer evangelist"
            );
    @Override
    public boolean include(Preferences preferences, Job job) {
        String title =job.getTitle().toLowerCase();

        //if this is a job title we are not qualified for
        if(jobTitlePhrases.stream().anyMatch(t->title.contains(t.toLowerCase()))){
            System.err.println("Coordination and Management job title->reject: "+job);
            return false;
        }
        return true;
    }


}
