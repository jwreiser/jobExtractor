package com.goodforallcode.jobExtractor.filters.shallow.title;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;


public class AboveSeniorTitleFilter implements JobFilter {


    List<String> titleOnlyPhrases =List.of( "stf ","director", "VP ","staff","lead ","manager"
            ,"architect","administrator","chief","principal","Systems Development","Systems Developer");

    @Override
    public boolean include(Preferences preferences, Job job) {
        if(!preferences.isExcludeAboveSenior()){
            return true;
        }
        String title =job.getTitle().toLowerCase();

        if(titleOnlyPhrases.stream().anyMatch(k->title.contains(k.toLowerCase()))){
            return false;
        }



        return true;
    }
}
