package com.goodforallcode.jobExtractor.filters.shallow;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

public class JobTooOldFilter implements JobFilter {
    @Override
    public boolean include(Preferences preferences, Job job) {
        if (preferences.getMaxJobAgeInDays()!=null &&
                job.getJobAgeInDays()!=null
        &&(job.getJobAgeInDays()> preferences.getMaxJobAgeInDays())){
            System.err.println(this.getClass()+" preferences->reject: "+job);
            return false;
        }else{
            return true;
        }

    }

}
