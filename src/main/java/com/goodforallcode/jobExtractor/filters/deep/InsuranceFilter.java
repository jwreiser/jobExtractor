package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyNameUtil;

import java.util.List;

public class InsuranceFilter implements JobFilter {
    List<String> companyNames =List.of( "Transamerica","Travelers",
            "State Farm","GEICO","Allstate","Coalition, Inc.","One80 Intermediaries");
    List<String> descriptionPhrases =List.of( "insurance");

    @Override
    public boolean include(Preferences preferences, Job job) {

        if(job.getIndustry()!=null && job.getIndustry().equals("Insurance")){
            System.err.println("insurance industry ->reject: "+job);
            return false;
        }

        if(job.getDescription()!=null) {
            final String description =job.getDescription().toLowerCase();
            if (descriptionPhrases.stream().anyMatch(k -> description.equals(k.toLowerCase()))) {
                System.err.println("insurance description->reject: " + job);
                return false;
            }
            if(companyNames.stream().anyMatch(c-> CompanyNameUtil.descriptionContainsCompanyName(c,job.getDescription()))){
                System.err.println("insurance based on company description ->reject: " + job);
                return false;
            }
        }
        return true;
    }
}
