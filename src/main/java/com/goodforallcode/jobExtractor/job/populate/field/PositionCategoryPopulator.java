package com.goodforallcode.jobExtractor.job.populate.field;

import com.goodforallcode.jobExtractor.filters.DescriptionPhrasesAndCount;
import com.goodforallcode.jobExtractor.filters.ExcludeJobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.ArrayList;
import java.util.List;

public class PositionCategoryPopulator implements FieldPopulator {
    public void populateField(Job job, Preferences preferences) {
        List<ExcludeJobFilter> filters = new ArrayList<>();

        filters.add(ExcludeJobFilter.build("AccountManagement")
                .titlePhrases(List.of("Account Manage"))
        );

        filters.add(ExcludeJobFilter.build("AdobeSoftwareDeveloper")
                .titlePhrases(List.of("AEM Developer"))
        );

        /**  Exceptions
         * Waitstaff
         */
        filters.add(ExcludeJobFilter.build("Advertising").industries(List.of("Advertising Services", "Advertising")).matchingCompanies(List.of("SocialVenu", "advertising"))
                .testForCompanyInDescription(true)
        );


        /**
         * Exceptions
         * recovery: Make a meaningful impact every day as a CenterWell Home Health nurse. You’ll provide personalized, one-on-one care that helps patients regain independence in the comfort of their homes. Working closely with a dedicated team of physicians and clinicians, you’ll develop and manage care plans that support recovery and help patients get back to the life they love
         */
        filters.add(ExcludeJobFilter.build("Addiction")
                .titleCompanyNameAndDescriptionPhrases(List.of("Addiction", "Substance Abuse"))
        );

        filters.add(ExcludeJobFilter.build("AgileRole")
                .titlePhrases(List.of("RTE Engineer", "Release Train Engineer", "Product Owner"))
        );

        filters.add(ExcludeJobFilter.build("AI").excludeIfTrueJobAttribute("ai"));

        filters.add(ExcludeJobFilter.build("Automation")
                .titlePhrases(List.of("Automation", "Rockwell Portfolio", "BAW"))
        );

        filters.add(ExcludeJobFilter.build("Aviation")
                .matchingCompanies(List.of("Aviation"))
        );

        filters.add(ExcludeJobFilter.build("Automotive")
                .titlePhrases(List.of("Automotive", "MECHANIC"))
                .titleStartsWithPhrases(List.of("Car "))
                .matchingCompanies(List.of("General Motors", "Toyota", "AutoZone", "Nissan", "Honda", "Meineke", "Jiffy Lube", "Firestone", "Goodyear"
                        , "Pep Boys", "Midas", "O'Reilly Auto Parts", "Advance Auto Parts", "Jeep", "Chrysler", "Dodge", "Fiat ", " Ram ", "Alfa Romeo", "Safelite"))
                .matchingCompaniesStartsWith(List.of("Ford"))
        );
        filters.add(ExcludeJobFilter.build("BasicSkillsAide")
                .titlePhrases(List.of("Direct Support Professional", "DSP ", "hygiene", "independence skill"))
        );

        filters.add(ExcludeJobFilter.build("BeautyAndSpa")
                .titlePhrases(List.of("Hairstylist", "Froster", "Stylist", "Dermatologist", "Dermatology", "Cosmetic",
                        "Aesthetic", "Plastic Surgeon", "Plastic Surgery", "Esthetician", "Spa ", "Massage", "Eyebrows"
                ))
                .matchingCompanies(List.of(" spa ", "Estee Lauder"))
                .matchingCompaniesEndsWith(List.of(" spa"))
        );
        filters.add(ExcludeJobFilter.build("BigData")
                .descriptionPhrasesAndCount(List.of("pipeline", "big data",
                        "Extract", "Transform", "Load", "ETL ", "spark", "hive ", "pig ", "warehousing", "hadoop"), 2)
                .titlePhrases(List.of("big data", "ETL ",
                        "Extract, Transform, and Load", "spark", "hive ", "pig ", "warehousing", "hadoop"
                ))
        );
        filters.add(ExcludeJobFilter.build("BlockChain")
                .titleAndDescriptionPhrases(List.of("Blockchain", "Crypto", " NFT ", " DeFi "))
                .matchingCompanies(List.of("CryptoRecruit", "Trust Machines",
                        "Kraken Digital Asset Exchange", "CoinTracker", "Coinbase"))
        );
            /*
            BCBA
             */
        filters.add(ExcludeJobFilter.build("BusinessAnalyst")
                .titlePhrases(List.of("Business Analyst", " BA ","Business Systems Analyst"))
                .titleStartsWithPhrases(List.of("BA "))
                .safeTitlePhrases(List.of("ABA "))
        );
        filters.add(ExcludeJobFilter.build("BusinessIntelligence")
                .titlePhrases(List.of("business intelligence", "BI ", "power bi", "Domo",
                        "Tableau", "Looker", "SAP ", "PowerBI", "QLik", "DataStudio", "ABAP",
                        "Cognos", "Pyramid", "PO Developer", "abinitio", "ab initio", "Yardi ", "QuickSight", "Crystal Report", "Reporting", "Dashboard"))
                .descriptionPhrasesAndCount(List.of("business intelligence", "BI ", "power bi", "Domo",
                        "Tableau", "Looker", "SAP ", "PowerBI", "QLik", "DataStudio", "ABAP",
                        "Cognos", "Pyramid", "PO Developer", "abinitio", "ab initio", "Yardi ", "QuickSight", "Crystal Report"), 1)
        );


        filters.add(ExcludeJobFilter.build("BusinessProcessManagement")
                .titleAndDescriptionPhrases(List.of("POSSE ", " BPM ", "Camunda", "Guidewire", "Onbase",
                        "Appian"))
        );

        filters.add(ExcludeJobFilter.build("Buyer")
                .titlePhrases(List.of("Purchasing Agent"))
        );

        filters.add(ExcludeJobFilter.build("Camp")
                .titleAndDescriptionPhrases(List.of("Camp ", "Counselor"))
                .safeTitleAndDescriptionPhrases(List.of("Readjustment Counselor", "bootcamp", "Health Counselor", "Addictions Counselor"))
        );


        filters.add(ExcludeJobFilter.build("Cardiology")
                .titlePhrases(List.of("Cardiac", "Cardiol", "cardiov"))
        );

        filters.add(ExcludeJobFilter.build("CaseManagementSoftware")
                .titleAndDescriptionPhrases(List.of("Entellitrak", "Documentum"))
        );

        filters.add(ExcludeJobFilter.build("CleaningAndMaintenance")
                .titlePhrases(List.of("Environmental Svc", "Environmental service", "Housekeeping", "Housekeeper", "Cleaner",
                        "Porter", "Custodian", "Janitor", "Maintenance", "Maintenance", "Custodial", "Sanitation"))
        );
        filters.add(ExcludeJobFilter.build("ClinicalDataEntry")
                .titlePhrases(List.of("Coder", "Coding", "Clinical Data Programmer", "EDC Programmer", "EDC Developer"))
        );

        filters.add(ExcludeJobFilter.build("CloudHeavy")
                .titlePhrases(List.of("AWS ", "Epic ", "OpenShift", "SailPoint", "Apigee",
                        "Informatica", "azure ", "gcp", "cloud", "Lambda", "Snowflake",
                        "Matillion", "Apigee", "FlashStack", "SFCC", "PCF", "Flashstack", "ECSA "))
                .titleAndDescriptionPhrases(List.of("SailPoint", "Apigee", "Sail point",
                        "Informatica",
                        "Matillion", "Apigee", "FlashStack", "SFCC", "PCF", "Flashstack", "ECSA "))
                .descriptionPhrasesAndCount(List.of("server-less", "serverless", " SNS", "azure ", "gcp",
                        " SQS", " API Gateway", " EventBridge", " DynamoDB", " Redshift", " S3", "EC2", " AWS ",
                        "cloud-native", "cloud native", "Matillion", " aws ", " Epic Client ", " Epic ancillary ", " Epic system "
                        , " Epic environment", "Hyperspace", "Interconnect", "SailPoint", "Apigee", "cloud",
                        "Informatica", "azure ", "gcp", "cloud", "Lambda", "Snowflake",
                        "Matillion", "Apigee", "FlashStack", "SFCC", "PCF", "Flashstack", "ECSA "), 3)
                .matchingCompanies(List.of("Cloudflare", "Render", "Bowman Williams", "Concourse Labs"))
        );

        filters.add(ExcludeJobFilter.build("Coach")
                .titlePhrases(List.of("Coach"))
        );

        filters.add(ExcludeJobFilter.build("Commerce")
                .titlePhrases(List.of("OMS ", "Magento"))
                .matchingCompanies(List.of("Whatnot", "Nisum"))
                .industries(List.of("Retail", "E-commerce"))
                .titleAndDescriptionPhrases(List.of("Hybris", " OMS ", "Vericent", "Varicent", "Shopify"))
        );


        filters.add(ExcludeJobFilter.build("ComputerVision")
                .titleAndDescriptionPhrases(List.of("Computer Vision", "computer-vision"))
        );

        filters.add(ExcludeJobFilter.build("Construction")
                .industries(List.of("Construction"))
                .matchingCompanies(List.of("UpCodes", "Construction"))
                .titlePhrases(List.of("Construction", "Roofing", "Roofer", "Carpenter", "Electrician", "Plumber", "HVAC", "Masonry"))
        );

        filters.add(ExcludeJobFilter.build("ContentManagementSoftware")
                .titlePhrases(List.of("Content Management", "CMS ", "Onbase", "CMS(", "CMS "))
                .descriptionPhrasesAndCount(List.of("AEM ", "Adobe Experience Manager", "Drupal", "Alfresco", " onbase",
                        "Sitecore", "Tridion"), 2)
                .caseSensitiveDescriptionPhrases(List.of("Brightspot"))
        );

        /**
         * proudly certified as a Woman-owned
         */
        filters.add(ExcludeJobFilter.build("Credentialed")
                .titleAndDescriptionPhrases(List.of("Credentialed", "Licensed", "Registered"))
                .titlePhrases(List.of("Certified"))
                .safeTitleAndDescriptionPhrases(List.of("non-licensed", "registered and turned out", "for licensed "))
        );

        filters.add(ExcludeJobFilter.build("CustomerService")
                .titlePhrases(List.of("Service Representative", "Ambassador", "Customer Service", "Customer Specialist", "Concierge"
                        , "Client Service Associate", "Customer Success", "Customer Support", "Customer Care", "Retention Specialist", "Membership Assistant"
                        , "Patient Representative", "Customer Assistant", "Care & Retention"
                        , "Call Center", "Membership Experience", "Member Experience", "Support Associate", "Guest Experience", "Customer Experience"
                        , "Account Liaison"))
        );


        filters.add(ExcludeJobFilter.build("CyberSecurity")
                .matchingCompanies(List.of("Zscaler", "Fortra", "Concourse Labs", "PropelAuth", "Trinity Cyber", "Quokka.io"))
                .titlePhrases(List.of("Vulnerability engineer", "Detection", "Sentinel", "SIEM ", "Risk ", "Cyber Security", "CyberSecurity", "Cyber-Security"))
                .safeTitlePhrases(List.of("credit risk"))
                .industries(List.of(" Computer and Network Security"))
        );

        filters.add(ExcludeJobFilter.build("Dangerous")
                .titlePhrases(List.of("Inventory Control", "Hazmat", "Security", "Surveillance", "Crisis", "Emergency", "Police Officer", "Private Investigator"
                        , "Firefighter", "Officer"))
                .safeTitlePhrases(List.of("Cyber Security", "Cybersecurity", "Cyber-Security", "Security Engineer", "Medical Officer"))
                .matchingCompanies(List.of("Brookhaven National Laboratory"))
        );


        filters.add(ExcludeJobFilter.build("Database")
                .titlePhrases(List.of("Database Developer", "Database Engineer", "SQL Developer", " SSIS "))
                .safeTitlePhrases(List.of(" and ", "/"))
        );

        filters.add(ExcludeJobFilter.build("DataExchangeSoftware")
                .titleAndDescriptionPhrases(List.of(" EDI "))
        );

        filters.add(ExcludeJobFilter.build("DataFocused")
                .titlePhrases(List.of("Data Migration",
                        " ETL ", "Data Developer", "Analytics", "Statistical",
                        "Data Analyst", "Data Scientist", "Data Science", "Data Engineer", "data analytics",
                        "InterSystems Ensemble", "IRIS ", "Netezza", " ETL "))
                .safeTitlePhrases(List.of(" and ", "/"))

        );

        filters.add(ExcludeJobFilter.build("DataManagementSoftware")
                .titlePhrases(List.of("Collibra", "Markit", "EDM "))
        );


        filters.add(ExcludeJobFilter.build("DayHabilitation")
                .titleAndDescriptionPhrases(List.of("Day Habilitation", "Day Hab "))
        );

        /**
         * Defense can be used to describe defense of a paper
         * military status
         * military service, veteran status, or any other category protected under
         * client-oriented defense
         * readdmissions
         */
        filters.add(ExcludeJobFilter.build("Defense").industries(List.of("Defense"))
                .descriptionPhrases(List.of(" DLA", " DOD", ",DLA", ",DOD",
                        "national security", " missions", "DHS", "army", "navy", "air force",
                        "Lockheed Martin", "Marine Corps", "missile"))
                .excludeCompanyAttributes(List.of("defense"))
                .matchingCompanies(List.of("General Dynamics",
                        "Northrop Grumman", "Parsons Corporation", "Raytheon", "Leidos", "RVCM (RevaComm)", "BAE Systems",
                        "ECS", "Innovative Defense Technologies (IDT)"))
                .matchingCompaniesEquals(List.of("SAIC"))//passaic
        );

        filters.add(ExcludeJobFilter.build("Dental")
                .titlePhrases(List.of("Dental", "Dentist", "Orthodontist", "Hygienist", "Endodontist", "Oral Surgeon", "Periodontist"))
                .matchingCompanies(List.of("Dental"))
        );

        filters.add(ExcludeJobFilter.build("DeveloperAdvocate")
                .titlePhrases(List.of("developer advocate"))
        );

        filters.add(ExcludeJobFilter.build("DevSecOps")
                .titlePhrases(List.of("Datacenter Engineer", "Devop", "Devsecop",
                        "Release Engineer", "Build", "Dev ops", "Devops",
                        " IT Engineer", "Information Technology Engineer",
                        "devsecops", "Monitoring", "VDI ",
                        "Infrastructure", "Site Reliability", "SRE", "Reliability Engineer", "Observability",
                        "Operations", " Ops Engineer", "CSfC Engineer",
                        "Information assurance", "Integration Engineer", "Release Management",
                        "Platform Engineer", "C3ISR", "SysOps", "Kubernetes", "Configuration", "Deployment"))
                .safeTitlePhrases(List.of("Executive Operations"))
                .descriptionPhrasesAndCount(List.of("network", "security", "install", "Delivery",
                        "VMware", "Servers", "Sccm", "administration", "administer",
                        "configuration management", " configure", " deploy", "maintain", "setup"), 3)
                .safeDescriptionPhrases(List.of("Security Act"))
                .titleAndDescriptionPhrases(List.of("Tibco"))

        );

        filters.add(ExcludeJobFilter.build("DietaryAide")
                .titlePhrases(List.of("Dietary Aide"))
        );

        filters.add(ExcludeJobFilter.build("Doctor")
                .titlePhrases(List.of("Physician", "Doctor", "Orthopedic", "Pediatrician", "Neurolog", "Endocrin",
                        "Primary Care", "Urologist"))
                .safeTitlePhrases(List.of("Physician Assistant", "doctoral"))
        );

        filters.add(ExcludeJobFilter.build("Elderly")
                .titlePhrases(List.of("Memory Care", "Senior Living", "Senior Care", "Elderly", "Geriatric"))
                .matchingCompanies(List.of("Senior Living", "Gurwin Healthcare System", "The Bristal Assisted Living", "Atria Senior", "Senior Life"))
                .titleAndDescriptionPhrases(List.of("Assisted Living"))
        );

        filters.add(ExcludeJobFilter.build("EmbeddedSoftware")
                .titleAndDescriptionPhrases(List.of("embedded"))
        );


        filters.add(ExcludeJobFilter.build("EnterpriseApplicationSoftwareDevelopment")
                .descriptionPhrases(List.of("Web Logic", "JBOSS", "WebSphere"))
        );

        filters.add(ExcludeJobFilter.build("Entertainment")
                .matchingCompanies(List.of("NBCUniversal", "Entertainment", "Cinema"))
                .matchingCompaniesEquals(List.of("Regal"))
                .industries(List.of("Entertainment Providers"))
        );

        filters.add(ExcludeJobFilter.build("EnvironmentBenefiting")
                .titlePhrases(List.of("Environmental Engineer"))
        );


        filters.add(ExcludeJobFilter.build("ERP")
                .titlePhrases(List.of("Web Logic", "JBOSS", "WebSphere"))
                .descriptionPhrasesAndCount(List.of("Workday", " ERP ", "NetSuite", "FinancialForce", "Certinia",
                        "Infor ", "Lawson", "Kinetic", "Syteline", "Epicor "), 2)
        );


        /**
         * Exceptions
         * Banking: Applicants will never be asked to provide personal identification information (e.g., SSN, Driver’s License, Passport) or financial information (e.g., Banking Information)
         * an independent brokerage that works to place homeless individuals and families into permanent housing
         */
        filters.add(ExcludeJobFilter.build("Finance")
                .matchingCompanies(List.of("Affirm", "Citibank", "Kraken Digital Asset Exchange", "BankUnited  ", "TransUnion", "Equifax",
                        "Jack Henry", "Equitable", "American Express", "U.S. Bank", "Modulus", "Clerkie", "Experian", "Financial",
                        "Juniper Square", "Peach", "T. Rowe Price", "Studio Management", "Mortgage", "Credit Union", " Bank", "Wells Fargo"))
                .matchingCompaniesStartsWith(List.of("Bank "))

                .industries(List.of("Venture Capital and Private Equity Principals",
                        "Financial Services", "Finance", "Investment Banking", "Investment Banking & Asset Management"))
                .titlePhrases(List.of("Simcorp", "Accounts", "Billing", "Loan Originator", "Wealth", "Financial", "Investment",
                        "Banker", "Finance", "Accounting", "Accountant", "Branch Ambassador", "Underwriting", "Loan ","Actuary",
                        " tax", "tax ", "Bookkeeper", "Collections", "broker", "Revenue", "Billing", "Biller", "Treasury", "Payroll"))
                .safeTitlePhrases(List.of("Financial District"))
                .descriptionPhrases(List.of("FinTech", "asset manager", "financial services",
                        "hedge fund", "trading", "Brokers", " investment management"))
                .safeDescriptionPhrases(List.of("financial stability"))
        );

        filters.add(ExcludeJobFilter.build("Fitness")
                .titlePhrases(List.of("Fitness Specialist", "Personal Trainer", "Fitness Coach", "Fitness Instructor"))
                .matchingCompanies(List.of("Planet Fitness", "Gold's Gym", "24 Hour Fitness", "LA Fitness", "Anytime Fitness",
                        "Snap Fitness", "YMCA", "Curves", "Equinox", "Crunch Fitness", "Life Time Fitness",
                        "OrangeTheory", "F45 Training", "Pure Barre", "SoulCycle"))
        );


        filters.add(ExcludeJobFilter.build("FoodIndustry")
                .titlePhrases(List.of("Dining", "Busser", "To-Go ", "Host", "Baker",
                        "Dishwasher", "Food Service", "Food Runner", "Barista", "Waiter", "Waitress",
                        "Fish Market", "Cook", "Server Assistant", "Bartender", "Prepared Foods", "Deli ", "Culinary", "Cafeteria",
                        "Meat", "Restaurant", "Banquet", " Server", "Chef"
                        , "Kitchen", "Pizza", "Server"))
                .matchingCompanies(List.of("Whole Foods Market", "Outback Steakhouse", "Red Lobster", "Buffalo Wild Wings", "Chick-fil-A", "Denny's",
                        "Cracker Barrel", "Taco Bell", "Pizza Hut", "Waffle House", "IHOP", "Hostess", "Stop & Shop",
                        "Olive Garden", "Applebee's", "Ruby Tuesday", "Checkers & Rally’s Drive-In Restaurants", "Wegmans",
                        "Red Robin", "Cheesecake Factory", "Panda Express", "Chipotle", "Panera", "KFC"
                        , "Baskin Robbins", "Pizza", "Krispy Kreme", "Waffle House", "Denny's", "IHOP", "White Castle",
                        "Dunkin'", "Dunkin "))
        );

        filters.add(ExcludeJobFilter.build("ForwardDeployedEngineer")
                .titlePhrases(List.of("Forward Deployed Engineer"))
        );

        filters.add(ExcludeJobFilter.build("FossilFuels")
                .matchingCompanies(List.of("Pride International", "National Grid"))
                .industries(List.of("Oil and Gas"))
                .safeTitlePhrases(List.of("renewable", "solar", "wind",
                        "nuclear",
                        "mills", "windmill", "geothermal", "geo", "offgrid", "turbine", "sustainable"))
                .testForCompanyInDescription(true)
        );

        filters.add(ExcludeJobFilter.build("FrontEndDeveloper")
                .titlePhrases(List.of("Ui developer", "Ui engineer", "UX developer",
                        " React", "Angular", "Typescript", "Javascript", " UX", "Front end", "Frontend", "Front-end"))
                .titleStartsWithPhrases(List.of("React"))
                .safeDescriptionPhrases(List.of("support front-end", "including front-end engineers"))
                .descriptionPhrasesAndCounts(List.of(new DescriptionPhrasesAndCount(List.of(
                        "bootstrap ", "CSS", "HTML", "Javascript", "frontend", "front end", " UX",
                        " ui", "ui ", "react", "angular", "angularjs", "typescript", "vue", "jquery", "JSP", "JSF"), 3), new DescriptionPhrasesAndCount(List.of(
                        "Redux", "PrimeFaces"
                        , "FreeMarker", "Handlebars", "multiple front-end frameworks"), 1)))
        );

        filters.add(ExcludeJobFilter.build("Fullstack")
                .titlePhrases(List.of("Facets"))
                .titleAndDescriptionPhrases(List.of("fullstack", "full stack", "full-stack"))
        );

        /**
         * Exceptions
         * Game:    Game changing
         * Games/gaming: tickets to games as benefit or in the breakroom
         */
        filters.add(ExcludeJobFilter.build("Gaming")
                .titlePhrases(List.of("game", "games", "Engine "))
                .industries(List.of("Computer Games"))
                .titleAndDescriptionPhrases(List.of("unreal", "Xbox", "PlayStation", "gameplay"))
                .matchingCompanies(List.of("Games", "Unity Technologies", "Electronic Arts", "Activision", "Blizzard",
                        "Zynga", "Ubisoft", "Take-Two Interactive", "Valve Corporation",
                        "Bethesda Softworks", "Square Enix", "Bandai", "Second Dinner", "Sleeper"))
                .safeTitlePhrases(List.of("Recommendation Engine", "Graph Engine"))
        );

        filters.add(ExcludeJobFilter.build("Gambling")
                .matchingCompanies(List.of("betting", "casino"))
                .testForCompanyInDescription(true)
        );

        filters.add(ExcludeJobFilter.build("Geospatial")
                .titlePhrases(List.of("GIS "))
                .titleAndDescriptionPhrases(List.of("geospatial ", " GIS "))
        );

        /**
         * Exceptions
         * Embedded: can't be in description as it could be embedded in our culture
         */
        filters.add(ExcludeJobFilter.build("HardwareSpecificProgramming")
                .titlePhrases(List.of("Embedded", "Centura", " IoT", "circuit"))
                .titleAndDescriptionPhrases(List.of("Systems Programmer", "System Programmer"
                        , "Firmware", "AR/VR headset", "drivers", "sensor", " IoT ", "semiconductor",
                        "VoIP", "GPU "))
                .matchingCompanies(List.of("Trinnex", "NVIDIA"))
        );

        /**
         * Exceptions
         * Strong: strong designing
         * infrastructure: spent last 10 years building infrastructure
         * scalable:        responsible for designing and implementing testable and scalable code.
         * Scalability       might show up in a mon senior position so this probably should not auto exclude
         * scaling          scaling our company
         */
        filters.add(ExcludeJobFilter.build("HighPerformanceComputing")
                .titlePhrases(List.of("HPC"))
                .descriptionPhrases(List.of("latency", "high-volume", "high throughput"))
                .descriptionPhrasesAndCount(List.of("profiling", "optimizing", "Tuning", "Onestream", " cpm "), 1)
        );


        filters.add(ExcludeJobFilter.build("HomeCare")
                .titlePhrases(List.of("Home Health Aid", "HHA", "Nanny", "Companion", "Personal Care Assistant", "PCA"))
                .descriptionPhrases(List.of("in-home", "in home"))
                .matchingCompanies(List.of("Home Companions", "Home Care"))
        );

        filters.add(ExcludeJobFilter.build("Hospitals")
                .matchingCompanies(List.of("Hospital"))
                .titleAndDescriptionPhrases(List.of("Operating Room", "Emergency Room"))
        );


        filters.add(ExcludeJobFilter.build("HumanAndCustomerManagementSoftware")
                .titlePhrases(List.of("Genesys", "CRM", "Salesforce", "Dynamics"
                        , "d365", "Exstream", "Power Platform", "HRMS", "Peoplesoft",
                        "HRIS", "Kitewheel", " HCM ", "Vlocity", "Medallia", "SFCC", " HR ", "Human Resources"))
                .titleStartsWithPhrases(List.of("HR "))
                .matchingCompanies(List.of("Ceridian"))
        );

        filters.add(ExcludeJobFilter.build("HVAC")
                .titleAndDescriptionPhrases(List.of("HVAC"))
        );


        filters.add(ExcludeJobFilter.build("IdentityManagementSoftwareDevelopment")
                .titlePhrases(List.of("IAM ", " IAM", "Identity Engineer", " IDM", "IDM ", " IDAM", "IDAM "))
                .titleAndDescriptionPhrases(List.of("Identity Governance", " IAM ",
                        "Access Management",
                        "Sailpoint", "IdAM ", "Active Directory"))
                .matchingCompanies(List.of("Provision IAM"))
                .descriptionPhrasesAndCount(List.of("Identity Governance", " IAM ",
                        "Access Management",
                        "Sailpoint", "IdAM ", "Active Directory", "Identity Engineer", "CyberArk"), 2)
                .testForCompanyInDescription(true)
        );

        filters.add(ExcludeJobFilter.build("Insurance")
                .matchingCompanies(List.of("Transamerica", "Travelers",
                        "State Farm", "GEICO", "Allstate", "Coalition, Inc.", "One80 Intermediaries", "insurance"))
                .industries(List.of("Insurance"))
                .titlePhrases(List.of("Insurance", "Duck Creek", "Claims Examiner", "Claims Adjuster"))
                .testForCompanyInDescription(false)//can't look for insurance in description as it could be describing a benefit
        );

        filters.add(ExcludeJobFilter.build("IntegrationSoftwareDevelopment")
                .titleAndDescriptionPhrases(List.of("Mulesoft", "Boomi ", "Implementation Engineer",
                        "Integrations ", "System Integrator"))
                .titlePhrases(List.of("Integration "))
        );

        filters.add(ExcludeJobFilter.build("InternetTelevisionAndMobile")
                .matchingCompanies(List.of("QCell"))
                .testForCompanyInDescription(true)
        );


        filters.add(ExcludeJobFilter.build("InventoryManagement")
                .titlePhrases(List.of("RF Smart", "RF-Smart", "Stock Associate", "Inventory"))
        );

        filters.add(ExcludeJobFilter.build("Investigation")
                .titlePhrases(List.of("Auditor","Investigat","Inspector"))
        );

        filters.add(ExcludeJobFilter.build("ITRole")
                .titlePhrases(List.of("IT Engineer"))
        );

        filters.add(ExcludeJobFilter.build("Javascript").descriptionPhrases(List.of("advanced JavaScript")));

        filters.add(ExcludeJobFilter.build("JEE").descriptionPhrasesAndCount(
                List.of("JSP", "JSF", "Struts", "Tomcat", "Websphere", "JBoss",
                        "J2EE", " JEE ", "/JEE"), 2));

        filters.add(ExcludeJobFilter.build("LaborServicesRepresentative")
                .titlePhrases(List.of("Labor Services Representative"))
        );


        filters.add(ExcludeJobFilter.build("Law")
                .titlePhrases(List.of("Counsel ", "Law ", "Legal", " law","Legislat"))//we actively work with our legal and security teams
                .titleEqualsPhrases(List.of("Assistant Counsel"))
                .titleAndDescriptionPhrases(List.of("Attorney", "Lawyer", "Paralegal", "Litigation"))
        );


        filters.add(ExcludeJobFilter.build("LoAndNoCodeProgramming")
                .titleAndDescriptionPhrases(List.of("Pega ", "Servicenow", "Low Code", "Low-Code",
                        "lansa ", "Quickbase", "Entellitrek", "Entellitrak", "Powerapps", "Low- Code",
                        "No-Code", "Unqork"))
        );

        filters.add(ExcludeJobFilter.build("MainframeComputer")
                .titleAndDescriptionPhrases(List.of("Mainframe", "AS400", "RPG", "z/OS", "Adabas", "COBOL"))
                .matchingCompanies(List.of("Rocket Software"))
        );

        filters.add(ExcludeJobFilter.build("Managerial")
                .titlePhrases(List.of("Manager", "developer evangelist", "Coordinator", "Management"))
                .safeTitlePhrases(List.of("Management Applications", "Travel Coordinator", "Case Manager", "Account Management"
                        , "Pain Management", "Central Management Office"))
        );

        filters.add(ExcludeJobFilter.build("Manufacturing")
                .matchingCompanies(List.of("Adaptec Solutions"))
                .industries(List.of("Automation Machinery Manufacturing"))
        );


        filters.add(ExcludeJobFilter.build("MedicalSpecs")
                .titlePhrases(List.of("FHIR", "HL7"))
        );

        filters.add(ExcludeJobFilter.build("MedicalAssistant")
                .titlePhrases(List.of("Medical Assistant", "Physician Assistant"))
        );
        filters.add(ExcludeJobFilter.build("MedicalDiagnostics")
                .titlePhrases(List.of("CT/MRI", "Radiolog", "Radiograph", " MRI", "MRI ", "Ultrasound", " EKG", "EKG ", " EEG", "EEG ", "Endoscopy"
                        , "X-Ray", "Cat Scan", "Radiation", "PET/CT", "PET Scan", "PET Tech", "Rad tech", "Sonogra", "Medical Tech", "CT Tech"
                        , "Echocard"))
        );

        filters.add(ExcludeJobFilter.build("MedicalRecords")
                .titlePhrases(List.of("Medical Records"))
        );


        filters.add(ExcludeJobFilter.build("MedicalRequiresTraining")
                .titlePhrases(List.of("Surgeon", "Pathologist", "Clinician", "Phlebotomist", "Paramedic",
                        "Respiratory Therapist", "Anesthesi"))
        );

        filters.add(ExcludeJobFilter.build("MedicalScribe")
                .titlePhrases(List.of("Medical Scribe", "Practice Scribe"))
        );

        filters.add(ExcludeJobFilter.build("MegaBrand")
                .matchingCompanies(List.of("Amazon", "Facebook", "Pinterest", "Walmart", "McDonald's", "Sam's Club"))
        );
        filters.add(ExcludeJobFilter.build("MentalHealth")
                .titlePhrases(List.of("Psychologist", "Psychiatrist", "Mental Health Therapist", "Family Therapist", "Marriage Therapist"))
        );


        filters.add(ExcludeJobFilter.build("MessagingSoftwareDevelopment")
                .titlePhrases(List.of("Kafka"))
        );

        filters.add(ExcludeJobFilter.build(" MicrosoftStackProgramming").matchingCompanies(List.of("Homecare Homebase")).testForCompanyInDescription(true));

        filters.add(ExcludeJobFilter.build("MobileProgramming")
                .titlePhrases(List.of("Mobile", "Android", "iOS", "React Native"))
                .titleAndDescriptionPhrases(List.of("flutter"))
        );

        filters.add(ExcludeJobFilter.build("NetworkEngineer")
                .titlePhrases(List.of("Network Engineer", "Network Architect", "Storage Engineer"))
        );

        filters.add(ExcludeJobFilter.build("Nursing")
                .titlePhrases(List.of("Nurse Practitioner", "CNA", "Nursing", "C.N.A.", " Nurse", " RN ", "LPN", "Nurse "))
                .titleStartsWithPhrases(List.of("RN"))
        );

        filters.add(ExcludeJobFilter.build("OfficeAssistant")
                .titlePhrases(List.of("Receptionist", "Office Assistant", "Secretary",
                        "Administrative", "Office Manager", "Front Desk", "Office Coordinator", "Executive Assistant"))
                .descriptionPhrases(List.of("administrative support"))
        );

        filters.add(ExcludeJobFilter.build("OccupationalTherapist")
                .titlePhrases(List.of("Occupational Therap", "OTR/L"))
                .titleStartsWithPhrases(List.of("COTA", "OTR", "OT "))
        );

        filters.add(ExcludeJobFilter.build("OracleTech")
                .titlePhrases(List.of("Oracle", "EBS "))
                .descriptionPhrases(List.of(" CPQ ", "Oracle Apex", "Oracle EBS", "Oracle Cloud ERP"
                        , "Oracle ERP", " EPM", "Oracle fusion", "Hyperion"))
                .titleAndDescriptionPhrases(List.of("FCCS", "Oracle Developer", "Oracle Engineer"))
        );

        filters.add(ExcludeJobFilter.build("PatientRepresentative")
                .titlePhrases(List.of("Patient Service Rep", "Patient Access Services Representative", "Patient Account Representative"))
        );

        filters.add(ExcludeJobFilter.build("PharmacyAndPharmaceutical")
                .titlePhrases(List.of("Pharmacist", "Pharmacy"))
                .matchingCompanies(List.of("CVS Health"))
                .industries(List.of("Pharmaceutical Manufacturing"))
                .testForCompanyInDescription(true)
        );


        filters.add(ExcludeJobFilter.build("PhysicalEngineer")
                .titlePhrases(List.of("Service Engineer", "Project developer", "Civil Engineer",
                        "Design engineer", "Project Engineer", "field services", "field service",
                        "field engineer", " OEM ", "Autodesk", " CAD ", "MES ",
                        "OSP Engineer"))
                .safeTitlePhrases(List.of("Business Applications"))
        );

        /**
         * Exceptions
         * PT can be part time
         */
        filters.add(ExcludeJobFilter.build("PhysicalTherapist")
                .titlePhrases(List.of("PT Assistant", " PTA ", "PT Assistant "))
                .titleAndDescriptionPhrases(List.of("Physical Therapist", "Physical Therapy"))
                .safeDescriptionPhrases(List.of("Physical Therapists"))//assist team of physical therapists
                .matchingCompanies(List.of("Physical Therapy", "Rehabilitation"))
        );

        filters.add(ExcludeJobFilter.build("ProductManagement")
                .titlePhrases(List.of("Project Analyst", "Product Manager", "Program Management")));


        filters.add(ExcludeJobFilter.build("ProfessionalServicesEngineer")
                .titlePhrases(List.of("Professional Services Engineer")));

        filters.add(ExcludeJobFilter.build("Proposals")
                .titlePhrases(List.of("Proposal")));


        filters.add(ExcludeJobFilter.build("PublicRelations")
                .titlePhrases(List.of("Public Relations")));

        filters.add(ExcludeJobFilter.build("RealEstate")
                .matchingCompanies(List.of("Real Estate", "Pacaso",
                        "Aalto", "MRI Software", "RE/MAX"))
                .industries(List.of("Real Estate"))
                .descriptionPhrases(List.of("real estate", "real-estate"))
        );

        filters.add(ExcludeJobFilter.build("QualityAssurance")
                .titlePhrases(List.of("Test engineer", "SDET", "tester", "QA ", "Software Developer In Test", "Software Developer Engineer in Test",
                        "Verification Engineer", "Quality", "Software Engineer In Test", "Software Development Engineer in Test",
                        "Test", "validation"))
                .safeTitlePhrases(List.of("Test Center"))
        );

        filters.add(ExcludeJobFilter.build("RecreationAndArtsTherapist")
                .titlePhrases(List.of("Recreation Therapist", "Recreational Therapist", "Recreation Therapy", "Recreational Therapy", "Recreation Aide", "Therapeutic Recreation", "Activity Specialist", "Arts Therapist"))
        );

        filters.add(ExcludeJobFilter.build("Recruiter")
                .titlePhrases(List.of("Talent Acquisition", "Recruiter", "Recruitment", "Talent Sourc"))
                .safeTitlePhrases(List.of("Recruitment Incentive"))
        );

        filters.add(ExcludeJobFilter.build("Religious")
                .matchingCompanies(List.of("The Church of Jesus Christ of Latter-day Saints"))
                .testForCompanyInDescription(true)
        );

        filters.add(ExcludeJobFilter.build("Research").safeDescriptionPhrases(List.of("reading industry publications", "reading publications"))
                .descriptionPhrases(List.of(" patent", "AAAI", "IAAI",
                        "IJCAI", " HRI ", " ICAPS", "AAMAS", "ICRA", "IROS", "ICLR", "ICML",
                        "NeurIPs", "CORL", " ITSC", "journals ", "publications"))
                .titlePhrases(List.of("research", "R&D "))
                .titleAndDescriptionPhrases(List.of("researcher"))
                .safeDescriptionPhrases(List.of("awards from major publications", "researchers"))
        );

        filters.add(ExcludeJobFilter.build("Retail")
                .matchingCompanies(List.of("Abercrombie"))
                .industries(List.of("Retail", "Apparel, Accessories & Footwear"))
                .titleAndDescriptionPhrases(List.of("retail"))
                .safeDescriptionPhrases(List.of("with all major retail", "with all retail", "with most retail", "with most major retail"))
                .titlePhrases(List.of("Stocking", "Stocker", "Stock ", "Warehouse", "Cashier", "Stockroom Associate", "Store Associate"))
                .testForCompanyInDescription(true)
        );

        filters.add(ExcludeJobFilter.build("SalesAndMarketing")
                .titlePhrases(List.of("Sales", "Marketing", "SingleView", "Brand Assistant", "Account Executive", "Marketer"))
                .safeTitlePhrases(List.of("Salesforce"))
                .matchingCompanies(List.of("Velir", "Marketing"))
                .industries(List.of("Marketing Services"))
        );

        /*
        Educator can be in something like nature educator that does not necessarily mean someone with a degree or that this is in a school
        Attendance can be in a mental health program, etc
        This should only count education parts of a school and not say janatorial, IT, coaching needs of the school
         */
        filters.add(ExcludeJobFilter.build("SchoolAndTeaching")
                .titlePhrases(List.of("School", "Teacher", "Rater", "Instructor", "Tutor", "Trainer", "Teaching", "Admissions ", "Mentor", "Lunch Monitor"
                        , " Admissions", "Dissertation", "Adjunct", "Faculty", "Proctor", "Classroom", "Professor", "Education", "Bus Assistant"
                        , "Lecturer"))
                .safeTitlePhrases(List.of("Personal Trainer", "Athletic Trainer"))
                .matchingCompanies(List.of("Kaplan"))
        );


        filters.add(ExcludeJobFilter.build("ScientificProgrammer")
                .titlePhrases(List.of("Scientific Programmer"))
        );

        filters.add(ExcludeJobFilter.build("ScienceAndLaboratory")
                .titlePhrases(List.of("Geologist", "Lab Aide", "Laboratory Scientist", "Laboratory Tech",
                        "Lab Tech", "Physicist"))
                .matchingCompanies(List.of("Labcorp", "Quest Diagnostics"))
        );

        filters.add(ExcludeJobFilter.build("SearchSoftwareDevelopment")
                .descriptionPhrasesAndCount(List.of("Elasticsearch", "OpenSearch", "lucene", "Solr", "Splunk"), 1)
                .titlePhrases(List.of("Elasticsearch", "OpenSearch", "lucene", "Solr", "Splunk"))
        );

        filters.add(ExcludeJobFilter.build("SkilledTrades")
                .titleAndDescriptionPhrases(List.of("machinist", "Chemist"))
                .safeTitleAndDescriptionPhrases(List.of("chemistry"))
        );


        filters.add(ExcludeJobFilter.build("SocialWorker")
                .titlePhrases(List.of("Social Worker"))
        );

        filters.add(ExcludeJobFilter.build("SoftwareDevelopmentProjectManagement")
                .titlePhrases(List.of("Jira"))
        );

        filters.add(ExcludeJobFilter.build("SoftwareLifecycleManagement")
                .titlePhrases(List.of("Lifecycle Management", "Teamcenter", "PLM "))
        );

        filters.add(ExcludeJobFilter.build("SpecializedSoftwareEngineer")
                .titlePhrases(List.of("PLC Programmer"))
        );

        filters.add(ExcludeJobFilter.build("SpeechTherapist")
                .titlePhrases(List.of("Speech Therapist"))
        );

        filters.add(ExcludeJobFilter.build("SupplyChain")
                .matchingCompanies(List.of("Veriforce"))
                .titlePhrases(List.of("HighJump"))
                .descriptionPhrases(List.of("supply chain"))
                .safeDescriptionPhrases(List.of("across the pharmacy supply chain", "across the supply chain"))
        );

        filters.add(ExcludeJobFilter.build("SupportFocusedJob")
                .descriptionPhrases(List.of("Support tickets", "second level support"))
                .titlePhrases(List.of("Support"))
                .safeTitlePhrases(List.of("Direct Support Professional", "Decision Support", "Life Support", "Resident Support"))
        );

        filters.add(ExcludeJobFilter.build("SystemAdministrator")
                .titlePhrases(List.of("System Admin"))
        );


        filters.add(ExcludeJobFilter.build("Technician")
                .titlePhrases(List.of("Technician", " Tech "))
                .safeTitlePhrases(List.of("Environmental Engineering Technician"))
        );

        filters.add(ExcludeJobFilter.build("Telecom")
                .descriptionPhrases(List.of("Telecom", "OSS and BSS", "OSS/BSS"))
                .matchingCompanies(List.of("Crown Castle"))
                .industries(List.of("Telecommunications"))
        );


        filters.add(ExcludeJobFilter.build("Transportation")
                .matchingCompanies(List.of("Cambridge Systematics, Inc."))
                .industries(List.of("Truck Transportation", "Transportation"))
                .descriptionPhrases(List.of("deliveries"))
                .titlePhrases(List.of("Delivery", "Driver", "transport", "Courier"))
                .safeTitlePhrases(List.of("Postpartum", "Data Courier"))
        );


        filters.add(ExcludeJobFilter.build("Veterinary")
                .titleAndDescriptionPhrases(List.of("Veterinary", "Veterinarian"))
        );

        filters.add(ExcludeJobFilter.build("Vision")
                .titlePhrases(List.of("Ophthalmic", "Optometric", "Optometrist", "Optician", "Ophthalmologist", "Optical"))
        );

        for (ExcludeJobFilter filter : filters) {
            if (filter.exclude(job) != null) {
                job.getPositionCategories().add(filter.getName());
            }
        }

    }
}
