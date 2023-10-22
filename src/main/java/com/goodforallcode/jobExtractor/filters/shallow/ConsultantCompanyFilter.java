package com.goodforallcode.jobExtractor.filters.shallow;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class ConsultantCompanyFilter implements JobFilter {
    static List<String>consultantKeywords=List.of("consultant","consulting");
    static List<String>consultantCompanyNames=List.of("Curate Partners","Modis","Akkodis"
    ,"Ricardo plc");

    @Override
    public boolean include(Preferences preferences, Job job) {
        if(!preferences.isExcludeConsultantCompanies()){
            return true;
        }
        String title =job.getTitle().toLowerCase();
        if(consultantCompanyNames.stream().anyMatch(c -> job.getCompanyName().equals(c))){
            System.err.println("Consultant company->reject: "+job);
            return false;
        }
        if(consultantKeywords.stream().anyMatch(k -> title.contains(k.toLowerCase()))){
            System.err.println("Consultant->reject: "+job);
            return false;
        }

        String companyName =job.getCompanyName().toLowerCase();

        if(consultantKeywords.stream().anyMatch(k -> companyName.contains(k.toLowerCase()))){
            System.err.println("Consultant companyName->reject: "+job);
            return false;
        }
        return true;
    }
}
