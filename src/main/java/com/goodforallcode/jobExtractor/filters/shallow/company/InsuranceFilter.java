package com.goodforallcode.jobExtractor.filters.shallow.company;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class InsuranceFilter implements JobFilter {
    List<String> phrases =List.of( "Duck Creek");
    List<String> companyNames =List.of( "Transamerica","Travelers","State Farm","GEICO","Allstate");
    List<String> descriptionPhrases =List.of( "insurance");

    @Override
    public boolean include(Preferences preferences, Job job) {
        final String title=job.getTitle().toLowerCase();
        if(phrases.stream().anyMatch(k->title.equals(k.toLowerCase()))){
            System.err.println("insurance title ->reject: "+job);
            return false;
        }


        if(companyNames.stream().anyMatch(k->job.getCompanyName().equals(k))){
            System.err.println("insurance ->reject: "+job);
            return false;
        }

        if(companyNames.stream().anyMatch(k->job.getCompanyName().contains("insurance"))){
            System.err.println("insurance contained in name ->reject: "+job);
            return false;
        }
        if(job.getDescription()!=null) {
            final String description =job.getDescription().toLowerCase();
            if (descriptionPhrases.stream().anyMatch(k -> description.equals(k.toLowerCase()))) {
                System.err.println("insurance description->reject: " + job);
                return false;
            }
        }
        return true;
    }
}
