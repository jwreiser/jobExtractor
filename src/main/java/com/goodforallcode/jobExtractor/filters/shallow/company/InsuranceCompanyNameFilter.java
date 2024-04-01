package com.goodforallcode.jobExtractor.filters.shallow.company;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyUtil;

import java.util.List;

public class InsuranceCompanyNameFilter implements JobFilter {
    List<String> titlePhrases =List.of( "Duck Creek");
    List<String> companyNames =List.of( "Transamerica","Travelers",
            "State Farm","GEICO","Allstate","Coalition, Inc.","One80 Intermediaries");
    List<String> descriptionPhrases =List.of( "insurance");

    @Override
    public boolean include(Preferences preferences, Job job) {
        final String title=job.getTitle().toLowerCase();
        if(titlePhrases.stream().anyMatch(k->title.contains(k.toLowerCase()))){
            System.err.println("insurance title ->reject: "+job);
            return false;
        }


        if(companyNames.stream().anyMatch(cn-> CompanyUtil.containsCompanyName(cn,job))){
            System.err.println("insurance ->reject: "+job);
            return false;
        }


        String companyName= job.getCompanyName().toLowerCase();
        if(companyNames.stream().anyMatch(k->companyName.contains("insurance"))){
            System.err.println("insurance contained in name ->reject: "+job);
            return false;
        }
        return true;
    }
}
