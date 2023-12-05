package com.goodforallcode.jobExtractor.extractor;

import com.goodforallcode.jobExtractor.model.Job;
import lombok.AllArgsConstructor;

import java.util.List;

public record JobResult(List<Job> acceptedJobs,List<Job> rejectedJobs,int totalJobs,int hiddenJobs
        ,int skippedJobs,int cachedJobs,int numPages){
}
