package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class HumanAndCustomerManagementAndSalesTechnologyFilter implements JobFilter {

    /**
     * M365 can be just sharepoint and not be a HCM job
     * Maintain accurate information on platforms required for this role, which include CRMs such as
     */
    List<String> phrases =List.of("Genesys","Salesforce","Dynamics"
            ,"d365","Exstream","Power Platform","HRMS","Peoplesoft",
            "HRIS","Kitewheel"," HCM ","Vlocity","Medallia","SFCC");

    public boolean include(Preferences preferences, Job job){
        if(!preferences.isSoftwareSearch()){
            return true;
        }
        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            if (phrases.stream().anyMatch(p -> description.contains(p.toLowerCase()))) {
                System.err.println(this.getClass()+"description->reject: " + job);
                return false;
            }
        }
     return true;

 }
}
