package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.CompanySummary;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

public class NumEmployeesFilter implements JobFilter {
    public boolean include(Preferences preferences, Job job){
        if (preferences.getMaxEmployees()!=null){
            return true;
        }
        if(job.getMinimumNumEmployees()>preferences.getMaxEmployees()) {
            System.err.println("num employees "+job.getMinimumNumEmployees()+" ->reject: " + job);
            return false;
        }
        if(job.getCompany()!=null) {
            CompanySummary sum=job.getCompany();
            if(sum.getEmployeeRangeHigh()!=null) {
                if(sum.getEmployeeRangeHigh()>preferences.getMaxEmployees()) {
                    System.err.println("num employees company range high" + sum.getEmployeeRangeHigh() + " ->reject: " + job);
                    return false;
                }
            }else if(sum.getEmployeeRangeLow()!=null && sum.getEmployeeRangeLow()>preferences.getMaxEmployees()) {
                System.err.println("num employees company range low" + sum.getEmployeeRangeLow() + " ->reject: " + job);
                return false;
            }
        }
        return true;

    }
}
