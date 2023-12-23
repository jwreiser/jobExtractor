package com.goodforallcode.jobExtractor.filters.both;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class FullstackFilter implements JobFilter {
    List<String> jobTitlePhrases=List.of("Full stack","Fullstack","Full-Stack","Facets");

    List<String> descriptionKeywords=List.of("fullstack","full stack","full-stack");
    @Override
    public boolean include(Preferences preferences, Job job) {
        if(!preferences.isExcludeFullStack()){
            return true;
        }
        String title =job.getTitle().toLowerCase();

        if(jobTitlePhrases.stream().anyMatch(t->title.contains(t.toLowerCase()))){
            System.err.println("full stack job title->reject: "+job);
            return false;
        }
        if(job.getDescription()!=null){
            final String descripton=job.getDescription().toLowerCase();
            if(descriptionKeywords.stream().anyMatch(k->descripton.contains(k.toLowerCase()))){
                System.err.println("full stack job description->reject: "+job);
                return false;
            }
        }
        return true;
    }

}
