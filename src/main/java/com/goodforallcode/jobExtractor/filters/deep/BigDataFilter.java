package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class BigDataFilter implements JobFilter {
    List<String> descriptionPhrases =List.of(   "pipelines","pipeline",
            "Extract, Transform, and Load");
    List<String> bothPhrases =List.of(   "big data","ETL ",
            "Extract, Transform, and Load",
            "spark","hive ","pig ","warehousing","hadoop");

    @Override
    public boolean include(Preferences preferences, Job job) {
        if(!preferences.isExcludeBigData()){
            return true;
        }
        String title = job.getTitle().toLowerCase();
        if (bothPhrases.stream().anyMatch(k -> title.contains(k.toLowerCase()))) {
            System.err.println(this.getClass()+" title both ->reject: " + job);
            return false;
        }
        if(job.getDescription()!=null) {
            String text = job.getDescription().toLowerCase();
            if (bothPhrases.stream().filter(k -> text.contains(k.toLowerCase())).count() > 2) {
                System.err.println(this.getClass()+" description both ->reject: " + job);
                return false;
            }
            if (descriptionPhrases.stream().filter(k -> text.contains(k.toLowerCase())).count() > 2) {
                System.err.println(this.getClass()+" description ->reject: " + job);
                return false;
            }
        }

        return true;
    }
}
