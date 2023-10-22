package com.goodforallcode.jobExtractor;

import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.model.preferences.PreferencesWithDefaults;

import java.util.List;

public class TestUtil {
    public static Preferences getDefaultPreferences(){
        Preferences preferences = new PreferencesWithDefaults();
        preferences.setProgrammingLanguages(List.of("Java"));
        preferences.setMinFortuneRank(760);
        preferences.setExcludeAboveSenior(true);
        preferences.setLocationPhrases(List.of("NY"," New York"));
        return preferences;
    }
}
