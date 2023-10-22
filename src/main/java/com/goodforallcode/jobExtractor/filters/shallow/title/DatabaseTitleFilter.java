package com.goodforallcode.jobExtractor.filters.shallow.title;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

/**
 * Includes people querying database too
 */
public class DatabaseTitleFilter implements JobFilter {
    List<String> jobTitlePhrases=List.of(
            "Database Developer","Database Engineer","SQL Developer"," SSIS "
            );
    @Override
    public boolean include(Preferences preferences, Job job) {
        String title =job.getTitle().toLowerCase();
        if(title.contains(" and ")||title.contains("/")){
            return true;//lets not reject multiple skill titles
        }
        //if this is a job title we are not qualified for
        if(jobTitlePhrases.stream().anyMatch(t->title.contains(t.toLowerCase()))){
            System.err.println("Database job title->reject: "+job);
            return false;
        }
        return true;
    }


}
