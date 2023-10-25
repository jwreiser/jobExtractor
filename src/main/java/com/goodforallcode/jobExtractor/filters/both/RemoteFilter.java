package com.goodforallcode.jobExtractor.filters.both;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class RemoteFilter implements JobFilter {
    /**
     *  Exceptions
     *  location: compensation is based on various factors including but not limited to job location,
     *  hybrid: can refer to a benefit of hybrid
     *  relocation:     can refer to relocation bonus on a possibly remote job
     */
    List<String> notRemotePhrases =List.of("partial remote","temporary remote",
            "Remote 20 hours per week","Mostly Remote","DAYS/WK ON SITE","days onsite",
            "Must be able to relocate","one day of remote work","Partial WFH",
    "Remote till pandemic","Remote til pandemic","able to travel","Future onsite work is required",
"week onsite","one day of remote work");
    List<String>remotePhrases=List.of("100% remote","Open for remote","remote or hybrid","WFH","Work From Home"
    ,"remotely within the U.S","remotely within the US","remote options","remote possible"
            ,"applications for remote work may be considered","Fully Remote","full and/or partial remote"
    ,"full or partial remote");
    List<String>titlePhrases=List.of("(Hybrid))");
    @Override
    public boolean include(Preferences preferences, Job job) {
        final String title=job.getTitle().toLowerCase();
        if (remotePhrases.stream().anyMatch(k->title.contains(k.toLowerCase()))){
            return true;
        }
        if (titlePhrases.stream().anyMatch(k->title.contains(k.toLowerCase()))){
            System.err.println("Not remote title ->reject: "+job);
            return false;
        }

        if (notRemotePhrases.stream().anyMatch(k->title.contains(k.toLowerCase()))){
            System.err.println("Not remote->reject: "+job);
            return false;
        }

        if(job.getDescription()!=null) {
            final String description = job.getDescription().toLowerCase();
            if (remotePhrases.stream().anyMatch(k -> description.contains(k.toLowerCase()))) {
                return true;
            }
            if (notRemotePhrases.stream().anyMatch(k -> description.contains(k.toLowerCase()))) {
                System.err.println("Not remote description->reject: " + job);
                return false;
            }
        }
        return true;
    }

}
