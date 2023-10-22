package com.goodforallcode.jobExtractor.cache;

import com.goodforallcode.jobExtractor.model.Job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class   DefaultJobCache implements JobCache {
    Map<String, List<String>> existingJobs=new HashMap<>();
    public boolean containsJob(Job job){
        boolean containsJob=false;
        List<String>jobTitles=existingJobs.get(job.getCompanyName());
        if(jobTitles!=null && jobTitles.contains(job.getTitle())){
            containsJob=true;
        }
        return containsJob;
    }
    public void addJob(Job job){
        List<String>jobTitles=existingJobs.get(job.getCompanyName());
        if(jobTitles!=null){
            jobTitles.add(job.getTitle());//assuming it is no already in as if in should not be here (except in race conditions)
        }else{
            jobTitles=new ArrayList<>(List.of(job.getTitle()));
            existingJobs.put(job.getCompanyName(),jobTitles);
        }

    }
}
