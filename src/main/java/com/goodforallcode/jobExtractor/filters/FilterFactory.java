package com.goodforallcode.jobExtractor.filters;

import com.goodforallcode.jobExtractor.filters.both.*;
import com.goodforallcode.jobExtractor.filters.deep.*;
import com.goodforallcode.jobExtractor.filters.deep.always.*;
import com.goodforallcode.jobExtractor.filters.deep.always.DevSecOpsDescriptionFilter;
import com.goodforallcode.jobExtractor.filters.deep.always.SupportFilter;
import com.goodforallcode.jobExtractor.filters.deep.always.lang.BasicFilter;
import com.goodforallcode.jobExtractor.filters.deep.always.lang.JavascriptFilter;
import com.goodforallcode.jobExtractor.filters.deep.always.lang.MicrosoftStackFilter;
import com.goodforallcode.jobExtractor.filters.deep.always.lang.PythonFilter;
import com.goodforallcode.jobExtractor.filters.include.*;
import com.goodforallcode.jobExtractor.filters.include.shallow.RelaxedEnvironmentFilter;
import com.goodforallcode.jobExtractor.filters.shallow.*;
import com.goodforallcode.jobExtractor.filters.shallow.company.*;
import com.goodforallcode.jobExtractor.filters.shallow.title.*;

import java.util.ArrayList;
import java.util.List;

public class FilterFactory {


    public static List<JobFilter> getShallowFiltersAlwaysExclude() {
        List<JobFilter> filters = new ArrayList<>();

        filters.add(new AdvertisingFilter());
        filters.add(new BadManagementFilter());
        filters.add(new BlockChainFilter());
        filters.add(new BigDataFilter());
        filters.add(new BusinessIntelligenceFilter());
        filters.add(new BusinessProcessManagementFilter());
        filters.add(new BusinessRoleFilter());
        filters.add(new CareerAcceleratorFilter());
        filters.add(new ClearanceFilter());
        filters.add(new CommerceFilter());
        filters.add(new ConsultantFilter());
        filters.add(new ConstructionFilter());
        filters.add(new ContractFilter());
        filters.add(new DataExchangeFilter());
        filters.add(new DefenseFilter());
        filters.add(new DishonestCompanyFilter());
        filters.add(new EnrollmentRequiredFilter());
        filters.add(new EntertainmentFilter());
        filters.add(new FinanceFilter());
        filters.add(new FortuneRankFilter());
        filters.add(new FossilFuelsFilter());
        filters.add(new FreelanceFilter());
        filters.add(new FresherAndInternLevelFilter());
        filters.add(new FrontEndFilter());
        filters.add(new FullstackFilter());
        filters.add(new GamingFilter());

        filters.add(new HardwareFilter());
        filters.add(new InsuranceFilter());
        filters.add(new InternetTelevisionAndMobileCompanyFilter());
        filters.add(new JobSecurityFilter(false));
        filters.add(new LevelFilter());
        filters.add(new LoAndNoCodeFilter());
        filters.add(new LocalFilter());
        filters.add(new MainframeFilter());
        filters.add(new ManufacturingFilter());
        filters.add(new MarketingFilter());
        filters.add(new MegaBrandFilter());
        filters.add(new MicrosoftStackFilter());

        filters.add(new NightShiftFilter());
        filters.add(new NotEnglishFilter());
        filters.add(new NumApplicantsFilter(false,false));
        filters.add(new OutsourcingAndOffshoreFilter());
        filters.add(new OldAgeFilter());
        filters.add(new PeopleFocusedFilter(false));
        filters.add(new ProgrammingLanguageFilter(false));
        filters.add(new OperatingSystemCompanyFilter());
        filters.add(new PharmacyFilter());
        filters.add(new PromotedFilter());
        filters.add(new ProgrammingFrameworkFilter());
        filters.add(new RealEstateFilter());
        filters.add(new RegularlyWarringCountryCompanyFilter());
        filters.add(new ReligiousFilter());
        filters.add(new RemoteFilter());
        filters.add(new ResearcherFilter());
        filters.add(new SalaryFilter());
        filters.add(new SeniorFilter());
        filters.add(new StartupFilter());
        filters.add(new WorkLifeBalanceFilter(false));


        //title filters
        filters.add(new AboveSeniorTitleFilter());
        filters.add(new AgileRoleTitleFilter());
        filters.add(new AITitleFilter());
        filters.add(new AutomationTitleFilter());
        filters.add(new BusinessAnalystTitleFilter());
        filters.add(new CaseManagementTitleFilter());
        filters.add(new ClinicalDataTitleFilter());
        filters.add(new CloudTitleFilter());
        filters.add(new ContentManagementTitleFilter());
        filters.add(new CoordinationAndManagementTitleFilter());
        filters.add(new DataFocusedTitleFilter());
        filters.add(new DatabaseTitleFilter());
        filters.add(new DataManagementTitleFilter());
        filters.add(new DevSecOpsTitleFilter());
        filters.add(new EducatorTitleFilter());
        filters.add(new HumanAndCustomerManagementAndSalesTechnologyTitleFilter());
        filters.add(new IdentityManagementTitleFilter());
        filters.add(new IntegrationTitleFilter());
        filters.add(new InventoryManagementTitleFilter());
        filters.add(new ITRoleTitleFilter());
        filters.add(new LifecycleManagementTitleFilter());
        filters.add(new MessagingTitleFilter());
        filters.add(new MobileTitleFilter());
        filters.add(new OpenCallTitleFilter());
        filters.add(new PhysicalEngineerTitleFilter());
        filters.add(new ProfessionalServicesEngineerTitleFilter());
        filters.add(new QATitleFilter());
        filters.add(new SalesTitleFilter());
        filters.add(new ScientificProgrammerTitleFilter());
        filters.add(new SoftwareDevelopmentProjectManagementTitleFilter());
        filters.add(new SpecializedSoftwareEngineerFilter());
        filters.add(new SupportTitleFilter());

        //company
        filters.add(new AccountingCompanyFilter());
        filters.add(new BettingCompanyFilter());
        filters.add(new CyberSecurityCompanyFilter());
        filters.add(new DefenseCompanyFilter());
        filters.add(new ForeignLocatedCompanyFilter());
        filters.add(new InsuranceCompanyNameFilter());
        filters.add(new OperatingSystemCompanyFilter());
        return filters;
    }

    public static List<JobFilter> getShallowFiltersSkip() {
        List<JobFilter> filters = new ArrayList<>();
        filters.add(new NumApplicantsFilter(true,false));
        filters.add(new YoungAgeFilter());
        return filters;
    }

    public static List<JobFilter> getDeepFilters() {
        List<JobFilter> filters = new ArrayList<>();
        filters.add(new ApplicationEngineerFilter());
        filters.add(new BigDataFilter());
        filters.add(new CachingFilter());
        filters.add(new ContentManagementFilter());
        filters.add(new HumanAndCustomerManagementAndSalesTechnologyFilter());
        filters.add(new EnterpriseApplicationsFilter());
        filters.add(new InsuranceFilter());
        filters.add(new IntegrationFilter());
        filters.add(new JeeFilter());
        filters.add(new ResearcherFilter());
        filters.add(new SupportFilter());
        return filters;
    }

    public static List<JobFilter> getDeepFiltersTrusted() {
        List<JobFilter> filters = new ArrayList<>();
        filters.add(new AIFilter());
        filters.add(new BettingFilter());
//        filters.add(new BigDataFilter());
        filters.add(new BusinessIntelligenceFilter());
        filters.add(new BusinessProcessManagementFilter());
//        filters.add(new CachingFilter());
        filters.add(new CaseManagementFilter());
        filters.add(new CloudFilter());
        filters.add(new CommerceFilter());
        filters.add(new ComplexFilter());
        filters.add(new ComputerVisionFilter());
        //        filters.add(new ContentManagementFilter());
        filters.add(new CustomerExperienceManagementFilter());
        filters.add(new CyberSecurityFilter());
        filters.add(new DataExchangeFilter());
        filters.add(new DefenseFilter());
        filters.add(new EntertainmentFilter());
//        filters.add(new EnterpriseApplicationsFilter());
        filters.add(new ERPFilter());
        filters.add(new FullstackFilter());
        filters.add(new GeospatialFilter());
        filters.add(new HumanAndCustomerManagementAndSalesTechnologyFilter());

        filters.add(new IdentityManagementFilter());
        filters.add(new InfrastructureSoftwareFilter());
//        filters.add(new InsuranceFilter());
//        filters.add(new IntegrationFilter());
//        filters.add(new JeeFilter());
        filters.add(new LoAndNoCodeFilter());
        filters.add(new MarketingFilter());
        filters.add(new NumApplicantsFilter(false,true));
        filters.add(new NumEmployeesFilter());
        filters.add(new OracleTechFilter());

        filters.add(new OutsourcingAndOffshoreFilter());
        filters.add(new PerformanceFilter());
        filters.add(new PLMFilter());

//        filters.add(new ResearcherFilter());
        filters.add(new SearchFilter());
//        filters.add(new SupportFilter());
        filters.add(new UnixFilter());
        filters.add(new YearsExperienceFilter());
        return filters;
    }
    public static List<JobFilter> getDeepFiltersSkip() {
        List<JobFilter> filters = new ArrayList<>();
        filters.add(new SourcedFromJobBoardFilter());
        filters.add(new YoungAgeFilter());
        return filters;
    }

    public static List<JobFilter> getDeepFiltersAlwaysExclude() {
        List<JobFilter> filters = new ArrayList<>();
        filters.add(new ProgrammingFrameworkFilter());
        return filters;
    }
    public static List<JobFilter> getCompanySummaryAlwaysIncludeFilters() {
        List<JobFilter> filters = new ArrayList<>();
        filters.add(new PeopleFocusedFilter(true));
        filters.add(new PublicGoodFilter());
        
        return filters;
    }
    public static List<JobFilter> getCompanySummaryExcludeFilters() {
        List<JobFilter> filters = new ArrayList<>();

        filters.add(new AcquisitionsFilter());
        filters.add(new AdvertisingFilter());
        filters.add(new BadManagementFilter());
        filters.add(new CommerceFilter());
        filters.add(new ConstructionFilter());
        filters.add(new ConsultantFilter());
        filters.add(new ContractFilter());
        filters.add(new DefenseFilter());
        filters.add(new EntertainmentFilter());
        filters.add(new ExternalContractorFilter());
        filters.add(new FinanceFilter());
        filters.add(new FortuneRankFilter());
        filters.add(new JobSecurityFilter(false));
        filters.add(new ManufacturingFilter());
        filters.add(new MarketingFilter());
        filters.add(new NightShiftFilter());
        filters.add(new NumEmployeesFilter());
        filters.add(new OutsourcingAndOffshoreFilter());
        filters.add(new PharmaceuticalFilter());
        filters.add(new RetailFilter());
        filters.add(new StartupFilter());
        filters.add(new StressFilter());
        filters.add(new TransportationFilter());
        filters.add(new WorkLifeBalanceFilter(false));

        return filters;
    }

    public static List<JobFilter> getDeepFiltersAlwaysExcludeTrusted() {
        List<JobFilter> filters = new ArrayList<>();

        filters.add(new AdvertisingFilter());
        filters.add(new ApplicationEngineerFilter());
        filters.add(new BadManagementFilter());
        filters.add(new BettingFilter());
        filters.add(new BlockChainFilter());
        filters.add(new BusinessIntelligenceFilter());
        filters.add(new BusinessRoleFilter());
        filters.add(new CareerAcceleratorFilter());
        filters.add(new ClearanceFilter());
        filters.add(new CommerceFilter());
        filters.add(new ConsultantFilter());
        filters.add(new ConstructionFilter());
        filters.add(new ContractFilter());
        filters.add(new CustomerExperienceManagementFilter());
        filters.add(new DevSecOpsDescriptionFilter());
        filters.add(new EntertainmentFilter());
        filters.add(new ERPFilter());
        filters.add(new FinanceFilter());
        filters.add(new FortuneRankFilter());
        filters.add(new FossilFuelsFilter());
        filters.add(new FreelanceFilter());
        filters.add(new FresherAndInternLevelFilter());
        filters.add(new FrontEndFilter());
        filters.add(new FullstackFilter());
        filters.add(new GamingFilter());
        filters.add(new GeospatialFilter());
        filters.add(new HardwareFilter());
        filters.add(new InsuranceFilter());
        filters.add(new InternetTelevisionAndMobileCompanyFilter());

        filters.add(new ManufacturingFilter());
        filters.add(new MarketingFilter());
        filters.add(new NumApplicantsFilter(false,true));
        filters.add(new PharmacyFilter());
        filters.add(new PharmaceuticalFilter());
        filters.add(new PLMFilter());

        filters.add(new RealEstateFilter());
        filters.add(new ReligiousFilter());
        filters.add(new RemoteFilter());
        filters.add(new RetailFilter());
        filters.add(new SupplyChainFilter());
        filters.add(new TelecomFilter());
        filters.add(new TransportationFilter());
        filters.add(new TravelFilter());
        filters.add(new WorkLifeBalanceFilter(false));

        //lang
        filters.add(new PythonFilter());
        return filters;
    }

    public static List<JobFilter> getDeepFiltersExcludeEvenIfAlwaysInclude() {
        List<JobFilter> filters = new ArrayList<>();

        filters.add(new AcceptingApplicationsFilter());
        filters.add(new AppliedFilter());
        filters.add(new DefenseFilter());
        filters.add(new DishonestCompanyFilter());
        filters.add(new EnrollmentRequiredFilter());
        filters.add(new FrontEndFilter());
        filters.add(new FullstackFilter());
        filters.add(new JobSecurityFilter(false));
        filters.add(new LocalFilter());
        filters.add(new MainframeFilter());
        filters.add(new MegaBrandFilter());
        filters.add(new MSTFilter());
        filters.add(new NightShiftFilter());
        filters.add(new NotEnglishFilter());
        filters.add(new NumApplicantsFilter(false,true));
//        filters.add(new ProgrammingFrameworkFilter());
        filters.add(new ProgrammingLanguageFilter(false));
        filters.add(new PSTFilter());
        filters.add(new RegularlyWarringCountryCompanyFilter());
        filters.add(new SkillsDeepFilter());
        filters.add(new StartupFilter());

        //lang
        filters.add(new BasicFilter());
        filters.add(new JavascriptFilter());
        filters.add(new MicrosoftStackFilter());
        return filters;
    }
    public static List<JobFilter> getAlwaysIncludeDeepFilters() {
        List<JobFilter> filters = new ArrayList<>();
        filters.add(new FavoringCitizenFilter());
        filters.add(new ModernizingMicroservicesFilter());
        filters.add(new PeopleFocusedFilter(true));
        filters.add(new PublicGoodFilter());
        filters.add(new SpringFilter());
        filters.add(new Year2ToMidFilter());

        
        return filters;
    }

    /**
     * This will run after all the mandatory excludes so it is not always including
     * @return
     */
    public static List<JobFilter> getAlwaysIncludeShallowFilters() {
        List<JobFilter> filters = new ArrayList<>();
        filters.add(new FavoringCitizenFilter());
        filters.add(new PublicGoodFilter());
        return filters;
    }

    /**
     * These will not always include but will be used when we were going to include anyway due to no excludes. This 
     * will help us manually approve
     * @return
     */
    public static List<JobFilter> getIncludeFilters() {
        List<JobFilter> filters = new ArrayList<>();
    
            filters.add(new TenureFilter());
        filters.add(new BackendTitleFilter());
        filters.add(new JobSecurityFilter(true));
        filters.add(new RelaxedEnvironmentFilter());
        filters.add(new ProgrammingLanguageFilter(true));
        filters.add(new WorkLifeBalanceFilter(true));
        return filters;
    }

}
