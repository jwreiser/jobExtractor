package com.goodforallcode.jobExtractor.filters.shallow.company;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class PharmacyFilter implements JobFilter {
    List<String> companyName =List.of( "CVS Health");

    @Override
    public boolean include(Preferences preferences, Job job) {


        if(companyName.stream().anyMatch(k->job.getCompanyName().equals(k))){
            System.err.println("Pharmacy ->reject: "+job);
            return false;
        }
        return true;
    }
}
