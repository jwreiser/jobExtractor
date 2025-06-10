package com.goodforallcode.jobExtractor.job.populate.job.shallow;

import com.goodforallcode.jobExtractor.job.populate.field.FieldPopulator;
import com.goodforallcode.jobExtractor.job.populate.job.deep.DeepJobPopulator;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.DateUtil;
import com.goodforallcode.jobExtractor.util.LocationUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeoutException;

public class NyStateJobsShallowJobPopulator implements ShallowJobPopulator {

    public Job populateJob(WebElement webElement, Element item, WebDriver driver, Preferences preferences, Integer jobIndex) throws TimeoutException {
        List<WebElement> tds = webElement.findElements(By.tagName("td"));
        String companyName = tds.get(5).getText();

        WebElement jobLink = webElement.findElements(By.tagName("a")).get(0);
        if (jobLink == null) {
            return null;
        }
        String jobUrl = jobLink.getAttribute("href");
        String title = jobLink.getText();

        Job job = new Job(title, companyName, jobUrl, jobUrl,title);

        if(preferences.isRemoteOnly()){
            job.setFullyRemote(true);//we can't extract it and rely on the search to do so
        }

        String dateString = tds.get(3).getText();

        if (!dateString.isEmpty()) {
            job.setJobAgeInDays(DateUtil.getAgeInDaysSlashes(dateString));
            job.setPostingDate(DateUtil.getLocalDateSlashes(dateString));
        }

        String locationString = tds.get(6).getText();
        LocationUtil locationUtil = new LocationUtil();
        locationUtil.addLocation(locationString, job);

        for (FieldPopulator fieldPopulator : DeepJobPopulator.fieldPopulators) {
            fieldPopulator.populateField(job, preferences);
        }
        return job;

    }




}
