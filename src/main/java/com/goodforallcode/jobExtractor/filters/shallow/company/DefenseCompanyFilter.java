package com.goodforallcode.jobExtractor.filters.shallow.company;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyUtil;

import java.util.List;

public class DefenseCompanyFilter implements JobFilter {

    List<String> companyNames =List.of("General Dynamics Information Technology"
            ,"Parsons Corporation","SAIC","Leidos","RVCM (RevaComm)","BAE Systems",
            "ECS","Raytheon","Innovative Defense Technologies (IDT)");
    @Override
    public boolean include(Preferences preferences, Job job) {

        if(companyNames.stream().anyMatch(cn-> CompanyUtil.containsCompanyName(cn,job))){
            System.err.println("military defense company->reject: "+job);
            return false;
        }
        return true;
    }
}
