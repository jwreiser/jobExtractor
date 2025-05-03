package com.goodforallcode.jobExtractor.filters.shallow.title;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class BilingualTitleFilter implements JobFilter {
    List<String> jobTitlePhrases=List.of("Spanish","German","French","Italian","Portuguese","Russian","Japanese",
            "Chinese","Korean","Arabic",
            "Mandarin","Cantonese","Bilingual","Multilingual"
            );
    @Override
    public boolean include(Preferences preferences, Job job) {
        String title =job.getTitle().toLowerCase();

        if(jobTitlePhrases.stream().anyMatch(t->title.contains(t.toLowerCase()))){
            System.err.println(this.getClass()+" title->include: "+job);
            return false;
        }
        return true;
    }


}
