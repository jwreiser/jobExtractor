package com.goodforallcode.jobExtractor.filters.custom;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class SeniorityFilter implements JobFilter {
    @Override
    public boolean include(Preferences preferences, Job job) {
        if(preferences.getSeniority()!=null&&!preferences.getSeniority().isEmpty()&&job.getSeniority()!=null) {
            String seniority = job.getSeniority().toLowerCase();
            if(seniority.equals("senior") && job.isAboveSenior()){
                return false;
            }
            if(seniority.equals("senior") && job.isNoExperience()){
                return false;
            }
            if(seniority.equals("entry level") && !job.isNoExperience()){
                return false;
            }
        }

        return true;

    }

}
