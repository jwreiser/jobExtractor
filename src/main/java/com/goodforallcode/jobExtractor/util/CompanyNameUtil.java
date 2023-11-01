package com.goodforallcode.jobExtractor.util;

import java.util.List;

public class CompanyNameUtil {
    public static boolean containsCompanyName(String companyName,String description){
        boolean contains=false;
        List<String> patterns=List.of("Our client[^\\.\\;]*"+companyName);
        for(String pattern:patterns) {
            if (RegexUtil.matchesPattern(description,pattern)){
                contains=true;
                break;
            }
        }
        return contains;
    }
}
