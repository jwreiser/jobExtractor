package com.goodforallcode.jobExtractor.filters.shallow;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyNameUtil;

import java.util.List;

public class ConsultantFilter implements JobFilter {
    static List<String>consultantKeywords=List.of("consultant","consulting",
            "consultancy");
    static List<String>consultantCompanyNames=List.of("Curate Partners","Modis","Akkodis"
    ,"Ricardo plc","FullStack Labs","Sierra7","Sierra7, Inc.","Vaco","QuantumBricks",
            "Lorven Technologies Inc.","ZETTALOGIX INC","Sierra Solutions","CGI",
            "Daugherty Business Solutions","World Wide Technology","Qualitest",
            "Solü Technology Partners","Nakupuna Companies","SDI Presence",
            "DMI (Digital Management, LLC)","Next Level Business Services, Inc.",
            "NLB Services");

    static final List<String> industries=List.of("Business Consulting and Services");
    @Override
    public boolean include(Preferences preferences, Job job) {
        if(!preferences.isExcludeConsultant()){
            return true;
        }
        if(consultantCompanyNames.stream().anyMatch(cn-> CompanyNameUtil.containsCompanyName(cn,job))){
            System.err.println("Consultant company->reject: "+job);
            return false;
        }

        if(job.getIndustry()!=null &&  industries.stream().anyMatch(in->in.equals(job.getIndustry()))){
            System.err.println("Consultant industry ->reject: "+job);
            return false;
        }

        final String title =job.getTitle().toLowerCase();
        if(consultantKeywords.stream().anyMatch(k -> title.contains(k.toLowerCase()))){
            System.err.println("Consultant title->reject: "+job);
            return false;
        }

        final String companyName=job.getCompanyName().toLowerCase();
        if(consultantKeywords.stream().anyMatch(k -> companyName.contains(k.toLowerCase()))){
            System.err.println("Consultant company name contains ->reject: "+job);
            return false;
        }


        if(consultantKeywords.stream().anyMatch(k -> job.getCompanyName().contains(k.toLowerCase()))){
            System.err.println("Consultant companyName->reject: "+job);
            return false;
        }

        if(job.getDescription()!=null){
            final String description=job.getDescription().toLowerCase();
            if(consultantKeywords.stream().anyMatch(k -> description.contains(k.toLowerCase()))){
                System.err.println("Consultant description ->reject: "+job);
                return false;
            }
            if(consultantCompanyNames.stream().anyMatch(c-> CompanyNameUtil.descriptionContainsCompanyName(c,job.getDescription()))){
                System.err.println("consultant based on company description ->reject: " + job);
                return false;
            }
        }
        return true;
    }
}
