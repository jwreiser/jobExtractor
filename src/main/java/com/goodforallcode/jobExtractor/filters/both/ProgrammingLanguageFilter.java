package com.goodforallcode.jobExtractor.filters.both;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.RegexUtil;

import java.util.List;
import java.util.Optional;

import static com.goodforallcode.jobExtractor.util.RegexUtil.getUntilNextBoundary;
import static com.goodforallcode.jobExtractor.util.RegexUtil.isPresentAndImportant;

/**
 * This is designed for languages that usually do not play together
 * So unlike C#, Java and Python which are often shared (especially Python) this focuses on
 * languages like rust and ruby
 */
public class ProgrammingLanguageFilter implements JobFilter {
    /**
     * Must not be " C" as that can match other things needs to be " C "
     * can't end in RoR as lowercased that is in words like error
     * scala: scalable perl: properly
     */
    public static List<String> languages = List.of(
            "Ruby", " Rust", "Golang", "Net ", "DotNet", ".Net", "iOS ", "React ",
            "Angular", "Typescript", "Javascript", "CNO", "C#", "C++", "Visual C", "Scala ",
            "Swift ", "Fortran","Delphi","Ignition",
            "Dart", " C ", " R ", "PHP", "Apex", "React Native",
            "VB.NET", "perl ", "MATLAB", "SAS", "COBOL", "ABAP", "Tcl", "Elixir", "Erlang",
            "F#","Vue.js","jQuery","React.js",
            "GO Lang", "ColdFusion", "verilog",
            "GraphQL", "R Shiny", "Node", "NodeJS", "Django", " RoR", "Haskell", "PL/SQL",
            "Node Js", "Node.js", "Ruby on Rails", "BBj", "BBx", "Business Basic");


    /*
    the appropriate spaces are added when these are used
    adding a space to Go here will make it Go  Developer which won't match
     */
    public static List<String> titleOnlyLanguages = List.of(
            "Natural", "Spark", "MUMPS", "Genie", "Go", "Scala", "Perl");
    static List<String> sharedLanguages = List.of("Python");
    boolean include;

    public ProgrammingLanguageFilter(boolean include) {
        this.include = include;
    }

    @Override
    public boolean include(Preferences preferences, Job job) {
        final String title = getSimplifiedTitle(job.getTitle());
        ;

        if (preferences.getProgrammingLanguages().stream().anyMatch(l ->
                title.contains(l.toLowerCase()))) {
            return true;
        }
        if (preferences.getProgrammingLanguages().stream().anyMatch(l ->
                job.getSkills().contains(l))) {
            return true;
        }
        if (!include) {


            if (titleContainsUnknownLanguage(job.getTitle())) {
                return false;
            }

            //don't reject shared lanaguages with conjunctions in the title
            if (!title.contains(" and ") && !title.contains("/")) {
                if (sharedLanguages.stream().anyMatch(l -> title.contains(l.toLowerCase() + " engineer"))) {
                    return false;
                }

                if (sharedLanguages.stream().anyMatch(l -> title.contains(l.toLowerCase() + " developer"))) {
                    return false;
                }
            }


            if (job.getDescription() != null) {
                final String description = job.getDescription().toLowerCase();
                if (languages.stream().anyMatch(l -> description.contains("strong knowledge of " + l.toLowerCase()))) {
                    return false;
                }

                if (languages.stream().anyMatch(l -> description.contains("advanced knowledge of " + l.toLowerCase()))) {
                    return false;
                }

                if (containsUnknownLanguage(description, preferences)) {
                    return false;
                }

                if (notEnoughExperience(description, preferences)) {
                    return false;
                }


            }
        }

        return !include;
    }

    public boolean titleContainsUnknownLanguage(String tempTitle) {
        final String title = getSimplifiedTitle(tempTitle);
        ;
        if (languages.stream().anyMatch(l -> title.contains(l.toLowerCase() + " developer"))
                || titleOnlyLanguages.stream().anyMatch(l -> title.contains(l.toLowerCase() + " developer"))) {
            return true;
        }

        if (languages.stream().anyMatch(l -> title.endsWith("- " + l.toLowerCase()))
                || titleOnlyLanguages.stream().anyMatch(l -> title.endsWith("- " + l.toLowerCase()))) {
            return true;
        }


        if (languages.stream().anyMatch(l -> title.contains("(" + l.toLowerCase() + ")"))
                || titleOnlyLanguages.stream().anyMatch(l -> title.contains("(" + l.toLowerCase() + ")"))) {
            return true;
        }
        return false;
    }

    private static String getSimplifiedTitle(String originalCasedTitle) {
        originalCasedTitle = originalCasedTitle.toLowerCase().replaceAll("- remote", "");
        originalCasedTitle = originalCasedTitle.replaceAll("web developer", "developer");
        originalCasedTitle = originalCasedTitle.replaceAll("backend developer", "developer");
        originalCasedTitle = originalCasedTitle.replaceAll("backend api developer", "developer");
        originalCasedTitle = originalCasedTitle.replaceAll("api backend developer", "developer");
        originalCasedTitle = originalCasedTitle.replaceAll("application developer", "developer");
        originalCasedTitle = originalCasedTitle.replaceAll("software engineer", "developer");
        originalCasedTitle = originalCasedTitle.replaceAll("engineer", "developer");
        originalCasedTitle = originalCasedTitle.replaceAll("programmer", "developer");
        return originalCasedTitle;
    }


    public boolean containsUnknownLanguage(String descriptionLower, Preferences preferences) {
        List<String> expertPatterns = List.of("expert"+getUntilNextBoundary(), "strong[^\\d\\)\\.\\;]*");
        List<String> knowledgePatterns = List.of("proficien"+getUntilNextBoundary(), "fluency[^\\d\\)\\.\\;]*"
                , "fluent[^\\d\\)\\.\\;]*", "with"+getUntilNextBoundary());
        //we don't want using or with as those don't demand deep knowledge and may not be strictly required
        boolean contains = false;
        if (expertPatterns.stream().anyMatch(p -> containsUnknownLanguage(p, descriptionLower, preferences, true))) {
            contains = true;
        } else if (knowledgePatterns.stream().anyMatch(p -> containsUnknownLanguage(p, descriptionLower, preferences, false))) {
            contains = true;
        }
        return contains;
    }

    public boolean containsUnknownLanguage(String patternText, String descriptionLower, Preferences preferences, boolean expert) {
        Optional<String> firstLanguage = preferences.getProgrammingLanguages().stream().filter(l ->
                RegexUtil.matchesPattern(descriptionLower,
                        patternText + l.toLowerCase())).findFirst();
        boolean containsUnknownLanguage = false;
        if (firstLanguage.isPresent()) {
            if (!firstLanguage.get().equalsIgnoreCase("Java")) {
                containsUnknownLanguage = false;
            } else if (matchesWholeJava(descriptionLower, patternText)) {
                containsUnknownLanguage = false;
            }
        }
        if (languages.stream().anyMatch(l ->
                isPresentAndImportant(descriptionLower,  patternText +
                        prepareLanguageForRegularExpression(l), expert))) {
            containsUnknownLanguage = true;
        }
        if (sharedLanguages.stream().anyMatch(l ->
                RegexUtil.matchesPattern(descriptionLower, patternText +
                        prepareLanguageForRegularExpression(l)))) {
            containsUnknownLanguage = true;
        }
        return containsUnknownLanguage;
    }

    private boolean matchesWholeJava(String descriptionLower, String patternText) {
        return RegexUtil.matchesPattern(descriptionLower, patternText + "Java ")
                || RegexUtil.matchesPattern(descriptionLower, patternText + "Java\\.")
                || RegexUtil.matchesPattern(descriptionLower, patternText + "Java\\)")
                || RegexUtil.matchesPattern(descriptionLower,
                patternText + "Java/") || RegexUtil.matchesPattern(descriptionLower,
                patternText + "Java,") || RegexUtil.matchesPattern(descriptionLower,
                patternText + "Java;");
    }

    private static String prepareLanguageForRegularExpression(String l) {
        return l.toLowerCase().replaceAll("\\+", "\\\\+").replaceAll("\\.", "\\\\.");
    }


    public static boolean notEnoughExperience(String descriptionLower, Preferences preferences) {
        boolean notEnoughExperience = false;
        List<String> patterns = List.of("experience[^\\d\\)\\.\\;]*(\\d*)"
                , "(\\d+)[\\+]* years[^\\d\\)\\.\\;]*experience[^\\d\\)\\.\\;]*");
        Integer experience;
        for (String patternText : patterns) {
            if (preferences.getProgrammingLanguages().stream().anyMatch(l -> RegexUtil.getIntegerValue(descriptionLower, patternText + l.toLowerCase()) != null)) {
                continue;
            }
            if (languages.stream().anyMatch(l -> RegexUtil.getIntegerValue(descriptionLower,
                    patternText + prepareLanguageForRegularExpression(l), 0) > preferences.getMaxYearsOfExperienceForUnlistedSkill())) {
                notEnoughExperience = true;
                break;
            }
            if (sharedLanguages.stream().anyMatch(l -> RegexUtil.getIntegerValue(descriptionLower, patternText + l.toLowerCase(), 0) > preferences.getMaxYearsOfExperienceForUnlistedSkill())) {
                notEnoughExperience = true;
                break;
            }
        }
        return notEnoughExperience;
    }
}

