package com.goodforallcode.jobExtractor.filters.shallow.company;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class InsuranceFilter implements JobFilter {
    List<String> phrases =List.of( "Transamerica","Travelers","State Farm","GEICO","Allstate");

    @Override
    public boolean include(Preferences preferences, Job job) {


        if(phrases.stream().anyMatch(k->job.getCompanyName().equals(k))){
            System.err.println("insurance ->reject: "+job);
            return false;
        }
        return true;
    }
}
