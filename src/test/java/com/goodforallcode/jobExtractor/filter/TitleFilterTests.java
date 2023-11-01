package com.goodforallcode.jobExtractor.filter;

import com.goodforallcode.jobExtractor.util.TestUtil;
import com.goodforallcode.jobExtractor.filters.shallow.title.BackendTitleFilter;
import com.goodforallcode.jobExtractor.filters.shallow.title.ProgrammingLanguageTitleFilter;
import com.goodforallcode.jobExtractor.model.Job;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TitleFilterTests {
    ProgrammingLanguageTitleFilter programmingLanguageTitleFilter =new ProgrammingLanguageTitleFilter(true);
    BackendTitleFilter backendTitleFilter =new BackendTitleFilter();
    @Test
    void testProgrammingLanguageIncluded(){
        assertTrue(programmingLanguageTitleFilter.include(TestUtil.getDefaultPreferences(),new Job("Entry Level Java Back-End Developer")));
        assertTrue(programmingLanguageTitleFilter.include(TestUtil.getDefaultPreferences(),new Job("Entry Level Java Back-End Developer")));
        assertTrue(programmingLanguageTitleFilter.include(TestUtil.getDefaultPreferences(),new Job("Junior Java Back-End Developer (Entry Level)")));
        assertTrue(programmingLanguageTitleFilter.include(TestUtil.getDefaultPreferences(),new Job("Java AND SQL Developer")));
        assertTrue(programmingLanguageTitleFilter.include(TestUtil.getDefaultPreferences(),new Job("Java AND PL/SQL Developer")));


    }

    @Test
    void testBackendIncluded(){

        assertTrue(programmingLanguageTitleFilter.include(TestUtil.getDefaultPreferences(),new Job("Backend Developers- 100% remote")));
        assertTrue(programmingLanguageTitleFilter.include(TestUtil.getDefaultPreferences(),new Job("Backend Developer")));
        assertTrue(programmingLanguageTitleFilter.include(TestUtil.getDefaultPreferences(),new Job("Backend Engineer(Entry-Level)")));
        assertTrue(programmingLanguageTitleFilter.include(TestUtil.getDefaultPreferences(),new Job("Back End Developer")));
        assertTrue(programmingLanguageTitleFilter.include(TestUtil.getDefaultPreferences(),new Job("Entry Level Java Back-End Developer")));
        assertTrue(programmingLanguageTitleFilter.include(TestUtil.getDefaultPreferences(),new Job("Junior Java Back-End Developer (Entry Level)")));
        assertTrue(programmingLanguageTitleFilter.include(TestUtil.getDefaultPreferences(),new Job("Junior Backend Engineer")));
        assertTrue(programmingLanguageTitleFilter.include(TestUtil.getDefaultPreferences(),new Job("Jr Backend Engineer")));

    }
}
