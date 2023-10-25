package com.goodforallcode.jobExtractor.filters.include;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class PeopleFocusedFilter implements JobFilter {
    boolean including;

    public PeopleFocusedFilter(boolean including) {
        this.including = including;
    }

    List<String> notPeopleFocusedCompanies =List.of("Maximus","IDR, Inc.");
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
        }
        return !including;

    }
}
