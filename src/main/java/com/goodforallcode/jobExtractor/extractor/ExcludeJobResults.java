package com.goodforallcode.jobExtractor.extractor;

import com.goodforallcode.jobExtractor.filters.JobFilter;

public record ExcludeJobResults(boolean includeJob, boolean excludeJob
        , boolean skipRemainingJobs, JobFilter includeFilter, JobFilter excludeFilter) {

}
