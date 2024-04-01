package com.goodforallcode.jobExtractor.filters.deep.always;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyUtil;

import java.time.LocalDate;
import java.util.List;

public class StartupFilter implements JobFilter {
    /**
     * Exceptions
     * Bonus : Could be a bonus plan for referrals
     * investors: can have investors but still be a PBC
     * funded:      could refer to insurance
     * funding:     could refer to contracts
     * founding:    can only be in title as could refer to founding principle
     * ventures:    can return to internal program like a mentorship joint ventures one
     *
     * examples
     * venture-backed
     */
    List<String> descriptionPhrases =List.of(
            "seed-stage", "y combinator","backed"," early stage",
            " VC","investors","pre-seed","valuation");
    List<String> bothPhrases =List.of("startup","start-up"," start up "
            ,"B corp","Series A","Series B","foundational","scale-up");
    List<String> notPhrases =List.of("public benefit corporation","PBC");
    List<String> companyNames =List.of("Patterned Learning AI","Patterned Learning AI",
            "Patterned Learning Career","minware","Included Health",
            "Storm 3","Storm 4","Storm 5","Storm 6",
            "Nira Energy","Apploi","Convictional","Bramkas",
            "Ascendion","WellSaid Labs","Alma","Maven Clinic","hims & hers","Amberflo.io",
            "AllVoices","Certificial","Rutter","Hazel Health","AIQ (Alpine IQ)","Jerry",
            "Underdog.io","ONE","Apexon","Docugami","Clerkie","Human Interest",
            "CornerUp","Cloudbeds","SandboxAQ","Fitness Matrix Inc","Sight Machine",
            "Offered.ai","SpectrumAi","Numerated");

    List<String> investorBackedCompanyNames =List.of("Avid Technology Professionals","TherapyNotes, LLC");

    List<String> titlePhrases =List.of("founding","Founder","Entrepreneur");

    public boolean include(Preferences preferences, Job job){
        if(companyNames.stream().anyMatch(cn-> CompanyUtil.containsCompanyName(cn,job))){
            System.err.println("startup based on company name ->reject: " + job);
            return false;
        }

        if(investorBackedCompanyNames.stream().anyMatch(cn-> CompanyUtil.containsCompanyName(cn,job))){
            System.err.println("startup based on company name  investor ->reject: " + job);
            return false;
        }

        String title =job.getTitle().toLowerCase();
        if(titlePhrases.stream().anyMatch(t->title.contains(t.toLowerCase()))){
            System.err.println("startup based on title ->reject: " + job);
            return false;
        }
        if(bothPhrases.stream().anyMatch(t->title.contains(t.toLowerCase()))){
            System.err.println("startup based on title both ->reject: " + job);
            return false;

        }
        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            if (notPhrases.stream().anyMatch(p -> description.contains(p.toLowerCase()))) {
                System.err.println("not startup ->include: " + job);
                return true;
            }
            if (bothPhrases.stream().anyMatch(p -> description.contains(p.toLowerCase()))) {
                System.err.println("startup both description ->reject: " + job);
                return false;
            }

            if (descriptionPhrases.stream().anyMatch(p -> description.contains(p.toLowerCase()))) {
                System.err.println("startup description ->reject: " + job);
                return false;
            }
        }
        if(job.getCompany()!=null && job.getCompany().getFoundingYear()!=null){
            LocalDate now=LocalDate.now();
            int yearsPassed=job.getCompany().getFoundingYear()-now.getYear();
            //rignt now data is a year old and it is accounted for here with some wiggle room for the data getting more out of date but at some point this will be wrong
            if (yearsPassed<=2){
                System.err.println("startup company founding ->reject: " + job);
                return false;
            }
        }
        return true;

    }
}
