package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class OracleTechFilter implements JobFilter {
    List<String>keywords=List.of(" CPQ ","Oracle Apex","Oracle EBS","Oracle Cloud ERP"
            ,"Oracle ERP","FCCS"," EPM");

    @Override
    public boolean include(Preferences preferences, Job job) {
        String text =job.getDescription().toLowerCase();

        if(keywords.stream().filter(k->text.contains(k.toLowerCase())).count()>1){
            System.err.println("Oracle ->reject: "+job);
            return false;
        }
        return true;
    }
}
