package com.goodforallcode.jobExtractor.controller;

import com.goodforallcode.jobExtractor.TestUtil;
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
                //life balance HAS
                "https://www.linkedin.com/jobs/search/?currentJobId=3728451744&distance=25&f_JT=F%2CP&f_T=9&f_WT=2&geoId=103644278&keywords=java%20life%20balance%20-senior%20-staff%20-sr.%20-principal%20-fullstack%20-frontend%20-embedded%20-salesforce%20-servicenow%20-lead%20-scientist%20-pattern%20-node%20-.net%20-mcse%20-jquery%20-ember%20-mulesoft%20-alfresco%20-golang%20-php%20-django%20-drupal%20-bi%20-ansible%20-blockchain%20-sdet%20-investors%20-relocate%20-pytorch%20-css%20-warehousing",
                //pension plan
                "https://www.linkedin.com/jobs/search/?currentJobId=3724417602&f_BE=5&f_E=2%2C3%2C4&f_WT=2&keywords=java&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R?"
        );

        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
        preferences.setMaxJobAgeInDays(60);
//        preferences.setMinJobAgeInDays(2);
        preferences.setMaxApplicants(400);
        preferences.setExcludeFresher(true);
        preferences.setExcludeSenior(true);
        preferences.setExcludeBigData(true);
        preferences.setExcludeBlockchain(true);
        preferences.setExcludeCloudHeavy(true);
        preferences.setExcludeComplexJobs(true);
        preferences.setExcludeRealEstate(true);
        preferences.setSkipTooManyApplicants(false);
        preferences.setSkipJobsSourcedFromExternalJobBoard(false);

        return new QueryInput(benefitUrls, preferences, "USERNAME", "PASSWORD");
    }


    @GetMapping("/companyName")
    public QueryInput getCompanyNameExample() {
        List<String> urls=List.of(
                //neurodivergent (jobs for humanity)
                "https://www.linkedin.com/jobs/search/?currentJobId=3733471087&f_C=35925095&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_LOCATION_AUTOCOMPLETE&refresh=true&sortBy=R",
                //clearance jobs company
                "https://www.linkedin.com/jobs/search/?currentJobId=3737786812&f_C=1007957&f_E=2%2C3%2C4&f_JT=F%2CP%2CC%2CT&f_WT=2&geoId=92000000&keywords=java%20-senior%20-sr%20-principal%20-lead%20-servicenow%20-.net%20-poly%20-dod%20-sme%20-devsecops%20-defense%20-scientist%20-structural%20-network%20-amps",
                //trimble
                "https://www.linkedin.com/jobs/search/?currentJobId=3738949112&f_C=5160%2C96206&f_E=2%2C3%2C4&f_WT=2&geoId=92000000&keywords=java%20-field&location=Worldwide&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=DD",
                //cedar
                "https://www.linkedin.com/jobs/search/?currentJobId=3734045040&distance=&f_AL=&f_BE=&f_C=10988174&f_CF=&f_CM=&f_CR=&f_CT=&f_E=&f_EA=&f_EL=&f_ES=&f_ET=&f_F=&f_FCE=&f_GC=&f_I=&f_JC=&f_JIYN=&f_JT=&f_LF=&f_PP=&f_SAL=&f_SB=&f_SB2=&f_SB3=&f_T=9&f_TP=&f_WRA=&f_WT=2&geoId=92000000&latLong=&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=false&sortBy=R",
                //nationwide
                "https://www.linkedin.com/jobs/search/?currentJobId=3739836581&f_C=2340&f_E=2%2C3%2C4&geoId=92000000&keywords=java&origin=JOB_SEARCH_PAGE_JOB_FILTER"
        );

        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
        preferences.setMaxJobAgeInDays(60);
        preferences.setMaxApplicants(400);
        preferences.setExcludeFresher(true);
        preferences.setExcludeSenior(true);
        preferences.setExcludeBigData(true);
        preferences.setExcludeBlockchain(true);
        preferences.setExcludeCloudHeavy(true);
        preferences.setExcludeComplexJobs(true);
        preferences.setExcludeRealEstate(true);
        preferences.setMaxEmployees(9000);
        preferences.setSkipTooManyApplicants(false);
        preferences.setSkipJobsSourcedFromExternalJobBoard(false);

        return  new QueryInput(urls, preferences, "USERNAME", "PASSWORD");
    }

    @GetMapping("/easyApply")
    public QueryInput getEasyApplyExample() {
        List<String> urls=List.of(
                "https://www.linkedin.com/jobs/search/?currentJobId=3731599390&distance=25&f_AL=true&f_E=2%2C3&f_F=it&f_JT=F&f_T=10738%2C9&f_WT=2&geoId=103644278&keywords=java%20-ui%20-servicenow%20-staff%20-embedded%20-scala%20-fast%20-typescript%20-braintrust%20-frontend%20-sdet%20-cybercoders%20-analytics%20-bootstrap%20-salesforce%20-jobot%20-fingerprint%20-.net%20-php%20-ruby%20-principal%20-sap%20-sas%20-quantumbricks%20-pressure%20-expert&origin=JOB_SEARCH_PAGE_JOB_FILTER&sortBy=R"   );

        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
        preferences.setMaxJobAgeInDays(30);
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
    @GetMapping("/fewApplicants")
    public QueryInput getFewApplicantsExample() {
        List<String> urls=List.of(
                //4 URLS
                //less than 10
                "https://www.linkedin.com/jobs/search/?currentJobId=3693981987&distance=25&f_E=2%2C3%2C4&f_EA=true&f_F=it&f_T=9%2C10738%2C24&f_WT=2&geoId=103644278&keywords=java%20-salesforce%20-ruby%20-php%20-scala%20-startup%20-fast%20-typescript%20-principal%20-servicenow%20-consulting%20-embedded%20-lead%20-cybercoders%20-canonical%20-.net%20-synergisticit%20-mobile%20-mainframe%20-sr.%20-hybrid%20-senior%20-fingerprint%20-bootstrap%20-adobe%20-field%20-staff%20-sap%20-security",
                //all jobs that might be acceptable to be filtered by age and num applicants
                //entry 920
                "https://www.linkedin.com/jobs/search/?currentJobId=3718516360&f_E=2&f_JT=F&f_T=9%2C39%2C23347%2C10738%2C25201%2C1660&f_WT=2&geoId=103644278&keywords=java%20-tutor%20-affirm%20-startup&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //associate 500
                "https://www.linkedin.com/jobs/search/?currentJobId=3707674204&f_E=3&f_JT=F%2CO&f_T=9%2C10738%2C23347%2C39%2C25201%2C1660&f_WT=2&keywords=java&origin=JOB_SEARCH_PAGE_JOB_FILTER&sortBy=DD",
                // mid/senior 950
                "https://www.linkedin.com/jobs/search/?currentJobId=3727208945&f_E=4&f_JT=F&f_T=9%2C39%2C23347%2C10738%2C25201%2C1660&f_WT=2&geoId=103644278&keywords=java%20-startup%20-typescript%20-affirm&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=DD"
        );

        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
        preferences.setMaxJobAgeInDays(90);
        preferences.setMinJobAgeInDays(2);
        preferences.setMaxApplicants(90);
        preferences.setMaxLevel(2);
        preferences.setExcludeFresher(false);
        preferences.setExcludeSenior(false);
        preferences.setExcludeBigData(false);
        preferences.setExcludeBlockchain(false);
        preferences.setExcludeCloudHeavy(false);
        preferences.setExcludeComplexJobs(false);
        preferences.setExcludeRealEstate(false);
        preferences.setSkipTooManyApplicants(true);
        preferences.setSkipJobsSourcedFromExternalJobBoard(true);

        return  new QueryInput(urls, preferences, "USERNAME", "PASSWORD");
    }


}
