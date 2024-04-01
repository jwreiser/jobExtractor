package com.goodforallcode.jobExtractor.filters.both;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

/**
 * This is true if the company uses external contractors. It has nothing to do if the company contracts out itself
 */
public class ExternalContractorFilter implements JobFilter {

    public boolean include(Preferences preferences, Job job){
        if(job.getCompany()!=null && job.getCompany().getSoftwareEngineerExternalContractors()!=null && job.getCompany().getSoftwareEngineerExternalContractors()){
            System.err.println("software engineer external contractor company  ->reject: " + job);
            return false;

        }
        return true;

    }
}
