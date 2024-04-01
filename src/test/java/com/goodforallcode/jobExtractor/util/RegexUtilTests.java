package com.goodforallcode.jobExtractor.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegexUtilTests {
    @Test
    void testGetValue(){
        assertEquals(5,RegexUtil.getIntegerValue("5 years experience with ruby",
                "(\\d+) years experience[^\\d\\)\\.\\;]*ruby",0));
        assertEquals(5,RegexUtil.getIntegerValue("5 years of development experience with ruby",
                "(\\d+) years[^\\d\\)\\.\\;]*experience[^\\d\\)\\.\\;]*ruby",0));
        assertEquals(5,RegexUtil.getIntegerValue(" 5+ years of development experience with ruby",
                "(\\d+)[\\+]+ years[^\\d\\)\\.\\;]*experience[^\\d\\)\\.\\;]*ruby"),0);

        assertEquals(5,RegexUtil.getIntegerValue("At least 5 years of experience building Python products with a focus on Network Architectures (PPP, routers, modems etc.).".toLowerCase(),
                "(\\d+) years[^\\d\\)\\.\\;]*experience[^\\d\\)\\.\\;]*python"),0);

        assertEquals(5,RegexUtil.getIntegerValue("At least 5 years of experience building Python products with a focus on Network Architectures (PPP, routers, modems etc.).".toLowerCase(),
                "(\\d+)[\\+]* years[^\\d\\)\\.\\;]*experience[^\\d\\)\\.\\;]*python"),0);

    }

}
