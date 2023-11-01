package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

public class NumApplicantsFilter implements JobFilter {
    boolean skipping;

    public NumApplicantsFilter(boolean skipping) {
        this.skipping = skipping;
    }

    public boolean include(Preferences preferences, Job job){
        if(skipping && !preferences.isSkipTooManyApplicants()){
            return true;
        }

        if (job.getNumApplicants()>preferences.getMaxApplicants()) {
            System.err.println("num applicants ("+job.getNumApplicants()+") > "+preferences.getMaxApplicants()+" ->reject: " + job);
            return false;
        }
        return true;

    }
}
