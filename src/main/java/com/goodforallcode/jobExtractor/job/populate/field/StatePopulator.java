package com.goodforallcode.jobExtractor.job.populate.field;
import com.goodforallcode.jobExtractor.filters.ExcludeJobFilter;
import com.goodforallcode.jobExtractor.model.Job;

import java.util.ArrayList;
import java.util.List;

public class StatePopulator implements FieldPopulator{
    public void populateField(Job job) {
        if ((job.getRemote()==null || !job.getRemote()) && (job.getState() == null || job.getState().isEmpty())) {
            List<ExcludeJobFilter> filters=new ArrayList<>();
            filters.add(ExcludeJobFilter.build("NJ")
                    .titleAndDescriptionPhrases(List.of("Newark", "New Jersey", "Jersey City", "New Brunswick", "Trenton"
                            , "Freehold", "Camden", "Toms River", "Bayonne", "East Orange", "Perth Amboy", "Passaic", "Scoth Plains", "Iselin", "Bridgewater"
                            , "Saddle Brook", "Hackensack", "North Bergen","Watchung","Long Valley","Piscataway"))
                    .badCompanies(List.of("New Jersey"))
                    .location("NJ"));

            filters.add(ExcludeJobFilter.build("PA")
                        .badCompanies(List.of("Drexel"))
                        .titleAndDescriptionPhrases(List.of("Philadelphia", "Havertown", "Norristown", "Schwenksville"))
                        .location("PA"));

            filters.add(ExcludeJobFilter.build("CT")
                            .badCompanies(List.of("Hartford"))
                            .titlePhrases(List.of(" CT "))
                            .safeTitlePhrases(List.of("CT Tech", "CT Scan"))
                            .location("CT")
                            .titleAndDescriptionPhrases(List.of("Hartford", "Connecticut", "Milford", "Fairfield", "Bridgeport", "Stratford", "Trumbull", "Shelton")));

            filters.add(ExcludeJobFilter.build("FL")
                    .badCompanies(List.of("University of Florida"))
                    .location("FL")
                    .titleAndDescriptionPhrases(List.of("Miami", "Tampa", "Orlando", "Jacksonville"))
            );
            for(ExcludeJobFilter filter: filters) {
                if (filter.exclude(job) != null) {
                    job.setState(filter.getName());
                    break;
                }
            }
        }


    }
}
