package com.goodforallcode.jobExtractor.filters.include;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyUtil;

import java.util.List;

public class PeopleFocusedFilter implements JobFilter {
    boolean including;

    public PeopleFocusedFilter(boolean including) {
        this.including = including;
    }

    List<String> notPeopleFocusedCompanies =List.of("Maximus","IDR, Inc.","Marketlab",
            "Gainwell Technologies","Revature",
            "Solü Technology Partners");

    List<String> phrases =List.of("life balance","people first","like family");
    public boolean include(Preferences preferences, Job job){
        if(!including && notPeopleFocusedCompanies.stream().anyMatch(cn-> CompanyUtil.containsCompanyName(cn,job))){
            System.err.println("not people focused -> exclude: " + job);
            return false;
        }
        if(including) {
            if(job.getDescription()!=null) {
                String description = job.getDescription().toLowerCase();
                if (phrases.stream().anyMatch(p -> description.contains(p))) {
                    return true;
                }
            }
            if(job.getCompany()!=null && job.getCompany().getPeopleFocused()!=null  && job.getCompany().getPeopleFocused()){
                return true;
            }
        }
        return !including;

    }
}
