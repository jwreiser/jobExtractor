package com.goodforallcode.jobExtractor.controller;

import com.goodforallcode.jobExtractor.util.TestUtil;
import com.goodforallcode.jobExtractor.model.QueryInput;
import com.goodforallcode.jobExtractor.model.preferences.PreferencesWithDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/example")
public class ExampleController {
    @GetMapping("/benefits")
    public QueryInput getBenefitsExample() {
        List<String> benefitUrls = List.of(
                //life balance
                "https://www.linkedin.com/jobs/search/?currentJobId=3696037264&f_E=2%2C3%2C4&f_JT=F%2CP&f_T=9%2C10738%2C25194%2C266%2C23347%2C18134&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java%20life%20balance%20%20&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=DD%22%2C",
                //pension plan (44)
                "https://www.linkedin.com/jobs/search/?currentJobId=3724417602&f_BE=5&f_E=2%2C3%2C4&f_WT=2&keywords=java&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R?"
        );

        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
        return new QueryInput(benefitUrls, preferences, "USERNAME", "PASSWORD");
    }

    @GetMapping("/easyApply")
    public QueryInput getEasyApplyExample() {
        List<String> urls = List.of(
                //entry
                "https://www.linkedin.com/jobs/search/?currentJobId=3755776586&f_AL=true&f_E=2&f_F=it&f_JT=F&f_T=9%2C10738%2C24%2C25194%2C3549&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_LOCATION_HISTORY&refresh=true&sortBy=DD",
                //associate (41)
                "https://www.linkedin.com/jobs/search/?currentJobId=3721910416&f_AL=true&f_E=3&f_F=it&f_JT=F&f_T=9%2C10738%2C24%2C25194%2C3549&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //mid-senior (160)
                "https://www.linkedin.com/jobs/search/?currentJobId=3748846952&f_AL=true&f_E=4&f_F=it&f_JT=F&f_T=9%2C10738%2C24%2C25194%2C3549&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java%20-affirm%20-nvidia%20-pinterest%20-startup%20-php%20-ruby%20-sdet%20-canonical%20-clevertech%20-instructor%20-tutor%20-%22systems%20engineer%22%20-%22full%20stack%22%20%20-mainframe%20-salesforce%20-pega%20-servicenow%20-%22reliability%20engineer%22%20-%22data%20engineer%22%20-embedded&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=DD"
        );

        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
        return new QueryInput(urls, preferences, "USERNAME", "PASSWORD");
    }


    @GetMapping("/companyName")
    public QueryInput getCompanyNameExample() {
        List<String> urls = List.of(
                //neurodivergent (jobs for humanity) (2)
                "https://www.linkedin.com/jobs/search/?currentJobId=3733471087&f_C=35925095&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_LOCATION_AUTOCOMPLETE&refresh=true&sortBy=R",
                //clearance jobs company (14)
                "https://www.linkedin.com/jobs/search/?currentJobId=3756317836&f_C=1007957&f_E=2%2C3%2C4&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java%20&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true",
                //trimble (4)
                "https://www.linkedin.com/jobs/search/?currentJobId=3738942969&f_C=5160%2C96206&f_E=2%2C3%2C4&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java%20-field&location=United%20States&origin=JOB_SEARCH_PAGE_LOCATION_HISTORY&refresh=true&sortBy=DD",

                //cedar (4)
                "https://www.linkedin.com/jobs/search/?currentJobId=3733820005&f_C=10988174&f_T=9&f_WT=2&geoId=103644278&location=United%20States&origin=JOB_SEARCH_PAGE_LOCATION_HISTORY&refresh=true&sortBy=R",
                //nationwide(4)
                "https://www.linkedin.com/jobs/search/?currentJobId=3743131455&f_C=2340&f_E=2%2C3%2C4&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //blackbaud (public good) (87)
                "https://www.linkedin.com/jobs/search/?currentJobId=3723298721&f_C=162724&f_E=2%2C3%2C4&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD"
        );

        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
        return new QueryInput(urls, preferences, "USERNAME", "PASSWORD");
    }


    @GetMapping("/industry")
    public QueryInput getIndustryExample() {
        List<String> urls = List.of(
                //good industries
                //entry (100)
                "https://www.linkedin.com/jobs/search/?currentJobId=3732514403&f_E=2&f_I=14%2C17%2C12%2C86%2C132%2C68%2C114%2C144%2C75%2C124%2C13%2C15%2C88%2C100%2C115%2C125%2C130%2C139%2C16%2C37%2C57%2C67%2C69%2C70%2C79%2C85%2C89%2C90&f_JT=F&f_T=9%2C2385%2C24&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //associate (130)
                "https://www.linkedin.com/jobs/search/?currentJobId=3669566650&f_E=3&f_I=17%2C14%2C57%2C75%2C132%2C100%2C114%2C115%2C12%2C124%2C125%2C13%2C130%2C139%2C144%2C15%2C16%2C37%2C67%2C68%2C69%2C70%2C79%2C85%2C86%2C88%2C89%2C90&f_T=9%2C2385%2C24&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //mid-senior (200)
                "https://www.linkedin.com/jobs/search/?currentJobId=3736766463&f_E=4&f_I=14%2C15%2C124%2C12%2C17%2C68%2C132%2C144%2C70%2C139%2C69%2C75%2C57%2C100%2C115%2C90%2C67%2C86%2C125%2C13%2C88%2C114%2C130%2C16%2C37%2C79%2C85%2C89&f_JT=F&f_T=9%2C2385%2C24%2C1660%2C18630&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD"
        );
        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
        return new QueryInput(urls, preferences, "USERNAME", "PASSWORD");
    }

    @GetMapping("/experience")
    public QueryInput getExperienceExample() {
        List<String> urls = List.of(
                //entry
                // it services and it consulting
                "https://www.linkedin.com/jobs/search/?currentJobId=3757161325&f_E=2&f_I=96&f_JT=F&f_T=9%2C10738%2C1660%2C23347%2C25201%2C39&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=DD",
                //staffing and recruiting (130)
                "https://www.linkedin.com/jobs/search/?currentJobId=3749393379&f_E=2&f_I=104&f_JT=F&f_T=9%2C10738%2C1660%2C23347%2C25201%2C39&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=DD",
                //technology information and internet (shows up as internet as well)
                "https://www.linkedin.com/jobs/search/?currentJobId=3757947520&f_E=2&f_I=6&f_JT=F&f_T=9%2C1660%2C10738%2C25201%2C23347%2C39%2C24&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java%20-affirm%20-nvidia%20-pinterest%20-startup%20-php%20-ruby%20-sdet%20-canonical%20-clevertech%20-instructor%20-tutor%20-%22systems%20engineer%22%20-%22full%20stack%22%20%20-mainframe%20-salesforce%20-pega%20-servicenow%20-%22reliability%20engineer%22%20-%22data%20engineer%22%20-embedded&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=DD",
                //software development
                "https://www.linkedin.com/jobs/search/?currentJobId=3728880334&f_E=2&f_I=4&f_JT=F&f_T=9%2C1660%2C10738%2C23347%2C25201%2C39&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java%20-affirm%20-nvidia%20-pinterest%20-startup%20-php%20-ruby%20-sdet%20-canonical%20-clevertech%20-instructor%20-tutor%20-%22systems%20engineer%22%20-%22full%20stack%22%20%20-mainframe%20-salesforce%20-pega%20-servicenow%20-embedded&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=DD",
                //hr
                "https://www.linkedin.com/jobs/search/?currentJobId=3751278911&f_E=2&f_I=137&f_JT=F&f_T=9%2C10738%2C1660%2C23347%2C25201%2C39&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=DD",
                //it and services, hospital and healthcare
                "https://www.linkedin.com/jobs/search/?currentJobId=3724672502&f_E=2&f_I=14%2C3231&f_JT=F&f_T=9%2C10738%2C25201%2C1660%2C23347%2C39&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java%20&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //telecom
                "https://www.linkedin.com/jobs/search/?currentJobId=3736964839&f_E=2&f_I=8&f_JT=F&f_T=9%2C10738%2C1660%2C23347%2C25201%2C39&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java%20&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //all non shady industries that don't have a  big number of job positions (as big industries search on their own)
                //(100)
                "https://www.linkedin.com/jobs/search/?currentJobId=3751496265&f_E=2&f_I=133%2C17%2C12%2C3%2C112%2C114%2C68%2C75%2C84%2C116%2C122%2C124%2C126%2C132%2C135%2C144%2C33%2C51%2C55%2C88%2C100%2C101%2C102%2C103%2C105%2C107%2C108%2C110%2C111%2C113%2C115%2C119%2C120%2C125%2C127%2C13%2C130%2C134%2C136%2C138%2C139%2C141%2C145%2C146%2C147%2C148%2C150%2C16%2C20%2C23%2C24%2C28%2C30%2C31%2C32%2C34%2C35%2C37%2C38%2C39%2C40%2C49%2C53%2C56%2C57%2C62%2C63%2C66%2C67%2C69%2C7%2C70%2C73%2C74%2C82%2C83%2C85%2C86%2C87%2C89%2C90%2C91%2C92%2C93%2C95%2C99&f_JT=F&f_T=9%2C10738%2C25201%2C1660%2C23347%2C39&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java%20&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",

                //associate 150
                "https://www.linkedin.com/jobs/search/?currentJobId=3756377114&f_E=3&f_JT=F%2CO&f_T=9%2C10738%2C23347%2C39%2C25201%2C1660&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java%20&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=DD"

        );
        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
        preferences.setMaxJobAgeInDays(7);
        preferences.setMaxApplicants(400);
        preferences.setExcludeFresher(true);
        preferences.setExcludeBigData(true);
        preferences.setExcludeBlockchain(true);
        preferences.setExcludeCloudHeavy(true);
        preferences.setExcludeComplexJobs(true);
        preferences.setExcludeRealEstate(true);
        preferences.setMaxEmployees(9000);

        return new QueryInput(urls, preferences, "USERNAME", "PASSWORD");
    }


    @GetMapping("/title")
    public QueryInput getTitleExample() {
        List<String> urls = List.of(
                //backend title
                "https://www.linkedin.com/jobs/search/?currentJobId=3700465813&distance=25&f_E=2%2C3%2C4&f_F=it&f_JT=F&f_T=25194&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java&origin=JOB_SEARCH_PAGE_JOB_FILTER&sortBy=R",
                //java software engineer (50)
                "https://www.linkedin.com/jobs/search/?currentJobId=3756377114&distance=25&f_E=2%2C3%2C4&f_T=10738&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java&origin=JOB_SEARCH_PAGE_JOB_FILTER&sortBy=DD",
                //java specialist
                "https://www.linkedin.com/jobs/search/?currentJobId=3754750353&f_E=2%2C3%2C4&f_T=1660&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java%20-tutor&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //junior titles (20)
                "https://www.linkedin.com/jobs/search/?currentJobId=3738283272&f_T=2490%2C3549&f_WT=2&geoId=103644278&keywords=java%20-synergisticit&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=DD",
                //developer (100)
                "https://www.linkedin.com/jobs/search/?currentJobId=3757366141&f_E=2%2C3%2C4&f_F=it&f_JT=F&f_T=24&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //web developer (10)
                "https://www.linkedin.com/jobs/search/?currentJobId=3750631444&f_E=2%2C3%2C4&f_F=it&f_JT=F&f_T=100&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //programmer analyst (20)
                "https://www.linkedin.com/jobs/search/?currentJobId=3755586053&f_E=2%2C3%2C4&f_T=8660&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java%20&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R"

        );

        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
        return new QueryInput(urls, preferences, "USERNAME", "PASSWORD");
    }

    @GetMapping("/jobType")
    public QueryInput getJobTypeExample() {
        List<String> urls = List.of(
                //part time/temp (35)
                "https://www.linkedin.com/jobs/search/?currentJobId=3758553191&f_E=2%2C3%2C4&f_JT=P%2CT&f_T=9%2C1660&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true",
                //contract
                "https://www.linkedin.com/jobs/search/?currentJobId=3754259084&distance=25&f_E=2%2C3&f_JT=C&f_T=9%2C10738&f_TPR=r2592000&f_WT=2&geoId=103644278&keywords=java%20-senior%20-sr.%20-staff%20-principal%20-fullstack%20-frontend%20-bootstrap%20-jquery%20-synergisticit%20-angular%20-jsp%20-jsf%20-sdet%20-guidewire%20-salesforce%20-servicenow%20-saml%20-camunda%20-ecommerce%20-financial%20-sas%20-sap%20-css&origin=JOB_SEARCH_PAGE_JOB_FILTER&sortBy=DD",
                //other
                "https://www.linkedin.com/jobs/search/?currentJobId=3747505201&f_E=2%2C3%2C4&f_JT=O&f_T=9%2C10738&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true"
        );

        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
        return new QueryInput(urls, preferences, "USERNAME", "PASSWORD");
    }

    @GetMapping("/collections")
    public QueryInput getCollectionsExample() {
        List<String> urls = List.of(
                //green collection (7)
                "https://www.linkedin.com/jobs/search/?currentJobId=3757947521&f_E=2%2C3%2C4&f_F=it&f_JC=(0%2Clisystem%2Cgreen-jobs)&f_T=9%2C10738&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",

                //top applicant (7)
                "https://www.linkedin.com/jobs/search/?currentJobId=3754989229&distance=25&f_JC=(0%2Clisystem%2Ctop-applicant-jobs)&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java%20-senior%20-sr.&origin=JOB_SEARCH_PAGE_JOB_FILTER"
        );

        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
        preferences.setMaxJobAgeInDays(23);
        preferences.setMaxApplicants(400);
        preferences.setExcludeFresher(true);
        preferences.setExcludeBigData(true);
        preferences.setExcludeBlockchain(true);
        preferences.setExcludeCloudHeavy(true);
        preferences.setExcludeComplexJobs(true);
        preferences.setExcludeRealEstate(true);
        preferences.setMaxEmployees(9000);

        return new QueryInput(urls, preferences, "USERNAME", "PASSWORD");
    }


    @GetMapping("/comittments")
    public QueryInput getComittmentsExample() {
        List<String> urls = List.of(
                //work life balance
                "https://www.linkedin.com/jobs/search/?currentJobId=3702007909&f_CM=3&f_E=2%2C3%2C4&f_F=it&f_JT=F&f_T=9%2C10738%2C24&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",

                //social impact
                // entry/associate
                "https://www.linkedin.com/jobs/search/?currentJobId=3751602656&f_CM=4&f_E=2%2C3&f_F=it&f_JT=F&f_T=9%2C25194%2C24&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java%20&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //mid-senior
                "https://www.linkedin.com/jobs/search/?currentJobId=3707130746&f_CM=4&f_E=4&f_F=it&f_JT=F&f_T=9%2C25194%2C24&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java%20&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",

                //career growth
                "https://www.linkedin.com/jobs/search/?currentJobId=3702007909&f_CM=5&f_E=2%2C3%2C4&f_F=it&f_JT=F&f_T=9&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java%20&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",

                //sustainable
                // entry/associate
                "https://www.linkedin.com/jobs/search/?currentJobId=3734762817&f_CM=2&f_E=2%2C3&f_JT=F&f_T=9%2C24%2C10738&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",
                //mid-senior
                "https://www.linkedin.com/jobs/search/?currentJobId=3643180375&f_CM=2&f_E=4&f_JT=F&f_T=9%2C24%2C10738&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",

                //DEI
                // entry/associate
                "https://www.linkedin.com/jobs/search/?currentJobId=3751602656&f_CM=1&f_E=2%2C3&f_JT=F&f_T=9%2C10738&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //mid-senior
                "https://www.linkedin.com/jobs/search/?currentJobId=3707130746&f_CM=1&f_E=4&f_JT=F&f_T=9%2C10738&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=D"
        );

        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
        return new QueryInput(urls, preferences, "USERNAME", "PASSWORD");
    }


    @GetMapping("/advantaged")
    public QueryInput getAdvantagedExample() {
        List<String> urls = List.of(
                //public trust
                //entry/associate
                "https://www.linkedin.com/jobs/search/?currentJobId=3757161325&f_CR=103644278&f_E=2%2C3&f_F=it&f_JT=F&f_T=9%2C10%2C24%2C10738&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java%20public%20trust%20&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //mid-senior
                "https://www.linkedin.com/jobs/search/?currentJobId=3736509369&f_CR=103644278&f_E=4&f_F=it&f_JT=F&f_T=9%2C10%2C24%2C10738&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java%20public%20trust%20&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",

                //usc only
                "https://www.linkedin.com/jobs/search/?currentJobId=3753242818&f_E=2%2C3%2C4&f_F=it&f_JT=F&f_T=10738%2C9%2C25194%2C24&f_WT=2&geoId=103644278&keywords=java%20%22usc%20only%22&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //govfirst
                "https://www.linkedin.com/jobs/search/?currentJobId=3750071059&f_C=80559121&f_WT=2&geoId=103644278&keywords=java%20-jest&location=United%20States&origin=JOB_SEARCH_PAGE_LOCATION_HISTORY&refresh=true",
                //inclusively
                "https://www.linkedin.com/jobs/search/?currentJobId=3756334396&f_C=65909538&f_E=2%2C3%2C4&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true",
                //mbi
                "https://www.linkedin.com/jobs/search/?currentJobId=3756876266&f_WT=2&geoId=103644278&keywords=java%20mbi&location=United%20States&origin=JOB_SEARCH_PAGE_LOCATION_HISTORY&refresh=true",

                //in network
                //entry/associate
                "https://www.linkedin.com/jobs/search/?currentJobId=3757161325&f_E=2%2C3&f_JIYN=true&f_T=9%2C24%2C10738&f_TPR=r604800&f_WT=2&keywords=java&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //mid-senior(130)
                "https://www.linkedin.com/jobs/search/?currentJobId=3748504605&f_E=4&f_JIYN=true&f_T=9%2C24%2C10738&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java%20-affirm%20-nvidia%20-pinterest%20-startup%20-php%20-ruby%20-sdet%20-canonical%20-clevertech%20-instructor%20-tutor%20%20-senior%20%20-mainframe%20-salesforce%20-pega%20-servicenow%20%20-embedded%20-crossover&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=DD"

        );

        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
        return new QueryInput(urls, preferences, "USERNAME", "PASSWORD");
    }
/////////////////////                                 BLOCKS                                 ////////////////////////////

    @GetMapping("/small")
    public QueryInput getSmallAmountOfResults() {
        List<String> urls = List.of(
                //pension plan (25)
                "https://www.linkedin.com/jobs/search/?currentJobId=3806664610&f_BE=5&f_E=1%2C2%2C3%2C4&f_T=10%2C9%2C25194&f_WT=2&keywords=java&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",

                //other job type(70)
                "https://www.linkedin.com/jobs/search/?currentJobId=3878309912&f_E=2%2C3%2C4&f_JT=O&f_T=9%2C10738&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true",

                //green collection (0)
                "https://www.linkedin.com/jobs/search/?currentJobId=3896959046&f_E=1%2C2%2C3%2C4&f_JC=(0%2Clisystem%2Cgreen-jobs)&f_T=9%2C10738&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",


                //public trust(150)
                "https://www.linkedin.com/jobs/search/?currentJobId=3901421121&f_E=2%2C3%2C4&f_JT=F%2CP&f_T=9%2C10&f_TPR=r2592000&f_WT=2&geoId=103644278&keywords=java%20public%20trust&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",

                //usc only (10)
                "https://www.linkedin.com/jobs/search/?currentJobId=3782250575&f_E=2%2C3%2C4&f_JT=F&f_T=9%2C10738%2C24%2C25194%2C23347&f_WT=2&geoId=103644278&keywords=java%20%22usc%20only%22&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",
                //company name : govfirst (0)
                "https://www.linkedin.com/jobs/search/?currentJobId=3901943215&f_C=80559121&f_E=2%2C3%2C4&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true",
                //company name : inclusively (0)
                "https://www.linkedin.com/jobs/search/?currentJobId=3756334396&f_C=65909538&f_E=2%2C3%2C4&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true",
                //mbi (10)
                "https://www.linkedin.com/jobs/search/?currentJobId=3756876266&f_WT=2&geoId=103644278&keywords=java%20mbi&location=United%20States&origin=JOB_SEARCH_PAGE_LOCATION_HISTORY&refresh=true",

                //company name : jobs for humanity (neurodivergent)  (2)
                "https://www.linkedin.com/jobs/search/?currentJobId=3901522481&f_C=35925095&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=R",
                //company name : clearance jobs (0)
                "https://www.linkedin.com/jobs/search/?currentJobId=3756317836&f_C=1007957&f_E=2%2C3%2C4&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java%20&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true",
                //company name : trimble (0)
                "https://www.linkedin.com/jobs/search/?currentJobId=3738942969&f_C=5160%2C96206&f_E=2%2C3%2C4&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java%20-field&location=United%20States&origin=JOB_SEARCH_PAGE_LOCATION_HISTORY&refresh=true&sortBy=DD",
                //company name : fearless(10)
                "https://www.linkedin.com/jobs/search/?currentJobId=3754729701&f_C=771382&f_E=2%2C3%2C4&f_T=9%2C10738%2C1660&f_WT=2&geoId=92000000&keywords=java&location=Worldwide&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",
                //company name : cedar (0)
                "https://www.linkedin.com/jobs/search/?currentJobId=3733820005&f_C=10988174&f_T=9&f_WT=2&geoId=103644278&location=United%20States&origin=JOB_SEARCH_PAGE_LOCATION_HISTORY&refresh=true&sortBy=R",
                //company name : nationwide(0)
                "https://www.linkedin.com/jobs/search/?currentJobId=3743131455&f_C=2340&f_E=2%2C3%2C4&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //company name : blackbaud (public good) (50)
                "https://www.linkedin.com/jobs/search/?currentJobId=3723298721&f_C=162724&f_E=2%2C3%2C4&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //company name : adhoc llc (0)
                "https://www.linkedin.com/jobs/search/?currentJobId=3758139279&f_C=10350118&f_E=2%2C3%2C4&f_WT=2&geoId=92000000&keywords=java%20-salesforce&location=Worldwide&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true",

                //ENTRY LEVEL-industry: technology information and internet (shows up as internet as well),internet, telecom (100)
                "https://www.linkedin.com/jobs/search/?currentJobId=3902737428&f_E=2&f_I=6%2C3132%2C1594%2C8%2C1285%2C3124&f_JT=F&f_T=9%2C10738%2C25201%2C1660%2C23347%2C24%2C39&f_TPR=r2592000&f_WT=2&geoId=103644278&keywords=java%20-affirm%20-nvidia%20-pinterest%20-startup%20-php%20-ruby%20-sdet%20-canonical%20-clevertech%20-instructor%20-tutor%20-%22systems%20engineer%22%20-%22full%20stack%22%20%20-mainframe%20-salesforce%20-pega%20-servicenow%20-%22reliability%20engineer%22%20-%22data%20engineer%22%20-embedded&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",

                //ENTRY LEVEL-industry: it and services, hospital and healthcare (80)
                "https://www.linkedin.com/jobs/search/?currentJobId=3900995107&f_E=2&f_I=14%2C3231&f_JT=F&f_T=9%2C10738%2C25201%2C1660%2C23347%2C39&f_WT=2&geoId=103644278&keywords=java%20&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",


                //commitments:career growth (50)
                "https://www.linkedin.com/jobs/search/?currentJobId=3792042786&f_CM=5&f_E=2%2C3&f_F=it&f_JT=F&f_T=9&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=DD",

                //string searches:mission -entry/associate(100)
                "https://www.linkedin.com/jobs/search/?currentJobId=3841357708&f_E=2%2C3&f_JT=F&f_T=9%2C25194%2C10738%2C266%2C14642&f_WT=2&geoId=103644278&keywords=%22Java%22%20%20%22mission%22%20-%22permission%22%20-decommissioning%20-commission%20%20-%22Quantitative%20Medicine%20Developer%22%20-%22full-stack%22%20-%22Database%20Developer%22%20-%22years%20professional%20python%22%20-%22Senior%20Software%20Engineer%22%20-%22Cloud%20Engineer%22%20-%22Test%20Engineer%22%20-%22Machine%20Learning%20Engineer%22%20-%22Frontend%20Software%20Engineer%22%20-%22Research%20Engineer%22%20-%22Staff%20Software%20Engineer%22-django%20-%22ServiceNow%20Developer%22%20-%22Principal%20Engineer%22%20-%22Full%20Stack%22%20-%22Lead%20Backend%22%20-%22Distinguished%20Java%20Engineer%22%20-%22Java%20Software%20Engineer%20III%22%20-%22Sr.%20Software%20Engineer%22%20-%22Principal%20Software%20Engineer%22%20-%22Java%20Enterprise%20Account%20Manager%22%20-%22Java%20AWS%20Developer%22%20-%22Site%20Reliability%20Engineer%22%20-%22Cloud%20Developer%22%20-startup%20-%22Principal%20Software%20Developer%22%20-%22Senior%20Software%20Developer%22%20-%22React%20Native%20Developer%22%20-%22Senior%20Backend%22%20-Betting%20-Telecommunication%20-%22Senior%20Android%22%20-%22Java%20FSE%22%20-consulting%20-SailPoint%20-Salesforce%20-%22Software%20Developer%205%22%20-%22must%20be%20local%20to%20DC%22%20-%22AI%2FML%20Engineer%22%20-%22Quality%20Engineer%22%20-%22EDI%20Developer%22%20-Android%20-%22FileNet%20Developer%22%20-%22Principal%20Software%20Digital%20Engineer%22%20-%22QA%20Engineer%22%20-%22Lead%20Software%20Engineer%22%20-%22DevOps%20Engineer%22%20-%22Remote%20Data%20Scientist%22%20-%22REACT%20Developer%22%20-%22Solutions%20Engineer%22%20-%22Data%20Engineer%22%20-%22Security%20Engineer%22%20-%22Infrastructure%20Engineer%22%20-%22Senior%20Data%20Scientist%22%20-%22Systems%20Engineer%22%20-%22Advanced%20Software%20Engineer%22%20-%22Staff%20Backend%20Engineer%22%20-%22Developer%20Advocate%22%20-%22Scientific%20Software%20Developer%22%20-%22Platform%20Engineer%22%20-%22.NET%20Developer%22%20-%22Front%20End%20Engineer%22%20-%22Unity%20Developer%22%20-%22Game%20Developer%22%20-%22NET%20and%20Web%20Developer%22%20-%22Integration%20Engineer%22%20-%22Staff%20Engineer%22%20-%22Frontend%20Developer%22%20-%22Web%20Developer%22%20-%22Front%20End%20SW%20Engineer%22%20-%22Integration%20Engineer%22%20-%22UI%20Engineer%22%20-%22Frontend%20Developer%22%20-%22Front%20end%20Developer%22%20-%22Software%20Engineer%20(.NET)%22%20-%22Front%20End%20Software%20Engineer%22%20-detectives%20-%22Geospatial%20Software%20Engineer%22%20-%22low%20latency%22%20-ecommerce%20-Kyruus%20-%22years%20typescript%22%20-%22low-code%22%20-Cloudflare%20-TIDAL&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",


                //commitments:DEI -entry/associate(50)
                "https://www.linkedin.com/jobs/search/?currentJobId=3900984532&f_CM=1&f_E=2%2C3&f_JT=F&f_T=9%2C10738%2C25194&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java%20%20-affirm%20-nvidia%20-pinterest%20-canonical%20-clevertech%20-crossover&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",

                //company name : SUNY SB (3)
                "https://www.linkedin.com/jobs/search/?currentJobId=3888073005&f_C=7201%2C15696722&f_E=2%2C3%2C4&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true",


                //junior software engineer (60)
                "https://www.linkedin.com/jobs/search/?currentJobId=3847800242&f_T=3549&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=R",
                //web developer (10)
                "https://www.linkedin.com/jobs/search/?currentJobId=3831195983&f_E=2%2C3%2C4&f_JT=F&f_T=100&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",
                //programmer analyst (20)
                "https://www.linkedin.com/jobs/search/?currentJobId=3755586053&f_E=2%2C3%2C4&f_T=8660&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java%20&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R"


                );

        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
        return new QueryInput(urls, preferences, "USERNAME", "PASSWORD");
    }

    //few applicants
    @GetMapping("/lessThan10")
    public QueryInput getLessThan10Applicants() {
        List<String> urls = List.of(

                //less than 10
                //entry (530)
                "https://www.linkedin.com/jobs/search/?currentJobId=3740816787&f_E=2&f_EA=true&f_F=it&f_JT=F&f_T=9%2C24%2C10738&f_WT=2&geoId=103644278&keywords=java%20-affirm%20-nvidia%20-pinterest%20-startup%20-php%20-ruby%20-sdet%20-canonical%20-clevertech%20-instructor%20-tutor%20-%22systems%20engineer%22%20-%22full%20stack%22%20-senior%20-field%20-mainframe%20-salesforce%20-pega%20-servicenow%20-%22reliability%20engineer%22%20-%22data%20engineer%22%20-bootstrap&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //associate (30) NO JAVA
                "https://www.linkedin.com/jobs/search/?currentJobId=3715401956&f_E=3&f_EA=true&f_F=it&f_JT=F%2CO&f_T=9%2C10738%2C24&f_TPR=r604800&f_WT=2&geoId=103644278&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=DD",
                //mid-senior (310)
                "https://www.linkedin.com/jobs/search/?currentJobId=3758551290&f_E=4&f_EA=true&f_F=it&f_JT=F&f_T=9%2C10738%2C24&f_WT=2&geoId=103644278&keywords=java%20-affirm%20-nvidia%20-pinterest%20-startup%20-php%20-ruby%20-sdet%20-canonical%20-clevertech%20-instructor%20-tutor%20-%22systems%20engineer%22%20-%22full%20stack%22%20-senior%20-field%20-mainframe%20-salesforce%20-pega%20-servicenow%20-%22reliability%20engineer%22%20-%22data%20engineer%22%20-bootstrap%20-typescript%20-android%20-embedded&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=DD"
        );

        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
//Not having this differentiates this from few applicants        preferences.setMinJobAgeInDays(2);
        preferences.setMaxApplicants(90);

        preferences.setExcludeFresher(false);
        preferences.setSkipTooManyApplicants(true);
        preferences.setSkipUnknownNumberOfApplicants(true);
        preferences.setSkipJobsSourcedFromExternalJobBoard(true);

        return new QueryInput(urls, preferences, "USERNAME", "PASSWORD");
    }

    @GetMapping("/staffingAndConsulting")
    public QueryInput getStaffingAndConsulting() {
        List<String> urls = List.of(

                //staffing and recruiting (800)
                "https://www.linkedin.com/jobs/search/?currentJobId=3904842198&f_E=2%2C3%2C4&f_I=104&f_JT=F&f_T=9%2C10738%2C1660&f_TPR=r2592000&f_WT=2&geoId=103644278&keywords=java%20-consultant%20-consulting%20%20-cobol%20-ios%20-salesforce%20-drupal&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //it services and IT consulting (123)
                "https://www.linkedin.com/jobs/search/?currentJobId=3902731390&f_E=2&f_I=96&f_JT=F&f_T=9%2C10738%2C1660&f_TPR=r2592000&f_WT=2&geoId=103644278&keywords=java%20-%22SAIC%22%20-%22Outcoder%20iO%22%20-groundswell%20-affirm%20-nvidia%20-pinterest%20-daugherty%20-canonical%20-clevertech%20-crossover%20-softrams%20-startup%20%20-instructor%20-tutor%20-mainframe%20-salesforce%20-pega%20-servicenow%20-embedded%20-%22systems%20engineer%22%20-%22full%20stack%22%20-bootstrap%20-typescript%20-android%20-senior%20-principal%20-lead%20-sr.%20-staff%20-consultant%20-consulting%20%20-php%20-ruby%20-field&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //human resources services (121)
                "https://www.linkedin.com/jobs/search/?currentJobId=3904465771&f_E=2%2C3%2C4&f_I=137&f_JT=F&f_T=9%2C10738%2C1660&f_TPR=r2592000&f_WT=2&geoId=103644278&keywords=java%20-consultant%20-consulting%20%20-cobol%20-ios%20-salesforce%20-drupal&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD"
                );

        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
//Not having this differentiates this from few applicants        preferences.setMinJobAgeInDays(2);
        preferences.setMaxApplicants(90);

        preferences.setExcludeFresher(true);
        preferences.setSkipTooManyApplicants(true);
        preferences.setSkipUnknownNumberOfApplicants(true);
        preferences.setSkipJobsSourcedFromExternalJobBoard(true);
        preferences.setExcludeConsultant(false);
        return new QueryInput(urls, preferences, "USERNAME", "PASSWORD");
    }


    //few applicants
    @GetMapping("/fewApplicants")
    public QueryInput getFewApplicants() {

        List<String> urls = List.of(
                //entry
                // it services and it consulting
                "https://www.linkedin.com/jobs/search/?currentJobId=3740816787&f_E=2&f_I=96&f_JT=F&f_T=9%2C10738%2C1660%2C23347%2C25201%2C39&f_WT=2&geoId=103644278&keywords=java%20-affirm%20-nvidia%20-pinterest%20-startup%20-php%20-ruby%20-sdet%20-canonical%20-clevertech%20-instructor%20-tutor%20-%22systems%20engineer%22%20-%22full%20stack%22%20-senior%20-field%20-mainframe%20-salesforce%20-pega%20-servicenow%20-%22reliability%20engineer%22%20-%22data%20engineer%22%20-bootstrap%20-typescript%20-android%20-embedded&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",

                //commitments:work life balance (600)
                "https://www.linkedin.com/jobs/search/?currentJobId=3819105099&f_CM=3&f_E=2%2C3%2C4&f_F=it&f_JT=F&f_T=9%2C24%2C10738%2C23347&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",

                //staffing and recruiting
                "https://www.linkedin.com/jobs/search/?currentJobId=3757254549&f_E=2&f_I=104&f_JT=F&f_T=9%2C10738%2C1660%2C23347%2C25201%2C39&f_WT=2&geoId=103644278&keywords=java%20-affirm%20-nvidia%20-pinterest%20-startup%20-php%20-ruby%20-sdet%20-canonical%20-clevertech%20-instructor%20-tutor%20-%22systems%20engineer%22%20-%22full%20stack%22%20-senior%20-field%20-mainframe%20-salesforce%20-pega%20-servicenow%20-%22reliability%20engineer%22%20-%22data%20engineer%22%20-bootstrap%20-typescript%20-android%20-embedded&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //technology information and internet (shows up as internet as well)
                "https://www.linkedin.com/jobs/search/?currentJobId=3757946891&f_E=2&f_I=6&f_JT=F&f_T=9%2C10738%2C1660%2C23347%2C25201%2C39&f_WT=2&geoId=103644278&keywords=java%20-affirm%20-nvidia%20-pinterest%20-startup%20-php%20-ruby%20-sdet%20-canonical%20-clevertech%20-instructor%20-tutor%20-%22systems%20engineer%22%20-%22full%20stack%22%20-senior%20-field%20-mainframe%20-salesforce%20-pega%20-servicenow%20-%22reliability%20engineer%22%20-%22data%20engineer%22%20-bootstrap%20-typescript%20-android%20-embedded&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //software development
                "https://www.linkedin.com/jobs/search/?currentJobId=3740816787&f_E=2&f_I=4&f_JT=F&f_T=9%2C10738%2C1660%2C23347%2C25201%2C39&f_WT=2&geoId=103644278&keywords=java%20-affirm%20-nvidia%20-pinterest%20-startup%20-php%20-ruby%20-sdet%20-canonical%20-clevertech%20-instructor%20-tutor%20-%22systems%20engineer%22%20-%22full%20stack%22%20-senior%20-field%20-mainframe%20-salesforce%20-pega%20-servicenow%20-%22reliability%20engineer%22%20-%22data%20engineer%22%20-bootstrap%20-typescript%20-android%20-embedded&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //hr
                "https://www.linkedin.com/jobs/search/?currentJobId=3749994619&f_E=2&f_I=137&f_JT=F&f_T=9%2C10738%2C1660%2C23347%2C25201%2C39&f_WT=2&geoId=103644278&keywords=java%20-affirm%20-nvidia%20-pinterest%20-startup%20-php%20-ruby%20-sdet%20-canonical%20-clevertech%20-instructor%20-tutor%20-%22systems%20engineer%22%20-%22full%20stack%22%20-senior%20-field%20-mainframe%20-salesforce%20-pega%20-servicenow%20-%22reliability%20engineer%22%20-%22data%20engineer%22%20-bootstrap%20-typescript%20-android%20-embedded&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //it and services, hospital and healthcare
                "https://www.linkedin.com/jobs/search/?currentJobId=3735125412&f_E=2&f_I=3231%2C14&f_JT=F&f_T=9%2C10738%2C1660%2C23347%2C25201%2C39&f_WT=2&geoId=103644278&keywords=java%20&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=DD",

                //telecom
                "https://www.linkedin.com/jobs/search/?currentJobId=3715349877&f_E=2&f_I=8&f_JT=F&f_T=9%2C25201%2C10738%2C1660%2C23347%2C39&f_WT=2&geoId=103644278&keywords=java%20&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //all non shady industries that don't have a  big number of job positions (as big industries search on their own)
                "https://www.linkedin.com/jobs/search/?currentJobId=3755781961&f_E=2&f_I=119%2C93%2C133%2C16%2C130%2C7%2C33%2C17%2C12%2C3%2C114%2C68%2C75%2C84%2C112%2C116%2C122%2C124%2C126%2C132%2C135%2C51%2C88%2C100%2C101%2C102%2C103%2C105%2C107%2C108%2C110%2C111%2C113%2C115%2C120%2C125%2C127%2C13%2C134%2C136%2C138%2C139%2C141%2C144%2C145%2C146%2C147%2C148%2C150%2C20%2C23%2C24%2C28%2C30%2C31%2C32%2C34%2C35%2C37%2C38%2C39%2C40%2C49%2C53%2C55%2C56%2C57%2C62%2C63%2C66%2C67%2C69%2C70%2C73%2C74%2C82%2C83%2C85%2C86%2C87%2C89%2C90%2C91%2C92%2C95%2C99&f_JT=F&f_T=9%2C10738%2C25201%2C1660%2C23347%2C39&f_WT=2&geoId=103644278&keywords=java%20&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",


                //associate 361
                "https://www.linkedin.com/jobs/search/?currentJobId=3730575940&f_E=3&f_JT=F%2CO&f_T=9%2C10738%2C23347%2C39%2C25201%2C1660&f_WT=2&geoId=103644278&keywords=java%20-affirm%20-nvidia%20-pinterest%20-startup%20-php%20-ruby%20-sdet%20-canonical%20-clevertech%20-instructor%20-tutor%20-%22systems%20engineer%22%20-%22full%20stack%22%20%20-mainframe%20-salesforce%20-pega%20-servicenow%20-embedded&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=DD"
        );

        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
        preferences.setMaxJobAgeInDays(90);
        preferences.setMinJobAgeInDays(2);
        preferences.setMaxApplicants(90);

        preferences.setExcludeFresher(false);
        preferences.setSkipTooManyApplicants(true);
        preferences.setSkipUnknownNumberOfApplicants(true);
        preferences.setSkipJobsSourcedFromExternalJobBoard(true);

        return new QueryInput(urls, preferences, "USERNAME", "PASSWORD");
    }


    //few applicants
    @GetMapping("/fewApplicants/senior")
    public QueryInput getFewApplicantsSenior() {

        List<String> urls = List.of(
                // mid/senior: it services and it consulting
                "https://www.linkedin.com/jobs/search/?currentJobId=3741605661&f_E=4&f_I=96&f_JT=F&f_T=9%2C10738%2C39%2C1660%2C23347&f_WT=2&geoId=103644278&keywords=java%20-startup%20-php%20-ruby%20-sdet%20-canonical%20-clevertech%20-instructor%20-tutor%20-%22systems%20engineer%22%20-%22full%20stack%22%20-senior%20-field%20-mainframe%20-salesforce%20-pega%20-servicenow%20-%22reliability%20engineer%22%20-%22data%20engineer%22%20-bootstrap%20-typescript%20-android%20-embedded%20-principal%20-lead%20-sr.%20%20-staff%20-crossover%20%20-.net&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //mid/senior: staffing and recruiting
                "https://www.linkedin.com/jobs/search/?currentJobId=3745429021&f_E=4&f_I=104&f_JT=F&f_T=9%2C10738%2C39%2C1660%2C23347&f_WT=2&geoId=103644278&keywords=java%20-startup%20-php%20-ruby%20-sdet%20-canonical%20-clevertech%20-instructor%20-tutor%20-%22systems%20engineer%22%20-%22full%20stack%22%20-senior%20-field%20-mainframe%20-salesforce%20-pega%20-servicenow%20-%22reliability%20engineer%22%20-%22data%20engineer%22%20-bootstrap%20-typescript%20-android%20-embedded%20-principal%20-lead%20-sr.%20%20-staff%20-crossover%20%20-.net&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //mid/senior: tech information and internet
                "https://www.linkedin.com/jobs/search/?currentJobId=3757504320&f_E=4&f_I=6&f_JT=F&f_T=9%2C10738%2C1660%2C23347%2C39&f_WT=2&geoId=103644278&keywords=java%20-startup%20-php%20-ruby%20-sdet%20-canonical%20-clevertech%20-instructor%20-tutor%20-%22systems%20engineer%22%20-%22full%20stack%22%20-senior%20-field%20-mainframe%20-salesforce%20-pega%20-servicenow%20-%22reliability%20engineer%22%20-%22data%20engineer%22%20-bootstrap%20-typescript%20-android%20-embedded%20-principal%20-lead%20-sr.%20%20-staff%20-crossover%20%20-.net&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //mid/senior: software development
                "https://www.linkedin.com/jobs/search/?currentJobId=3757504320&f_E=4&f_I=4&f_JT=F&f_T=9%2C10738%2C1660%2C23347%2C39&f_WT=2&geoId=103644278&keywords=java%20-startup%20-php%20-ruby%20-sdet%20-canonical%20-clevertech%20-instructor%20-tutor%20-%22systems%20engineer%22%20-%22full%20stack%22%20-senior%20-field%20-mainframe%20-salesforce%20-pega%20-servicenow%20-%22reliability%20engineer%22%20-%22data%20engineer%22%20-bootstrap%20-typescript%20-android%20-embedded%20-principal%20-lead%20-sr.%20%20-staff%20-crossover%20%20-.net&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //mid/senior: hospital and healthcare(700)
                "https://www.linkedin.com/jobs/search/?currentJobId=3884172493&f_E=4&f_I=14&f_WT=2&keywords=java&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",
                //mid/senior: all non shady industries that don't have a  big number of job positions (as big industries search on their own)
                "https://www.linkedin.com/jobs/search/?currentJobId=3854824434&f_E=4&f_I=119%2C93%2C133%2C16%2C130%2C7%2C33%2C17%2C12%2C3%2C114%2C68%2C75%2C84%2C112%2C116%2C122%2C124%2C126%2C132%2C135%2C51%2C88%2C100%2C101%2C102%2C103%2C105%2C107%2C108%2C110%2C111%2C113%2C115%2C120%2C125%2C127%2C13%2C134%2C136%2C138%2C139%2C141%2C144%2C145%2C146%2C147%2C148%2C150%2C20%2C23%2C24%2C28%2C30%2C31%2C32%2C34%2C35%2C37%2C38%2C39%2C40%2C49%2C53%2C55%2C56%2C57%2C62%2C63%2C66%2C67%2C69%2C70%2C73%2C74%2C82%2C83%2C85%2C86%2C87%2C89%2C90%2C91%2C92%2C95%2C99&f_JT=F&f_T=9%2C10738%2C25201%2C1660%2C23347%2C39&f_WT=2&geoId=103644278&keywords=java%20-affirm%20-nvidia%20-pinterest%20-startup%20-php%20-ruby%20-sdet%20-canonical%20-clevertech%20-instructor%20-tutor%20-%22systems%20engineer%22%20-%22full%20stack%22%20-field%20-mainframe%20-salesforce%20-pega%20-servicenow%20-%22reliability%20engineer%22%20-%22data%20engineer%22%20-bootstrap%20-typescript%20-android%20-embedded&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R"
        );

        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
        preferences.setMaxJobAgeInDays(90);
        preferences.setMinJobAgeInDays(2);
        preferences.setMaxApplicants(90);

        preferences.setExcludeFresher(false);
        preferences.setSkipTooManyApplicants(true);
        preferences.setSkipUnknownNumberOfApplicants(true);
        preferences.setSkipJobsSourcedFromExternalJobBoard(true);

        return new QueryInput(urls, preferences, "USERNAME", "PASSWORD");
    }

    @GetMapping("/midsize")
    public QueryInput getMidSizeAmountOfResults() {
        List<String> urls = List.of(
                //all non shady industries that don't have a  big number of job positions (as big industries search on their own) (400)
        "https://www.linkedin.com/jobs/search/?currentJobId=3901324353&f_E=2%2C3%2C4&f_I=100%2C67%2C17%2C12%2C124%2C75%2C139%2C132%2C91%2C69%2C70%2C68%2C148%2C53%2C57%2C16%2C115%2C144%2C34%2C82%2C86%2C105%2C114%2C90%2C13%2C23%2C88%2C108%2C38%2C63%2C89%2C103%2C111%2C125%2C127%2C130%2C136%2C150%2C35%2C37%2C39%2C40%2C66%2C73%2C85%2C95&f_JT=F&f_T=9%2C39%2C23347%2C10738%2C1660&f_TPR=r2592000&f_WT=2&geoId=103644278&keywords=java%20&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",

                //commitments:work life balance (300)
                "https://www.linkedin.com/jobs/search/?currentJobId=3871316357&f_CM=3&f_E=2%2C3%2C4&f_F=it&f_JT=F&f_T=9%2C24%2C10738%2C23347&f_TPR=r2592000&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",

                //developer (200)
                "https://www.linkedin.com/jobs/search/?currentJobId=3896575771&f_E=2%2C3%2C4&f_F=it&f_JT=F&f_T=24&f_TPR=r2592000&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
               //NYC Metro: entry/associate(250)
                "https://www.linkedin.com/jobs/search/?currentJobId=3898192733&f_E=2%2C3&f_T=9%2C24%2C10&f_TPR=r604800&f_WT=2&geoId=90000070&keywords=java&location=New%20York%20City%20Metropolitan%20Area&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",

                //backend title(275)
                "https://www.linkedin.com/jobs/search/?currentJobId=3861293576&distance=25&f_E=2%2C3%2C4&f_JT=F&f_T=25194&f_TPR=r2592000&f_WT=2&geoId=103644278&keywords=java&origin=JOB_SEARCH_PAGE_JOB_FILTER&sortBy=R",

                //associate (250)
                "https://www.linkedin.com/jobs/search/?currentJobId=3762937161&f_E=3&f_JT=F&f_T=9%2C10738%2C1660&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java%20-groundswell%20-affirm%20-nvidia%20-pinterest%20-daugherty%20%20-canonical%20-clevertech%20-startup%20%20-instructor%20-tutor%20%20%20-mainframe%20-salesforce%20-pega%20-servicenow%20-embedded%20%20-%22full%20stack%22%20-consultant%20-consulting%20%20-php%20-ruby&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=DD",

                //java titles (300):java software engineer, senior java software engineer,java specialist
                "https://www.linkedin.com/jobs/search/?currentJobId=3866548570&f_E=2%2C3%2C4&f_JT=F&f_T=23347%2C1660%2C10738&f_TPR=r2592000&f_WT=2&keywords=java&origin=JOB_SEARCH_PAGE_JOB_FILTER&sortBy=R",

                //software development (178)
                "https://www.linkedin.com/jobs/search/?currentJobId=3765249459&f_E=2&f_F=eng%2Cit&f_I=4&f_JT=F&f_T=9%2C10738%2C1660&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java%20-groundswell%20-affirm%20-nvidia%20-pinterest%20-daugherty%20-startup%20-canonical%20-clevertech%20-instructor%20-tutor%20-mainframe%20-salesforce%20-pega%20-servicenow%20-embedded%20-%22systems%20engineer%22%20-%22full%20stack%22%20-bootstrap%20-typescript%20%20-consultant%20-consulting%20%20-php%20-ruby&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=DD",

                //part time/temp (200)
                "https://www.linkedin.com/jobs/search/?currentJobId=3842171386&f_E=2%2C3%2C4&f_JT=P%2CT&f_T=9%2C1660%2C24&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true",

                //social impact -entry/associate(100)
                "https://www.linkedin.com/jobs/search/?currentJobId=3865193152&f_CM=4&f_E=2%2C3&f_JT=F&f_T=9%2C14827%2C23131%2C2385%2C24&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",
//contract(195)
//                "https://www.linkedin.com/jobs/search/?currentJobId=3754259084&distance=25&f_E=2%2C3&f_JT=C&f_T=9%2C10738&f_TPR=r2592000&f_WT=2&geoId=103644278&keywords=java%20-senior%20-sr.%20-staff%20-principal%20-fullstack%20-frontend%20-bootstrap%20-jquery%20-synergisticit%20-angular%20-jsp%20-jsf%20-sdet%20-guidewire%20-salesforce%20-servicenow%20-saml%20-camunda%20-ecommerce%20-financial%20-sas%20-sap%20-css&origin=JOB_SEARCH_PAGE_JOB_FILTER&sortBy=DD",

                //good industries-entry/associate(250)
                "https://www.linkedin.com/jobs/search/?currentJobId=3893176876&f_E=2%2C3&f_I=14%2C12%2C124%2C100%2C15%2C17%2C70%2C132%2C67%2C139%2C69%2C144%2C68%2C115%2C13%2C57%2C86%2C16%2C90%2C114%2C125%2C130%2C37%2C85%2C88%2C89&f_JT=F%2CP%2CT&f_T=9%2C2385%2C24&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",


                //string searches: passion (350)
                "https://www.linkedin.com/jobs/search/?currentJobId=3849603901&f_E=2%2C3%2C4&f_JT=F&f_T=9%2C25194%2C10738%2C266%2C14642&f_WT=2&geoId=103644278&keywords=%22Java%22%20%20%22passion%22%20%20-%22Quantitative%20Medicine%20Developer%22%20-%22full-stack%22%20-%22Database%20Developer%22%20-%22years%20professional%20python%22%20-%22Senior%20Software%20Engineer%22%20-%22Cloud%20Engineer%22%20-%22Test%20Engineer%22%20-%22Machine%20Learning%20Engineer%22%20-%22Frontend%20Software%20Engineer%22%20-%22Research%20Engineer%22%20-%22Staff%20Software%20Engineer%22-django%20-%22ServiceNow%20Developer%22%20-%22Principal%20Engineer%22%20-%22Full%20Stack%22%20-%22Lead%20Backend%22%20-%22Distinguished%20Java%20Engineer%22%20-%22Java%20Software%20Engineer%20III%22%20-%22Sr.%20Software%20Engineer%22%20-%22Principal%20Software%20Engineer%22%20-%22Java%20Enterprise%20Account%20Manager%22%20-%22Java%20AWS%20Developer%22%20-%22Site%20Reliability%20Engineer%22%20-%22Cloud%20Developer%22%20-startup%20-%22Principal%20Software%20Developer%22%20-%22Senior%20Software%20Developer%22%20-%22React%20Native%20Developer%22%20-%22Senior%20Backend%22%20-Betting%20-Telecommunication%20-%22Senior%20Android%22%20-%22Java%20FSE%22%20-consulting%20-SailPoint%20-Salesforce%20-%22Software%20Developer%205%22%20-%22must%20be%20local%20to%20DC%22%20-%22AI%2FML%20Engineer%22%20-%22Quality%20Engineer%22%20-%22EDI%20Developer%22%20-Android%20-%22FileNet%20Developer%22%20-%22Principal%20Software%20Digital%20Engineer%22%20-%22QA%20Engineer%22%20-%22Lead%20Software%20Engineer%22%20-%22DevOps%20Engineer%22%20-%22Remote%20Data%20Scientist%22%20-%22REACT%20Developer%22%20-%22Solutions%20Engineer%22%20-%22Data%20Engineer%22%20-%22Security%20Engineer%22%20-%22Infrastructure%20Engineer%22%20-%22Senior%20Data%20Scientist%22%20-%22Systems%20Engineer%22%20-%22Advanced%20Software%20Engineer%22%20-%22Staff%20Backend%20Engineer%22%20-%22Developer%20Advocate%22%20-%22Scientific%20Software%20Developer%22%20-%22Platform%20Engineer%22%20-%22.NET%20Developer%22%20-%22Front%20End%20Engineer%22%20-%22Unity%20Developer%22%20-%22Game%20Developer%22%20-%22NET%20and%20Web%20Developer%22%20-%22Integration%20Engineer%22%20-%22Staff%20Engineer%22%20-%22Frontend%20Developer%22%20-%22Web%20Developer%22%20-%22Front%20End%20SW%20Engineer%22%20-%22Integration%20Engineer%22%20-%22UI%20Engineer%22%20-%22Frontend%20Developer%22%20-%22Front%20end%20Developer%22%20-%22Software%20Engineer%20(.NET)%22%20-%22Front%20End%20Software%20Engineer%22%20-detectives%20-%22Geospatial%20Software%20Engineer%22%20-%22low%20latency%22%20-%22funding%22%20-Specialist%20-%22start-up%22%20-financial%20-%22fortune%20500%22%20-%22front-end%22%20-Scala%20-%22reside%20dmv%22%20-%22Sr%20Developer%22%20-%22Principal%20Backend%22%20-frontend%20-%22Technical%20Lead%22%20-founding%20-%22Programmer%20Analyst%206%22%20-Appian%20-%22Developer%20Evangelist%22%20-%22Technical%20Services%20Engineer%22%20-installation%20-%22low-code%22%20-contract%20-%22Software%20Development%20Engineer%20Test%22%20-%22Software%20Development%20Engineer%20III%22%20-Fullstack%20-%22Performance%20Engineer%22&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",



                //NY:entry/associate (400)
                "https://www.linkedin.com/jobs/search/?currentJobId=3894233251&f_E=2%2C3&f_JT=F&f_T=9%2C10%2C24&f_TPR=r604800&geoId=105080838&keywords=java&location=New%20York%2C%20United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R"

                );

        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
        return new QueryInput(urls, preferences, "USERNAME", "PASSWORD");
    }

    @GetMapping("/onsite")
    public QueryInput onsite() {
        List<String> urls = List.of(
                //part time/temp (200)
        "https://www.linkedin.com/jobs/search/?currentJobId=3888079850&f_E=2%2C3%2C4&f_JT=P%2CT&f_T=9%2C1660%2C24&f_WT=1%2C3&geoId=105080838&keywords=java&location=New%20York%2C%20United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true",

                //string searches: passion (10)
                "https://www.linkedin.com/jobs/search/?currentJobId=3857715987&f_E=2%2C3%2C4&f_JT=F&f_T=9%2C25194%2C10738%2C266%2C14642&f_WT=1%2C3&geoId=105080838&keywords=%22Java%22%20%20%22passion%22%20%20-%22Quantitative%20Medicine%20Developer%22%20-%22full-stack%22%20-%22Database%20Developer%22%20-%22years%20professional%20python%22%20-%22Senior%20Software%20Engineer%22%20-%22Cloud%20Engineer%22%20-%22Test%20Engineer%22%20-%22Machine%20Learning%20Engineer%22%20-%22Frontend%20Software%20Engineer%22%20-%22Research%20Engineer%22%20-%22Staff%20Software%20Engineer%22-django%20-%22ServiceNow%20Developer%22%20-%22Principal%20Engineer%22%20-%22Full%20Stack%22%20-%22Lead%20Backend%22%20-%22Distinguished%20Java%20Engineer%22%20-%22Java%20Software%20Engineer%20III%22%20-%22Sr.%20Software%20Engineer%22%20-%22Principal%20Software%20Engineer%22%20-%22Java%20Enterprise%20Account%20Manager%22%20-%22Java%20AWS%20Developer%22%20-%22Site%20Reliability%20Engineer%22%20-%22Cloud%20Developer%22%20-startup%20-%22Principal%20Software%20Developer%22%20-%22Senior%20Software%20Developer%22%20-%22React%20Native%20Developer%22%20-%22Senior%20Backend%22%20-Betting%20-Telecommunication%20-%22Senior%20Android%22%20-%22Java%20FSE%22%20-consulting%20-SailPoint%20-Salesforce%20-%22Software%20Developer%205%22%20-%22must%20be%20local%20to%20DC%22%20-%22AI%2FML%20Engineer%22%20-%22Quality%20Engineer%22%20-%22EDI%20Developer%22%20-Android%20-%22FileNet%20Developer%22%20-%22Principal%20Software%20Digital%20Engineer%22%20-%22QA%20Engineer%22%20-%22Lead%20Software%20Engineer%22%20-%22DevOps%20Engineer%22%20-%22Remote%20Data%20Scientist%22%20-%22REACT%20Developer%22%20-%22Solutions%20Engineer%22%20-%22Data%20Engineer%22%20-%22Security%20Engineer%22%20-%22Infrastructure%20Engineer%22%20-%22Senior%20Data%20Scientist%22%20-%22Systems%20Engineer%22%20-%22Advanced%20Software%20Engineer%22%20-%22Staff%20Backend%20Engineer%22%20-%22Developer%20Advocate%22%20-%22Scientific%20Software%20Developer%22%20-%22Platform%20Engineer%22%20-%22.NET%20Developer%22%20-%22Front%20End%20Engineer%22%20-%22Unity%20Developer%22%20-%22Game%20Developer%22%20-%22NET%20and%20Web%20Developer%22%20-%22Integration%20Engineer%22%20-%22Staff%20Engineer%22%20-%22Frontend%20Developer%22%20-%22Web%20Developer%22%20-%22Front%20End%20SW%20Engineer%22%20-%22Integration%20Engineer%22%20-%22UI%20Engineer%22%20-%22Frontend%20Developer%22%20-%22Front%20end%20Developer%22%20-%22Software%20Engineer%20(.NET)%22%20-%22Front%20End%20Software%20Engineer%22%20-detectives%20-%22Geospatial%20Software%20Engineer%22%20-%22low%20latency%22%20-%22funding%22%20-Specialist%20-%22start-up%22%20-financial%20-%22fortune%20500%22%20-%22front-end%22%20-Scala%20-%22reside%20dmv%22%20-%22Sr%20Developer%22%20-%22Principal%20Backend%22%20-frontend%20-%22Technical%20Lead%22%20-founding%20-%22Programmer%20Analyst%206%22%20-Appian%20-%22Developer%20Evangelist%22%20-%22Technical%20Services%20Engineer%22%20-installation%20-%22low-code%22%20-contract%20-%22Software%20Development%20Engineer%20Test%22%20-%22Software%20Development%20Engineer%20III%22%20-Fullstack%20-%22Performance%20Engineer%22&location=New%20York%2C%20United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",

                //string searches:mission (20)
                "https://www.linkedin.com/jobs/search/?currentJobId=3854355611&f_E=2%2C3%2C4&f_JT=F&f_T=9%2C25194%2C10738%2C266%2C14642&f_WT=1%2C3&geoId=105080838&keywords=%22Java%22%20%20%22mission%22%20-%22permission%22%20-decommissioning%20-commission%20%20-%22Quantitative%20Medicine%20Developer%22%20-%22full-stack%22%20-%22Database%20Developer%22%20-%22years%20professional%20python%22%20-%22Senior%20Software%20Engineer%22%20-%22Cloud%20Engineer%22%20-%22Test%20Engineer%22%20-%22Machine%20Learning%20Engineer%22%20-%22Frontend%20Software%20Engineer%22%20-%22Research%20Engineer%22%20-%22Staff%20Software%20Engineer%22-django%20-%22ServiceNow%20Developer%22%20-%22Principal%20Engineer%22%20-%22Full%20Stack%22%20-%22Lead%20Backend%22%20-%22Distinguished%20Java%20Engineer%22%20-%22Java%20Software%20Engineer%20III%22%20-%22Sr.%20Software%20Engineer%22%20-%22Principal%20Software%20Engineer%22%20-%22Java%20Enterprise%20Account%20Manager%22%20-%22Java%20AWS%20Developer%22%20-%22Site%20Reliability%20Engineer%22%20-%22Cloud%20Developer%22%20-startup%20-%22Principal%20Software%20Developer%22%20-%22Senior%20Software%20Developer%22%20-%22React%20Native%20Developer%22%20-%22Senior%20Backend%22%20-Betting%20-Telecommunication%20-%22Senior%20Android%22%20-%22Java%20FSE%22%20-consulting%20-SailPoint%20-Salesforce%20-%22Software%20Developer%205%22%20-%22must%20be%20local%20to%20DC%22%20-%22AI%2FML%20Engineer%22%20-%22Quality%20Engineer%22%20-%22EDI%20Developer%22%20-Android%20-%22FileNet%20Developer%22%20-%22Principal%20Software%20Digital%20Engineer%22%20-%22QA%20Engineer%22%20-%22Lead%20Software%20Engineer%22%20-%22DevOps%20Engineer%22%20-%22Remote%20Data%20Scientist%22%20-%22REACT%20Developer%22%20-%22Solutions%20Engineer%22%20-%22Data%20Engineer%22%20-%22Security%20Engineer%22%20-%22Infrastructure%20Engineer%22%20-%22Senior%20Data%20Scientist%22%20-%22Systems%20Engineer%22%20-%22Advanced%20Software%20Engineer%22%20-%22Staff%20Backend%20Engineer%22%20-%22Developer%20Advocate%22%20-%22Scientific%20Software%20Developer%22%20-%22Platform%20Engineer%22%20-%22.NET%20Developer%22%20-%22Front%20End%20Engineer%22%20-%22Unity%20Developer%22%20-%22Game%20Developer%22%20-%22NET%20and%20Web%20Developer%22%20-%22Integration%20Engineer%22%20-%22Staff%20Engineer%22%20-%22Frontend%20Developer%22%20-%22Web%20Developer%22%20-%22Front%20End%20SW%20Engineer%22%20-%22Integration%20Engineer%22%20-%22UI%20Engineer%22%20-%22Frontend%20Developer%22%20-%22Front%20end%20Developer%22%20-%22Software%20Engineer%20(.NET)%22%20-%22Front%20End%20Software%20Engineer%22%20-detectives%20-%22Geospatial%20Software%20Engineer%22%20-%22low%20latency%22%20-ecommerce%20-Kyruus%20-%22years%20typescript%22%20-%22low-code%22%20-Cloudflare%20-TIDAL&location=New%20York%2C%20United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",

                //melville (200)
                "https://www.linkedin.com/jobs/search/?currentJobId=3796909738&distance=10&f_E=2%2C3%2C4&f_T=9%2C10&f_WT=1%2C3&geoId=106027227&keywords=java&location=Melville%2C%20New%20York%2C%20United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",
                //suffolk county(230)
                "https://www.linkedin.com/jobs/search/?currentJobId=3877311901&f_E=2%2C3%2C4&f_T=9%2C24%2C10&f_WT=1%2C3&geoId=104148244&keywords=java&location=Suffolk%20County%2C%20New%20York%2C%20United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",
                //holtsville(173)
                "https://www.linkedin.com/jobs/search/?currentJobId=3877311901&distance=10&f_E=2%2C3%2C4&f_JT=F&f_T=9%2C24%2C10738&f_WT=1%2C3&geoId=102615215&keywords=java%20&location=Holtsville%2C%20New%20York%2C%20United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",
                //central islip(170)
                "https://www.linkedin.com/jobs/search/?currentJobId=3755843816&distance=10&f_E=2%2C3%2C4&f_JT=F&f_T=9%2C24%2C10738&f_WT=1%2C3&geoId=104554220&keywords=java%20&location=Central%20Islip%2C%20New%20York%2C%20United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R&start=25",
                //patchogue(170)
                "https://www.linkedin.com/jobs/search/?currentJobId=3877311901&distance=10&f_E=2%2C3%2C4&f_JT=F&f_T=9%2C24%2C10738&f_WT=1%2C3&geoId=100205004&keywords=java%20&location=Patchogue%2C%20New%20York%2C%20United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",
                //bayshore(170)
                "https://www.linkedin.com/jobs/search/?currentJobId=3796909738&distance=10&f_E=2%2C3%2C4&f_JT=F&f_T=9%2C10738%2C24&f_WT=3%2C1&geoId=101927337&keywords=java%20&location=Bay%20Shore%2C%20New%20York%2C%20United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",
                //Hauppauge (174)
                "https://www.linkedin.com/jobs/search/?currentJobId=3890808075&distance=10&f_E=2%2C3%2C4&f_JT=F&f_T=9%2C24%2C10738&f_WT=1%2C3&geoId=102570389&keywords=java&location=Hauppauge%2C%20New%20York%2C%20United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true",
                //good industries(250)
                "https://www.linkedin.com/jobs/search/?currentJobId=3877311901&f_E=2%2C3%2C4&f_I=133%2C17%2C12%2C3%2C112%2C114%2C68%2C75%2C84%2C116%2C122%2C124%2C126%2C132%2C135%2C144%2C33%2C51%2C55%2C88%2C100%2C101%2C102%2C103%2C105%2C107%2C108%2C110%2C111%2C113%2C115%2C119%2C120%2C125%2C127%2C13%2C130%2C134%2C136%2C138%2C139%2C141%2C145%2C146%2C147%2C148%2C150%2C16%2C20%2C23%2C24%2C28%2C30%2C31%2C32%2C34%2C35%2C37%2C38%2C39%2C40%2C49%2C53%2C56%2C57%2C62%2C63%2C66%2C67%2C69%2C7%2C70%2C73%2C74%2C82%2C83%2C85%2C86%2C87%2C89%2C90%2C91%2C92%2C93%2C95%2C99&f_JT=F&f_T=9%2C39%2C10738%2C23347%2C1660&f_TPR=r2592000&f_WT=1%2C3&geoId=105080838&keywords=java%20&location=New%20York%2C%20United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",
                //commitments:work life balance (50)
                "https://www.linkedin.com/jobs/search/?currentJobId=3674035417&f_CM=3&f_E=2%2C3%2C4&f_F=it&f_JT=F&f_T=9%2C24%2C10738%2C23347&f_TPR=r2592000&f_WT=1%2C3&geoId=105080838&keywords=java&location=New%20York%2C%20United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",

                //sb location (170)
                "https://www.linkedin.com/jobs/search/?currentJobId=3890808075&distance=10&f_E=2%2C3%2C4&f_T=19163%2C848%2C39%2C9&f_WT=1%2C3&geoId=106434598&keywords=java%20&location=Stony%20Brook%2C%20New%20York%2C%20United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R"
        );

        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
        preferences.setExcludeNonRemote(false);
        return new QueryInput(urls, preferences, "USERNAME", "PASSWORD");
    }

    @GetMapping("/large")
        public QueryInput getLargeAmountOfResults() {
            List<String> urls = List.of(
                //commitments:sustainable  (500)
                "https://www.linkedin.com/jobs/search/?currentJobId=3812223457&f_CM=2&f_E=2%2C3%2C4&f_JT=F&f_T=9%2C24%2C10738%2C1660&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",
                //entry/associate (500)
                "https://www.linkedin.com/jobs/search/?currentJobId=3900812277&distance=25&f_E=2%2C3&f_JT=F&f_T=9%2C24%2C10%2C1660&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",
                //string searches: software engineer: entry/associate (550)
                "https://www.linkedin.com/jobs/search/?currentJobId=3863628626&f_E=2%2C3&f_JT=F&f_T=9%2C10738%2C1660%2C23347%2C25194&f_TPR=r2592000&f_WT=2&geoId=103644278&keywords=%22Software%20Engineer%22%20java%20%20-php%20-ruby%20-scala%20-.net%20-django%20%20-%22Senior%20Software%20Engineer%22%20%20-%22Senior%20Software%20Developer%22%20-%22Full%20Stack%20Developer%22%20-%22Sr.%20Software%20Developer%22%20-%22Sr.%20Software%20Engineer%22%20-%22Systems%20Engineer%22%20-%22Staff%20Software%20Engineer%22%20-%22Solutions%20Engineer%22&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=R",
                //good industries(500)
                "https://www.linkedin.com/jobs/search/?currentJobId=3869625431&f_E=4&f_I=14%2C100%2C67%2C124%2C12%2C139%2C15%2C17%2C132%2C69%2C70%2C68%2C57%2C115%2C16%2C144%2C86%2C13%2C90%2C114%2C88%2C89%2C125%2C130%2C37%2C85&f_JT=F%2CP%2CT&f_T=9%2C2385%2C24&f_TPR=r2592000&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",
                //PAST week - entry/associate (600)
                "https://www.linkedin.com/jobs/search/?currentJobId=3900247578&f_E=2%2C3&f_JT=F&f_T=9%2C10738%2C25194%2C266%2C14642%2C24&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java%20%20%20&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //good benefits (pension covered elsewhere) - entry/associate(510)
                "https://www.linkedin.com/jobs/search/?currentJobId=3865193152&f_BE=1%2C2%2C3%2C4%2C7%2C8%2C9%2C10%2C11%2C12&f_E=2%2C3&f_JT=F&f_T=9%2C25194%2C266%2C10738%2C14642&f_TPR=r2592000&f_WT=2&geoId=103644278&keywords=java%20%20-php%20-ruby%20-scala%20-.net%20-django%20-%22Solutions%20Engineer%22%20-%22Senior%20Full%20Stack%20Engineer%22%20-%22Principal%20Software%20Engineer%22%20-%22Full%20Stack%20Developer%22%20%20-%22Systems%20Engineer%22%20-%22Staff%20Software%20Engineer%22%20&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",
                //software development: entry/associate(750)
                "https://www.linkedin.com/jobs/search/?currentJobId=3764141752&f_E=2%2C3&f_JT=F%2CP%2CT&f_T=9%2C23347%2C10738%2C1660%2C25194&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=Software%20Development%20-%22Senior%20Software%20Engineer%22%20%20-%22Senior%20Software%20Developer%22%20-%22Full%20Stack%20Developer%22%20-%22Sr.%20Software%20Developer%22%20-%22Sr.%20Software%20Engineer%22%20-%22Systems%20Engineer%22%20-%22Staff%20Software%20Engineer%22%20-%22Solutions%20Engineer%22%20-%22Senior%20Full%20Stack%20Engineer%22%20-%22Principal%20Software%20Engineer%22%20-php%20-ruby%20-scala%20-.net%20-django%20&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //software development:past week under 10 applicants-entry/associate(650)
                "https://www.linkedin.com/jobs/search/?currentJobId=3904910616&f_E=2%2C3&f_EA=true&f_JT=F&f_T=9%2C10738%2C266%2C14642%2C25194&f_TPR=r2592000&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //PAST week - just java keyword - entry/associate(600)
                "https://www.linkedin.com/jobs/search/?currentJobId=3900812277&f_E=2%2C3&f_JT=F&f_T=9%2C25194%2C10738%2C14642%2C266&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java%20%20&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",
                //top applicant:entry/associate(550)
                "https://www.linkedin.com/jobs/search/?currentJobId=3900812277&f_E=2%2C3&f_JC=(0%2Clisystem%2Ctop-applicant-jobs)&f_JT=F&f_T=9%2C10738%2C23347&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java%20-senior%20-sr.%20-principal%20-staff%20-salesforce&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=R"

        );

        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
        return new QueryInput(urls, preferences, "USERNAME", "PASSWORD");
    }


    @GetMapping("/senior")
    public QueryInput getSeniorLevel() {
        List<String> urls = List.of(
                //mid-senior (500)
                "https://www.linkedin.com/jobs/search/?currentJobId=3788201277&f_AL=true&f_E=4&f_F=it&f_JT=F&f_T=9%2C10738%2C24%2C25194%2C3549&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //social impact (750)
                "https://www.linkedin.com/jobs/search/?currentJobId=3869625431&f_CM=4&f_E=4&f_JT=F&f_T=9%2C14827%2C23131%2C2385%2C24&f_TPR=r2592000&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",
                //NYC Metro: senior(500)
                "https://www.linkedin.com/jobs/search/?currentJobId=3786751968&f_E=4&f_JT=F&f_T=9%2C24%2C10738&f_TPR=r604800&f_WT=2&geoId=90000070&keywords=java%20-%22SAIC%22%20-%22Outcoder%20iO%22%20-groundswell%20-affirm%20-nvidia%20-daugherty%20-canonical%20-clevertech%20-startup%20-instructor%20-tutor%20%20%20-mainframe%20-salesforce%20-pega%20-servicenow%20-embedded%20-%22systems%20engineer%22%20-%22full%20stack%22%20-bootstrap%20-typescript%20-consultant%20-consulting%20-php%20-ruby%20-field&location=New%20York%20City%20Metropolitan%20Area&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",
               //PAST week - mid-senior (850)
                "https://www.linkedin.com/jobs/search/?currentJobId=3903320345&f_E=4&f_JT=F&f_T=9%2C10738%2C25194%2C266%2C14642%2C24&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java%20-bootstrap%20-typescript%20-android%20-%22full%20stack%22%20%20%20-php%20-ruby%20-scala%20-.net%20-principal%20-lead%20-staff%20-startup%20-sdet%20-instructor%20-tutor%20-%22systems%20engineer%22%20-field%20-mainframe%20-salesforce%20-pega%20-servicenow%20-embedded&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //search string - software engineer: mid-senior (1400)
                "https://www.linkedin.com/jobs/search/?currentJobId=3903320345&f_E=4&f_JT=F&f_T=9%2C23347%2C10738%2C1660%2C25194&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=Software%20Engineer%20%20-php%20-ruby%20-scala%20-.net%20-django%20%20-%22Senior%20Software%20Engineer%22%20%20-%22Senior%20Software%20Developer%22%20-%22Full%20Stack%20Developer%22%20-%22Sr.%20Software%20Developer%22%20-%22Sr.%20Software%20Engineer%22%20-%22Systems%20Engineer%22%20-%22Staff%20Software%20Engineer%22%20-%22Solutions%20Engineer%22%20-%22Senior%20Full%20Stack%20Engineer%20%22%20-%22Principal%20Software%20Engineer%22%20-fullstack&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //top applicant-senior (1k)
                "https://www.linkedin.com/jobs/search/?currentJobId=3903332129&f_E=4&f_JC=(0%2Clisystem%2Ctop-applicant-jobs)&f_JT=F&f_T=9%2C10738%2C23347&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java%20-senior%20-sr.%20-principal%20-staff&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",
                //NY:senior(600)
                "https://www.linkedin.com/jobs/search/?currentJobId=3786751968&f_E=4&f_JT=F&f_T=9%2C24%2C10738&f_TPR=r604800&f_WT=2&geoId=105080838&keywords=java%20-%22SAIC%22%20-%22Outcoder%20iO%22%20-groundswell%20-affirm%20-nvidia%20-daugherty%20-canonical%20-clevertech%20-startup%20-instructor%20-tutor%20%20%20-mainframe%20-salesforce%20-pega%20-servicenow%20-embedded%20-%22systems%20engineer%22%20-%22full%20stack%22%20-bootstrap%20-typescript%20%20-consultant%20-consulting%20-php%20-ruby%20-field&location=New%20York%2C%20United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",
                //commitments:DEI(500)
                "https://www.linkedin.com/jobs/search/?currentJobId=3905262066&f_CM=1&f_E=4&f_JT=F&f_T=9%2C10738%2C25194&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java%20%20-affirm%20-nvidia%20-pinterest%20-canonical%20-clevertech%20-crossover&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",
                //good benefits (pension covered elsewhere) - entry/associate(600)
                "https://www.linkedin.com/jobs/search/?currentJobId=3899991112&f_BE=1%2C2%2C3%2C4%2C7%2C8%2C9%2C10%2C11%2C12&f_E=4&f_JT=F&f_T=9%2C25194%2C266%2C10738%2C14642&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java%20%20-php%20-ruby%20-scala%20-.net%20-django%20-%22Solutions%20Engineer%22%20-%22Senior%20Full%20Stack%20Engineer%22%20-%22Principal%20Software%20Engineer%22%20-%22Full%20Stack%20Developer%22%20%20-%22Systems%20Engineer%22%20-%22Staff%20Software%20Engineer%22%20&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",
                //search string - software development:senior(400) 24 hours
                "https://www.linkedin.com/jobs/search/?currentJobId=3831103764&f_E=4&f_JT=F%2CP%2CT&f_T=9%2C23347%2C25194%2C10738%2C1660&f_TPR=r86400&f_WT=2&geoId=103644278&keywords=Software%20Development%20%20%20-php%20-ruby%20-scala%20-.net%20-django%20-%22Solutions%20Engineer%22%20-%22Senior%20Full%20Stack%20Engineer%22%20-%22Principal%20Software%20Engineer%22%20-%22Full%20Stack%20Developer%22%20%20-%22Systems%20Engineer%22%20-%22Staff%20Software%20Engineer%22%20&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=D",
                //string searches:mission (300)
                "https://www.linkedin.com/jobs/search/?currentJobId=3874510779&f_E=4&f_JT=F&f_T=9%2C25194%2C10738%2C266%2C14642&f_TPR=r2592000&f_WT=2&geoId=103644278&keywords=%22Java%22%20%20%22mission%22%20-%22permission%22%20-decommissioning%20-commission%20%20-%22Quantitative%20Medicine%20Developer%22%20-%22full-stack%22%20-%22Database%20Developer%22%20-%22years%20professional%20python%22%20-%22Senior%20Software%20Engineer%22%20-%22Cloud%20Engineer%22%20-%22Test%20Engineer%22%20-%22Machine%20Learning%20Engineer%22%20-%22Frontend%20Software%20Engineer%22%20-%22Research%20Engineer%22%20-%22Staff%20Software%20Engineer%22-django%20-%22ServiceNow%20Developer%22%20-%22Principal%20Engineer%22%20-%22Full%20Stack%22%20-%22Lead%20Backend%22%20-%22Distinguished%20Java%20Engineer%22%20-%22Java%20Software%20Engineer%20III%22%20-%22Sr.%20Software%20Engineer%22%20-%22Principal%20Software%20Engineer%22%20-%22Java%20Enterprise%20Account%20Manager%22%20-%22Java%20AWS%20Developer%22%20-%22Site%20Reliability%20Engineer%22%20-%22Cloud%20Developer%22%20-startup%20-%22Principal%20Software%20Developer%22%20-%22Senior%20Software%20Developer%22%20-%22React%20Native%20Developer%22%20-%22Senior%20Backend%22%20-Betting%20-Telecommunication%20-%22Senior%20Android%22%20-%22Java%20FSE%22%20-consulting%20-SailPoint%20-Salesforce%20-%22Software%20Developer%205%22%20-%22must%20be%20local%20to%20DC%22%20-%22AI%2FML%20Engineer%22%20-%22Quality%20Engineer%22%20-%22EDI%20Developer%22%20-Android%20-%22FileNet%20Developer%22%20-%22Principal%20Software%20Digital%20Engineer%22%20-%22QA%20Engineer%22%20-%22Lead%20Software%20Engineer%22%20-%22DevOps%20Engineer%22%20-%22Remote%20Data%20Scientist%22%20-%22REACT%20Developer%22%20-%22Solutions%20Engineer%22%20-%22Data%20Engineer%22%20-%22Security%20Engineer%22%20-%22Infrastructure%20Engineer%22%20-%22Senior%20Data%20Scientist%22%20-%22Systems%20Engineer%22%20-%22Advanced%20Software%20Engineer%22%20-%22Staff%20Backend%20Engineer%22%20-%22Developer%20Advocate%22%20-%22Scientific%20Software%20Developer%22%20-%22Platform%20Engineer%22%20-%22.NET%20Developer%22%20-%22Front%20End%20Engineer%22%20-%22Unity%20Developer%22%20-%22Game%20Developer%22%20-%22NET%20and%20Web%20Developer%22%20-%22Integration%20Engineer%22%20-%22Staff%20Engineer%22%20-%22Frontend%20Developer%22%20-%22Web%20Developer%22%20-%22Front%20End%20SW%20Engineer%22%20-%22Integration%20Engineer%22%20-%22UI%20Engineer%22%20-%22Frontend%20Developer%22%20-%22Front%20end%20Developer%22%20-%22Software%20Engineer%20(.NET)%22%20-%22Front%20End%20Software%20Engineer%22%20-detectives%20-%22Geospatial%20Software%20Engineer%22%20-%22low%20latency%22%20-ecommerce%20-Kyruus%20-%22years%20typescript%22%20-%22low-code%22%20-Cloudflare%20-TIDAL&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",

                //software development:past week under 10 applicants(1.4k)
                "https://www.linkedin.com/jobs/search/?currentJobId=3872602005&f_E=4&f_EA=true&f_JT=F&f_T=9%2C266%2C25194%2C10738%2C14642&f_TPR=r2592000&f_WT=2&geoId=103644278&keywords=java%20-principal%20-staff&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R"
        );

        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
        return new QueryInput(urls, preferences, "USERNAME", "PASSWORD");
    }

        @GetMapping("/collections/rare")
    public QueryInput getRareCollections() {
        List<String> urls = List.of(

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
                "https://www.linkedin.com/jobs/collections/pharmaceuticals/?currentJobId=3652886006&discover=true&subscriptionOrigin=JOBS_HOME",
                //sustainability
                "https://www.linkedin.com/jobs/collections/sustainability/?currentJobId=3856693368&discover=true&subscriptionOrigin=JOBS_HOME",
                //dei
                "https://www.linkedin.com/jobs/collections/de-and-i/?currentJobId=3868460140&discover=true&subscriptionOrigin=JOBS_HOME",
                //higher ed
                "https://www.linkedin.com/jobs/collections/higher-edu/?currentJobId=3848971164&discover=true&subscriptionOrigin=JOBS_HOME",
                //social impact
                "https://www.linkedin.com/jobs/collections/social-impact/?currentJobId=3869625431&discover=true&subscriptionOrigin=JOBS_HOME",
                //biotech
                "https://www.linkedin.com/jobs/collections/biotechnology/?currentJobId=3815220526&discover=true&subscriptionOrigin=JOBS_HOME",
                //work life balance
                "https://www.linkedin.com/jobs/collections/work-life-balance/?currentJobId=3852127566&discover=true&subscriptionOrigin=JOBS_HOME"

        );

        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
        return new QueryInput(urls, preferences, "USERNAME", "PASSWORD");
    }
}
