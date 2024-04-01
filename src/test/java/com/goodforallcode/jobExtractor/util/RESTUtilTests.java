package com.goodforallcode.jobExtractor.util;

import com.goodforallcode.jobExtractor.model.CompanySummary;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RESTUtilTests {
    @Test
    void testCallURL() throws IOException {
        CompanySummary summary = RESTUtil.callUrl("http://localhost:5000/company/summarize/Walmart");
        assertNotNull(summary);
    }

    }
