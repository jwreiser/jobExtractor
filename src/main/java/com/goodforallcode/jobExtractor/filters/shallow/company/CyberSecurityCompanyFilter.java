package com.goodforallcode.jobExtractor.filters.shallow.company;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyUtil;

import java.util.List;

public class CyberSecurityCompanyFilter implements JobFilter {
    List<String> companyNames =List.of( "Zscaler","Fortra","Concourse Labs",
            "PropelAuth","Trinity Cyber","Quokka.io");
    List<String> titles=List.of("Vulnerability engineer","Detection","Sentinel", "SIEM ","Risk ","Cyber Security","CyberSecurity","Cyber-Security");




    @Override
    public boolean include(Preferences preferences, Job job) {
        final String title=job.getTitle().toLowerCase();
        if(titles.stream().anyMatch(k->title.contains(k.toLowerCase()))){
            return false;
        }

        if(title.contains("security")&& !title.contains("clearance")){
            return false;
        }
        if(job.getCompanyName().toLowerCase().contains("security")){
            return false;
        }
        if(companyNames.stream().anyMatch(cn-> CompanyUtil.containsCompanyName(cn,job))){
            return false;
        }

        return true;
    }
}
