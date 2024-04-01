package com.goodforallcode.jobExtractor.filters.shallow.company;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyUtil;

import java.util.List;

public class DishonestCompanyFilter implements JobFilter {
    List<String> companyNames =List.of( "IT Minds LLC","Bitsoft International, Inc."
    ,"Envision Horizons");

    @Override
    public boolean include(Preferences preferences, Job job) {


        if(companyNames.stream().anyMatch(c-> CompanyUtil.containsCompanyName(c,job))){
            System.err.println("dishonest company ->reject: "+job);
            return false;
        }
        return true;
    }
}
