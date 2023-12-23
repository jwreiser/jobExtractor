package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class ContentManagementFilter implements JobFilter {

    List<String> bothPhrases =List.of("AEM ","Adobe Experience Manager","Drupal","Alfresco"," onbase",
            "Sitecore","Tridion");
    List<String> caseSensitivePhrases =List.of("Brightspot");

    public boolean include(Preferences preferences, Job job){
        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            String descriptionCase = job.getDescription();
            long count=bothPhrases.stream().filter(p -> description.contains(p.toLowerCase())).count();
            if (count>2) {
                System.err.println("CMS count "+count+" ->reject: " + job);
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
