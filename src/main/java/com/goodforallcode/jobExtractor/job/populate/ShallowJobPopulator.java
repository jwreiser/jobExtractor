package com.goodforallcode.jobExtractor.job.populate;

import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeoutException;

public interface ShallowJobPopulator {
    Job populateJob(Element item, WebDriver driver, Preferences preferences) throws TimeoutException;
}
