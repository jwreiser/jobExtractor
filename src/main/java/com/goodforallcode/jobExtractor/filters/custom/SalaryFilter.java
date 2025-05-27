package com.goodforallcode.jobExtractor.filters.custom;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

public class SalaryFilter implements JobFilter {
    @Override
    public boolean include(Preferences preferences, Job job) {
        if (
                job.getMinYearlySalary()!=null
                        && job.getMinYearlySalary()> preferences.getMaxYearlySalary()){
            return false;
        }

        if ( preferences.getMinYearlySalary()!=null && job.getMaxYearlySalary()!=null
                && job.getMaxYearlySalary()>1000//make sure we don't get hourly here by accident
                && job.getMaxYearlySalary()< preferences.getMinYearlySalary()){
            return false;
        }

        if (
                (job.getMinHourlySalary()!=null && job.getMinHourlySalary()> preferences.getMaxHourlyRate())
                        ||(job.getMaxHourlySalary()!=null && job.getMaxHourlySalary()< preferences.getMinHourlyRate())){
            return false;
        }

        return true;

    }

}
