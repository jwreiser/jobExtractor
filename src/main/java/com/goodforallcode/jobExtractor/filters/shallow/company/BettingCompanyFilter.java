package com.goodforallcode.jobExtractor.filters.shallow.company;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class BettingCompanyFilter implements JobFilter {

    List<String> phrases =List.of("betting","casino");

    public boolean include(Preferences preferences, Job job){
        final String company=job.getCompanyName().toLowerCase();
        if(phrases.stream().anyMatch(p->company.contains(p.toLowerCase()))) {
            System.err.println("Betting  company ->reject: " + job);
            return false;
        }
     return true;

 }
}
