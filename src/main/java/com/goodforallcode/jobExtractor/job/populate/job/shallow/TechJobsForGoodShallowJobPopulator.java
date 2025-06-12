package com.goodforallcode.jobExtractor.job.populate.job.shallow;

import com.goodforallcode.jobExtractor.job.populate.field.FieldPopulator;
import com.goodforallcode.jobExtractor.job.populate.job.deep.DeepJobPopulator;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.LocationUtil;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeoutException;

public class TechJobsForGoodShallowJobPopulator implements ShallowJobPopulator {

    public Job populateJob(WebElement webElement, Element item, WebDriver driver, Preferences preferences, Integer jobIndex) throws TimeoutException {
        WebElement jobLink = webElement.findElement(By.tagName("a"));
        if(jobLink == null) {
            return null; // If the job link is not found its not worth having a job as we can't link to it
        }
        String jobUrl = jobLink.getAttribute("href");

        WebElement titleDiv = jobLink.findElements(By.className("header")).get(0);
        String title = titleDiv.getText();

        WebElement companySpan = jobLink.findElement(By.className("company_name"));
        String companyName=null;
        if(companySpan!=null){
            companyName = companySpan.getText();
        }


        WebElement descriptionDiv= jobLink.findElement(By.className("description"));
        String description= null;
        if (descriptionDiv != null) {
            description= descriptionDiv.getText();
        }





        Job job = new Job(title, companyName, jobUrl, jobUrl,description);

        WebElement salarySpan = jobLink.findElement(By.className("salary"));
        if(salarySpan!=null) {
            String salaryString = salarySpan.getText();
            if (!salaryString.isEmpty()) {
                if(salaryString.toLowerCase().contains("per hour")) {
                    salaryString = salaryString.toLowerCase().replaceAll("per hour", "").replace("$", "").trim();
                    job.setMinHourlySalary(Integer.parseInt(salaryString));
                } else {

                    String[] salaryParts = salaryString.replace("$", "").replace("K", "000").trim().split("-");
                    if (salaryParts.length > 1) {
                        job.setMinYearlySalary(Integer.parseInt(salaryParts[0].trim()));
                        job.setMaxYearlySalary(Integer.parseInt(salaryParts[1].trim()));
                    } else if (salaryParts.length == 1) {
                        job.setMinYearlySalary(Integer.parseInt(salaryParts[0].trim()));
                    }
                }

            }
        }


        WebElement dateSpan = jobLink.findElement(By.className("date-posted"));
        if(dateSpan!=null) {
            String dateString = dateSpan.getText().replaceAll("Posted ", "");
            Long daysAgo=null;
            if(dateString.contains("days ago") || dateString.contains("day ago")) {
                daysAgo=Long.parseLong(dateString.split(" ")[0]);
            }else if(dateString.contains("hours ago") || dateString.contains("hour ago")) {
                daysAgo=0L;
            }else if(dateString.contains("week ago")) {
                daysAgo=7L;
            }else if(dateString.contains("weeks ago")) {
                long weeksAgo = Long.parseLong(dateString.split(" ")[0]);
                daysAgo=weeksAgo*7L;
            }

            if (daysAgo!=null) {
                job.setJobAgeInDays(daysAgo);
            }
        }
        String locationString = jobLink.findElements(By.className("location")).get(0).getText().replace("\\(","").replace("\\)","");
        if(locationString.contains("Remote")) {
            job.setFullyRemote(true);
            locationString.replace("Remote","").replace(",","");
        }
        if(locationString!=null && !locationString.isEmpty() && !locationString.equalsIgnoreCase("Remote")) {
            LocationUtil locationUtil = new LocationUtil();
            locationUtil.addLocation(locationString, job);
        }


        for (FieldPopulator fieldPopulator : DeepJobPopulator.fieldPopulators) {
            fieldPopulator.populateField(job, preferences);
        }
        return job;

    }




}
