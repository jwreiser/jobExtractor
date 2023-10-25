package com.goodforallcode.jobExtractor.job.populate;

import com.goodforallcode.jobExtractor.model.Job;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeoutException;

public interface ShallowJobPopulator {
    Job populateJob(Element item, WebDriver driver) throws TimeoutException;
}
