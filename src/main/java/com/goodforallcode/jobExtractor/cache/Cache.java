package com.goodforallcode.jobExtractor.cache;

import com.goodforallcode.jobExtractor.model.CompanySummary;
import com.goodforallcode.jobExtractor.model.Job;
import com.mongodb.client.MongoClient;

public interface Cache {

    boolean containsJob(Job job, MongoClient mongoClient);
    boolean containsJobNoDescription(Job job, MongoClient mongoClient);
    void addJob(Job job,boolean include, MongoClient mongoClient);
    void addRemainingJobs(MongoClient mongoClient);

    //company summary
    CompanySummary getCompanySummary(Job job,MongoClient mongoClient);
    void addCompanySummary(CompanySummary summary, Job job,MongoClient mongoClient);
    void addRemainingCompanySummaries(MongoClient mongoClient);
}
