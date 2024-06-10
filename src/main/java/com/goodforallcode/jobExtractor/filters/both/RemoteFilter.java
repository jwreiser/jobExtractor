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
"week onsite","a hybrid position","(Hybrid)","(Hybrid role)","in office days per ",
    "(Onsite / Hybrid)","is not remote,","is not remote ","is not remote.","week onsite","onsite in",
            "is based in ","(On-site)","(Onsite)");

    List<String> notRemoteEndsWith =List.of("- Hybrid","-Hybrid","- Onsite","-Onsite",
            ": Hybrid",":Hybrid",": Onsite",":Onsite","- Onsite/Hybrid","-Onsite/Hybrid","(Onsite",
            "- ONSITE HYBRID");
    List<String> notRemoteStartsWith =List.of("Hybrid:","Hybrid-","Onsite:","Onsite-");

    List<String>remotePhrases=List.of("100% remote","Open for remote","remote or hybrid","WFH","Work From Home"
    ,"remotely within the U.S","remotely within the US","remote options","remote possible"
            ,"applications for remote work may be considered","Fully Remote","full and/or partial remote"
    ,"full or partial remote");
    @Override
    public boolean include(Preferences preferences, Job job) {
        if(!preferences.isExcludeNonRemote()){
            return true;
        }

        final String title=job.getTitle().toLowerCase();
        if(job.getRemote()!=null && !job.getRemote()){
            return false;
        }
        if(isTitleRemote(title)!=null ){
            return isTitleRemote(title);
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

    /**
     * This needs to return null if we have not found anything in the title that
     * indicates remoteness so that we can have the calling function know that it can
     * return true when the function returns true and that true is not being used as the
     * default state
     * @param titleLowerCase
     * @return
     */
    public Boolean isTitleRemote(String titleLowerCase){
        if (remotePhrases.stream().anyMatch(k->titleLowerCase.contains(k.toLowerCase()))){
            return true;
        }

        if (notRemotePhrases.stream().anyMatch(k->titleLowerCase.contains(k.toLowerCase()))){
            System.err.println("Not remote title->reject: "+titleLowerCase);
            return false;
        }

        if (notRemoteEndsWith.stream().anyMatch(k->titleLowerCase.endsWith(k.toLowerCase()))){
            System.err.println("Not remote title ends with->reject: "+titleLowerCase);
            return false;
        }

        if (notRemoteStartsWith.stream().anyMatch(k->titleLowerCase.startsWith(k.toLowerCase()))){
            System.err.println("Not remote title starts with->reject: "+titleLowerCase);
            return false;
        }
        return null;
    }
}
