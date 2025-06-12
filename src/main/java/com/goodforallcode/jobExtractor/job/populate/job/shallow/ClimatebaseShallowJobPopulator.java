package com.goodforallcode.jobExtractor.job.populate.job.shallow;

import com.goodforallcode.jobExtractor.job.populate.field.FieldPopulator;
import com.goodforallcode.jobExtractor.job.populate.job.deep.DeepJobPopulator;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.DateUtil;
import com.goodforallcode.jobExtractor.util.LocationUtil;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeoutException;

public class ClimatebaseShallowJobPopulator implements ShallowJobPopulator {

    public Job populateJob(WebElement webElement, Element item, WebDriver driver, Preferences preferences, Integer jobIndex) throws TimeoutException {
        String jobUrl = webElement.getAttribute("href");

        WebElement mainDiv = webElement.findElements(By.className("Card_Information__HkDL9")).get(0);
        WebElement titleSpan = mainDiv.findElements(By.tagName("span")).get(0);
        String title = titleSpan.getText();

        WebElement ul = mainDiv.findElements(By.tagName("ul")).get(0);
        List<WebElement> list = ul.findElements(By.tagName("li"));
        String companyName = list.get(0).getText();

        WebElement descriptionP = mainDiv.findElements(By.tagName("p")).get(0);
        String description= null;
        if (descriptionP != null) {
            description= descriptionP.getText();
        }





        Job job = new Job(title, companyName, jobUrl, jobUrl,description);

        WebElement dateDiv = mainDiv.findElements(By.tagName("div")).get(0);
        if(dateDiv!=null) {
            DateUtil.setDateInformation(dateDiv.getText(), job);
        }
        String locationString = list.get(1).getText();
        LocationUtil locationUtil = new LocationUtil();
        locationUtil.addLocation(locationString, job);

        String remoteString = list.get(2).getText();
        if(remoteString.toLowerCase().contains("remote")) {
            job.setFullyRemote(true);
        } else {
            job.setFullyRemote(false);
        }

        for (FieldPopulator fieldPopulator : DeepJobPopulator.fieldPopulators) {
            fieldPopulator.populateField(job, preferences);
        }
        return job;

    }




}
