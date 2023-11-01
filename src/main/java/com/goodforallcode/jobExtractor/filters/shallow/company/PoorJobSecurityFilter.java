package com.goodforallcode.jobExtractor.filters.shallow.company;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class PoorJobSecurityFilter implements JobFilter {
    List<String> companyNames =List.of( "Allstate","New Relic","Breezeline","Slack",
    "Crossover","Invitae","Omnicell","Komodo Health");

    @Override
    public boolean include(Preferences preferences, Job job) {


        if(companyNames.stream().anyMatch(k->job.getCompanyName().equals(k))){
            System.err.println("Layoffs ->reject: "+job);
            return false;
        }
        return true;
    }
}
