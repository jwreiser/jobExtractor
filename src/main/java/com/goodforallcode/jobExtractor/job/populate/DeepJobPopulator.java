package com.goodforallcode.jobExtractor.job.populate;

import com.goodforallcode.jobExtractor.model.Job;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriver;

public interface DeepJobPopulator {
    void populateJob(Job job,WebDriver driver);
}
