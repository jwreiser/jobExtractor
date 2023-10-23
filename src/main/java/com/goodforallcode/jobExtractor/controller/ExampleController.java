package com.goodforallcode.jobExtractor.controller;

import com.goodforallcode.jobExtractor.TestUtil;
import com.goodforallcode.jobExtractor.model.QueryInput;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
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
        preferences.setMinYearlySalary(80000);
        preferences.setMaxJobAgeInDays(60);
//        preferences.setMinJobAgeInDays(2);
        preferences.setMaxApplicants(400);
        preferences.setMaxLevel(2);
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

        QueryInput example = new QueryInput(benefitUrls, preferences, "USERNAME", "PASSWORD");
        return example;
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
        preferences.setMinYearlySalary(80000);
        preferences.setMaxJobAgeInDays(60);
//        preferences.setMinJobAgeInDays(2);
        preferences.setMaxApplicants(400);
        preferences.setMaxLevel(2);
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

        QueryInput example = new QueryInput(urls, preferences, "USERNAME", "PASSWORD");
        return example;
    }

}
