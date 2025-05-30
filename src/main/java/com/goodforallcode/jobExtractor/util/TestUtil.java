package com.goodforallcode.jobExtractor.util;

import com.goodforallcode.jobExtractor.model.preferences.PreferencesWithDefaults;

import java.util.List;

public class TestUtil {
    public static PreferencesWithDefaults getDefaultPreferences(){
        PreferencesWithDefaults preferences = new PreferencesWithDefaults();
        preferences.setProgrammingLanguages(List.of("Java","pl/sql"));
        preferences.setProgrammingFrameworks(List.of("Spring"));
        preferences.setMaxFortuneRank(760);
        preferences.setMinYearlySalary(80000);
        preferences.setMaxLevel(2);
        preferences.setAmountOfTotalExperience(6);
        preferences.setMaxEmployees(9000);
        preferences.setExcludeConsultant(true);
        preferences.setAmountOfTotalExperience(6);
        preferences.setMaxJobAgeInDays(7);

        preferences.setExcludeComplexJobs(true);
        preferences.setExcludeConsultant(true);
        preferences.setExcludePromoted(false);

        preferences.setSkipTooManyApplicants(false);
        preferences.setSkipUnknownNumberOfApplicants(false);

        preferences.setBadLocationPhrases(List.of("NY","New York","Babylon","Melville","Hauppauge","Ronkonkoma","Islandia"
                ,"Plainview","Stony Brook"));

        preferences.setSkills(List.of("Microservices","Java","REST APIs","Git", "Hibernate","Representational State Transfer (REST)", "Maven"));
        return preferences;
    }
}
