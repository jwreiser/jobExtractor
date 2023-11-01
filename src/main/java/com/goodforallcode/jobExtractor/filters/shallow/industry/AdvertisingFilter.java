package com.goodforallcode.jobExtractor.filters.shallow.industry;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class AdvertisingFilter implements JobFilter {
    String industry ="Advertising Services";
    @Override
    public boolean include(Preferences preferences, Job job) {
        if(job.getIndustry()!=null && job.getIndustry().equals(industry)){
            System.err.println("Advertising ->reject: " + job);
            return false;
        }
        return true;
    }


}
