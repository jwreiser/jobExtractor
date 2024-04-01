package com.goodforallcode.jobExtractor.filters.both;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyUtil;

import java.util.List;

public class ManufacturingFilter implements JobFilter {
    String industry ="Automation Machinery Manufacturing";
    final List<String> companyNames=List.of("Adaptec Solutions");
    @Override
    public boolean include(Preferences preferences, Job job) {
        if(companyNames.stream().anyMatch(cn-> CompanyUtil.containsCompanyName(cn,job))){
            System.err.println("Manufacturing company name->reject: " + job);
            return false;
        }
        if(job.getIndustries()!=null && job.getIndustries().contains(industry)){
            System.err.println("Manufacturing ->reject: " + job);
            return false;
        }
        return true;
    }


}
