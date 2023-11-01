package com.goodforallcode.jobExtractor.filters.include;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyNameUtil;

import java.util.List;

public class PeopleFocusedFilter implements JobFilter {
    boolean including;

    public PeopleFocusedFilter(boolean including) {
        this.including = including;
    }

    List<String> notPeopleFocusedCompanies =List.of("Maximus","IDR, Inc.","Marketlab");

    List<String> phrases =List.of("life balance","people first","like family");
    public boolean include(Preferences preferences, Job job){
        if(notPeopleFocusedCompanies.stream().anyMatch(c->job.getCompanyName().equals(c))){
            System.err.println("not people focused -> exclude: " + job);
            return false;
        }
        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            if (phrases.stream().anyMatch(p -> description.contains(p))) {
                System.err.println("people focused -> include: " + job);
                return true;
            }
            if(notPeopleFocusedCompanies.stream().anyMatch(c-> CompanyNameUtil.containsCompanyName(c,job.getDescription()))){
                System.err.println("people focused based on company description ->reject: " + job);
                return false;
            }
        }
        return !including;

    }
}
