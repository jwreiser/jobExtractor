package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyUtil;

import java.util.List;

public class CyberSecurityFilter implements JobFilter {

    List<String> descriptionPhrases=List.of("cybersecurity");
    List<String> companyNames =List.of( "Zscaler","Fortra","Concourse Labs",
            "PropelAuth","Trinity Cyber","Quokka.io");


    @Override
    public boolean include(Preferences preferences, Job job) {
        if(job.getIndustries()!=null && job.getIndustries(). contains(" Computer and Network Security")){
            System.err.println("Cybersecurity industry ->reject: "+job);
            return false;
        }

        if(job.getDescription()!=null){
            final String description=job.getDescription().toLowerCase();
            if(descriptionPhrases.stream().anyMatch(p->description.contains(p.toLowerCase()))){
                System.err.println("Cybersecurity description ->reject: "+job);
                return false;
            }
            if(companyNames.stream().anyMatch(c-> CompanyUtil.descriptionContainsCompanyName(c,job.getDescription()))){
                System.err.println("cybersecurity based on company description ->reject: " + job);
                return false;
            }
        }
        return true;
    }
}
