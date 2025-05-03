package com.goodforallcode.jobExtractor.filters.shallow.title;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class ElderlyBothFilter implements JobFilter {
    List<String> jobTitlePhrases=List.of(
            "Memory Care","Senior Living","Senior Care","Elderly","Geriatric"
            );
    List<String> companyNamePhrases =List.of(
            "Senior Living","Gurwin Healthcare System","The Bristal Assisted Living","Atria Senior"
    );

    List<String> bothPhrases=List.of(
            "Assisted Living"
    );



    @Override
    public boolean include(Preferences preferences, Job job) {
        String title =job.getTitle().toLowerCase();

        //if this is a job title we are not qualified for
        if(jobTitlePhrases.stream().anyMatch(t->title.contains(t.toLowerCase()))){
            System.err.println(this.getClass()+" title->reject: "+job);
            return false;
        }

        if (bothPhrases.stream().anyMatch(k -> title.contains(k.toLowerCase()))) {
            System.err.println(this.getClass() + " title->reject: " + job);
            return false;
        }
        if(job.getCompanyName()!=null) {
            String companyName =job.getCompanyName().toLowerCase();
            if (companyNamePhrases.stream().anyMatch(k -> companyName.contains(k.toLowerCase()))) {
                System.err.println(this.getClass() + " company name->reject: " + job);
                return false;
            }
        }

        if(job.getDescription()!=null) {
            String description =job.getDescription().toLowerCase();
            if (bothPhrases.stream().anyMatch(k -> description.contains(k.toLowerCase()))) {
                System.err.println(this.getClass() + " description->reject: " + job);
                return false;
            }
        }
        return true;
    }


}
