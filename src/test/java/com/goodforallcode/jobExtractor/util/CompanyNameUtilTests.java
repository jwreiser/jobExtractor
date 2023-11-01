package com.goodforallcode.jobExtractor.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompanyNameUtilTests {
    @Test
    void testContainsCompanyName(){
        assertTrue(CompanyNameUtil.containsCompanyName("Ascendion",
                "Our client, Ascendion Inc., is seeking the following"));

    }

}
