package com.goodforallcode.jobExtractor.filters.deep.always.lang;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class PythonFilter implements JobFilter {
    List<String> keywords = List.of("django","php","pyspark","python","FastAPI","Flask");

    @Override
    public boolean include(Preferences preferences, Job job) {
        String text = job.getDescription().toLowerCase();

        long mainCount = keywords.stream().filter(k -> text.contains(k.toLowerCase())).count();
        if (mainCount > 2) {
            System.err.println("python "+mainCount+"->reject: " + job);
            return false;
        }
        return true;
    }
}
