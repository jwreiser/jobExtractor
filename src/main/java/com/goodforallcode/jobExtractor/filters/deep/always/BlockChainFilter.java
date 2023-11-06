package com.goodforallcode.jobExtractor.filters.deep.always;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class BlockChainFilter implements JobFilter {
    List<String>keywords=List.of("Blockchain","Crypto"," NFT "," DeFi ");
    List<String>companyNames=List.of("CryptoRecruit","Trust Machines");


    @Override
    public boolean include(Preferences preferences, Job job) {
        if(!preferences.isExcludeBlockchain()){
            return true;
        }
        if(companyNames.stream().anyMatch(c->c.equals(job.getCompanyName()))){
            System.err.println("blockchain company name->reject: " + job);
            return false;
        }
        final String title= job.getTitle().toLowerCase();
        if (keywords.stream().anyMatch(k -> title.contains(k.toLowerCase()))) {
            System.err.println("blockchain title ->reject: " + job);
            return false;
        }
        if(job.getDescription()!=null) {
            String description = job.getDescription().toLowerCase();
            if (keywords.stream().anyMatch(k -> description.contains(k.toLowerCase()))) {
                System.err.println("blockchain description ->reject: " + job);
                return false;
            }
        }
        return true;
    }
}
