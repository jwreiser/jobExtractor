package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;
import java.util.Optional;

public class TelecomFilter implements JobFilter {

    List<String> descriptionPhrases =List.of("Telecom","OSS and BSS","OSS/BSS");
    final List<String> companyNames= List.of("Crown Castle");


    public boolean include(Preferences preferences, Job job){
        String title =job.getTitle().toLowerCase();
        if(job.getIndustry()!=null&&job.getIndustry().equals("Telecommunications")) {
            System.err.println("Telecom industry ->reject: " + job);
            return false;
        }

        if(job.getCompanyName()!=null && companyNames.stream().anyMatch(cn->job.getCompanyName().equals(cn))){
            System.err.println("Telecom company name->reject: " + job);
            return false;
        }
        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            Optional<String> matchingPhrase = descriptionPhrases.stream().filter(p -> description.contains(p.toLowerCase())).findFirst();
            if (matchingPhrase.isPresent()) {
                System.err.println("Telecom description phrase:" +matchingPhrase.get() +": "+job);

                return false;
            }
        }

     return true;

 }
}
