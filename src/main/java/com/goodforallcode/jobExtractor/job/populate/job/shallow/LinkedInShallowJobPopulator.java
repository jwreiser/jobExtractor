package com.goodforallcode.jobExtractor.job.populate.job.shallow;

import com.goodforallcode.jobExtractor.job.populate.TextJobPopulator;
import com.goodforallcode.jobExtractor.job.populate.field.FieldPopulator;
import com.goodforallcode.jobExtractor.job.populate.job.deep.LinkedInDeepJobPopulator;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.DateUtil;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.util.LocationUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeoutException;

public class LinkedInShallowJobPopulator implements ShallowJobPopulator {

    LocationUtil locationUtil = new LocationUtil();

    public Job populateJob(WebElement webElement,Element item, WebDriver driver, Preferences preferences,Integer jobIndex) throws TimeoutException {
        Element jobCardWrapper = item.getElementsByClass("job-card-job-posting-card-wrapper").first();
        Element wrapperMainDiv=null;
        Elements wrapperChildDivs=null;
        if(jobCardWrapper!=null) {
            wrapperMainDiv = jobCardWrapper.getElementsByClass("flex-grow-1").first();
            if(wrapperMainDiv!=null) {
                wrapperChildDivs = wrapperMainDiv.getElementsByTag("div");
            }
        }


        Element jobLink = item.select("a.job-card-container__link").first();
        if(jobLink==null){
            jobLink = item.select("a.job-card-job-posting-card-wrapper__card-link").first();
            if(jobLink==null){
                return null;
            }
        }

        String jobUrl = jobLink.attr("href");
        String title = null;
        if(wrapperMainDiv!=null){
            title =wrapperMainDiv.getElementsByTag("strong").text();
        }else{
            title = jobLink.text();
        }

        String companyName = null;
        Element companyElement=item.getElementsByClass("artdeco-entity-lockup__subtitle").first();
        if(companyElement!=null){
            companyName = companyElement.text();
        }else if(wrapperChildDivs!=null && (wrapperChildDivs.size()==12||wrapperChildDivs.size()==8)){
            companyName = wrapperChildDivs.get(2).text();
        }
        String fullUrl = jobUrl;
        if(!jobUrl.startsWith("http")){
            fullUrl ="http://linkedin.com"+jobUrl;
        }
        Job job = new Job(title, companyName, jobUrl,fullUrl);

        if(wrapperMainDiv!=null) {
            if(wrapperChildDivs!=null && (wrapperChildDivs.size()==12||wrapperChildDivs.size()==8)) {
                String location = wrapperChildDivs.get(4).text();
                locationUtil.addLocation(location, job);
            }
            if(wrapperChildDivs!=null && (wrapperChildDivs.size()==12||wrapperChildDivs.size()==8)) {
                String salary = wrapperChildDivs.get(7).text();
                populateSalaryInformation(job, salary);
            }
        }
        if(jobCardWrapper!=null){
            Element timeElement = jobCardWrapper.getElementsByTag("time").first();
            if(timeElement!=null) {
                String dateString = timeElement.attr("datetime");
                if (!dateString.isEmpty()) {
                    job.setJobAgeInDays(DateUtil.getAgeInDays(dateString));
                    job.setPostingDate(DateUtil.getLocalDate(dateString));
                }
            }
        }
        WebElement jobLinkElement;
        try{
            jobLinkElement=driver.findElement(By.id(jobLink.id()));//this is what we click on for expanded details
            job.setJobDetailsLink(jobLinkElement);
        }catch (NoSuchElementException nse){
            System.err.println("Could not find job link if this is the only problem we should be okay to continue, though we won't be able to link to the job in the results");
        }catch (Exception nse){
            if(wrapperMainDiv!=null) {
                WebElement rootElement=driver.findElement(By.id(item.id()));//this is what we click on for expanded details
                if(rootElement!=null) {
                    String className=null;

                    for(String currentClass:jobLink.classNames()){
                        if(!currentClass.contains("job")){
                            className = currentClass;
                            break;
                        }
                    }
                    if(className!=null) {
                        jobLinkElement=rootElement.findElement(By.className(className));
                        job.setJobDetailsLink(jobLinkElement);
                    }

                    job.setJobDetailsLink(rootElement);
                }

            }else{
                throw new TimeoutException();
            }

        }
        try {
            //this only shows up when you pay for LinkedIn premium
            TextJobPopulator.updateJob(item.select("div.job-card-container__job-insight-text").text(), job);


            Elements footerItems = item.select("li.job-card-container__footer-item.inline-flex.align-items-center");
            for (Element footerItem : footerItems) {
                TextJobPopulator.updateJob(footerItem.text(), job);
            }

            Elements metadataItems = item.select("ul.job-card-container__metadata-wrapper");
            if (!metadataItems.isEmpty()) {
                metadataItems = metadataItems.first().select("li");
                for (Element metadataItem : metadataItems) {
                    TextJobPopulator.updateJob(metadataItem.text().replaceAll("<!---->", ""), job);
                }
            }

            addAgeInformation(item, job);
            addLevel(title.toUpperCase(), job);
            if (!title.isEmpty()) {
                Element hideButton = item.getElementsByAttributeValueStarting("aria-label", "Dismiss ").first();
                if(hideButton!=null) {
                    try {
                        job.setHideButton(driver.findElement(By.id(hideButton.id())));
                    }catch (NoSuchElementException nse){
                        //continue as hopefully future buttons work
                        System.err.println("Could not find hide button");
                    }
                }
            } else {
                job.setUrl("jobs/view/" + item.attr("data-occludable-job-id"));
            }

        }catch (Exception ex){
            throw ex;//catching as a way to allow for inserting a breakpoint
        }
        for(FieldPopulator fieldPopulator: LinkedInDeepJobPopulator.fieldPopulators){
            fieldPopulator.populateField(job,preferences);
        }
        return job;

    }

    public static void addLevel(String title, Job job) {
        if (title.toLowerCase().contains("level")) {
            int start = title.indexOf("level");
            if(start<0){
                start = title.indexOf("Level");
            }
            start+= 6;
            String levelString = title.substring(start).split(" ")[0];
            Integer level = null;
            try {
                level = Integer.parseInt(levelString);
            } catch (NumberFormatException ne) {
                switch (levelString) {
                    case "I":
                        level = 1;
                        break;
                    case "II":
                        level = 2;
                        break;
                    case "III":
                        level = 3;
                        break;
                    case "IV":
                        level = 4;
                        break;
                    case "V":
                        level = 5;
                        break;
                    case "VI":
                        level = 6;
                        break;
                }
            }
            job.setLevel(level);
        } else {
            if (title.contains("III")||title.contains("L3")) {
                job.setLevel(3);
            } else  if (title.contains(" II")||title.contains("L2")) {//must be after III for III to match correctly
                job.setLevel(2);
            } else if (title.contains(" IV ")||title.contains("L4")) {
                job.setLevel(4);
            }else if (title.contains(" VI ")) {
                    job.setLevel(6);

            } else if (title.contains(" V ")) {
                job.setLevel(5);
            }
            else  if (title.endsWith(" IV")) {
                job.setLevel(4);
            }else if (title.endsWith(" VI")) {
                job.setLevel(6);
            } else if (title.endsWith(" V")) {
                job.setLevel(5);
            }else if(title.contains("L1")){
                job.setLevel(1);
            }
        }

    }

    /**
     * Even though reposted times will be different than original posting dates
     * we need something to distinguish job duplicates and time seems like a necessary
     * @param item
     * @param job
     */
    private static void addAgeInformation(Element item, Job job) {
        Elements times = item.getElementsByTag("time");
        if(!times.isEmpty()) {
            for (Element time : times) {
                if (time.text().contains("Reposted")) {
                    job.setReposted(true);
                }
                String dateString = item.getElementsByClass("job-search-card__listdate").attr("datetime");
                if (dateString.isEmpty()) {
                    dateString = item.getElementsByClass("job-search-card__listdate--new").attr("datetime");
                }
                if (!dateString.isEmpty()) {
                    job.setJobAgeInDays(DateUtil.getAgeInDays(dateString));
                    job.setPostingDate(DateUtil.getLocalDate(dateString));
                } else {
                    dateString = time.attr("datetime");
                    if (!dateString.isEmpty()) {
                        job.setJobAgeInDays(DateUtil.getAgeInDays(dateString));
                        job.setPostingDate(DateUtil.getLocalDate(dateString));
                    }
                }
            }
        }
    }



}
