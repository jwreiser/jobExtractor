package com.goodforallcode.jobExtractor.job.populate.job.shallow;

import com.goodforallcode.jobExtractor.job.populate.field.FieldPopulator;
import com.goodforallcode.jobExtractor.job.populate.job.DeepJobPopulator;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.DateUtil;
import com.goodforallcode.jobExtractor.util.LocationUtil;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeoutException;

public class USAJobsShallowJobPopulator implements ShallowJobPopulator {

    public Job populateJob(Element item, WebDriver driver, Preferences preferences,Integer jobIndex) throws TimeoutException {
        String text;
        String companyName = item.getElementsByClass("usajobs-search-result--core__agency").text();

        Element jobLink = item.select("a").first();
        if(jobLink==null){
            return null;
        }
        String jobUrl = jobLink.attr("href");
        String title = jobLink.text();
        String description = item.getElementsByClass("usajobs-search-result--core__subagency").text();

        Job job = new Job(title, companyName, jobUrl,"https://www.usajobs.gov"+jobUrl,description);

        String dateString = item.getElementsByClass("usajobs-search-result--core__closing-date").text();
        String editedDateString = dateString.toLowerCase().replaceAll("opening and closing dates open ", "").replaceAll("open ", "").trim();
        String postedDateString = editedDateString.split(" to ")[0].trim();
        String[] dateParts=postedDateString.split("/");
        String rearrangedDateString = dateParts[2]+"-"+dateParts[0]+"-"+dateParts[1];
        job.setJobAgeInDays(DateUtil.getAgeInDays(rearrangedDateString));
        job.setPostingDate(DateUtil.getLocalDate(rearrangedDateString));

        for(FieldPopulator fieldPopulator: DeepJobPopulator.fieldPopulators){
            fieldPopulator.populateField(job,preferences);
        }
        return job;

    }

    private static void populateSalaryInformation(Element item, Job job) {
        Element salaryDiv = item.getElementsByClass("field-salary").first();
        if(salaryDiv!=null) {
            String salaryString = salaryDiv.text().replaceAll("Pay range: ", "").replaceAll("\\$", "").replaceAll("\\.00", "");
            if (!salaryString.isEmpty() && salaryString.endsWith("per hour")) {
                String[] salaryParts = salaryString.replaceAll("per hour", "").trim().split("-");
                if (salaryParts.length > 1) {
                    job.setMinHourlySalary((int)Double.parseDouble(salaryParts[0].trim()));
                    job.setMaxHourlySalary((int)Double.parseDouble(salaryParts[1].trim()));
                }
            } else if (!salaryString.isEmpty() && salaryString.endsWith("per hour")) {
                String[] salaryParts = salaryString.replaceAll("per year", "").trim().split("-");
                if (salaryParts.length > 1) {
                    job.setMinYearlySalary((int)Double.parseDouble(salaryParts[0].trim()));
                    job.setMaxYearlySalary((int)Double.parseDouble(salaryParts[1].trim()));
                }
            }
        }
    }


}
