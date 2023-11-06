package com.goodforallcode.jobExtractor.filters.shallow.company;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyNameUtil;

import java.util.List;

public class FinanceFilter implements JobFilter {
    /**
     * Exceptions
     * financial:   financial stability
     */
    List<String> companyNames =List.of( "Affirm","Citibank","Kraken Digital Asset Exchange"
            ,"Jack Henry","Equitable","American Express","U.S. Bank","Modulus");

    List<String> descriptionPhrases =List.of("FinTech",
            "hedge fund", "banking","trading","Brokers","Brokerage"," investment management");
    List<String> titlePhrases =List.of("Simcorp");
    List<String> industries =List.of("Venture Capital and Private Equity Principals","Financial Services");

    @Override
    public boolean include(Preferences preferences, Job job) {
        if(job.getIndustry()!=null && industries.stream().anyMatch(i->job.getIndustry().equals(i))){
            System.err.println("Finance company industry ->reject: "+job);
            return false;
        }
        final String title= job.getTitle().toLowerCase();
        if(titlePhrases.stream().anyMatch(k->title.contains(k.toLowerCase()))){
            System.err.println("Finance title  ->reject: "+job);
            return false;
        }


        if(companyNames.stream().anyMatch(k->job.getCompanyName().equals(k))){
            System.err.println("Finance company name ->reject: "+job);
            return false;
        }

        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            if (descriptionPhrases.stream().anyMatch(p -> description.contains(p.toLowerCase()))) {
                System.err.println("Finance description  ->reject: " + job);
                return false;
            }
            if(companyNames.stream().anyMatch(c-> CompanyNameUtil.containsCompanyName(c,job.getDescription()))){
                System.err.println("finance based on company description ->reject: " + job);
                return false;
            }
        }

        return true;
    }
}
