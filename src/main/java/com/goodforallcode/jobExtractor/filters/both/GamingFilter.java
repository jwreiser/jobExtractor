package com.goodforallcode.jobExtractor.filters.both;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class GamingFilter implements JobFilter {
    /**
     * Exceptions
     * Game:    Game changing
     * Ganes: tickets to games as benefit
     */
    List<String> phrases = List.of("gaming", "unreal","Xbox","PlayStation","gameplay");
    List<String> titlePhrases = List.of("game", "games");

    @Override
    public boolean include(Preferences preferences, Job job) {
        final String title = job.getTitle().toLowerCase();
        if (titlePhrases.stream().anyMatch(k -> title.contains(k.toLowerCase()))) {
            System.err.println("game title only ->reject: " + job);
            return false;
        }
        if (phrases.stream().anyMatch(k -> title.contains(k.toLowerCase()))) {
            System.err.println("game title  ->reject: " + job);
            return false;
        }

        if (job.getDescription() != null) {
            final String text = job.getDescription().toLowerCase();
            if (phrases.stream().anyMatch(k -> text.contains(k.toLowerCase()))) {
                System.err.println("game description ->reject: " + job);
                return false;
            }
        }


        return true;
    }
}
