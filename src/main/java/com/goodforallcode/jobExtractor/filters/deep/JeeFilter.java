package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class JeeFilter implements JobFilter {
    List<String>keywords=List.of("JSP","JSF","Struts","Tomcat","Websphere","JBoss","J2EE"," JEE ");

    @Override
    public boolean include(Preferences preferences, Job job) {
        if (job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            long count = keywords.stream().filter(k -> description.contains(k.toLowerCase())).count();
            if (count > 2) {
                System.err.println("JEE count " + count + " ->reject: " + job);
                return false;
            }
        }
        return true;
    }
}
