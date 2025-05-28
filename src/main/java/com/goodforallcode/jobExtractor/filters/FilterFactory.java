package com.goodforallcode.jobExtractor.filters;

import com.goodforallcode.jobExtractor.filters.custom.*;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.ArrayList;
import java.util.List;

public class FilterFactory {

    public static List<JobFilter> getAlwaysExcludeCustomFilters(Preferences preferences) {
        List<JobFilter> filters = new ArrayList<>();
        filters.add(new FortuneRankFilter());
        filters.add(new LocalFilter());
        filters.add(new PositionCategoryFilter());
        filters.add(new ProgrammingFrameworkFilter());
        filters.add(new PromotedFilter());
        filters.add(new SalaryFilter());
        filters.add(new SeniorityFilter());
        filters.add(new SkillsFilter());
        return filters;
    }

    public static List<ExcludeJobFilter> getExcludeFilters(Preferences preferences) {
        List<ExcludeJobFilter> filters = new ArrayList<>();




        filters.add(ExcludeJobFilter.build("CaseManagement")
                .titleAndDescriptionPhrases(List.of("Entellitrak", "Documentum")));




        filters.add(ExcludeJobFilter.build("ContentManagement")
                .titlePhrases(List.of("Content Management", "CMS ", "Onbase", "CMS(", "CMS "))
                .descriptionPhrasesAndCount(List.of("AEM ", "Adobe Experience Manager", "Drupal", "Alfresco", " onbase",
                        "Sitecore", "Tridion"), 2)
                .caseSensitiveDescriptionPhrases(List.of("Brightspot"))
        );

        filters.add(ExcludeJobFilter.build("DataExchange")
                .titleAndDescriptionPhrases(List.of(" EDI "))
        );



        filters.add(ExcludeJobFilter.build("EnterpriseApplications")
                .descriptionPhrases(List.of("Enterprise Application", "Web Logic", "JBOSS", "WebSphere"))
        );


        filters.add(ExcludeJobFilter.build("ERP")
                .titlePhrases(List.of("Enterprise Application", "Web Logic", "JBOSS", "WebSphere"))
                .descriptionPhrasesAndCount(List.of("Workday", " ERP ", "NetSuite", "FinancialForce", "Certinia",
                        "Infor ", "Lawson", "Kinetic", "Syteline", "Epicor "), 2)
        );


        filters.add(ExcludeJobFilter.build("ExternalContractor")
                .excludeAttributes(List.of("softwareEngineerExternalContractors"))
        );

        if (preferences.isSoftwareSearch()) {
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
                    .descriptionPhrases(List.of("latency","high-volume", "high throughput"))
            );
        }



        filters.add(ExcludeJobFilter.build("Messaging")
                .titlePhrases(List.of("Kafka"))
        );

        if (preferences.getMaxEmployees() != null) {
            filters.add(ExcludeJobFilter.build("NumEmployees").maxAttribute("MinimumNumEmployees", (float) preferences.getMaxEmployees()));
        }

        filters.add(ExcludeJobFilter.build("Performance")
                .descriptionPhrasesAndCount(List.of("profiling", "optimizing", "Tuning", "Onestream", " cpm "),1)
        );


        filters.add(ExcludeJobFilter.build("SoftwareDevelopmentProjectManagement")
                .titlePhrases(List.of("Jira"))
        );

        if (preferences.isSkipTooManyApplicants()) {
            filters.add(ExcludeJobFilter.build("TooManyApplicants").maxAttribute("NumApplicants", (float) preferences.getMaxApplicants()));
        }

        filters.add(ExcludeJobFilter.build("Unix")
                .titlePhrases(List.of("Unix", "OpenShift", "RHEL", "Linux"))
        );


        return filters;
    }

    public static List<ExcludeJobFilter> getAlwaysExcludeFilters(Preferences preferences) {
        List<ExcludeJobFilter> filters = new ArrayList<>();


        filters.add(ExcludeJobFilter.build("AcceptingApplications").excludeIfFalseJobAttribute("AcceptingApplications"));

        filters.add(ExcludeJobFilter.build("Acquisitions")
                .excludeAttributes(List.of("acquisitions"))
                .badCompanies(List.of("BeyondTrust","PointClickCare","MeridianLink"))
        );

        if(preferences.isSoftwareSearch()) {
            filters.add(ExcludeJobFilter.build("AgileRole")
                    .titlePhrases(List.of("RTE Engineer", "Release Train Engineer", "Product Owner"))
            );
        }

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

        if(preferences.isSoftwareSearch()) {
            filters.add(ExcludeJobFilter.build("AI").excludeIfTrueJobAttribute("AI"));
        }

        filters.add(ExcludeJobFilter.build("Applied").excludeIfTrueJobAttribute("Applied"));


        if (preferences.isSoftwareSearch()) {
            filters.add(ExcludeJobFilter.build("Automation")
                    .titlePhrases(List.of("Automation", "Rockwell Portfolio", "BAW"))
            );

            filters.add(ExcludeJobFilter.build("Basic").descriptionPhrases(List.of("JBasic", "T24", "JBASE", "PICK", "jQL", "Visual basic")));


        }

        filters.add(ExcludeJobFilter.build("BadManagement").badCompanies(List.of("Research Innovations Incorporated",
                "IT Labs", "Hansen Technologies", "LaunchDarkly", "TIDAL", "DXC Technology", "Oak Ridge National Laboratory", "EPLAN",
                "Envision Horizons","NinjaOne","Tire Rack"))
        );


        filters.add(ExcludeJobFilter.build("Bilingual")
                .titlePhrases(List.of("Spanish", "German", "French", "Italian", "Portuguese", "Russian", "Japanese",
                        "Chinese", "Korean", "Arabic","Bi-lingual",
                        "Mandarin", "Cantonese", "Bilingual", "Multilingual"))
        );

        if (preferences.isSoftwareSearch()) {
            /*
            BCBA
             */
            filters.add(ExcludeJobFilter.build("BusinessAnalyst")
                    .titlePhrases(List.of("Business Analyst", " BA "))
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
                .descriptionPhrases(List.of("must have an active Public Trust Clearance", "Secret clearance"))
                .safeDescriptionPhrases(List.of("the secret"))
                .titleAndDescriptionPhrases(List.of("top secret", "secret ", "TS/SCI ", " TS/SCI"))
        );




        if (testForStateExclusion("CT", preferences)) {
            filters.add(ExcludeJobFilter.build("Connecticut")
                    .excludeIfJobAttributeAndValue("state", "CT")
                    .runOnlyIfNotFullyRemote(true)
            );
        }

        if (preferences.isExcludeConsultant()) {
            filters.add(ExcludeJobFilter.build("Consultant").excludeIfTrueJobAttribute("consultant"));
        }

        if(preferences.isSoftwareSearch()) {
            filters.add(ExcludeJobFilter.build("ContentManagement")
                    .titlePhrases(List.of("Content Management", "CMS ", "Onbase", "CMS(", "CMS "))
                    .descriptionPhrasesAndCount(List.of("AEM ", "Adobe Experience Manager", "Drupal", "Alfresco", " onbase",
                            "Sitecore", "Tridion"), 2)
                    .caseSensitiveDescriptionPhrases(List.of("Brightspot"))
            );
        }

        if (preferences.isExcludeContractJobs()) {
             /*
    exceptions:
    positions are subject to background screening as required by law or contract
     */

            filters.add(ExcludeJobFilter.build("Contract")
                    .excludeIfTrueJobAttribute("contract")
                    .excludeAttributes(List.of("contractor"))
            );
        }

        filters.add(ExcludeJobFilter.build("CoordinationAndManagement")
                .titlePhrases(List.of("Manager", "Systems engineer", "System engineer", "System Analyst"
                        , "Systems Analyst", "developer advocate", "developer evangelist", "Coordinator", "Management"))
        );


        filters.add(ExcludeJobFilter.build("Credentialed")
                .titlePhrases(List.of("LCSW", "Certified", "Licensed", "Registered", "LPN", "CRN", "PCA", "-RN ", " RN ", "BCBA", "LMSW","/PRN"))
                .safeTitlePhrases(List.of(" train"))
        );



        if(!preferences.isSoftwareSearch()) {
            filters.add(ExcludeJobFilter.build("Dangerous")
                    .titlePhrases(List.of("Inventory Control", "Hazmat", "Security", "Surveillance", "Crisis", "Emergency", "Police Officer", "Private Investigator"
                            , "Firefighter", "Officer"))
            );
        }


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



        filters.add(ExcludeJobFilter.build("Dishonest")
                .badCompanies(List.of("IT Minds LLC", "Bitsoft International, Inc."
                        , "Envision Horizons", "Diaconia"))
                .testForCompanyInDescription(true)
        );





        filters.add(ExcludeJobFilter.build("EnrollmentRequired")
                .badCompanies(List.of("IT Pros"))
                .testForCompanyInDescription(true)
        );



        if (testForStateExclusion("FL", preferences)) {
            filters.add(ExcludeJobFilter.build("Florida")
                    .excludeIfJobAttributeAndValue("state", "FL")
                    .runOnlyIfNotFullyRemote(true)
            );
        }


        filters.add(ExcludeJobFilter.build("ForeignLocatedCompany")
                .badCompanies(List.of("Redcare Pharmacy"))
                .testForCompanyInDescription(true)
        );


        filters.add(ExcludeJobFilter.build("Freelance")
                .badCompanies(List.of("CleverTech"))
                .titleAndDescriptionPhrases(List.of("Freelance"))
        );


        filters.add(ExcludeJobFilter.build("FrontEnd")
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


        if (preferences.isExcludeFullStack()) {
            filters.add(ExcludeJobFilter.build("Fullstack")
                    .titlePhrases(List.of("Facets"))
                    .titleAndDescriptionPhrases(List.of("fullstack", "full stack", "full-stack"))
            );
        }



        filters.add(ExcludeJobFilter.build("Geospatial")
                .titlePhrases(List.of("GIS "))
                .titleAndDescriptionPhrases(List.of("geospatial ", " GIS "))
        );

        if (preferences.isSoftwareSearch()) {
            /**
             * Exceptions
             * Embedded: can't be in description as it could be embedded in our culture
             */
            filters.add(ExcludeJobFilter.build("Hardware")
                    .titlePhrases(List.of("Embedded", "Centura", " IoT", "circuit"))
                    .titleAndDescriptionPhrases(List.of("Systems Programmer", "System Programmer"
                            , "Firmware", "AR/VR headset", "drivers", "sensor", " IoT ", "semiconductor",
                            "VoIP", "GPU "))
                    .badCompanies(List.of("Trinnex", "NVIDIA"))
            );
        }

        filters.add(ExcludeJobFilter.build("HighSchoolTypeJob")
                .titlePhrases(List.of(
                        "Mailroom"," Camp ",
                        "Clerk", "Laborer", "Attendant",
                        "Groomer",  "Valet",
                         "Manufacturing", "Assembler"
                        ,  "Lifeguard", "Installer", "Installation"
                        , "Operator", "Dock Worker", "Data Entry"))
                .badCompanies(List.of("The Container Store","Barnes & Noble", "Hobby Lobby","7-Eleven", "Dollar Tree", "Dollar General", "Family Dollar",
                        "Federal Express", "FedEx", "Fed Ex", "Fedex", "Fed Ex"))
        );

        filters.add(ExcludeJobFilter.build("HumanAndCustomerManagement")
                .titlePhrases(List.of("Genesys", "CRM", "Salesforce", "Dynamics"
                        , "d365", "Exstream", "Power Platform", "HRMS", "Peoplesoft",
                        "HRIS", "Kitewheel", " HCM ", "Vlocity", "Medallia", "SFCC", "HR ", "Human Resources"))
                .badCompanies(List.of("Ceridian"))
        );



        if(preferences.isSoftwareSearch()) {
        filters.add(ExcludeJobFilter.build("Integration")
                .titleAndDescriptionPhrases(List.of("Mulesoft", "Boomi ", "Implementation Engineer",
                         "Integrations ", "System Integrator"))
                .titlePhrases(List.of("Integration "))
        );
        }




        filters.add(ExcludeJobFilter.build("Javascript").descriptionPhrases(List.of("advanced JavaScript")));
        filters.add(ExcludeJobFilter.build("JEE").descriptionPhrasesAndCount(List.of("JSP", "JSF", "Struts", "Tomcat", "Websphere", "JBoss",
                "J2EE", " JEE ", "/JEE"), 2));

        filters.add(ExcludeJobFilter.build("JobSecurity")
                .badCompanies(List.of("Allstate", "New Relic", "Breezeline", "Slack", "Wordly",
                        "Crossover", "Invitae", "Omnicell", "Komodo Health", "Rocket Software", "Zinnia", "CSG",
                        "ODP Corporation","Dataminr","Help Scout",
                        "NTT DATA Services", "Cruise", "VMware", "Intelerad Medical Systems",
                        "Air Apps", "CivicPlus", "Vertisystem Inc.", "Kyruus", "Atlassian", "Zwift"))
                .includeAttribute("jobSecurity")
                .excludeAttributes(List.of("recentLayoffs"))
        );

        filters.add(ExcludeJobFilter.build(" JobTooOld").maxAttribute("jobAgeInDays", (float) preferences.getMaxJobAgeInDays()));


        if(preferences.getMaxLevel()!=null) {
            filters.add(ExcludeJobFilter.build("Level")
                    .maxAttribute("level", (float) preferences.getMaxLevel())
            );
        }

        filters.add(ExcludeJobFilter.build("LifecycleManagement")
                .titlePhrases(List.of("Lifecycle Management", "Teamcenter", "PLM "))
        );

        filters.add(ExcludeJobFilter.build("LoAndNoCode")
                .titleAndDescriptionPhrases(List.of("Pega ", "Servicenow", "Low Code", "Low-Code",
                        "lansa ", "Quickbase", "Entellitrek", "Entellitrak", "Powerapps", "Low- Code",
                        "No-Code", "Unqork"))
        );


        if (preferences.isSoftwareSearch()) {
            filters.add(ExcludeJobFilter.build("Mainframe")
                    .titleAndDescriptionPhrases(List.of("Mainframe", "AS400", "RPG", "z/OS", "Adabas","COBOL"))
                    .badCompanies(List.of("Rocket Software"))
            );
        }






        filters.add(ExcludeJobFilter.build(" MicrosoftStack").badCompanies(List.of("Homecare Homebase")).testForCompanyInDescription(true));

        if (preferences.isSoftwareSearch()) {
            filters.add(ExcludeJobFilter.build("MobileProgramming")
                    .titlePhrases(List.of("Mobile", "Android", "iOS", "React Native"))
                    .titleAndDescriptionPhrases(List.of("flutter"))
            );
        }


        filters.add(ExcludeJobFilter.build("MST")
                .descriptionPhrases(List.of(" MST ", " MDT ", "overlap with 9am-5pm MST"))
        );

        if (testForStateExclusion("NJ", preferences)) {
            filters.add(ExcludeJobFilter.build("New Jersey")
                    .excludeIfJobAttributeAndValue("state", "NJ")
                    .runOnlyIfNotFullyRemote(true)
            );
        }

        filters.add(ExcludeJobFilter.build("NightShift")
                .descriptionPhrases(List.of("nights", "pm to close","-1am", "-2am", "-3am", "-4am", "-5am", "-6am", "-7am", "-8am"))
                .titlePhrases(List.of("Evening", " night ", " nights"))
                .titleStartsWithPhrases(List.of("night "))
                .titleAndDescriptionPhrases(List.of("- 7A", "- 7A", "- 7:", "- 7:", "- 8A", "- 8A", "- 8:", "- 8:", "Over Night", "-11p",
                        "2nd shift", "3rd shift", "second shift", "third shift", "night shift", "evening shift", "graveyard shift","overnight"))
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
            filters.add(ExcludeJobFilter.build("OnCall").excludeIfTrueJobAttribute("onCall"));
        }

        filters.add(ExcludeJobFilter.build("OpenCall")
                .titlePhrases(List.of("Engineering Prospects", "open call", "Expression of Interest"))
        );

        filters.add(ExcludeJobFilter.build("OperatingSystemCompany")
                .badCompanies(List.of("Canonical"))
        );

        if (preferences.isSoftwareSearch()) {
            filters.add(ExcludeJobFilter.build("OracleTech")
                    .titlePhrases(List.of("Oracle", "EBS "))
                    .descriptionPhrases(List.of(" CPQ ", "Oracle Apex", "Oracle EBS", "Oracle Cloud ERP"
                            , "Oracle ERP", " EPM", "Oracle fusion", "Hyperion"))
                    .titleAndDescriptionPhrases(List.of("FCCS", "Oracle Developer", "Oracle Engineer"))
            );
        }

        filters.add(ExcludeJobFilter.build("OutsourcingAndOffshore")
                .badCompanies(List.of("Rockwell Automation", "Equinix", "Banner Health", "Ritchie Bros",
                        "Blue Cross NC", "Ascension", "Transamerica", "Conduent", "Zinnia", "LiveRamp",
                        "Intelerad Medical Systems"))
                .testForCompanyInDescription(true)
                .excludeAttributes(List.of("offshores"))
                        .titleAndDescriptionPhrases(List.of("Hyderabad", "India"))
                .descriptionPhrases(List.of("IT services and outsourcing company", "with offshore"))
        );


        if (testForStateExclusion("PA", preferences)) {
            filters.add(ExcludeJobFilter.build("Pennsylvania")
                    .excludeIfJobAttributeAndValue("state", "PA")
                    .runOnlyIfNotFullyRemote(true)
            );
        }

        filters.add(ExcludeJobFilter.build("PharmacyAndPharmaceutical")
                .titlePhrases(List.of("Pharmacist", "Pharmacy"))
                .badCompanies(List.of("CVS Health"))
                .industries(List.of("Pharmaceutical Manufacturing"))
                .testForCompanyInDescription(true)
        );

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


        filters.add(ExcludeJobFilter.build("RegularlyWarringCountryCompany")
                .badCompanies(List.of("Київстар"))
                .testForCompanyInDescription(true)
        );

        filters.add(ExcludeJobFilter.build("Religious")
                .badCompanies(List.of("The Church of Jesus Christ of Latter-day Saints"))
                .testForCompanyInDescription(true)
        );

        if (preferences.isRemoteOnly()) {
            /**
             *  Exceptions
             *  location: compensation is based on various factors including but not limited to job location,
             *  hybrid: can refer to a benefit of hybrid
             *  relocation:     can refer to relocation bonus on a possibly remote job
             */
            filters.add(ExcludeJobFilter.build("Remote")
                    .safeTitleAndDescriptionPhrases(List.of("partial remote", "temporary remote",
                            "Remote 20 hours per week", "Mostly Remote", "DAYS/WK ON SITE", "days onsite",
                            "Must be able to relocate", "one day of remote work", "Partial WFH",
                            "Remote till pandemic", "Remote til pandemic", "able to travel", "Future onsite work is required",
                            "week onsite", "a hybrid position", "(Hybrid)", "(Hybrid role)", "in office days per ",
                            "(Onsite / Hybrid)", "is not remote,", "is not remote ", "is not remote.", "week onsite", "onsite in",
                            "is based in "))
                    .safeTitlePhrases(List.of("- Hybrid", "-Hybrid", "- Onsite", "-Onsite",
                            ": Hybrid", ":Hybrid", ": Onsite", ":Onsite", "- Onsite/Hybrid", "-Onsite/Hybrid", ":Onsite/Hybrid",
                            ": Onsite/Hybrid", "- ONSITE HYBRID", "Hybrid:", "Hybrid-", "Onsite:", "Onsite-"))
                    .titleAndDescriptionPhrases(List.of("100% remote", "Open for remote", "remote or hybrid", "WFH", "Work From Home"
                            , "remotely within the U.S", "remotely within the US", "remote options", "remote possible"
                            , "applications for remote work may be considered", "Fully Remote", "full and/or partial remote"
                            , "full or partial remote"))
                    .descriptionPhrases(List.of("seeking an onsite"))
            );
        }







        filters.add(ExcludeJobFilter.build("Search")
                .descriptionPhrasesAndCount(List.of("Elasticsearch", "OpenSearch", "lucene", "Solr", "Splunk"), 1)
                .titlePhrases(List.of("Elasticsearch", "OpenSearch", "lucene", "Solr", "Splunk"))
        );


        filters.add(ExcludeJobFilter.build("Startup")
                .excludeIfTrueJobAttribute("startup")
        );
        filters.add(ExcludeJobFilter.build("Stress")
                .excludeAttributes(List.of("stress"))
        );

        filters.add(ExcludeJobFilter.build("SupplyChain")
                .badCompanies(List.of("Veriforce"))
                .titlePhrases(List.of("HighJump"))
                .descriptionPhrases(List.of("supply chain"))
        );

        if (preferences.isSoftwareSearch()) {
            filters.add(ExcludeJobFilter.build("SupportFocusedJob")
                    .descriptionPhrases(List.of("Support tickets", "second level support"))
                    .titlePhrases(List.of("Support"))
            );
        }


        filters.add(ExcludeJobFilter.build("Travel").maxAttribute("travelPercent", (float) preferences.getMaxTravelPercentage()));


        filters.add(ExcludeJobFilter.build("Volunteer")
                .titlePhrases(List.of("Volunteer"))
        );

        if (preferences.isExcludeWeekends()) {
            filters.add(ExcludeJobFilter.build("Weekends").descriptionPhrases(List.of("weekends")));
        }
        if (preferences.isExcludePoorWorkLifeBalance()) {
            filters.add(ExcludeJobFilter.build("WorkLifeBalance")
                    .goodCompanies(List.of("Ebay", "Guidehouse", "Trimble", "American Specialty Health", "Nationwide", "Webstaurant Store",
                            "Mayo Clinic"))
                    .badCompanies(List.of("Cardinal Health", "Cruise", "CVS Health", "Aha!", "Cash App"
                            , "Square", "Crunchyroll", "HCLTech", "Palo Alto Networks", "Intelerad Medical Systems",
                            "Tenable", "Kasten by Veeam", "Dremio", "Gigster", "Samsung Electronics",
                            "Arize AI", "Gevo, Inc.", "Harmonia Holdings", "Block","PKWARE",
                            "Penn State Health", "Actalent", "Grafana Labs", "Softrams", "FinTech LLC",
                            "Paytient", "DaVita", "Businessolver", "Integra Connect", "Corcentric",
                            "Discover Financial Services", "CivicPlus", "Saxon-Global", "GE", "Home Depot", "Wendy's"
                    ))
                    .excludeAttributes(List.of("workLifeBalance", "softwareEngineerHighOvertime"))
                    .testForCompanyInDescription(true)
            );

        }


        filters.add(ExcludeJobFilter.build("YearsExperience").maxAttribute("maxExperienceRequired", (float) preferences.getAmountOfTotalExperience()));


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


        filters.add(IncludeOrSkipJobFilter.build("Backend").titlePhrases(List.of("Backend", "Back-end", "Back end")));

        filters.add(IncludeOrSkipJobFilter.build("JobSecurity").goodCompanies(List.of("Mayo Clinic"))
                .includeAttribute("jobSecurity")
        );
        filters.add(IncludeOrSkipJobFilter.build("ModernizingMicroservices").descriptionPhrases(List.of("microservice", "microservices", "modernization", " API ", " APIs ",
                "RestFul", "webservice", "web service")));

        filters.add(IncludeOrSkipJobFilter.build("RelaxedEnvironment").goodCompanies(List.of("State Farm")));
        filters.add(IncludeOrSkipJobFilter.build("ProgrammingLanguage").skills(preferences.getSkills())
                .runIfFalse(List.of(preferences.isSoftwareSearch())));
        filters.add(IncludeOrSkipJobFilter.build("Spring").descriptionPhrases(List.of("Spring Boot", "Spring Data", "Spring MVC", "Spring Batch")));


        filters.add(IncludeOrSkipJobFilter.build("Tenure").minAttribute("Tenure", preferences.getDesiredTenure()));

        filters.add(IncludeOrSkipJobFilter.build("WorkLifeBalance")
                .goodCompanies(List.of("Ebay", "Guidehouse", "Trimble", "American Specialty Health", "Nationwide", "Webstaurant Store",
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


        return filters;
    }

    public static List<IncludeOrSkipJobFilter> getAlwaysIncludeFilters(Preferences preferences) {
        List<IncludeOrSkipJobFilter> filters = new ArrayList<>();
        filters.add(IncludeOrSkipJobFilter.build("EarlyCareer")
                .titleAndDescriptionPhrases(List.of("Early career", "Entry level", "Entry-Level"))
                .titlePhrases(List.of("entry", "early"))
        );


        filters.add(IncludeOrSkipJobFilter.build("FavoringCitizen").titleAndDescriptionPhrases(List.of(" mbi ", "public trust", "IRS Clearance",
                "Citizenship required")).descriptionPhrases(List.of("Must be a US Citizen", "maintain a security clearance"
                , "obtain a security clearance"
        )));
        filters.add(IncludeOrSkipJobFilter.build("PeopleFocused").descriptionPhrases(List.of("life balance", "people first", "like family"))
                .includeAttribute("peopleFocused"));

        filters.add(IncludeOrSkipJobFilter.build("PublicGood").testForCompanyInDescription(true).goodCompanies(List.of("Blackbaud", "Mayo Clinic"))
                .descriptionPhrases(List.of("nonprofit", "health equity", "fair opportunity", "unjust"
                        , "structural inequities", "racism", "underserved", "food access")).industries(List.of("Hospitals and Health Care", "Mental Health Care", "Higher Education"))
                .includeAttribute("publicGood")
        );
        filters.add(IncludeOrSkipJobFilter.build("WillTrain")
                .titleAndDescriptionPhrases(List.of("will train", "willing to train"))
                .titlePhrases(List.of(" train"))
        );


        return filters;
    }

    public static boolean testForStateExclusion(String state, Preferences preferences) {
        if (preferences.getState() == null || preferences.getState().isEmpty()) {
            return false;
        } else {
            return !preferences.getState().equalsIgnoreCase(state);
        }
    }
}
