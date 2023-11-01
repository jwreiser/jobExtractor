package com.goodforallcode.jobExtractor.filters.both;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

/**
 * this does not look to see if you have the level contained in a title
 * This instead looks for indications of the job being a senior level job
 */
public class NotSeniorFilter implements JobFilter {
    /**
     * Exceptions
     * Coaches: Health Coaches(Benefit)
     * expert: recruiter may have expertise and seems hyperbolic at times
     * principal can only be in title because it could be reports to
     * specialist also may refer to recruiter
     * subject matter expert: seems to happen in non senior roles too
     */
    List<String> descriptionPhrases =List.of("Completes product technical design",
            "produce software designs","subordinate",
            "Advanced knowledge of","lead project teams");

    List<String> notDescriptionPhrases =List.of("Intermediate to advanced knowledge of");


    List<String> titleOnlyPhrases =List.of( "specialist","sr ","sr.", "senior");

    @Override
    public boolean include(Preferences preferences, Job job) {
        if(!preferences.isExcludeSenior()){
            return true;
        }
        String title =job.getTitle().toLowerCase();

        if(titleOnlyPhrases.stream().anyMatch(k->title.contains(k.toLowerCase()))){
            System.err.println("Senior title only ->reject: "+job);
            return false;
        }

        if(job.getDescription()!=null) {
            String description =job.getDescription().toLowerCase();

            if(notDescriptionPhrases.stream().anyMatch(k->description.contains(k.toLowerCase()))){
                return true;
            }
            if(descriptionPhrases.stream().anyMatch(k->description.contains(k.toLowerCase()))){
                System.err.println("Senior description ->reject: "+job);
                return false;
            }

        }

        return true;
    }
}
