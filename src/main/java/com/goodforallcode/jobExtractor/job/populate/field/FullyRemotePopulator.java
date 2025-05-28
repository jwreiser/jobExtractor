package com.goodforallcode.jobExtractor.job.populate.field;

import com.goodforallcode.jobExtractor.filters.custom.LocalFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class FullyRemotePopulator implements FieldPopulator {
     List<String> notRemotePhrases =List.of("local to","Local candidates", "located in a commutable distance",
            "will only be considering candidates within","Preferred location is","will be located in","MUST LIVE IN","Must reside in",
            "position will be based in","Residents only","Must reside"," DC Area","a hybrid work",
            "Primary Location","within commute distance","must be living");

    public void populateField(Job job, Preferences preferences) {

        if (job.getDescription() != null && isRemoteOrUnknown(job)) {
            String description = job.getDescription().toLowerCase();

            if(isRemoteOrUnknown(job) && notRemotePhrases.stream().filter(p ->description.contains(p.toLowerCase())).findAny().isPresent()){
                job.setFullyRemote(false);
            }

            if(job.getFullyRemote()==null && LocalFilter.notLocalPhrases.stream().filter(p ->description.contains(p.toLowerCase())).findAny().isPresent()){
                job.setFullyRemote(true);
            }
        }
    }

    private static boolean isRemoteOrUnknown(Job job) {
        return job.getFullyRemote() == null || job.getFullyRemote();
    }
}
