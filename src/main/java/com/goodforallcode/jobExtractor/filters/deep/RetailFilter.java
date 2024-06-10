package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class RetailFilter implements JobFilter {
    List<String> industries =List.of("Retail","Apparel, Accessories & Footwear");
    final List<String> companyNames= List.of("Abercrombie & Fitch Co.");
    @Override
    public boolean include(Preferences preferences, Job job) {
        if(job.getIndustries()!=null && industries.stream().anyMatch(i->job.getIndustries(). contains(i))){
            return false;
        }
        if(job.getCompanyName()!=null && companyNames.stream().anyMatch(cn->job.getCompanyName().equals(cn))){
            return false;
        }
        if(job.getDescription()!=null){
            final String descriptionLower=job.getDescription().toLowerCase();
            if(descriptionLower.contains("retail")){
                return false;
            }

        }
        return true;
    }


}
