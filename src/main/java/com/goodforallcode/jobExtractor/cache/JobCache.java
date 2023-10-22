package com.goodforallcode.jobExtractor.cache;

import com.goodforallcode.jobExtractor.model.Job;

public interface JobCache {
    boolean containsJob(Job job);
    void addJob(Job job);
}
