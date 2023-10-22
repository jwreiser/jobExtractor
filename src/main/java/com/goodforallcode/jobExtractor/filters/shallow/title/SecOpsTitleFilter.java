package com.goodforallcode.jobExtractor.filters.shallow.title;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class SecOpsTitleFilter implements JobFilter {
    List<String> jobTitlePhrases=List.of(
            "Release Engineer","Build","Dev ops","Devops",
            " IT Engineer","Information Technology Engineer",
            "Security", "devsecops","Vulnerability engineer","Monitoring",
            "Infrastructure","Site Reliability","SRE","Reliability Engineer","Observability",
            "Operations"," Ops Engineer","CSfC Engineer","Automation","Detection",
            "Information assurance","Integration Engineer","Release Management",
            "Platform Engineer","C3ISR"
            );
    @Override
    public boolean include(Preferences preferences, Job job) {
        String title =job.getTitle().toLowerCase();

         if(jobTitlePhrases.stream().anyMatch(t->title.contains(t.toLowerCase()))){
            System.err.println("Security or Operations job title->reject: "+job);
            return false;
        }
        return true;
    }


}
