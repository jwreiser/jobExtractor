package com.goodforallcode.jobExtractor.util;

import com.goodforallcode.jobExtractor.model.preferences.PreferencesWithDefaults;

import java.util.List;

public class TestUtil {
    public static PreferencesWithDefaults getDefaultPreferences(){
        PreferencesWithDefaults preferences = new PreferencesWithDefaults();
        preferences.setProgrammingLanguages(List.of("Java"));
        preferences.setMinFortuneRank(760);
        preferences.setExcludeAboveSenior(true);
        preferences.setExcludeFullStack(true);
        preferences.setExcludeFrontEndJobs(true);
        preferences.setExcludeIdentityManagement(true);
        preferences.setMinYearlySalary(80000);
        preferences.setMaxLevel(2);
        preferences.setExcludeConsultant(true);
        preferences.setExcludePromoted(false);
        preferences.setAmountOfTotalExperience(6);
        preferences.setMaxEmployees(9000);
        preferences.setExcludeConsultant(true);
        preferences.setAmountOfTotalExperience(6);

        preferences.setMaxJobAgeInDays(60);
        preferences.setExcludeFresher(true);
        preferences.setExcludeSenior(true);
        preferences.setExcludeBigData(true);
        preferences.setExcludeBlockchain(true);
        preferences.setExcludeCloudHeavy(true);
        preferences.setExcludeComplexJobs(true);
        preferences.setExcludeRealEstate(true);
        preferences.setSkipTooManyApplicants(false);
        preferences.setSkipJobsSourcedFromExternalJobBoard(false);

        preferences.setLocationPhrases(List.of("NY"," New York"));
        return preferences;
    }
}
