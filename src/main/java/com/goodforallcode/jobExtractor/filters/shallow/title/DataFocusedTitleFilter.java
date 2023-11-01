package com.goodforallcode.jobExtractor.filters.shallow.title;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class DataFocusedTitleFilter implements JobFilter {
    List<String> jobTitlePhrases=List.of("Data Migration",
            " ETL ","Data Developer","Analytics","Statistical",
            "Data Analyst","Data Scientist","Data Science","Data Engineer","data analytics",
            "InterSystems Ensemble","IRIS ","Netezza"
            );
    @Override
    public boolean include(Preferences preferences, Job job) {
        String title =job.getTitle().toLowerCase();
        if(title.contains(" and ")){
            return true;//lets not reject multiple skill titles
        }
        //if this is a job title we are not qualified for
        if(jobTitlePhrases.stream().anyMatch(t->title.contains(t.toLowerCase()))){
            System.err.println("Data focused job title->reject: "+job);
            return false;
        }
        return true;
    }


}
