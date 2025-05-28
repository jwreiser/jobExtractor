package com.goodforallcode.jobExtractor.filters.custom;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class SkillsFilter implements JobFilter {
    @Override
    public boolean include(Preferences preferences, Job job) {
        if(job.getSkills() != null && !job.getSkills().isEmpty()) {
            if(preferences.getSkills()!=null){
                List<String> skills = preferences.getSkills().stream()
                        .map(String::toLowerCase)
                        .toList();

                if(job.getSkills().stream().anyMatch(s->skills.contains(s.toLowerCase()))) {
                    return true;
                }
            }
            return false;
        }

        return true;

    }

}
