package com.goodforallcode.jobExtractor.filters.custom;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.StringUtil;

public class MunicipalityFilter implements JobFilter {


    public boolean include(Preferences preferences, Job job) {
        if (job.getFullyRemote() != null && job.getFullyRemote()) {
            return true;
        }
        if (StringUtil.valuePopulated(job.getMunicipality())) {
            String location = job.getMunicipality().toLowerCase();
            if (preferences.getBadLocationPhrases().stream().anyMatch(l -> location.contains(l.toLowerCase()))) {
                return false;
            }

        }


        return true;
    }


}
