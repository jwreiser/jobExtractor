package com.goodforallcode.jobExtractor.filters.deep.always;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class SearchFilter implements JobFilter {
    List<String> keywords = List.of( "Elasticsearch","OpenSearch","lucene","Solr");

    @Override
    public boolean include(Preferences preferences, Job job) {
        String text = job.getDescription().toLowerCase();

        long mainCount = keywords.stream().filter(k -> text.contains(k.toLowerCase())).count();
        if (mainCount > 1) {
            System.err.println("search ->reject: " + job);
            return false;
        }
        return true;
    }
}
