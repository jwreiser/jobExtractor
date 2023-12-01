package com.goodforallcode.jobExtractor.filter;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.filters.shallow.title.BackendTitleFilter;
import com.goodforallcode.jobExtractor.filters.shallow.title.ProgrammingFrameworkFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.util.TestUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProgrammingFrameworkFilterTests {
    JobFilter filter =new ProgrammingFrameworkFilter();
    BackendTitleFilter backendTitleFilter =new BackendTitleFilter();
    @Test
    void testIncludeTitle(){
        assertFalse(filter.include(TestUtil.getDefaultPreferences(),
                new Job("java Developer with Drools - Remote - W2/1099 only")));
    }
    @Test
    void testIncludeTitle_IncludeFrameworksInPreferences(){
        assertTrue(filter.include(TestUtil.getDefaultPreferences(),
                new Job("java Developer with Spring - Remote - W2/1099 only")));
    }
}
