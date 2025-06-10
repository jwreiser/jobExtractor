package com.goodforallcode.jobExtractor.job.populate.field;
import com.goodforallcode.jobExtractor.filters.ExcludeJobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class OutsourcingPopulator implements FieldPopulator{
    public void populateField(Job job, Preferences preferences) {
        ExcludeJobFilter filter = ExcludeJobFilter.build("OutsourcingAndOffshore")
                .matchingCompanies(List.of("Rockwell Automation", "Equinix", "Banner Health", "Ritchie Bros",
                        "Blue Cross NC", "Ascension", "Transamerica", "Conduent", "Zinnia", "LiveRamp",
                        "Intelerad Medical Systems","emids"))
                .testForCompanyInDescription(true)
                .excludeCompanyAttributes(List.of("offshores"))
                .titleAndDescriptionPhrases(List.of("Hyderabad", "India"))
                .descriptionPhrases(List.of("IT services and outsourcing company", "with offshore"));

        if (filter.exclude(job) != null) {
            job.setOutsources(true);
        }


    }
}
