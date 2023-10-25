package com.goodforallcode.jobExtractor.filters.shallow.company;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class CyberSecurityFilter implements JobFilter {
    List<String> companyNames =List.of( "Zscaler");
    List<String> titles=List.of("Vulnerability engineer","Detection",
            "Security","Sentinel", "SIEM ","Risk ");
    @Override
    public boolean include(Preferences preferences, Job job) {
        final String title=job.getTitle().toLowerCase();
        if(titles.stream().anyMatch(k->title.contains(k.toLowerCase()))){
            System.err.println("Cybersecurity title ->reject: "+job);
            return false;
        }

        if(companyNames.stream().anyMatch(k->job.getCompanyName().equals(k))){
            System.err.println("Cybersecurity ->reject: "+job);
            return false;
        }
        return true;
    }
}
