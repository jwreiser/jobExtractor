package com.goodforallcode.jobExtractor.filters.both;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class DefenseFilter implements JobFilter {

    /**
     * Defense can be used to describe defense of a paper
     * military status
     * military service, veteran status, or any other category protected under
     * readdmissions
     */
    List<String> bothPhrases =List.of(" DLA "," DOD ","defense",
            "national security"," missions"," DHS "
            ,"army","navy","air force","Lockheed Martin","Marine Corps","missile");


    @Override
    public boolean include(Preferences preferences, Job job) {
        if(job.getIndustries()!=null){
            for(String industry:job.getIndustries()) {
                if (industry.startsWith("Defense ")) {
                    System.err.println("military industry ->reject: " + job);
                    return false;
                }
            }
        }

        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            if (bothPhrases.stream().anyMatch(k -> description.contains(k.toLowerCase()))) {
                System.err.println(getClass()+" description ->reject: " + job);
                return false;
            }
            if(description.contains("intelligence") && !description.contains(" ai ")
                    && !description.contains("/ai ") && !description.contains(" ai/")
                    && !description.contains(" artificial ") && !description.contains(" business ") ){
                System.err.println(getClass()+" description (intelligence) ->reject: " + job);
                return false;
            }
        }

        if(job.getCompany()!=null && job.getCompany().getDefense()!=null && job.getCompany().getDefense()){
            System.err.println("defense based on company summary ->reject: " + job);
            return false;
        }
        return true;
    }
}
