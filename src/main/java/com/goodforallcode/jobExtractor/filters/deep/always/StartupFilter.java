package com.goodforallcode.jobExtractor.filters.deep.always;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;
import java.util.Locale;

public class StartupFilter implements JobFilter {
    /**
     * Exceptions
     * Bonus : Could be a bonus plan for referrals
     * investors: can have investors but still be a PBC
     * funded:      could refer to insurance
     * funding:     could refer to contracts
     * founding:    can only be in title as could refer to founding principle
     * ventures:    can return to internal program like a mentorship joint ventures one
     */
    List<String> phrases =List.of("startup","start-up"
            ,"Series A","Series B",
            "Storm 3","Storm 4","Storm 5","Storm 6",
            "seed-stage", "y combinator","backed"," early stage",
            "B corp"," VC","backed","investors","pre-seed");

    List<String> notPhrases =List.of("public benefit corporation","PBC");
    List<String> companyNamePhrases =List.of("Patterned Learning AI","minware","Included Health",
            "Ascendion","WellSaid Labs","Alma","Maven Clinic","hims & hers");
    List<String> titlePhrases =List.of("founding");

    public boolean include(Preferences preferences, Job job){
        if(companyNamePhrases.stream().anyMatch(c->c.equals(job.getCompanyName()))){
            System.err.println("startup based on company ->include: " + job);
            return false;

        }
        String title =job.getTitle().toLowerCase();
        if(titlePhrases.stream().anyMatch(t->title.contains(t.toLowerCase()))){
            System.err.println("startup based on title ->include: " + job);
            return false;

        }
        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            if (notPhrases.stream().anyMatch(p -> description.contains(p))) {
                System.err.println("not startup ->include: " + job);
                return true;
            }
            if (phrases.stream().anyMatch(p -> description.contains(p))) {
                System.err.println("startup ->reject: " + job);
                return false;
            }
        }
        return true;

    }
}
