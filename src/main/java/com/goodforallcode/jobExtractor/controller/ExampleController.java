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
                //life balance HAS
                "https://www.linkedin.com/jobs/search/?currentJobId=3728451744&distance=25&f_JT=F%2CP&f_T=9&f_WT=2&geoId=103644278&keywords=java%20life%20balance%20-senior%20-staff%20-sr.%20-principal%20-fullstack%20-frontend%20-embedded%20-salesforce%20-servicenow%20-lead%20-scientist%20-pattern%20-node%20-.net%20-mcse%20-jquery%20-ember%20-mulesoft%20-alfresco%20-golang%20-php%20-django%20-drupal%20-bi%20-ansible%20-blockchain%20-sdet%20-investors%20-relocate%20-pytorch%20-css%20-warehousing",
                //pension plan
                "https://www.linkedin.com/jobs/search/?currentJobId=3724417602&f_BE=5&f_E=2%2C3%2C4&f_WT=2&keywords=java&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=R?"
        );

        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
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
        return  new QueryInput(urls, preferences, "USERNAME", "PASSWORD");
    }

    @GetMapping("/easyApply")
    public QueryInput getEasyApplyExample() {
        List<String> urls=List.of(
                "https://www.linkedin.com/jobs/search/?currentJobId=3731599390&distance=25&f_AL=true&f_E=2%2C3&f_F=it&f_JT=F&f_T=10738%2C9&f_WT=2&geoId=103644278&keywords=java%20-ui%20-servicenow%20-staff%20-embedded%20-scala%20-fast%20-typescript%20-braintrust%20-frontend%20-sdet%20-cybercoders%20-analytics%20-bootstrap%20-salesforce%20-jobot%20-fingerprint%20-.net%20-php%20-ruby%20-principal%20-sap%20-sas%20-quantumbricks%20-pressure%20-expert&origin=JOB_SEARCH_PAGE_JOB_FILTER&sortBy=R"   );

        PreferencesWithDefaults preferences = TestUtil.getDefaultPreferences();
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
                "https://www.linkedin.com/jobs/search/?currentJobId=3685770424&f_E=4&f_JT=F&f_T=9%2C23347%2C39%2C10738%2C1660&f_WT=2&geoId=103644278&keywords=java%20-startup%20-typescript%20-affirm%20-php%20-ruby%20-sdet%20-canonical%20-clevertech%20-instructor%20&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD"
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
        preferences.setSkipJobsSourcedFromExternalJobBoard(true);

        return  new QueryInput(urls, preferences, "USERNAME", "PASSWORD");
    }


    @GetMapping("/industry")
    public QueryInput getIndustryExample() {
        List<String> urls=List.of(
                //software not mid-senior
                "https://www.linkedin.com/jobs/search/?currentJobId=3754495641&f_E=2%2C3&f_F=it&f_I=4&f_JT=F&f_T=9%2C10738&f_WT=2&geoId=103644278&keywords=java%20-startup%20%20-ruby%20%20-android%20-php&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //software mid-senior
                "https://www.linkedin.com/jobs/search/?currentJobId=3742382450&f_E=4&f_F=it&f_I=4&f_JT=F&f_T=9%2C10738&f_WT=2&geoId=103644278&keywords=java%20-startup%20%20-ruby%20%20-android%20-php%20%20-sdet%20-canonical%20-clevertech%20-instructor%20&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=DD",
                //good industry not mid-senior
                "https://www.linkedin.com/jobs/search/?currentJobId=3694697655&f_E=2%2C3&f_I=14%2C17%2C75%2C12%2C124%2C15%2C100%2C132%2C139%2C69%2C114%2C144%2C68%2C70%2C57%2C89%2C115%2C125%2C13%2C130%2C16%2C37%2C67%2C79%2C85%2C86%2C88%2C90&f_JT=F&f_T=9%2C24%2C2385&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //good industry mid-senior
                "https://www.linkedin.com/jobs/search/?currentJobId=3732400312&f_E=4&f_I=14%2C17%2C75%2C12%2C124%2C15%2C100%2C132%2C139%2C69%2C114%2C144%2C68%2C70%2C57%2C89%2C115%2C125%2C13%2C130%2C16%2C37%2C67%2C79%2C85%2C86%2C88%2C90&f_JT=F&f_T=9%2C24%2C2385&f_WT=2&geoId=103644278&keywords=java%20-startup%20-php&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=DD"
        );
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


    @GetMapping("/experience")
    public QueryInput getExperienceExample() {
        List<String> urls=List.of(
                //associate
                "https://www.linkedin.com/jobs/search/?currentJobId=3707674204&f_E=3&f_F=it&f_JT=F&f_T=9%2C10738&f_WT=2&geoId=103644278&keywords=java%20%20&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=DD",
                //entry
                //by location
                "https://www.linkedin.com/jobs/search/?currentJobId=3742111364&f_E=2&f_JT=F&f_PP=104383890%2C102448103%2C102571732%2C102277331%2C106224388%2C103743442%2C103112676%2C105517665%2C100219842%2C103736294%2C100868799%2C104055874%2C104455108%2C100182490%2C102962635%2C103213036%2C104102413%2C104119503%2C105662409%2C106733128&f_T=9%2C10738%2C24&f_TPR=r2592000&f_WT=2&geoId=103644278&keywords=java%20%20%20-startup%20-php%20-ruby%20-sdet%20-canonical%20-clevertech%20-instructor%20-tutor%20-%22systems%20engineer%22%20%20-geico%20-onsite%20%20-%22full%20stack%22&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //by salary
                "https://www.linkedin.com/jobs/search/?currentJobId=3738833621&f_E=2&f_JT=F&f_SB2=3&f_T=9%2C10738%2C24&f_TPR=r2592000&f_WT=2&geoId=103644278&keywords=java%20%20%20-startup%20-php%20-ruby%20-sdet%20-canonical%20-clevertech%20-instructor%20-tutor%20-%22systems%20engineer%22%20-%22full%20stack%22%20-senior%20-servicenow%20-%22reliability%20engineer%22&location=United%20States&origin=JOB_SEARCH_PAGE_JOB_FILTER&refresh=true&sortBy=DD",
                //by benefits
                "https://www.linkedin.com/jobs/search/?currentJobId=3748648783&f_BE=1%2C2%2C3%2C4%2C5%2C7%2C8%2C9%2C10%2C11%2C12&f_E=2&f_JT=F&f_T=9%2C10738%2C24&f_TPR=r2592000&f_WT=2&geoId=103644278&keywords=java%20-startup%20-php%20-ruby%20-sdet%20-canonical%20-clevertech%20-instructor%20-tutor%20-%22systems%20engineer%22%20-%22full%20stack%22%20-senior%20-field%20-mainframe%20-salesforce%20-pega%20-%22data%20engineer%22&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=DD"
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


    @GetMapping("/title")
    public QueryInput getTitleExample() {
        List<String> urls=List.of(
                //backend title
                "https://www.linkedin.com/jobs/search/?currentJobId=3681178268&distance=25&f_E=2%2C3&f_F=it&f_JT=F&f_T=25194&f_WT=2&geoId=103644278&keywords=java",
                //java software engineer
                "https://www.linkedin.com/jobs/search/?currentJobId=3710231340&distance=25&f_E=2%2C3%2C4&f_T=10738&f_TPR=r2592000&f_WT=2&geoId=103644278&keywords=java&origin=JOB_SEARCH_PAGE_JOB_FILTER&sortBy=DD",
                //java specialist
                "https://www.linkedin.com/jobs/search/?currentJobId=3731238563&f_E=2%2C3%2C4&f_T=1660&f_TPR=r2592000&f_WT=2&geoId=103644278&keywords=java%20-tutor&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=DD",
                //junior titles
                "https://www.linkedin.com/jobs/search/?currentJobId=3738283272&f_T=2490%2C3549&f_WT=2&geoId=103644278&keywords=java%20-synergisticit&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=DD",
                //programmer analyst
                "https://www.linkedin.com/jobs/search/?currentJobId=3699274371&f_E=2%2C3%2C4&f_T=8660&f_WT=2&geoId=103644278&keywords=java%20&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=R"

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

    @GetMapping("/jobType")
    public QueryInput getJobTypeExample() {
        List<String> urls=List.of(
                //part time/temp
                "https://www.linkedin.com/jobs/search/?currentJobId=3681755972&distance=25&f_E=2%2C3&f_JT=P%2CT&f_T=9&f_WT=2&geoId=103644278&keywords=java%20-synergisticit%20-sr%20-hpc%20-edi%20-.net%20-consultant%20-servicenow%20-senior%20-wordpress%20-hustlewing%20-mobile%20-scientist%20-aem%20-maximo%20-ruby%20-lead%20-golang%20-ui%20-sap%20-guidewire%20-army%20-salesforce%20-asp.net%20-saml%20-lockheed%20-models%20-eteki%20-angular%20-critical%20-secret",
                //contrct
                "https://www.linkedin.com/jobs/search/?currentJobId=3754259084&distance=25&f_E=2%2C3&f_JT=C&f_T=9%2C10738&f_TPR=r2592000&f_WT=2&geoId=103644278&keywords=java%20-senior%20-sr.%20-staff%20-principal%20-fullstack%20-frontend%20-bootstrap%20-jquery%20-synergisticit%20-angular%20-jsp%20-jsf%20-sdet%20-guidewire%20-salesforce%20-servicenow%20-saml%20-camunda%20-ecommerce%20-financial%20-sas%20-sap%20-css&origin=JOB_SEARCH_PAGE_JOB_FILTER&sortBy=DD",
                //other
                "https://www.linkedin.com/jobs/search/?currentJobId=3739999787&f_E=2%2C3&f_JT=O&f_T=9&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true"
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

    @GetMapping("/collections")
    public QueryInput getCollectionsExample() {
        List<String> urls=List.of(
                //green collection
                "https://www.linkedin.com/jobs/search/?currentJobId=3731450453&f_E=2%2C3%2C4&f_F=it&f_JC=(0%2Clisystem%2Cgreen-jobs)&f_T=9%2C10738&f_WT=2&geoId=103644278&keywords=java&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=R",

                //top applicant
                "https://www.linkedin.com/jobs/search/?currentJobId=3702242662&distance=25&f_JC=(0%2Clisystem%2Ctop-applicant-jobs)&f_WT=2&geoId=103644278&keywords=java%20-senior%20-sr."
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
                "https://www.linkedin.com/jobs/search/?currentJobId=3738855508&f_CM=3&f_E=2%2C3%2C4&f_F=it&f_T=9%2C10738&f_TPR=r2592000&f_WT=2&geoId=103644278&keywords=java%20-affirm%20-startup%20-php%20-ruby%20-sdet%20-canonical%20-clevertech%20-instructor%20-tutor%20-%22systems%20engineer%22%20-%22full%20stack%22%20-field%20-mainframe%20-salesforce%20-pega%20-servicenow%20-%22reliability%20engineer%22%20-%22data%20engineer%22&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=DD",
                //social impact
                "https://www.linkedin.com/jobs/search/?currentJobId=3707131603&f_CM=4&f_E=2%2C3%2C4&f_F=it&f_JT=F&f_T=9&f_TPR=r2592000&f_WT=2&geoId=103644278&keywords=java%20-affirm%20-startup%20-php%20-ruby%20-sdet%20-canonical%20-clevertech%20-instructor%20-tutor%20-%22systems%20engineer%22%20-%22full%20stack%22%20-field%20-mainframe%20-salesforce%20-pega%20-servicenow%20-%22reliability%20engineer%22%20-%22data%20engineer%22&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=DD",
                //career growth
                "https://www.linkedin.com/jobs/search/?currentJobId=3707131603&f_CM=5&f_E=2%2C3%2C4&f_F=it&f_JT=F&f_T=9&f_TPR=r2592000&f_WT=2&geoId=103644278&keywords=java%20-affirm%20-startup%20-php%20-ruby%20-sdet%20-canonical%20-clevertech%20-instructor%20-tutor%20-%22systems%20engineer%22%20-%22full%20stack%22%20-field%20-mainframe%20-salesforce%20-pega%20-servicenow%20-%22reliability%20engineer%22%20-%22data%20engineer%22&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=DD",
                //sustainable
                "https://www.linkedin.com/jobs/search/?currentJobId=3708965460&distance=25&f_CM=2&f_E=2%2C3%2C4&f_JT=F&f_T=9%2C10738&f_WT=2&geoId=103644278&keywords=java%20-consultant%20-salesforce%20-fast%20-mainframe%20-affirm%20-machine%20-embedded%20-lead%20-staff%20-electrical%20-.net%20-typescript%20-principal%20-senior%20-azure%20-bairesdev%20-onsite%20-mobile%20-actimize%20-hcm%20-consulting%20-sr.%20-scientific%20-polygraph%20-poly%20-dynamics%20-ruby",
                //DEI
                "https://www.linkedin.com/jobs/search/?currentJobId=3730718331&f_CM=1&f_E=2%2C3%2C4&f_JT=F&f_T=9%2C10738&f_TPR=r2592000&f_WT=2&geoId=103644278&keywords=java%20-affirm%20-nvidia%20-pinterest%20-startup%20-php%20-ruby%20-sdet%20-canonical%20-clevertech%20-instructor%20-tutor%20-%22systems%20engineer%22%20-%22full%20stack%22%20-field%20-mainframe%20-salesforce%20-pega%20-servicenow%20-%22reliability%20engineer%22%20-%22data%20engineer%22&location=United%20States&origin=JOB_SEARCH_PAGE_SEARCH_BUTTON&refresh=true&sortBy=DD"
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


    @GetMapping("/advantaged")
    public QueryInput getAdvantagedExample() {
        List<String> urls=List.of(
                //public trust
                "https://www.linkedin.com/jobs/search/?currentJobId=3704049774&f_CR=103644278&f_E=2%2C3%2C4&f_F=it&f_JT=F%2CP%2CC%2CT&f_T=9%2C10%2C24&f_WT=2&geoId=92000000&keywords=java%20public%20trust%20-servicenow%20-salesforce%20-senior%20-principal%20-azure%20-devsecops%20-appian%20-mumps%20-sailpoint%20-.net%20-sdet%20-mulesoft%20-drupal%20-infrastructure%20-sap%20-sr.%20-fullstack%20-angular%20-xacta%20-startup%20-php%20-lead%20-bootstrap%20-bi%20-django%20-expertise%20-idam",
                //usc only
                "https://www.linkedin.com/jobs/search/?currentJobId=3638719250&distance=25&f_F=it&f_T=10738%2C9&f_WT=2&geoId=103644278&keywords=java%20%22usc%20only%22%20-servicenow%20-sailpoint%20-cybersecurity%20-rotation%20-venture%20-fast%20-startup%20-fullstack%20-azure%20-salesforce%20-devops%20-.net%20-lead%20-%20intelligence%20-install",
                //govfirst
                "https://www.linkedin.com/jobs/search/?f_C=80559121&f_WT=2&geoId=92000000&keywords=java%20-jest",
                //inclusively
                "https://www.linkedin.com/jobs/search/?currentJobId=3736704175&f_C=65909538&f_E=2%2C3%2C4&f_WT=2&geoId=92000000&keywords=java%20-senior",
                //mbi
                "https://www.linkedin.com/jobs/search/?currentJobId=3732049080&f_WT=2&geoId=92000000&keywords=java%20mbi"
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


}
