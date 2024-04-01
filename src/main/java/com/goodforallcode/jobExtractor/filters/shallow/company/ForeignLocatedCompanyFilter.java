package com.goodforallcode.jobExtractor.filters.shallow.company;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyUtil;

import java.util.List;

/**
 * This should be only used for companies who mostly have physical locations abroad
 */
public class ForeignLocatedCompanyFilter implements JobFilter {
    List<String> companyNames =List.of( "Redcare Pharmacy");

    @Override
    public boolean include(Preferences preferences, Job job) {


        if(companyNames.stream().anyMatch(c-> CompanyUtil.containsCompanyName(c,job))){
            System.err.println("Foreign Located Employer ->reject: "+job);
            return false;
        }
        return true;
    }
}
