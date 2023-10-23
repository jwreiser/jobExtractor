package com.goodforallcode.jobExtractor.filters.shallow.company;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class FinanceFilter implements JobFilter {
    List<String> companyNames =List.of( "Affirm","Citibank","Kraken Digital Asset Exchange"
            ,"Jack Henry","Equitable","American Express","U.S. Bank");

    List<String> descriptionPhrases =List.of("financial","FinTech","finance",
            "banking","trading","Brokers","Brokerage");

    @Override
    public boolean include(Preferences preferences, Job job) {


        if(companyNames.stream().anyMatch(k->job.getCompanyName().equals(k))){
            System.err.println("Finance ->reject: "+job);
            return false;
        }

        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            if (descriptionPhrases.stream().anyMatch(p -> description.contains(p.toLowerCase()))) {
                System.err.println("Marketing ->reject: " + job);
                return false;
            }
        }

        return true;
    }
}
