package com.goodforallcode.jobExtractor.filters.deep.always;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class DevSecOpsFilter implements JobFilter {
    List<String>keywords=List.of("network","security","installation","VMware","Servers","Sccm","administration");

    @Override
    public boolean include(Preferences preferences, Job job) {
        String text =job.getDescription().toLowerCase();
        long count=keywords.stream().filter(k->text.contains(k.toLowerCase())).count();
        if(count>3){
            System.err.println("DevSecOps "+count+" ->reject: "+job);
            return false;
        }
        return true;
    }
}
