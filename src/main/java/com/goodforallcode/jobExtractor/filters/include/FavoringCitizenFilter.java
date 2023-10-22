package com.goodforallcode.jobExtractor.filters.include;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class FavoringCitizenFilter implements JobFilter {
    List<String> phrases =List.of("Must be a US Citizen","maintain a security clearance"
            ,"obtain a security clearance"," mbi ","public trust","IRS Clearance");
    public boolean include(Preferences preferences, Job job){
        String description =job.getDescription().toLowerCase();
        if (phrases.stream().anyMatch(p->description.contains(p))) {
            System.err.println("citizen -> include: " + job);
            return true;
        }
        return false;

    }
}
