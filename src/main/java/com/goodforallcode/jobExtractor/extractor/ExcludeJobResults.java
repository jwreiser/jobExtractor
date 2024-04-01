package com.goodforallcode.jobExtractor.extractor;

import com.goodforallcode.jobExtractor.filters.JobFilter;

import java.util.List;

public record ExcludeJobResults(boolean includeJob, boolean excludeJob
        , boolean skipRemainingJobs, List<JobFilter> includeFilters, JobFilter excludeFilter) {

}
