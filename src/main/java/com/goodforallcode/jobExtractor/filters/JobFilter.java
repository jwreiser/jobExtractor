package com.goodforallcode.jobExtractor.filters;

import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

public interface JobFilter {
    public boolean include(Preferences preferences, Job job);
}
