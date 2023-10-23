package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class ERPFilter implements JobFilter {

    List<String> phrases =List.of("Workday"," ERP ","NetSuite","FinancialForce","Certinia",
    "Infor ","Lawson","Kinetic","Syteline");

    public boolean include(Preferences preferences, Job job){
        String title =job.getTitle().toLowerCase();
        if (phrases.stream().anyMatch(p->title.contains(p.toLowerCase()))) {
            System.err.println("ERP title ->reject: " + job);
            return false;
        }

        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            long count =phrases.stream().filter(p -> description.contains(p.toLowerCase())).count();
            if (count>2) {
                System.err.println("ERP description count "+count+" ->reject: " + job);
                return false;
            }
        }

     return true;

 }
}
