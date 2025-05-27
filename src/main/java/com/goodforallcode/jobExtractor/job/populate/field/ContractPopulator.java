package com.goodforallcode.jobExtractor.job.populate.field;
import com.goodforallcode.jobExtractor.filters.ExcludeJobFilter;
import com.goodforallcode.jobExtractor.model.Job;

import java.util.List;

public class ContractPopulator implements FieldPopulator{
    public void populateField(Job job) {
        if (!job.isContract()) {
            ExcludeJobFilter filter=ExcludeJobFilter.build("Contract")
                    .badCompanies(List.of("Accenture", "Guidehouse","Piper Companies"
                            , "Wise Skulls", "Brooksource", "Harnham", "Cypress HCM", "Belcan", "Mindex", "Altimetrik",
                            "Kforce Inc", "Kforce Com", "Oktobor Animation", "Groundswell", "Raft",
                            "NTT DATA Services", "Spatial Front, Inc", "Tential Solutions",
                            "IT Crowd", "Koniag Government Services", "SCIGON", "Latitude Inc", "IT Labs",
                            "AgileEngine", "Bitsoft International, Inc.", "Revature", "Gridiron IT", "mphasis",
                            "Brillio", "GE", "SBS Creatix", "Compunnel Inc."))
                    .titlePhrases(List.of("contract"))
                    .descriptionPhrases(List.of("Federal clients", "supporting a DoD","contract position","government contractor"));
            if( filter.exclude(job)!= null) {
                job.setContract(true);
            }
        }


    }
}
