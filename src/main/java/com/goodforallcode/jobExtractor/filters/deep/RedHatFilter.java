package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class RedHatFilter implements JobFilter {
    List<String>keywords=List.of("OpenShift","RHEL","Red Hat JBoss","Red Hat Fuse");

    @Override
    public boolean include(Preferences preferences, Job job) {
        if (job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();

            if (keywords.stream().filter(k -> description.contains(k.toLowerCase())).count() > 1) {
                System.err.println("Red hat ->reject: " + job);
                return false;
            }
        }
        return true;
    }
}
