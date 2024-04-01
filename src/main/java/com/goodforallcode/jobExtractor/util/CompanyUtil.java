package com.goodforallcode.jobExtractor.util;

import com.goodforallcode.jobExtractor.model.Job;

import java.util.List;

public class CompanyUtil {
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

    public static boolean isRecruiting(String companyName,List<String>industries){
        List<String>recruitingIndustries=List.of("Human Resources & Staffing","Human Resources & Management Consulting", "Human Resources Services"
                ,"Staffing and Recruiting","Outsourcing and Offshoring Consulting");
        List<String>recruitingKeywords=List.of("Human Resources","Recruit","Staffing","hustles","Offshor","Outsourc");
        List<String>recruitingCompanies=List.of("Dice","HustleWing - Side hustles for professionals","Jobs for Humanity","EdgeGlobal LLC"
        ,"tek inspirations llc","Jobot","Redleo Software");
        String lowerCase=companyName.toLowerCase();
        if(recruitingCompanies.stream().anyMatch(c->lowerCase.equals(c.toLowerCase()))){
            return true;
        }

        if(industries!=null && recruitingIndustries.stream().anyMatch(i->industries.contains(i))){
            return true;
        }

        for(String keyword:recruitingKeywords){
            if(industries!=null && industries.stream().anyMatch(i->i.toLowerCase().contains(keyword.toLowerCase()))){
                return true;
            }
            if(companyName.toLowerCase().contains(keyword.toLowerCase())){
                return true;
            }
        }
        return false;
    }
}
