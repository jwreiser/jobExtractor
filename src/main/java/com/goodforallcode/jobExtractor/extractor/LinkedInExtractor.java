package com.goodforallcode.jobExtractor.extractor;

import com.goodforallcode.jobExtractor.TestUtil;
import com.goodforallcode.jobExtractor.cache.JobCache;
import com.goodforallcode.jobExtractor.filters.FilterFactory;
import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.job.populate.DeepJobPopulator;
import com.goodforallcode.jobExtractor.job.populate.LinkedInDeepJobPopulator;
import com.goodforallcode.jobExtractor.job.populate.ShallowJobPopulator;
import com.goodforallcode.jobExtractor.job.populate.LinkedInShallowJobPopulator;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;

import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import static com.mongodb.client.model.Indexes.text;

@Getter
@Setter
public class LinkedInExtractor extends Extractor {

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

        if(driver.getTitle().equals("Security Verification | LinkedIn")){
            System.err.println("You need to pass verification to login");
        }
    }

    public boolean includeDeepPass(WebDriver driver,Job currentJob,Preferences preferences,String url){
        boolean includeJob=true;

        List<JobFilter> filters = FilterFactory.getDeepFilters(url, preferences);
        if (filters.stream().anyMatch(f -> !f.include(preferences, currentJob))) {
            includeJob = false;
            doubleClickOnElement(driver,currentJob.getHideButton());
        }

        return includeJob;
    }
    @Override
    public Stream<Job> getJobs(Set<Cookie> cookies, Preferences preferences, String url,JobCache cache) {
        List<Job> jobs = new ArrayList<>();
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

            List<JobFilter> alwaysIncludeDeepFilters = FilterFactory.getAlwaysIncludeDeepFilters(url, preferences);
            List<JobFilter> alwaysIncludeShallowFilters = FilterFactory.getAlwaysIncludeShallowFilters(url, preferences);
            List<JobFilter> alwaysExcludeDeepFilters = FilterFactory.getDeepFiltersAlwaysExclude(url, preferences);
            List<JobFilter> alwaysExcludeShallowFilters = FilterFactory.getShallowFiltersAlwaysExclude(url, preferences);
            List<JobFilter> deepSkipFilters = FilterFactory.getDeepFiltersSkip(url, preferences);
            List<JobFilter> shallowSkipFilters = FilterFactory.getShallowFiltersSkip(url, preferences);
            List<JobFilter> shallowFilters = FilterFactory.getShallowFilters(url, preferences);
            DeepJobPopulator deepJobPopulator= getDeepJobPopulator();
            int numBadJobs,jobsOnPage;

            do {
                currentPageNum++;

                WebElement resultsDiv = loadMainElement(newDriver);

                nextPageButton = getNextPageButton(newDriver);
                if(preferences.getSkipFirstPages()!=null &&preferences.getSkipFirstPages()>currentPageNum){
                    doubleClickOnElement(newDriver,nextPageButton);
                    continue;
                }

                scrollResultsIntoView(newDriver,resultsDiv);

                Elements items = getJobItems(newDriver,currentPageNum);

                boolean includeJob;
                ShallowJobPopulator populator= getShallowJobPopulator();
                numBadJobs=0;
                jobsOnPage=0;

                for (Element item : items) {
                    jobsOnPage++;

                    final Job currentJob = populator.populateJob(item,newDriver);
                    currentJob.setSourceUrl(url);
                    if(currentJob==null){
                        System.err.println(++numBadJobs+" out of "+jobsOnPage);
                        break;
                    }

                    includeJob = true;
                    if(shallowSkipFilters.stream().anyMatch(f->!f.include(preferences, currentJob))){
                        /*we don't want to cache jobs that are too new otherwise we may
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
                    if(alwaysExcludeShallowFilters.stream().anyMatch(f->!f.include(preferences, currentJob))){
                        includeJob = false;
                        excludeJob(newDriver, currentJob);
                        continue;
                    }
                    if (alwaysIncludeShallowFilters.stream().anyMatch(f -> f.include(preferences, currentJob))) {
                        includeJob = true;
                        includeJob(newDriver,jobs,currentJob);
                        continue;
                    }

                    if (shallowFilters.stream().anyMatch(f -> !f.include(preferences, currentJob))) {
                        includeJob = false;
                        excludeJob(newDriver, currentJob);
                    }


                    if(includeJob) {
                        doubleClickOnElement(newDriver,currentJob.getJobDetailsLink());
                        deepJobPopulator.populateJob(currentJob, newDriver);
                        if (deepSkipFilters.stream().anyMatch(f -> !f.include(preferences, currentJob))) {
                            continue;
                        }
                        if (alwaysExcludeDeepFilters.stream().anyMatch(f -> !f.include(preferences, currentJob))) {
                            excludeJob(newDriver, currentJob);
                            continue;
                        }

                        if (alwaysIncludeDeepFilters.stream().anyMatch(f -> f.include(preferences, currentJob))) {
                            includeJob(newDriver, jobs, currentJob);
                            continue;
                        }
                        includeJob = includeDeepPass(newDriver, currentJob, preferences, url);
                    }

                    if (includeJob) {
                        includeJob(newDriver, jobs, currentJob);
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
        return jobs.stream();
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
                    System.err.println("");
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




        List<String> bigCollectionsUrls=List.of(
                //flex pto
                "https://www.linkedin.com/jobs/collections/unlimited-vacation/?currentJobId=3694909287&discover=true&subscriptionOrigin=JOBS_HOME",
                //small business
                "https://www.linkedin.com/jobs/collections/small-business/?currentJobId=3735665450&discover=true&subscriptionOrigin=JOBS_HOME",
                //nonprofit
                "https://www.linkedin.com/jobs/collections/non-profits/?currentJobId=3729190356&discover=true&subscriptionOrigin=JOBS_HOME",
                //mobility tech
                "https://www.linkedin.com/jobs/collections/mobility-tech/?currentJobId=3737834163&discover=true&subscriptionOrigin=JOBS_HOME",
                //healthcare
                "https://www.linkedin.com/jobs/collections/hospitals-and-healthcare/?currentJobId=3715841847&discover=true&subscriptionOrigin=JOBS_HOME",
                //govt
                "https://www.linkedin.com/jobs/collections/government/?currentJobId=3721910416&discover=true&subscriptionOrigin=JOBS_HOME",

                //family friendly
                "https://www.linkedin.com/jobs/collections/family-friendly/?currentJobId=3710666847",
                //401k match
                "https://www.linkedin.com/jobs/collections/employer-401k-match/?currentJobId=3693981987&discover=true&subscriptionOrigin=JOBS_HOME",

                //education
                "https://www.linkedin.com/jobs/collections/education/?currentJobId=3724520461",
                //climate tech
                "https://www.linkedin.com/jobs/collections/climate-and-cleantech/?currentJobId=3737833230&discover=true&subscriptionOrigin=JOBS_HOME",
                //pharmacueticals
                "https://www.linkedin.com/jobs/collections/pharmaceuticals/?currentJobId=3652886006&discover=true&subscriptionOrigin=JOBS_HOME"

        );


     List<String> advantagedUrls=List.of(
                //public trust
//                "https://www.linkedin.com/jobs/search/?currentJobId=3704049774&f_CR=103644278&f_E=2%2C3%2C4&f_F=it&f_JT=F%2CP%2CC%2CT&f_T=9%2C10%2C24&f_WT=2&geoId=92000000&keywords=java%20public%20trust%20-servicenow%20-salesforce%20-senior%20-principal%20-azure%20-devsecops%20-appian%20-mumps%20-sailpoint%20-.net%20-sdet%20-mulesoft%20-drupal%20-infrastructure%20-sap%20-sr.%20-fullstack%20-angular%20-xacta%20-startup%20-php%20-lead%20-bootstrap%20-bi%20-django%20-expertise%20-idam",
                //usc only
//                "https://www.linkedin.com/jobs/search/?currentJobId=3638719250&distance=25&f_F=it&f_T=10738%2C9&f_WT=2&geoId=103644278&keywords=java%20%22usc%20only%22%20-servicenow%20-sailpoint%20-cybersecurity%20-rotation%20-venture%20-fast%20-startup%20-fullstack%20-azure%20-salesforce%20-devops%20-.net%20-lead%20-%20intelligence%20-install",
                //govfirst
                "https://www.linkedin.com/jobs/search/?f_C=80559121&f_WT=2&geoId=92000000&keywords=java%20-jest",
                //inclusively
//                "https://www.linkedin.com/jobs/search/?currentJobId=3736704175&f_C=65909538&f_E=2%2C3%2C4&f_WT=2&geoId=92000000&keywords=java%20-senior",
                //mbi
                "https://www.linkedin.com/jobs/search/?currentJobId=3732049080&f_WT=2&geoId=92000000&keywords=java%20mbi"

        );



        List<String> committmentUrls=List.of(
                //work life balance
//        "https://www.linkedin.com/jobs/search/?currentJobId=3707131603&f_CM=3&f_E=2%2C3%2C4&f_F=it&f_T=9%2C10738&f_WT=2&geoId=103644278&keywords=java%20-affirm&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=R"
                //social impact
//                "https://www.linkedin.com/jobs/search/?currentJobId=3728184698&f_CM=4&f_E=2%2C3%2C4&f_F=it&f_JT=F&f_T=9&f_WT=2&keywords=java&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R"
                //career growth
//                "https://www.linkedin.com/jobs/search/?currentJobId=3739153230&f_CM=5&f_E=2%2C3%2C4&f_F=it&f_JT=F&f_T=9&f_WT=2&keywords=java&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R"
                //sustainable
//                "https://www.linkedin.com/jobs/search/?currentJobId=3708965460&distance=25&f_CM=2&f_E=2%2C3%2C4&f_JT=F&f_T=9%2C10738&f_WT=2&geoId=103644278&keywords=java%20-consultant%20-salesforce%20-fast%20-mainframe%20-affirm%20-machine%20-embedded%20-lead%20-staff%20-electrical%20-.net%20-typescript%20-principal%20-senior%20-azure%20-bairesdev%20-onsite%20-mobile%20-actimize%20-hcm%20-consulting%20-sr.%20-scientific%20-polygraph%20-poly%20-dynamics%20-ruby"
                //DEI
//                "https://www.linkedin.com/jobs/search/?currentJobId=3728885241&f_CM=1&f_E=2%2C3%2C4&f_JT=F&f_T=9%2C10738&f_WT=2&geoId=103644278&keywords=java%20-affirm%20-nvidia%20-pinterest&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true"

        );

        List<String> collectionUrls=List.of(
                //green collection
//                "https://www.linkedin.com/jobs/search/?currentJobId=3731450453&f_E=2%2C3%2C4&f_F=it&f_JC=(0%2Clisystem%2Cgreen-jobs)&f_T=9%2C10738&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=R"

                //top applicant
                "https://www.linkedin.com/jobs/search/?currentJobId=3702242662&distance=25&f_JC=(0%2Clisystem%2Ctop-applicant-jobs)&f_WT=2&geoId=103644278&keywords=java%20-senior%20-sr."

        );

        List<String> jobTypeUrls=List.of(
                //part time/temp
//                "https://www.linkedin.com/jobs/search/?currentJobId=3681755972&distance=25&f_E=2%2C3&f_JT=P%2CT&f_T=9&f_WT=2&geoId=103644278&keywords=java%20-synergisticit%20-sr%20-hpc%20-edi%20-.net%20-consultant%20-servicenow%20-senior%20-wordpress%20-hustlewing%20-mobile%20-scientist%20-aem%20-maximo%20-ruby%20-lead%20-golang%20-ui%20-sap%20-guidewire%20-army%20-salesforce%20-asp.net%20-saml%20-lockheed%20-models%20-eteki%20-angular%20-critical%20-secret"
                //contract
//                "https://www.linkedin.com/jobs/search/?currentJobId=3707540238&distance=25&f_E=2%2C3%2C4&f_JT=C&f_T=10738&f_WT=2&geoId=103644278&keywords=java%20-senior%20-sr.%20-staff%20-principal%20-fullstack%20-frontend%20-bootstrap%20-jquery%20-synergisticit%20-angular%20-jsp%20-jsf%20-sdet%20-guidewire%20-salesforce%20-servicenow%20-saml%20-camunda%20-ecommerce%20-financial%20-sas%20-sap%20-css"
                //other
                "https://www.linkedin.com/jobs/search/?currentJobId=3739999787&f_E=2%2C3&f_JT=O&f_T=9&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true"
        );

        List<String> titleUrls=List.of(
                //backend title
//                "https://www.linkedin.com/jobs/search/?currentJobId=3681178268&distance=25&f_E=2%2C3&f_F=it&f_JT=F&f_T=25194&f_WT=2&geoId=103644278&keywords=java"
                //java developer/java specialist
//                "https://www.linkedin.com/jobs/search/?currentJobId=3731237562&distance=25&f_E=2%2C3%2C4&f_T=10738%2C1660&f_WT=2&geoId=103644278&keywords=java&origin=JOB_SEARCH_PAGE_JOB_FILTER&sortBy=DD"
                //junior titles
//                "https://www.linkedin.com/jobs/search/?currentJobId=3738283272&f_T=2490%2C3549&f_WT=2&geoId=103644278&keywords=java%20-synergisticit&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=DD"
                //programmer analyst
                "https://www.linkedin.com/jobs/search/?currentJobId=3699274371&f_E=2%2C3%2C4&f_T=8660&f_WT=2&geoId=103644278&keywords=java%20&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=R"

        );

        List<String> experienceUrls=List.of(
                //associate HAS
//                "https://www.linkedin.com/jobs/search/?currentJobId=3707674204&f_E=3&f_F=it&f_JT=F&f_T=9%2C10738&f_WT=2&geoId=103644278&keywords=java%20%20&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=DD"
                //entry
                "https://www.linkedin.com/jobs/search/?currentJobId=3715349877&f_E=2&f_JT=F%2CP%2CC&f_T=9%2C10738&f_WT=2&keywords=java&origin=JOB_SEARCH_PAGE_JOB_FILTER&sortBy=DD"
        );
        List<String> miscUrls=List.of(
                //easy apply
//                "https://www.linkedin.com/jobs/search/?currentJobId=3731599390&distance=25&f_AL=true&f_E=2%2C3&f_F=it&f_JT=F&f_T=10738%2C9&f_WT=2&geoId=103644278&keywords=java%20-ui%20-servicenow%20-staff%20-embedded%20-scala%20-fast%20-typescript%20-braintrust%20-frontend%20-sdet%20-cybercoders%20-analytics%20-bootstrap%20-salesforce%20-jobot%20-fingerprint%20-.net%20-php%20-ruby%20-principal%20-sap%20-sas%20-quantumbricks%20-pressure%20-expert&origin=JOB_SEARCH_PAGE_JOB_FILTER&sortBy=R"
        );

        List<String> industryUrls=List.of(
                //software
//                "https://www.linkedin.com/jobs/search/?currentJobId=3737803531&f_E=2%2C3%2C4&f_F=it&f_I=4&f_JT=F&f_T=9%2C10738&f_WT=2&geoId=103644278&keywords=java%20-startup%20%20-ruby%20%20%20-android%20&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=R"
                //good industry
                "https://www.linkedin.com/jobs/search/?currentJobId=3706133609&f_E=2%2C3%2C4&f_I=14%2C17%2C75%2C12%2C124%2C15%2C100%2C132%2C139%2C69%2C114%2C144%2C68%2C70%2C57%2C89%2C115%2C125%2C13%2C130%2C16%2C37%2C67%2C79%2C85%2C86%2C88%2C90&f_JT=F&f_T=9%2C24%2C2385&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=DD"
        );

        List<String> fewApplicantUrls=List.of(
                //less than 10
//                "https://www.linkedin.com/jobs/search/?currentJobId=3693981987&distance=25&f_E=2%2C3%2C4&f_EA=true&f_F=it&f_T=9%2C10738%2C24&f_WT=2&geoId=103644278&keywords=java%20-salesforce%20-ruby%20-php%20-scala%20-startup%20-fast%20-typescript%20-principal%20-servicenow%20-consulting%20-embedded%20-lead%20-cybercoders%20-canonical%20-.net%20-synergisticit%20-mobile%20-mainframe%20-sr.%20-hybrid%20-senior%20-fingerprint%20-bootstrap%20-adobe%20-field%20-staff%20-sap%20-security"
                //all jobs that might be acceptable to be filtered by age and num applicants
                //entry 920
//                "https://www.linkedin.com/jobs/search/?currentJobId=3718516360&f_E=2&f_JT=F&f_T=9%2C39%2C23347%2C10738%2C25201%2C1660&f_WT=2&geoId=103644278&keywords=java%20-tutor%20-affirm%20-startup&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD"
                //associate 500
//                "https://www.linkedin.com/jobs/search/?currentJobId=3707674204&f_E=3&f_JT=F%2CO&f_T=9%2C10738%2C23347%2C39%2C25201%2C1660&f_WT=2&keywords=java&origin=JOB_SEARCH_PAGE_JOB_FILTER&sortBy=DD"
                // mid/senior 950
                "https://www.linkedin.com/jobs/search/?currentJobId=3727208945&f_E=4&f_JT=F&f_T=9%2C39%2C23347%2C10738%2C25201%2C1660&f_WT=2&geoId=103644278&keywords=java%20-startup%20-typescript%20-affirm&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=DD"
        );

        Preferences preferences = TestUtil.getDefaultPreferences();
        preferences.setMinYearlySalary(80000);
        preferences.setMaxJobAgeInDays(60);
//        preferences.setMinJobAgeInDays(2);
//        preferences.setMaxApplicants(90);
        preferences.setMaxLevel(3);
        preferences.setExcludeFresher(true);
        preferences.setExcludeSenior(true);
        preferences.setExcludeBigData(true);
        preferences.setExcludeBlockchain(true);
        preferences.setExcludeCloudHeavy(true);
        preferences.setExcludeComplexJobs(true);
        preferences.setExcludeConsultantCompanies(true);
        preferences.setExcludeProfitFocusedCompanies(true);
        preferences.setExcludePromoted(false);
        preferences.setExcludeIdentityManagement(true);
        preferences.setExcludeRealEstate(true);
        preferences.setAmountOfTotalExperience(6);
        preferences.setMaxEmployees(9000);
        preferences.setSkipFirstPages(null);
        preferences.setSkipTooManyApplicants(false);
        preferences.setSkipJobsSourcedFromExternalJobBoard(false);
    }



}
