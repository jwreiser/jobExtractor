package com.goodforallcode.jobExtractor.filters.both;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyUtil;

import java.util.List;

public class GamingFilter implements JobFilter {
    /**
     * Exceptions
     * Game:    Game changing
     * Games/gaming: tickets to games as benefit or in the breakroom
     */
    List<String> phrases = List.of("unreal","Xbox","PlayStation","gameplay");
    List<String> titlePhrases = List.of("game", "games","Engine ");
    List<String> companyNames = List.of("Second Dinner","Sleeper");

    @Override
    public boolean include(Preferences preferences, Job job) {
        if(job.getCompanyName()!=null && companyNames.stream().anyMatch(cn-> CompanyUtil.containsCompanyName(cn,job))){
            return false;
        }
        final String title = job.getTitle().toLowerCase();
        if (titlePhrases.stream().anyMatch(k -> title.contains(k.toLowerCase()))) {
            return false;
        }
        if (phrases.stream().anyMatch(k -> title.contains(k.toLowerCase()))) {
            return false;
        }
        if (job.getIndustries()!=null && job.getIndustries().contains("Computer Games")) {
            return false;
        }

        if (job.getDescription() != null) {
            final String description = job.getDescription().toLowerCase();
            if (phrases.stream().anyMatch(k -> description.contains(k.toLowerCase()))) {
                return false;
            }
            if(companyNames.stream().anyMatch(c-> CompanyUtil.descriptionContainsCompanyName(c,job.getDescription()))){
                return false;
            }
        }


        return true;
    }
}
