package com.goodforallcode.jobExtractor.filter;

import com.goodforallcode.jobExtractor.filters.shallow.title.BackendTitleFilter;
import com.goodforallcode.jobExtractor.filters.shallow.title.ProgrammingLanguageTitleFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.util.TestUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProgrammingLanguageFilterTests {
    ProgrammingLanguageTitleFilter programmingLanguageTitleFilter =new ProgrammingLanguageTitleFilter(true);
    BackendTitleFilter backendTitleFilter =new BackendTitleFilter();
    @Test
    void testNotEnoughExperience() {
        assertFalse(ProgrammingLanguageTitleFilter.notEnoughExperience(" 5+ years of development experience with java 8+",TestUtil.getDefaultPreferences()));

        assertTrue(ProgrammingLanguageTitleFilter.notEnoughExperience(" 5+ years of development experience with ruby",TestUtil.getDefaultPreferences()));
        assertTrue(ProgrammingLanguageTitleFilter.notEnoughExperience(" 4+ years of solid coding experience working in python.",TestUtil.getDefaultPreferences()));
        assertTrue(ProgrammingLanguageTitleFilter.notEnoughExperience(" At least 5 years of experience building Python products with a focus on Network Architectures (PPP, routers, modems etc.).".toLowerCase(),TestUtil.getDefaultPreferences()));

    }

    @Test
    void testContainsLanguage() {
        assertTrue(ProgrammingLanguageTitleFilter.containsUnknownLanguage("proficiency in high-level and scripting languages (c++, python, bash, groovy)",TestUtil.getDefaultPreferences()));
        assertFalse(ProgrammingLanguageTitleFilter.containsUnknownLanguage("proficiency in high-level and scripting languages (c++, python, bash, groovy,java)",TestUtil.getDefaultPreferences()));
        assertTrue(ProgrammingLanguageTitleFilter.containsUnknownLanguage("proficiency in high-level and scripting languages (c++, python, bash, groovy). new sentence java",TestUtil.getDefaultPreferences()));
        assertTrue(ProgrammingLanguageTitleFilter.containsUnknownLanguage("proficiency in high-level and scripting languages (c++, python, bash, groovy); new thought java",TestUtil.getDefaultPreferences()));
        assertTrue(ProgrammingLanguageTitleFilter.containsUnknownLanguage("proficiency in high-level and scripting languages (c++, python, bash, groovy) outside of parenthesis java",TestUtil.getDefaultPreferences()));
    }
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
