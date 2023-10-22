package com.goodforallcode.jobExtractor.filters.shallow.company;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class RealEstateFilter implements JobFilter {
    List<String> phrases =List.of( "Anywhere Real Estate Inc.");

    @Override
    public boolean include(Preferences preferences, Job job) {
        if(!preferences.isExcludeRealEstate()){
            return true;
        }

        if(phrases.stream().anyMatch(k->job.getCompanyName().equals(k))){
            System.err.println("Real Estate ->reject: "+job);
            return false;
        }
        return true;
    }
}
