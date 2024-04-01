package com.goodforallcode.jobExtractor.filters.shallow.company;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.CompanySummary;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyUtil;

import java.util.List;

public class BadManagementFilter implements JobFilter {


    List<String> microManagementCompanyNames =List.of( "Research Innovations Incorporated",
            "IT Labs","Hansen Technologies");

    List<String> poorManagementCompanyNames =List.of(  "LaunchDarkly","TIDAL","DXC Technology","Oak Ridge National Laboratory","EPLAN",
            "Envision Horizons");

    @Override
    public boolean include(Preferences preferences, Job job) {

        if(poorManagementCompanyNames.stream().anyMatch(cn-> CompanyUtil.containsCompanyName(cn,job))){
            System.err.println("bad management company name ->reject: "+job);
            return false;
        }
        if(microManagementCompanyNames.stream().anyMatch(cn-> CompanyUtil.containsCompanyName(cn,job))){
            System.err.println("Micromanagement ->reject: "+job);
            return false;
        }
        if(job.getCompany()!=null){
            CompanySummary sum=job.getCompany();
            if(sum.getPositiveEmployeeToneTowardsManagement()!=null && sum.getPositiveEmployeeToneTowardsManagement()){
                return true;
            }
            if(sum.getNegativeManagementCompetenceTone()!=null && sum.getNegativeManagementCompetenceTone()){
                System.err.println("bad management tone ->reject: "+job);
                return false;
            }
            if(sum.getTopDownManagement()!=null && sum.getTopDownManagement()){
                System.err.println("bad management top down ->reject: "+job);
                return false;
            }
        }

        return true;
    }
}
