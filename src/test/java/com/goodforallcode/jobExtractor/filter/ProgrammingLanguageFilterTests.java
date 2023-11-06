package com.goodforallcode.jobExtractor.filter;

import com.goodforallcode.jobExtractor.filters.shallow.title.BackendTitleFilter;
import com.goodforallcode.jobExtractor.filters.shallow.title.ProgrammingLanguageFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.util.TestUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProgrammingLanguageFilterTests {
    ProgrammingLanguageFilter programmingLanguageFilter =new ProgrammingLanguageFilter(true);
    BackendTitleFilter backendTitleFilter =new BackendTitleFilter();
    @Test
    void testNotEnoughExperience() {
        assertFalse(ProgrammingLanguageFilter.notEnoughExperience(" 5+ years of development experience with java 8+",TestUtil.getDefaultPreferences()));

        assertTrue(ProgrammingLanguageFilter.notEnoughExperience(" 5+ years of development experience with ruby",TestUtil.getDefaultPreferences()));
        assertTrue(ProgrammingLanguageFilter.notEnoughExperience(" 4+ years of solid coding experience working in python.",TestUtil.getDefaultPreferences()));
        assertTrue(ProgrammingLanguageFilter.notEnoughExperience(" At least 5 years of experience building Python products with a focus on Network Architectures (PPP, routers, modems etc.).".toLowerCase(),TestUtil.getDefaultPreferences()));

    }

    @Test
    void testContainsLanguage() {
        assertTrue(ProgrammingLanguageFilter.containsUnknownLanguage("proficiency in high-level and scripting languages (c++, python, bash, groovy)",TestUtil.getDefaultPreferences()));
        assertFalse(ProgrammingLanguageFilter.containsUnknownLanguage("proficiency in high-level and scripting languages (c++, python, bash, groovy,java)",TestUtil.getDefaultPreferences()));
        assertTrue(ProgrammingLanguageFilter.containsUnknownLanguage("proficiency in high-level and scripting languages (c++, python, bash, groovy). new sentence java",TestUtil.getDefaultPreferences()));
        assertTrue(ProgrammingLanguageFilter.containsUnknownLanguage("proficiency in high-level and scripting languages (c++, python, bash, groovy); new thought java",TestUtil.getDefaultPreferences()));
        assertTrue(ProgrammingLanguageFilter.containsUnknownLanguage("proficiency in high-level and scripting languages (c++, python, bash, groovy) outside of parenthesis java",TestUtil.getDefaultPreferences()));
        assertTrue(ProgrammingLanguageFilter.containsUnknownLanguage("expertise with python required, including debugging and testing",TestUtil.getDefaultPreferences()));
    }
    @Test
    void testProgrammingLanguageIncluded(){
        assertTrue(programmingLanguageFilter.include(TestUtil.getDefaultPreferences(),new Job("Entry Level Java Back-End Developer")));
        assertTrue(programmingLanguageFilter.include(TestUtil.getDefaultPreferences(),new Job("Entry Level Java Back-End Developer")));
        assertTrue(programmingLanguageFilter.include(TestUtil.getDefaultPreferences(),new Job("Junior Java Back-End Developer (Entry Level)")));
        assertTrue(programmingLanguageFilter.include(TestUtil.getDefaultPreferences(),new Job("Java AND SQL Developer")));
        assertTrue(programmingLanguageFilter.include(TestUtil.getDefaultPreferences(),new Job("Java AND PL/SQL Developer")));


    }

    @Test
    void testBackendIncluded(){

        assertTrue(programmingLanguageFilter.include(TestUtil.getDefaultPreferences(),new Job("Backend Developers- 100% remote")));
        assertTrue(programmingLanguageFilter.include(TestUtil.getDefaultPreferences(),new Job("Backend Developer")));
        assertTrue(programmingLanguageFilter.include(TestUtil.getDefaultPreferences(),new Job("Backend Engineer(Entry-Level)")));
        assertTrue(programmingLanguageFilter.include(TestUtil.getDefaultPreferences(),new Job("Back End Developer")));
        assertTrue(programmingLanguageFilter.include(TestUtil.getDefaultPreferences(),new Job("Entry Level Java Back-End Developer")));
        assertTrue(programmingLanguageFilter.include(TestUtil.getDefaultPreferences(),new Job("Junior Java Back-End Developer (Entry Level)")));
        assertTrue(programmingLanguageFilter.include(TestUtil.getDefaultPreferences(),new Job("Junior Backend Engineer")));
        assertTrue(programmingLanguageFilter.include(TestUtil.getDefaultPreferences(),new Job("Jr Backend Engineer")));

    }

    @Test
    void testNoCppButLetterC(){
        Job job =new Job("Researcher, Interactive AI");
        job.setCompanyName("Kelly Science, Engineering, Technology & Telecom");
        job.setDescription("5+ years of relevant project experience, with extent and type of experience dictating title and compensation");
        assertFalse(ProgrammingLanguageFilter.notEnoughExperience(job.getDescription(),TestUtil.getDefaultPreferences()));
    }

    @Test
    void testCpp(){
        Job job =new Job("Researcher, Interactive AI");
        job.setCompanyName("Kelly Science, Engineering, Technology & Telecom");
        job.setDescription("5+ years of relevant project experience, with extent and type of experience dictating title and c++");
        assertTrue(ProgrammingLanguageFilter.notEnoughExperience(job.getDescription(),TestUtil.getDefaultPreferences()));
    }
}
