package com.goodforallcode.jobExtractor.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringUtilTests {
    @Test
    void testCompressDescription(){
        String result = StringUtil.compressDescription("About the job LTI (Logic Technology, Inc.) the \\\"Pro People\\\" company ", "");
        assertEquals("lti logic",result);

    }

}
