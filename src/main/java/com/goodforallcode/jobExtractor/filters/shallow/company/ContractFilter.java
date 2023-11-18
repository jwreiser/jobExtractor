package com.goodforallcode.jobExtractor.filters.shallow.company;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyNameUtil;

import java.util.List;

public class ContractFilter implements JobFilter {
    List<String> companyNames =List.of( "Accenture Federal Services","Guidehouse"
            ,"Wise Skulls","Brooksource","Harnham","Cypress HCM","Belcan","Mindex",
            "Kforce Inc","Kforce Com","Oktobor Animation","Groundswell","Raft",
            "NTT DATA Services","Spatial Front, Inc","Tential Solutions",
            "IT Crowd","Koniag Government Services","SCIGON","Latitude Inc","IT Labs",
            "AgileEngine","Bitsoft International, Inc.","Revature");

    List<String> bothPhrases =List.of( "contract");

    @Override
    public boolean include(Preferences preferences, Job job) {
        if(!preferences.isExcludeContractJobs()&&!preferences.getIncludedContractMinimumDuration().isPresent()){
            return true;
        }

        if(job.getContractMonths()!=null && preferences.getIncludedContractMinimumDuration().isPresent()){
            if(preferences.getIncludedContractMinimumDuration().get()>job.getContractMonths()){
                System.err.println("Contract months ->reject: "+job);
                return false;
            }
        }


        if(job.isContract()){
            System.err.println("Contract based on flag -> reject: "+job);
            return false;
        }

        if(companyNames.stream().anyMatch(c->CompanyNameUtil.containsCompanyName(c,job))){
            System.err.println("Contract Based Employer ->reject: "+job);
            return false;
        }

        final String title= job.getTitle().toLowerCase();
        if(bothPhrases.stream().anyMatch(p->title.contains(p.toLowerCase()))){
            System.err.println("Contract Based on title ->reject: "+job);
            return false;
        }


        if(job.getDescription()!=null){
            final String description= job.getDescription().toLowerCase();
            if(bothPhrases.stream().anyMatch(p->description.contains(p.toLowerCase()))){
                System.err.println("Contract Based on description ->reject: "+job);
                return false;
            }
        }
        return true;
    }
}
