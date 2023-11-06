package com.goodforallcode.jobExtractor.filters.both;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyNameUtil;

import java.util.List;

public class ResearcherFilter implements JobFilter {
    /**
     * Example
     * conferences: other jobs go to conferences
     */
    List<String> descriptionPhrases =List.of(" patent","AAAI", "IAAI",
            "IJCAI", " HRI ", "ICAPS", "AAMAS", "ICRA", "IROS", "ICLR", "ICML",
            "NeurIPs", "CORL", " ITSC","publications ","journals ");
    List<String> bothPhrases =List.of("researcher","PHD ","R&D ");
    List<String> titlePhrases =List.of("research");
    @Override
    public boolean include(Preferences preferences, Job job) {
        final String title=job.getTitle().toLowerCase();
        if(bothPhrases.stream().anyMatch(k->title.contains(k.toLowerCase()))){
            System.err.println("researcher both title ->reject: "+job);
            return false;
        }
        if(titlePhrases.stream().anyMatch(k->title.contains(k.toLowerCase()))){
            System.err.println("researcher title ->reject: "+job);
            return false;
        }
        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            if (bothPhrases.stream().anyMatch(k -> description.contains(k.toLowerCase()))) {
                System.err.println("researcher both description ->reject: " + job);
                return false;
            }
            if (descriptionPhrases.stream().anyMatch(k -> description.contains(k.toLowerCase()))) {
                System.err.println("researcher description ->reject: " + job);
                return false;
            }
        }
        return true;
    }
}
