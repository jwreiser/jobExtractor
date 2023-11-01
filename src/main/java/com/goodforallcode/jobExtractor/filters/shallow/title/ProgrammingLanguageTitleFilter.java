package com.goodforallcode.jobExtractor.filters.shallow.title;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.RegexUtil;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is designed for languages that usually do not play together
 * So unlike C#, Java and Python which are often shared (especially Python) this focuses on
 * languages like rust and ruby
 */
public class ProgrammingLanguageTitleFilter implements JobFilter {
    private static List<String> languages=List.of(
            "Ruby","Rust","Go","Golang","Net","DotNet",".Net","iOS","React","Angular","Typescript"
            ,"Javascript","CNO","C#","C++","Visual C","Scala","Swift","Dart"," C"," R","PHP",
            "VB.NET","perl","MATLAB","SAS","COBOL","ABAP","Tcl","Elixir","Erlang","F#","GO Lang",
            "ColdFusion","Genie","Natural","Spark","verilog","MUMPS","GraphQL","R Shiny","Node","NodeJS",
            "Django","RoR","Haskell");

    static List<String> sharedLanguages=List.of("Python");
    boolean include;

    public ProgrammingLanguageTitleFilter(boolean include) {
        this.include = include;
    }

    @Override
    public boolean include(Preferences preferences, Job job) {
        String title=job.getTitle().toLowerCase();
        if(preferences.getProgrammingLanguages().stream().anyMatch(l->job.getTitle().contains(l.toLowerCase()))){
            return true;
        }
        if(!include) {
            if (languages.stream().anyMatch(l -> title.contains(l.toLowerCase() + " engineer"))) {
                System.err.println("language engineer: title ->reject: " + job);
                return false;
            }

            if (languages.stream().anyMatch(l -> title.contains(l.toLowerCase() + " software engineer"))) {
                System.err.println("language software engineer: title ->reject: " + job);
                return false;
            }
            if (languages.stream().anyMatch(l -> title.contains(l.toLowerCase() + " developer"))) {
                System.err.println("language developer ->reject: " + job);
                return false;
            }

            if (languages.stream().anyMatch(l -> title.contains(l.toLowerCase() + " backend developer"))) {
                System.err.println("language developer ->reject: " + job);
                return false;
            }

            if (languages.stream().anyMatch(l -> title.contains(l.toLowerCase() + " api backend developer"))) {
                System.err.println("language developer ->reject: " + job);
                return false;
            }

            if (languages.stream().anyMatch(l -> title.endsWith("- "+l.toLowerCase()))) {
                System.err.println("language developer ->reject: " + job);
                return false;
            }

            if (languages.stream().anyMatch(l -> title.contains(l.toLowerCase() + " programmer"))) {
                System.err.println("language programmer ->reject: " + job);
                return false;
            }

            if (languages.stream().anyMatch(l -> title.contains(l.toLowerCase() + " application developer"))) {
                System.err.println("language application developer ->reject: " + job);
                return false;
            }

            if (languages.stream().anyMatch(l -> title.contains("("+l.toLowerCase() + ")"))) {
                System.err.println("language solo skill ->reject: " + job);
                return false;
            }

            if(title.contains(" and ")||title.contains("/")){
                //don't reject shared lanaguages with conjunctions in the title
                return true;
            }

            if (sharedLanguages.stream().anyMatch(l -> title.contains(l.toLowerCase() + " engineer"))) {
                System.err.println("shared language engineer ->reject: " + job);
                return false;
            }

            if (sharedLanguages.stream().anyMatch(l -> title.contains(l.toLowerCase() + " developer"))) {
                System.err.println("shared language developer ->reject: " + job);
                return false;
            }

            if(job.getDescription()!=null){
                final String description=job.getDescription().toLowerCase();
                if (languages.stream().anyMatch(l -> description.contains("strong knowledge of "+l.toLowerCase()))) {
                    System.err.println("strong knowledge pf ->reject: " + job);
                    return false;
                }

                if (languages.stream().anyMatch(l -> description.contains("advanced knowledge of "+l.toLowerCase()))) {
                    System.err.println("advanced knowledge of ->reject: " + job);
                    return false;
                }

                if (containsUnknownLanguage(description,preferences)) {
                    System.err.println("language contains unknown->reject: " + job);
                    return false;
                }

                if (notEnoughExperience(description,preferences)) {
                    System.err.println("language notEnoughExperience ->reject: " + job);
                    return false;
                }


            }
        }
        return !include;
    }

    public static boolean containsUnknownLanguage(String descriptionLower,Preferences preferences) {
        List<String> patterns=List.of("proficiency[^\\d\\)\\.\\;]*");
        boolean contains=false;
        for(String patternText: patterns) {
            if(preferences.getProgrammingLanguages().stream().anyMatch(l->
                    RegexUtil.matchesPattern(descriptionLower,patternText+l.toLowerCase()))){
                continue;
            }
            if(languages.stream().anyMatch(l->RegexUtil.matchesPattern(descriptionLower,patternText+l.toLowerCase()))){
                contains=true;
                break;
            }
        }
        return contains;
    }



    public static boolean notEnoughExperience(String descriptionLower,Preferences preferences) {
        boolean notEnoughExperience=false;
        List<String> patterns=List.of("experience[^\\d\\)\\.\\;]*(\\d*)"
        ,"(\\d+)[\\+]* years[^\\d\\)\\.\\;]*experience[^\\d\\)\\.\\;]*");
        Integer experience;
        for(String patternText:patterns) {
            if (preferences.getProgrammingLanguages().stream().anyMatch(l ->RegexUtil.getValue(descriptionLower, patternText+l.toLowerCase())!=null)){
                continue;
            }
            if (languages.stream().anyMatch(l ->RegexUtil.getValue(descriptionLower, patternText+l.toLowerCase(),0) > preferences.getMaxYearsOfExperienceForUnlistedSkill())){
                notEnoughExperience = true;
                break;
            }
            if (sharedLanguages.stream().anyMatch(l ->RegexUtil.getValue(descriptionLower, patternText+l.toLowerCase(),0) > preferences.getMaxYearsOfExperienceForUnlistedSkill())){
                notEnoughExperience = true;
                break;
            }
        }
        return notEnoughExperience;
    }
}

