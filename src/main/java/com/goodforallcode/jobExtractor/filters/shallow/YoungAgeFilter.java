package com.goodforallcode.jobExtractor.filters.shallow;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

public class YoungAgeFilter implements JobFilter {
    @Override
    public boolean include(Preferences preferences, Job job) {
       if (preferences.getMinJobAgeInDays()!=null &&
               job.getJobAgeInDays()!=null && (job.getJobAgeInDays()< preferences.getMinJobAgeInDays())){
            System.err.println("Job too new "+job.getJobAgeInDays()+" days old ->reject: "+job);
            return false;
        }else{
            return true;
        }

    }

}
