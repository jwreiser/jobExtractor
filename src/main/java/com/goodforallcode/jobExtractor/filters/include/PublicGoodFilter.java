package com.goodforallcode.jobExtractor.filters.include;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class PublicGoodFilter implements JobFilter {
    List<String> phrases =List.of("nonprofit","health equity","fair opportunity","unjust"
    ,"structural inequities","racism","underserved","food access");
    List<String> industryPhrases =List.of("Hospitals and Health Care","Mental Health Care","Higher Education");
    List<String> companies =List.of("Blackbaud");
    public boolean include(Preferences preferences, Job job){
        if(companies.stream().anyMatch(c->job.getCompanyName().equals(c))){
            System.err.println("public good company name-> include: " + job);
            return true;
        }
        if(job.getDescription()!=null) {
            final String description = job.getDescription().toLowerCase();
            if (phrases.stream().anyMatch(p -> description.contains(p))) {
                System.err.println("public good -> include: " + job);
                return true;
            }
        }
        if(job.getIndustry()!=null) {
            final String industry = job.getIndustry();
            if (industry != null) {
                if (industryPhrases.stream().anyMatch(p -> industry.equals(p))) {
                    System.err.println("public good -> include: " + job);
                    return true;
                }
            }
        }
        return false;

    }
}
