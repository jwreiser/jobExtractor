package com.goodforallcode.jobExtractor.filters.deep.always.lang;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class MicrosoftStackFilter implements JobFilter {
    List<String>keywords=List.of("C++","C#");

    List<String>doublePointKeywords=List.of(".net","ASP.net","VB.Net"," WPF ");
    List<String>quadPointKeywords=List.of("Microsoft Stack","Microsoft Visual Studio"
            ,"NET development ecosystem", "NET ecosystem", "NET core",
            "NET framework");

    @Override
    public boolean include(Preferences preferences, Job job) {
        String text =job.getDescription().toLowerCase();
        long singlePointCount=keywords.stream().filter(k->text.contains(k.toLowerCase())).count();
        long doublePointCount=2*doublePointKeywords.stream().filter(k->text.contains(k.toLowerCase())).count();
        long quadPointCount=4*quadPointKeywords.stream().filter(k->text.contains(k.toLowerCase())).count();
        if((singlePointCount+doublePointCount+quadPointCount)>3){
            System.err.println(".Net single point "+singlePointCount
                    +" doublePointCount " +doublePointCount+
                    " quadPointCount " +quadPointCount+
                    " ->reject: "+job);
            return false;
        }
        return true;
    }
}
