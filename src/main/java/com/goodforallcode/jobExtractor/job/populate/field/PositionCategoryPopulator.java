package com.goodforallcode.jobExtractor.job.populate.field;

import com.goodforallcode.jobExtractor.filters.ExcludeJobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.ArrayList;
import java.util.List;

public class PositionCategoryPopulator implements FieldPopulator {
    public void populateField(Job job, Preferences preferences) {
        List<ExcludeJobFilter> filters = new ArrayList<>();
        /**  Exceptions
         * Waitstaff
         */
        filters.add(ExcludeJobFilter.build("Advertising").industries(List.of("Advertising Services", "Advertising")).badCompanies(List.of("SocialVenu", "advertising"))
                .testForCompanyInDescription(true)
        );


        filters.add(ExcludeJobFilter.build("AddictionAndRecovery")
                .titleCompanyNameAndDescriptionPhrases(List.of("Recovery", "Addiction", "Substance Abuse"))
        );

        filters.add(ExcludeJobFilter.build("Automotive")
                .titlePhrases(List.of("Automotive", "MECHANIC"))
                .titleStartsWithPhrases(List.of("Car "))
                .badCompanies(List.of("Ford", "General Motors", "Toyota", "AutoZone", "Nissan", "Honda", "Meineke", "Jiffy Lube", "Firestone", "Goodyear"
                        , "Pep Boys", "Midas", "O'Reilly Auto Parts", "Advance Auto Parts", "Jeep", "Chrysler", "Dodge", "Fiat ", " Ram ", "Alfa Romeo", "Safelite"))
        );
        filters.add(ExcludeJobFilter.build("BasicSkillsAide")
                .titlePhrases(List.of("Direct Support Professional", "DSP ", "hygiene"))
        );

        filters.add(ExcludeJobFilter.build("BigData")
                .descriptionPhrases(List.of("pipelines", "pipeline",
                        "Extract, Transform, and Load"))
                .titleAndDescriptionPhrases(List.of("big data", "ETL ",
                        "Extract, Transform, and Load",
                        "spark", "hive ", "pig ", "warehousing", "hadoop"))
        );
        filters.add(ExcludeJobFilter.build("BlockChain")
                .titleAndDescriptionPhrases(List.of("Blockchain", "Crypto", " NFT ", " DeFi "))
                .badCompanies(List.of("CryptoRecruit", "Trust Machines",
                        "Kraken Digital Asset Exchange", "CoinTracker", "Coinbase"))
        );

        filters.add(ExcludeJobFilter.build("CleaningAndMaintenance")
                .titlePhrases(List.of("Environmental Svc", "Environmental services", "Housekeeping", "Housekeeper", "Cleaner",
                        "Porter", "Custodian", "Janitor", "Maintenance", "Maintenance", "Custodial"))
        );
        filters.add(ExcludeJobFilter.build("ClinicalDataEntry")
                .titlePhrases(List.of("Coder", "Coding", "Clinical Data Programmer", "EDC Programmer", "EDC Developer"))
        );

        filters.add(ExcludeJobFilter.build("CloudHeavy")
                .titlePhrases(List.of("AWS ", "Epic ", "OpenShift", "SailPoint", "Apigee",
                        "Informatica", "azure ", "gcp", "cloud", "Lambda", "Snowflake",
                        "Matillion", "Apigee", "FlashStack", "SFCC", "PCF", "Flashstack", "ECSA "))
                .titleAndDescriptionPhrases(List.of("SailPoint", "Apigee", "Sail point",
                        "Informatica",  "Lambda", "Snowflake",
                        "Matillion", "Apigee", "FlashStack", "SFCC", "PCF", "Flashstack", "ECSA "))
                .descriptionPhrasesAndCount(List.of("server-less", "serverless", " SNS","azure ", "gcp",
                        " SQS", " API Gateway", " EventBridge", " DynamoDB", " Redshift", " S3", "EC2", " AWS ",
                        "cloud-native", "cloud native", "Matillion", " aws ", " Epic Client ", " Epic ancillary ", " Epic system "
                        , " Epic environment", "Hyperspace", "Interconnect", "SailPoint", "Apigee", "cloud",
                        "Informatica", "azure ", "gcp", "cloud", "Lambda", "Snowflake",
                        "Matillion", "Apigee", "FlashStack", "SFCC", "PCF", "Flashstack", "ECSA "), 3)
                .badCompanies(List.of("Cloudflare", "Render", "Bowman Williams", "Concourse Labs"))
        );
        filters.add(ExcludeJobFilter.build("Commerce")
                .titlePhrases(List.of("OMS ", "Magento"))
                .badCompanies(List.of("Whatnot", "Nisum"))
                .industries(List.of("Retail", "E-commerce"))
                .titleAndDescriptionPhrases(List.of("Hybris", " OMS ", "Vericent", "Varicent", "Shopify"))
        );


        filters.add(ExcludeJobFilter.build("ComputerVision")
                .titleAndDescriptionPhrases(List.of("Computer Vision", "computer-vision"))
        );

        filters.add(ExcludeJobFilter.build("Construction")
                .industries(List.of("Construction"))
                .badCompanies(List.of("UpCodes", "Construction"))
                .titlePhrases(List.of("Construction", "Roofing", "Roofer", "Carpenter", "Electrician", "Plumber", "HVAC", "Masonry"))
        );

        filters.add(ExcludeJobFilter.build("CustomerService")
                .titlePhrases(List.of("Service Representative", "Ambassador", "Customer Service", "Customer Specialist", "Concierge"
                        , "Client Service Associate", "Customer Success", "Customer Support", "Customer Care", "Retention Specialist", "Membership Assistant"
                        , "Patient Representative", "Customer Assistant"
                        , "Call Center", "Membership Experience", "Member Experience", "Support Associate", "Guest Experience", "Customer Experience"
                        , "Account Liaison"))
        );

        filters.add(ExcludeJobFilter.build("CyberSecurity")
                .badCompanies(List.of("Zscaler", "Fortra", "Concourse Labs", "PropelAuth", "Trinity Cyber", "Quokka.io", "security"))
                .titlePhrases(List.of("Vulnerability engineer", "Detection", "Sentinel", "SIEM ", "Risk ", "Cyber Security", "CyberSecurity", "Cyber-Security"))
                .industries(List.of(" Computer and Network Security"))
        );

        filters.add(ExcludeJobFilter.build("Database")
                .titlePhrases(List.of("Database Developer", "Database Engineer", "SQL Developer", " SSIS "))
                .safeTitlePhrases(List.of(" and ", "/"))
        );

        filters.add(ExcludeJobFilter.build("DayHabilitation")
                .titleAndDescriptionPhrases(List.of("Day Habilitation"))
        );

        /**
         * Defense can be used to describe defense of a paper
         * military status
         * military service, veteran status, or any other category protected under
         * client-oriented defense
         * readdmissions
         */
        filters.add(ExcludeJobFilter.build("Defense").industries(List.of("Defense"))
                .descriptionPhrases(List.of("DLA", "DOD",
                        "national security", " missions", "DHS", "army", "navy", "air force",
                        "Lockheed Martin", "Marine Corps", "missile"))
                .excludeAttributes(List.of("defense"))
                .badCompanies(List.of("Northrop Grumman", "Raytheon", "General Dynamics",
                        "Parsons Corporation", "SAIC", "Leidos", "RVCM (RevaComm)", "BAE Systems",
                        "ECS", "Innovative Defense Technologies (IDT)"))
        );

        filters.add(ExcludeJobFilter.build("Dental")
                .titlePhrases(List.of("Dental", "Dentist", "Orthodontist", "Hygienist"))
                .badCompanies(List.of("Dental"))
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
                    .titleAndDescriptionPhrases(List.of("Tibco"))
            );
        }

        filters.add(ExcludeJobFilter.build("DietaryAide")
                .titlePhrases(List.of("Dietary Aide"))
        );

        filters.add(ExcludeJobFilter.build("Elderly")
                .titlePhrases(List.of("Memory Care", "Senior Living", "Senior Care", "Elderly", "Geriatric"))
                .badCompanies(List.of("Senior Living", "Gurwin Healthcare System", "The Bristal Assisted Living", "Atria Senior", "Senior Life"))
                .titleAndDescriptionPhrases(List.of("Assisted Living"))
        );

        filters.add(ExcludeJobFilter.build("Entertainment")
                .badCompanies(List.of("NBCUniversal", "Entertainment", "Cinema"))
                .badCompaniesEquals(List.of("Regal"))
                .industries(List.of("Entertainment Providers"))
        );

        /**
         * Exceptions
         * financial:   financial stability
         * Banking: Applicants will never be asked to provide personal identification information (e.g., SSN, Driver’s License, Passport) or financial information (e.g., Banking Information)
         * an independent brokerage that works to place homeless individuals and families into permanent housing
         */
        filters.add(ExcludeJobFilter.build("Finance")
                .badCompanies(List.of("Affirm", "Citibank", "Kraken Digital Asset Exchange"
                        , "Jack Henry", "Equitable", "American Express", "U.S. Bank", "Modulus", "Clerkie",
                        "Juniper Square", "Peach", "T. Rowe Price", "Studio Management", "Mortgage", "Credit Union", " Bank", "Wells Fargo"))
                .industries(List.of("Venture Capital and Private Equity Principals",
                        "Financial Services", "Finance", "Investment Banking", "Investment Banking & Asset Management"))
                .titlePhrases(List.of("Simcorp", "Accounts", "Billing", "Loan Originator", "Wealth", "Financial", "Investment",
                        "Banker", "Finance", "Accounting", "Accountant", "Branch Ambassador", "Underwriting", "Loan "
                        , " tax", "tax ", "Bookkeeper", "Collections", "broker", "Revenue", "Billing", "Biller"))
                .descriptionPhrases(List.of("FinTech", "asset manager","financial services",
                        "hedge fund", "trading", "Brokers", " investment management"))
        );

        filters.add(ExcludeJobFilter.build("FoodIndustry")
                .titlePhrases(List.of("Dining", "Busser", "To-Go ", "Host",
                        "Dishwasher", "Food Service", "Food Runner", "Barista", "Waiter", "Waitress",
                        "Fish Market", "Cook", "Server Assistant", "Bartender", "Prepared Foods", "Deli ", "Culinary", "Cafeteria", "Meat", "Restaurant", "Banquet", " Server"
                        , "Kitchen"))
                .badCompanies(List.of("Whole Foods Market", "Outback Steakhouse", "Red Lobster", "Buffalo Wild Wings", "Chick-fil-A", "Denny's",
                        "Cracker Barrel", "Taco Bell", "Pizza Hut", "Waffle House", "IHOP", "Hostess", "Stop & Shop",
                        "Olive Garden", "Applebee's", "Ruby Tuesday", "Checkers & Rally’s Drive-In Restaurants", "Wegmans",
                        "Red Robin", "Cheesecake Factory", "Panda Express", "Chipotle", "Panera", "KFC"
                        , "Baskin Robbins", "Pizza", "Krispy Kreme", "Waffle House", "Denny's", "IHOP",
                        "Dunkin'", "Dunkin "))
        );

        filters.add(ExcludeJobFilter.build("ForwardDeployedEngineer")
                .titlePhrases(List.of("Forward Deployed Engineer"))
        );

        filters.add(ExcludeJobFilter.build("FossilFuels")
                .badCompanies(List.of("Pride International"))
                .industries(List.of("Oil and Gas"))
                .safeTitlePhrases(List.of("renewable", "solar", "wind",
                        "nuclear",
                        "mills", "windmill", "geothermal", "geo", "offgrid", "turbine", "sustainable"))
                .testForCompanyInDescription(true)
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
                .badCompanies(List.of("Games", "Unity Technologies", "Electronic Arts", "Activision", "Blizzard",
                        "Zynga", "Ubisoft", "Take-Two Interactive", "Valve Corporation",
                        "Bethesda Softworks", "Square Enix", "Bandai", "Second Dinner", "Sleeper"))
        );

        filters.add(ExcludeJobFilter.build("Gambling")
                .badCompanies(List.of("betting", "casino"))
                .testForCompanyInDescription(true)
        );

        filters.add(ExcludeJobFilter.build("HomeCare")
                .titlePhrases(List.of("Home Health Aide", "HHA", "Nanny", "Companion"))
                .descriptionPhrases(List.of("in-home"))
                .badCompanies(List.of("Home Companions", "Home Care"))
        );

        filters.add(ExcludeJobFilter.build("Hospitals")
                .badCompanies(List.of("Hospital"))
                .titleAndDescriptionPhrases(List.of("Operating Room", "Emergency Room"))
        );

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

        filters.add(ExcludeJobFilter.build("Insurance")
                .badCompanies(List.of("Transamerica", "Travelers",
                        "State Farm", "GEICO", "Allstate", "Coalition, Inc.", "One80 Intermediaries", "insurance"))
                .industries(List.of("Insurance"))
                .titlePhrases(List.of("Insurance", "Duck Creek"))
                .testForCompanyInDescription(false)//can't look for insurance in description as it could be describing a benefit
        );

        filters.add(ExcludeJobFilter.build("InternetTelevisionAndMobile")
                .badCompanies(List.of("QCell"))
                .testForCompanyInDescription(true)
        );

        filters.add(ExcludeJobFilter.build("InventoryManagement")
                .titlePhrases(List.of("RF Smart", "RF-Smart", "Stock Associate", "Inventory"))
        );

        filters.add(ExcludeJobFilter.build("ITRole")
                .titlePhrases(List.of("IT Engineer"))
        );

        filters.add(ExcludeJobFilter.build("Law")
                .titlePhrases(List.of("Counsel ", "Law ", "Legal", " law"))//we actively work with our legal and security teams
                .titleAndDescriptionPhrases(List.of("Attorney", "Lawyer", "Paralegal", "Litigation"))
        );

        filters.add(ExcludeJobFilter.build("Manufacturing")
                .badCompanies(List.of("Adaptec Solutions"))
                .industries(List.of("Automation Machinery Manufacturing"))
        );


        filters.add(ExcludeJobFilter.build("MedicalSpecs")
                .titlePhrases(List.of("FHIR", "HL7"))
        );

        filters.add(ExcludeJobFilter.build("MedicalAssistant")
                .titlePhrases(List.of("Medical Assistant"))
        );
        filters.add(ExcludeJobFilter.build("MedicalDiagnostics")
                .titlePhrases(List.of("CT/MRI", "Radiolog", "Radiograph", " MRI", "MRI ", "Ultrasound", " EKG", "EKG ", " EEG", "EEG ", "Endoscopy"
                        , "X-Ray", "Cat Scan", "Radiation", "PET/CT", "PET Scan", "PET Tech", "Rad tech", "Sonogra", "Medical Tech", "CT Tech"))
        );

        filters.add(ExcludeJobFilter.build("MedicalRequiresTraining")
                .titlePhrases(List.of("Physician", "Surgeon", "Pathologist", "Orthopedic", "Psychiatrist", "Clinician", "Phlebotomist", "Pediatrician", "Paramedic",
                        "Cardiac", "Cardiol", "Respiratory Therapist"))
        );

        filters.add(ExcludeJobFilter.build("MegaBrand")
                .badCompanies(List.of("Amazon", "Facebook", "Pinterest", "Walmart", "McDonald's", "Sam's Club"))
        );

        filters.add(ExcludeJobFilter.build("Nursing")
                .titlePhrases(List.of("Nurse Practitioner", "CNA", "Nursing", "C.N.A.", " Nurse", " RN ", "LPN", "Nurse "))
        );
        filters.add(ExcludeJobFilter.build("OfficeAssistant")
                .titlePhrases(List.of("Receptionist", "Office Assistant", "Secretary", "Administrative", "Office Manager", "Front Desk", "Office Coordinator"))
        );

        filters.add(ExcludeJobFilter.build("OccupationalTherapist")
                .titlePhrases(List.of("Occupational Therapist"))
        );

        filters.add(ExcludeJobFilter.build("Patient Service Representative")
                .titlePhrases(List.of("Patient Service Rep"))
        );

        filters.add(ExcludeJobFilter.build("PharmacyAndPharmaceutical")
                .titlePhrases(List.of("Pharmacist", "Pharmacy"))
                .badCompanies(List.of("CVS Health"))
                .industries(List.of("Pharmaceutical Manufacturing"))
                .testForCompanyInDescription(true)
        );

        filters.add(ExcludeJobFilter.build("PhysicalEngineer")
                .titlePhrases(List.of("Service Engineer", "Project developer", "Civil Engineer",
                        "Design engineer", "Project Engineer", "field services", "field service",
                        "field engineer", "Applications Engineer", " OEM ", "Autodesk", " CAD ", "MES ",
                        "OSP Engineer"))
                .safeTitlePhrases(List.of("Business Applications"))
        );

        filters.add(ExcludeJobFilter.build("PhysicalTherapist")
                .titlePhrases(List.of("Physical Therapist", "Physical Therapy", "PT Assistant", " PTA ", "PT Assistant "))
        );


        filters.add(ExcludeJobFilter.build("ProfessionalServicesEngineer")
                .titlePhrases(List.of("Professional Services Engineer")));
        filters.add(ExcludeJobFilter.build("RealEstate")
                .badCompanies(List.of("Anywhere Real Estate Inc.", "Pacaso",
                        "Aalto", "MRI Software", "RE/MAX"))
                .industries(List.of("Real Estate"))
                .descriptionPhrases(List.of("real estate", "real-estate"))
        );

        filters.add(ExcludeJobFilter.build("RecreationAndArtsTherapist")
                .titlePhrases(List.of("Recreation Therapist", "Recreational Therapist", "Recreation Therapy", "Recreational Therapy", "Recreation Aide", "Therapeutic Recreation", "Activity Specialist", "Arts Therapist"))
        );

        filters.add(ExcludeJobFilter.build("Recruiter")
                .titlePhrases(List.of("Talent Acquisition", "Recruiter", "Recruitment", "Talent Sourc"))
        );

        filters.add(ExcludeJobFilter.build("Research").safeDescriptionPhrases(List.of("reading industry publications", "reading publications"))
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
                .titlePhrases(List.of("Stocking", "Stocker", "Stock ", "Warehouse", "Cashier", "Stockroom Associate", "Store Associate"))
                .testForCompanyInDescription(true)
        );

        filters.add(ExcludeJobFilter.build("SalesAndMarketing")
                .titlePhrases(List.of("Solution engineer", "Solutions engineer"
                        , "Solutions Developer", "Solution Developer", "Sales", "Marketing", "SingleView", "Brand Assistant", "Account Executive", "Marketer"))
                .badCompanies(List.of("Velir", "Marketing"))
                .industries(List.of("Marketing Services"))
        );

        /*
        Educator can be in something like nature educator that does not necessarily mean someone with a degree or that this is in a school
        Attendance can be in a mental health program, etc
        This should only count education parts of a school and not say janatorial, IT, coaching needs of the school
         */
        filters.add(ExcludeJobFilter.build("SchoolAndTeaching")
                .titlePhrases(List.of("School", "Teacher", "Rater", "Instructor", "Tutor", "Trainer", "Teaching", "Admissions "
                        , " Admissions", "Dissertation", "Adjunct", "Faculty", "Proctor", "Classroom", "Professor", "Education", "Bus Assistant"))
                .badCompanies(List.of("Kaplan"))
        );


        filters.add(ExcludeJobFilter.build("ScientificProgrammer")
                .titlePhrases(List.of("Scientific Programmer"))
        );

        filters.add(ExcludeJobFilter.build("ScienceAndLaboratory")
                .titlePhrases(List.of("Geologist", "Lab Aide", "Laboratory Scientist", "Laboratory Technologist", "Physicist"))
                .badCompanies(List.of("Labcorp", "Quest Diagnostics"))
        );

        filters.add(ExcludeJobFilter.build("SkilledTrades")
                .titlePhrases(List.of("Shop Technician", "Field Tech"))
                .titleAndDescriptionPhrases(List.of("machinist"))
        );

        filters.add(ExcludeJobFilter.build("SkinHairAndSpa")
                .titlePhrases(List.of("Hairstylist", "Froster", "Stylist", "Dermatologist", "Dermatology", "Cosmetic", "Aesthetic", "Plastic Surgeon", "Plastic Surgery", "Esthetician", "Spa ", "Massage"))
                .badCompanies(List.of(" spa"))
        );

        filters.add(ExcludeJobFilter.build("SpecializedSoftwareEngineer")
                .titlePhrases(List.of("PLC Programmer"))
        );

        filters.add(ExcludeJobFilter.build("SpeechTherapist")
                .titlePhrases(List.of("Speech Therapist"))
        );

        filters.add(ExcludeJobFilter.build("Technician")
                .titlePhrases(List.of("Technician"))
        );

        filters.add(ExcludeJobFilter.build("Telecom")
                .descriptionPhrases(List.of("Telecom", "OSS and BSS", "OSS/BSS"))
                .badCompanies(List.of("Crown Castle"))
                .industries(List.of("Telecommunications"))
        );


        filters.add(ExcludeJobFilter.build("Transportation")
                .badCompanies(List.of("Cambridge Systematics, Inc."))
                .industries(List.of("Truck Transportation", "Transportation"))
                .titlePhrases(List.of("Delivery", "Driver", "transport", "Courier"))
        );
        filters.add(ExcludeJobFilter.build("Veterinary")
                .titleAndDescriptionPhrases(List.of("Veterinary", "Veterinarian"))
        );

        filters.add(ExcludeJobFilter.build("Vision")
                .titlePhrases(List.of("Ophthalmic", "Optometric", "Optometrist", "Optician", "Ophthalmologist"))
        );

        for (ExcludeJobFilter filter : filters) {
            if (filter.exclude(job) != null) {
                job.getPositionCategories().add(filter.getName());
            }
        }

    }
}
