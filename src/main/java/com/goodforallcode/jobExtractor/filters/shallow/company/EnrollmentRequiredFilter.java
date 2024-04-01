package com.goodforallcode.jobExtractor.filters.shallow.company;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyUtil;

import java.util.List;

/**
 * Companies here require you to be in there system and will contact you for jobs you cannot screen
 */
public class EnrollmentRequiredFilter implements JobFilter {

    List<String> companyNames =List.of( "IT Pros");

    @Override
    public boolean include(Preferences preferences, Job job) {

        if(companyNames.stream().anyMatch(cn-> CompanyUtil.containsCompanyName(cn,job))){
            System.err.println("Enrollment required company name ->reject: "+job);
            return false;
        }


        return true;
    }
}
