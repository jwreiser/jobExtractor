package com.goodforallcode.jobExtractor.filters.both;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class NotFresherAndInternLevelFilter implements JobFilter {
    static List<String> keywords = List.of(
            "intern ", "internship");
    static List<String> excludeKeywords = List.of("internships");
    static List<String> titleKeywords = List.of( "Fresher","intern ");
    @Override
    public boolean include(Preferences preferences, Job job) {
        String title = job.getTitle().toLowerCase();
        if(!preferences.isExcludeFresher()){
            return true;
        }
        if (keywords.stream().anyMatch(k -> title.contains(k.toLowerCase()))) {
            System.err.println("fresher and intern->reject: " + job);
            return false;

        }

        if (titleKeywords.stream().anyMatch(k -> title.contains(k.toLowerCase()))) {
            System.err.println("fresher and intern title->reject: " + job);
            return false;

        }
        if (job.getDescription() != null) {
            String description = job.getDescription().toLowerCase();
            if(excludeKeywords.stream().anyMatch(k->description.contains(k.toLowerCase()))){
                return true;
            }
            if (keywords.stream().anyMatch(k -> description.contains(k.toLowerCase()))) {
                System.err.println("fresher and intern description ->reject: " + job);
                return false;
            }
        }

        return true;
    }

}
