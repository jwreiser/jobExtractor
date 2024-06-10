package com.goodforallcode.jobExtractor.filters.shallow.company;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyUtil;

import java.util.List;

public class MarketingFilter implements JobFilter {
    List<String> companyName =List.of( "Velir");
    List<String> titlePhrases =List.of( "SingleView");
    List<String> industries =List.of( "Marketing Services");

    @Override
    public boolean include(Preferences preferences, Job job) {
final String title=job.getTitle().toLowerCase();
        if(titlePhrases.stream().anyMatch(t->title.contains(t.toLowerCase()))){
            return false;
        }
        if(companyName.stream().anyMatch(cn-> CompanyUtil.containsCompanyName(cn,job))){
            return false;
        }
        if(job.getIndustries()!=null && industries.stream().anyMatch(i->job.getIndustries().contains(i))){
            return false;
        }
        return true;
    }
}
