package com.goodforallcode.jobExtractor.job.populate.field;

import com.goodforallcode.jobExtractor.filters.ExcludeJobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class WorklifeBalancePopulator implements FieldPopulator {

    public void populateField(Job job, Preferences preferences) {

        ExcludeJobFilter filter = ExcludeJobFilter.build("WorkLifeBalance")
                .exceptionalCompanies(List.of("Ebay", "Guidehouse", "Trimble", "American Specialty Health", "Nationwide", "Webstaurant Store",
                        "Mayo Clinic"))
                .matchingCompanies(List.of("Cardinal Health", "Cruise", "CVS Health", "Aha!", "Cash App"
                        , "Square", "Crunchyroll", "HCLTech", "Palo Alto Networks", "Intelerad Medical Systems",
                        "Tenable", "Kasten by Veeam", "Dremio", "Gigster", "Samsung Electronics",
                        "Arize AI", "Gevo, Inc.", "Harmonia Holdings", "Block","PKWARE",
                        "Penn State Health", "Actalent", "Grafana Labs", "Softrams", "FinTech LLC",
                        "Paytient", "DaVita", "Businessolver", "Integra Connect", "Corcentric",
                        "Discover Financial Services", "CivicPlus", "Saxon-Global", "Home Depot", "Wendy's"
                ))
                .matchingCompaniesStartsWith(List.of("GE "))
                .excludeCompanyAttributes(List.of("workLifeBalance", "softwareEngineerHighOvertime"))
                .testForCompanyInDescription(true);

        if (filter.exclude(job) != null) {
            job.setWorkLifeBalance(true);
        }
    }


}

