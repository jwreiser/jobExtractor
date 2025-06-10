package com.goodforallcode.jobExtractor.filters;

import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.util.CompanyUtil;
import com.goodforallcode.jobExtractor.util.ReflectionUtil;
import com.goodforallcode.jobExtractor.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExcludeJobFilter {


    String name;
    String municipality;
    List<Boolean> runIfFalse = new ArrayList<>();
    List<String> titlePhrases = new ArrayList<>();
    List<String> titleStartsWithPhrases = new ArrayList<>();
    List<String> titleEqualsPhrases = new ArrayList<>();
    List<String> safeTitlePhrases = new ArrayList<>();
    List<String> safeDescriptionPhrases = new ArrayList<>();
    List<String> descriptionPhrases = new ArrayList<>();
    List<String> caseSensitiveDescriptionPhrases = new ArrayList<>();

    List<String> matchingCompanies = new ArrayList<>();
    List<String> matchingCompaniesEquals = new ArrayList<>();
    List<String> matchingCompaniesStartsWith = new ArrayList<>();
    List<String> matchingCompaniesEndsWith = new ArrayList<>();

    List<String> exceptionalCompanies = new ArrayList<>();
    String includeAttribute;
    String excludeIfTrueJobAttribute;
    String excludeIfJobAttribute;
    String excludeIfJobAttributeValue;
    String excludeIfFalseJobAttribute;
    List<String> excludeCompanyAttributes = new ArrayList<>();
    List<DescriptionPhrasesAndCount> descriptionPhrasesAndCounts = new ArrayList<>();
    boolean testForCompanyInDescription = false;
    boolean runOnlyIfNotFullyRemote = false;
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

    public ExcludeJobFilter municipality(String value) {
        this.municipality = value.toLowerCase();
        return this;
    }

    public ExcludeJobFilter titleCompanyNameAndDescriptionPhrases(List<String> phrases) {
        this.titlePhrases.addAll(new ArrayList<>(phrases));
        this.descriptionPhrases.addAll(new ArrayList<>(phrases));
        this.matchingCompanies.addAll(phrases);
        return this;
    }

    public ExcludeJobFilter titleStartsWithPhrases(List<String> phrases) {
        this.titleStartsWithPhrases.addAll(new ArrayList<>(phrases));
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

    public ExcludeJobFilter runOnlyIfNotFullyRemote(boolean value) {
        this.runOnlyIfNotFullyRemote = value;
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

    public ExcludeJobFilter excludeIfJobAttributeAndValue(String jobAttribute, String attributeValue) {
        this.excludeIfJobAttribute = jobAttribute;
        this.excludeIfJobAttributeValue = attributeValue;
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
        this.safeTitlePhrases.addAll(safeTitlePhrases);
        return this;
    }

    public ExcludeJobFilter excludeCompanyAttributes(List<String> excludeAttributes) {
        this.excludeCompanyAttributes.addAll(excludeAttributes);
        return this;
    }

    public ExcludeJobFilter titlePhrases(List<String> titlePhrases) {
        this.titlePhrases.addAll(new ArrayList<>(titlePhrases));
        return this;
    }
    public ExcludeJobFilter titleEqualsPhrases(List<String> titlePhrases) {
        this.titleEqualsPhrases.addAll(new ArrayList<>(titlePhrases));
        return this;
    }

    public ExcludeJobFilter matchingCompanies(List<String> badCompanies) {
        this.matchingCompanies.addAll(badCompanies);
        return this;
    }

    public ExcludeJobFilter matchingCompaniesEquals(List<String> badCompanies) {
        this.matchingCompaniesEquals.addAll(badCompanies);
        return this;
    }

    public ExcludeJobFilter matchingCompaniesStartsWith(List<String> badCompanies) {
        this.matchingCompaniesStartsWith.addAll(badCompanies);
        return this;
    }

    public ExcludeJobFilter matchingCompaniesEndsWith(List<String> badCompanies) {
        this.matchingCompaniesEndsWith.addAll(badCompanies);
        return this;
    }

    public ExcludeJobFilter exceptionalCompanies(List<String> goodCompanies) {
        this.exceptionalCompanies.addAll(goodCompanies);
        return this;
    }

    public ExcludeJobFilter safeDescriptionPhrases(List<String> safeDescriptionPhrases) {
        this.safeDescriptionPhrases.addAll(safeDescriptionPhrases);
        return this;
    }

    public ExcludeJobFilter safeTitleAndDescriptionPhrases(List<String> safeDescriptionPhrases) {
        this.safeDescriptionPhrases.addAll(safeDescriptionPhrases);
        this.safeTitlePhrases.addAll(safeDescriptionPhrases);
        return this;
    }

    public ExcludeJobFilter caseSensitiveDescriptionPhrases(List<String> caseSensitiveDescriptionPhrases) {
        this.caseSensitiveDescriptionPhrases.addAll(caseSensitiveDescriptionPhrases);
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

    public String exclude(Job job) {
        if (runIfFalse.stream().anyMatch(b -> b)) {
            return null;
        }
        if (runOnlyIfNotFullyRemote && job.getFullyRemote() != null && job.getFullyRemote()) {
            return null;
        }
        final String title = job.getTitle().toLowerCase();
        boolean safeTitle = false;
        Optional<String> match = null;
        if (job.getCompanyName() != null) {
            String companyName = job.getCompanyName();
            match = exceptionalCompanies.stream().filter(c -> companyName.contains(c.toLowerCase())).findFirst();
            if (match.isPresent()) {
                return null;
            }
        }
        if (job.getDescription() != null && exceptionalCompanies != null && testForCompanyInDescription) {
            String description = job.getDescription();
            match = exceptionalCompanies.stream().filter(c -> description.contains(c.toLowerCase())).findFirst();
            if (match.isPresent()) {
                return null;
            }
        }

        if (StringUtil.valuePopulated(job.getCompanyName())) {
            String companyName = job.getCompanyName().toLowerCase();

            if (matchingCompanies != null) {
                match = matchingCompanies.stream().filter(c -> companyName.contains(c.toLowerCase())).findFirst();
                if (match.isPresent()) {
                    return getName() + " - company name -> " + match.get();
                }
            }
            if (matchingCompaniesEquals != null) {
                match = matchingCompaniesEquals.stream().filter(c -> companyName.equalsIgnoreCase(c)).findFirst();
                if (match.isPresent()) {
                    return getName() + " - company name equals -> " + match.get();
                }
            }
            if (matchingCompaniesStartsWith != null) {
                match = matchingCompaniesStartsWith.stream().filter(c -> companyName.startsWith(c.toLowerCase())).findFirst();
                if (match.isPresent()) {
                    return getName() + " - company name starts with -> " + match.get();
                }
            }
            if (matchingCompaniesEndsWith != null) {
                match = matchingCompaniesEndsWith.stream().filter(c -> companyName.endsWith(c.toLowerCase())).findFirst();
                if (match.isPresent()) {
                    return getName() + " - company name starts with -> " + match.get();
                }
            }

            if (testForCompanyInDescription && matchingCompanies != null) {
                match = matchingCompanies.stream().filter(c -> CompanyUtil.descriptionContainsCompanyName(c, job.getDescription())).findFirst();
                if (match.isPresent()) {
                    return getName() + " - based on company description -> " + match.get();
                }
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

        if (titleStartsWithPhrases != null && !titleStartsWithPhrases.isEmpty() && !safeTitle) {
            match = titleStartsWithPhrases.stream().filter(p -> title.startsWith(p.toLowerCase())).findFirst();

            if (match.isPresent()) {
                return getName() + " - title -> " + match.get();

            }
        }

        if (titleEqualsPhrases != null && !titleEqualsPhrases.isEmpty() && !safeTitle) {
            match = titleEqualsPhrases.stream().filter(p -> title.equalsIgnoreCase(p)).findFirst();

            if (match.isPresent()) {
                return getName() + " - title equals -> " + match.get();

            }
        }

        if (job.getCompany() != null && includeAttribute != null) {
            if (!ReflectionUtil.isAttributeTrue(job.getCompany(), includeAttribute)) {
                return getName() + " - attribute - " + includeAttribute;
            }
        }

        if (job.getCompany() != null && excludeCompanyAttributes != null) {
            match = excludeCompanyAttributes.stream().filter(a -> ReflectionUtil.isAttributeTrue(job.getCompany(), a)).findFirst();
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

        if (excludeIfJobAttribute != null && excludeIfJobAttributeValue != null) {
            String value = ReflectionUtil.getAttributeString(job, excludeIfJobAttribute);
            if (value != null && value.equals(excludeIfJobAttributeValue)) {
                return getName() + " - job attribute - " + excludeIfJobAttribute + " " + value;
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
        if (municipality != null && !municipality.isEmpty() && job.getMunicipality() != null && !job.getMunicipality().isEmpty()) {
            String jobMunicipality = job.getMunicipality().toLowerCase();
            if (jobMunicipality.endsWith(" " + municipality)) {
                return getName() + " - Municipality -> " + jobMunicipality;
            } else if (jobMunicipality.contains(" " + municipality + " ")) {
                return getName() + " - Municipality -> " + jobMunicipality;
            } else if (jobMunicipality.equals(municipality)) {
                return getName() + " - Municipality -> " + jobMunicipality;
            }

        }

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

