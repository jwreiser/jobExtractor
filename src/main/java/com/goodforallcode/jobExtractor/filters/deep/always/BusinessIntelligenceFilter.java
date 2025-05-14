package com.goodforallcode.jobExtractor.filters.deep.always;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class BusinessIntelligenceFilter implements JobFilter {
    List<String>keywords=List.of("business intelligence","BI ","power bi","Domo",
            "Tableau","Looker","SAP ","PowerBI","QLik","DataStudio","ABAP",
            "Cognos","Pyramid",

            "PO Developer","abinitio","ab initio","Yardi ","QuickSight","Crystal Report");
    final List<String>titles=List.of("Reporting","Dashboard");


    @Override
    public boolean include(Preferences preferences, Job job) {
        final String title = job.getTitle().toLowerCase();
        if (titles.stream().anyMatch(k -> title.contains(k.toLowerCase()))) {
            return false;
        }
        if (keywords.stream().anyMatch(k -> title.contains(k.toLowerCase()))) {
            return false;
        }

        if(job.getDescription()!=null) {
            String text = job.getDescription().toLowerCase();

            if (keywords.stream().filter(k -> text.contains(k.toLowerCase())).count() > 1) {
                return false;
            }
        }
        return true;
    }
}
