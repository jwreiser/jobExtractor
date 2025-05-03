package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class ClearanceFilter implements JobFilter {
/*
Exceptions
Secret: secrets in terms of authentication type stuff
Employee Polygraph Protection Act
 */
    List<String> activeClearancePhrases =List.of("must have an active Public Trust Clearance");

    static List<String> difficultClearancePhrases =List.of("top secret","secret ","TS/SCI "," TS/SCI");
    static List<String> notClearancePhrases =List.of("the secret");


    public boolean include(Preferences preferences, Job job){
        String title = job.getTitle().toLowerCase();
        if (difficultClearancePhrases.stream().anyMatch(p -> title.contains(p.toLowerCase()))) {
            System.err.println("difficult clearance title ->reject: " + job);
            return false;
        }
        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            if (activeClearancePhrases.stream().anyMatch(p -> description.contains(p.toLowerCase()))) {
                System.err.println("Job active clearance->reject: " + job);
                return false;
            }

            if (notClearancePhrases.stream().anyMatch(p -> description.contains(p.toLowerCase()))) {
                System.err.println("safe clearance word -> include: " + job);
                return true;
            }

            if (difficultClearancePhrases.stream().anyMatch(p -> description.contains(p.toLowerCase()))) {
                System.err.println("difficult clearance->reject: " + job);
                return false;
            }
        }
     return true;

 }
}
