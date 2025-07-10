package com.goodforallcode.jobExtractor.job.populate.job.shallow;

import com.goodforallcode.jobExtractor.job.populate.field.FieldPopulator;
import com.goodforallcode.jobExtractor.job.populate.job.deep.DeepJobPopulator;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.LocationUtil;
import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeoutException;

public class GlassdoorShallowJobPopulator implements ShallowJobPopulator {
    LocationUtil locationUtil = new LocationUtil();

    public Job populateJob(WebElement webElement, Element item, WebDriver driver, Preferences preferences, Integer jobIndex) throws TimeoutException {
        //i think order is more likely to be constant than the autogend class name
        List<WebElement> referringLinks = webElement.findElements(By.tagName("a"));
        WebElement referringLink = null;
        if (referringLinks != null && !referringLinks.isEmpty()) {
            referringLink = referringLinks.get(0);
        }
        if (referringLink == null) {
            return null;
        }

        String text;
        WebElement companyElement = webElement.findElement(By.tagName("h4"));
        String companyName = null;
        if (companyElement != null) {
            companyName = companyElement.getText();
        }

        String jobUrl = referringLink.getAttribute("href");
        WebElement titleElement = webElement.findElement(By.tagName("h1"));
        String title = null;
        if (titleElement != null) {
            title = titleElement.getText();
        }

        String description = null;
        WebElement descriptionElement = webElement.findElement(By.className("JobDetails_jobDescription__uW_fK"));
        if (descriptionElement != null) {
            description = descriptionElement.getText();
        }

        Job job = new Job(title, companyName, jobUrl, jobUrl, description);



        WebElement ratingElement = webElement.findElement(By.className("rating-single-star_RatingText__XENmU"));
        if (ratingElement != null) {
            String ratingText = ratingElement.getText();
            if (ratingText != null && !ratingText.isEmpty()) {
                try {
                    Double rating = Double.parseDouble(ratingText);
                    job.setEmployerRating(rating);
                } catch (NumberFormatException ex) {
                    //ignore
                }
            }
        }

        WebElement locationElement = webElement.findElement(By.xpath("//div[@data-test='location']"));
        if (locationElement != null) {
            String location = locationElement.getText();
            if (location != null && !location.isEmpty()) {
                if (location.equalsIgnoreCase("Remote")) {
                    job.setFullyRemote(true);
                }
                locationUtil.addLocation(location, job);
            }
        }

        //using data-test gave stale information for salary
        WebElement salaryAndLocationElement = webElement.findElement(By.className("JobDetails_locationAndPay__XGFmY"));
        if(salaryAndLocationElement!= null) {
            List<WebElement> salaryAndLocationDivs = salaryAndLocationElement.findElements(By.tagName("div"));
            if(salaryAndLocationDivs != null && salaryAndLocationDivs.size()==3) {
                WebElement salaryElement = salaryAndLocationDivs.get(2);
                if (salaryElement != null) {
                    String salary = salaryElement.getText();
                    populateSalaryInformation(job, salary);
                }
            }
        }



        for (FieldPopulator fieldPopulator : DeepJobPopulator.fieldPopulators) {
            fieldPopulator.populateField(job, preferences);
        }
        return job;

    }


}
