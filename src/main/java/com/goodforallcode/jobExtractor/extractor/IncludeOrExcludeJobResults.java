package com.goodforallcode.jobExtractor.extractor;

import com.goodforallcode.jobExtractor.filters.ExcludeJobFilter;
import com.goodforallcode.jobExtractor.filters.IncludeOrSkipJobFilter;

import java.util.List;
import java.util.Optional;

public record IncludeOrExcludeJobResults(boolean includeJob, boolean excludeJob
        , boolean skipRemainingJobs, List<IncludeOrSkipJobFilter> includeFilters, Optional<ExcludeJobFilter> excludeFilter) {

}
