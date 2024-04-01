package com.goodforallcode.jobExtractor.filters.shallow;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyUtil;

import java.util.List;

public class EntertainmentFilter implements JobFilter {
    String industry ="Entertainment Providers";
    final List<String> companyNames=List.of("NBCUniversal");
    @Override
    public boolean include(Preferences preferences, Job job) {
        if(companyNames.stream().anyMatch(c-> CompanyUtil.containsCompanyName(c,job))){
            System.err.println("entertainment company name->reject: " + job);
            return false;
        }
        if(job.getIndustries()!=null && job.getIndustries(). contains(industry)){
            System.err.println("entertainment ->reject: " + job);
            return false;
        }
        return true;
    }


}
