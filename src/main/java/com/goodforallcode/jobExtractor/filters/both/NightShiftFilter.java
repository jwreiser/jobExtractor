package com.goodforallcode.jobExtractor.filters.both;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.CompanySummary;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class NightShiftFilter implements JobFilter {
    List<String> bothPhrases =List.of("Night Shift");
    @Override
    public boolean include(Preferences preferences, Job job) {
        final String title= job.getTitle().toLowerCase();
        if(bothPhrases.stream().anyMatch(k->title.contains(k.toLowerCase()))){
            System.err.println("Night shift title->reject: "+job);
            return false;
        }
        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            if (bothPhrases.stream().anyMatch(k -> description.contains(k.toLowerCase()))) {
                System.err.println("Night shift description ->reject: " + job);
                return false;
            }

        }
        if(job.getCompany()!=null){
            CompanySummary sum = job.getCompany();
            if(sum.getSoftwareEngineerNightOrWeekendHours()!=null && sum.getSoftwareEngineerNightOrWeekendHours()) {
                System.err.println("summary night or weekend ->reject: " + job);
                return false;
            }
        }
        return true;
    }
}
