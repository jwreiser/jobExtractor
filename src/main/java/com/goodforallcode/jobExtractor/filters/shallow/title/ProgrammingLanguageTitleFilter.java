package com.goodforallcode.jobExtractor.filters.shallow.title;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

/**
 * This is designed for languages that usually do not play together
 * So unlike C#, Java and Python which are often shared (especially Python) this focuses on
 * languages like rust and ruby
 */
public class ProgrammingLanguageTitleFilter implements JobFilter {
    List<String> languages=List.of(
            "Ruby","Rust","Go","Golang","Net","DotNet",".Net","iOS","React","Angular","Typescript"
            ,"Javascript","CNO","C#","C++","Visual C","Scala","Swift","Dart"," C"," R","PHP",
            "VB.NET","perl","MATLAB","SAS","COBOL","ABAP","Tcl","Elixir","Erlang","F#","GO Lang",
            "ColdFusion","Genie","Natural","Spark","verilog","MUMPS","GraphQL");
    List<String> sharedLanguages=List.of("Python");
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

            if (languages.stream().anyMatch(l -> title.contains(l.toLowerCase() + " developer"))) {
                System.err.println("language developer ->reject: " + job);
                return false;
            }

            if (languages.stream().anyMatch(l -> title.contains(l.toLowerCase() + " backend developer"))) {
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
        }
        return !include;
    }

}
