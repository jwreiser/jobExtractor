package com.goodforallcode.jobExtractor.filters;

import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyUtil;
import com.goodforallcode.jobExtractor.util.ReflectionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExcludeJobFilter {


    String name;
    List<Boolean> runIfFalse = new ArrayList<>();
    List<String> titlePhrases = new ArrayList<>();
    List<String> safeTitlePhrases = new ArrayList<>();
    List<String> safeDescriptionPhrases = new ArrayList<>();
    List<String> descriptionPhrases = new ArrayList<>();
    List<String> caseSensitiveDescriptionPhrases = new ArrayList<>();

    List<String> badCompanies = new ArrayList<>();
    List<String> goodCompanies = new ArrayList<>();
    String includeAttribute;
    String excludeIfTrueJobAttribute;
    String excludeIfFalseJobAttribute;
    List<String> excludeAttributes = new ArrayList<>();
    List<DescriptionPhrasesAndCount> descriptionPhrasesAndCounts = new ArrayList<>();
    boolean testForCompanyInDescription = false;
    Float minMaxAttributeValue = null;
    String minAttribute = null;
    String maxAttribute = null;

    List<String> industries = new ArrayList<>();

    public static ExcludeJobFilter build(String name) {
        ExcludeJobFilter filter = new ExcludeJobFilter();
        filter.name = name;
        return filter;
    }

    public ExcludeJobFilter titleAndDescriptionPhrases(List<String> phrases) {
        this.titlePhrases.addAll(new ArrayList<>(phrases));
        this.descriptionPhrases.addAll(new ArrayList<>(phrases));
        return this;
    }

    public ExcludeJobFilter titleCompanyNameAndDescriptionPhrases(List<String> phrases) {
        this.titlePhrases.addAll(new ArrayList<>(phrases));
        this.descriptionPhrases.addAll(new ArrayList<>(phrases));
        this.badCompanies.addAll(phrases);
        return this;
    }


    public ExcludeJobFilter industries(List<String> industries) {
        this.industries = industries;
        return this;
    }

    public ExcludeJobFilter runIfFalse(List<Boolean> runIfFalse) {
        this.runIfFalse = runIfFalse;
        return this;
    }

    public ExcludeJobFilter testForCompanyInDescription(boolean testForCompanyInDescription) {
        this.testForCompanyInDescription = testForCompanyInDescription;
        return this;
    }

    public ExcludeJobFilter maxAttribute(String minAttribute, Float maxAttributeValue) {
        this.maxAttribute = maxAttribute;
        this.minMaxAttributeValue = maxAttributeValue;
        return this;
    }

    public ExcludeJobFilter minAttribute(String minAttribute, Float minAttributeValue) {
        this.minAttribute = minAttribute;
        this.minMaxAttributeValue = minAttributeValue;
        return this;
    }

    public ExcludeJobFilter includeAttribute(String includeAttribute) {
        this.includeAttribute = includeAttribute;
        return this;
    }


    public ExcludeJobFilter excludeIfTrueJobAttribute(String excludeIfTrueJobAttribute) {
        this.excludeIfTrueJobAttribute = excludeIfTrueJobAttribute;
        return this;
    }

    public ExcludeJobFilter excludeIfFalseJobAttribute(String excludeIfFalseJobAttribute) {
        this.excludeIfFalseJobAttribute = excludeIfFalseJobAttribute;
        return this;
    }

    public ExcludeJobFilter safeTitlePhrases(List<String> safeTitlePhrases) {
        this.safeTitlePhrases = safeTitlePhrases;
        return this;
    }

    public ExcludeJobFilter excludeAttributes(List<String> excludeAttributes) {
        this.excludeAttributes = excludeAttributes;
        return this;
    }

    public ExcludeJobFilter titlePhrases(List<String> titlePhrases) {
        this.titlePhrases.addAll(new ArrayList<>(titlePhrases));
        return this;
    }

    public ExcludeJobFilter badCompanies(List<String> badCompanies) {
        this.badCompanies.addAll(badCompanies);
        return this;
    }

    public ExcludeJobFilter goodCompanies(List<String> goodCompanies) {
        this.goodCompanies = goodCompanies;
        return this;
    }

    public ExcludeJobFilter safeDescriptionPhrases(List<String> safeDescriptionPhrases) {
        this.safeDescriptionPhrases = safeDescriptionPhrases;
        return this;
    }

    public ExcludeJobFilter caseSensitiveDescriptionPhrases(List<String> caseSensitiveDescriptionPhrases) {
        this.caseSensitiveDescriptionPhrases = caseSensitiveDescriptionPhrases;
        return this;
    }


    public ExcludeJobFilter descriptionPhrases(List<String> descriptionPhrases) {
        this.descriptionPhrases.addAll(new ArrayList<>(descriptionPhrases));
        return this;
    }

    public ExcludeJobFilter descriptionPhrasesAndCount(List<String> descriptionPhrases, int excludeDescriptionCount) {
        this.descriptionPhrasesAndCounts.add(new DescriptionPhrasesAndCount(descriptionPhrases, excludeDescriptionCount));
        return this;
    }

    public ExcludeJobFilter descriptionPhrasesAndCounts(List<DescriptionPhrasesAndCount> descriptionPhrasesAndCounts) {
        this.descriptionPhrasesAndCounts.addAll(new ArrayList<>(descriptionPhrasesAndCounts));
        return this;
    }

    public String exclude(Preferences preferences, Job job) {
        if (runIfFalse.stream().anyMatch(b -> b)) {
            return null;
        }
        final String title = job.getTitle().toLowerCase();
        boolean safeTitle = false;
        Optional<String> match = null;
        if (job.getCompanyName() != null) {
            String companyName = job.getCompanyName();
            match = goodCompanies.stream().filter(c -> companyName.contains(c.toLowerCase())).findFirst();
            if (match.isPresent()) {
                return null;
            }
        }
        if (job.getDescription() != null && goodCompanies != null && testForCompanyInDescription) {
            String description = job.getDescription();
            match = goodCompanies.stream().filter(c -> description.contains(c.toLowerCase())).findFirst();
            if (match.isPresent()) {
                return null;
            }
        }

        if (badCompanies != null) {
            match = badCompanies.stream().filter(c -> job.getCompanyName().contains(c)).findFirst();
            if (match.isPresent()) {
                return getName() + " - company name -> " + match.get();
            }
        }
        if (testForCompanyInDescription && badCompanies != null) {
            match = badCompanies.stream().filter(c -> CompanyUtil.descriptionContainsCompanyName(c, job.getDescription())).findFirst();
            if (match.isPresent()) {
                return getName() + " - based on company description -> " + match.get();
            }
        }

        if (safeTitlePhrases != null && !safeTitlePhrases.isEmpty()) {
            match = safeTitlePhrases.stream().filter(p -> title.contains(p.toLowerCase())).findFirst();
            if (match.isPresent()) {
                safeTitle = true;
            }
        }
        if (titlePhrases != null && !titlePhrases.isEmpty() && !safeTitle) {
            match = titlePhrases.stream().filter(p -> title.contains(p.toLowerCase())).findFirst();

            if (match.isPresent()) {
                return getName() + " - title -> " + match.get();

            }
        }

        if (job.getCompany() != null && includeAttribute != null) {
            if (!ReflectionUtil.isAttributeTrue(job.getCompany(), includeAttribute)) {
                return getName() + " - attribute - " + includeAttribute;
            }
        }

        if (job.getCompany() != null && excludeAttributes != null) {
            match = excludeAttributes.stream().filter(a -> ReflectionUtil.isAttributeTrue(job.getCompany(), a)).findFirst();
            if (match.isPresent()) {
                return getName() + " - attribute - " + match.get();
            }
        }

        if (excludeIfFalseJobAttribute != null) {
            if (!ReflectionUtil.isAttributeTrue(job, excludeIfFalseJobAttribute)) {
                return getName() + " - job false attribute - " + excludeIfFalseJobAttribute;
            }
        }
        if (excludeIfTrueJobAttribute != null) {
            if (ReflectionUtil.isAttributeTrue(job, excludeIfTrueJobAttribute)) {
                return getName() + " - job true attribute - " + excludeIfTrueJobAttribute;
            }
        }
        if (maxAttribute != null && minMaxAttributeValue != null) {
            Float value = ReflectionUtil.getAttributeFloat(job, maxAttribute);
            if (value != null && value > minMaxAttributeValue) {
                return getName() + " - max attribute - " + maxAttribute + " " + value;
            }
        }

        //industries must come after titles  to take advantage of safe titles
        if (job.getIndustries() != null && !safeTitle) {
            match = industries.stream().filter(p -> job.getIndustries().contains(p)).findFirst();
            if (match.isPresent()) {
                return getName() + " - industries -> " + match.get();
            }

        }
        /*
        if (minAttribute!=null && minAttributeValue!=null) {
            Float value = ReflectionUtil.getAttributeFloat(job, minAttribute);
            if (value != null && value >= minAttributeValue) {
                return getName() + " - attribute - " + minAttribute + " " + value;
            }
        }

        Optional<String> match = titleAndDescriptionPhrases.stream().filter(p -> title.contains(p.toLowerCase())).findFirst();
        if(match.isPresent()){
            return getName()+" - title -> " + match.get();
        }
        if(job.getCompanyName()!=null) {
            match = titleAndDescriptionPhrases.stream().filter(c -> job.getCompanyName().equals(c)).findFirst();
            if(match.isPresent()){
                return getName()+" - company name -> " + match.get();
            }
        }

         */

        if (job.getDescription() != null) {
            String description = job.getDescription().toLowerCase();

            match = safeDescriptionPhrases.stream().filter(c -> description.contains(c.toLowerCase())).findFirst();
            if (!match.isPresent()) {
                String casedDescription = job.getDescription();

                match = descriptionPhrases.stream().filter(c -> description.contains(c.toLowerCase())).findFirst();
                if (match.isPresent()) {
                    return getName() + " - description -> " + match.get();
                }
                match = caseSensitiveDescriptionPhrases.stream().filter(c -> casedDescription.contains(c)).findFirst();
                if (match.isPresent()) {
                    return getName() + " - description -> " + match.get();
                }

                if (!descriptionPhrasesAndCounts.isEmpty()) {
                    for (DescriptionPhrasesAndCount pair : descriptionPhrasesAndCounts) {
                        long count = pair.descriptionPhrases.stream().filter(c -> description.contains(c.toLowerCase())).count();
                        if (count > pair.excludeDescriptionCount) {
                            return getName() + " - description count-> " + count;
                        }
                    }
                }
            }
        }

        return null;
    }

    public String getName() {
        return this.name;
    }
}

