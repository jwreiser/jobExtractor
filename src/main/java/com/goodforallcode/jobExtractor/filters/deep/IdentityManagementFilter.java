package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyNameUtil;

import java.util.List;

public class IdentityManagementFilter implements JobFilter {
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

        if (companyNames.stream().anyMatch(cn-> CompanyNameUtil.containsCompanyName(cn,job))){
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
        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();

            if (exclusiveDescriptionPhrases.stream().filter(k -> description.contains(k.toLowerCase())).count() > 2) {
                System.err.println("Identity description exclusive ->reject: " + job);
                return false;
            }

            if (phrases.stream().filter(k -> description.contains(k.toLowerCase())).count() > 2) {
                System.err.println("Identity description ->reject: " + job);
                return false;
            }

            if (identitySoftware.stream().filter(k -> description.contains(k.toLowerCase())).count() > 2) {
                System.err.println("Identity Software description ->reject: " + job);
                return false;
            }
            if(companyNames.stream().anyMatch(c-> CompanyNameUtil.descriptionContainsCompanyName(c,job.getDescription()))){
                System.err.println("identity management based on company description ->reject: " + job);
                return false;
            }
        }
        return true;
    }
}
