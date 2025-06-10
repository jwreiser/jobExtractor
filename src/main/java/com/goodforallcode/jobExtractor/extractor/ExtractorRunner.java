package com.goodforallcode.jobExtractor.extractor;

import com.goodforallcode.jobExtractor.model.QueryInput;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class ExtractorRunner {
    public JobResult extractMatchingJobs(@RequestBody QueryInput queryInput) {
        JobResult result = new JobResult();

        JobResult currentResult;
        Extractor extractor;

        //TODO we are assuming that there is only one shared extractor, if we support more secure extractors we must change this logic
        Extractor sharedExtractor= null;
        Set<Cookie> sharedCookies = null;
        WebDriver sharedDriver = null;
        List<String> sharedUrls = new ArrayList<>();

        for (String url : queryInput.getUrls()) {
            extractor = ExtractorFactory.getExtractor(url);
            if (extractor.needsSharedInputDriver()) {
                if (sharedDriver == null) {
                    sharedExtractor=extractor;
                    sharedDriver = extractor.getDriver(queryInput.getUserName(), queryInput.getPassword());
                    sharedCookies = updateCookies(sharedDriver);
                }
                sharedUrls.add(url);
            } else {
                //TODO run these extractors in parallel
                currentResult = extractor.getJobs(null, queryInput.getPreferences(), List.of(url), null);
                result.merge(currentResult);

            }

        }

        if(sharedExtractor!=null) {
            currentResult = sharedExtractor.getJobs(sharedCookies, queryInput.getPreferences(), sharedUrls, sharedDriver);
            result.merge(currentResult);
        }
        if (sharedDriver != null) {
            try {
                sharedDriver.close();
            } catch (Exception ex) {

            }
            try {
                sharedDriver.quit();
            } catch (Exception ex) {

            }
        }
        return result;

    }

    private static Set<Cookie> updateCookies(WebDriver driver) {
        Set<Cookie> cookies = driver.manage().getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("li_at")) {
                if (!cookie.getDomain().equals(".linkedin.com")) {
                    String value = cookie.getValue();
                    Date expiry = cookie.getExpiry();
                    driver.manage().deleteCookie(cookie);
                    driver.manage().addCookie(
                            new Cookie("li_at", value, ".linkedin.com", "/", expiry)
                    );
                    cookies = driver.manage().getCookies();
                }
                break;
            }
        }
        return cookies;
    }
}
