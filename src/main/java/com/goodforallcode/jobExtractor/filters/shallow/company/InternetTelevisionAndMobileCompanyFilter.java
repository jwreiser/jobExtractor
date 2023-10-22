package com.goodforallcode.jobExtractor.filters.shallow.company;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class InternetTelevisionAndMobileCompanyFilter implements JobFilter {
    List<String> phrases =List.of( "QCell");

    @Override
    public boolean include(Preferences preferences, Job job) {


        if(phrases.stream().anyMatch(k->job.getCompanyName().equals(k))){
            System.err.println("Internet Television And Mobile ->reject: "+job);
            return false;
        }
        return true;
    }
}
