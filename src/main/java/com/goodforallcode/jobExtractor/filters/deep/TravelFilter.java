package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

public class TravelFilter implements JobFilter {
    public boolean include(Preferences preferences, Job job){
        if (job.getTravelPercent()!=null && job.getTravelPercent()>preferences.getMaxTravelPercentage()) {
            System.err.println("Travel Percentage ->reject: " + job);
            return false;
        }
        return true;

    }
}
