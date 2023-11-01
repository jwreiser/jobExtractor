package com.goodforallcode.jobExtractor.filters.deep.always;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class PSTFilter implements JobFilter {
    /**
     * Examples:
     * Pacific /Mountain/ Central time zones
     */
    List<String> keywords=List.of(" PST "," PDT "," Pacific ");
    @Override
    public boolean include(Preferences preferences, Job job) {
        if (job.getDescription()!=null) {
            if (job.getDescription().contains("overlap with 9am-5pm PST")) {
                System.err.println("PST ->include: " + job);
                return true;
            }

            if (keywords.stream().anyMatch(k -> job.getDescription().contains(k))) {
                System.err.println("PST ->reject: " + job);
                return false;
            }
        }
        return true;
    }
}
