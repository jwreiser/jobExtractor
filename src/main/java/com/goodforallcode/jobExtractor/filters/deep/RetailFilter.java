package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class RetailFilter implements JobFilter {
    String industry ="Retail";
    final List<String> companyNames= List.of("Abercrombie & Fitch Co.");
    @Override
    public boolean include(Preferences preferences, Job job) {
        if(job.getIndustry()!=null && job.getIndustry().equals(industry)){
            System.err.println("retail ->reject: " + job);
            return false;
        }
        if(job.getCompanyName()!=null && companyNames.stream().anyMatch(cn->job.getCompanyName().equals(cn))){
            System.err.println("retail company name->reject: " + job);
            return false;
        }
        if(job.getDescription()!=null){
            final String descriptionLower=job.getDescription().toLowerCase();
            if(descriptionLower.contains("retail")){
                System.err.println("retail description ->reject: " + job);
                return false;
            }

        }
        return true;
    }


}
