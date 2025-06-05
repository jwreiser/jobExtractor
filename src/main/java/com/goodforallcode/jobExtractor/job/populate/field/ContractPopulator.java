package com.goodforallcode.jobExtractor.job.populate.field;

import com.goodforallcode.jobExtractor.filters.ExcludeJobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;
import java.util.Optional;

public class ContractPopulator implements FieldPopulator {
    public void populateField(Job job, Preferences preferences) {
        if (contractNotSetToTrue(job)) {
            if (job.getDescription() != null && job.getDescription().length() > 0) {
                Optional<Integer> contractDuration = getContractDuration(job.getDescription().toLowerCase());
                if (contractDuration.isPresent()) {//always do this so we can set the contract duration
                    job.setContractMonths(contractDuration.get());
                    job.setContract(true);
                }
            }
            if (contractNotSetToTrue(job)) {
                ExcludeJobFilter filter = ExcludeJobFilter.build("Contract")
                        .badCompanies(List.of("Accenture", "Guidehouse", "Piper Companies"
                                , "Wise Skulls", "Brooksource", "Harnham", "Cypress HCM", "Belcan", "Mindex", "Altimetrik",
                                "Kforce Inc", "Kforce Com", "Oktobor Animation", "Groundswell", "Raft",
                                "NTT DATA Services", "Spatial Front, Inc", "Tential Solutions",
                                "IT Crowd", "Koniag Government Services", "SCIGON", "Latitude Inc", "IT Labs",
                                "AgileEngine", "Bitsoft International, Inc.", "Revature", "Gridiron IT", "mphasis",
                                "Brillio", "SBS Creatix", "Compunnel Inc."))
                        .badCompaniesStartsWith(List.of("GE "))
                        .titlePhrases(List.of("contract"))
                        .descriptionPhrases(List.of("Federal clients", "supporting a DoD", "contract position", "government contractor", "+ Month Contract"
                                , "+ Months Contract","This is a contract", "contractor position", "contract role", "contractor role", "contract opportunity"));
                if (filter.exclude(job) != null) {
                    job.setContract(true);
                }
            }
        }


    }

    private static boolean contractNotSetToTrue(Job job) {
        return job.getContract() == null || !job.getContract();
    }

    private static Optional<Integer> getContractDuration(String description) {
        int start;
        Optional result = Optional.empty();
        int end = description.indexOf("+ months");
        if (end <= 0) {
            end = description.indexOf("+ month contract");
        }
        if (end <= 0) {
            end = description.indexOf("+months");
        }
        if (end > 0) {
            start = description.indexOf(" ", end - 5);
            String base = description.substring(start, end + 1);

            String experience = removeNonNumericText(base);
            if (NumberUtils.isCreatable(experience)) {
                result = Optional.of(Integer.parseInt(experience));
            }
        }
        return result;
    }

    private static String removeNonNumericText(String base) {
        return base.replaceAll("\\+", "")
                .replaceAll("<!---->", "").replaceAll(":", "").trim();
    }
}
