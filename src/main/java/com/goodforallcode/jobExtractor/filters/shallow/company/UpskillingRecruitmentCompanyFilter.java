package com.goodforallcode.jobExtractor.filters.shallow.company;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class UpskillingRecruitmentCompanyFilter implements JobFilter {
    List<String> phrases =List.of( "SynergisticIT");

    @Override
    public boolean include(Preferences preferences, Job job) {


        if(phrases.stream().anyMatch(k->job.getCompanyName().equals(k))){
            System.err.println("Upskilling Recruitment ->reject: "+job);
            return false;
        }
        return true;
    }
}
