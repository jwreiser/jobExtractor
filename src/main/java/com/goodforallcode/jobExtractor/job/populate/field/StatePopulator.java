package com.goodforallcode.jobExtractor.job.populate.field;

import com.goodforallcode.jobExtractor.filters.ExcludeJobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StatePopulator implements FieldPopulator {
    List<String> states = List.of("Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia"
            , "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland"
            , "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming");

    public void populateField(Job job, Preferences preferences) {
        if ((job.getFullyRemote() == null || !job.getFullyRemote()) && (job.getState() == null || job.getState().isEmpty())) {
            boolean populated=false;
            List<ExcludeJobFilter> filters = new ArrayList<>();
            filters.add(ExcludeJobFilter.build("Connecticut").matchingCompanies(List.of("Hartford")).titlePhrases(List.of(" CT ")).safeTitlePhrases(List.of("CT Tech", "CT Scan")).municipality("CT").titleAndDescriptionPhrases(List.of("Hartford", "Connecticut", "Milford", "Fairfield", "Bridgeport", "Stratford", "Trumbull", "Shelton")));

            filters.add(ExcludeJobFilter.build("Florida").matchingCompanies(List.of("University of Florida")).municipality("FL").titleAndDescriptionPhrases(List.of("Miami", "Tampa", "Orlando", "Jacksonville","Florida")));
            filters.add(ExcludeJobFilter.build("Kentucky").titleAndDescriptionPhrases(List.of("Hopkinsville")).municipality("KY"));
            filters.add(ExcludeJobFilter.build("Massachusetts").titleCompanyNameAndDescriptionPhrases(List.of("Natick")).municipality("MA"));


            filters.add(ExcludeJobFilter.build("New Jersey").titleCompanyNameAndDescriptionPhrases(List.of("Newark", "New Jersey", "Jersey City", "New Brunswick", "Trenton", "Freehold", "Camden", "Toms River", "Bayonne", "East Orange", "Perth Amboy", "Passaic", "Scoth Plains", "Iselin", "Bridgewater", "Boonton", "Denville", "Parsippany", "Saddle Brook", "Hackensack", "North Bergen", "Watchung", "Long Valley", "Piscataway", "Verona", "Hoboken", "Matawan", "Plainsboro", "Paterson")).municipality("NJ"));


            filters.add(ExcludeJobFilter.build("Pennsylvania").matchingCompanies(List.of("Drexel")).titleAndDescriptionPhrases(List.of("Philadelphia", "Havertown", "Norristown", "Schwenksville")).municipality("PA"));


            filters.add(ExcludeJobFilter.build("Virginia").municipality("VA").titleAndDescriptionPhrases(List.of("Richmond", "Virginia")));

            for (ExcludeJobFilter filter : filters) {
                if (filter.exclude(job) != null) {
                    job.setState(filter.getName());
                    populated=true;
                    break;
                }
            }
            if(!populated && job.getCompanyName()!=null){
                String company=job.getCompanyName().toLowerCase();
                Optional<String> state=states.stream().filter(s->company.contains(s.toLowerCase())).findFirst();
                if(state.isPresent()){
                    job.setState(state.get());
                    populated=true;

                }
            }
        }


    }
}
