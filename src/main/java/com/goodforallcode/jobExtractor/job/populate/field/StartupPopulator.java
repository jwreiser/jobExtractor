package com.goodforallcode.jobExtractor.job.populate.field;
import com.goodforallcode.jobExtractor.filters.ExcludeJobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;
import java.util.Optional;

public class StartupPopulator implements FieldPopulator{

    /**
     * Exceptions
     * Bonus : Could be a bonus plan for referrals
     * investors: can have investors but still be a PBC
     * funded:      could refer to insurance
     * funding:     could refer to contracts
     * ventures:    can return to internal program like a mentorship joint ventures one
     *evaluations
     * examples
     * venture-backed
     */
    public void populateField(Job job, Preferences preferences) {

                ExcludeJobFilter filter = ExcludeJobFilter.build("Startup")
                        .badCompanies(List.of("Patterned Learning AI", "Patterned Learning AI",
                                "Patterned Learning Career", "minware", "Included Health", "Valon",
                                "Storm 3", "Storm 4", "Storm 5", "Storm 6","Truehold",
                                "Nira Energy", "Apploi", "Convictional", "Bramkas",
                                "Ascendion", "WellSaid Labs", "Alma", "Maven Clinic", "hims & hers", "Amberflo.io",
                                "AllVoices", "Certificial", "Rutter", "Hazel Health", "AIQ (Alpine IQ)", "Jerry",
                                "Underdog.io", "ONE", "Apexon", "Docugami", "Clerkie", "Human Interest",
                                "CornerUp", "Cloudbeds", "SandboxAQ", "Fitness Matrix Inc", "Sight Machine",
                                "Offered.ai", "SpectrumAi", "Numerated", "Avid Technology Professionals", "TherapyNotes, LLC"))
                        .descriptionPhrases(List.of("seed-stage", "y combinator", "backed", " early stage", "early-stage", "raised funding ",
                                " VC", "investors", "pre-seed", " valuation", "founding", "Startup-Like Environment", "Startup Environment"))
                        .safeDescriptionPhrases(List.of("public benefit corporation", "PBC", "founding principle"))
                        .titleAndDescriptionPhrases(List.of("startup", "start-up", " start up ","startup"
                                , "B corp", "Series A", "Series B", "foundational", "scale-up"))
                        .titlePhrases(List.of("founding", "Founder", "Entrepreneur"))
                        .testForCompanyInDescription(true);

                if (filter.exclude(job) != null) {
                    job.setStartUp(true);
                }
            }



    }

