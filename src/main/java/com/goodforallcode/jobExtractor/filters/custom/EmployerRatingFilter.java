package com.goodforallcode.jobExtractor.filters.custom;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;


public class EmployerRatingFilter implements JobFilter {
    @Override
    public boolean include(Preferences preferences, Job job) {
        boolean include=true;
        if (job.getEmployerRating() != null&& preferences.getMinEmployerRating() != null && job.getEmployerRating()< preferences.getMinEmployerRating()) {
                include = false;
        }
        return include;
    }
}
