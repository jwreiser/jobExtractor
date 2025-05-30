package com.goodforallcode.jobExtractor.filters.custom;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.StringUtil;

public class RemoteFilter implements JobFilter {
    @Override
    public boolean include(Preferences preferences, Job job) {
        if((job.getFullyRemote()==null||!job.getFullyRemote())
                && preferences.isRemoteOnly()) {
                return false;
        }

        return true;

    }

}
