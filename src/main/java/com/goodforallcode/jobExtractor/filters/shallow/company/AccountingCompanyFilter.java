package com.goodforallcode.jobExtractor.filters.shallow.company;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyNameUtil;

import java.util.List;

public class AccountingCompanyFilter implements JobFilter {
    List<String> companyNames =List.of( "FORVIS");

    @Override
    public boolean include(Preferences preferences, Job job) {


        if(companyNames.stream().anyMatch(c-> CompanyNameUtil.containsCompanyName(c,job))){
            System.err.println("Accounting Employer ->reject: "+job);
            return false;
        }
        return true;
    }
}
