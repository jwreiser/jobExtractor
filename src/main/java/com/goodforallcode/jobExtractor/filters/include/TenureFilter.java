package com.goodforallcode.jobExtractor.filters.include;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class TenureFilter implements JobFilter {

    public boolean include(Preferences preferences, Job job){
        if (job.getTenure()!=null && job.getTenure()>= preferences.getDesiredTenure()) {
            System.err.println("tenure -> include: " + job);
            return true;
        }
        return false;

    }
}
