package com.goodforallcode.jobExtractor.filters.shallow.title;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class AgileRoleTitleFilter implements JobFilter {
    List<String> jobTitlePhrases=List.of(
            "RTE Engineer","Release Train Engineer","Product Owner"
            );
    @Override
    public boolean include(Preferences preferences, Job job) {
        String title =job.getTitle().toLowerCase();
        if(jobTitlePhrases.stream().anyMatch(t->title.contains(t.toLowerCase()))){
            System.err.println("Agile job title->reject: "+job);
            return false;
        }
        return true;
    }


}
