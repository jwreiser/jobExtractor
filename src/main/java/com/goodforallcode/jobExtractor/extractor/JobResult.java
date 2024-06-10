package com.goodforallcode.jobExtractor.extractor;

import com.goodforallcode.jobExtractor.model.Job;
import lombok.AllArgsConstructor;

import java.util.List;

public class JobResult{
    List<Job> acceptedJobs;
    List<Job> rejectedJobs;
    List<Job> shallowCachedJobs;
    List<Job> deepCachedJobs;
    int totalJobs;

    public List<Job> getAcceptedJobs() {
        return acceptedJobs;
    }

    public List<Job> getRejectedJobs() {
        return rejectedJobs;
    }

    public List<Job> getShallowCachedJobs() {
        return shallowCachedJobs;
    }

    public List<Job> getDeepCachedJobs() {
        return deepCachedJobs;
    }

    public int getTotalJobs() {
        return totalJobs;
    }

    public int getHiddenJobs() {
        return hiddenJobs;
    }

    public int getSkippedJobs() {
        return skippedJobs;
    }

    public int getNumPages() {
        return numPages;
    }

    int hiddenJobs;
    int skippedJobs;
    int numPages;

    public JobResult(List<Job> acceptedJobs, List<Job> rejectedJobs, List<Job> shallowCachedJobs, List<Job> deepCachedJobs, int totalJobs, int hiddenJobs, int skippedJobs, int numPages) {
        this.acceptedJobs = acceptedJobs;
        this.rejectedJobs = rejectedJobs;
        this.shallowCachedJobs = shallowCachedJobs;
        this.deepCachedJobs = deepCachedJobs;
        this.totalJobs = totalJobs;
        this.hiddenJobs = hiddenJobs;
        this.skippedJobs = skippedJobs;
        this.numPages = numPages;
    }

    public List<Job> acceptedJobs() {
        return acceptedJobs;
    }

    public List<Job> rejectedJobs() {
        return rejectedJobs;
    }

    public List<Job> shallowCachedJobs() {
        return shallowCachedJobs;
    }

    public List<Job> deepCachedJobs() {
        return deepCachedJobs;
    }

    public int totalJobs() {
        return totalJobs;
    }

    public int hiddenJobs() {
        return hiddenJobs;
    }

    public int skippedJobs() {
        return skippedJobs;
    }

    public int numPages() {
        return numPages;
    }
}
