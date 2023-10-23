package com.goodforallcode.jobExtractor.model;

import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.model.preferences.PreferencesWithDefaults;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class QueryInput {
    List<String> urls;
    PreferencesWithDefaults preferences;
    String userName;
    String password;
}
