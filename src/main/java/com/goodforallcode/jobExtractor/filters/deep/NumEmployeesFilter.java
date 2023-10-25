package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

public class NumEmployeesFilter implements JobFilter {
    public boolean include(Preferences preferences, Job job){
        if (preferences.getMaxEmployees()!=null &&
                (job.getNumEmployees()>preferences.getMaxEmployees())) {
            System.err.println("num employees "+job.getNumEmployees()+" ->reject: " + job);
            return false;
        }
        return true;

    }
}
