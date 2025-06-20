package com.goodforallcode.jobExtractor.filters;

import com.goodforallcode.jobExtractor.filters.custom.*;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.ArrayList;
import java.util.List;

public class FilterFactory {

    public static List<JobFilter> getAlwaysExcludeCustomFilters(Preferences preferences) {
        List<JobFilter> filters = new ArrayList<>();
        filters.add(new AgeFilter());
        filters.add(new FortuneRankFilter());
        filters.add(new MunicipalityFilter());
        filters.add(new PositionCategoryFilter());
        filters.add(new ProgrammingFrameworkFilter());
        filters.add(new PromotedFilter());
        filters.add(new RemoteFilter());
        filters.add(new SalaryFilter());
        filters.add(new SeniorityFilter());
        filters.add(new StateFilter());
        filters.add(new SkillsFilter());
        return filters;
    }

    public static List<ExcludeJobFilter> getExcludeFilters(Preferences preferences) {
        List<ExcludeJobFilter> filters = new ArrayList<>();




        filters.add(ExcludeJobFilter.build("ExternalContractor")
                .excludeCompanyAttributes(List.of("softwareEngineerExternalContractors"))
        );




        if (preferences.getMaxEmployees() != null) {
            filters.add(ExcludeJobFilter.build("NumEmployees").maxAttribute("MinimumNumEmployees", (float) preferences.getMaxEmployees()));
        }


        if (preferences.isSkipTooManyApplicants()) {
            filters.add(ExcludeJobFilter.build("TooManyApplicants").maxAttribute("NumApplicants", (float) preferences.getMaxApplicants()));
        }



        return filters;
    }

    public static List<ExcludeJobFilter> getAlwaysExcludeFilters(Preferences preferences) {
        List<ExcludeJobFilter> filters = new ArrayList<>();


        filters.add(ExcludeJobFilter.build("AcceptingApplications").excludeIfFalseJobAttribute("AcceptingApplications"));

        filters.add(ExcludeJobFilter.build("Acquisitions")
                .excludeCompanyAttributes(List.of("acquisitions"))
                .matchingCompanies(List.of("BeyondTrust","PointClickCare","MeridianLink"))
        );


        if (preferences.isExcludeAggressiveTimelines()) {
            filters.add(ExcludeJobFilter.build("AggressiveTimelines")
                    .matchingCompanies(List.of("Clarifai", "Digital Technology Partners",
                            "Stryker", "Clover Health"))
                    .descriptionPhrases(List.of("fast-moving", "fast-paced", "fast paced", "aggressive timelines",
                            "aggressive delivery schedule"))
                    .testForCompanyInDescription(true)
                    .excludeCompanyAttributes(List.of("fastPaced"))
            );
        }


        filters.add(ExcludeJobFilter.build("Applied").excludeIfTrueJobAttribute("Applied"));



        filters.add(ExcludeJobFilter.build("BadManagement").matchingCompanies(List.of("Research Innovations Incorporated",
                "IT Labs", "Hansen Technologies", "LaunchDarkly", "TIDAL", "DXC Technology", "Oak Ridge National Laboratory", "EPLAN",
                "Envision Horizons","NinjaOne","Tire Rack","Help Scout"))
        );


        filters.add(ExcludeJobFilter.build("Bilingual")
                .titlePhrases(List.of("Spanish", "German", "French", "Italian", "Portuguese", "Russian", "Japanese",
                        "Chinese", "Korean", "Arabic","Bi-lingual",
                        "Mandarin", "Cantonese", "Bilingual", "Multilingual"))
        );


        filters.add(ExcludeJobFilter.build("CareerAccelerator")
                .matchingCompanies(List.of("Outcoder iO", "SynergisticIT"))
                .testForCompanyInDescription(true)
        );



                        /*
Exceptions
Secret: secrets in terms of authentication type stuff

 */

        filters.add(ExcludeJobFilter.build("Clearance")
                .descriptionPhrases(List.of("must have an active Public Trust Clearance", "Secret clearance"))
                .safeDescriptionPhrases(List.of("the secret","secret ingredient","Employee Polygraph Protection Act"
                ,"secret herbs","secret sauce"))
                .titleAndDescriptionPhrases(List.of("top secret", "secret ", "TS/SCI ", " TS/SCI"))
        );


        if (preferences.isExcludeConsultant()) {
            filters.add(ExcludeJobFilter.build("Consultant").excludeIfTrueJobAttribute("consultant"));
        }

        if (preferences.isExcludeContractJobs()) {
             /*
    exceptions:
    positions are subject to background screening as required by law or contract
     */

            filters.add(ExcludeJobFilter.build("Contract")
                    .excludeIfTrueJobAttribute("contract")
                    .excludeCompanyAttributes(List.of("contractor"))
            );
        }



        filters.add(ExcludeJobFilter.build("Credentialed")
                .excludeIfTrueJobAttribute("credentialed")
        );



        filters.add(ExcludeJobFilter.build("Dishonest")
                .matchingCompanies(List.of("IT Minds LLC", "Bitsoft International, Inc."
                        , "Envision Horizons", "Diaconia"))
                .testForCompanyInDescription(true)
        );





        filters.add(ExcludeJobFilter.build("EnrollmentRequired")
                .matchingCompanies(List.of("IT Pros"))
                .testForCompanyInDescription(true)
        );




        filters.add(ExcludeJobFilter.build("ForeignLocatedCompany")
                .matchingCompanies(List.of("Redcare Pharmacy"))
                .testForCompanyInDescription(true)
        );


        filters.add(ExcludeJobFilter.build("Freelance")
                .matchingCompanies(List.of("CleverTech"))
                .titleAndDescriptionPhrases(List.of("Freelance"))
        );


        filters.add(ExcludeJobFilter.build("JobSecurity")
                .matchingCompanies(List.of("Allstate", "New Relic", "Breezeline", "Slack", "Wordly",
                        "Crossover", "Invitae", "Omnicell", "Komodo Health", "Rocket Software", "Zinnia", "CSG",
                        "ODP Corporation","Dataminr","Help Scout","The ODP Corporation",
                        "NTT DATA Services", "Cruise", "VMware", "Intelerad Medical Systems","Toast",
                        "Air Apps", "CivicPlus", "Vertisystem Inc.", "Kyruus", "Atlassian", "Zwift"))
                .includeAttribute("jobSecurity")
                .excludeCompanyAttributes(List.of("recentLayoffs"))
        );

        filters.add(ExcludeJobFilter.build(" JobTooOld").maxAttribute("jobAgeInDays", (float) preferences.getMaxJobAgeInDays()));


        if(preferences.getMaxLevel()!=null) {
            filters.add(ExcludeJobFilter.build("Level")
                    .maxAttribute("level", (float) preferences.getMaxLevel())
            );
        }

        filters.add(ExcludeJobFilter.build("LowEducationField")
                .excludeIfTrueJobAttribute("lowEducationField")
        );


        filters.add(ExcludeJobFilter.build("NightShift")
                .excludeIfTrueJobAttribute("nightShift")
        );

        filters.add(ExcludeJobFilter.build("NotEnglish")
                .titleAndDescriptionPhrases(List.of("Entwick", "korean", "Versicherung", "Softwarový"
                        , "inženýr", "Japanese", "Pessoa", "Desenvolvedor", "Pleno", "Sênior", "Especialista",
                        "Trabalho", "Remoto", "automatizadora", " de ", "testes", "Pleno", "Sênior",
                        "Geliştirici", "NİTELİKLER", "merkezli", "şirketi", "deneyimi", "Güçlü", "analizi",
                        "equipo", "Desarrollo", " Wir ", "Ingeniero", "Especializado", "Japanese", "Mandarin"
                        , "同"))
        );


        filters.add(ExcludeJobFilter.build(" NotPeopleFocused").matchingCompanies(List.of("Maximus", "IDR, Inc.", "Marketlab",
                "Gainwell Technologies", "Revature",
                "Solü Technology Partners")));


        if (preferences.isExcludeOnCall()) {
            filters.add(ExcludeJobFilter.build("OnCall").excludeIfTrueJobAttribute("onCall"));
        }

        filters.add(ExcludeJobFilter.build("OpenCall")
                .titlePhrases(List.of("Engineering Prospects", "open call", "Expression of Interest"))
        );

        filters.add(ExcludeJobFilter.build("OutsourcingAndOffshore")
                .excludeIfTrueJobAttribute("outsources")
        );

        filters.add(ExcludeJobFilter.build("RegularlyWarringCountryCompany")
                .matchingCompanies(List.of("Київстар"))
                .testForCompanyInDescription(true)
        );




        filters.add(ExcludeJobFilter.build("Startup")
                .excludeIfTrueJobAttribute("startup")
        );
        filters.add(ExcludeJobFilter.build("Stress")
                .excludeCompanyAttributes(List.of("stress"))
        );



        filters.add(ExcludeJobFilter.build("Travel").maxAttribute("travelPercent", (float) preferences.getMaxTravelPercentage()));


        filters.add(ExcludeJobFilter.build("Volunteer")
                .titlePhrases(List.of("Volunteer"))
        );

        if (preferences.isExcludeWeekends()) {
            filters.add(ExcludeJobFilter.build("Weekends")
                    .descriptionPhrases(List.of("weekends"))
                    .safeDescriptionPhrases(List.of("weekends as needed"))
            );
        }
            filters.add(ExcludeJobFilter.build("WorkLifeBalance")
                    .exceptionalCompanies(List.of("Ebay", "Guidehouse", "Trimble", "American Specialty Health", "Nationwide", "Webstaurant Store",
                            "Mayo Clinic"))
                    .matchingCompanies(List.of("Cardinal Health", "Cruise", "CVS Health", "Aha!", "Cash App"
                            , "Square", "Crunchyroll", "HCLTech", "Palo Alto Networks", "Intelerad Medical Systems",
                            "Tenable", "Kasten by Veeam", "Dremio", "Gigster", "Samsung Electronics",
                            "Arize AI", "Gevo, Inc.", "Harmonia Holdings", "Block","PKWARE",
                            "Penn State Health", "Actalent", "Grafana Labs", "Softrams", "FinTech LLC",
                            "Paytient", "DaVita", "Businessolver", "Integra Connect", "Corcentric",
                            "Discover Financial Services", "CivicPlus", "Saxon-Global", "Home Depot", "Wendy's"
                    ))
                            .matchingCompaniesStartsWith(List.of("GE "))
                    .excludeCompanyAttributes(List.of("workLifeBalance", "softwareEngineerHighOvertime"))
                    .testForCompanyInDescription(true)
            );




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
                .includeCompanyAttribute("jobSecurity")
        );
        filters.add(IncludeOrSkipJobFilter.build("ModernizingMicroservices").descriptionPhrases(List.of("microservice", "microservices", "modernization", " API ", " APIs ",
                "RestFul", "webservice", "web service")));

        filters.add(IncludeOrSkipJobFilter.build("RelaxedEnvironment").goodCompanies(List.of("State Farm")));
        filters.add(IncludeOrSkipJobFilter.build("Skills").skills(preferences.getSkills()));
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
        filters.add(IncludeOrSkipJobFilter.build("WillTrain")
                .includeJobAttribute("willTrain")
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
                .includeCompanyAttribute("peopleFocused"));

        filters.add(IncludeOrSkipJobFilter.build("PublicGood").testForCompanyInDescription(true).goodCompanies(List.of("Blackbaud", "Mayo Clinic"))
                .descriptionPhrases(List.of("nonprofit", "health equity", "fair opportunity", "unjust"
                        , "structural inequities", "racism", "underserved", "food access")).industries(List.of("Hospitals and Health Care", "Mental Health Care", "Higher Education"))
                .includeCompanyAttribute("publicGood")
        );


        return filters;
    }

}
