package com.goodforallcode.jobExtractor.job.populate.field;

import com.goodforallcode.jobExtractor.filters.ExcludeJobFilter;
import com.goodforallcode.jobExtractor.filters.IncludeOrSkipJobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SeniorityPopulator implements FieldPopulator {
    public void populateField(Job job, Preferences preferences) {

        List<String> titleAboveSeniorSeniorityLevels = List.of("stf ", "director", "VP ", "lead", "manager", "Supervisor"
                , "architect", "administrator", "chief", "principal", "Systems Development", "Systems Developer","staff ", " staff"
                ,"specialist","Foreperson","Dean ","Mgr ","President");
        List<String> titleSafeAboveSeniorSeniorityLevels = List.of("Customer Service Specialist","Recreational Specialist");

        List<String> titleSeniorSeniorityLevels = List.of("senior", "sr. ", "sr ");
        List<String> titleSafeSeniorSeniorityLevels = List.of();

        List<String> titleFresherSeniorityLevels = List.of( "intern", "trainee", "apprentice", "fresher");

        List<String> titleMidCareerSeniorityLevels = List.of("associate","junior","jr.","jr ");
        List<String> titleSafeMidCareerSeniorityLevels = List.of("Customer Engagement Associate", "Stockroom Associate", "Store Associate", "Client Service Associate"
                , "Support Associate", "Sales Associate", "Customer Service Associate", "Stock Associate","Guest Associate","Patient Care Associate",
                "Accounting Associate", "Unit Associate","Care Associate","Patient Encounter Associate","Health Associate","Board Associate");


        /**
         * Exceptions:
         * EARLY INTERVENTION
         */
        List<String> titleJuniorSeniorityLevels = List.of("junior","jr.", "entry level","early career","Entry-Level");

        List<String> clumpedSeniorityLevels = titleJuniorSeniorityLevels;


        Optional<String> matchingPhrase=titleSafeAboveSeniorSeniorityLevels.stream().filter(k -> job.getTitle().toLowerCase().contains(k.toLowerCase())).findFirst();
        if(!matchingPhrase.isPresent()) {
            titleAboveSeniorSeniorityLevels.stream().filter(k -> job.getTitle().toLowerCase().contains(k.toLowerCase()))
                    .findFirst().ifPresent(seniority -> {
                        job.setSeniority(seniority);
                        job.setAboveSenior(true);
                        job.setNoExperience(false);
                    });
        }

        matchingPhrase=titleSafeSeniorSeniorityLevels.stream().filter(k -> job.getTitle().toLowerCase().contains(k.toLowerCase())).findFirst();
        if(StringUtil.valueNotPopulated(job.getSeniority())&&!matchingPhrase.isPresent()) {
            titleSeniorSeniorityLevels.stream().filter(k -> job.getTitle().toLowerCase().contains(k.toLowerCase()))
                    .findFirst().ifPresent(seniority -> {
                        job.setSeniority(seniority);
                        job.setSenior(true);
                        job.setNoExperience(false);
                    });
        }
        clumpedSeniorityLevels.stream().filter(k -> job.getTitle().toLowerCase().contains(k.toLowerCase()))
                .findFirst().ifPresent(seniority -> {
                    job.setSeniority(seniority);
                });

        titleFresherSeniorityLevels.stream().filter(k -> job.getTitle().toLowerCase().contains(k.toLowerCase()))
                .findFirst().ifPresent(seniority -> {
                    job.setSeniority(seniority);
                    job.setNoExperience(true);
                });

        matchingPhrase=titleSafeMidCareerSeniorityLevels.stream().filter(k -> job.getTitle().toLowerCase().contains(k.toLowerCase())).findFirst();
        if(StringUtil.valueNotPopulated(job.getSeniority())&&!matchingPhrase.isPresent()) {//this must go last so that it doesn't override the above with a potentially incorrect "associate" value

            titleMidCareerSeniorityLevels.stream().filter(k -> job.getTitle().toLowerCase().contains(k.toLowerCase()))
                    .findFirst().ifPresent(seniority -> {
                        job.setSeniority(seniority);
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

        filter=ExcludeJobFilter.build("MidCareer").titleAndDescriptionPhrases(List.of(
                 "intermediate"))
                .safeDescriptionPhrases(List.of("Intermediate Care"));
        ;
        if (filter.exclude(job) != null) {
            job.setSeniority("mid career");
            job.setSenior(false);
            job.setNoExperience(false);
            job.setMidCareer(true);
        }


    }
}
