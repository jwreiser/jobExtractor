package com.goodforallcode.jobExtractor.filters.deep.always;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class SupportFilter implements JobFilter {
    List<String> phrases = List.of("Support tickets","second level support");

    @Override
    public boolean include(Preferences preferences, Job job) {

        if(job.getDescription()!=null) {
            String text = job.getDescription().toLowerCase();
            if (phrases.stream().anyMatch(k->text.contains(k.toLowerCase()))) {
                System.err.println("support ->reject: " + job);
                return false;
            }
        }
        return true;
    }
}
