package com.goodforallcode.jobExtractor.job.populate.field;

import com.goodforallcode.jobExtractor.filters.ExcludeJobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.ArrayList;
import java.util.List;

public class ConsultantPopulator implements FieldPopulator {
    public void populateField(Job job, Preferences preferences) {
        /*exceptions
    planning consultants
    consulting with customers
     */

        ExcludeJobFilter filter = ExcludeJobFilter.build("Consultant")
                .titlePhrases(List.of("consulting", "consultant"))
                .badCompanies(List.of("Curate Partners", "Modis", "Akkodis","Open Systems Technologies"
                        , "Ricardo plc", "FullStack Labs", "Sierra7",  "Vaco", "QuantumBricks",
                        "Lorven Technologies Inc.", "ZETTALOGIX INC", "Sierra Solutions", "CGI",
                        "Daugherty Business Solutions", "World Wide Technology", "Qualitest", "Cognizant",
                        "Solü Technology Partners", "Nakupuna Companies", "SDI Presence",
                        "DMI (Digital Management, LLC)", "Next Level Business Services",
                        "NLB Services"))
                .industries(List.of("Business Consulting and Services", "Consulting"))
                .descriptionPhrases(List.of("consulting"))
                .safeDescriptionPhrases(List.of("consulting with"))
                .titleCompanyNameAndDescriptionPhrases(List.of("consultancy"))
                .testForCompanyInDescription(true)
                .excludeAttributes(List.of("consulting")
                );
        if (filter.exclude(job) != null) {
            job.setConsultant(true);
        }

    }
}
