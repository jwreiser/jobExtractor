package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class OracleTechFilter implements JobFilter {
    List<String> descriptionPhases =List.of(" CPQ ","Oracle Apex","Oracle EBS","Oracle Cloud ERP"
            ,"Oracle ERP"," EPM","Oracle fusion","Hyperion");
    List<String> bothPhases =List.of("FCCS","Oracle Developer","Oracle Engineer");

    List<String>titleKeywords=List.of("Oracle","EBS ");


    @Override
    public boolean include(Preferences preferences, Job job) {
        final String title=job.getTitle().toLowerCase();
        if (titleKeywords.stream().anyMatch(k -> title.contains(k.toLowerCase()))) {
            System.err.println("Oracle title ->reject: " + job);
            return false;
        }
        if (bothPhases.stream().anyMatch(k -> title.contains(k.toLowerCase()))) {
            System.err.println("Oracle title ->reject: " + job);
            return false;
        }

        if(job.getDescription()!=null) {
            String text = job.getDescription().toLowerCase();
            if (bothPhases.stream().anyMatch(k -> text.contains(k.toLowerCase()))) {
                System.err.println("Oracle description both ->reject: " + job);
                return false;
            }

            if (descriptionPhases.stream().filter(k -> text.contains(k.toLowerCase())).count() > 1) {
                System.err.println("Oracle description only ->reject: " + job);
                return false;
            }
        }
        return true;
    }
}
