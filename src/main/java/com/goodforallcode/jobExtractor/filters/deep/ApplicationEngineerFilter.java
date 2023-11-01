package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class ApplicationEngineerFilter implements JobFilter {
    List<String> notADeveloperKeywords = List.of("electrical","Solidworks", "AutoCAD", "CAD ");

    @Override
    public boolean include(Preferences preferences, Job job) {
        if(!job.getTitle().contains("Application ")){
            return true;
        }

        if(job.getDescription()!=null) {
            String text = job.getDescription().toLowerCase();
            if(preferences.getProgrammingLanguages().stream().anyMatch(p->text.contains(p.toLowerCase()))){
                return true;
            }
            if (notADeveloperKeywords.stream().anyMatch(k->text.contains(k.toLowerCase()))) {
                System.err.println("not a developer application engineer description ->reject: " + job);
                return false;
            }
        }
        return true;
    }
}
