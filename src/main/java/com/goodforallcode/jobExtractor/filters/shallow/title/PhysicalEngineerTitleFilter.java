package com.goodforallcode.jobExtractor.filters.shallow.title;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

/**
 * These are titles that are often used in fields like construction
 * Some like "Design engineer" might be used elsewhere
 */
public class PhysicalEngineerTitleFilter implements JobFilter {
    List<String> jobTitlePhrases=List.of(
            "Service Engineer","Project developer","Civil Engineer",
            "Design engineer","Project Engineer","field services","field service",
            "field engineer","Applications Engineer", " OEM ","Autodesk"," CAD "
            );
    List<String> notPhysicalJobTitlePhrases=List.of(
            "Business Applications"
    );

    @Override
    public boolean include(Preferences preferences, Job job) {
        String title =job.getTitle().toLowerCase();
        if(notPhysicalJobTitlePhrases.stream().anyMatch(t->title.contains(t.toLowerCase()))){
            return true;
        }

        if(jobTitlePhrases.stream().anyMatch(t->title.contains(t.toLowerCase()))){
            System.err.println("Physical Engineer job title->reject: "+job);
            return false;
        }
        return true;
    }


}
