package com.goodforallcode.jobExtractor.job.populate.field;

import com.goodforallcode.jobExtractor.filters.ExcludeJobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.StringUtil;

import java.util.List;
import java.util.Optional;

public class SeniorityPopulator implements FieldPopulator {
    public void populateField(Job job, Preferences preferences) {

        List<String> titleAboveSeniorSeniorityLevels = List.of("stf ", "director", "VP ", "lead", "manager", "Supervisor"
                , "architect", "administrator", "chief", "principal", "Systems Development", "Systems Developer", "staff ", " staff"
                , "Foreperson", "Dean ", "Mgr ", "President");

        List<String> titleArchitectSeniorityLevels = List.of(
                "architect", "Systems Engineer", "System Engineer");

        //specialist is can be used in non senior roles, so it is not included here, but am including it in the safe list in case we find a good level for it
        List<String> titleSafeAboveSeniorSeniorityLevels = List.of("Customer Service Specialist", "Recreational Specialist");

        List<String> titleSeniorSeniorityLevels = List.of("senior", "sr. ", "sr ");
        List<String> titleSafeSeniorSeniorityLevels = List.of();

        List<String> titleSafeFresherSeniorityLevels = List.of("internal");
        List<String> titleFresherSeniorityLevels = List.of("intern", "trainee", "apprentice", "fresher");

        List<String> titleMidCareerSeniorityLevels = List.of("junior", "jr.", "jr ");

        List<String> titleRequiresExperience = List.of("specialist");


        /**
         * Exceptions:
         * EARLY INTERVENTION
         */
        List<String> titleJuniorSeniorityLevels = List.of("junior", "jr.", "entry level", "early career", "Entry-Level");

        List<String> clumpedSeniorityLevels = titleJuniorSeniorityLevels;


        Optional<String> matchingPhrase = titleSafeAboveSeniorSeniorityLevels.stream().filter(k -> job.getTitle().toLowerCase().contains(k.toLowerCase())).findFirst();
        if (!matchingPhrase.isPresent()) {
            titleAboveSeniorSeniorityLevels.stream().filter(k -> job.getTitle().toLowerCase().contains(k.toLowerCase()))
                    .findFirst().ifPresent(seniority -> {
                        job.setSeniority(seniority.trim());
                        job.setAboveSenior(true);
                        job.setNoExperience(false);
                    });
        }

        titleArchitectSeniorityLevels.stream().filter(k -> job.getTitle().toLowerCase().contains(k.toLowerCase()))
                .findFirst().ifPresent(seniority -> {
                    job.setSeniority(seniority.trim());
                    job.setAboveSenior(true);
                    job.setArchitect(true);
                    job.setNoExperience(false);
                });

        matchingPhrase = titleSafeSeniorSeniorityLevels.stream().filter(k -> job.getTitle().toLowerCase().contains(k.toLowerCase())).findFirst();
        if (StringUtil.valueNotPopulated(job.getSeniority()) && !matchingPhrase.isPresent()) {
            titleSeniorSeniorityLevels.stream().filter(k -> job.getTitle().toLowerCase().contains(k.toLowerCase()))
                    .findFirst().ifPresent(seniority -> {
                        job.setSeniority(seniority.trim());
                        job.setSenior(true);
                        job.setNoExperience(false);
                    });
        }
        clumpedSeniorityLevels.stream().filter(k -> job.getTitle().toLowerCase().contains(k.toLowerCase()))
                .findFirst().ifPresent(seniority -> {
                    job.setSeniority(seniority.trim());
                });

        matchingPhrase = titleSafeFresherSeniorityLevels.stream().filter(k -> job.getTitle().toLowerCase().contains(k.toLowerCase())).findFirst();
        if (StringUtil.valueNotPopulated(job.getSeniority()) && !matchingPhrase.isPresent()) {
            titleFresherSeniorityLevels.stream().filter(k -> job.getTitle().toLowerCase().contains(k.toLowerCase()))
                    .findFirst().ifPresent(seniority -> {
                        job.setSeniority(seniority.trim());
                        job.setNoExperience(true);
                    });
        }
        if (StringUtil.valueNotPopulated(job.getSeniority())) {//this must go last so that it doesn't override the above with a potentially incorrect "associate" value

            titleMidCareerSeniorityLevels.stream().filter(k -> job.getTitle().toLowerCase().contains(k.toLowerCase()))
                    .findFirst().ifPresent(seniority -> {
                        job.setSeniority(seniority.trim());
                        job.setMidCareer(true);
                        job.setNoExperience(false);
                    });
        }


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

        ExcludeJobFilter filter = ExcludeJobFilter.build("Senior")
                .descriptionPhrases(List.of("Completes product technical design",
                        "produce software designs", "subordinate", "lead project teams"))
                .safeDescriptionPhrases(List.of("Intermediate to advanced knowledge of"));
        if (filter.exclude(job) != null) {
            job.setSeniority("senior");
            job.setSenior(true);
            job.setNoExperience(false);
        }

        filter = ExcludeJobFilter.build("FresherAndIntern")
                .descriptionPhrases(List.of(" intern ", "Fresher", "intern ", "internship"));
        if (filter.exclude(job) != null) {
            job.setSeniority("entry level");
            job.setSenior(false);
            job.setNoExperience(true);
        }

        filter = ExcludeJobFilter.build("MidCareer").titleAndDescriptionPhrases(List.of(
                        "intermediate"))
                .safeDescriptionPhrases(List.of("Intermediate Care"));
        ;
        if (filter.exclude(job) != null) {
            job.setSeniority("mid career");
            job.setSenior(false);
            job.setNoExperience(false);
            job.setMidCareer(true);
        }
        if (StringUtil.valueNotPopulated(job.getSeniority())) {//this must go last so that it doesn't override the above with a potentially incorrect "associate" value
            titleRequiresExperience.stream().filter(k -> job.getTitle().toLowerCase().contains(k.toLowerCase()))
                    .findFirst().ifPresent(seniority -> {
                        job.setSeniority(seniority.trim());
                        job.setNoExperience(false);
                    });

        }

    }
}
