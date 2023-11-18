package com.goodforallcode.jobExtractor.filters;

import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

public interface JobFilter {
    boolean include(Preferences preferences, Job job);
    default public String getName(){
        return getClass().getSimpleName();
    }
}
