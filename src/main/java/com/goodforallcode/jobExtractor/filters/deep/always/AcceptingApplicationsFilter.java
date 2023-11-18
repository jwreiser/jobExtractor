package com.goodforallcode.jobExtractor.filters.deep.always;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

public class AcceptingApplicationsFilter implements JobFilter {

    @Override
    public boolean include(Preferences preferences, Job job) {
        if (!job.isAcceptingApplications()) {
            System.err.println("Not Accepting Applications  ->reject: " + job);
            return false;
        }
        return true;
    }
}
