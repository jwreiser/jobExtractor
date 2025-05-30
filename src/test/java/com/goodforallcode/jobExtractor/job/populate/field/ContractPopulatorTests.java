package com.goodforallcode.jobExtractor.job.populate.field;

import com.goodforallcode.jobExtractor.filters.ExcludeJobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.model.preferences.PreferencesWithDefaults;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ContractPopulatorTests {
    @Test
    void testGE() {
        boolean match = false;
        ContractPopulator populator = new ContractPopulator();
        Job job = new Job("DYNAMITE - Team Leader PT - Smith Haven MallDYNAMITE - Team Leader PT - Smith Haven Mall","Garage Clothing");
        Preferences preferences = new PreferencesWithDefaults();
        populator.populateField(job, preferences);
        assertNull(job.getContract());
    }
}
