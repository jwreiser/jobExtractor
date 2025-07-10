package com.goodforallcode.jobExtractor.job.populate.field;

import com.goodforallcode.jobExtractor.filters.ExcludeJobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class AggressiveTimeLinesPopulator implements FieldPopulator {

    public void populateField(Job job, Preferences preferences) {

        ExcludeJobFilter filter = ExcludeJobFilter.build("AggressiveTimelines")
                .matchingCompanies(List.of("Clarifai", "Digital Technology Partners",
                        "Stryker", "Clover Health","Lyra"))
                .descriptionPhrases(List.of("fast-moving", "fast-paced", "fast paced", "aggressive timelines",
                        "aggressive delivery schedule"))
                .testForCompanyInDescription(true)
                .excludeCompanyAttributes(List.of("fastPaced"));


        if (filter.exclude(job) != null) {
            job.setAggressiveTimelines(true);
        }
    }


}

