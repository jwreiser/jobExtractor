package com.goodforallcode.jobExtractor.filters.shallow.company;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class CommerceFilter implements JobFilter {
    List<String> companyNames = List.of("Whatnot");
    List<String> titles = List.of("OMS Developer");
    List<String> industries = List.of("Retail");

    List<String> phrases = List.of("Hybris", " OMS ", "Vericent","Varicent", "Shopify");

    @Override
    public boolean include(Preferences preferences, Job job) {
        if (job.getIndustry() != null) {
            if (industries.stream().anyMatch(k -> job.getIndustry().equals(k))) {
                System.err.println("commerce industry ->reject: " + job);
                return false;
            }
        }

        final String title = job.getTitle().toLowerCase();
        if (titles.stream().anyMatch(k -> title.equals(k.toLowerCase()))) {
            System.err.println("commerce title ->reject: " + job);
            return false;
        }

        if (companyNames.stream().anyMatch(k -> job.getCompanyName().equals(k))) {
            System.err.println("ecommerce ->reject: " + job);
            return false;
        }

        if (job.getDescription() != null) {
            String description = job.getDescription().toLowerCase();
            if (phrases.stream().anyMatch(p -> description.contains(p.toLowerCase()))) {
                System.err.println("Commerce->reject: " + job);
                return false;
            }
        }

        return true;
    }
}
