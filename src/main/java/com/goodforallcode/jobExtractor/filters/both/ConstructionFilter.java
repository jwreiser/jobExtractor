package com.goodforallcode.jobExtractor.filters.both;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyUtil;

import java.util.List;

public class ConstructionFilter implements JobFilter {
    String industry ="Construction";
    final List<String> companyNames=List.of("UpCodes");
    @Override
    public boolean include(Preferences preferences, Job job) {
        if(companyNames.stream().anyMatch(c-> CompanyUtil.containsCompanyName(c,job))){
            System.err.println("Construction company name->reject: " + job);
            return false;
        }
        if(job.getCompanyName().toLowerCase().contains("Construction")){
            System.err.println("Construction in company name->reject: " + job);
            return false;
        }
        if(job.getIndustries()!=null && job.getIndustries().contains(industry)){
            System.err.println("Construction ->reject: " + job);
            return false;
        }
        return true;
    }


}
