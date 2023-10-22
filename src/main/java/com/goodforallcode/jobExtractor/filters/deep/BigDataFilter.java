package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;
import java.util.stream.Collectors;

public class BigDataFilter implements JobFilter {
    List<String> phrases =List.of(   "big data","ETL","pipelines","pipeline",
            "Extract, Transform, and Load","Flink","Talend",
            "spark","hive","pig","warehousing","lake","hadoop","Warehouse");

    @Override
    public boolean include(Preferences preferences, Job job) {
        if(!preferences.isExcludeBigData()){
            return true;
        }

        String text =job.getDescription().toLowerCase();

        if(phrases.stream().filter(k->text.contains(k.toLowerCase())).count()>2){
            System.err.println("BigData ->reject: "+job);
            return false;
        }
        return true;
    }
}
