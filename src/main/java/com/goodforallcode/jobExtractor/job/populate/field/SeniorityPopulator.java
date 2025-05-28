package com.goodforallcode.jobExtractor.job.populate.field;

import com.goodforallcode.jobExtractor.filters.ExcludeJobFilter;
import com.goodforallcode.jobExtractor.filters.IncludeOrSkipJobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.ArrayList;
import java.util.List;

public class SeniorityPopulator implements FieldPopulator {
    public void populateField(Job job, Preferences preferences) {

        List<String> titleAboveSeniorSeniorityLevels = new ArrayList<>(List.of("stf ", "director", "VP ", "lead", "manager", "Supervisor"
                , "architect", "administrator", "chief", "principal", "Systems Development", "Systems Developer"));
        if (preferences.isSoftwareSearch()) {
            titleAboveSeniorSeniorityLevels.addAll(List.of("staff ", " staff"));
        }

        List<String> titleSeniorSeniorityLevels = List.of("senior", "sr. ","specialist", "sr ");

        List<String> titleFresherSeniorityLevels = List.of( "intern", "trainee", "apprentice", "fresher");
        List<String> titleMidCareerSeniorityLevels = List.of("associate");

        List<String> titleJuniorSeniorityLevels = List.of("junior","jr.", "entry level","early");

        List<String> clumpedSeniorityLevels = titleJuniorSeniorityLevels;

        titleAboveSeniorSeniorityLevels.stream().filter(k -> job.getTitle().toLowerCase().contains(k.toLowerCase()))
                .findFirst().ifPresent(seniority -> {
                    job.setSeniority(seniority);
                    job.setAboveSenior(true);
                });

        titleSeniorSeniorityLevels.stream().filter(k -> job.getTitle().toLowerCase().contains(k.toLowerCase()))
                .findFirst().ifPresent(seniority -> {
                    job.setSeniority(seniority);
                    job.setSenior(true);
                });

        titleMidCareerSeniorityLevels.stream().filter(k -> job.getTitle().toLowerCase().contains(k.toLowerCase()))
                .findFirst().ifPresent(seniority -> {
                    job.setSeniority(seniority);
                    job.setMidCareer(true);
                });

        clumpedSeniorityLevels.stream().filter(k -> job.getTitle().toLowerCase().contains(k.toLowerCase()))
                .findFirst().ifPresent(seniority -> {
                    job.setSeniority(seniority);
                });

        titleFresherSeniorityLevels.stream().filter(k -> job.getTitle().toLowerCase().contains(k.toLowerCase()))
                .findFirst().ifPresent(seniority -> {
                    job.setSeniority(seniority);
                    job.setNoExperience(true);
                });


        /**
         * Exceptions
         * Coaches: Health Coaches(Benefit)
         * expert: recruiter may have expertise and seems hyperbolic at times
         * principal can only be in title because it could be reports to
         * specialist also may refer to recruiter
         *
         * subject matter expert:   seems to happen in non senior roles too
         * Advanced knowledge of:   seems to happen in quite jr roles too
         */

        ExcludeJobFilter filter=ExcludeJobFilter.build("Senior")
                .descriptionPhrases(List.of("Completes product technical design",
                        "produce software designs", "subordinate", "lead project teams"))
                .safeDescriptionPhrases(List.of("Intermediate to advanced knowledge of"));
        if (filter.exclude(job) != null) {
            job.setSeniority("senior");
            job.setSenior(true);
            job.setNoExperience(false);
        }

            filter=ExcludeJobFilter.build("FresherAndIntern")
                    .descriptionPhrases(List.of(" intern ","Fresher", "intern ", "internship"));
        if (filter.exclude(job) != null) {
            job.setSeniority("entry level");
            job.setSenior(false);
            job.setNoExperience(true);
        }

        /**
         * Exceptions duties associated patient
         */
        filter=ExcludeJobFilter.build("MidCareer").titleAndDescriptionPhrases(List.of(
                "associate ", "junior", "jr", "intermediate"));
        if (filter.exclude(job) != null) {
            job.setSeniority("mid career");
            job.setSenior(false);
            job.setNoExperience(false);
            job.setMidCareer(true);
        }


    }
}
