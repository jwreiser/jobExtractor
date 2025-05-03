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

    List<String> goodCompanies =List.of("Ebay","Guidehouse","Trimble"
            ,"American Specialty Health","Nationwide","Webstaurant Store",
            "Mayo Clinic");
    List<String> badCompanies =List.of("Cardinal Health","Cruise","CVS Health","Aha!","Cash App"
    ,"Square","Crunchyroll","HCLTech","Palo Alto Networks","Intelerad Medical Systems",
            "Tenable","Kasten by Veeam","Dremio","Gigster","Samsung Electronics",
            "Arize AI","Gevo, Inc.","Harmonia Holdings Group, LLC","Block",
            "Penn State Health","Actalent","Grafana Labs","Softrams","FinTech LLC",
            "Paytient","DaVita","Businessolver","Integra Connect",
            "Discover Financial Services","CivicPlus","Saxon-Global","GE","The Home Depot","The Wendy's Company"
    );

    public boolean include(Preferences preferences, Job job){
        if(!preferences.isExcludePoorWorkLifeBalance()){
            return true;
        }
        if(goodCompanies.stream().anyMatch(cn-> CompanyUtil.containsCompanyName(cn,job))){
            System.err.println(this.getClass()+" company name ->include: " + job);
            return true;
        }

        if(badCompanies.stream().anyMatch(cn-> CompanyUtil.containsCompanyName(cn,job))){
            System.err.println(this.getClass()+" company name ->reject: " + job);
            return false;
        }


        if(job.getCompany()!=null){
            CompanySummary sum = job.getCompany();
            if(sum.getWorkLifeBalance()!=null && !sum.getWorkLifeBalance()){
                System.err.println("work life balance company ->reject: " + job);
                return false;
            }
            if(sum.getSoftwareEngineerHighOvertime()!=null && sum.getSoftwareEngineerHighOvertime()){
                System.err.println("work life balance company OT->reject: " + job);
                return false;
            }
        }
        return !include;

    }
}
