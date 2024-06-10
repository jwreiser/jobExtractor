package com.goodforallcode.jobExtractor.filters.shallow.title;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class IntegrationTitleFilter implements JobFilter {

    List<String> bothPhrases =List.of("Mulesoft","Boomi ");
    List<String> titles =List.of("Implementation Engineer","Integration "
            ,"Integrations ","System Integrator");

    public boolean include(Preferences preferences, Job job){
        String title = job.getTitle().toLowerCase();
        if (titles.stream().anyMatch(p -> title.contains(p.toLowerCase()))) {
            return false;
        }
        if (bothPhrases.stream().anyMatch(p -> title.contains(p.toLowerCase()))) {
            System.err.println("Integration title both ->reject: " + job);
            return false;
        }
     return true;

 }
}
