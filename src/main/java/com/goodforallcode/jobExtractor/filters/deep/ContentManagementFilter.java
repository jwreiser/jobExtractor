package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class ContentManagementFilter implements JobFilter {

    List<String> phrases =List.of("AEM ","Adobe Experience Manager","Content Management"
            ,"CMS ","Drupal","Alfresco"," onbase","Sitecore");
    List<String> caseSensitivePhrases =List.of("Brightspot");

    public boolean include(Preferences preferences, Job job){
        String title = job.getTitle().toLowerCase();
        if (phrases.stream().anyMatch(p -> title.contains(p.toLowerCase()))) {
            System.err.println("CMS title->reject: " + job);
            return false;
        }
        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            String descriptionCase = job.getDescription();
            if (phrases.stream().anyMatch(p -> description.contains(p.toLowerCase()))) {
                System.err.println("CMS->reject: " + job);
                return false;
            }
            if (caseSensitivePhrases.stream().anyMatch(p -> descriptionCase.contains(p))) {
                System.err.println("CMS case sensitive ->reject: " + job);
                return false;
            }
        }

     return true;

 }
}
