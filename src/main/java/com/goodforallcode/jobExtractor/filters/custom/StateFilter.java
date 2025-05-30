package com.goodforallcode.jobExtractor.filters.custom;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.StringUtil;

public class StateFilter implements JobFilter {
    @Override
    public boolean include(Preferences preferences, Job job) {
        if((job.getFullyRemote()==null||!job.getFullyRemote())
                && StringUtil.valuePopulated(preferences.getState())
                && StringUtil.valuePopulated(job.getState())) {
            if(!preferences.getState().toLowerCase().equals(job.getState().toLowerCase())){
                return false;
            }

        }

        return true;

    }

}
