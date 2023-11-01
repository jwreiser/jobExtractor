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
        String companyName = item.getElementsByClass("job-card-container__primary-description").text();

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

            String insight = item.select("div.job-card-container__job-insight-text").text();
            if (insight.contains("top applicant")) {
                job.setTopApplicant(true);
            }
            Elements footerItems = item.select("li.job-card-container__footer-item.inline-flex.align-items-center");
            for (Element footerItem : footerItems) {
                text = footerItem.text();
                if (text.contains("Promoted")) {
                    job.setPromoted(true);
                } else if (text.contains("Easy Apply")) {
                    job.setEasyApply(true);
                }
            }
            Element numApplicants = item.select("soan.tvm__text.tvm__text--positive").first();
            if (numApplicants != null) {
                String[] applicantSplit = numApplicants.text().replaceAll("<!---->", "").split(" ");
                job.setNumApplicants(Integer.valueOf(applicantSplit[0]));
            }

            Elements metadataItems = item.select("li.job-card-container__metadata-item");
            for (Element metadataItem : metadataItems) {
                text = metadataItem.text().replaceAll("<!---->", "");
                if (text.contains("Remote")) {
                    job.setRemote(true);
                    job.setLocation(text.replaceAll("\\(Remote\\)", ""));
                } else if (text.contains("/yr") || text.contains("/hr")) {
                    addSalaryInformation(text, job);
                }
            }

            Elements highlightedFooterItems = item.select("div.job-card-container__footer-item--highlighted");
            for (Element eItem : highlightedFooterItems) {
                text = eItem.text().replaceAll("<!---->", "");
                if (text.contains("recommend this job anymore")) {
                    job.setHidden(true);
                }
            }

            addAgeInformation(item, job);
            addLevel(title.toUpperCase(), job);
            if (!title.isEmpty()) {
                Element hideButton = item.getElementsByAttributeValue("aria-label", "Dismiss job " + title).first();
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

    private static void addLevel(String title, Job job) {
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
            if (title.contains("III")) {
                job.setLevel(3);
            } else  if (title.contains(" II")) {//must be after III for III to match correctly
                job.setLevel(2);
            } else if (title.contains(" IV ")) {
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
            }
        }

    }

    private static void addAgeInformation(Element item, Job job) {
        Elements time = item.getElementsByTag("time");
        if(time.text().contains("Reposted")){
            job.setReposted(true);
            return;//ignore reposted times
        }
        String dateString = time.attr("datetime");
        if (!dateString.isEmpty()) {
            job.setJobAgeInDays(DateUtil.getAgeInDays(dateString));
        }
        dateString = item.getElementsByClass("job-search-card__listdate").attr("datetime");
        if (!dateString.isEmpty()) {
            job.setJobAgeInDays(DateUtil.getAgeInDays(dateString));
        }
    }


    private void addSalaryInformation( String salaryRange, Job job) {
        int benefitsPortion = salaryRange.indexOf("·");
        if(benefitsPortion>0){
            salaryRange=salaryRange.substring(0,benefitsPortion);
        }
        boolean contract=false;
        if(salaryRange.contains("/hr")){
            contract=true;
            job.setContract(true);
        }
        salaryRange=salaryRange.replaceAll("/hr","").replaceAll("/yr","");
        String[] parts = salaryRange.split(" ");
        if (parts.length >= 1) {
            Integer minSalary = NumUtil.convertSalaryToLong(parts[0]);
            if (minSalary != null) {
                if(contract){
                    job.setMinHourlySalary(minSalary);
                }else {
                    job.setMinYearlySalary(minSalary);
                }
            }
            if (parts.length >= 3) {//part 2 is the dash
                Integer maxSalary = NumUtil.convertSalaryToLong(parts[2]);
                if (maxSalary != null) {
                    if(contract){
                        job.setMaxHourlySalary(maxSalary);
                    }else {
                        job.setMaxYearlySalary(maxSalary);
                    }

                }
            }
        }
    }
}
