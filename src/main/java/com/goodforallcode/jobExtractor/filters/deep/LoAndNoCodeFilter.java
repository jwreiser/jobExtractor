package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class LoAndNoCodeFilter implements JobFilter {

    List<String> phrases =List.of("Pega ","Servicenow","Low Code","Low-Code",
            "lansa ","Quickbase","Entellitrek","Entellitrak","Powerapps","Low- Code",
            "No-Code","Unqork");

    public boolean include(Preferences preferences, Job job){
        String title = job.getTitle().toLowerCase();
        if (phrases.stream().anyMatch(p -> title.contains(p.toLowerCase()))) {
            return false;
        }
        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            if (phrases.stream().anyMatch(p -> description.contains(p.toLowerCase()))) {
                return false;
            }
        }
     return true;

 }
}
