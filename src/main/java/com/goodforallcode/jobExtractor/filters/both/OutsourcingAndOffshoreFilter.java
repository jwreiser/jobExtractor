package com.goodforallcode.jobExtractor.filters.both;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyUtil;

import java.util.List;

public class OutsourcingAndOffshoreFilter implements JobFilter {
    List<String> companyNames =List.of( "Rockwell Automation","Equinix","Banner Health",
            "Blue Cross NC","Ascension","Transamerica","Conduent","Zinnia","LiveRamp",
            "Intelerad Medical Systems");
    List<String> offShorePhrases =List.of("IT services and outsourcing company","with offshore");
    @Override
    public boolean include(Preferences preferences, Job job) {


        if(companyNames.stream().anyMatch(cn-> CompanyUtil.containsCompanyName(cn,job))){
            System.err.println("offshore ->reject: "+job);
            return false;
        }

        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            if (offShorePhrases.stream().anyMatch(p -> description.contains(p))) {
                System.err.println("Job offshore->reject: " + job);
                return false;
            }
            if(companyNames.stream().anyMatch(c-> CompanyUtil.descriptionContainsCompanyName(c,job.getDescription()))){
                System.err.println("offshore based on company description ->reject: " + job);
                return false;
            }
        }
        if(job.getCompany()!=null && job.getCompany().getOffshores()!=null && job.getCompany().getOffshores()){
            System.err.println("offshore based on company summary ->reject: " + job);
            return false;
        }
        return true;
    }
}
