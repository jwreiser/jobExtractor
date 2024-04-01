package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

public class PharmaceuticalFilter implements JobFilter {
    String industry ="Pharmaceutical Manufacturing";

    @Override
    public boolean include(Preferences preferences, Job job) {
        if(job.getIndustries()!=null && job.getIndustries(). contains(industry)){
            System.err.println("Pharmaceutical ->reject: " + job);
            return false;
        }
        return true;
    }


}
