package com.goodforallcode.jobExtractor.filters.custom;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.BooleanUtil;

public class SeniorityFilter implements JobFilter {
    @Override
    public boolean include(Preferences preferences, Job job) {
        if (preferences.getSeniority() != null && !preferences.getSeniority().isEmpty() && job.getSeniority() != null) {
            String seniority = preferences.getSeniority().toLowerCase();
            BooleanUtil booleanUtil = new BooleanUtil();
            if (seniority.equals("senior") && booleanUtil.someValuesPopulatedAndTrue(job.getAboveSenior(),job.getNoExperience(),job.getArchitect())) {
                return false;
            }
            if (seniority.equals("entry level") && (booleanUtil.valuePopulatedAndFalse(job.getNoExperience())||
            booleanUtil.someValuesPopulatedAndTrue(job.getMidCareer(),job.getSenior(),job.getAboveSenior(),job.getArchitect()))) {
                return false;
            }

        }

        return true;

    }

}
