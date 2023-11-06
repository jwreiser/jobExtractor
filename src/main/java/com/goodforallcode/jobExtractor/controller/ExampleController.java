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
        List<String> urls=List.of(
                //entry
                "https://www.linkedin.com/jobs/search/?currentJobId=3755776586&f_AL=true&f_E=2&f_F=it&f_JT=F&f_T=9%2C10738%2C24%2C25194%2C3549&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_LOCATION_HISTORY&refresh=true&sortBy=DD",
                //associate (41)
                "https://www.linkedin.com/jobs/search/?currentJobId=3721910416&f_AL=true&f_E=3&f_F=it&f_JT=F&f_T=9%2C10738%2C24%2C25194%2C3549&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //mid-senior (160)
                "https://www.linkedin.com/jobs/search/?currentJobId=3748846952&f_AL=true&f_E=4&f_F=it&f_JT=F&f_T=9%2C10738%2C24%2C25194%2C3549&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java%20-affirm%20-nvidia%20-pinterest%20-startup%20-php%20-ruby%20-sdet%20-canonical%20-clevertech%20-instructor%20-tutor%20-%22systems%20engineer%22%20-%22full%20stack%22%20%20-mainframe%20-salesforce%20-pega%20-servicenow%20-%22reliability%20engineer%22%20-%22data%20engineer%22%20-embedded&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=DD"
        );

        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
        return  new QueryInput(urls, preferences, "USERNAME", "PASSWORD");
    }


    @GetMapping("/companyName")
    public QueryInput getCompanyNameExample() {
        List<String> urls=List.of(
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
        return  new QueryInput(urls, preferences, "USERNAME", "PASSWORD");
    }

    @GetMapping("/fewApplicants")
    public QueryInput getFewApplicantsExample() {
        List<String> urls=List.of(

                //less than 10
                //entry (530)
                "https://www.linkedin.com/jobs/search/?currentJobId=3740816787&f_E=2&f_EA=true&f_F=it&f_JT=F&f_T=9%2C24%2C10738&f_WT=2&geoId=103644278&keywords=java%20-affirm%20-nvidia%20-pinterest%20-startup%20-php%20-ruby%20-sdet%20-canonical%20-clevertech%20-instructor%20-tutor%20-%22systems%20engineer%22%20-%22full%20stack%22%20-senior%20-field%20-mainframe%20-salesforce%20-pega%20-servicenow%20-%22reliability%20engineer%22%20-%22data%20engineer%22%20-bootstrap&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //associate
                "https://www.linkedin.com/jobs/search/?currentJobId=3621591252&f_E=3&f_EA=true&f_F=it&f_JT=F&f_T=9%2C10738%2C24&f_WT=2&geoId=103644278&keywords=java%20&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=DD",
                //mid-senior (310)
                "https://www.linkedin.com/jobs/search/?currentJobId=3758551290&f_E=4&f_EA=true&f_F=it&f_JT=F&f_T=9%2C10738%2C24&f_WT=2&geoId=103644278&keywords=java%20-affirm%20-nvidia%20-pinterest%20-startup%20-php%20-ruby%20-sdet%20-canonical%20-clevertech%20-instructor%20-tutor%20-%22systems%20engineer%22%20-%22full%20stack%22%20-senior%20-field%20-mainframe%20-salesforce%20-pega%20-servicenow%20-%22reliability%20engineer%22%20-%22data%20engineer%22%20-bootstrap%20-typescript%20-android%20-embedded&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=DD",

                //entry
                // it services and it consulting
                "https://www.linkedin.com/jobs/search/?currentJobId=3740816787&f_E=2&f_I=96&f_JT=F&f_T=9%2C10738%2C1660%2C23347%2C25201%2C39&f_WT=2&geoId=103644278&keywords=java%20-affirm%20-nvidia%20-pinterest%20-startup%20-php%20-ruby%20-sdet%20-canonical%20-clevertech%20-instructor%20-tutor%20-%22systems%20engineer%22%20-%22full%20stack%22%20-senior%20-field%20-mainframe%20-salesforce%20-pega%20-servicenow%20-%22reliability%20engineer%22%20-%22data%20engineer%22%20-bootstrap%20-typescript%20-android%20-embedded&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
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
                "https://www.linkedin.com/jobs/search/?currentJobId=3730575940&f_E=3&f_JT=F%2CO&f_T=9%2C10738%2C23347%2C39%2C25201%2C1660&f_WT=2&geoId=103644278&keywords=java%20-affirm%20-nvidia%20-pinterest%20-startup%20-php%20-ruby%20-sdet%20-canonical%20-clevertech%20-instructor%20-tutor%20-%22systems%20engineer%22%20-%22full%20stack%22%20%20-mainframe%20-salesforce%20-pega%20-servicenow%20-embedded&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=DD",

                // mid/senior
                // it services and it consulting
                "https://www.linkedin.com/jobs/search/?currentJobId=3741605661&f_E=4&f_I=96&f_JT=F&f_T=9%2C10738%2C39%2C1660%2C23347&f_WT=2&geoId=103644278&keywords=java%20-startup%20-php%20-ruby%20-sdet%20-canonical%20-clevertech%20-instructor%20-tutor%20-%22systems%20engineer%22%20-%22full%20stack%22%20-senior%20-field%20-mainframe%20-salesforce%20-pega%20-servicenow%20-%22reliability%20engineer%22%20-%22data%20engineer%22%20-bootstrap%20-typescript%20-android%20-embedded%20-principal%20-lead%20-sr.%20%20-staff%20-crossover%20%20-.net&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //staffing and recruiting
                "https://www.linkedin.com/jobs/search/?currentJobId=3745429021&f_E=4&f_I=104&f_JT=F&f_T=9%2C10738%2C39%2C1660%2C23347&f_WT=2&geoId=103644278&keywords=java%20-startup%20-php%20-ruby%20-sdet%20-canonical%20-clevertech%20-instructor%20-tutor%20-%22systems%20engineer%22%20-%22full%20stack%22%20-senior%20-field%20-mainframe%20-salesforce%20-pega%20-servicenow%20-%22reliability%20engineer%22%20-%22data%20engineer%22%20-bootstrap%20-typescript%20-android%20-embedded%20-principal%20-lead%20-sr.%20%20-staff%20-crossover%20%20-.net&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //tech information and internet
                "https://www.linkedin.com/jobs/search/?currentJobId=3757504320&f_E=4&f_I=6&f_JT=F&f_T=9%2C10738%2C1660%2C23347%2C39&f_WT=2&geoId=103644278&keywords=java%20-startup%20-php%20-ruby%20-sdet%20-canonical%20-clevertech%20-instructor%20-tutor%20-%22systems%20engineer%22%20-%22full%20stack%22%20-senior%20-field%20-mainframe%20-salesforce%20-pega%20-servicenow%20-%22reliability%20engineer%22%20-%22data%20engineer%22%20-bootstrap%20-typescript%20-android%20-embedded%20-principal%20-lead%20-sr.%20%20-staff%20-crossover%20%20-.net&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //software development
                "https://www.linkedin.com/jobs/search/?currentJobId=3757504320&f_E=4&f_I=4&f_JT=F&f_T=9%2C10738%2C1660%2C23347%2C39&f_WT=2&geoId=103644278&keywords=java%20-startup%20-php%20-ruby%20-sdet%20-canonical%20-clevertech%20-instructor%20-tutor%20-%22systems%20engineer%22%20-%22full%20stack%22%20-senior%20-field%20-mainframe%20-salesforce%20-pega%20-servicenow%20-%22reliability%20engineer%22%20-%22data%20engineer%22%20-bootstrap%20-typescript%20-android%20-embedded%20-principal%20-lead%20-sr.%20%20-staff%20-crossover%20%20-.net&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //hr
                "https://www.linkedin.com/jobs/search/?currentJobId=3751249914&f_E=4&f_I=137&f_JT=F&f_T=9%2C10738%2C1660%2C23347%2C39&f_WT=2&geoId=103644278&keywords=java%20-startup%20-php%20-ruby%20-sdet%20-canonical%20-clevertech%20-instructor%20-tutor%20-%22systems%20engineer%22%20-%22full%20stack%22%20-senior%20-field%20-mainframe%20-salesforce%20-pega%20-servicenow%20-%22reliability%20engineer%22%20-%22data%20engineer%22%20-bootstrap%20-typescript%20-android%20-embedded%20-principal%20-lead%20-sr.%20%20-staff%20-crossover%20%20-.net&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //it and services, hospital and healthcare
                "https://www.linkedin.com/jobs/search/?currentJobId=3751249914&f_E=4&f_I=137&f_JT=F&f_T=9%2C10738%2C1660%2C23347%2C39&f_WT=2&geoId=103644278&keywords=java%20-startup%20-php%20-ruby%20-sdet%20-canonical%20-clevertech%20-instructor%20-tutor%20-%22systems%20engineer%22%20-%22full%20stack%22%20-senior%20-field%20-mainframe%20-salesforce%20-pega%20-servicenow%20-%22reliability%20engineer%22%20-%22data%20engineer%22%20-bootstrap%20-typescript%20-android%20-embedded%20-principal%20-lead%20-sr.%20%20-staff%20-crossover%20%20-.net&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //all non shady industries that don't have a  big number of job positions (as big industries search on their own)
                "https://www.linkedin.com/jobs/search/?currentJobId=3614760213&f_E=4&f_I=119%2C93%2C133%2C16%2C130%2C7%2C33%2C17%2C12%2C3%2C114%2C68%2C75%2C84%2C112%2C116%2C122%2C124%2C126%2C132%2C135%2C51%2C88%2C100%2C101%2C102%2C103%2C105%2C107%2C108%2C110%2C111%2C113%2C115%2C120%2C125%2C127%2C13%2C134%2C136%2C138%2C139%2C141%2C144%2C145%2C146%2C147%2C148%2C150%2C20%2C23%2C24%2C28%2C30%2C31%2C32%2C34%2C35%2C37%2C38%2C39%2C40%2C49%2C53%2C55%2C56%2C57%2C62%2C63%2C66%2C67%2C69%2C70%2C73%2C74%2C82%2C83%2C85%2C86%2C87%2C89%2C90%2C91%2C92%2C95%2C99&f_JT=F&f_T=9%2C10738%2C25201%2C1660%2C23347%2C39&f_WT=2&geoId=103644278&keywords=java%20-affirm%20-nvidia%20-pinterest%20-startup%20-php%20-ruby%20-sdet%20-canonical%20-clevertech%20-instructor%20-tutor%20-%22systems%20engineer%22%20-%22full%20stack%22%20-field%20-mainframe%20-salesforce%20-pega%20-servicenow%20-%22reliability%20engineer%22%20-%22data%20engineer%22%20-bootstrap%20-typescript%20-android%20-embedded&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=DD"
                );

        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
        preferences.setMaxJobAgeInDays(90);
        preferences.setMinJobAgeInDays(2);
        preferences.setMaxApplicants(90);

        preferences.setExcludeFresher(false);
        preferences.setExcludeSenior(false);
        preferences.setExcludeBigData(false);
        preferences.setExcludeBlockchain(false);
        preferences.setExcludeComplexJobs(false);
        preferences.setExcludeRealEstate(false);
        preferences.setSkipTooManyApplicants(true);
        preferences.setSkipUnknownNumberOfApplicants(true);
        preferences.setSkipJobsSourcedFromExternalJobBoard(true);

        return  new QueryInput(urls, preferences, "USERNAME", "PASSWORD");
    }


    @GetMapping("/industry")
    public QueryInput getIndustryExample() {
        List<String> urls=List.of(
                //good industries
                //entry (100)
                "https://www.linkedin.com/jobs/search/?currentJobId=3732514403&f_E=2&f_I=14%2C17%2C12%2C86%2C132%2C68%2C114%2C144%2C75%2C124%2C13%2C15%2C88%2C100%2C115%2C125%2C130%2C139%2C16%2C37%2C57%2C67%2C69%2C70%2C79%2C85%2C89%2C90&f_JT=F&f_T=9%2C2385%2C24&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //associate (130)
                "https://www.linkedin.com/jobs/search/?currentJobId=3669566650&f_E=3&f_I=17%2C14%2C57%2C75%2C132%2C100%2C114%2C115%2C12%2C124%2C125%2C13%2C130%2C139%2C144%2C15%2C16%2C37%2C67%2C68%2C69%2C70%2C79%2C85%2C86%2C88%2C89%2C90&f_T=9%2C2385%2C24&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //mid-senior (200)
                "https://www.linkedin.com/jobs/search/?currentJobId=3736766463&f_E=4&f_I=14%2C15%2C124%2C12%2C17%2C68%2C132%2C144%2C70%2C139%2C69%2C75%2C57%2C100%2C115%2C90%2C67%2C86%2C125%2C13%2C88%2C114%2C130%2C16%2C37%2C79%2C85%2C89&f_JT=F&f_T=9%2C2385%2C24%2C1660%2C18630&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD"
        );
        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
        return  new QueryInput(urls, preferences, "USERNAME", "PASSWORD");
    }
    @GetMapping("/experience")
    public QueryInput getExperienceExample() {
        List<String> urls=List.of(
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
        preferences.setExcludeSenior(true);
        preferences.setExcludeBigData(true);
        preferences.setExcludeBlockchain(true);
        preferences.setExcludeCloudHeavy(true);
        preferences.setExcludeComplexJobs(true);
        preferences.setExcludeRealEstate(true);
        preferences.setMaxEmployees(9000);

        return  new QueryInput(urls, preferences, "USERNAME", "PASSWORD");
    }


    @GetMapping("/title")
    public QueryInput getTitleExample() {
        List<String> urls=List.of(
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
        return  new QueryInput(urls, preferences, "USERNAME", "PASSWORD");
    }

    @GetMapping("/jobType")
    public QueryInput getJobTypeExample() {
        List<String> urls=List.of(
                //part time/temp (35)
                "https://www.linkedin.com/jobs/search/?currentJobId=3758553191&f_E=2%2C3%2C4&f_JT=P%2CT&f_T=9%2C1660&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true",
                //contract
                "https://www.linkedin.com/jobs/search/?currentJobId=3754259084&distance=25&f_E=2%2C3&f_JT=C&f_T=9%2C10738&f_TPR=r2592000&f_WT=2&geoId=103644278&keywords=java%20-senior%20-sr.%20-staff%20-principal%20-fullstack%20-frontend%20-bootstrap%20-jquery%20-synergisticit%20-angular%20-jsp%20-jsf%20-sdet%20-guidewire%20-salesforce%20-servicenow%20-saml%20-camunda%20-ecommerce%20-financial%20-sas%20-sap%20-css&origin=JOB_SEARCH_PAGE_JOB_FILTER&sortBy=DD",
                //other
                "https://www.linkedin.com/jobs/search/?currentJobId=3747505201&f_E=2%2C3%2C4&f_JT=O&f_T=9%2C10738&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true"
        );

        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
        return  new QueryInput(urls, preferences, "USERNAME", "PASSWORD");
    }

    @GetMapping("/collections")
    public QueryInput getCollectionsExample() {
        List<String> urls=List.of(
                //green collection (7)
                "https://www.linkedin.com/jobs/search/?currentJobId=3757947521&f_E=2%2C3%2C4&f_F=it&f_JC=(0%2Clisystem%2Cgreen-jobs)&f_T=9%2C10738&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R",

                //top applicant (7)
                "https://www.linkedin.com/jobs/search/?currentJobId=3754989229&distance=25&f_JC=(0%2Clisystem%2Ctop-applicant-jobs)&f_TPR=r604800&f_WT=2&geoId=103644278&keywords=java%20-senior%20-sr.&origin=JOB_SEARCH_PAGE_JOB_FILTER"
        );

        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
        preferences.setMaxJobAgeInDays(23);
        preferences.setMaxApplicants(400);
        preferences.setExcludeFresher(true);
        preferences.setExcludeSenior(true);
        preferences.setExcludeBigData(true);
        preferences.setExcludeBlockchain(true);
        preferences.setExcludeCloudHeavy(true);
        preferences.setExcludeComplexJobs(true);
        preferences.setExcludeRealEstate(true);
        preferences.setMaxEmployees(9000);

        return  new QueryInput(urls, preferences, "USERNAME", "PASSWORD");
    }


    @GetMapping("/comittments")
    public QueryInput getComittmentsExample() {
        List<String> urls=List.of(
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
        return  new QueryInput(urls, preferences, "USERNAME", "PASSWORD");
    }


    @GetMapping("/advantaged")
    public QueryInput getAdvantagedExample() {
        List<String> urls=List.of(
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
        return  new QueryInput(urls, preferences, "USERNAME", "PASSWORD");
    }



    @GetMapping("/bigCollections")
    public QueryInput getBigCollectionsExample() {
        List<String> urls=List.of(
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

        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
        return  new QueryInput(urls, preferences, "USERNAME", "PASSWORD");
    }


}
