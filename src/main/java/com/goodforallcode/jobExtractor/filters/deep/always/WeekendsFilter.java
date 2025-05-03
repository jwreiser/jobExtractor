package com.goodforallcode.jobExtractor.filters.deep.always;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.CompanySummary;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyUtil;

import java.util.List;

public class WeekendsFilter implements JobFilter {
    List<String> phrases =List.of("weekends");

    public boolean include(Preferences preferences, Job job){
        if(!preferences.isExcludeWeekends()){
            return true;
        }

        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            if (phrases.stream().anyMatch(p -> description.contains(p))) {
                System.err.println(this.getClass()+" description ->reject: " + job);
                return false;
            }
        }
        return true;

    }
}
