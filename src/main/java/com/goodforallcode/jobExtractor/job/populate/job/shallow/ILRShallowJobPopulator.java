package com.goodforallcode.jobExtractor.job.populate.job.shallow;

import com.goodforallcode.jobExtractor.job.populate.field.FieldPopulator;
import com.goodforallcode.jobExtractor.job.populate.job.deep.DeepJobPopulator;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.DateUtil;
import com.goodforallcode.jobExtractor.util.LocationUtil;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeoutException;

//TODO determine if the salaries are to be trusted in general as some of them are ludicrous
public class ILRShallowJobPopulator implements ShallowJobPopulator {

    public Job populateJob(WebElement webElement, Element item, WebDriver driver, Preferences preferences, Integer jobIndex) throws TimeoutException {
        String text;
        String companyName = item.getElementsByClass("employer-name").text();
        String description = item.getElementsByClass("field-description").text();

        Element jobLink = item.select("a").first();
        if(jobLink==null){
            return null;
        }
        String jobUrl = jobLink.attr("href");
        String title = jobLink.text();

        Job job = new Job(title, companyName, jobUrl,"https://jobsearchplatform.ilr.cornell.edu"+jobUrl,description);

        Element dateDiv = item.getElementsByClass("field-datetime").first();
        String dateString = dateDiv.text().replaceAll("Posting date: ","");

        if (!dateString.isEmpty()) {
            job.setJobAgeInDays(DateUtil.getAgeInDays(dateString));
            job.setPostingDate(DateUtil.getLocalDate(dateString));
        }

        Element locationDiv = item.getElementsByClass("field-map").first();
        if(locationDiv!=null) {
            String locationString = locationDiv.text().replaceAll("Location: ", "");
            LocationUtil locationUtil = new LocationUtil();
            locationUtil.addLocation(locationString, job);
        }

        populateSalaryInformation(item, job);
        for(FieldPopulator fieldPopulator: DeepJobPopulator.fieldPopulators){
            fieldPopulator.populateField(job,preferences);
        }
        return job;

    }

    private  void populateSalaryInformation(Element item, Job job) {
        Element salaryDiv = item.getElementsByClass("field-salary").first();
        if(salaryDiv!=null) {
            String salaryString = salaryDiv.text().replaceAll("Pay range: ", "").replaceAll("\\$", "").replaceAll("\\.00", "");
            populateSalaryInformation(job, salaryString);
        }
    }



}
