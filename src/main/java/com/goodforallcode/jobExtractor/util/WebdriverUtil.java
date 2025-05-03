package com.goodforallcode.jobExtractor.util;

import com.goodforallcode.jobExtractor.exception.ContentLoadingException;
import org.openqa.selenium.*;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

public class WebdriverUtil {
    WebDriver driver;
    private static final String UL_CONTAINING_LI_PER_JOB = "jobs-search__results-list";
    private static final String DIV_CONTAINING_UL_FOR_SMALL_BATCHES = "scaffold-layout__list";

    public WebdriverUtil(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getMainElement(String url) throws ContentLoadingException {
        WebElement resultsDiv = null;
        try {

            resultsDiv = loadMainElement();
        } catch (ContentLoadingException cle) {
            try {
                Thread.sleep(1_000);
            } catch (Exception ex) {

            }
            driver.get(url);
            resultsDiv = loadMainElement();
        }
        return resultsDiv;
    }

    public  WebElement getUiContainingLiPerJob() {
        WebElement ulElement=null;
        List<WebElement> listItems = driver.findElements(By.className(UL_CONTAINING_LI_PER_JOB));

        boolean allJobLinks=false;
        if(listItems.isEmpty()){
            ulElement=null;
            List<WebElement> parents = driver.findElements(By.className(DIV_CONTAINING_UL_FOR_SMALL_BATCHES));
            List<WebElement> links;
            if(parents.size()==1){
                List<WebElement> uls =parents.get(0).findElements(By.tagName("ul"));
                if(!uls.isEmpty()){
                    for(WebElement ul:uls) {
                        links=ul.findElements(By.tagName("a"));
                        if(links.isEmpty()){
                            continue;
                        }

                        allJobLinks = true;
                        for(WebElement link:links) {
                            if(!link.getAttribute("href").contains("/jobs/view/")) {
                                allJobLinks = false;
                                break;
                            }
                        }

                        if (allJobLinks) {
                            ulElement = ul;
                            break;
                        }
                    }
                }
            }
        }else{
            ulElement= listItems.get(0);
        }
        return  ulElement;
    }

    public static boolean notOnJobsPage(WebDriver newDriver) {
        return newDriver.getCurrentUrl().equals("https://www.linkedin.com/jobs/");
    }
    private WebElement loadMainElement() throws ContentLoadingException {
        WebElement mainElement = null;
        if (notOnJobsPage(driver)) {
            return null;
        }
        while(mainElement==null) {
            try {
                mainElement = driver.findElement(By.tagName("main"));
            } catch (NoSuchElementException nse) {
                System.err.println("Could not get main element");
            }
        }

        return mainElement;
    }
}
