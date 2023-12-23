package com.goodforallcode.jobExtractor.filters.deep.always;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class FrontEndFilter implements JobFilter {
    /**
     * Exceptions
     *
     * Exclusive
     * Front-end : might show up in a work with front-end, BAs...
     * UX: work with UX
     */
    List<String>keywords=List.of("bootstrap ","CSS","HTML","Javascript","frontend","front end"," UX",
            " ui","ui ","react","angular","angularjs","typescript","vue","jquery","JSP","JSF");
    List<String>exclusiveKeywords=List.of(
            "Redux","PrimeFaces"
            ,"FreeMarker", "Handlebars","multiple front-end frameworks");

    List<String> jobTitlePhrases=List.of(
            "Ui developer","Ui engineer","UX developer",
            " React","Angular","Typescript","Javascript"," UX"
    );
    List<String> jobTitleStartsWithPhrases=List.of(
            "Front end","Frontend","Front-end"
    );

    @Override
    public boolean include(Preferences preferences, Job job) {
        String title =job.getTitle().toLowerCase();

        //if this is a job title we are not qualified for
        if(jobTitleStartsWithPhrases.stream().anyMatch(t->title.startsWith(t.toLowerCase()))){
            System.err.println("Front end job title->reject: "+job);
            return false;
        }
        if(jobTitlePhrases.stream().anyMatch(t->title.contains(t.toLowerCase()))){
            System.err.println("Front end job title->reject: "+job);
            return false;
        }
        if(job.getDescription()!=null) {
            String text = job.getDescription().toLowerCase();

            long mainCount = keywords.stream().filter(k -> text.contains(k.toLowerCase())).count();
            if (mainCount > 3) {
                System.err.println("front end count " + mainCount + " ->reject: " + job);
                return false;
            }
            long exclusiveCount = exclusiveKeywords.stream().filter(k -> text.contains(k.toLowerCase())).count();
            if (exclusiveCount > 1 || (exclusiveCount > 0 && mainCount > 1)) {
                System.err.println("front end exclusive ->reject: " + job);
                return false;
            }
        }
        return true;
    }

}
