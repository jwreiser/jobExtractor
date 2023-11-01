package com.goodforallcode.jobExtractor.filters.deep.always.lang;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class BasicFilter implements JobFilter {
    List<String>keywords=List.of("JBasic","T24", "JBASE", "PICK","jQL", "Visual basic");

    @Override
    public boolean include(Preferences preferences, Job job) {
        if (job.getDescription()!=null) {
            String description = job.getDescription();//case sensitive

            if (keywords.stream().anyMatch(k -> description.contains(k))) {
                System.err.println("Basic ->reject: " + job);
                return false;
            }
        }
        return true;
    }
}
