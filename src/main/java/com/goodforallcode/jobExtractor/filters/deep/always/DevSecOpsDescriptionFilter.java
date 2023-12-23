package com.goodforallcode.jobExtractor.filters.deep.always;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class DevSecOpsDescriptionFilter implements JobFilter {
    List<String> keywords = List.of("network", "security", "install",
            "VMware", "Servers", "Sccm", "administration", "administer",
            "configuration management"," configure"," deploy","maintain","setup");


    @Override
    public boolean include(Preferences preferences, Job job) {
        if (job.getDescription() != null) {
            String text = job.getDescription().toLowerCase();
            long count = keywords.stream().filter(k -> text.contains(k.toLowerCase())).count();
            if (count > 3) {
                System.err.println("DevSecOps " + count + " ->reject: " + job);
                return false;
            }
        }

        return true;
    }
}
