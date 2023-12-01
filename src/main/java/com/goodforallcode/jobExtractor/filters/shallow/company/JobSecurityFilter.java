package com.goodforallcode.jobExtractor.filters.shallow.company;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyNameUtil;

import java.util.List;

public class JobSecurityFilter implements JobFilter {
    List<String> badCompanyNames =List.of( "Allstate","New Relic","Breezeline","Slack",
    "Crossover","Invitae","Omnicell","Komodo Health","Rocket Software","Zinnia","CSG",
    "NTT DATA Services","Cruise","VMware","Intelerad Medical Systems",
    "Air Apps","CivicPlus");

    List<String> goodCompanyNames =List.of( "Mayo Clinic");

    boolean include;

    public JobSecurityFilter(boolean include) {
        this.include = include;
    }

    @Override
    public boolean include(Preferences preferences, Job job) {
        if(goodCompanyNames.stream().anyMatch(cn-> CompanyNameUtil.containsCompanyName(cn,job))){
            System.err.println("good job security ->include: "+job);
            return true;
        }

        if(badCompanyNames.stream().anyMatch(cn-> CompanyNameUtil.containsCompanyName(cn,job))){
            System.err.println("Layoffs ->reject: "+job);
            return false;
        }
        return !include;
    }
}
