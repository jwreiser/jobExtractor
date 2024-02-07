package com.goodforallcode.jobExtractor.extractor;

import com.goodforallcode.jobExtractor.model.Job;
import lombok.AllArgsConstructor;

import java.util.List;

public record JobResult(List<Job> acceptedJobs,List<Job> rejectedJobs,List<Job> shallowCachedJobs,List<Job> deepCachedJobs,int totalJobs,int hiddenJobs
        ,int skippedJobs,int numPages){
}
