package com.goodforallcode.jobExtractor.util;

import com.goodforallcode.jobExtractor.model.Job;

import java.util.List;

public class CompanyNameUtil {
    public static boolean descriptionContainsCompanyName(String companyName, String description){
        boolean contains=false;
        if(description==null){
            return false;
        }
        final String descriptionLower=description.toLowerCase();
        List<String> patterns=List.of("our client[^\\.\\;]*"+companyName.toLowerCase());
        for(String pattern:patterns) {
            if (RegexUtil.matchesPattern(descriptionLower,pattern)){
                contains=true;
                break;
            }
        }
        return contains;
    }
    public static boolean containsCompanyName(String companyName, Job job){
        if(job.getCompanyName()!=null&&job.getCompanyName().equals(companyName)){
            return true;
        }
        return descriptionContainsCompanyName(companyName,job.getDescription());
    }
}
