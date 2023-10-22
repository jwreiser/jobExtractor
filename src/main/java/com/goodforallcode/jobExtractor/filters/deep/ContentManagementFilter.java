package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class ContentManagementFilter implements JobFilter {

    List<String> phrases =List.of("AEM ","Adobe Experience Manager","Content Management"
            ,"CMS ","Drupal","Alfresco"," onbase");

    public boolean include(Preferences preferences, Job job){
        String description =job.getDescription().toLowerCase();
     if (phrases.stream().anyMatch(p->description.contains(p.toLowerCase()))) {
         System.err.println("CMS->reject: " + job);
         return false;
     }

     return true;

 }
}
