package com.goodforallcode.jobExtractor.filters.deep.always;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.CompanySummary;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyUtil;

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
            ,"American Specialty Health","Nationwide","Webstaurant Store",
            "Mayo Clinic");
    List<String> badCompanies =List.of("Cardinal Health","The Home Depot","Aha!","Cash App"
    ,"Square","Crunchyroll","HCLTech","Palo Alto Networks","Intelerad Medical Systems",
            "Tenable","Kasten by Veeam","Dremio","Gigster","Samsung Electronics",
            "Arize AI","Gevo, Inc.","Harmonia Holdings Group, LLC","Block",
            "Penn State Health","Actalent","Grafana Labs","Softrams","FinTech LLC",
            "Cruise","Paytient","DaVita","Businessolver","Integra Connect",
            "Discover Financial Services","CivicPlus","Saxon-Global","GE"
    );
    List<String> fastCompanies =List.of("Clarifai","Digital Technology Partners",
            "Stryker","Clover Health"
    );
    List<String> onCallCompanies =List.of("Oracle","Ancestry","Gremlin","Homecare Homebase"
    );
    List<String> safePhrases =List.of("internal rotation");

    public boolean include(Preferences preferences, Job job){
        if(!preferences.isExcludePoorWorkLifeBalance()){
            return true;
        }
        if(goodCompanies.stream().anyMatch(cn-> CompanyUtil.containsCompanyName(cn,job))){
            return true;
        }

        if(badCompanies.stream().anyMatch(cn-> CompanyUtil.containsCompanyName(cn,job))){
            return false;
        }
        if(fastCompanies.stream().anyMatch(cn-> CompanyUtil.containsCompanyName(cn,job))){
            return false;
        }
        if(onCallCompanies.stream().anyMatch(cn-> CompanyUtil.containsCompanyName(cn,job))){
            return false;
        }
        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            if (safePhrases.stream().anyMatch(p -> description.contains(p))) {
                return true;
            }
            if (phrases.stream().anyMatch(p -> description.contains(p))) {
                return false;
            }
        }
        if(job.getCompany()!=null){
            CompanySummary sum = job.getCompany();
            if(sum.getWorkLifeBalance()!=null && !sum.getWorkLifeBalance()){
                return false;
            }
            if(sum.getFastPaced()!=null && !sum.getFastPaced()){
                return false;
            }
            if(sum.getSoftwareEngineerHighOvertime()!=null && sum.getSoftwareEngineerHighOvertime()){
                return false;
            }
            if(sum.getSoftwareEngineerAfterHoursSupport()!=null && sum.getSoftwareEngineerAfterHoursSupport()){
                return false;
            }
        }
        return !include;

    }
}
