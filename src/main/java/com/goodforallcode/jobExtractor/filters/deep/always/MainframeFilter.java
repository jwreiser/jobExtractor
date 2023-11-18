package com.goodforallcode.jobExtractor.filters.deep.always;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyNameUtil;

import java.util.List;

public class MainframeFilter implements JobFilter {
    List<String> phrases =List.of("Mainframe","AS400","RPG","z/OS","Adabas");
    List<String> companyNames =List.of("Rocket Software");

    @Override
    public boolean include(Preferences preferences, Job job) {
        if(companyNames.stream().anyMatch(c-> CompanyNameUtil.containsCompanyName(c,job))){
            System.err.println("Mainframe company name ->reject: " + job);
            return false;
        }
        final String title= job.getTitle().toLowerCase();
        if(phrases.stream().anyMatch(p->title.contains(p.toLowerCase()))){
            System.err.println("Mainframe title ->reject: " + job);
            return false;
        }
        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();

            if (phrases.stream().anyMatch(k -> description.contains(k.toLowerCase()))) {
                System.err.println("Mainframe ->reject: " + job);
                return false;
            }
        }
        return true;
    }
}
