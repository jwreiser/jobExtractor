package com.goodforallcode.jobExtractor.job.populate;

import com.goodforallcode.jobExtractor.job.populate.job.LinkedInDeepJobPopulator;
import com.goodforallcode.jobExtractor.model.Job;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedInDeepJobPopulatorTest {
    LinkedInDeepJobPopulator populator =new LinkedInDeepJobPopulator();
    @Test
    void testUpTo(){
        assertEquals(30,LinkedInDeepJobPopulator.getTravelPercentage("travel up to 30%"));
        assertEquals(42,LinkedInDeepJobPopulator.getTravelPercentage("travel: up to 42%"));

        assertEquals(3,LinkedInDeepJobPopulator.getTravelPercentage("travel up to 3%"));
        assertEquals(100,LinkedInDeepJobPopulator.getTravelPercentage("travel up to 100%"));

    }
    @Test
    void testAddRecruitingClient(){
        Job test=new Job();
        test.setCompanyName("Not Useful Name");
        test.setDescription("Introduction stuff our client, an education learning model company creating complex, scalable solutions for students and teachers that recognize the fact that each student has unique strengths and needs. they develop digital products and innovative learning models that meet students where they are and connect them to where they need to be.");
        test.setIndustries(List.of("Staffing services"));
        assertNull(populator.addRecruitingClient(test));
        test.setDescription("Introduction stuff our client, short but sweet text spaces, scalable solutions for students and teachers that recognize the fact that each student has unique strengths and needs. they develop digital products and innovative learning models that meet students where they are and connect them to where they need to be.");
        assertNull(populator.addRecruitingClient(test));
        test.setDescription("Introduction stuff Our client, Beacon Hill Staffing Group, is seeking the following");

        assertEquals("beacon hill staffing group",populator.addRecruitingClient(test));

    }
    @Test
    void testPercentTravel(){
        assertEquals(30,LinkedInDeepJobPopulator.getTravelPercentage("30% travel"));
        assertEquals(3,LinkedInDeepJobPopulator.getTravelPercentage("3% travel"));
        assertEquals(100,LinkedInDeepJobPopulator.getTravelPercentage("100% travel"));

    }

    @Test
    void testTravelPercent(){
        assertEquals(33,LinkedInDeepJobPopulator.getTravelPercentage("travel: 33%"));
        assertEquals(43,LinkedInDeepJobPopulator.getTravelPercentage("travel:43%"));

        assertEquals(30,LinkedInDeepJobPopulator.getTravelPercentage("travel 30%"));
        assertEquals(3,LinkedInDeepJobPopulator.getTravelPercentage("travel 3%"));
        assertEquals(100,LinkedInDeepJobPopulator.getTravelPercentage("travel 100%"));

    }

    @Test
    void testTravelLessThan(){
        assertEquals(33,LinkedInDeepJobPopulator.getTravelPercentage("travel less than 33%"));
        assertEquals(4,LinkedInDeepJobPopulator.getTravelPercentage("travel less than 4%"));

    }

    @Test
    void testWithRandomWords(){
        assertEquals(25,LinkedInDeepJobPopulator.getTravelPercentage("travel regionally as needed (up to 20-25% per quarter)"));
        assertEquals(23,LinkedInDeepJobPopulator.getTravelPercentage("travel regionally as needed (up to 23% per quarter)"));
        assertEquals(35,LinkedInDeepJobPopulator.getTravelPercentage("travel regionally as needed 35% per quarter."));
        assertEquals(33,LinkedInDeepJobPopulator.getTravelPercentage("travel regionally as needed upt to 33%,"));
        assertEquals(32,LinkedInDeepJobPopulator.getTravelPercentage("travel regionally as needed up to 32%;"));
//bad punct
        assertEquals(null,LinkedInDeepJobPopulator.getTravelPercentage("travel regionally as needed. 35% per quarter"));
        assertEquals(null,LinkedInDeepJobPopulator.getTravelPercentage("travel regionally as needed; 35% per quarter"));
        assertEquals(null,LinkedInDeepJobPopulator.getTravelPercentage("travel regionally as needed, 35% per quarter"));
        assertEquals(null,LinkedInDeepJobPopulator.getTravelPercentage("travel regionally as needed) 35% per quarter"));

    }
}
