package com.goodforallcode.jobExtractor.filters.shallow.company;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyUtil;

import java.util.List;

public class RealEstateFilter implements JobFilter {
    List<String> companyName =List.of( "Anywhere Real Estate Inc.","Pacaso",
            "Aalto","MRI Software");
    List<String> companyNameStartsWith =List.of( "RE/MAX");

    @Override
    public boolean include(Preferences preferences, Job job) {
        if(!preferences.isExcludeRealEstate()){
            return true;
        }
        if(job.getIndustries()!=null && job.getIndustries(). contains("Real Estate")){
            System.err.println("Real Estate Industry ->reject: "+job);
            return false;
        }
        if(companyName.stream().anyMatch(cn-> CompanyUtil.containsCompanyName(cn,job))){
            System.err.println("Real Estate name ->reject: "+job);
            return false;
        }
        if(companyNameStartsWith.stream().anyMatch(k->job.getCompanyName().startsWith(k))){
            System.err.println("Real Estate name starts with ->reject: "+job);
            return false;
        }
        if(job.getDescription()!=null){
            String description=job.getDescription().toLowerCase();
            if(description.toLowerCase().contains("real estate")||description.toLowerCase().contains("real-estate")){
                System.err.println("Real Estate description" +
                        " ->reject: "+job);
                return false;
            }
        }
        return true;
    }
}
