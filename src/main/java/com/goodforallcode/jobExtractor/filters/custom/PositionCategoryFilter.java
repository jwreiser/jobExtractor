package com.goodforallcode.jobExtractor.filters.custom;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

public class PositionCategoryFilter implements JobFilter {
    public boolean include(Preferences preferences, Job job) {
        for(String category : job.getPositionCategories()) {
            System.err.println("Checking position category: " + category);
            if (!preferences.getAcceptablePositionCategories().contains(category)) {
                return false;
            }
        }
        return true;
    }

}
