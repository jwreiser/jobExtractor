package com.goodforallcode.jobExtractor.filters.shallow;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyUtil;

import java.util.List;

public class ConsultantFilter implements JobFilter {
    /*exceptions
    planning consultants
    consulting with customers
     */
    static List<String> allKeywords =List.of( "consultancy");
    static List<String>titleConsultantKeywords=List.of("consulting","consultant");
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
            System.err.println("Consultant company->reject: "+job);
            return false;
        }

        if(job.getIndustries()!=null &&  industries.stream().anyMatch(in->job.getIndustries().contains(in))){
            System.err.println("Consultant industry ->reject: "+job);
            return false;
        }

        final String title =job.getTitle().toLowerCase();
        if(allKeywords.stream().anyMatch(k -> title.contains(k.toLowerCase()))){
            System.err.println("Consultant title->reject: "+job);
            return false;
        }
        if(titleConsultantKeywords.stream().anyMatch(k -> title.contains(k.toLowerCase()))){
            System.err.println("Consultant title->reject: "+job);
            return false;
        }

        final String companyName=job.getCompanyName().toLowerCase();
        if(allKeywords.stream().anyMatch(k -> companyName.contains(k.toLowerCase()))){
            System.err.println("Consultant company name contains ->reject: "+job);
            return false;
        }


        if(allKeywords.stream().anyMatch(k -> job.getCompanyName().contains(k.toLowerCase()))){
            System.err.println("Consultant companyName->reject: "+job);
            return false;
        }

        if(job.getDescription()!=null){
            final String description=job.getDescription().toLowerCase();
            if(allKeywords.stream().anyMatch(k -> description.contains(k.toLowerCase()))){
                System.err.println("Consultant description ->reject: "+job);
                return false;
            }
            if(consultantCompanyNames.stream().anyMatch(c-> CompanyUtil.descriptionContainsCompanyName(c,job.getDescription()))){
                System.err.println("consultant based on company description ->reject: " + job);
                return false;
            }
        }

        if(job.getCompany()!=null && job.getCompany().getConsulting()!=null && job.getCompany().getConsulting()){
            System.err.println("consultant based on company summary ->reject: " + job);
            return false;
        }
        return true;
    }
}
