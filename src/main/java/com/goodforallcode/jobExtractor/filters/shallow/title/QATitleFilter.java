package com.goodforallcode.jobExtractor.filters.shallow.title;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class QATitleFilter implements JobFilter {
    List<String> jobTitlePhrases=List.of(
            "Test engineer", "SDET","tester","QA ","Software Developer In Test","Software Developer Engineer in Test",
            "Verification Engineer","Quality Engineer", "Software Engineer In Test","Software Development Engineer in Test"
            );
    @Override
    public boolean include(Preferences preferences, Job job) {
        String title =job.getTitle().toLowerCase();

        //if this is a job title we are not qualified for
        if(jobTitlePhrases.stream().anyMatch(t->title.contains(t.toLowerCase()))){
            System.err.println("QA job title->reject: "+job);
            return false;
        }
        return true;
    }


}
