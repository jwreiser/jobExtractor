package com.goodforallcode.jobExtractor.filters.shallow.company;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * There can be multiple entries for a rank as I will include some other
 * big countries such as India
 */
public class FortuneRankFilter implements JobFilter {
    static Map<String,Integer> ranks=new HashMap<>();
    {
        ranks.put("Autodesk",648);
        ranks.put("McKesson",9);
        ranks.put("Wipro",2);
        ranks.put("Infosys",3);
        ranks.put("Oracle",91);

    }

    @Override
    public boolean include(Preferences preferences, Job job) {
        boolean include=true;
        if(preferences!=null&&preferences.getMinFortuneRank()!=null) {
            for (Map.Entry<String, Integer> entry : ranks.entrySet()) {
                if(entry.getKey().equals(job.getCompanyName())){
                    if(entry.getValue()<preferences.getMinFortuneRank()){
                        System.err.println("rank "+entry.getValue()+" ->reject: " + job);
                        include=false;
                    }
                    break;
                }
            }
        }
        return  include;
    }
}
