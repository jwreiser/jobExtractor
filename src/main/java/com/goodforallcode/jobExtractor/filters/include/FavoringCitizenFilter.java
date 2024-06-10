package com.goodforallcode.jobExtractor.filters.include;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class FavoringCitizenFilter implements JobFilter {
    List<String> descriptionPhrases =List.of("Must be a US Citizen","maintain a security clearance"
            ,"obtain a security clearance"
    );
    List<String> bothPhrases =List.of(" mbi ","public trust","IRS Clearance",
            "Citizenship required");
    public boolean include(Preferences preferences, Job job){
        final String title = job.getTitle().toLowerCase();
        if(bothPhrases.stream().anyMatch(p->title.contains(p.toLowerCase()))){
            return true;
        }
        if (job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            if (descriptionPhrases.stream().anyMatch(p -> description.contains(p.toLowerCase()))) {
                return true;
            }
            if(bothPhrases.stream().anyMatch(p->description.contains(p.toLowerCase()))){
                return true;
            }
        }
        return false;

    }
}
