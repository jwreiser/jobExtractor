package com.goodforallcode.jobExtractor.filters;

import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyUtil;
import com.goodforallcode.jobExtractor.util.ReflectionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IncludeOrSkipJobFilter {
    String name;
    List<String> titleAndDescriptionPhrases=new ArrayList<>();
    List<String> titlePhrases=new ArrayList<>();
    List<Boolean> runIfFalse =new ArrayList<>();

    List<String> descriptionPhrases=new ArrayList<>();
    List<String> badCompanies=new ArrayList<>();
    List<String> goodCompanies=new ArrayList<>();
    List<String> skills=new ArrayList<>();
    String includeAttribute;
    boolean testForCompanyInDescription=false;
    Float minMaxAttributeValue =null;
    String minAttribute=null;
    String maxAttribute=null;

    List<String> industries=new ArrayList<>();
    public static IncludeOrSkipJobFilter build(String name) {
        IncludeOrSkipJobFilter filter = new IncludeOrSkipJobFilter();
        filter.name = name;
        return filter;
    }
    public IncludeOrSkipJobFilter skills(List<String> skills) {
        this.skills = skills;
        return  this;
    }

    public IncludeOrSkipJobFilter titleAndDescriptionPhrases(List<String> titleAndDescriptionPhrases) {
        this.titlePhrases.addAll(new ArrayList<>(titleAndDescriptionPhrases));
        this.descriptionPhrases.addAll(new ArrayList<>(titleAndDescriptionPhrases));
        return  this;
    }
    public IncludeOrSkipJobFilter runIfFalse(List<Boolean> runIfFalse) {
        this.runIfFalse = runIfFalse;
        return  this;
    }

    public IncludeOrSkipJobFilter industries(List<String> industries) {
        this.industries = industries;
        return  this;
    }
    public IncludeOrSkipJobFilter testForCompanyInDescription(boolean testForCompanyInDescription) {
        this.testForCompanyInDescription = testForCompanyInDescription;
        return  this;
    }
    public IncludeOrSkipJobFilter minAttribute(String minAttribute, Float minAttributeValue) {
        this.minAttribute = minAttribute;
        this.minMaxAttributeValue = minAttributeValue;
        return  this;
    }
    public IncludeOrSkipJobFilter maxAttribute(String maxAttribute, Float maxAttributeValue) {
        this.maxAttribute = maxAttribute;
        this.minMaxAttributeValue = maxAttributeValue;
        return  this;
    }
    public IncludeOrSkipJobFilter includeAttribute(String includeAttribute) {
        this.includeAttribute = includeAttribute;
        return  this;
    }

    public IncludeOrSkipJobFilter titlePhrases(List<String> titlePhrases) {
        this.titlePhrases.addAll(new ArrayList<>(titlePhrases));
        return  this;
    }

    public IncludeOrSkipJobFilter badCompanies(List<String> badCompanies) {
        this.badCompanies = badCompanies;
        return  this;
    }
    public IncludeOrSkipJobFilter goodCompanies(List<String> goodCompanies) {
        this.goodCompanies = goodCompanies;
        return  this;
    }

    public IncludeOrSkipJobFilter descriptionPhrases(List<String> descriptionPhrases) {
        this.descriptionPhrases.addAll(new ArrayList<>(descriptionPhrases));
        return  this;
    }

    public String include(Preferences preferences, Job job){
        if(runIfFalse.stream().anyMatch(b->b)){
            return null;
        }
        final String title = job.getTitle().toLowerCase();
        if (minAttribute!=null && minMaxAttributeValue !=null) {
            Float value = ReflectionUtil.getAttributeFloat(job, minAttribute);
            if (value != null && value >= minMaxAttributeValue) {
                return getName() + " - attribute - " + minAttribute + " " + value;
            }
        }
        if (maxAttribute!=null && minMaxAttributeValue !=null) {
            Float value = ReflectionUtil.getAttributeFloat(job, maxAttribute);
            if (value != null && value < minMaxAttributeValue) {
                return getName() + " - attribute - " + maxAttribute + " " + value;
            }
        }

        Optional<String> match = titleAndDescriptionPhrases.stream().filter(p -> title.contains(p.toLowerCase())).findFirst();
        if(match.isPresent()){
            return getName()+" - title -> " + match.get();
        }

        match = skills.stream().filter(s -> job.getSkills().contains(s)).findFirst();
        if(match.isPresent()){
            return getName()+" - skills -> " + match.get();
        }

        match = skills.stream().filter(s -> title.contains(s.toLowerCase())).findFirst();
        if(match.isPresent()){
            return getName()+" - title skills -> " + match.get();
        }


        match =  titlePhrases.stream().filter(p -> title.contains(p.toLowerCase())).findFirst();
        if(match.isPresent()){
            return getName()+" - title -> " + match.get();
        }
        if(job.getCompanyName()!=null) {
            match = titleAndDescriptionPhrases.stream().filter(c -> job.getCompanyName().equals(c)).findFirst();
            if(match.isPresent()){
                return getName()+" - company name -> " + match.get();
            }
        }
        if(job.getCompany()!=null && includeAttribute!=null){
            if(ReflectionUtil.isAttributeTrue(job.getCompany(),includeAttribute)){
                return getName()+" - attribute - " +  includeAttribute;
            }
        }
        if (job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();

            match = descriptionPhrases.stream().filter(c -> description.contains(c.toLowerCase())).findFirst();
            if(match.isPresent()){
                return getName()+" - description -> " + match.get();
            }

            match = titleAndDescriptionPhrases.stream().filter(c -> description.contains(c.toLowerCase())).findFirst();
            if(match.isPresent()){
                return getName()+" - description -> " + match.get();
            }


            if(testForCompanyInDescription && goodCompanies!=null){
                match = goodCompanies.stream().filter(c ->CompanyUtil.descriptionContainsCompanyName(c, job.getDescription())).findFirst();
                if(match.isPresent()){
                    return getName()+" - based on company description -> " + match.get();
                }
            }

            if (job.getIndustries() != null) {
                match = industries.stream().filter(p -> job.getIndustries().contains(p)).findFirst();
                if(match.isPresent()){
                    return getName()+" - industries -> " + match.get();
                }

            }

        }
        return null;
    }

    public String getName() {
        return name;
    }
}
