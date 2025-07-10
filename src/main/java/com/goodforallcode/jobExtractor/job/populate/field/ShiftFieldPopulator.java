package com.goodforallcode.jobExtractor.job.populate.field;

import com.goodforallcode.jobExtractor.filters.ExcludeJobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class ShiftFieldPopulator implements FieldPopulator {
    public void populateField(Job job, Preferences preferences) {


        ExcludeJobFilter filter = ExcludeJobFilter.build("NightShift").titleAndDescriptionPhrases(List.of())
                .descriptionPhrases(List.of("pm to close"))
                .titlePhrases(List.of(" night ", " nights"))
                .titleStartsWithPhrases(List.of("night "))
                .titleAndDescriptionPhrases(List.of("Evening", "- 7A", "- 7A", "- 7:30A", "- 8:30A", "- 8A", "- 8A",
                        "-11p","-12AM","-1am", "-2am", "-3am", "-4am", "-5am", "-6am", "-7am", "-8am",
                        "nights","Over Night",
                        "2nd shift", "3rd shift", "second shift", "third shift", "night shift", "evening shift", "graveyard shift","overnight"))
                .excludeCompanyAttributes(List.of("softwareEngineerNightOrWeekendHours"))
                .safeDescriptionPhrases(List.of("some nights "))
                .matchingCompanies(List.of("Toast"));


        if (filter.exclude(job) != null) {
            job.setNightShift(true);
        }

    }
}
