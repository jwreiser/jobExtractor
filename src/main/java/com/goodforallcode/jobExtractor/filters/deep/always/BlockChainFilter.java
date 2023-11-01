package com.goodforallcode.jobExtractor.filters.deep.always;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class BlockChainFilter implements JobFilter {
    List<String>keywords=List.of("Blockchain","Crypto"," NFT "," DeFi ");

    @Override
    public boolean include(Preferences preferences, Job job) {
        if(!preferences.isExcludeBlockchain()){
            return true;
        }
        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            if (keywords.stream().anyMatch(k -> description.contains(k.toLowerCase()))) {
                System.err.println("blockchain ->reject: " + job);
                return false;
            }
        }
        return true;
    }
}
