package com.goodforallcode.jobExtractor.filters.shallow.title;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.CompanySummary;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class NightShiftBothFilter implements JobFilter {
    List<String> jobTitlePhrases=List.of(
"Evening","-7A","-7A","Over Night","-11p");

    List<String> descriptionPhrases=List.of(
            "nights","pm to close");

    List<String> bothPhrases=List.of(
            "2nd shift","3rd shift","second shift","third shift","night shift","evening shift","graveyard shift");


    @Override
    public boolean include(Preferences preferences, Job job) {
        String title =job.getTitle().toLowerCase();

         if(jobTitlePhrases.stream().anyMatch(t->title.contains(t.toLowerCase()))){
            System.err.println(this.getClass()+" title->reject: "+job);
            return false;
        }

        if(bothPhrases.stream().anyMatch(t->title.contains(t.toLowerCase()))){
            System.err.println(this.getClass()+" title->reject: "+job);
            return false;
        }
         if(job.getDescription()!=null) {
             String description =job.getDescription().toLowerCase();
             if (descriptionPhrases.stream().anyMatch(t -> description.contains(t.toLowerCase()))) {
                 System.err.println(this.getClass() + " description->reject: " + job);
                 return false;
             }
             if (bothPhrases.stream().anyMatch(t -> description.contains(t.toLowerCase()))) {
                 System.err.println(this.getClass() + " description->reject: " + job);
                 return false;
             }
         }

        if(job.getCompany()!=null){
            CompanySummary sum = job.getCompany();
            if(sum.getSoftwareEngineerNightOrWeekendHours()!=null && sum.getSoftwareEngineerNightOrWeekendHours()) {
                System.err.println("summary night or weekend ->reject: " + job);
                return false;
            }
        }

        return true;
    }


}
