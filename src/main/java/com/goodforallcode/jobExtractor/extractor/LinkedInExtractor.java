package com.goodforallcode.jobExtractor.extractor;

import com.goodforallcode.jobExtractor.cache.JobCache;
import com.goodforallcode.jobExtractor.filters.FilterFactory;
import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.job.populate.DeepJobPopulator;
import com.goodforallcode.jobExtractor.job.populate.LinkedInDeepJobPopulator;
import com.goodforallcode.jobExtractor.job.populate.LinkedInShallowJobPopulator;
import com.goodforallcode.jobExtractor.job.populate.ShallowJobPopulator;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Getter
@Setter
public class LinkedInExtractor extends Extractor {
    static List<JobFilter> deepSkipFilters = FilterFactory.getDeepFiltersSkip();

    static List<JobFilter> alwaysIncludeDeepFilters = FilterFactory.getAlwaysIncludeDeepFilters();
    static List<JobFilter> alwaysIncludeShallowFilters = FilterFactory.getAlwaysIncludeShallowFilters();
    static List<JobFilter> shallowFilters = FilterFactory.getShallowFilters();
    static List<JobFilter> alwaysExcludeDeepFilters = FilterFactory.getDeepFiltersAlwaysExclude();
    static List<JobFilter> filters = FilterFactory.getDeepFilters();
    static List<JobFilter> filtersTrusted = FilterFactory.getDeepFiltersTrusted();

    static List<JobFilter> shallowFiltersTrusted = FilterFactory.getShallowFiltersTrusted();

    static List<JobFilter> alwaysExcludeDeepFiltersTrusted = FilterFactory.getDeepFiltersAlwaysExcludeTrusted();
    static List<JobFilter> alwaysExcludeShallowFilters = FilterFactory.getShallowFiltersAlwaysExclude();
    static List<JobFilter> shallowSkipFilters = FilterFactory.getShallowFiltersSkip();

    @Override
    public WebDriver login(String userName, String password) {
        WebDriver driver = getWebDriver();
        driver.get("https://www.linkedin.com/");
        driver.findElement(By.id("session_key")).sendKeys(userName);
        driver.findElement(By.id("session_password")).sendKeys(password);

        WebElement sign = driver.findElement(By.className("sign-in-form__submit-btn--full-width"));
        doubleClickOnElement(driver, sign);
        return driver;

    }

    private static WebDriver getWebDriver() {
        System.setProperty("webdriver.gecko.driver", "D:/development/geckodriver.exe");
        WebDriver driver=new FirefoxDriver();
//        WebDriver driver=new HtmlUnitDriver(true);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        return driver;
    }

    public void doubleClickOnElement(WebDriver driver, WebElement element) {
       super.doubleClickOnElement(driver, element);

        if(driver!=null && driver.getTitle().equals("Security Verification | LinkedIn")){
            System.err.println("You need to pass verification to login");
        }
    }

    public boolean includeDeepPass(WebDriver driver,Job currentJob,Preferences preferences){
        boolean includeJob=true;

        if (filtersTrusted.stream().anyMatch(f -> !f.include(preferences, currentJob))) {
            includeJob = false;
            doubleClickOnElement(driver,currentJob.getHideButton());
        }

        if (filters.stream().anyMatch(f -> !f.include(preferences, currentJob))) {
            includeJob = false;
            doubleClickOnElement(driver,currentJob.getHideButton());
        }

        return includeJob;
    }
    @Override
    public JobResult getJobs(Set<Cookie> cookies, Preferences preferences, String url,JobCache cache) {
        List<Job> acceptedJobs = new ArrayList<>();
        List<Job> rejectedJobs = new ArrayList<>();
        WebDriver newDriver=null;
        try {


            newDriver = getWebDriver();
            newDriver.get(url);
            for(Cookie cookie:cookies) {
                newDriver.manage().addCookie(cookie);
            }

            newDriver.get(url);
            int currentPageNum=0;
            WebElement nextPageButton=null;

            DeepJobPopulator deepJobPopulator= getDeepJobPopulator();



            do {
                currentPageNum++;

                //TODO return something more complex that will return at least a no more results flag
                WebElement resultsDiv = loadMainElement(newDriver);
                if(resultsDiv==null){
                    /*
                    we could not get the page.
                    Most likely this is because there are no more results
                    stop processing this job and move onto the next
                     */
                    break;
                }
                nextPageButton = getNextPageButton(newDriver);
                if(preferences.getSkipFirstPages()!=null &&preferences.getSkipFirstPages()>currentPageNum){
                    doubleClickOnElement(newDriver,nextPageButton);
                    continue;
                }

                scrollResultsIntoView(newDriver,resultsDiv);

                Elements items = getJobItems(newDriver,currentPageNum);

                ShallowJobPopulator populator= getShallowJobPopulator();

                for (Element item : items) {

                    final Job currentJob = populator.populateJob(item,newDriver);
                    if(currentJob==null){
                        break;
                    }
                    currentJob.setSourceUrl(url);


                    if(shallowSkipFilters.stream().anyMatch(f->!f.include(preferences, currentJob))){
                        /*we don't want to cache acceptedJobs that are too new otherwise we may
                        not see them again in other future searches
                         */
                        continue;
                    }

                    if(cache.containsJob(currentJob)){
                        excludeJob(newDriver, currentJob);
                        continue;
                    }else{
                        cache.addJob(currentJob);
                    }

                    ExcludeJobResults excludeJobResults = excludeJob(currentJob, preferences,
           newDriver,deepJobPopulator);
                    if(excludeJobResults.exludeJob()){
                        excludeJob(newDriver, currentJob);
                        rejectedJobs.add(currentJob);
                    }else if(excludeJobResults.includeJob()){
                        includeJob(newDriver, acceptedJobs, currentJob);
                    }

                    if(excludeJobResults.skipRemainingJobs()){
                        /*
                        this should be true when there is an indication that the rest of the acceptedJobs on this page
                         will have issues
                         */
                        break;
                    }


                }
                if(nextPageButton!=null){
                    doubleClickOnElement(newDriver,nextPageButton);
                }
            }while (nextPageButton!=null);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(newDriver!=null){
            try {
                newDriver.close();
            }catch (Exception ex){

            }
        }
        return new JobResult(acceptedJobs,rejectedJobs);
    }


    public ExcludeJobResults excludeJob(Job currentJob,Preferences preferences,WebDriver driver
    ,DeepJobPopulator deepJobPopulator){
        boolean includeJob=true;

        if(alwaysExcludeShallowFilters.stream().anyMatch(f->!f.include(preferences, currentJob))){
            return new ExcludeJobResults(false,true,false);
        }
        if (alwaysIncludeShallowFilters.stream().anyMatch(f -> f.include(preferences, currentJob))) {
            return new ExcludeJobResults(true,false,false);
        }

        if(shallowFiltersTrusted.stream().anyMatch(f -> !f.include(preferences, currentJob))) {
            includeJob=false;
        }
        if (includeJob && shallowFilters.stream().anyMatch(f -> !f.include(preferences, currentJob))) {
            includeJob=false;
        }


        if(includeJob) {
            doubleClickOnElement(driver,currentJob.getJobDetailsLink());
            try{
                if(driver!=null) {
                    deepJobPopulator.populateJob(currentJob, driver);
                }
            }catch (java.util.concurrent.TimeoutException te){
                return new ExcludeJobResults(false,false,true);
            }
            if (deepSkipFilters.stream().anyMatch(f -> !f.include(preferences, currentJob))) {
                return new ExcludeJobResults(false,false,false);
            }

            if (alwaysExcludeDeepFiltersTrusted.stream().anyMatch(f -> !f.include(preferences, currentJob))) {
                return new ExcludeJobResults(false,true,false);
            }

            //TODO MOVE ALL FILTERS TO TRUSTED AND REMOVE THIS BLOCK
            if (alwaysExcludeDeepFilters.stream().anyMatch(f -> !f.include(preferences, currentJob))) {
                return new ExcludeJobResults(false,true,false);
            }

            if (alwaysIncludeDeepFilters.stream().anyMatch(f -> f.include(preferences, currentJob))) {
                return new ExcludeJobResults(true,false,false);
            }
            includeJob = includeDeepPass(driver, currentJob, preferences);
        }
        return new ExcludeJobResults(includeJob,!includeJob,false);
    }
    private void excludeJob(WebDriver driver, Job currentJob) {
        try {
            doubleClickOnElement(driver, currentJob.getHideButton());
        }catch (MoveTargetOutOfBoundsException ex){
            //swallow this so we continue to remaining work
            //it is not a big deal if we don't hide one job
            System.err.println("out of bounds: "+ currentJob);
        }
    }


    protected  ShallowJobPopulator getShallowJobPopulator() {
        return new LinkedInShallowJobPopulator();
    }

    protected DeepJobPopulator getDeepJobPopulator() {
        return new LinkedInDeepJobPopulator();
    }

    private Elements getJobItems(WebDriver driver, int currentPageNum) {
        WebElement ul = driver.findElement(By.className("scaffold-layout__list-container"));

        Document doc = Jsoup.parse(ul.getAttribute("innerHTML"));
        Elements items = doc.select("li.jobs-search-results__list-item");
        return items;
    }

    private void scrollResultsIntoView(WebDriver driver, WebElement resultsDiv) {
        Document document=Jsoup.parse(resultsDiv.getAttribute("innerHTML"));
        Element span = document.getElementsByTag("span").first();


    /*
    we are hardcoding this to deal with the weirdness of the number of results changing over time.
    I don't want to miss loading some jobs, especially since we scroll quick. I would rather waste a few seconds
    */
        int scrolls=5;
        //we were not loading all of the elements fully but scrolling them into view fixes it
        for(int i=1;i<scrolls*25;i++) {
            ((JavascriptExecutor) driver).executeScript("document.getElementsByClassName('jobs-search-results-list')[0].scrollTop=" +i*75);
            try{
                Thread.sleep(100);
            }catch (Exception ex){

            }
        }
    }

    private static String getNumResultsTextFromElement(Element span) {
        return span.text().replaceAll("<!---->", "").split(" ")[0];
    }

    private static WebElement loadMainElement(WebDriver driver) {
        WebElement resultsDiv=null;
        FluentWait wait = new FluentWait(driver);
        wait.withTimeout(Duration.ofSeconds(3));
        wait.pollingEvery(Duration.ofMillis(250));
        wait.ignoring(NoSuchElementException.class);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".jobs-search-results-list__subtitle,.jobs-search-no-results-banner")));
            try{
                resultsDiv = driver.findElement(By.className("jobs-search-results-list__subtitle"));
            }catch (NoSuchElementException nse) {
                if (driver.findElement(By.className("jobs-search-no-results-banner")) != null) {
                    return null;
                } else {
                    System.err.println();
                }
            }
        }catch (TimeoutException te) {
                System.err.println();
        }
        return resultsDiv;
    }
    private String getNumResultsStringCollections(WebDriver driver) {
        WebElement resultsDiv = driver.findElement(By.className("jobs-search-results-list__title-heading"));
        Document doc=Jsoup.parse(resultsDiv.getAttribute("innerHTML"));
        String numberText="";
        for (Element small :doc.getElementsByTag("small")){
            if(small.text().contains("results")){
                numberText=getNumResultsTextFromElement(small);
            }
        }

        return numberText;
    }

    private WebElement getNextPageButton(WebDriver driver) {
        WebElement nextPage=null;
        try {
            WebElement pageStateDiv = driver.findElement(By.className("artdeco-pagination__page-state"));

            if (pageStateDiv != null) {
                String pageText = pageStateDiv.getAttribute("innerHTML").replaceAll("\\W", "").substring(4);
                pageText = pageText.replaceAll("of", " ");
                Integer currentPage = Integer.parseInt(pageText.split(" ")[0]);
                WebElement pageDiv = driver.findElement(By.className("jobs-search-results-list__pagination"));
                Document pageDoc = Jsoup.parse(pageDiv.getAttribute("innerHTML"));
                String attributeVal = "Page " + ++currentPage;
                //Element nextPageElement=pageDoc.getElementsByAttributeValue("aria-label",attributeVal).first();
                nextPage = driver.findElement(By.cssSelector("button[aria-label='" + attributeVal + "']"));
            }
        }catch (NoSuchElementException nse){
            //this usually  happens when we look for a next page that is not there
        }
        return nextPage;
    }



    public static void main(String[] args) {
        LinkedInExtractor e = new LinkedInExtractor();















    }



}
