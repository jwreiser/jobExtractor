package com.goodforallcode.jobExtractor.filters.deep.always;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyNameUtil;

import java.util.List;

public class WorkLifeBalanceFilter implements JobFilter {
    Boolean include;

    public WorkLifeBalanceFilter(Boolean include) {
        this.include = include;
    }

    /*
        exceptions
        aggressive: aggressive expansion might not indicate work life balance problems
        critical : thinking
        pressure: work well under pressure does not mean bad balance. Seems like trope not sure if it would mean lack of balance
         */
    List<String> phrases =List.of("on call","on-call","go-live support",
            "fast-moving","fast-paced", "fast paced","24/7","aggressive timelines"," 24-7 ",
           "24x7","rotation","After business hours","After hours","aggressive delivery schedule",
            "nights","weekends","outside of normal business","outside normal business");
    List<String> goodCompanies =List.of("Ebay","Guidehouse","Trimble"
            ,"American Specialty Health","Nationwide");
    List<String> badCompanies =List.of("Cardinal Health","The Home Depot","Aha!","Cash App"
    ,"Square","Crunchyroll","HCLTech","Palo Alto Networks","Intelerad Medical Systems",
            "Tenable","Kasten by Veeam","Dremio","Gigster","Samsung Electronics",
            "Arize AI","Gevo, Inc.","Harmonia Holdings Group, LLC","Block",
            "Penn State Health","Actalent","Grafana Labs"
    );
    List<String> fastCompanies =List.of("Clarifai","Digital Technology Partners"
    );
    List<String> safePhrases =List.of("internal rotation");

    public boolean include(Preferences preferences, Job job){
        if(!preferences.isExcludePoorWorkLifeBalance()){
            return true;
        }
        if(goodCompanies.stream().anyMatch(c->job.getCompanyName().equals(c))){
            System.err.println("good work life balance company ->include: " + job);
            return true;
        }

        if(badCompanies.stream().anyMatch(c->job.getCompanyName().equals(c))){
            System.err.println("bad work life balance company ->reject: " + job);
            return false;
        }
        if(fastCompanies.stream().anyMatch(c->job.getCompanyName().equals(c))){
            System.err.println("bad work life balance company fast ->reject: " + job);
            return false;
        }
        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            if (safePhrases.stream().anyMatch(p -> description.contains(p))) {
                return true;
            }
            if (phrases.stream().anyMatch(p -> description.contains(p))) {
                System.err.println("work life balance ->reject: " + job);
                return false;
            }
            if(badCompanies.stream().anyMatch(c-> CompanyNameUtil.containsCompanyName(c, job.getDescription()))){
                System.err.println("worklife balance based on company description ->reject: " + job);
                return false;
            }
            if(fastCompanies.stream().anyMatch(c-> CompanyNameUtil.containsCompanyName(c, job.getDescription()))){
                System.err.println("worklife balance based on company description fast->reject: " + job);
                return false;
            }
        }
        return !include;

    }
}
