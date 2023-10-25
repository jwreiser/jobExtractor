package com.goodforallcode.jobExtractor.job.populate;

import com.goodforallcode.jobExtractor.model.Job;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeoutException;

public interface DeepJobPopulator {
    void populateJob(Job job,WebDriver driver) throws TimeoutException;
}
