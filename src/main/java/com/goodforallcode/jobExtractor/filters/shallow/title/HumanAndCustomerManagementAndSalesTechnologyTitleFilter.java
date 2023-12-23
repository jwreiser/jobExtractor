package com.goodforallcode.jobExtractor.filters.shallow.title;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class HumanAndCustomerManagementAndSalesTechnologyTitleFilter implements JobFilter {

    /**
     * M365 can be just sharepoint and not be a HCM job
     */
    List<String> phrases =List.of("Genesys","CRM","Salesforce","Dynamics"
            ,"d365","Exstream","Power Platform","HRMS","Peoplesoft",
            "HRIS","Kitewheel"," HCM ","Vlocity","Medallia","SFCC");

    public boolean include(Preferences preferences, Job job){
        String title = job.getTitle().toLowerCase();
        if (phrases.stream().anyMatch(p -> title.contains(p.toLowerCase()))) {
            System.err.println("Customer management title ->reject: " + job);
            return false;
        }
     return true;

 }
}
