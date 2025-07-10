package com.goodforallcode.jobExtractor.filters.custom;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.BooleanUtil;
import com.goodforallcode.jobExtractor.util.StringUtil;

public class RemoteFilter implements JobFilter {
    BooleanUtil booleanUtil = new BooleanUtil();
    @Override
    public boolean include(Preferences preferences, Job job) {
        if(preferences.isRemoteOnly() && booleanUtil.valuePopulatedAndFalse(job.getFullyRemote())) {
                return false;
        }

        return true;

    }

}
