package com.goodforallcode.jobExtractor.filters.shallow.title;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.RegexUtil;

import java.util.List;

public class ProgrammingFrameworkFilter implements JobFilter {
    private static List<String> frameworks = List.of(
            "Flutter","Drools","Spring","WS02");

    @Override
    public boolean include(Preferences preferences, Job job) {
        String tempTitle = job.getTitle().toLowerCase();
        if (tempTitle.endsWith("- Remote")) {
            tempTitle = tempTitle.replaceAll("- Remote", "");
        }
        final String title = tempTitle;

        //check we don't know framework prior to rejecting
        if(preferences.getProgrammingFrameworks().stream().anyMatch(
                f->title.contains(f.toLowerCase()))){
            return true;
        }


        if (frameworks.stream().anyMatch(l -> title.contains( l.toLowerCase()))) {
            System.err.println("framework developer ->reject: " + job);
            return false;
        }


        if (job.getDescription() != null) {
            final String description = job.getDescription().toLowerCase();
            if (frameworks.stream().anyMatch(l -> description.contains("strong knowledge of " + l.toLowerCase())
            &&!preferences.getProgrammingFrameworks().contains(l))) {
                System.err.println("strong knowledge of framework ->reject: " + job);
                return false;
            }

            if (frameworks.stream().anyMatch(l -> description.contains("advanced knowledge of " + l.toLowerCase())
                    &&!preferences.getProgrammingFrameworks().contains(l))) {
                System.err.println("advanced knowledge of framework ->reject: " + job);
                return false;
            }

        }

        return true;
    }

}

