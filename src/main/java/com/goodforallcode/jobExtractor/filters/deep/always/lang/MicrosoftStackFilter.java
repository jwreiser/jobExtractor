package com.goodforallcode.jobExtractor.filters.deep.always.lang;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class MicrosoftStackFilter implements JobFilter {
    List<String> singlePointKeywords =List.of("C++","C#");

    List<String>doublePointKeywords=List.of(".net","ASP.net","VB.Net"," WPF ","WinForms");
    List<String>quadPointKeywords=List.of("Microsoft Stack","Microsoft Visual Studio"
            ,"NET development ecosystem", "NET ecosystem", "NET core",
            "NET framework");

    @Override
    public boolean include(Preferences preferences, Job job) {
        final String title=job.getTitle().toLowerCase();
        if(singlePointKeywords.stream().anyMatch(k->title.contains(k.toLowerCase()))){
            System.err.println("Microsoft stack single point title ->reject: " + job);
            return false;
        }
        if(doublePointKeywords.stream().anyMatch(k->title.contains(k.toLowerCase()))){
            System.err.println("Microsoft stack double point title ->reject: " + job);
            return false;
        }
        if(job.getDescription()!=null) {
            String text = job.getDescription().toLowerCase();
            long singlePointCount = singlePointKeywords.stream().filter(k -> text.contains(k.toLowerCase())).count();
            long doublePointCount = 2 * doublePointKeywords.stream().filter(k -> text.contains(k.toLowerCase())).count();
            long quadPointCount = 4 * quadPointKeywords.stream().filter(k -> text.contains(k.toLowerCase())).count();
            if ((singlePointCount + doublePointCount + quadPointCount) > 3) {
                System.err.println(".Net single point " + singlePointCount
                        + " doublePointCount " + doublePointCount +
                        " quadPointCount " + quadPointCount +
                        " ->reject: " + job);
                return false;
            }
        }
        return true;
    }
}
