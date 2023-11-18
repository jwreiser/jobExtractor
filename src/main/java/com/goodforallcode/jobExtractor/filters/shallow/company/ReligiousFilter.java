package com.goodforallcode.jobExtractor.filters.shallow.company;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyNameUtil;

import java.util.List;

public class ReligiousFilter implements JobFilter {
    List<String> companyName =List.of( "The Church of Jesus Christ of Latter-day Saints");

    @Override
    public boolean include(Preferences preferences, Job job) {
        if(companyName.stream().anyMatch(cn-> CompanyNameUtil.containsCompanyName(cn,job))){
            System.err.println("Religious ->reject: "+job);
            return false;
        }
        return true;
    }
}
