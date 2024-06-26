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
        if(job.getIndustries()!=null&&job.getIndustries(). contains("Telecommunications")) {
            return false;
        }

        if(job.getCompanyName()!=null && companyNames.stream().anyMatch(cn->job.getCompanyName().equals(cn))){
            return false;
        }
        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            Optional<String> matchingPhrase = descriptionPhrases.stream().filter(p -> description.contains(p.toLowerCase())).findFirst();
            if (matchingPhrase.isPresent()) {
                return false;
            }
        }

     return true;

 }
}
