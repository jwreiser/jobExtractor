package com.goodforallcode.jobExtractor.filters.shallow.company;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class FinanceFilter implements JobFilter {
    List<String> companyNames =List.of( "Affirm","Citibank","Kraken Digital Asset Exchange"
            ,"Jack Henry","Equitable","American Express","U.S. Bank");

    @Override
    public boolean include(Preferences preferences, Job job) {


        if(companyNames.stream().anyMatch(k->job.getCompanyName().equals(k))){
            System.err.println("Finance ->reject: "+job);
            return false;
        }
        return true;
    }
}
