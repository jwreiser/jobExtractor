package com.goodforallcode.jobExtractor.job.populate.job.shallow;

import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeoutException;

public interface ShallowJobPopulator {
    Job populateJob(WebElement webElement,Element element, WebDriver driver, Preferences preferences, Integer jobIndex) throws TimeoutException;
}
