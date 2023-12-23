package com.goodforallcode.jobExtractor.job.populate;

import com.goodforallcode.jobExtractor.model.Job;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkedInShallowJobPopulatorTest {
    LinkedInShallowJobPopulator populator =new LinkedInShallowJobPopulator();
    @Test
    void testAddLevel_L(){
        Job job=new Job("Software Engineer (L3)");
        LinkedInShallowJobPopulator.addLevel("Software Engineer (L3)",job);
        assertEquals(3,job.getLevel());

    }

}
