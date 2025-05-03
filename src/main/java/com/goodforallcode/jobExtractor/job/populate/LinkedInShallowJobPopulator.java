package com.goodforallcode.jobExtractor.job.populate;

import com.goodforallcode.jobExtractor.util.DateUtil;
import com.goodforallcode.jobExtractor.util.NumUtil;
import com.goodforallcode.jobExtractor.model.Job;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeoutException;

public class LinkedInShallowJobPopulator implements ShallowJobPopulator {
    public Job populateJob(Element item, WebDriver driver) throws TimeoutException {
        String text;
        String companyName = item.getElementsByClass("artdeco-entity-lockup__subtitle").text();

        Element jobLink = item.select("a.job-card-container__link").first();
        if(jobLink==null){
            System.err.println("No job link in "+item);
            return null;
        }
        String jobUrl = jobLink.attr("href");
        String title = jobLink.text();

        Job job = new Job(title, companyName, jobUrl,"http://linkedin.com"+jobUrl);

        WebElement jobLinkElement;
        try{
            jobLinkElement=driver.findElement(By.id(jobLink.id()));
            job.setJobDetailsLink(jobLinkElement);
        }catch (NoSuchElementException nse){
            System.err.println("Could not find job link if this is the only problem we should be okay to continue, though we won't be able to link to the job in the results");
        }catch (Exception nse){
            throw new TimeoutException();
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
