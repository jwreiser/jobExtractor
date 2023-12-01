package com.goodforallcode.jobExtractor.filter;

import com.goodforallcode.jobExtractor.filters.shallow.company.FortuneRankFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.util.TestUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FortuneRankFilterTests {
    FortuneRankFilter filter =new FortuneRankFilter();
    @Test
    void testProgrammingLanguageIncluded(){
        Job job=new Job("Entry Level Java Back-End Developer");
        job.setDescription("McDonald’s evolving Accelerating the Arches growth strategy puts our customers and people first, and leverages our competitive advantages to strengthen our brand. We are recognized on lists like Fortune’s Most Admired Companies and Fast Company’s Most Innovative Companies.\n" +
                "\n" +
                "Doubling Down on the 4Ds (Delivery, Digital, Drive Thru, and Development) \n" +
                "\n" +
                "Our growth pillars emphasize the important role technology plays as the leading, global omni-channel restaurant brand. Technology enables the organization through digital technology, and improving the customer, crew and employee experience each and every day.\n");
        assertFalse(filter.include(TestUtil.getDefaultPreferences(),job));
        }

    @Test
    void testIncludeDescription(){
        assertFalse(filter.includeDescription(
                "About the job Marathon TS is seeking a Java Developer for a fortune 30 clientAbout the job Marathon TS is seeking a Java Developer for a fortune 30 client"
                 ,TestUtil.getDefaultPreferences()));
    }
}
