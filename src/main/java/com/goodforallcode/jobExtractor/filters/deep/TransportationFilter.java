package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

/**
 * Exceptions:
 * meal preparation Medication and appointment reminders Transportation
 */
public class TransportationFilter implements JobFilter {
    List<String> industries =List.of("Truck Transportation","Transportation");
    final List<String> companyNames= List.of("Cambridge Systematics, Inc.");
    @Override
    public boolean include(Preferences preferences, Job job) {
        if(job.getIndustries()!=null && industries.stream().anyMatch(i->job.getIndustries().contains(i))){
            System.err.println("Transportation ->reject: " + job);
            return false;
        }
        if(job.getCompanyName()!=null && companyNames.stream().anyMatch(cn->job.getCompanyName().equals(cn))){
            System.err.println("Transportation company name->reject: " + job);
            return false;
        }

        return true;
    }


}
