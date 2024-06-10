package com.goodforallcode.jobExtractor.extractor;

import com.goodforallcode.jobExtractor.exception.ContentLoadingException;
import com.goodforallcode.jobExtractor.job.populate.*;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.JobInfo;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CacheUtil;
import com.mongodb.client.MongoClient;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

//import static java.lang.StringTemplate.STR;

@Getter
@Setter
@Component
public class LinkedInExtractor extends Extractor {


    private JobInfoPopulator jobInfoPopulator = new JobInfoPopulator();

    static ShallowJobPopulator shallowJobPopulator = new LinkedInShallowJobPopulator();
    private static int RESULTS_PER_PAGE = 25;

    @Override
    public WebDriver login(String userName, String password) {
        WebDriver driver = getWebDriver("https://www.linkedin.com/");
        driver.get("https://www.linkedin.com/");
        driver.findElement(By.id("session_key")).sendKeys(userName);
        driver.findElement(By.id("session_password")).sendKeys(password);

        WebElement sign = driver.findElement(By.className("sign-in-form__submit-btn--full-width"));
        doubleClickOnElement(driver, sign,false);
        return driver;

    }


    public void doubleClickOnElement(WebDriver driver, WebElement element) {
        super.doubleClickOnElement(driver, element,false);

        if (driver != null && driver.getTitle().equals("Security Verification | LinkedIn")) {
            System.err.println("You need to pass verification to login");
        }
    }


    @Override
    public JobResult getJobs(Set<Cookie> cookies, Preferences preferences, String url, MongoClient mongoClient) {
        List<Job> acceptedJobs = new ArrayList<>();
        List<Job> rejectedJobs = new ArrayList<>();
        List<Job> deepCachedJobs = new ArrayList<>();
        List<Job> shallowCachedJobs = new ArrayList<>();
        WebDriver newDriver = null;
        int totalHidden = 0, totalJobs = 0, numJobs = 0, totalSkipped = 0, totalCached = 0, currentPageNum = 0, actualPageNum = 1;
        Optional<Integer> numResultsOption = Optional.empty();

        try {
            newDriver = getWebDriver(url);
        } catch (SessionNotCreatedException sessionNotCreatedException)
        {
            System.err.println("SessionNotCreatedException for " + url);
        }

        if(newDriver!=null) {
            try {

                //if we don't get the url first we can't add the cookies
                newDriver.get(url);
                for (Cookie cookie : cookies) {
                    newDriver.manage().addCookie(cookie);
                }

                newDriver.get(url);
                if (notOnJobsPage(newDriver)) {
                    return new JobResult(acceptedJobs, rejectedJobs, shallowCachedJobs, deepCachedJobs, totalJobs, totalHidden, totalSkipped, actualPageNum);
                }
                int hiddenJobs = 0, skippedJobs = 0, cachedJobs = 0;
                boolean everyJobHiddenCachedOrSkipped = false, skipRemainingJobs = false;
                WebElement nextPageButton = null;
                List<Integer> pageValues;
                Integer lastPageNumber = 1;
                int currNumJobs = 1;
                do {
                    currentPageNum++;
                /*
                the current number of results can change as the page loads so we
                don't want to JUST read it right away as it may get lowered
                 */
                    try {
                        Thread.sleep(5_000);
                    } catch (InterruptedException ex) {

                    }
                    if (noMatchingJobs(newDriver)) {
                        break;
                    }

                    waitForPageToLoad(newDriver);
                    if (noMatchingJobs(newDriver)) {
                        numResultsOption = Optional.of(0);
                    } else {
                        numResultsOption = getCurrentNumberOfResults(newDriver);
                    }

                    if (notOnJobsPage(newDriver)) {
                        return new JobResult(acceptedJobs, rejectedJobs, shallowCachedJobs, deepCachedJobs, totalJobs, totalHidden, totalSkipped, actualPageNum);
                    }

                    if (numResultsOption.isPresent() && numResultsOption.get() <= RESULTS_PER_PAGE) {
                        if (numResultsOption.get() == 0) {
                            break;
                        } else {
                            actualPageNum = 1;
                            lastPageNumber = 1;
                        }
                    } else {
                        pageValues = getCurrentAndMaxPage(newDriver);
                        if (pageValues != null) {
                            actualPageNum = pageValues.get(0);
                            lastPageNumber = pageValues.get(1);
                        }
                    }
                    System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!       Page: " + actualPageNum + " of " + lastPageNumber + "for " + url + "      !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    //TODO return something more complex that will return at least a no more results flag
                    WebElement resultsDiv = null;
                    try {

                        resultsDiv = loadMainElement(newDriver, actualPageNum, lastPageNumber, numResultsOption);
                    } catch (ContentLoadingException cle) {
                        try {
                            Thread.sleep(1_000);
                        } catch (Exception ex) {

                        }
                        newDriver.get(url);
                        resultsDiv = loadMainElement(newDriver, actualPageNum, lastPageNumber, numResultsOption);
                    }
                    if (resultsDiv == null) {
                    /*
                    we could not get the page.
                    Most likely this is because there are no more results
                    stop processing this job and move onto the next
                     */
                        break;
                    }


                    nextPageButton = getNextPageButton(newDriver, actualPageNum);
                    if (preferences.getSkipFirstPages() != null && preferences.getSkipFirstPages() > currentPageNum) {
                        doubleClickOnElement(newDriver, nextPageButton,false);
                        continue;
                    }


                /*
                if we are already passed the last page count wise; we are spinning and need to get
                to the final page
                 */
                    if (lastPageNumber < currentPageNum && everyJobHiddenCachedOrSkipped) {
                        doubleClickOnElement(newDriver, nextPageButton,false);
                    }

                    scrollResultsIntoView(newDriver, resultsDiv);

                    Elements items = getJobItems(newDriver);

                    hiddenJobs = 0;
                    skippedJobs = 0;
                    cachedJobs = 0;
                    everyJobHiddenCachedOrSkipped = false;
                    skipRemainingJobs = false;
                    numJobs = items.size();

                    hideMessages(newDriver);
                    JobInfo currentJobInfo;
                    currNumJobs = 0;
                    for (Element item : items) {
                        currNumJobs++;
                        if(currNumJobs%5==0){
                            System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!           " + currNumJobs + ":  of " + numJobs + " for " + url + "      !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        }

                        currentJobInfo = new JobInfo(hiddenJobs, skippedJobs, cachedJobs, totalHidden, totalCached, totalSkipped
                                , everyJobHiddenCachedOrSkipped, skipRemainingJobs, false);
                        totalJobs++;

                        currentJobInfo = jobInfoPopulator.populateJobInfo(acceptedJobs, rejectedJobs, deepCachedJobs, shallowCachedJobs, item, 0, currentJobInfo
                                , numJobs, url, newDriver, preferences, shallowJobPopulator);

                        hiddenJobs = currentJobInfo.getHiddenJobs();
                        skippedJobs = currentJobInfo.getSkippedJobs();
                        cachedJobs = currentJobInfo.getCachedJobs();
                        totalHidden = currentJobInfo.getTotalHidden();
                        totalCached = currentJobInfo.getTotalCached();
                        totalSkipped = currentJobInfo.getTotalSkipped();
                        everyJobHiddenCachedOrSkipped = currentJobInfo.isEveryJobHiddenCachedOrSkipped();

                        if (currentJobInfo.isSkipRemainingJobs()) {
                            break;
                        }
                        if (currentJobInfo.isStaleState()) {
                            return getJobs(cookies, preferences, url, mongoClient);
                        }

                    }
                    if (nextPageButton != null && numJobs == 25) {
                        doubleClickOnElement(newDriver, nextPageButton,false);
                    }
                } while (nextPageButton != null && numJobs == 25);

            } catch (Exception e) {
                e.printStackTrace();
            }
            System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!   SUCCESSFULLY EXTRACTED FROM " + url + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }

        if (newDriver != null) {
            try {
                newDriver.quit();
            } catch (Exception ex) {

            }
        }
        return new JobResult(acceptedJobs, rejectedJobs, shallowCachedJobs, deepCachedJobs, totalJobs, totalHidden, totalSkipped, actualPageNum);
    }


    private void hideMessages(WebDriver newDriver) {
        try {
            WebElement svg = newDriver.findElement(By.cssSelector("svg[data-test-icon='chevron-down-small']"));
            WebElement button = svg.findElement(By.xpath(".."));
            if (button != null && button.getTagName().equals("button")) {
                button.click();
            }
        } catch (NoSuchElementException | StaleElementReferenceException nse) {
            //I don't like how this does not just return false. If the item is not there don't click it
        }
        try {
            List<WebElement> closeImages = newDriver.findElements(By.cssSelector("svg[data-test-icon='close-small']"));
            WebElement button;
            for (WebElement image : closeImages) {
                if (image.isDisplayed()) {
                    button = image.findElement(By.xpath(".."));
                    if (button != null && button.getTagName().equals("button")) {
                        button.click();
                    }
                }
            }
        } catch (NoSuchElementException nse) {
            //I don't like how this does not just return false. If the item is there don't click it
        } catch (StaleElementReferenceException stale) {
            //this seems to happen when there is no message div to hide
        }
    }

    private static boolean notOnJobsPage(WebDriver newDriver) {
        return newDriver.getCurrentUrl().equals("https://www.linkedin.com/jobs/");
    }

    private Elements getJobItems(WebDriver driver) {
        WebElement ul = null;
        Elements items = new Elements();
        try {
            ul = driver.findElement(By.className("scaffold-layout__list-container"));
        } catch (NoSuchElementException nse) {
            if (!isPageFullyLoaded(driver)) {
                driver.navigate().refresh();//at least sometimes we can't get pages when the page is not fully loaded
                try {//waiting does not seem to help
                    Thread.sleep(5_000);
                    ul = driver.findElement(By.className("artdeco-pagination__page-state"));
                } catch (InterruptedException | NoSuchElementException nse2) {
                    return items;
                }
            } else {
                return items;
            }
        }

        if (ul != null) {
            Document doc = Jsoup.parse(ul.getAttribute("innerHTML"));
            items = doc.select("li.jobs-search-results__list-item");
        }
        return items;
    }

    private void scrollResultsIntoView(WebDriver driver, WebElement resultsDiv) {
        Document document = Jsoup.parse(resultsDiv.getAttribute("innerHTML"));
        Element span = document.getElementsByTag("span").first();


    /*
    we are hardcoding this to deal with the weirdness of the number of results changing over time.
    I don't want to miss loading some jobs, especially since we scroll quick. I would rather waste a few seconds
    */
        int scrolls = 5;
        //we were not loading all of the elements fully but scrolling them into view fixes it
        for (int i = 1; i < scrolls * 25; i++) {
            try {
                ((JavascriptExecutor) driver).executeScript("document.getElementsByClassName('jobs-search-results-list')[0].scrollTop=" + i * 75);
            } catch (Exception ex) {//may get runtimes we can't catch specifically
                try {
                    Thread.sleep(5_0000);
                    ((JavascriptExecutor) driver).executeScript("document.getElementsByClassName('jobs-search-results-list')[0].scrollTop=" + i * 75);
                } catch (Exception ex1) {

                }
            }
            try {
                Thread.sleep(100);
            } catch (Exception ex) {

            }
        }
    }

    private static String getNumResultsTextFromElement(Element span) {
        return span.text().replaceAll("<!---->", "").split(" ")[0];
    }

    private static WebElement loadMainElement(WebDriver driver, Integer currentPageNumber, Integer lastPageNumber, Optional<Integer> currNumOfResults) throws ContentLoadingException {
        WebElement resultsDiv = null;
        FluentWait wait = new FluentWait(driver);
        wait.withTimeout(Duration.ofSeconds(30));
        wait.pollingEvery(Duration.ofMillis(500));
        wait.ignoring(NoSuchElementException.class);
        if (notOnJobsPage(driver)) {
            return null;
        }
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".jobs-search-results-list__subtitle,.jobs-search-no-results-banner")));
            try {
                resultsDiv = driver.findElement(By.className("jobs-search-results-list__subtitle"));
            } catch (NoSuchElementException nse) {
                if (currentPageNumber < lastPageNumber - 1) {
                    driver.navigate().back();
                    resultsDiv = loadMainElement(driver, currentPageNumber, lastPageNumber, currNumOfResults);
                } else if (currentPageNumber == (lastPageNumber - 1) && currNumOfResults.isPresent()) {
                    int approxResultsSeen = 25 * currentPageNumber;
                    int approxRemainingResults = currNumOfResults.get() - approxResultsSeen;
                    if (approxRemainingResults > 10) {
                        driver.navigate().back();
                        resultsDiv = loadMainElement(driver, currentPageNumber, lastPageNumber, currNumOfResults);
                    }
                }
            }
        } catch (TimeoutException te) {
            throw new ContentLoadingException();
        }
        return resultsDiv;
    }

    private String getNumResultsStringCollections(WebDriver driver) {
        WebElement resultsDiv = driver.findElement(By.className("jobs-search-results-list__title-heading"));
        Document doc = Jsoup.parse(resultsDiv.getAttribute("innerHTML"));
        String numberText = "";
        for (Element small : doc.getElementsByTag("small")) {
            if (small.text().contains("results")) {
                numberText = getNumResultsTextFromElement(small);
            }
        }

        return numberText;
    }

    private List<Integer> getCurrentAndMaxPage(WebDriver driver) {
        WebElement nextPage = null;
        WebElement pageStateDiv = null;
        try {
            pageStateDiv = driver.findElement(By.className("artdeco-pagination__page-state"));
        } catch (NoSuchElementException nse) {
            if (noMatchingJobs(driver)) {
                return List.of(1, 1);
            } else if (!isPageFullyLoaded(driver)) {
                //we are on a jobs page and need to get back to the main jobs listing
                if (driver.getCurrentUrl().startsWith("https://www.linkedin.com/jobs/view")) {
                    driver.navigate().back();
                } else {
                    driver.navigate().refresh();//TODO at least sometimes we can't get pages when the page is not fully loaded, but we need to scroll to bottom
                }
                try {//waiting does not seem to help
                    Thread.sleep(5_000);
                    pageStateDiv = driver.findElement(By.className("artdeco-pagination__page-state"));
                } catch (InterruptedException | NoSuchElementException nse2) {
                    return null;
                }
            } else {
                return null;
            }
        }
        if (pageStateDiv != null) {
            String pageText = pageStateDiv.getAttribute("innerHTML").replaceAll("\\W", "").substring(4);
            pageText = pageText.replaceAll("of", " ");
            Integer currentPage = Integer.parseInt(pageText.split(" ")[0]);
            Integer maxPage = Integer.parseInt(pageText.split(" ")[1]);
            return List.of(currentPage, maxPage);
        } else {
            return null;
        }

    }

    /**
     * I would like to rely on the simple page source to have this step move quickly and without worrying about DOM
     * problems that being said you can't rely on:
     * Are these results helpful?
     * Help Center
     * Accessibility
     * <p>
     * consistently being there
     *
     * @param driver
     * @return
     */
    private boolean isPageFullyLoaded(WebDriver driver) {
        String pageSource = driver.getPageSource();
        if (pageSource.length() < 1000) {
            return false;
        }
        if (pageSource.contains("There was a problem loading your filters")) {
            return false;
        }

        WebElement resultsDiv = null;
        try {
            resultsDiv = driver.findElement(By.className("jobs-search-results-list__subtitle"));
        } catch (NoSuchElementException nse) {
            return false;
        }
        Optional<Integer> currentNumberOfResults = getCurrentNumberOfResults(driver, resultsDiv);
        if (currentNumberOfResults.isPresent() && currentNumberOfResults.get() > RESULTS_PER_PAGE) {
            WebElement paginationDiv = null;
            try {
                paginationDiv = driver.findElement(By.className("jobs-search-results-list__pagination"));
            } catch (NoSuchElementException nse) {
                driver.navigate().refresh();
                try {
                    Thread.sleep(4_000);
                } catch (Exception ex) {

                }
            }
            if (paginationDiv == null) {
                return false;
            }
        }
        return true;
    }

    private boolean noMatchingJobs(WebDriver driver) {
        String pageSource = driver.getPageSource();
        if (pageSource.contains("No matching jobs found")) {
            return true;
        } else {
            return false;
        }
    }

    private void waitForPageToLoad(WebDriver driver) {
        int maxTries = 10, numTries = 0;

        while ((noMatchingJobs(driver) || !isPageFullyLoaded(driver))
                && numTries < maxTries) {
            driver.navigate().refresh();//sometimes the page has been loaded wrong
            try {
                Thread.sleep(12_000);
            } catch (InterruptedException ex) {

            }
            numTries++;
        }
    }

    private Optional<Integer> getCurrentNumberOfResults(WebDriver driver) {
        WebElement resultsDiv = null;
        try {
            resultsDiv = driver.findElement(By.className("jobs-search-results-list__subtitle"));
        } catch (NoSuchElementException nse) {
            return Optional.empty();
        }
        return getCurrentNumberOfResults(driver, resultsDiv);
    }

    private Optional<Integer> getCurrentNumberOfResults(WebDriver driver, WebElement resultsDiv) {
        if (resultsDiv != null) {
            String innerHTML = resultsDiv.getAttribute("innerHTML");
            int resultsLoc = innerHTML.indexOf(" results");
            if (resultsLoc > 0) {
                int start = innerHTML.indexOf("<!---->") + "<!---->".length();
                if (start < resultsLoc) {
                    String results = innerHTML.substring(start, resultsLoc).replaceAll(",", "");
                    if (NumberUtils.isCreatable(results)) {
                        return Optional.of(Integer.parseInt(results));
                    }
                } else if (innerHTML.indexOf("0 results") > 0) {
                    return Optional.of(0);
                }
            } else if (innerHTML.indexOf(" result") > 0) {
                return Optional.of(1);
            } else {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    private WebElement getNextPageButton(WebDriver driver, Integer currentPage) {
        WebElement nextPage = null;
        try {

            WebElement pageDiv = driver.findElement(By.className("jobs-search-results-list__pagination"));
            Document pageDoc = Jsoup.parse(pageDiv.getAttribute("innerHTML"));
            String attributeVal = "Page " + ++currentPage;
            //Element nextPageElement=pageDoc.getElementsByAttributeValue("aria-label",attributeVal).first();
            nextPage = driver.findElement(By.cssSelector("button[aria-label='" + attributeVal + "']"));
        } catch (NoSuchElementException nse) {
            //this usually  happens when we look for a next page that is not there
        }
        return nextPage;
    }



}
