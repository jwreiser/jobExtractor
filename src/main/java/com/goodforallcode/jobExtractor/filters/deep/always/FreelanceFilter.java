package com.goodforallcode.jobExtractor.filters.deep.always;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyUtil;

import java.util.List;

public class FreelanceFilter implements JobFilter {

    List<String>keywords=List.of("Freelance");
    List<String>companyNames=List.of("CleverTech");

    @Override
    public boolean include(Preferences preferences, Job job) {
        if(companyNames.stream().anyMatch(cn-> CompanyUtil.containsCompanyName(cn,job))){
            System.err.println("freelance company name ->reject: " + job);
            return false;
        }

        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            if (keywords.stream().anyMatch(k -> description.contains(k.toLowerCase()))) {
                System.err.println("freelance description ->reject: " + job);
                return false;
            }
            if(companyNames.stream().anyMatch(c-> CompanyUtil.descriptionContainsCompanyName(c,job.getDescription()))){
                System.err.println("freelance based on company description ->reject: " + job);
                return false;
            }
        }

        return true;
    }

}
