package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class EnterpriseApplicationsFilter implements JobFilter {
    List<String> phrases =List.of( "Enterprise Application", "Web Logic", "JBOSS","WebSphere");

    @Override
    public boolean include(Preferences preferences, Job job) {
        if (job.getDescription()!=null) {
            if (job.getDescription() != null) {
                String description = job.getDescription().toLowerCase();

                if (phrases.stream().filter(k -> description.contains(k.toLowerCase())).count() > 2) {
                    System.err.println("Enterprise Application ->reject: " + job);
                    return false;
                }
            }
        }
        return true;
    }
}
