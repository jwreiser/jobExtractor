package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

public class SourcedFromJobBoardFilter implements JobFilter {
    public boolean include(Preferences preferences, Job job){
        if (job.isSourcedFromJobBoard() && preferences.isSkipJobsSourcedFromExternalJobBoard()) {
            System.err.println("SourcedFromJobBoard ->reject: " + job);
            return false;
        }
        return true;

    }
}
