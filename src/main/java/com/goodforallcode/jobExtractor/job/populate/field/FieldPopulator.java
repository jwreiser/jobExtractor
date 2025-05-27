package com.goodforallcode.jobExtractor.job.populate.field;
import com.goodforallcode.jobExtractor.filters.ExcludeJobFilter;
import com.goodforallcode.jobExtractor.model.Job;

import java.util.List;

public interface FieldPopulator {
    public void populateField(Job job);
}
