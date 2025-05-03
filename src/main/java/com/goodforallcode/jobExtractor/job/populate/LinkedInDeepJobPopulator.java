package com.goodforallcode.jobExtractor.job.populate;

import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.util.CompanyUtil;
import com.goodforallcode.jobExtractor.util.RegexUtil;
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
import java.util.Optional;
import java.util.concurrent.TimeoutException;

public class LinkedInDeepJobPopulator implements DeepJobPopulator {
    String emptyComment = "<!---->";

    public boolean populateJob(Job job, WebDriver driver) throws TimeoutException {
        String text;
        WebElement mainDiv = null;
        boolean success = true;
        int failures = 0;
        while (failures < 4) {
            try {
                mainDiv = driver.findElement(By.className("jobs-search__job-details--container"));
                break;
            } catch (org.openqa.selenium.NoSuchElementException nse) {
                try {
                    mainDiv = driver.findElement(By.className("scaffold-layout__main"));
                    break;
                } catch (org.openqa.selenium.NoSuchElementException nse2) {
                    return false;//nothing to handle; proceed with no deep details
                }
            } catch (Exception te) {
                try {
                    Thread.sleep(3_000);
                } catch (Exception ex) {

                }
                failures++;
                if (failures >= 4) {
                    throw new TimeoutException();
                }
            }
        }
        Document detailsDoc = Jsoup.parse(mainDiv.getAttribute("innerHTML"));
        int start;
        try {
            //this is most important so it should go first
            success = addDescriptionBasedDetails(job, detailsDoc);
            if (!success) {
                return success;
            }//this relies on LinkedIn premium
            Element tenureLink = detailsDoc.getElementsByClass("jobs-premium-company-growth__median-tenure-years-clock-icon").first();
            if (tenureLink != null) {
                Element tenure = tenureLink.parent().getElementsByTag("strong").first();
                if (tenure != null) {
                    job.setTenure(getValueFromTwoWordText(tenure.text(), 1));
                }
            }

            populateSummaryBasedFields(job, detailsDoc);
            Elements appliedDivs = detailsDoc.getElementsByClass("jobs-s-apply");
            for (Element appliedDiv : appliedDivs) {
                if (appliedDiv.text().contains("ago")) {
                    job.setApplied(true);
                    break;
                }
            }
            Elements messages = detailsDoc.getElementsByClass("artdeco-inline-feedback__message");
            for (Element element : messages) {
                TextJobPopulator.updateJob(element.text(), job);
            }

            addInsightBasedInformation(job, detailsDoc);
            addPremiumComparisonInformation(job, detailsDoc);
            List<WebElement> elements = driver.findElements(By.className("jobs-save-button"));
            if (elements.size() == 2) {
                job.setSaveButton(elements.get(1));
            }
            addRecruitingClient(job);
        } catch (Exception ex) {
            throw ex;//catching as a way to allow for inserting a breakpoint
        }

        return success;
    }

    public String addRecruitingClient(Job job) {
        String client=null;
        if(CompanyUtil.isRecruiting(job.getCompanyName(),job.getIndustries())){
            String descriptionLower=job.getDescription().toLowerCase();
//            client names can have periods in them
            List<String> patterns = List.of("our client,([^\\,\\;]*)[\\,\\;]*");

            if (descriptionLower.contains("our client")) {
                client = RegexUtil.getValue(descriptionLower, patterns);
                if(client!=null && !CompanyUtil.isRecruiting(client,null)){
                    job.setRecruiterClient(client);
                }else{
                    System.err.println("Could not get client from!!!!!!!!!!!"+descriptionLower);
                }
            }
        }
        return client;
    }

    private static void addPremiumComparisonInformation(Job job, Document detailsDoc) {
        Element howYouCompare= detailsDoc.getElementById("how-you-match-card-container");
        if(howYouCompare!=null){
            Elements skillDivs = howYouCompare.getElementsByClass("pt5");
            for(Element skillDiv:skillDivs){
                Element label=skillDiv.getElementsByTag("h3").first();
                if(label!=null && !label.text().contains("applicants")){
                    for(Element link:skillDiv.getElementsByTag("a")) {
                        String skills = link.text();
                        for (String skill : skills.split(" · ")) {
                            if (!job.getSkills().contains(skill)) {
                                job.getSkills().add(skill);
                            }
                        }
                    }
                }
            }
        }
    }

    private static void addInsightBasedInformation(Job job, Document detailsDoc) {
        int start;
        String text;
        int foundInsights = 0, knownInsights = 3;
        Elements divUnderTheHeaderStartingWithCompanyList = detailsDoc.getElementsByClass("job-details-jobs-unified-top-card__primary-description-without-tagline");
        if(divUnderTheHeaderStartingWithCompanyList!=null&&divUnderTheHeaderStartingWithCompanyList.size()>0){
            Element divUnderTheHeaderStartingWithCompany=divUnderTheHeaderStartingWithCompanyList.get(0);
            String totalText=divUnderTheHeaderStartingWithCompany.text();
            int startLoc=totalText.indexOf("·");
            if(startLoc>0) {
                int endLoc = totalText.indexOf("·", startLoc + 1);
                if(endLoc>0) {
                    String location = totalText.substring(startLoc+1, endLoc).trim();
                    job.setLocation(location);
                }
            }
        }
        Elements insightElements = detailsDoc.getElementsByClass("job-card-container__job-insight-text");
        for (Element element : insightElements) {


            for (Element span : element.getElementsByTag("span")) {
                text = span.text();
                if (text.contains("employees")) {
                    job.setMinimumNumEmployees(getValueFromTwoWordText(text, 1).intValue());
                    job.setMaximumNumEmployees(getValueFromTwoWordText(text, 2).intValue());
                    start = text.indexOf("·");
                    if (start > 0) {
                        String industry = text.substring(start + 2);
                        job.getIndustries().add(industry);
                        job.setJobIndustry(industry);
                        foundInsights++;
                    }
                }
                TextJobPopulator.updateJob(text,job);
            }
        }
    }

    private static void populateSummaryBasedFields(Job job, Document detailsDoc) {
        String text;
        Element summary = detailsDoc.getElementsByClass("jobs-description-content__text--stretch").first();
        if (summary != null) {
            processSummary(job, summary);
        }
        //try two different ways as first does not always work
        summary = detailsDoc.getElementsByClass("job-details-jobs-unified-top-card__primary-description-container").first();
        if (summary != null) {
            processSummary(job, summary);
        }

    }

    private static void processSummary(Job job, Element summary) {
        String text;
        Elements summaryElements = summary.getElementsByTag("span");
        for (Element summaryElement : summaryElements) {
            text = summaryElement.text();
            String[] bullets=text.split(" · ");
            for(String bulletText:bullets) {
                if (bulletText.contains("applicants")) {
                    if (bulletText.toLowerCase().contains("over ")) {
                        job.setNumApplicants(300);//I don’t like the magic number but since the actual value is unknown I don’t want to waste time guessing
                    } else {
                        Float applicants = getValueFromTwoWordText(bulletText, 1);

                        if (applicants != null) {
                            job.setNumApplicants(applicants.intValue());
                        }
                    }
                } else if (job.getJobAgeInDays() == null && bulletText.contains(" ago")) {
                    String ageText = bulletText.substring(0, bulletText.indexOf("ago")).trim();
                    Long value = Long.parseLong(CharMatcher.inRange('0', '9').retainFrom(ageText));

                    if (bulletText.contains("day ago")) {
                        job.setJobAgeInDays(1L);
                    } else if (bulletText.contains("days ago")) {
                        job.setJobAgeInDays(value);
                    } else if (bulletText.contains("week ago")) {
                        job.setJobAgeInDays(7L);
                    } else if (bulletText.contains("weeks ago")) {
                        job.setJobAgeInDays(7 * value);
                    } else if (bulletText.contains("hours ago")) {
                        job.setJobAgeInDays(0L);
                    }

                }
            }

        }
    }

    private boolean addDescriptionBasedDetails(Job job, Document detailsDoc) {
        Element descriptionDiv = detailsDoc.getElementById("job-details");
        if (descriptionDiv != null) {
            String description = detailsDoc.getElementById("job-details").text();
            String descriptionLower = description.toLowerCase();
            if (descriptionLower.equals("about the job")) {
                return false;
            } else if (descriptionLower.isEmpty()) {
                return false;
            }
            job.setDescription(description);
            Optional<Integer> maxExperienceRequired = getMaxExperienceNeeded(description);
            if (maxExperienceRequired.isPresent()) {
                job.setMaxExperienceRequired(maxExperienceRequired.get());

            }
            Optional<Integer> contractDuration = getContractDuration(description);
            if (contractDuration.isPresent()) {
                job.setContractMonths(contractDuration.get());
                job.setContract(true);

            }

            Elements descriptionStatuses = descriptionDiv.getElementsByAttributeValue("role", "status");
            for (Element status : descriptionStatuses) {
                TextJobPopulator.updateJob (status.text(),job);
            }
            job.setTravelPercent(getTravelPercentage(descriptionLower));
        }
        return true;
    }

    public static Integer getTravelPercentage(String descriptionLower) {
        List<String> patterns = List.of("(\\d*)% travel", "travel[:\\s]+(\\d*)%",
                "travel[:\\s]+up to[:\\s]+(\\d*)%", "travel[:\\s]+less than[:\\s]+(\\d*)%"
                , "travel[^\\d\\)\\.\\,\\;]*\\d*-(\\d*)%", "travel[^\\d\\)\\.\\,\\;]*(\\d*)%");

        Integer percent = null;

        if (descriptionLower.contains("travel")) {
            percent = RegexUtil.getIntegerValue(descriptionLower, patterns);
        }
        return percent;
    }


    private static Float getValueFromTwoWordText(String text, int numValue) {
        Float value = null;
        text = text.replaceAll("<!---->", " ")
                .replaceAll("\\+", "").replaceAll(",", "");
        int index = text.indexOf("·");
        if (index > 0) {
            text = text.substring(0, index);
        }
        text = text.replaceAll("-", " ").trim();
        String[] values = text.split(" ");

        if (values.length > numValue) {
            String valueText = values[numValue - 1];
            if (NumberUtils.isCreatable(valueText)) {
                value = Float.parseFloat(valueText);
            }
        } else if (values.length > 0) {
            String valueText = values[0];
            value = Float.parseFloat(valueText);
        }


        return value;
    }


}
