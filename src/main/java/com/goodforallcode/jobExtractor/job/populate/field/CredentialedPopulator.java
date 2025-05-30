package com.goodforallcode.jobExtractor.job.populate.field;

import com.goodforallcode.jobExtractor.filters.ExcludeJobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class CredentialedPopulator implements FieldPopulator {
    public void populateField(Job job, Preferences preferences) {


        ExcludeJobFilter filter = ExcludeJobFilter.build("Credentialed")
                .titlePhrases(List.of("LCSW", "Certified", "Licensed", "Registered", "LPN", "CRN", "PCA", "-RN ", " RN ", "BCBA", "LMSW","/PRN"))
                .safeTitlePhrases(List.of(" train"));

        if (filter.exclude(job) != null) {
            job.setCredentialed(true);
        }

    }
}
