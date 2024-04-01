package com.goodforallcode.jobExtractor.filters.include;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyUtil;

import java.util.List;

public class PublicGoodFilter implements JobFilter {
    List<String> phrases = List.of("nonprofit", "health equity", "fair opportunity", "unjust"
            , "structural inequities", "racism", "underserved", "food access");
    List<String> industryPhrases = List.of("Hospitals and Health Care", "Mental Health Care", "Higher Education");
    List<String> companyNames = List.of("Blackbaud", "Mayo Clinic");

    public boolean include(Preferences preferences, Job job) {
        if (companyNames.stream().anyMatch(c -> job.getCompanyName().equals(c))) {
            System.err.println("public good company name-> include: " + job);
            return true;
        }
        if (job.getDescription() != null) {
            final String description = job.getDescription().toLowerCase();
            if (phrases.stream().anyMatch(p -> description.contains(p))) {
                System.err.println("public good -> include: " + job);
                return true;
            }
            if (companyNames.stream().anyMatch(c -> CompanyUtil.descriptionContainsCompanyName(c, job.getDescription()))) {
                System.err.println("public good based on company description ->reject: " + job);
                return false;
            }
        }
        if (job.getIndustries() != null) {
            if (industryPhrases.stream().anyMatch(p -> job.getIndustries().contains(p))) {
                System.err.println("public good -> include: " + job);
                return true;
            }

        }
        if(job.getCompany()!=null && job.getCompany().getPublicGood()!=null && job.getCompany().getPublicGood()){
            return true;
        }
        return false;

    }
}
