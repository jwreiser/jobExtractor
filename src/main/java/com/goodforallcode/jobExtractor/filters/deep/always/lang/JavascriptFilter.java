package com.goodforallcode.jobExtractor.filters.deep.always.lang;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class JavascriptFilter implements JobFilter {
    List<String>keywords=List.of("Javascript","typescript","node",
            "express","react"
            ,"angular","Webpack","VUE","npm");
    List<String>soloPhrases=List.of("advanced JavaScript");

    @Override
    public boolean include(Preferences preferences, Job job) {
        String text =job.getDescription().toLowerCase();

        if(soloPhrases.stream().anyMatch(p->text.contains(p.toLowerCase()))){
            System.err.println("javascript solo->reject: "+job);
            return false;
        }

        long  mainCount=keywords.stream().filter(k->text.contains(k.toLowerCase())).count();
        if(mainCount>3){
            System.err.println("javascript count "+mainCount+"->reject: "+job);
            return false;
        }
        return true;
    }
}
