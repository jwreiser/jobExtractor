package com.goodforallcode.jobExtractor.controller;

import com.goodforallcode.jobExtractor.extractor.Extractor;
import com.goodforallcode.jobExtractor.extractor.ExtractorFactory;
import com.goodforallcode.jobExtractor.extractor.JobResult;
import com.goodforallcode.jobExtractor.model.QueryInput;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Set;

@RestController()
public class ExtractionController {
    @PostMapping("/extract")
    public JobResult extractMatchingJobs(@RequestBody QueryInput queryInput){
        Extractor extractor= ExtractorFactory.getExtractor(queryInput.getUrls().get(0));
        WebDriver driver = extractor.getDriver(queryInput.getUserName(), queryInput.getPassword());
        Set<Cookie> cookies = driver.manage().getCookies();
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("li_at")) {
                if (!cookie.getDomain().equals(".linkedin.com")) {
                    String value=cookie.getValue();
                    Date expiry=cookie.getExpiry();
                    driver.manage().deleteCookie(cookie);
                    driver.manage().addCookie(
                            new Cookie("li_at", value, ".linkedin.com", "/", expiry)
                    );
                    cookies = driver.manage().getCookies();
                }
                break;
            }
        }

        JobResult result=extractor.getJobs(cookies,queryInput.getPreferences(), queryInput.getUrls(),driver);
        try{
            driver.close();
        }catch(Exception ex){

        }

        return result;
    }
}
