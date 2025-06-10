package com.goodforallcode.jobExtractor.job.populate.field;
import com.goodforallcode.jobExtractor.filters.ExcludeJobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class OnCallPopulator implements FieldPopulator{
    public void populateField(Job job, Preferences preferences) {
        ExcludeJobFilter filter = ExcludeJobFilter.build("OnCall")
                .descriptionPhrases(List.of("on call", "on-call", "go-live support",
                        "24/7", " 24-7 ","24-hour","24 hour",
                        "24x7", "rotation", "After business hours", "After hours",
                        "outside of normal business", "outside normal business"))
                .matchingCompanies(List.of("Oracle", "Ancestry", "Gremlin", "Homecare Homebase","Wealthfront","Verint"))
                .safeDescriptionPhrases(List.of("internal rotation","Day Rotation"))
                .testForCompanyInDescription(true)
                .excludeCompanyAttributes(List.of("softwareEngineerAfterHoursSupport"));

        if (filter.exclude(job) != null) {
            job.setOnCall(true);
        }


    }
}
