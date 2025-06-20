package com.goodforallcode.jobExtractor.filters.custom;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.RegexUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * There can be multiple entries for a rank as I will include some other
 * big countries such as India
 */
public class FortuneRankFilter implements JobFilter {
    static Map<String,Integer> ranks=new HashMap<>();

    static {
        ranks.put("Autodesk",648);
        ranks.put("Motorola Solutions",423);
        ranks.put("McKesson",9);
        ranks.put("McDonald’s",152);
        ranks.put("Wipro",2);
        ranks.put("Fedex",41);
        ranks.put("Infosys",3);
        ranks.put("Oracle",91);
    }

    private List<String> companiesThatCanBeInDescription=List.of("Autodesk","Oracle");
    @Override
    public boolean include(Preferences preferences, Job job) {
        boolean include=true;
        if(preferences==null || preferences.getMaxFortuneRank()==null) {
            return include;
        }
        for (Map.Entry<String, Integer> entry : ranks.entrySet()) {
            if(entry.getKey().equals(job.getCompanyName())){
                if(entry.getValue()<preferences.getMaxFortuneRank()){
                    include=false;
                }
                break;
            }
        }
        if(job.getDescription()!=null){
            final String descriptionLower= job.getDescription().toLowerCase();
            include = includeDescription(descriptionLower,preferences);
        }
        if(job.getCompany()!=null && job.getCompany().getFortuneRanking()!=null && job.getCompany().getFortuneRanking()<preferences.getMaxFortuneRank()){
            System.err.println("summary rank "+job.getCompany().getFortuneRanking()+" ->reject: " + job);
            include=false;
        }
        return  include;
    }

    public boolean includeDescription(String descriptionLower,Preferences preferences) {
        boolean include=true;

        if(ranks.keySet().stream().anyMatch(company->containsUnsafeCompany(descriptionLower,company,companiesThatCanBeInDescription))){
            include =false;
        }
        Integer rank=RegexUtil.getIntegerValue(descriptionLower,"fortune[^\\d]*(\\d*)[^\\d]*client");
        if(rank!=null && preferences!=null&&preferences.getMaxFortuneRank()!=null && rank<preferences.getMaxFortuneRank()){
            return false;
        }

        return include;
    }

    public boolean containsUnsafeCompany(String descriptionLower,String companyName,List<String> companiesThatCanBeInDescription){
        if(companiesThatCanBeInDescription.contains(companyName)){
            return false;
        }else if(descriptionLower.contains(companyName.toLowerCase())){
            return true;
        }
        return false;

    }
}
