package com.goodforallcode.jobExtractor.filter;

import com.goodforallcode.jobExtractor.filters.both.ProgrammingLanguageFilter;
import com.goodforallcode.jobExtractor.filters.both.UnixFilter;
import com.goodforallcode.jobExtractor.util.TestUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnixFilterTests {
    UnixFilter filter =new UnixFilter();
    @Test
    void testDescriptionInclude() {
        assertFalse(filter.descriptionInclude(" Java, java, java!* linux (preferably RedHat) Object Oriented Design/Object Oriented Programming AWS experience is required"));
        assertTrue(filter.descriptionInclude(" Java, java, java!* linux (preferably RedHat) is preferred Object Oriented Design/Object Oriented Programming AWS experience is required"));
        assertFalse(filter.descriptionInclude("Desired Qualifications: • AWS Management • EC2 (redhat linux) • RDS Database Management • Elasticache (Redis) • Load Balancer (ALB/ELB) • Route 53 / DNS"));

    }
}
