package com.goodforallcode.jobExtractor.filters.shallow.title;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyUtil;

import java.util.List;

public class IdentityManagementTitleFilter implements JobFilter {
    List<String> phrases =List.of( "Identity Governance"," IAM ",
            "Access Management",
            "Sailpoint","IdAM ","Active Directory");
    List<String> exclusiveDescriptionPhrases =List.of("Identity Engineer");
    List<String> titlePhrases =List.of("IAM ","Identity Engineer");
    List<String> identitySoftware =List.of( "CyberArk");
    List<String> companyNames =List.of( "Provision IAM");

    @Override
    public boolean include(Preferences preferences, Job job) {
        if(!preferences.isExcludeIdentityManagement()){
            return true;
        }
        final String title = job.getTitle().toLowerCase();
        final String companyName = job.getCompanyName();

        if (companyNames.stream().anyMatch(cn-> CompanyUtil.containsCompanyName(cn,job))){
            System.err.println("Identity Company Name ->reject: " + job);
            return false;
        }

        if (titlePhrases.stream().anyMatch(k -> title.contains(k.toLowerCase()))) {
            System.err.println("Identity Title only ->reject: " + job);
            return false;
        }
        if (companyNames.stream().anyMatch(cn  -> title.contains(cn.toLowerCase()))) {
            System.err.println("Identity Title contains company name ->reject: " + job);
            return false;
        }
        if (phrases.stream().anyMatch(k -> title.contains(k.toLowerCase()))) {
            System.err.println("Identity Title ->reject: " + job);
            return false;
        }
        return true;
    }
}
