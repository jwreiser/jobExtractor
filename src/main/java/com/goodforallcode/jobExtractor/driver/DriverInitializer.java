package com.goodforallcode.jobExtractor.driver;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class DriverInitializer {
    public static WebDriver getInitializedDriver(Set<Cookie> cookies, String url){
        WebDriver driver =null;
        try {

            driver = getWebDriver();
        } catch (SessionNotCreatedException e) {
            driver = getWebDriver();
        }
        try

        {
            //if we don't get the url first we can't add the cookies
            driver.get(url);
            for (Cookie cookie : cookies) {
                driver.manage().addCookie(cookie);
            }

            driver.get(url);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return driver;
    }

    public static WebDriver getWebDriver() {
        System.setProperty("webdriver.gecko.driver", "D:/development/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
//        WebDriver driver = new ChromeDriver();
//        WebDriver driver = new InternetExplorerDriver();
//        WebDriver driver=new HtmlUnitDriver(true);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        return driver;
    }
}
