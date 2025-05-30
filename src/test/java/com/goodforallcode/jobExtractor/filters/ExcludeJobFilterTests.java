package com.goodforallcode.jobExtractor.filters;

import com.goodforallcode.jobExtractor.model.Job;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ExcludeJobFilterTests {
    @Test
    void testDevSecOps() {
        boolean exclude = false;
        ExcludeJobFilter filter = ExcludeJobFilter.build("DevSecOps")
                .titlePhrases(List.of("Datacenter Engineer", "Devop", "Devsecop",
                        "Release Engineer", "Build", "Dev ops", "Devops",
                        " IT Engineer", "Information Technology Engineer",
                        "devsecops", "Monitoring", "VDI ",
                        "Infrastructure", "Site Reliability", "SRE", "Reliability Engineer", "Observability",
                        "Operations", " Ops Engineer", "CSfC Engineer",
                        "Information assurance", "Integration Engineer", "Release Management",
                        "Platform Engineer", "C3ISR", "SysOps", "Kubernetes", "Delivery", "Configuration", "Deployment"))
                .safeTitlePhrases(List.of("Executive Operations"))
                .descriptionPhrasesAndCount(List.of("network", "security", "install",
                        "VMware", "Servers", "Sccm", "administration", "administer",
                        "configuration management", " configure", " deploy", "maintain", "setup"), 3)
                .titleAndDescriptionPhrases(List.of("Tibco"));
        Job job = new Job("Executive Operations & IT CoordinatorExecutive Operations & IT Coordinator");
        if (filter.exclude(job) != null) {
            exclude = true;
        }
        assertFalse(exclude);
    }
}
