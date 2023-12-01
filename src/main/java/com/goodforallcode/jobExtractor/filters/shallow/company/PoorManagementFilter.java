package com.goodforallcode.jobExtractor.filters.shallow.company;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyNameUtil;

import java.util.List;

public class PoorManagementFilter implements JobFilter {
    List<String> microManagementCompanyNames =List.of( "Research Innovations Incorporated",
    "IT Labs","Hansen Technologies");

    List<String> poorManagementCompanyNames =List.of( "Oak Ridge National Laboratory","EPLAN",
            "Envision Horizons");

    @Override
    public boolean include(Preferences preferences, Job job) {


        if(microManagementCompanyNames.stream().anyMatch(cn-> CompanyNameUtil.containsCompanyName(cn,job))){
            System.err.println("Micromanagement ->reject: "+job);
            return false;
        }

        if(poorManagementCompanyNames.stream().anyMatch(cn-> CompanyNameUtil.containsCompanyName(cn,job))){
            System.err.println("poor management ->reject: "+job);
            return false;
        }
        return true;
    }
}
