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

import java.util.concurrent.TimeoutException;

public class IdealistShallowJobPopulator implements ShallowJobPopulator {

    public Job populateJob(WebElement webElement, Element item, WebDriver driver, Preferences preferences, Integer jobIndex) throws TimeoutException {
        WebElement applyDiv = webElement.findElement(By.id("apply"));
        WebElement jobLink = applyDiv.findElement(By.tagName("a"));
        if(jobLink == null) {
            return null; // If the job link is not found its not worth having a job as we can't link to it
        }
        String jobUrl = jobLink.getAttribute("href");

        WebElement titleElement = webElement.findElement(By.xpath("//h1[@data-qa-id='listing-name']"));
        String title =null;
        if(titleElement!=null){
            title = titleElement.getText();
        }

        WebElement companyElement = webElement.findElement(By.xpath("//a[@data-qa-id='org-link']"));
        String companyName=null;
        if(companyElement!=null){
            companyName = companyElement.getText();
        }


        String description= null;
        WebElement descriptionHeader = driver.findElement(By.xpath("//h3[text()='Description']"));
        if(descriptionHeader!= null) {
            WebElement descriptionDiv= descriptionHeader.findElement(By.xpath("./.."));//get the parent div
            if (descriptionDiv != null) {
                description= descriptionDiv.getText().replace("\n"," ");
            }
        }






        Job job = new Job(title, companyName, jobUrl, jobUrl,description);
        WebElement consultantH5 = webElement.findElement(By.xpath("//h5[@data-qa-id='listing-type']"));
        if(consultantH5!=null && consultantH5.getText().toLowerCase().contains("consultant")) {
            job.setConsultant(true);
        }

        if(preferences.isRemoteOnly()){
            job.setFullyRemote(true);//we are relying on the search to be remote only, otherwise we need to use temporary looking classnames, etc to get remote information

        }

        WebElement datePublished = webElement.findElement(By.xpath("//div[contains(text(), 'Published')]"));
        if(datePublished!=null) {
            DateUtil.setDateInformation(datePublished.getText(), job);
        }


        for (FieldPopulator fieldPopulator : DeepJobPopulator.fieldPopulators) {
            fieldPopulator.populateField(job, preferences);
        }
        return job;

    }


}
