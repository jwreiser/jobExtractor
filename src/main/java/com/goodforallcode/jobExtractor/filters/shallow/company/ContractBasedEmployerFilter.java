package com.goodforallcode.jobExtractor.filters.shallow.company;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class ContractBasedEmployerFilter implements JobFilter {
    List<String> companyNames =List.of( "Accenture Federal Services","Guidehouse"
            ,"Wise Skulls","Brooksource","Harnham","Cypress HCM","Belcan","Mindex",
            "Kforce Inc","Kforce Com","Oktobor Animation");

    @Override
    public boolean include(Preferences preferences, Job job) {


        if(companyNames.stream().anyMatch(k->job.getCompanyName().equals(k))){
            System.err.println("Contract Based Employer ->reject: "+job);
            return false;
        }
        return true;
    }
}
