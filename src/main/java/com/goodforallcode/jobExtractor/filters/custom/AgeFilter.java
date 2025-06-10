package com.goodforallcode.jobExtractor.filters.custom;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.RegexUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AgeFilter implements JobFilter {
    @Override
    public boolean include(Preferences preferences, Job job) {
        boolean include=true;
        if (job.getJobAgeInDays() != null&& preferences.getMaxJobAgeInDays() != null && job.getJobAgeInDays()> preferences.getMaxJobAgeInDays()) {
                include = false;
        }
        return include;
    }
}
