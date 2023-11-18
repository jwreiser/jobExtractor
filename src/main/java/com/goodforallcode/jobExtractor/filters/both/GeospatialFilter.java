package com.goodforallcode.jobExtractor.filters.both;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class GeospatialFilter implements JobFilter {
    List<String> bothPhrases =List.of("geospatial "," GIS ");
    List<String> titlePhrases =List.of("GIS ");
    @Override
    public boolean include(Preferences preferences, Job job) {
        final String title= job.getTitle().toLowerCase();

        if (bothPhrases.stream().anyMatch(k -> job.getTitle().contains(k.toLowerCase()))) {
            System.err.println("geospatial title both ->reject: " + job);
            return false;
        }
        if (titlePhrases.stream().anyMatch(k -> job.getTitle().contains(k.toLowerCase()))) {
            System.err.println("geospatial title ->reject: " + job);
            return false;
        }
        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            if (bothPhrases.stream().anyMatch(k -> description.contains(k.toLowerCase()))) {
                System.err.println("geospatial description both  ->reject: " + job);
                return false;
            }
        }
        return true;
    }
}
