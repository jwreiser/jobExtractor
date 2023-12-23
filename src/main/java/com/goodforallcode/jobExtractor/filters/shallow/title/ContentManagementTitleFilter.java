package com.goodforallcode.jobExtractor.filters.shallow.title;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class ContentManagementTitleFilter implements JobFilter {
    List<String> titlePhrases =List.of("Content Management","CMS ","Onbase","CMS(","CMS ");

    List<String> bothPhrases =List.of("AEM ","Adobe Experience Manager","Drupal","Alfresco"," onbase",
            "Sitecore","Tridion");

    public boolean include(Preferences preferences, Job job){
        String title = job.getTitle().toLowerCase();
        if (titlePhrases.stream().anyMatch(p -> title.contains(p.toLowerCase()))) {
            System.err.println("CMS title only->reject: " + job);
            return false;
        }
        if (bothPhrases.stream().anyMatch(p -> title.contains(p.toLowerCase()))) {
            System.err.println("CMS title->reject: " + job);
            return false;
        }

     return true;

 }
}
