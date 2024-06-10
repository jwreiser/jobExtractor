package com.goodforallcode.jobExtractor.filters.shallow.title;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class OpenCallTitleFilter implements JobFilter {
    List<String> jobTitlePhrases=List.of(
            "Engineering Prospects","open call","Expression of Interest"
            );
    @Override
    public boolean include(Preferences preferences, Job job) {
        String title =job.getTitle().toLowerCase();
        if(jobTitlePhrases.stream().anyMatch(t->title.contains(t.toLowerCase()))){
            return false;
        }
        return true;
    }


}
