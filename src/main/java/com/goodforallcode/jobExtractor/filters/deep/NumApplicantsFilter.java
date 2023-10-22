package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

public class NumApplicantsFilter implements JobFilter {
    public boolean include(Preferences preferences, Job job){
        if (job.getNumApplicants()>preferences.getMaxApplicants()) {
            System.err.println("num applicants ("+job.getNumApplicants()+") > "+preferences.getMaxApplicants()+" ->reject: " + job);
            return false;
        }
        return true;

    }
}
