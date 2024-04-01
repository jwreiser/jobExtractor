package com.goodforallcode.jobExtractor.filters.both;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

public class StressFilter implements JobFilter {

    public boolean include(Preferences preferences, Job job){
        if(job.getCompany()!=null && job.getCompany().getStress()!=null && job.getCompany().getStress()){
            System.err.println("stress company  ->reject: " + job);
            return false;

        }
        return true;

    }
}
