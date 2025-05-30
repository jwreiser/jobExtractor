package com.goodforallcode.jobExtractor.job.populate.field;
import com.goodforallcode.jobExtractor.filters.ExcludeJobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.ArrayList;
import java.util.List;

public class TimeZonePopulator implements FieldPopulator{
    public void populateField(Job job, Preferences preferences) {
            List<ExcludeJobFilter> filters=new ArrayList<>();
            filters.add(ExcludeJobFilter.build("Pacific")
                    .descriptionPhrases(List.of(" PST ", " PDT ", " Pacific ", "overlap with 9am-5pm PST"))
            );

        filters.add(ExcludeJobFilter.build("Mountain")
                .descriptionPhrases(List.of(" MST ", " MDT ", "overlap with 9am-5pm MST","Mountain Time"))
        );

        filters.add(ExcludeJobFilter.build("Eastern")
                .descriptionPhrases(List.of(" EST ", " EDT ", "overlap with 9am-5pm EST"))
        );

        filters.add(ExcludeJobFilter.build("Central")
                .descriptionPhrases(List.of(" CST ", " CDT ", "overlap with 9am-5pm CST"))
        );


        for(ExcludeJobFilter filter: filters) {
                if (filter.exclude(job) != null) {
                    job.setTimeZone(filter.getName());
                    break;
                }
            }
        }



}
