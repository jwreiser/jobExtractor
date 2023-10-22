package com.goodforallcode.jobExtractor.filters.shallow;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

public class HiddenFilter implements JobFilter {
    @Override
    public boolean include(Preferences preferences, Job job) {
        if (job.isHidden()){
            System.err.println("Hidden->reject: "+job);
            return false;
        }else{
            return true;
        }

    }

}
