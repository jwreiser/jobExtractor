package com.goodforallcode.jobExtractor.filters.shallow.company;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyNameUtil;

import java.util.List;

public class CyberSecurityFilter implements JobFilter {
    List<String> companyNames =List.of( "Zscaler","Fortra");
    List<String> titles=List.of("Vulnerability engineer","Detection","Sentinel", "SIEM ","Risk ","Cyber Security","CyberSecurity","Cyber-Security");


    List<String> descriptionPhrases=List.of("cybersecurity");


    @Override
    public boolean include(Preferences preferences, Job job) {
        final String title=job.getTitle().toLowerCase();
        if(titles.stream().anyMatch(k->title.contains(k.toLowerCase()))){
            System.err.println("Cybersecurity title ->reject: "+job);
            return false;
        }

        if(title.contains("security")&& !title.contains("clearance")){
            System.err.println("Cybersecurity title security ->reject: "+job);
            return false;
        }
        if(companyNames.stream().anyMatch(k->job.getCompanyName().equals(k))){
            System.err.println("Cybersecurity ->reject: "+job);
            return false;
        }

        if(job.getDescription()!=null){
            final String description=job.getDescription().toLowerCase();
            if(descriptionPhrases.stream().anyMatch(p->description.contains(p.toLowerCase()))){
                System.err.println("Cybersecurity description ->reject: "+job);
                return false;
            }
            if(companyNames.stream().anyMatch(c-> CompanyNameUtil.containsCompanyName(c,job.getDescription()))){
                System.err.println("cybersecurity based on company description ->reject: " + job);
                return false;
            }
        }
        return true;
    }
}
