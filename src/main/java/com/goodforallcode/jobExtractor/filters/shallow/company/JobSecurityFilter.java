package com.goodforallcode.jobExtractor.filters.shallow.company;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.CompanySummary;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyUtil;

import java.util.List;

public class JobSecurityFilter implements JobFilter {
    List<String> badCompanyNames =List.of( "Allstate","New Relic","Breezeline","Slack",
    "Crossover","Invitae","Omnicell","Komodo Health","Rocket Software","Zinnia","CSG",
    "NTT DATA Services","Cruise","VMware","Intelerad Medical Systems",
    "Air Apps","CivicPlus","Vertisystem Inc.","Kyruus","Atlassian");

    List<String> goodCompanyNames =List.of( "Mayo Clinic");

    boolean include;

    public JobSecurityFilter(boolean include) {
        this.include = include;
    }

    @Override
    public boolean include(Preferences preferences, Job job) {
        if (include && goodCompanyNames.stream().anyMatch(cn -> CompanyUtil.containsCompanyName(cn, job))) {
            System.err.println("good job security ->include: " + job);
            return true;
        }

        if (!include && badCompanyNames.stream().anyMatch(cn -> CompanyUtil.containsCompanyName(cn, job))) {
            System.err.println("Layoffs ->reject: " + job);
            return false;
        }
        if (job.getCompany() != null) {
            CompanySummary sum = job.getCompany();
            if (sum.getJobSecurity() != null) {
                if (include && sum.getJobSecurity()) {
                    return true;
                } else if (!include && !sum.getJobSecurity()) {
                    System.err.println("summary security ->reject: " + job);
                    return false;
                }

            }
            if (!include && sum.getRecentLayoffs() != null && sum.getRecentLayoffs()) {
                System.err.println("summary layoffs ->reject: " + job);
                return false;
            }
        }

        return !include;
    }
}
