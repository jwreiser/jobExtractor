package com.goodforallcode.jobExtractor.filters.shallow.company;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyUtil;

import java.util.List;

public class CareerAcceleratorFilter implements JobFilter {

    List<String> companyNames =List.of( "Outcoder iO","SynergisticIT");

    @Override
    public boolean include(Preferences preferences, Job job) {

        if(companyNames.stream().anyMatch(cn-> CompanyUtil.containsCompanyName(cn,job))){
            return false;
        }


        return true;
    }
}
