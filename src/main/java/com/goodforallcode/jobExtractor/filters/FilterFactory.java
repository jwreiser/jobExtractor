package com.goodforallcode.jobExtractor.filters;

import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.ArrayList;
import java.util.List;

public class FilterFactory {

    public static List<ExcludeJobFilter> getAlwaysExcludeFilters(Preferences preferences) {
        List<ExcludeJobFilter> filters = new ArrayList<>();
        /*
                filters.add(new ApplicationEngineerFilter());
        filters.add(new DevSecOpsDescriptionFilter());

        filters.add(new BadManagementFilter());
        filters.add(new BlockChainFilter());
        filters.add(new BusinessIntelligenceFilter());
        filters.add(new BusinessProcessManagementFilter());
        filters.add(new BusinessRoleFilter());
        filters.add(new CommerceFilter());
        filters.add(new ContractFilter());
        filters.add(new CustomerExperienceManagementFilter());

        filters.add(new DataExchangeFilter());
        filters.add(new ERPFilter());

        filters.add(new FortuneRankFilter());
        filters.add(new FreelanceFilter());
        filters.add(new GamingFilter());
        filters.add(new GeospatialFilter());

        filters.add(new HardwareFilter());

        filters.add(new LevelFilter());
        filters.add(new LocalFilter());
        filters.add(new MainframeFilter());

        filters.add(new OutsourcingAndOffshoreFilter());

        filters.add(new PLMFilter());
        filters.add(new ProgrammingFrameworkFilter());
        filters.add(new PromotedFilter());
        filters.add(new RemoteFilter());
        filters.add(new SalaryFilter());
        filters.add(new SupplyChainFilter());
        filters.add(new TelecomFilter());
        filters.add(new TravelFilter());



        */


        if (preferences.isExcludeAboveSenior()) {
            filters.add(ExcludeJobFilter.build("AboveSenior")
                    .titlePhrases(List.of("stf ", "director", "VP ", "lead", "manager"
                            , "architect", "administrator", "chief", "principal", "Systems Development", "Systems Developer"))
            );
            if (preferences.isSoftwareSearch()) {
                filters.add(ExcludeJobFilter.build("AboveSeniorSoftware")
                        .titlePhrases(List.of("staff ", " staff"))
                );
            }
        }

        filters.add(ExcludeJobFilter.build("AcceptingApplications").excludeIfFalseJobAttribute("AcceptingApplications"));

        /**  Exceptions
         * Waitstaff
         */
        filters.add(ExcludeJobFilter.build("Advertising").industries(List.of("Advertising Services", "Advertising")).badCompanies(List.of("SocialVenu", "advertising"))
                .testForCompanyInDescription(true)
        );

        filters.add(ExcludeJobFilter.build("AgileRole")
                .titlePhrases(List.of("RTE Engineer", "Release Train Engineer", "Product Owner"))
        );

        if (preferences.isExcludeAggressiveTimelines()) {
            filters.add(ExcludeJobFilter.build("AggressiveTimelines")
                    .badCompanies(List.of("Clarifai", "Digital Technology Partners",
                            "Stryker", "Clover Health"))
                    .descriptionPhrases(List.of("fast-moving", "fast-paced", "fast paced", "aggressive timelines",
                            "aggressive delivery schedule"))
                    .testForCompanyInDescription(true)
                    .excludeAttributes(List.of("fastPaced"))
            );
        }


        filters.add(ExcludeJobFilter.build("AI")
                .titlePhrases(List.of("Machine Learning", " ML ", "NLP", " AI ", "AI Programmer",
                        "Artificial Intelligence", "AI/ML", "ML/AI"))
        );

        filters.add(ExcludeJobFilter.build("Applied").excludeIfTrueJobAttribute("Applied"));

        filters.add(ExcludeJobFilter.build("Automotive")
                .titlePhrases(List.of("Automotive","MECHANIC"))
                .badCompanies(List.of("Ford", "General Motors", "Toyota","AutoZone", "Nissan", "Honda","Meineke Car Care Center"))
        );

        if(preferences.isSoftwareSearch()) {
            filters.add(ExcludeJobFilter.build("Automation")
                    .titlePhrases(List.of("Automation", "Rockwell Portfolio", "BAW"))
            );

            filters.add(ExcludeJobFilter.build("Basic").descriptionPhrases(List.of("JBasic", "T24", "JBASE", "PICK", "jQL", "Visual basic")));

            if (preferences.isExcludeBigData()) {

                filters.add(ExcludeJobFilter.build("BigData")
                        .descriptionPhrases(List.of("pipelines", "pipeline",
                                "Extract, Transform, and Load"))
                        .titleAndDescriptionPhrases(List.of("big data", "ETL ",
                                "Extract, Transform, and Load",
                                "spark", "hive ", "pig ", "warehousing", "hadoop"))
                );

            }
        }

        filters.add(ExcludeJobFilter.build("Bilingual")
                .titlePhrases(List.of("Spanish", "German", "French", "Italian", "Portuguese", "Russian", "Japanese",
                        "Chinese", "Korean", "Arabic",
                        "Mandarin", "Cantonese", "Bilingual", "Multilingual"))
        );

        if (preferences.isSoftwareSearch()) {
            filters.add(ExcludeJobFilter.build("BusinessAnalyst")
                    .titlePhrases(List.of("Business Analyst", "BA "))
                    .safeTitlePhrases(List.of("ABA "))
            );
            filters.add(ExcludeJobFilter.build("CaseManagement")
                    .titleAndDescriptionPhrases(List.of("Entellitrak", "Documentum"))
            );
        }

        filters.add(ExcludeJobFilter.build("CareerAccelerator")
                .badCompanies(List.of("Outcoder iO", "SynergisticIT"))
                .testForCompanyInDescription(true)
        );

                        /*
Exceptions
Secret: secrets in terms of authentication type stuff
Employee Polygraph Protection Act
 */

        filters.add(ExcludeJobFilter.build("Clearance")
                .descriptionPhrases(List.of("must have an active Public Trust Clearance"))
                .safeDescriptionPhrases(List.of("the secret"))
                .titleAndDescriptionPhrases(List.of("top secret", "secret ", "TS/SCI ", " TS/SCI"))
        );


        filters.add(ExcludeJobFilter.build("ClinicalData")
                .titlePhrases(List.of("Coder", "Coding", "Clinical Data Programmer", "EDC Programmer", "EDC Developer"))
        );

        if (preferences.isExcludeCloudHeavy()) {
            filters.add(ExcludeJobFilter.build("Cloud")
                    .titlePhrases(List.of("AWS ", "Epic ", "OpenShift", "SailPoint", "Apigee",
                            "Informatica", "azure ", "gcp", "cloud", "Lambda", "Snowflake",
                            "Matillion", "Apigee", "FlashStack", "SFCC", "PCF", "Flashstack", "ECSA "))
                    .titleAndDescriptionPhrases(List.of("SailPoint", "Apigee", "Sail point",
                            "Informatica", "azure ", "gcp", "cloud", "Lambda", "Snowflake",
                            "Matillion", "Apigee", "FlashStack", "SFCC", "PCF", "Flashstack", "ECSA "))
                    .descriptionPhrasesAndCount(List.of("server-less", "serverless", " SNS"
                            , " SQS", " API Gateway", " EventBridge", " DynamoDB", " Redshift", " S3", "EC2", " AWS ",
                            "cloud-native", "cloud native", "Matillion", " aws ", " Epic Client ", " Epic ancillary ", " Epic system "
                            , " Epic environment", "Hyperspace", "Interconnect", "SailPoint", "Apigee",
                            "Informatica", "azure ", "gcp", "cloud", "Lambda", "Snowflake",
                            "Matillion", "Apigee", "FlashStack", "SFCC", "PCF", "Flashstack", "ECSA "), 3)
                    .badCompanies(List.of("Cloudflare", "Render", "Bowman Williams", "Concourse Labs"))
            );

        }
        filters.add(ExcludeJobFilter.build("Connecticut")
                .badCompanies(List.of("Hartford"))
                .titlePhrases(List.of(" CT "))
                .titleAndDescriptionPhrases(List.of("Hartford", "Connecticut", "Milford", "Fairfield", "Bridgeport"))
        );

        if (preferences.isExcludeConsultant()) {
        /*exceptions
    planning consultants
    consulting with customers
     */

            filters.add(ExcludeJobFilter.build("Consultant")
                    .titlePhrases(List.of("consulting", "consultant"))
                    .badCompanies(List.of("Curate Partners", "Modis", "Akkodis"
                            , "Ricardo plc", "FullStack Labs", "Sierra7", "Sierra7, Inc.", "Vaco", "QuantumBricks",
                            "Lorven Technologies Inc.", "ZETTALOGIX INC", "Sierra Solutions", "CGI",
                            "Daugherty Business Solutions", "World Wide Technology", "Qualitest", "Cognizant",
                            "Solü Technology Partners", "Nakupuna Companies", "SDI Presence",
                            "DMI (Digital Management, LLC)", "Next Level Business Services, Inc.",
                            "NLB Services"))
                    .industries(List.of("Business Consulting and Services", "Consulting"))
                    .titleCompanyNameAndDescriptionPhrases(List.of("consultancy"))
                    .testForCompanyInDescription(true)
                    .excludeAttributes(List.of("consulting")
                    ));
        }


        filters.add(ExcludeJobFilter.build("ContentManagement")
                .titlePhrases(List.of("Content Management", "CMS ", "Onbase", "CMS(", "CMS "))
                .descriptionPhrasesAndCount(List.of("AEM ", "Adobe Experience Manager", "Drupal", "Alfresco", " onbase",
                        "Sitecore", "Tridion"), 2)
                .caseSensitiveDescriptionPhrases(List.of("Brightspot"))
        );

        if (preferences.isExcludeContractJobs()) {
             /*
    exceptions:
    positions are subject to background screening as required by law or contract
     */

            filters.add(ExcludeJobFilter.build("Contract").badCompanies(List.of("Accenture Federal Services", "Guidehouse"
                            , "Wise Skulls", "Brooksource", "Harnham", "Cypress HCM", "Belcan", "Mindex",
                            "Kforce Inc", "Kforce Com", "Oktobor Animation", "Groundswell", "Raft",
                            "NTT DATA Services", "Spatial Front, Inc", "Tential Solutions",
                            "IT Crowd", "Koniag Government Services", "SCIGON", "Latitude Inc", "IT Labs",
                            "AgileEngine", "Bitsoft International, Inc.", "Revature", "Gridiron IT", "mphasis",
                            "Brillio", "GE", "SBS Creatix", "Compunnel Inc."))
                    .titlePhrases(List.of("contract"))
                    .excludeIfTrueJobAttribute("contract")
                    .excludeAttributes(List.of("contractor"))
            );
        }

        filters.add(ExcludeJobFilter.build("CoordinationAndManagement")
                .titlePhrases(List.of("Manager", "Systems engineer", "System engineer", "System Analyst"
                        , "Systems Analyst", "developer advocate", "developer evangelist"))
        );


        filters.add(ExcludeJobFilter.build("Credentialed")
                .titlePhrases(List.of("LCSW", "Certified", "Licensed", "Registered", "LPN", "CRN", "PCA", "-RN ", " RN ","BCBA"))
                .safeTitlePhrases(List.of(" train"))
        );

        filters.add(ExcludeJobFilter.build("CyberSecurity").badCompanies(List.of("Zscaler", "Fortra", "Concourse Labs",
                "PropelAuth", "Trinity Cyber", "Quokka.io", "security")).titlePhrases(List.of("Vulnerability engineer", "Detection", "Sentinel", "SIEM ", "Risk ", "Cyber Security", "CyberSecurity", "Cyber-Security")));
        filters.add(ExcludeJobFilter.build("JobSecurity").badCompanies(List.of("Allstate", "New Relic", "Breezeline", "Slack",
                        "Crossover", "Invitae", "Omnicell", "Komodo Health", "Rocket Software", "Zinnia", "CSG",
                        "NTT DATA Services", "Cruise", "VMware", "Intelerad Medical Systems",
                        "Air Apps", "CivicPlus", "Vertisystem Inc.", "Kyruus", "Atlassian"))
                .includeAttribute("jobSecurity")
                .excludeAttributes(List.of("recentLayoffs"))
        );



        filters.add(ExcludeJobFilter.build("Dangerous")
                .titlePhrases(List.of("Inventory Control", "Hazmat", "Security", "Surveillance", "Crisis", "Emergency", "Police Officer", "Private Investigator", "Firefighter"))
        );

        filters.add(ExcludeJobFilter.build("Dishonest")
                .badCompanies(List.of("IT Minds LLC", "Bitsoft International, Inc."
                        , "Envision Horizons", "Diaconia"))
                .testForCompanyInDescription(true)
        );


        filters.add(ExcludeJobFilter.build("Education")
                .titlePhrases(List.of("School","Teacher","Rater", "Instructor", "Tutor", "Trainer","Teaching","Admissions "," Admissions","Dissertation","Adjunct","Faculty","Proctor"))
                .badCompanies(List.of("Kaplan"))
        );

        filters.add(ExcludeJobFilter.build("Elderly")
                .titlePhrases(List.of("Memory Care", "Senior Living", "Senior Care", "Elderly", "Geriatric"))
                .badCompanies(List.of("Senior Living", "Gurwin Healthcare System", "The Bristal Assisted Living", "Atria Senior"))
                .titleAndDescriptionPhrases(List.of("Assisted Living"))
        );

        filters.add(ExcludeJobFilter.build("Entertainment")
                .badCompanies(List.of("NBCUniversal"))
                .industries(List.of("Entertainment Providers"))
        );


        filters.add(ExcludeJobFilter.build("EnrollmentRequired")
                .badCompanies(List.of("IT Pros"))
                .testForCompanyInDescription(true)
        );

        filters.add(ExcludeJobFilter.build("ForeignLocatedCompany")
                .badCompanies(List.of("Redcare Pharmacy"))
                .testForCompanyInDescription(true)
        );

        filters.add(ExcludeJobFilter.build("FossilFuels")
                .badCompanies(List.of("Pride International"))
                .industries(List.of("Oil and Gas"))
                .safeTitlePhrases(List.of("renewable", "solar", "wind",
                        "nuclear",
                        "mills", "windmill", "geothermal", "geo", "offgrid", "turbine", "sustainable"))
                .testForCompanyInDescription(true)
        );

        filters.add(ExcludeJobFilter.build("Insurance")
                .badCompanies(List.of("Transamerica", "Travelers",
                        "State Farm", "GEICO", "Allstate", "Coalition, Inc.", "One80 Intermediaries", "insurance"))
                .industries(List.of("Insurance"))
                .titlePhrases(List.of("Insurance","Duck Creek"))
                .testForCompanyInDescription(false)//can't look for insurance in description as it could be describing a benefit
        );

        filters.add(ExcludeJobFilter.build("InternetTelevisionAndMobile")
                .badCompanies(List.of("QCell"))
                .testForCompanyInDescription(true)
        );

        filters.add(ExcludeJobFilter.build("SkilledTrades")
                .industries(List.of("Construction"))
                .badCompanies(List.of("UpCodes", "Construction"))
                .titlePhrases(List.of("Carpenter", "Electrician", "HVAC", "Plumber","Construction"))
                .titleAndDescriptionPhrases(List.of("machinist"))
        );


//////////////////////////////////////////////////////////////




        filters.add(ExcludeJobFilter.build("Database")
                .titlePhrases(List.of("Database Developer", "Database Engineer", "SQL Developer", " SSIS "))
                .safeTitlePhrases(List.of(" and ", "/"))
        );

        filters.add(ExcludeJobFilter.build("DataFocused")
                .titlePhrases(List.of("Data Migration",
                        " ETL ", "Data Developer", "Analytics", "Statistical",
                        "Data Analyst", "Data Scientist", "Data Science", "Data Engineer", "data analytics",
                        "InterSystems Ensemble", "IRIS ", "Netezza", " ETL "))
                .safeTitlePhrases(List.of(" and ", "/"))

        );
        filters.add(ExcludeJobFilter.build("DataManagement")
                .titlePhrases(List.of("Collibra", "Markit", "EDM "))
        );

        /**
         * Defense can be used to describe defense of a paper
         * military status
         * military service, veteran status, or any other category protected under
         * readdmissions
         */
        filters.add(ExcludeJobFilter.build("Defense").industries(List.of("Defense"))
                .descriptionPhrases(List.of("DLA", "DOD", "defense",
                        "national security", " missions", "DHS", "army", "navy", "air force",
                        "Lockheed Martin", "Marine Corps", "missile"))
                .excludeAttributes(List.of("defense"))
                .badCompanies(List.of("Northrop Grumman", "Raytheon", "General Dynamics",
                        "Parsons Corporation", "SAIC", "Leidos", "RVCM (RevaComm)", "BAE Systems",
                        "ECS", "Innovative Defense Technologies (IDT)"))
        );

        if (preferences.isSoftwareSearch()) {

            filters.add(ExcludeJobFilter.build("DevSecOps")
                    .titlePhrases(List.of("Datacenter Engineer", "Devop", "Devsecop",
                            "Release Engineer", "Build", "Dev ops", "Devops",
                            " IT Engineer", "Information Technology Engineer",
                            "devsecops", "Monitoring", "VDI ",
                            "Infrastructure", "Site Reliability", "SRE", "Reliability Engineer", "Observability",
                            "Operations", " Ops Engineer", "CSfC Engineer",
                            "Information assurance", "Integration Engineer", "Release Management",
                            "Platform Engineer", "C3ISR", "SysOps", "Kubernetes", "Delivery", "Configuration", "Deployment"))
                    .descriptionPhrasesAndCount(List.of("network", "security", "install",
                            "VMware", "Servers", "Sccm", "administration", "administer",
                            "configuration management", " configure", " deploy", "maintain", "setup"), 3)
            );
        }

        /**
         * Exceptions
         * financial:   financial stability
         * Banking: Applicants will never be asked to provide personal identification information (e.g., SSN, Driver’s License, Passport) or financial information (e.g., Banking Information)
         * an independent brokerage that works to place homeless individuals and families into permanent housing
         */
        filters.add(ExcludeJobFilter.build("Finance").badCompanies(List.of("Affirm", "Citibank", "Kraken Digital Asset Exchange"
                        , "Jack Henry", "Equitable", "American Express", "U.S. Bank", "Modulus", "Clerkie",
                        "Juniper Square", "Peach", "T. Rowe Price", "Studio Management", "Mortgage", "Credit Union", " Bank","Wells Fargo"))
                .industries(List.of("Venture Capital and Private Equity Principals",
                        "Financial Services", "Finance", "Investment Banking", "Investment Banking & Asset Management"))
                .titlePhrases(List.of("Simcorp", "Accounts", "Billing", "Loan Originator", "Wealth", "Financial", "Investment",
                        "Banker", "Finance", "Accounting", "Accountant", "Branch Ambassador","Underwriting"
                        ," tax","tax ","Bookkeeper","Collections"))
                .descriptionPhrases(List.of("FinTech", "asset manager",
                        "hedge fund", "trading", "Brokers", " investment management"))
        );


        filters.add(ExcludeJobFilter.build("Florida")
                .badCompanies(List.of("University of Florida"))
                .titleAndDescriptionPhrases(List.of("Florida", "Miami", "Tampa", "Orlando", "Jacksonville"))
        );

        filters.add(ExcludeJobFilter.build("FoodIndustry")
                .titlePhrases(List.of(
                        "Dishwasher", "Food Service", "Food Runner", "Barista", "Waiter", "Waitress",
                        "Fish Market","Cook", "Server Assistant", "Bartender","Prepared Foods","Deli ","Culinary","Cafeteria","Meat","Restaurant","Banquet","Server "))
                .badCompanies(List.of("Whole Foods Market","Outback Steakhouse", "Red Lobster", "Buffalo Wild Wings", "Chick-fil-A", "Denny's",
                        "Cracker Barrel", "Taco Bell", "Pizza Hut", "Waffle House", "IHOP","Hostess",
                        "Olive Garden", "Applebee's", "Ruby Tuesday", "Checkers & Rally’s Drive-In Restaurants",
                        "Red Robin", "Cheesecake Factory", "Panda Express"))
        );


        if (preferences.isExcludeFresher()) {
            filters.add(ExcludeJobFilter.build("FresherAndIntern")
                    .descriptionPhrases(List.of(" intern "))
                    .titleAndDescriptionPhrases(List.of("Fresher", "intern ", "internship"))
            );
        }

        filters.add(ExcludeJobFilter.build("FrontEnd")
                .titlePhrases(List.of("Ui developer", "Ui engineer", "UX developer",
                        " React", "Angular", "Typescript", "Javascript", " UX", "Front end", "Frontend", "Front-end"))
                .descriptionPhrasesAndCounts(List.of(new DescriptionPhrasesAndCount(List.of(
                        "bootstrap ", "CSS", "HTML", "Javascript", "frontend", "front end", " UX",
                        " ui", "ui ", "react", "angular", "angularjs", "typescript", "vue", "jquery", "JSP", "JSF"), 3), new DescriptionPhrasesAndCount(List.of(
                        "Redux", "PrimeFaces"
                        , "FreeMarker", "Handlebars", "multiple front-end frameworks"), 1)))
        );


        if (preferences.isExcludeFullStack()) {
            filters.add(ExcludeJobFilter.build("Fullstack")
                    .titlePhrases(List.of("Full stack", "Fullstack", "Full-Stack", "Facets"))
                    .descriptionPhrases(List.of("fullstack", "full stack", "full-stack"))
            );
        }



        filters.add(ExcludeJobFilter.build("HighSchoolTypeJob")
                .titlePhrases(List.of("Cashier", "Receptionist", "Concierge", "Porter", "Customer Service", "Office Assistant", "Customer Specialist", "Housekeeping", "Housekeeper",
                        "Custodian", "Janitor", "Maintenance", "Maintenance Worker","Mailroom",
                        "Administrative Assistant", "Hairstylist", "Warehouse", "Customer Success", "Customer Support", "Front Desk",
                        "Clerk", "Laborer", "Attendant", "Call Center",
                        "Groomer", "Membership Experience", "Member Experience",
                        "Froster",  "Support Associate", "Construction", "Manufacturing", "Assembler", "Client Service Associate"
                        , "Stockroom Associate","Store Associate",  "Brand Ambassador", "Lifeguard", "Installer", "Installation"
                        , "Operator", "Guest Experience", "Customer Experience","Dock Worker"))
        );

        filters.add(ExcludeJobFilter.build("HomeCare")
                .titlePhrases(List.of("Home Health Aide", "HHA", "Nanny"))
        );

        filters.add(ExcludeJobFilter.build("HumanAndCustomerManagementAndSalesTechnology")
                .titlePhrases(List.of("Genesys", "CRM", "Salesforce", "Dynamics"
                        , "d365", "Exstream", "Power Platform", "HRMS", "Peoplesoft",
                        "HRIS", "Kitewheel", " HCM ", "Vlocity", "Medallia", "SFCC"))
                .badCompanies(List.of("Ceridian"))
        );
        if (preferences.isExcludeIdentityManagement()) {
            filters.add(ExcludeJobFilter.build("IdentityManagement")
                    .titlePhrases(List.of("IAM ", "Identity Engineer"))
                    .titleAndDescriptionPhrases(List.of("Identity Governance", " IAM ",
                            "Access Management",
                            "Sailpoint", "IdAM ", "Active Directory"))
                    .badCompanies(List.of("Provision IAM"))
                    .descriptionPhrasesAndCount(List.of("Identity Governance", " IAM ",
                            "Access Management",
                            "Sailpoint", "IdAM ", "Active Directory", "Identity Engineer", "CyberArk"), 2)
                    .testForCompanyInDescription(true)
            );
        }
        filters.add(ExcludeJobFilter.build("Integration")
                .titleAndDescriptionPhrases(List.of("Mulesoft", "Boomi ", "Implementation Engineer", "Integration "
                        , "Integrations ", "System Integrator"))
        );

        filters.add(ExcludeJobFilter.build("InventoryManagement")
                .titlePhrases(List.of("RF Smart", "RF-Smart","Stock Associate", "Inventory"))
        );

        filters.add(ExcludeJobFilter.build("ITRole")
                .titlePhrases(List.of("IT Engineer"))
        );
        filters.add(ExcludeJobFilter.build("LifecycleManagement")
                .titlePhrases(List.of("Lifecycle Management", "Teamcenter", "PLM "))
        );

        filters.add(ExcludeJobFilter.build("LoAndNoCode")
                .titleAndDescriptionPhrases(List.of("Pega ", "Servicenow", "Low Code", "Low-Code",
                        "lansa ", "Quickbase", "Entellitrek", "Entellitrak", "Powerapps", "Low- Code",
                        "No-Code", "Unqork"))
        );

        filters.add(ExcludeJobFilter.build("MegaBrand")
                .badCompanies(List.of("Amazon", "Facebook", "Pinterest", "Walmart", "McDonald's", "Sam's Club"))
        );

        filters.add(ExcludeJobFilter.build("Messaging")
                .titlePhrases(List.of("Kafka"))
        );
        if (preferences.isSoftwareSearch()) {
            filters.add(ExcludeJobFilter.build("MobileProgramming")
                    .titlePhrases(List.of("Mobile", "Android", "iOS"))
            );
        }

        filters.add(ExcludeJobFilter.build("MST")
                .descriptionPhrases(List.of(" MST ", " MDT ", "overlap with 9am-5pm MST"))
        );




        filters.add(ExcludeJobFilter.build("Performance")
                .descriptionPhrases(List.of("profiling", "optimizing", "Tuning", "Onestream", " cpm "))
        );

        filters.add(ExcludeJobFilter.build("PhysicalEngineer")
                .titlePhrases(List.of("Service Engineer", "Project developer", "Civil Engineer",
                        "Design engineer", "Project Engineer", "field services", "field service",
                        "field engineer", "Applications Engineer", " OEM ", "Autodesk", " CAD ", "MES ",
                        "OSP Engineer"))
                .safeTitlePhrases(List.of("Business Applications"))
        );

        filters.add(ExcludeJobFilter.build("ProfessionalServicesEngineer")
                .titlePhrases(List.of("Professional Services Engineer")));

        filters.add(ExcludeJobFilter.build("PST")
                .descriptionPhrases(List.of(" PST ", " PDT ", " Pacific ", "overlap with 9am-5pm PST"))
        );

        if (preferences.isSoftwareSearch()) {
            filters.add(ExcludeJobFilter.build("QA")
                    .titlePhrases(List.of("Test engineer", "SDET", "tester", "QA ", "Software Developer In Test", "Software Developer Engineer in Test",
                            "Verification Engineer", "Quality Engineer", "Software Engineer In Test", "Software Development Engineer in Test",
                            "Test", "Quality Assurance"))
            );
        }

        if (preferences.isExcludeRealEstate()) {
            filters.add(ExcludeJobFilter.build("RealEstate")
                    .badCompanies(List.of("Anywhere Real Estate Inc.", "Pacaso",
                            "Aalto", "MRI Software", "RE/MAX"))
                    .industries(List.of("Real Estate"))
                    .descriptionPhrases(List.of("real estate", "real-estate"))
            );
        }

        filters.add(ExcludeJobFilter.build("Recruiter")
                .titlePhrases(List.of("Talent Acquisition", "Recruiter", "Recruitment"))
        );


        filters.add(ExcludeJobFilter.build("RegularlyWarringCountryCompany")
                .badCompanies(List.of("Київстар"))
                .testForCompanyInDescription(true)
        );

        filters.add(ExcludeJobFilter.build("Religious")
                .badCompanies(List.of("The Church of Jesus Christ of Latter-day Saints"))
                .testForCompanyInDescription(true)
        );
        filters.add(ExcludeJobFilter.build("RequiresTraining")
                .titlePhrases(List.of( "Physician", "Surgeon", "Researcher", "Dental", "Dentist", "Veterinarian", "Psychologist",
                        "Supervisor", "Veterinary", "Pathologist", "Ophthalmic", "Optometric", "Optometrist", "Optician", "Orthopedic",
                        "Ophthalmologist", "Radiograph", "Radiolog", "Psychiatrist", "Nurse Practitioner", "Physicist", "Phlebotomist",
                        "Cardiac", "Cardiol", "CNA", "Physical Therapist","Respiratory Therapist"))
        );

        filters.add(ExcludeJobFilter.build("SalesAndMarketing")
                .titlePhrases(List.of("Solution engineer", "Solutions engineer"
                        , "Solutions Developer", "Solution Developer", "Sales", "Marketing", "SingleView"))
                .badCompanies(List.of("Velir"))
                .industries(List.of("Marketing Services"))
        );

        filters.add(ExcludeJobFilter.build("ScientificProgrammer")
                .titlePhrases(List.of("Scientific Programmer"))
        );

        filters.add(ExcludeJobFilter.build("SoftwareDevelopmentProjectManagement")
                .titlePhrases(List.of("Jira"))
        );


        filters.add(ExcludeJobFilter.build("SpecializedSoftwareEngineer")
                .titlePhrases(List.of("PLC Programmer"))
        );

////////////////////////////////////////


        filters.add(ExcludeJobFilter.build("Gambling")
                .badCompanies(List.of("betting", "casino"))
                .testForCompanyInDescription(true)
        );

        filters.add(ExcludeJobFilter.build("Javascript").descriptionPhrases(List.of("advanced JavaScript")));

        filters.add(ExcludeJobFilter.build("JobSecurity").badCompanies(List.of("Allstate", "New Relic", "Breezeline", "Slack",
                        "Crossover", "Invitae", "Omnicell", "Komodo Health", "Rocket Software", "Zinnia", "CSG",
                        "NTT DATA Services", "Cruise", "VMware", "Intelerad Medical Systems",
                        "Air Apps", "CivicPlus", "Vertisystem Inc.", "Kyruus", "Atlassian"))
                .includeAttribute("jobSecurity")
                .excludeAttributes(List.of("recentLayoffs"))
        );


        filters.add(ExcludeJobFilter.build(" JobTooOld").maxAttribute("jobAgeInDays", (float) preferences.getMaxJobAgeInDays()));

        filters.add(ExcludeJobFilter.build("Law")
                .titlePhrases(List.of("Counsel ", "Law ","Legal", " law"))//we actively work with our legal and security teams
                .titleAndDescriptionPhrases(List.of("Attorney", "Lawyer", "Paralegal",  "Litigation"))
        );

        filters.add(ExcludeJobFilter.build("Manufacturing")
                .badCompanies(List.of("Adaptec Solutions"))
                .industries(List.of("Automation Machinery Manufacturing"))
        );

        filters.add(ExcludeJobFilter.build(" MicrosoftStack").badCompanies(List.of("Homecare Homebase")).testForCompanyInDescription(true));

        filters.add(ExcludeJobFilter.build("New Jersey")
                .titleAndDescriptionPhrases(List.of("Newark", "New Jersey", "Jersey City", "New Brunswick", "Trenton","Freehold", "Camden", "Toms River", "Bayonne", "East Orange", "Perth Amboy", "Passaic"))
        );

        filters.add(ExcludeJobFilter.build("NightShift")
                .descriptionPhrases(List.of("nights", "pm to close"))
                .titlePhrases(List.of("Evening"))
                .titleAndDescriptionPhrases(List.of("- 7A", "- 7A", "- 7:", "- 7:", "- 8A", "- 8A", "- 8:", "- 8:", "Over Night", "-11p"))
                .titleAndDescriptionPhrases(List.of("2nd shift", "3rd shift", "second shift", "third shift", "night shift", "evening shift", "graveyard shift"))
                .excludeAttributes(List.of("softwareEngineerNightOrWeekendHours"))
        );

        filters.add(ExcludeJobFilter.build("NotEnglish")
                .titleAndDescriptionPhrases(List.of("Entwick", "korean", "Versicherung", "Softwarový"
                        , "inženýr", "Japanese", "Pessoa", "Desenvolvedor", "Pleno", "Sênior", "Especialista",
                        "Trabalho", "Remoto", "automatizadora", " de ", "testes", "Pleno", "Sênior",
                        "Geliştirici", "NİTELİKLER", "merkezli", "şirketi", "deneyimi", "Güçlü", "analizi",
                        "equipo", "Desarrollo", " Wir ", "Ingeniero", "Especializado", "Japanese", "Mandarin"
                        , "同"))
        );


        filters.add(ExcludeJobFilter.build(" NotPeopleFocused").badCompanies(List.of("Maximus", "IDR, Inc.", "Marketlab",
                "Gainwell Technologies", "Revature",
                "Solü Technology Partners")));


        if (preferences.isExcludeOnCall()) {
            filters.add(ExcludeJobFilter.build("OnCall").descriptionPhrases(List.of("on call", "on-call", "go-live support",
                            "24/7", " 24-7 ",
                            "24x7", "rotation", "After business hours", "After hours",
                            "outside of normal business", "outside normal business"))
                    .badCompanies(List.of("Oracle", "Ancestry", "Gremlin", "Homecare Homebase"))
                    .safeDescriptionPhrases(List.of("internal rotation"))
                    .testForCompanyInDescription(true)
                    .excludeAttributes(List.of("softwareEngineerAfterHoursSupport"))
            );
        }

        filters.add(ExcludeJobFilter.build("OpenCall")
                .titlePhrases(List.of("Engineering Prospects", "open call", "Expression of Interest"))
        );

        filters.add(ExcludeJobFilter.build("OperatingSystemCompany")
                .badCompanies(List.of("Canonical"))
        );

        filters.add(ExcludeJobFilter.build("Pennsylvania")
                .badCompanies(List.of("Drexel"))
                .titleAndDescriptionPhrases(List.of("Philadelphia","Havertown","Norristown","Schwenksville"))
        );
        filters.add(ExcludeJobFilter.build("PharmacyAndPharmaceutical")
                .titlePhrases(List.of("Pharmacist", "Pharmacy"))
                .badCompanies(List.of("CVS Health"))
                .industries(List.of("Pharmaceutical Manufacturing"))
                .testForCompanyInDescription(true)
        );

        filters.add(ExcludeJobFilter.build("Retail")
                .badCompanies(List.of("Abercrombie"))
                .industries(List.of("Retail", "Apparel, Accessories & Footwear"))
                .descriptionPhrases(List.of("retail"))
                .testForCompanyInDescription(true)
        );

        if (preferences.isExcludeSenior()) {
            /**
             * Exceptions
             * Coaches: Health Coaches(Benefit)
             * expert: recruiter may have expertise and seems hyperbolic at times
             * principal can only be in title because it could be reports to
             * specialist also may refer to recruiter
             *
             * subject matter expert:   seems to happen in non senior roles too
             * Advanced knowledge of:   seems to happen in quite jr roles too
             */

            filters.add(ExcludeJobFilter.build("Senior")
                    .descriptionPhrases(List.of("Completes product technical design",
                            "produce software designs", "subordinate", "lead project teams"))
                    .safeDescriptionPhrases(List.of("Intermediate to advanced knowledge of"))
                    .titlePhrases(List.of("specialist", "sr ", "sr.", "senior"))
            );

        }

        filters.add(ExcludeJobFilter.build("Skin")
                .titlePhrases(List.of("Dermatologist", "Dermatology", "Cosmetic", "Aesthetic", "Plastic Surgeon", "Plastic Surgery"))
        );

        /**
         * Exceptions
         * Bonus : Could be a bonus plan for referrals
         * investors: can have investors but still be a PBC
         * funded:      could refer to insurance
         * funding:     could refer to contracts
         * founding:    can only be in title as could refer to founding principle
         * ventures:    can return to internal program like a mentorship joint ventures one
         *evaluations
         * examples
         * venture-backed
         */
        filters.add(ExcludeJobFilter.build("Startup")
                .badCompanies(List.of("Patterned Learning AI", "Patterned Learning AI",
                        "Patterned Learning Career", "minware", "Included Health",
                        "Storm 3", "Storm 4", "Storm 5", "Storm 6",
                        "Nira Energy", "Apploi", "Convictional", "Bramkas",
                        "Ascendion", "WellSaid Labs", "Alma", "Maven Clinic", "hims & hers", "Amberflo.io",
                        "AllVoices", "Certificial", "Rutter", "Hazel Health", "AIQ (Alpine IQ)", "Jerry",
                        "Underdog.io", "ONE", "Apexon", "Docugami", "Clerkie", "Human Interest",
                        "CornerUp", "Cloudbeds", "SandboxAQ", "Fitness Matrix Inc", "Sight Machine",
                        "Offered.ai", "SpectrumAi", "Numerated", "Avid Technology Professionals", "TherapyNotes, LLC"))
                .descriptionPhrases(List.of("seed-stage", "y combinator", "backed", " early stage",
                        " VC", "investors", "pre-seed", " valuation"))
                .safeDescriptionPhrases(List.of("public benefit corporation", "PBC"))
                .titleAndDescriptionPhrases(List.of("startup", "start-up", " start up "
                        , "B corp", "Series A", "Series B", "foundational", "scale-up"))
                .titlePhrases(List.of("founding", "Founder", "Entrepreneur"))
                .testForCompanyInDescription(true)
        );

        filters.add(ExcludeJobFilter.build("SupportFocusedJob")
                .descriptionPhrases(List.of("Support tickets", "second level support"))
                .titlePhrases(List.of("Support"))
        );

        if (preferences.isSkipTooManyApplicants()) {
            filters.add(ExcludeJobFilter.build("TooManyApplicants").maxAttribute("NumApplicants", (float) preferences.getMaxApplicants()));
        }

        filters.add(ExcludeJobFilter.build("Transportation")
                .badCompanies(List.of("Cambridge Systematics, Inc."))
                .industries(List.of("Truck Transportation","Transportation"))
                .titlePhrases(List.of("Delivery", "Driver","transport","Courier"))
        );


        if (preferences.isExcludeWeekends()) {
            filters.add(ExcludeJobFilter.build("Weekends").descriptionPhrases(List.of("weekends")));
        }
        if (preferences.isExcludePoorWorkLifeBalance()) {
            filters.add(ExcludeJobFilter.build("WorkLifeBalance").goodCompanies(List.of("Ebay", "Guidehouse", "Trimble"
                            , "American Specialty Health", "Nationwide", "Webstaurant Store",
                            "Mayo Clinic"))
                    .badCompanies(List.of("Cardinal Health", "Cruise", "CVS Health", "Aha!", "Cash App"
                            , "Square", "Crunchyroll", "HCLTech", "Palo Alto Networks", "Intelerad Medical Systems",
                            "Tenable", "Kasten by Veeam", "Dremio", "Gigster", "Samsung Electronics",
                            "Arize AI", "Gevo, Inc.", "Harmonia Holdings Group, LLC", "Block",
                            "Penn State Health", "Actalent", "Grafana Labs", "Softrams", "FinTech LLC",
                            "Paytient", "DaVita", "Businessolver", "Integra Connect",
                            "Discover Financial Services", "CivicPlus", "Saxon-Global", "GE", "The Home Depot", "The Wendy's Company"
                    ))
                    .excludeAttributes(List.of("workLifeBalance", "softwareEngineerHighOvertime"))
                    .testForCompanyInDescription(true)
            );

        }
        return filters;
    }

    /**
     * These are skipped in case we want to include them later or in another search
     *
     * @param preferences
     * @return
     */
    public static List<IncludeOrSkipJobFilter> getShallowFiltersSkip(Preferences preferences) {
        List<IncludeOrSkipJobFilter> filters = new ArrayList<>();
        if (preferences.isSkipTooManyApplicants()) {
            filters.add(IncludeOrSkipJobFilter.build("TooManyApplicants").minAttribute("NumApplicants", (float) preferences.getMaxApplicants()));
        }
        filters.add(IncludeOrSkipJobFilter.build("YoungAge").maxAttribute("jobAgeInDays", (float) preferences.getMinJobAgeInDays()));

        return filters;
    }

    public static List<ExcludeJobFilter> getExcludeFilters(Preferences preferences) {
        List<ExcludeJobFilter> filters = new ArrayList<>();
        /*
                filters.add(new FortuneRankFilter());

        filters.add(new ApplicationEngineerFilter());
        filters.add(new BusinessIntelligenceFilter());
        filters.add(new BusinessProcessManagementFilter());
        filters.add(new CachingFilter());
        filters.add(new ComplexFilter());
        filters.add(new CustomerExperienceManagementFilter());
        filters.add(new CyberSecurityFilter());
        filters.add(new DataExchangeFilter());
        filters.add(new EnterpriseApplicationsFilter());


        filters.add(new ERPFilter());
        filters.add(new GeospatialFilter());

        filters.add(new InfrastructureSoftwareFilter());
        filters.add(new JeeFilter());


        filters.add(new OracleTechFilter());

        filters.add(new PLMFilter());

        filters.add(new SearchFilter());
        filters.add(new UnixFilter());
        filters.add(new YearsExperienceFilter());
*/

        filters.add(ExcludeJobFilter.build("Acquisitions")
                .excludeAttributes(List.of("acquisitions"))
        );
        filters.add(ExcludeJobFilter.build("Advertising").industries(List.of("Advertising Services", "Advertising")).badCompanies(List.of("SocialVenu", "advertising"))
                .testForCompanyInDescription(true)
        );


        filters.add(ExcludeJobFilter.build("AI")
                .descriptionPhrasesAndCount(List.of("chatbot", "chatbots", "Conversational AI", "ML", "NLP"
                        , "natural language processing", "NLU", "Natural Language Understanding"
                        , "machine learning", "TTS", "STT", "SSML", "Tensorflow", "Pytorch"
                        , "ONNX", "MXNet"), 2)
        );

        filters.add(ExcludeJobFilter.build("BadManagement").badCompanies(List.of("Research Innovations Incorporated",
                "IT Labs", "Hansen Technologies", "LaunchDarkly", "TIDAL", "DXC Technology", "Oak Ridge National Laboratory", "EPLAN",
                "Envision Horizons"))
        );

        filters.add(ExcludeJobFilter.build("CaseManagement")
                .titleAndDescriptionPhrases(List.of("Entellitrak", "Documentum")));

        filters.add(ExcludeJobFilter.build("Commerce")
                .titlePhrases(List.of("OMS ", "Magento"))
                .badCompanies(List.of("Whatnot", "Nisum"))
                .industries(List.of("Retail", "E-commerce"))
                .titleAndDescriptionPhrases(List.of("Hybris", " OMS ", "Vericent", "Varicent", "Shopify"))
        );


        filters.add(ExcludeJobFilter.build("ComputerVision")
                .titleAndDescriptionPhrases(List.of("Computer Vision", "computer-vision"))
        );


        if (preferences.isExcludeConsultant()) {
        /*exceptions
    planning consultants
    consulting with customers
     */

            filters.add(ExcludeJobFilter.build("Consultant")
                    .titlePhrases(List.of("consulting", "consultant"))
                    .badCompanies(List.of("Curate Partners", "Modis", "Akkodis"
                            , "Ricardo plc", "FullStack Labs", "Sierra7", "Sierra7, Inc.", "Vaco", "QuantumBricks",
                            "Lorven Technologies Inc.", "ZETTALOGIX INC", "Sierra Solutions", "CGI",
                            "Daugherty Business Solutions", "World Wide Technology", "Qualitest", "Cognizant",
                            "Solü Technology Partners", "Nakupuna Companies", "SDI Presence",
                            "DMI (Digital Management, LLC)", "Next Level Business Services, Inc.",
                            "NLB Services"))
                    .industries(List.of("Business Consulting and Services", "Consulting"))
                    .titleCompanyNameAndDescriptionPhrases(List.of("consultancy"))
                    .testForCompanyInDescription(true)
                    .excludeAttributes(List.of("consulting")
                    ));
        }

        filters.add(ExcludeJobFilter.build("Construction")
                .industries(List.of("Construction"))
                .badCompanies(List.of("UpCodes", "Construction"))
        );

        filters.add(ExcludeJobFilter.build("ContentManagement")
                .titlePhrases(List.of("Content Management", "CMS ", "Onbase", "CMS(", "CMS "))
                .descriptionPhrasesAndCount(List.of("AEM ", "Adobe Experience Manager", "Drupal", "Alfresco", " onbase",
                        "Sitecore", "Tridion"), 2)
                .caseSensitiveDescriptionPhrases(List.of("Brightspot"))
        );

        if (preferences.isExcludeContractJobs()) {
             /*
    exceptions:
    positions are subject to background screening as required by law or contract
     */

            filters.add(ExcludeJobFilter.build("Contract").badCompanies(List.of("Accenture Federal Services", "Guidehouse"
                            , "Wise Skulls", "Brooksource", "Harnham", "Cypress HCM", "Belcan", "Mindex",
                            "Kforce Inc", "Kforce Com", "Oktobor Animation", "Groundswell", "Raft",
                            "NTT DATA Services", "Spatial Front, Inc", "Tential Solutions",
                            "IT Crowd", "Koniag Government Services", "SCIGON", "Latitude Inc", "IT Labs",
                            "AgileEngine", "Bitsoft International, Inc.", "Revature", "Gridiron IT", "mphasis",
                            "Brillio", "GE", "SBS Creatix", "Compunnel Inc."))
                    .titlePhrases(List.of("contract"))
                    .excludeIfTrueJobAttribute("contract")
                    .excludeAttributes(List.of("contractor"))
            );
        }


        filters.add(ExcludeJobFilter.build("Entertainment")
                .badCompanies(List.of("NBCUniversal"))
                .industries(List.of("Entertainment Providers"))
        );

        filters.add(ExcludeJobFilter.build("ExternalContractor")
                .excludeAttributes(List.of("softwareEngineerExternalContractors"))
        );





        filters.add(ExcludeJobFilter.build("Manufacturing")
                .badCompanies(List.of("Adaptec Solutions"))
                .industries(List.of("Automation Machinery Manufacturing"))
        );


        if (preferences.getMaxEmployees() != null) {
            filters.add(ExcludeJobFilter.build("NumEmployees").maxAttribute("MinimumNumEmployees", (float) preferences.getMaxEmployees()));
        }

        filters.add(ExcludeJobFilter.build("OutsourcingAndOffshore")
                .badCompanies(List.of("Rockwell Automation", "Equinix", "Banner Health",
                        "Blue Cross NC", "Ascension", "Transamerica", "Conduent", "Zinnia", "LiveRamp",
                        "Intelerad Medical Systems"))
                .testForCompanyInDescription(true)
                .excludeAttributes(List.of("offshores"))
                .descriptionPhrases(List.of("IT services and outsourcing company", "with offshore"))
        );

        filters.add(ExcludeJobFilter.build("PharmacyAndPharmaceutical")
                .titlePhrases(List.of("Pharmacist", "Pharmacy"))
                .badCompanies(List.of("CVS Health"))
                .industries(List.of("Pharmaceutical Manufacturing"))
                .testForCompanyInDescription(true)
        );

        filters.add(ExcludeJobFilter.build("ResearcherFilter").safeDescriptionPhrases(List.of("reading industry publications", "reading publications"))
                .descriptionPhrases(List.of(" patent", "AAAI", "IAAI",
                        "IJCAI", " HRI ", " ICAPS", "AAMAS", "ICRA", "IROS", "ICLR", "ICML",
                        "NeurIPs", "CORL", " ITSC", "journals ", "publications"))
                .titlePhrases(List.of("research", "R&D "))
                .titleAndDescriptionPhrases(List.of("researcher"))
        );

        filters.add(ExcludeJobFilter.build("Retail")
                .badCompanies(List.of("Abercrombie"))
                .industries(List.of("Retail", "Apparel, Accessories & Footwear"))
                .descriptionPhrases(List.of("retail"))
                .testForCompanyInDescription(true)
        );


        filters.add(ExcludeJobFilter.build("Stress")
                .excludeAttributes(List.of("stress"))
        );


        if (preferences.isSkipTooManyApplicants()) {
            filters.add(ExcludeJobFilter.build("TooManyApplicants").maxAttribute("NumApplicants", (float) preferences.getMaxApplicants()));
        }

        if (preferences.isExcludePoorWorkLifeBalance()) {
            filters.add(ExcludeJobFilter.build("WorkLifeBalance").goodCompanies(List.of("Ebay", "Guidehouse", "Trimble"
                            , "American Specialty Health", "Nationwide", "Webstaurant Store",
                            "Mayo Clinic"))
                    .badCompanies(List.of("Cardinal Health", "Cruise", "CVS Health", "Aha!", "Cash App"
                            , "Square", "Crunchyroll", "HCLTech", "Palo Alto Networks", "Intelerad Medical Systems",
                            "Tenable", "Kasten by Veeam", "Dremio", "Gigster", "Samsung Electronics",
                            "Arize AI", "Gevo, Inc.", "Harmonia Holdings Group, LLC", "Block",
                            "Penn State Health", "Actalent", "Grafana Labs", "Softrams", "FinTech LLC",
                            "Paytient", "DaVita", "Businessolver", "Integra Connect",
                            "Discover Financial Services", "CivicPlus", "Saxon-Global", "GE", "The Home Depot", "The Wendy's Company"
                    ))
                    .excludeAttributes(List.of("workLifeBalance", "softwareEngineerHighOvertime"))
                    .testForCompanyInDescription(true)
            );

        }


        return filters;
    }

    public static List<IncludeOrSkipJobFilter> getDeepFiltersSkip(Preferences preferences) {
        List<IncludeOrSkipJobFilter> filters = new ArrayList<>();
        filters.add(IncludeOrSkipJobFilter.build("YoungAge").maxAttribute("jobAgeInDays", (float) preferences.getMinJobAgeInDays()));
        return filters;
    }


    /**
     * These will not always include but will be used when we were going to include anyway due to no excludes. This
     * will help us manually approve
     *
     * @return
     */
    public static List<IncludeOrSkipJobFilter> getIncludeFilters(Preferences preferences) {
        List<IncludeOrSkipJobFilter> filters = new ArrayList<>();

        filters.add(IncludeOrSkipJobFilter.build("Tenure").minAttribute("Tenure", preferences.getDesiredTenure()));
        filters.add(IncludeOrSkipJobFilter.build("Backend").titlePhrases(List.of("Backend", "Back-end", "Back end")));

        filters.add(IncludeOrSkipJobFilter.build("JobSecurity").goodCompanies(List.of("Mayo Clinic"))
                .includeAttribute("jobSecurity")
        );
        filters.add(IncludeOrSkipJobFilter.build("RelaxedEnvironment").goodCompanies(List.of("State Farm")));
        filters.add(IncludeOrSkipJobFilter.build("ProgrammingLanguage").skills(preferences.getProgrammingLanguages())
                .runIfFalse(List.of(preferences.isSoftwareSearch())));


        if (preferences.isExcludePoorWorkLifeBalance()) {
            filters.add(IncludeOrSkipJobFilter.build("WorkLifeBalance").goodCompanies(List.of("Ebay", "Guidehouse", "Trimble"
                            , "American Specialty Health", "Nationwide", "Webstaurant Store",
                            "Mayo Clinic"))
                    .badCompanies(List.of("Cardinal Health", "Cruise", "CVS Health", "Aha!", "Cash App"
                            , "Square", "Crunchyroll", "HCLTech", "Palo Alto Networks", "Intelerad Medical Systems",
                            "Tenable", "Kasten by Veeam", "Dremio", "Gigster", "Samsung Electronics",
                            "Arize AI", "Gevo, Inc.", "Harmonia Holdings Group, LLC", "Block",
                            "Penn State Health", "Actalent", "Grafana Labs", "Softrams", "FinTech LLC",
                            "Paytient", "DaVita", "Businessolver", "Integra Connect",
                            "Discover Financial Services", "CivicPlus", "Saxon-Global", "GE", "The Home Depot", "The Wendy's Company"
                    ))
                    .testForCompanyInDescription(true)
            );

        }

        return filters;
    }

    public static List<IncludeOrSkipJobFilter> getAlwaysIncludeFilters(Preferences preferences) {
        List<IncludeOrSkipJobFilter> filters = new ArrayList<>();
        filters.add(IncludeOrSkipJobFilter.build("EarlyCareer")
                .titleAndDescriptionPhrases(List.of("Early career", "Entry level", "Entry-Level"))
                .titlePhrases(List.of("entry", "early"))
        );

        if(preferences.isIncludeEarlyToMidCareer()) {
            /**
             * Exceptions duties associated patient
             */
            filters.add(IncludeOrSkipJobFilter.build("EarlyToMidCareer").titleAndDescriptionPhrases(List.of(
                            "associate ", "junior", "jr", "intermediate"))
                    .titlePhrases(List.of("entry", "early", "mid", "associate"))
            );
        }

        filters.add(IncludeOrSkipJobFilter.build("FavoringCitizen").titleAndDescriptionPhrases(List.of(" mbi ", "public trust", "IRS Clearance",
                "Citizenship required")).descriptionPhrases(List.of("Must be a US Citizen", "maintain a security clearance"
                , "obtain a security clearance"
        )));
        filters.add(IncludeOrSkipJobFilter.build("ModernizingMicroservices").descriptionPhrases(List.of("microservice", "microservices", "modernization", " API ", " APIs ",
                "RestFul", "webservice", "web service")));
        filters.add(IncludeOrSkipJobFilter.build("Spring").descriptionPhrases(List.of("Spring Boot", "Spring Data", "Spring MVC", "Spring Batch")));
        filters.add(IncludeOrSkipJobFilter.build("PeopleFocused").descriptionPhrases(List.of("life balance", "people first", "like family"))
                .includeAttribute("peopleFocused"));

        filters.add(IncludeOrSkipJobFilter.build("PublicGood").testForCompanyInDescription(true).goodCompanies(List.of("Blackbaud", "Mayo Clinic"))
                .descriptionPhrases(List.of("nonprofit", "health equity", "fair opportunity", "unjust"
                        , "structural inequities", "racism", "underserved", "food access")).industries(List.of("Hospitals and Health Care", "Mental Health Care", "Higher Education"))
                .includeAttribute("publicGood")
        );


        return filters;
    }

}
