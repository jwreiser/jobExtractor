package com.goodforallcode.jobExtractor.job.populate.field;
import com.goodforallcode.jobExtractor.filters.ExcludeJobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class PartTimePopulator implements FieldPopulator{

    /**
     * Exceptions
     * Bonus : Could be a bonus plan for referrals
     * investors: can have investors but still be a PBC
     * funded:      could refer to insurance
     * funding:     could refer to contracts
     * ventures:    can return to internal program like a mentorship joint ventures one
     *evaluations
     * examples
     * venture-backed
     */
    public void populateField(Job job, Preferences preferences) {

                ExcludeJobFilter filter = ExcludeJobFilter.build("PartTime")
                        .titleAndDescriptionPhrases(List.of("part time","part-time"));

                if (filter.exclude(job) != null) {
                    job.setPartTime(true);
                }
            }



    }

