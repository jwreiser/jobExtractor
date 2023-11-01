package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class IntegrationFilter implements JobFilter {

    List<String> bothPhrases =List.of("Mulesoft","Boomi ");
    List<String> titles =List.of("Implementation Engineer","Integration "
            ,"Integrations ","System Integrator");

    public boolean include(Preferences preferences, Job job){
        String title = job.getTitle().toLowerCase();
        if (titles.stream().anyMatch(p -> title.contains(p.toLowerCase()))) {
            System.err.println("Integration title ->reject: " + job);
            return false;
        }
        if (bothPhrases.stream().anyMatch(p -> title.contains(p.toLowerCase()))) {
            System.err.println("Integration title both ->reject: " + job);
            return false;
        }
        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            if (bothPhrases.stream().anyMatch(p -> description.contains(p.toLowerCase()))) {
                System.err.println("Integration->reject: " + job);
                return false;
            }
        }
     return true;

 }
}
