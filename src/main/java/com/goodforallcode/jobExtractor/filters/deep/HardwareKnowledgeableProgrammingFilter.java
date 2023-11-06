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
    List<String> titlePhrases =List.of("Embedded","Centura"," IoT","circuit");

    List<String> bothPhrases =List.of( "Systems Programmer", "System Programmer"
            ,"Firmware","AR/VR headset","drivers","sensor"," IoT ","semiconductor",
            "VoIP"
    );

    List<String> companyNames =List.of( "Trinnex");


    public boolean include(Preferences preferences, Job job){
        if(companyNames.stream().anyMatch(cn->job.getCompanyName().equals(cn))){
            System.err.println("hardware knowledgeable company name ->reject: " + job);
            return false;
        }
        final String title = job.getTitle().toLowerCase();
        if (bothPhrases.stream().anyMatch(p -> title.contains(p.toLowerCase()))) {
            System.err.println("hardware knowledgeable title ->reject: " + job);
            return false;
        }
        if (titlePhrases.stream().anyMatch(p -> title.contains(p.toLowerCase()))) {
            System.err.println("hardware knowledgeable title only  ->reject: " + job);
            return false;
        }
        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            if (bothPhrases.stream().anyMatch(p -> description.contains(p.toLowerCase()))) {
                System.err.println("embedded description ->reject: " + job);
                return false;
            }
        }
     return true;

 }
}
