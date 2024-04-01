package com.goodforallcode.jobExtractor.filters.shallow.company;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyUtil;

import java.util.List;

public class CommerceFilter implements JobFilter {
    List<String> companyNames = List.of("Whatnot","Nisum");
    List<String> titles = List.of("OMS ","Magento");
    List<String> industries = List.of("Retail","E-commerce");

    List<String> bothPhrases = List.of("Hybris", " OMS ", "Vericent","Varicent", "Shopify");

    @Override
    public boolean include(Preferences preferences, Job job) {
        if (job.getIndustries() != null) {
            if (industries.stream().anyMatch(k -> job.getIndustries(). contains(k))) {
                System.err.println("commerce industry ->reject: " + job);
                return false;
            }
        }

        final String title = job.getTitle().toLowerCase();
        if (titles.stream().anyMatch(k -> title.contains(k.toLowerCase()))) {
            System.err.println("commerce title ->reject: " + job);
            return false;
        }

        if (bothPhrases.stream().anyMatch(k -> title.contains(k.toLowerCase()))) {
            System.err.println("commerce title both ->reject: " + job);
            return false;
        }
        if (companyNames.stream().anyMatch(cn-> CompanyUtil.containsCompanyName(cn,job))){
            System.err.println("ecommerce ->reject: " + job);
            return false;
        }

        if (job.getDescription() != null) {
            String description = job.getDescription().toLowerCase();
            if (bothPhrases.stream().anyMatch(p -> description.contains(p.toLowerCase()))) {
                System.err.println("Commerce->reject: " + job);
                return false;
            }
            if(companyNames.stream().anyMatch(c-> CompanyUtil.descriptionContainsCompanyName(c,job.getDescription()))){
                System.err.println("commerce based on company description ->reject: " + job);
                return false;
            }
        }

        return true;
    }
}
