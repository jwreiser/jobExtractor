package com.goodforallcode.jobExtractor.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompanyUtilTests {
    @Test
    void testDescriptionContainsCompanyName(){
        assertTrue(CompanyUtil.descriptionContainsCompanyName("Ascendion",
                "Our client, Ascendion Inc., is seeking the following"));

    }

}
