package com.goodforallcode.jobExtractor.job.populate.job.shallow;

import com.goodforallcode.jobExtractor.job.populate.field.FieldPopulator;
import com.goodforallcode.jobExtractor.job.populate.job.deep.DeepJobPopulator;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeoutException;

public class GlassdoorShallowJobPopulator implements ShallowJobPopulator {

    public Job populateJob(WebElement webElement,Element item, WebDriver driver, Preferences preferences,Integer jobIndex) throws TimeoutException {
        Element deepLink = item.getElementsByClass("JobCard_trackingLink__HMyun").first();
        if(deepLink==null){
            return null;
        }
        String deepUrl = deepLink.attr("href");

        String text;
        String companyName = item.getElementsByClass("EmployerProfile_compactEmployerName__9MGcV").text();

        Element referringLink = item.getElementsByClass("JobCard_jobTitle__GLyJ1").first();
        String jobUrl = referringLink.attr("href");
        String title = referringLink.text();

        Job job = new Job(title, companyName, jobUrl,jobUrl);
        if(preferences.isRemoteOnly()){//we are currently relying on the search to limit to remote jobs
            job.setFullyRemote(true);
        }
        WebElement jobLinkElement;
        try{
            jobLinkElement=driver.findElement(By.xpath("//a[@href='"+deepUrl+"']"));
            job.setJobDetailsLink(jobLinkElement);
        }catch (NoSuchElementException nse){
            System.err.println("Could not find job link if this is the only problem we should be okay to continue, though we won't be able to link to the job in the results");
        }catch (Exception nse){
            throw new TimeoutException();
        }
        String ageString = item.getElementsByClass("JobCard_listingAge__jJsuc").text();
        Long ageInDays = null;
        if(ageString.equals("24h")){
            ageInDays=1L;
        }else if(ageString.endsWith("d")&& NumberUtils.isParsable(ageString.replaceAll("d",""))) {
            ageInDays = Long.parseLong(ageString.replaceAll("d", ""));
        }
        if(ageInDays!= null){
            job.setJobAgeInDays(ageInDays);
        }


        for(FieldPopulator fieldPopulator: DeepJobPopulator.fieldPopulators){
            fieldPopulator.populateField(job,preferences);
        }
        return job;

    }



}
