package com.goodforallcode.jobExtractor.filters.shallow;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

public class SalaryFilter implements JobFilter {
    @Override
    public boolean include(Preferences preferences, Job job) {
        if (
                job.getMinYearlySalary()!=null
                        && job.getMinYearlySalary()> preferences.getMaxYearlySalary()){
            System.err.println("Yearly Salary too high->reject: "+job);
            return false;
        }

        if ( preferences.getMinYearlySalary()!=null && job.getMaxYearlySalary()!=null
                && job.getMaxYearlySalary()< preferences.getMinYearlySalary()){
            System.err.println("Yearly Salary too low->reject: "+job);
            return false;
        }

        if (
                (job.getMinHourlySalary()!=null && job.getMinHourlySalary()> preferences.getMaxContractRate())
                        ||(job.getMaxHourlySalary()!=null && job.getMaxHourlySalary()< preferences.getMinContractRate())){
            System.err.println("Hourly Salary out of range->reject: "+job);
            return false;
        }

        return true;

    }

}
