package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class HumanAndCustomerManagementAndSalesTechnologyFilter implements JobFilter {

    List<String> phrases =List.of("Genesys","CRM","Salesforce","Dynamics"
            ,"d365","Exstream","Power Platform","m365","HRMS","Peoplesoft",
            "HRIS","Kitewheel"," HCM ","Vlocity");

    public boolean include(Preferences preferences, Job job){
        String title = job.getTitle().toLowerCase();
        if (phrases.stream().anyMatch(p -> title.contains(p.toLowerCase()))) {
            System.err.println("Customer management title ->reject: " + job);
            return false;
        }
        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            if (phrases.stream().anyMatch(p -> description.contains(p.toLowerCase()))) {
                System.err.println("Customer management->reject: " + job);
                return false;
            }
        }
     return true;

 }
}
