package com.goodforallcode.jobExtractor.filters.shallow.company;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyUtil;

import java.util.List;

/**
 * This is intended to catch larger financial companies. Credit Unions that may be more aabout people than money are not included optimistically
 */
public class FinanceFilter implements JobFilter {
    /**
     * Exceptions
     * financial:   financial stability
     */
    List<String> companyNames =List.of( "Affirm","Citibank","Kraken Digital Asset Exchange"
            ,"Jack Henry","Equitable","American Express","U.S. Bank","Modulus","Clerkie",
            "Juniper Square","Peach","T. Rowe Price","Studio Management");

    List<String> descriptionPhrases =List.of("FinTech","asset manager",
            "hedge fund", "banking","trading","Brokers","Brokerage"," investment management");
    List<String> titlePhrases =List.of("Simcorp");
    List<String> industries =List.of("Venture Capital and Private Equity Principals",
            "Financial Services","Finance","Investment Banking","Investment Banking & Asset Management");
    List<String> companyNameContainsKeywords =List.of("Mortgage"," Bank" );

    @Override
    public boolean include(Preferences preferences, Job job) {
        if(job.getIndustries()!=null && industries.stream().anyMatch(i->job.getIndustries(). contains(i))){
            return false;
        }
        final String title= job.getTitle().toLowerCase();
        if(titlePhrases.stream().anyMatch(k->title.contains(k.toLowerCase()))){
            return false;
        }

        final String companyName= job.getCompanyName().toLowerCase();
        if(companyNameContainsKeywords.stream().anyMatch(k->companyName.contains(k.toLowerCase()))){
            return false;
        }

        if(companyNames.stream().anyMatch(cn-> CompanyUtil.containsCompanyName(cn,job))){
            return false;
        }
        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            if (descriptionPhrases.stream().anyMatch(p -> description.contains(p.toLowerCase()))) {
                return false;
            }
            if(companyNames.stream().anyMatch(c-> CompanyUtil.descriptionContainsCompanyName(c,job.getDescription()))){
                return false;
            }
        }

        return true;
    }
}
