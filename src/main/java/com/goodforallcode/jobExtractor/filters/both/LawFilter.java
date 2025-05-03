package com.goodforallcode.jobExtractor.filters.both;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class LawFilter implements JobFilter {

    List<String> bothPhrases =List.of("Attorney","Lawyer","Paralegal","Legal","Litigation"
    );

    List<String> titlePhrases =List.of("Law"
    );


    @Override
    public boolean include(Preferences preferences, Job job) {
        String title = job.getTitle().toLowerCase();
        if (bothPhrases.stream().anyMatch(k -> title.contains(k.toLowerCase()))) {
            System.err.println(getClass()+" title ->reject: " + job);
            return false;
        }
        if (titlePhrases.stream().anyMatch(k -> title.contains(k.toLowerCase()))) {
            System.err.println(getClass()+" title ->reject: " + job);
            return false;
        }
        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            if (bothPhrases.stream().anyMatch(k -> description.contains(k.toLowerCase()))) {
                System.err.println(getClass()+" description ->reject: " + job);
                return false;
            }
        }

      return true;
    }
}
