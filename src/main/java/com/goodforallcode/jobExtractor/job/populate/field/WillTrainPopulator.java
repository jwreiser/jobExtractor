package com.goodforallcode.jobExtractor.job.populate.field;

import com.goodforallcode.jobExtractor.filters.ExcludeJobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class WillTrainPopulator implements FieldPopulator {

    public void populateField(Job job, Preferences preferences) {

        ExcludeJobFilter filter = ExcludeJobFilter.build("WillTrain")
                .titleAndDescriptionPhrases(List.of("will train", "willing to train"))
                .titlePhrases(List.of(" train"))
                .safeTitlePhrases(List.of("trainer"));

        if (filter.exclude(job) != null) {
            job.setWillTrain(true);
        }
    }


}

