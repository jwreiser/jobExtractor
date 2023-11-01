package com.goodforallcode.jobExtractor.filters.both;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class NotFresherAndInternLevelFilter implements JobFilter {
    /**
     * Exception
     * internship: Minimum of X years of experience in software development or a relevant internship.
     */
    static List<String> descriptionKeywords = List.of(
            " intern ");
    static List<String> titleKeywords = List.of( "Fresher","intern ", "internship");
    @Override
    public boolean include(Preferences preferences, Job job) {
        String title = job.getTitle().toLowerCase();
        if(!preferences.isExcludeFresher()){
            return true;
        }

        if (titleKeywords.stream().anyMatch(k -> title.contains(k.toLowerCase()))) {
            System.err.println("fresher and intern title->reject: " + job);
            return false;

        }
        if (job.getDescription() != null) {
            String description = job.getDescription().toLowerCase();
            if (descriptionKeywords.stream().anyMatch(k -> description.contains(k.toLowerCase()))) {
                System.err.println("fresher and intern description ->reject: " + job);
                return false;
            }
        }

        return true;
    }

}
