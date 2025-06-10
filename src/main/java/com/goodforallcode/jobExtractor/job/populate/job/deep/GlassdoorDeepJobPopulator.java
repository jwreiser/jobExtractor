package com.goodforallcode.jobExtractor.job.populate.job.deep;

import com.goodforallcode.jobExtractor.job.populate.TextJobPopulator;
import com.goodforallcode.jobExtractor.job.populate.field.FieldPopulator;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
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

public class GlassdoorDeepJobPopulator implements DeepJobPopulator {
    String emptyComment = "<!---->";
    public boolean populateJob(Job job, WebDriver driver, Preferences preferences) throws TimeoutException {
        String text;
        WebElement mainDiv = driver.findElement(By.className("JobDetails_jobDetailsContainer__y9P3L"));
        boolean success=true;

        Document detailsDoc = Jsoup.parse(mainDiv.getAttribute("innerHTML"));
        Element descriptionDiv=detailsDoc.getElementsByClass("JobDetails_jobDescription__uW_fK").first();
        if(descriptionDiv!=null){
            job.setDescription(descriptionDiv.text());
        }
        return success;
    }


}
