package com.goodforallcode.jobExtractor.job.populate;

import com.goodforallcode.jobExtractor.model.Job;
import com.google.common.base.CharMatcher;
import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LinkedInDeepJobPopulator implements DeepJobPopulator {
    String emptyComment="<!---->";
    public void populateJob(Job job,WebDriver driver) {
        String text;
        WebElement detailsDiv = driver.findElement(By.className("jobs-search__job-details--container"));
        Document detailsDoc= Jsoup.parse(detailsDiv.getAttribute("innerHTML"));
        int start;
        try {
            Element tenureLink = detailsDoc.getElementsByClass("jobs-premium-company-growth__median-tenure-years-clock-icon").first();
            if(tenureLink!=null) {
                Element tenure = tenureLink.parent().getElementsByTag("strong").first();
                if(tenure!=null){
                    job.setTenure(getValueFromTwoWordText(tenure.text(),1));
                }
            }

            Element summary = detailsDoc.getElementsByClass("job-details-jobs-unified-top-card__primary-description").first();
            Elements summaryElements = summary.getElementsByTag("span");
            for(Element summaryElement:summaryElements){
                text=summaryElement.text();
                if(text.contains("applicants")){
                    job.setNumApplicants(getValueFromTwoWordText(text,1).intValue());
                }else if(job.getJobAgeInDays()==null && text.contains(" ago")){
                    Long value=Long.parseLong(CharMatcher.inRange('0', '9').retainFrom(text));
                    if(text.contains("day ago")){
                        job.setJobAgeInDays(value);
                    }else if(text.contains("days ago")){
                        job.setJobAgeInDays(value);
                    }else if(text.contains("week ago")){
                        job.setJobAgeInDays(7*value);
                    }else if(text.contains("weeks ago")){
                        job.setJobAgeInDays(7*value);
                    }

                }
            }

            Elements appliedDivs=detailsDoc.getElementsByClass("jobs-s-apply");
            for(Element appliedDiv:appliedDivs){
                if(appliedDiv.text().contains("ago")) {
                    job.setApplied(true);
                }
            }
            Elements insightElements = detailsDoc.getElementsByClass("job-details-jobs-unified-top-card__job-insight");
            for(Element element:insightElements){
                Elements spans = element.getElementsByTag("span");
                if(spans.size()>0) {
                    text=spans.first().text();
                    if (text.contains("employees")) {
                        job.setNumEmployees(getValueFromTwoWordText(text, 2).intValue());
                        start = text.indexOf("·");
                        if (start > 0) {
                            String industry = text.substring(start + 2);
                            job.setIndustry(industry);
                        }
                        break;
                    }
                }
            }

            List<WebElement> elements = driver.findElements(By.className("jobs-save-button"));
            if(elements.size()==2) {
                job.setSaveButton(elements.get(1));
            }
            Element descriptionDiv = detailsDoc.getElementById("job-details");
            String description=detailsDoc.getElementById("job-details").text();
            job.setDescription(description);
            populateMaxExperienceNeeded(job,description);

            Elements descriptionStatuses = descriptionDiv.getElementsByAttributeValue("role", "status");
            for(Element status:descriptionStatuses){
                if(status.text().contains("This job is sourced from a job board.")){
                    job.setSourcedFromJobBoard(true);
                    break;
                }
            }
        }catch (Exception ex){
            throw ex;//catching as a way to allow for inserting a breakpoint
        }


    }

    private void populateMaxExperienceNeeded(Job job, String description) {
        int start;

        int end= description.indexOf("years' experience");
        if(end<0){
            end= description.indexOf("years experience");
        }
        if(end<0){
            end= description.indexOf("years of professional experience");
        }
        if(end>0){
            start= description.indexOf(" ",end-5);
            String experience= description.substring(start,end-1).replaceAll("\\+","").replaceAll("<!---->","").trim();
            if( NumberUtils.isCreatable(experience) ) {
                job.setMaxExperienceRequired(Integer.parseInt(experience));
            }
        }
    }

    private static Float getValueFromTwoWordText(String text,int numValue) {
        Float value=null;
        text=text.replaceAll("<!---->"," ")
                .replaceAll("\\+","").replaceAll(",","");
        int index=text.indexOf("·");
        if(index>0){
            text=text.substring(0,index);
        }
        text=text.replaceAll("-"," ").trim();
        String[] values= text.split(" ");

        if(values.length>numValue){
         String valueText=values[numValue-1];
         value=Float.parseFloat(valueText);
        } else if (values.length>0) {
            String valueText=values[0];
            value=Float.parseFloat(valueText);
        }


        return value;
    }


}
