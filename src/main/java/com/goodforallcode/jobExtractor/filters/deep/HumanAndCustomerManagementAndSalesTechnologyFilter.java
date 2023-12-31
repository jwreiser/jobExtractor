package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class HumanAndCustomerManagementAndSalesTechnologyFilter implements JobFilter {

    /**
     * M365 can be just sharepoint and not be a HCM job
     */
    List<String> phrases =List.of("Genesys","CRM","Salesforce","Dynamics"
            ,"d365","Exstream","Power Platform","HRMS","Peoplesoft",
            "HRIS","Kitewheel"," HCM ","Vlocity","Medallia","SFCC");

    public boolean include(Preferences preferences, Job job){

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
