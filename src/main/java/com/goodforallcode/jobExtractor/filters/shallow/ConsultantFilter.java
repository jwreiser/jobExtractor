package com.goodforallcode.jobExtractor.filters.shallow;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyUtil;

import java.util.List;

public class ConsultantFilter implements JobFilter {
    static List<String>consultantKeywords=List.of("consultant","consulting",
            "consultancy");
    static List<String>consultantCompanyNames=List.of("Curate Partners","Modis","Akkodis"
    ,"Ricardo plc","FullStack Labs","Sierra7","Sierra7, Inc.","Vaco","QuantumBricks",
            "Lorven Technologies Inc.","ZETTALOGIX INC","Sierra Solutions","CGI",
            "Daugherty Business Solutions","World Wide Technology","Qualitest","Cognizant",
            "Solü Technology Partners","Nakupuna Companies","SDI Presence",
            "DMI (Digital Management, LLC)","Next Level Business Services, Inc.",
            "NLB Services");

    static final List<String> industries=List.of("Business Consulting and Services","Consulting");
    @Override
    public boolean include(Preferences preferences, Job job) {
        if(!preferences.isExcludeConsultant()){
            return true;
        }
        if(consultantCompanyNames.stream().anyMatch(cn-> CompanyUtil.containsCompanyName(cn,job))){
            return false;
        }

        if(job.getIndustries()!=null &&  industries.stream().anyMatch(in->job.getIndustries().contains(in))){
            return false;
        }

        final String title =job.getTitle().toLowerCase();
        if(consultantKeywords.stream().anyMatch(k -> title.contains(k.toLowerCase()))){
            return false;
        }

        final String companyName=job.getCompanyName().toLowerCase();
        if(consultantKeywords.stream().anyMatch(k -> companyName.contains(k.toLowerCase()))){
            return false;
        }


        if(consultantKeywords.stream().anyMatch(k -> job.getCompanyName().contains(k.toLowerCase()))){
            return false;
        }

        if(job.getDescription()!=null){
            final String description=job.getDescription().toLowerCase();
            if(consultantKeywords.stream().anyMatch(k -> description.contains(k.toLowerCase()))){
                return false;
            }
            if(consultantCompanyNames.stream().anyMatch(c-> CompanyUtil.descriptionContainsCompanyName(c,job.getDescription()))){
                return false;
            }
        }

        if(job.getCompany()!=null && job.getCompany().getConsulting()!=null && job.getCompany().getConsulting()){
            return false;
        }
        return true;
    }
}
