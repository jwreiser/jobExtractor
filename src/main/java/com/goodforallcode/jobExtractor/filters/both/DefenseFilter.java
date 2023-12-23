package com.goodforallcode.jobExtractor.filters.both;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyNameUtil;

import java.util.List;

public class DefenseFilter implements JobFilter {

    /**
     * Defense can be used to describe defense of a paper
     */
    List<String> phrases =List.of("military"," DLA "," DOD ","defense",
            "national security","missions"," DHS "
            ,"army","navy","air force","Lockheed Martin","Marine Corps","missile");
    @Override
    public boolean include(Preferences preferences, Job job) {
        if(job.getIndustry()!=null){
            if(job.getIndustry().startsWith("Defense ")){
                System.err.println("military industry ->reject: " + job);
                return false;
            }
        }

        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            if (phrases.stream().anyMatch(k -> description.contains(k.toLowerCase()))) {
                System.err.println("military defense ->reject: " + job);
                return false;
            }
            if(description.contains("intelligence") && !description.contains(" ai ")
                    && !description.contains("/ai ") && !description.contains(" ai/")
                    && !description.contains(" artificial ") && !description.contains(" business ") ){
                System.err.println("defense (intelligence) ->reject: " + job);
                return false;
            }
        }
        return true;
    }
}
