package com.goodforallcode.jobExtractor.filters.both;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyUtil;

import java.util.List;

public class AdvertisingFilter implements JobFilter {
    List<String> industries =List.of("Advertising Services","Advertising");
    final List<String> companyNames=List.of("SocialVenu");
    final List<String> descriptionPhrases=List.of("advertising");
    @Override
    public boolean include(Preferences preferences, Job job) {
        if(companyNames.stream().anyMatch(c-> CompanyUtil.containsCompanyName(c,job))){
            System.err.println("Advertising company name->reject: " + job);
            return false;
        }
        if(job.getCompanyName().toLowerCase().contains("advertising")){
            System.err.println("Advertising in company name->reject: " + job);
            return false;
        }
        if(job.getIndustries()!=null && industries.stream().anyMatch(i->job.getIndustries().contains(i))){
            System.err.println("Advertising ->reject: " + job);
            return false;
        }
        if(job.getDescription()!=null){
            final String description = job.getDescription().toLowerCase();
            if(descriptionPhrases.stream().anyMatch(p->description.contains(p.toLowerCase()))){
                System.err.println("Advertising description ->reject: " + job);
                return false;
            }
        }
        return true;
    }


}
