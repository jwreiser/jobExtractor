package com.goodforallcode.jobExtractor.filters.deep.always;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class FreelanceFilter implements JobFilter {

    List<String>keywords=List.of("Freelance");
    List<String>companyNames=List.of("CleverTech");

    @Override
    public boolean include(Preferences preferences, Job job) {
        if(companyNames.stream().anyMatch(c->c.equals(job.getCompanyName()))){
            System.err.println("freelance company name ->reject: " + job);
            return false;
        }

        if(job.getDescription()!=null) {
            String text = job.getDescription().toLowerCase();
            if (keywords.stream().anyMatch(k -> text.contains(k.toLowerCase()))) {
                System.err.println("freelance description ->reject: " + job);
                return false;
            }
        }

        return true;
    }

}
