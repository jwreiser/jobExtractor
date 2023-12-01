package com.goodforallcode.jobExtractor.cache;

import com.goodforallcode.jobExtractor.model.Job;
import com.mongodb.client.MongoClient;

public interface JobCache {

    boolean containsJob(Job job, MongoClient mongoClient);
    void addJob(Job job,boolean include, MongoClient mongoClient);
    void addRemainingJobs(MongoClient mongoClient);

}
