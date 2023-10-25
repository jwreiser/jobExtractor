package com.goodforallcode.jobExtractor.filters.deep.always;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

public class AppliedFilter implements JobFilter {

    @Override
    public boolean include(Preferences preferences, Job job) {
        if (job.isApplied()) {
            System.err.println("Applied ->reject: " + job);
            return false;
        }
        return true;
    }
}
