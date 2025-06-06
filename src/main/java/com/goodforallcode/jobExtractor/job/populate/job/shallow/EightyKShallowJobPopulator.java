package com.goodforallcode.jobExtractor.job.populate.job.shallow;

import com.goodforallcode.jobExtractor.job.populate.field.FieldPopulator;
import com.goodforallcode.jobExtractor.job.populate.job.DeepJobPopulator;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.DateUtil;
import com.goodforallcode.jobExtractor.util.LocationUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeoutException;

public class EightyKShallowJobPopulator implements ShallowJobPopulator {

    public Job populateJob(Element item, WebDriver driver, Preferences preferences,Integer jobIndex) throws TimeoutException {

        Element companyAndTitleDiv=item.getElementsByClass("items-start").first();
        Elements companySpans=companyAndTitleDiv.getElementsByTag("span");

        String title = companySpans.first().text();
        String companyName = companySpans.get(1).text();



        String description = item.getElementsByClass("html-text").text();

        String jobUrl  = null;
        try {
            List<WebElement> jobLinks = driver.findElements(By.linkText("VIEW JOB DETAILS"));
            if(!jobLinks.isEmpty()) {
                WebElement jobLink=jobLinks.get(0);
                jobUrl = jobLink.getAttribute("href");
            }
        }catch (NoSuchElementException e) {
            System.err.println("Could not find job link for item: " + title + " at company: " + companyName);
        }


        Job job = new Job(title, companyName, jobUrl,jobUrl,description);
        for(FieldPopulator fieldPopulator: DeepJobPopulator.fieldPopulators){
            fieldPopulator.populateField(job,preferences);
        }
        return job;

    }


}
