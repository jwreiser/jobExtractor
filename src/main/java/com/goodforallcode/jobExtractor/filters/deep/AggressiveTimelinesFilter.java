package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.CompanySummary;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyUtil;

import java.util.List;

public class AggressiveTimelinesFilter implements JobFilter {

    /*
        exceptions
        aggressive: aggressive expansion might not indicate work life balance problems
        critical : thinking
        pressure: work well under pressure does not mean bad balance. Seems like trope not sure if it would mean lack of balance
         */
    List<String> phrases =List.of(
            "fast-moving","fast-paced", "fast paced","aggressive timelines",
           "aggressive delivery schedule");
    List<String> fastCompanies =List.of("Clarifai","Digital Technology Partners",
            "Stryker","Clover Health"
    );

    public boolean include(Preferences preferences, Job job){
        if(!preferences.isExcludeAggressiveTimelines()){
            return true;
        }
        if(fastCompanies.stream().anyMatch(cn-> CompanyUtil.containsCompanyName(cn,job))){
            System.err.println(this.getClass()+" company named ->reject: " + job);
            return false;
        }
        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            if (phrases.stream().anyMatch(p -> description.contains(p))) {
                System.err.println(this.getClass()+" description ->reject: " + job);
                return false;
            }
        }
        if(job.getCompany()!=null){
            CompanySummary sum = job.getCompany();
            if(sum.getFastPaced()!=null && !sum.getFastPaced()){
                System.err.println(this.getClass()+" company summary->reject: " + job);
                return false;
            }
        }
        return true;
    }
}
