package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

public class RetailFilter implements JobFilter {
    String industry ="Retail";
    @Override
    public boolean include(Preferences preferences, Job job) {
        if(job.getIndustry()!=null && job.getIndustry().equals(industry)){
            System.err.println("Advertising ->reject: " + job);
            return false;
        }
        return true;
    }


}
