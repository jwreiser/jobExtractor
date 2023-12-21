package com.goodforallcode.jobExtractor.util;

import com.goodforallcode.jobExtractor.model.preferences.PreferencesWithDefaults;

import java.util.List;

public class TestUtil {
    public static PreferencesWithDefaults getDefaultPreferences(){
        PreferencesWithDefaults preferences = new PreferencesWithDefaults();
        preferences.setProgrammingLanguages(List.of("Java","pl/sql"));
        preferences.setProgrammingFrameworks(List.of("Spring"));
        preferences.setMinFortuneRank(760);
        preferences.setExcludeIdentityManagement(true);
        preferences.setMinYearlySalary(80000);
        preferences.setMaxLevel(2);
        preferences.setAmountOfTotalExperience(6);
        preferences.setMaxEmployees(9000);
        preferences.setExcludeConsultant(true);
        preferences.setAmountOfTotalExperience(6);
        preferences.setMaxJobAgeInDays(4);

        preferences.setExcludeAboveSenior(true);
        preferences.setExcludeBigData(true);
        preferences.setExcludeBlockchain(true);
        preferences.setExcludeCloudHeavy(true);
        preferences.setExcludeComplexJobs(true);
        preferences.setExcludeConsultant(true);
        preferences.setExcludeFresher(true);
        preferences.setExcludeFrontEndJobs(true);
        preferences.setExcludeFullStack(true);
        preferences.setExcludePromoted(false);
        preferences.setExcludeRealEstate(true);
        preferences.setExcludeSenior(true);

        preferences.setSkipTooManyApplicants(false);
        preferences.setSkipUnknownNumberOfApplicants(false);
        preferences.setSkipJobsSourcedFromExternalJobBoard(false);

        preferences.setLocationPhrases(List.of("NY","New York","Babylon","Melville","Hauppauge","Ronkonkoma","Islandia"
                ,"Plainview","Stony Brook"));
        return preferences;
    }
}
