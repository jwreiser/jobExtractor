package com.goodforallcode.jobExtractor.filters.shallow.company;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyUtil;

import java.util.List;

public class FossilFuelsFilter implements JobFilter {
    List<String> companyNames =List.of( "Pride International");

    List<String> industries =List.of("Oil and Gas");

    List<String> notFossilFuelPhrases =List.of("renewable","solar","wind",
            "nuclear",
            "mills","windmill","geothermal", "geo","offgrid","turbine","sustainable");

    @Override
    public boolean include(Preferences preferences, Job job) {
        if(job.getIndustries()!=null && job.getDescription()!=null && industries.stream().anyMatch(i->job.getIndustries(). contains(i))){
            final String description=job.getDescription().toLowerCase();
            if(notFossilFuelPhrases.stream().noneMatch(p->description.contains(p))) {
                System.err.println("Fossil Fuel company industry ->reject: " + job);
            }
            return false;
        }


        if(companyNames.stream().anyMatch(cn-> CompanyUtil.containsCompanyName(cn,job))){
            System.err.println("Finance company name ->reject: "+job);
            return false;
        }


        return true;
    }
}
