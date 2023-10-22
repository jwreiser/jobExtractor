package com.goodforallcode.jobExtractor.filters.deep.always;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class MSTFilter implements JobFilter {

    List<String> keywords=List.of(" MST "," MDT ");
    @Override
    public boolean include(Preferences preferences, Job job) {
        if (job.getDescription().contains("overlap with 9am-5pm MST")) {
            System.err.println("MST ->include: " + job);
            return true;
        }

        if (keywords.stream().anyMatch(k->job.getDescription().contains(k))) {
            System.err.println("MST ->reject: " + job);
            return false;
        }
        return true;
    }
}
