package com.goodforallcode.jobExtractor.filters.custom;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

public class PromotedFilter implements JobFilter {
    public boolean include(Preferences preferences, Job job) {
        if (job.isPromoted() && preferences.isExcludePromoted()){
            System.err.println("Promoted->reject: "+job);
            return false;
        }
        return true;
    }

}
