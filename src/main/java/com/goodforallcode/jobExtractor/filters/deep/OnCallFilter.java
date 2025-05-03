
package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.CompanySummary;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyUtil;

import java.util.List;

public class OnCallFilter implements JobFilter {
       /*
        exceptions
        aggressive: aggressive expansion might not indicate work life balance problems
        critical : thinking
        pressure: work well under pressure does not mean bad balance. Seems like trope not sure if it would mean lack of balance
        from Providers who staff the unit 24/7 to our Unit Secretaries and Techs
         */
    List<String> phrases =List.of("on call","on-call","go-live support",
"24/7"," 24-7 ",
           "24x7","rotation","After business hours","After hours",
           "outside of normal business","outside normal business");

    List<String> onCallCompanies =List.of("Oracle","Ancestry","Gremlin","Homecare Homebase"
    );
    List<String> safePhrases =List.of("internal rotation");

    public boolean include(Preferences preferences, Job job){
        if(!preferences.isExcludeOnCall()){
            return true;
        }
        if(onCallCompanies.stream().anyMatch(cn-> CompanyUtil.containsCompanyName(cn,job))){
            System.err.println(this.getClass()+" company name ->reject: " + job);
            return false;
        }
        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            if (safePhrases.stream().anyMatch(p -> description.contains(p))) {
                return true;
            }
            if (phrases.stream().anyMatch(p -> description.contains(p))) {
                System.err.println(this.getClass()+" description ->reject: " + job);
                return false;
            }
        }
        if(job.getCompany()!=null){
            CompanySummary sum = job.getCompany();
            if(sum.getSoftwareEngineerAfterHoursSupport()!=null && sum.getSoftwareEngineerAfterHoursSupport()){
                System.err.println(this.getClass()+"  company summary->reject: " + job);
                return false;
            }
        }
        return true;
    }
}
