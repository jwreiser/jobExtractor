package com.goodforallcode.jobExtractor.job.populate.field;

import com.goodforallcode.jobExtractor.filters.ExcludeJobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

/**
 *  Exceptions
 *  location: compensation is based on various factors including but not limited to job location,
 *  hybrid: can refer to a benefit of hybrid
 *  relocation:     can refer to relocation bonus on a possibly remote job
 */
public class FullyRemotePopulator implements FieldPopulator {

    List<String> notRemoteDescriptionPhrases =List.of("local to","Local candidates", "located in a commutable distance",
            "will only be considering candidates within","Preferred location is","will be located in","MUST LIVE IN","Must reside in",
            "position will be based in","Residents only","Must reside"," DC Area","a hybrid work",
            "Primary Location","within commute distance","must be living","seeking an onsite");

    List<String> notRemoteTitlePhrases =List.of("Hybrid","Onsite","On-site","On Site","In-office","In Office","In person","In-Person",
            "Remote/Hybrid","Remote/Onsite","Remote/On-Site","Remote/In-Office","Remote/In Office",
            "Remote/In Person","Remote/In-Person");

    List<String> notRemoteTitleAndDescriptionPhrases =List.of("partial remote", "temporary remote",
            "Remote 20 hours per week", "Mostly Remote", "DAYS/WK ON SITE", "days onsite",
            "Must be able to relocate", "one day of remote work", "Partial WFH",
            "Remote till pandemic", "Remote til pandemic", "able to travel", "Future onsite work is required",
            "week onsite", "a hybrid position", "(Hybrid)", "(Hybrid role)", "in office days per ",
            "(Onsite / Hybrid)", "is not remote,", "is not remote ", "is not remote.", "week onsite", "onsite in",
            "is based in ");

    List<String> remotePhrases =List.of("applications for remote work may be considered",
            "Remote in United States","Remote in USA","remote within United States","remote from within United States","remote from within USA",
            "reside in United States","reside in the USA","Remote within USA","100% remote", "Open for remote", "remote or hybrid", "WFH", "Work From Home"
            , "remotely within the U.S", "remotely within the US", "remote options", "remote possible"
            , "applications for remote work may be considered", "Fully Remote", "full and/or partial remote"
            , "full or partial remote");


    public void populateField(Job job, Preferences preferences) {
        String title = job.getTitle() != null ? job.getTitle().toLowerCase() : "";
        if(isRemoteOrUnknown(job) && notRemoteTitlePhrases.stream().filter(p ->title.contains(p.toLowerCase())).findAny().isPresent()){
            job.setFullyRemote(false);
        }

        if(isRemoteOrUnknown(job) && notRemoteTitleAndDescriptionPhrases.stream().filter(p ->title.contains(p.toLowerCase())).findAny().isPresent()){
            job.setFullyRemote(false);
        }

        if (job.getDescription() != null && isRemoteOrUnknown(job)) {
            String description = job.getDescription().toLowerCase();

            if(isRemoteOrUnknown(job) && notRemoteDescriptionPhrases.stream().filter(p ->description.contains(p.toLowerCase())).findAny().isPresent()){
                job.setFullyRemote(false);
            }
            if(isRemoteOrUnknown(job) && notRemoteTitleAndDescriptionPhrases.stream().filter(p ->description.contains(p.toLowerCase())).findAny().isPresent()){
                job.setFullyRemote(false);
            }

            if(job.getFullyRemote()==null && remotePhrases.stream().filter(p ->description.contains(p.toLowerCase())).findAny().isPresent()){
                job.setFullyRemote(true);
            }
        }
    }

    private static boolean isRemoteOrUnknown(Job job) {
        return job.getFullyRemote() == null || job.getFullyRemote();
    }
}
