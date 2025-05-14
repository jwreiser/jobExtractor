package com.goodforallcode.jobExtractor.filters;

import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

public interface JobFilter {
    default public String getName(){
        return getClass().getSimpleName();
    }
    default boolean include(Preferences preferences, Job job){
        return true;
    }
}
