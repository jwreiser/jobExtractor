package com.goodforallcode.jobExtractor.filters.both;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.RegexUtil;

import java.util.List;
import java.util.Optional;

import static com.goodforallcode.jobExtractor.util.RegexUtil.getUntilNextBoundary;
import static com.goodforallcode.jobExtractor.util.RegexUtil.isPresentAndImportant;

public class UnixFilter implements JobFilter {
    List<String> bothPhrases =List.of("Unix","OpenShift","RHEL","Linux");
    List<String> descriptionPhrases =List.of("Red Hat JBoss", "Red Hat Fuse");

    @Override
    public boolean include(Preferences preferences, Job job) {
        final String title= job.getTitle().toLowerCase();
        if(bothPhrases.stream().anyMatch(p->title.contains(p.toLowerCase()))){
            System.err.println("Unix title ->reject: " + job);
            return false;
        }
        if(job.getDescription()!=null){
            final String descriptionLower= job.getDescription().toLowerCase();
            if (!descriptionInclude(descriptionLower)) {
                return false;
            }
        }
        return true;
    }

    public boolean descriptionInclude(String descriptionLower) {
        if (bothPhrases.stream().anyMatch(p ->
                isPresentAndImportant(descriptionLower, p.toLowerCase()+getUntilNextBoundary(), false))) {
            System.err.println("Unix description both ->reject");
            return false;
        }
        if (descriptionPhrases.stream().anyMatch(p ->
                isPresentAndImportant(descriptionLower, p.toLowerCase()+getUntilNextBoundary(), false))) {
            System.err.println("Unix description ->reject");
            return false;
        }

        return true;
    }
}

