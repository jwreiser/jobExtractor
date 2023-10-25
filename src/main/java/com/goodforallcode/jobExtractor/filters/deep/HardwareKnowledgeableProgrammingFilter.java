package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class HardwareKnowledgeableProgrammingFilter implements JobFilter {
    /**
     * Exceptions
     * Embedded: can't be in description as it could be embedded in our culture
     */
    List<String> titlePhrases =List.of("Embedded","Centura");

    List<String> phrases =List.of( "Systems Programmer", "System Programmer"
            ,"Firmware","AR/VR headset","drivers");

    public boolean include(Preferences preferences, Job job){
        final String title = job.getTitle().toLowerCase();
        if (phrases.stream().anyMatch(p -> title.contains(p.toLowerCase()))) {
            System.err.println("embedded title ->reject: " + job);
            return false;
        }
        if (titlePhrases.stream().anyMatch(p -> title.contains(p.toLowerCase()))) {
            System.err.println("hardware knowledgeable title only  ->reject: " + job);
            return false;
        }
        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            if (phrases.stream().anyMatch(p -> description.contains(p.toLowerCase()))) {
                System.err.println("embedded description ->reject: " + job);
                return false;
            }
        }
     return true;

 }
}
