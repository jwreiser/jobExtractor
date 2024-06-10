package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class ClearanceFilter implements JobFilter {
/*
Exceptions
Secret: secrets in terms of authentication type stuff
 */
    List<String> activeClearancePhrases =List.of("must have an active Public Trust Clearance");
    List<String> polygraphKeywords =List.of("polygraph","poly");
    static List<String> difficultClearancePhrases =List.of("top secret","secret ","TS/SCI");
    static List<String> notClearancePhrases =List.of("the secret");


    public boolean include(Preferences preferences, Job job){
        String title = job.getTitle().toLowerCase();
        if (difficultClearancePhrases.stream().anyMatch(p -> title.contains(p.toLowerCase()))) {
            return false;
        }
        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            if (activeClearancePhrases.stream().anyMatch(p -> description.contains(p.toLowerCase()))) {
                return false;
            }
            if (polygraphKeywords.stream().anyMatch(p -> description.contains(p.toLowerCase()))) {
                return false;
            }

            if (notClearancePhrases.stream().anyMatch(p -> description.contains(p.toLowerCase()))) {
                return true;
            }

            if (difficultClearancePhrases.stream().anyMatch(p -> description.contains(p.toLowerCase()))) {
                return false;
            }
        }
     return true;

 }
}
