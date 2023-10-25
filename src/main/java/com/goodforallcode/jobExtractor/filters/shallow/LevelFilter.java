package com.goodforallcode.jobExtractor.filters.shallow;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

public class LevelFilter implements JobFilter {
    @Override
    public boolean include(Preferences preferences, Job job) {

        if ( preferences.getMaxLevel()!=null && job.getLevel()!=null && preferences.getMaxLevel()<job.getLevel()){
            System.err.println("Level too high->reject: "+job);
            return false;
        }else{
            return true;
        }

    }

}
