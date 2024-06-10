package com.goodforallcode.jobExtractor.util;

import com.goodforallcode.jobExtractor.service.JobCacheKafkaService;
import com.goodforallcode.jobExtractor.model.CompanySummary;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.service.SummaryCacheKafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CacheUtil {
    @Autowired
    private JobCacheKafkaService jobCacheKafkaService;
    @Autowired
    private SummaryCacheKafkaService summaryCacheKafkaService;

    private static String getHost(){
        return "cache";
    }
    private static String getPort(){
        return "5088";
    }

    public  void addJobToCache(Job job, boolean include) {
        jobCacheKafkaService.sendJobInsert(job,include);
    }

    public void addRemainingJobs() {
        jobCacheKafkaService.sendJobInsert(null,null);
    }

    public  void addRemainingSummaries() {
        summaryCacheKafkaService.sendSummaryInsert(null,true);
    }

    public  void addSummary(CompanySummary summary) {
        summaryCacheKafkaService.sendSummaryInsert(summary,false);
    }

    public boolean containsJob(Job job,boolean description) {
        if(description){
            return RESTUtil.callUrlBoolean("http://"+getHost()+":"+getPort()+"/contains/description/with", job);
        }else {
            return RESTUtil.callUrlBoolean("http://"+getHost()+":"+getPort()+"/contains/description/none", job);
        }

    }


    public static  CompanySummary getCompanySummary(Job job) {
        return RESTUtil.callUrl("http://"+getHost()+":"+getPort()+"/summary/get",job);
    }
}
