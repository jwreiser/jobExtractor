package com.goodforallcode.jobExtractor.filters.shallow.company;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyUtil;

import java.util.List;

public class InternetTelevisionAndMobileCompanyFilter implements JobFilter {
    List<String> phrases =List.of( "QCell");

    @Override
    public boolean include(Preferences preferences, Job job) {


        if(phrases.stream().anyMatch(cn-> CompanyUtil.containsCompanyName(cn,job))){
            System.err.println("Internet Television And Mobile ->reject: "+job);
            return false;
        }
        return true;
    }
}
