package com.goodforallcode.jobExtractor.filters.both;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

public class AcquisitionsFilter implements JobFilter {

    public boolean include(Preferences preferences, Job job){
        if(job.getCompany()!=null && job.getCompany().getAcquisitions()!=null && job.getCompany().getAcquisitions()){
            System.err.println("Acquisitions company  ->reject: " + job);
            return false;

        }
        return true;

    }
}
