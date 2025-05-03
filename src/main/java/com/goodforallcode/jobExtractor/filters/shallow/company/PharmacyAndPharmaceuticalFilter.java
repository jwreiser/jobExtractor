package com.goodforallcode.jobExtractor.filters.shallow.company;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyUtil;

import java.util.List;

public class PharmacyAndPharmaceuticalFilter implements JobFilter {
    List<String> companyName =List.of( "CVS Health");
    String industry ="Pharmaceutical Manufacturing";
    List<String> jobTitlePhrases=List.of(
            "Pharmacist","Pharmacy"
    );
    @Override
    public boolean include(Preferences preferences, Job job) {
        String title =job.getTitle().toLowerCase();

        if(jobTitlePhrases.stream().anyMatch(t->title.contains(t.toLowerCase()))){
            System.err.println(this.getClass()+" title->reject: "+job);
            return false;
        }

        if(job.getIndustries()!=null && job.getIndustries(). contains(industry)){
            System.err.println("Pharmaceutical ->reject: " + job);
            return false;
        }

        if(companyName.stream().anyMatch(cn-> CompanyUtil.containsCompanyName(cn,job))){
            System.err.println("Pharmacy ->reject: "+job);
            return false;
        }
        return true;
    }
}
