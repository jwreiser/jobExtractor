package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class ProfitFocusedFilter implements JobFilter {
    List<String> phrases =List.of("financial","marketing","entrepreneur","insurance"
    ,"banking","trading","FinTech","betting","casino","finance","blockchain","crypto"
    ,"DeFi","Brokers","Brokerage","firm");
    public boolean include(Preferences preferences, Job job){
        if(!preferences.isExcludeProfitFocusedCompanies()){
            return true;
        }
        String description =job.getDescription().toLowerCase();
        if (phrases.stream().anyMatch(p->description.contains(p))) {
            System.err.println("profit focused ->reject: " + job);
            return false;
        }
        return true;

    }
}
