package com.goodforallcode.jobExtractor.controller;

import com.goodforallcode.jobExtractor.extractor.Extractor;
import com.goodforallcode.jobExtractor.extractor.ExtractorFactory;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.QueryInput;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController()
public class ExtractionController {
    @PostMapping("/extract")
    public List<Job> extractMatchingJobs(@RequestBody QueryInput queryInput){
        Extractor extractor= ExtractorFactory.getExtractor(queryInput.getUrls().get(0));
        WebDriver driver = extractor.login(queryInput.getUserName(), queryInput.getPassword());
        Set<Cookie> cookies = driver.manage().getCookies();
        List<Job> jobs=extractor.getJobs(cookies,queryInput.getPreferences(), queryInput.getUrls());
        try{
            driver.close();
        }catch(Exception ex){

        }

        return jobs;
    }
}
