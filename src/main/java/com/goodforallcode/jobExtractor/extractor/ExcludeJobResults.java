package com.goodforallcode.jobExtractor.extractor;

import com.goodforallcode.jobExtractor.filters.JobFilter;

import java.util.List;

public class ExcludeJobResults {
    public ExcludeJobResults(boolean includeJob, boolean excludeJob, boolean skipRemainingJobs, List<JobFilter> includeFilters, JobFilter excludeFilter) {
        this.includeJob = includeJob;
        this.excludeJob = excludeJob;
        this.skipRemainingJobs = skipRemainingJobs;
        this.includeFilters = includeFilters;
        this.excludeFilter = excludeFilter;
    }

    boolean includeJob;
    boolean excludeJob;
    boolean skipRemainingJobs;
    List<JobFilter> includeFilters;
    JobFilter excludeFilter;

    public boolean includeJob() {
        return includeJob;
    }


    public boolean excludeJob() {
        return excludeJob;
    }

    public boolean skipRemainingJobs() {
        return skipRemainingJobs;
    }

    public List<JobFilter> includeFilters() {
        return includeFilters;
    }


    public JobFilter excludeFilter() {
        return excludeFilter;
    }


}
