package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class BettingFilter implements JobFilter {

    List<String> phrases =List.of("betting","casino");

    public boolean include(Preferences preferences, Job job){
        final String company=job.getCompanyName().toLowerCase();
        if(phrases.stream().anyMatch(p->company.contains(p.toLowerCase()))) {
            System.err.println("Betting  company ->reject: " + job);
            return false;
        }
        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            if (phrases.stream().anyMatch(p -> description.contains(p.toLowerCase()))) {
                System.err.println("Betting ->reject: " + job);
                return false;
            }

        }

     return true;

 }
}
