package com.goodforallcode.jobExtractor.filters.both;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class LocalFilter implements JobFilter {
    List<String> phrases =List.of("local to","Local candidates"
    ,"will only be considering candidates within","Preferred location is","will be located in",
"position will be based in","Residents only",
            "Primary Location","within commute distance","must be living");

    List<String> notLocalPhrases =List.of("applications for remote work may be considered");
    public boolean include(Preferences preferences, Job job) {
        final String title=job.getTitle().toLowerCase();

        if (phrases.stream().anyMatch(k->title.contains(k.toLowerCase()))){
            if(!preferences.getLocationPhrases().stream().anyMatch(p->title.contains(" "+p+" ")||title.contains(p+",")||title.contains(","+p))) {
                System.err.println("local title->reject: " + job);
                return false;
            }
        }

        if(job.getDescription()!=null) {
            final String description = job.getDescription().toLowerCase();
            if(notLocalPhrases.stream().anyMatch(p->description.contains(p.toLowerCase()))){
                System.err.println("local  description->include: " + job);
                return true;
            }
            if (phrases.stream().anyMatch(k -> description.contains(k.toLowerCase()))) {
                if(!preferences.getLocationPhrases().stream().anyMatch(p->description.contains(" "+p+" ")||description.contains(p+",")||description.contains(","+p))) {
                    System.err.println("Not local  description->reject: " + job);
                    return false;
                }

            }
        }
        return true;
    }

}
