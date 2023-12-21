package com.goodforallcode.jobExtractor.filters;

import com.goodforallcode.jobExtractor.filters.both.*;
import com.goodforallcode.jobExtractor.filters.deep.*;
import com.goodforallcode.jobExtractor.filters.deep.always.*;
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

        filters.add(new AccountingCompanyFilter());
        filters.add(new AdvertisingFilter());
        filters.add(new BadManagementFilter());
        filters.add(new BettingFilter());
        filters.add(new CareerAcceleratorFilter());
        filters.add(new ConstructionFilter());
        filters.add(new DishonestCompanyFilter());
        filters.add(new EnrollmentRequiredFilter());
        filters.add(new EntertainmentFilter());
        filters.add(new InternetTelevisionAndMobileCompanyFilter());
        filters.add(new JobSecurityFilter(false));
        filters.add(new MainframeFilter());
        filters.add(new ManufacturingFilter());
        filters.add(new MegaBrandFilter());
        filters.add(new PoorManagementFilter());
        filters.add(new OperatingSystemCompanyFilter());
        filters.add(new PharmacyFilter());
        filters.add(new RegularlyWarringCountryCompanyFilter());
        filters.add(new ReligiousFilter());

        filters.add(new BigDataFilter());
        filters.add(new BlockChainFilter());
        filters.add(new ComputerVisionFilter());
        filters.add(new ConsultantFilter());
        filters.add(new ContractFilter());
        filters.add(new CustomerExperienceManagementFilter());
        filters.add(new CyberSecurityFilter());
        filters.add(new DataExchangeFilter());
        filters.add(new DefenseFilter());
        filters.add(new ERPFilter());
        filters.add(new FinanceFilter());
        filters.add(new FortuneRankFilter());
        filters.add(new FossilFuelsFilter());
        filters.add(new FreelanceFilter());
        filters.add(new FresherAndInternLevelFilter());
        filters.add(new FullstackFilter());
        filters.add(new GamingFilter());
        filters.add(new GeospatialFilter());
        filters.add(new HardwareFilter());
        filters.add(new IdentityManagementFilter());
        filters.add(new InsuranceFilter());

        filters.add(new LevelFilter());
        filters.add(new LocalFilter());
        filters.add(new MarketingFilter());

        filters.add(new NightShiftFilter());
        filters.add(new NumApplicantsFilter(false,false));
        filters.add(new OutsourcingAndOffshoreFilter());
        filters.add(new OldAgeFilter());
        filters.add(new OracleTechFilter());
        filters.add(new PeopleFocusedFilter(false));
        filters.add(new PLMFilter());
        filters.add(new PromotedFilter());
        filters.add(new ProgrammingFrameworkFilter());
        filters.add(new RealEstateFilter());

        filters.add(new RemoteFilter());
        filters.add(new ResearcherFilter());
        filters.add(new SalaryFilter());
        filters.add(new StartupFilter());
        filters.add(new SupplyChainFilter());
        filters.add(new SupportFilter());
        filters.add(new TelecomFilter());
        filters.add(new UnixFilter());



        //title filters
        filters.add(new AgileTitleFilter());
        filters.add(new AITitleFilter());
        filters.add(new AutomationTitleFilter());
        filters.add(new BusinessAnalystTitleFilter());
        filters.add(new BusinessIntelligenceFilter());
        filters.add(new BusinessProcessManagementFilter());
        filters.add(new BusinessRoleFilter());
        filters.add(new CaseManagementFilter());
        filters.add(new ClearanceFilter());
        filters.add(new ClinicalDataTitleFilter());
        filters.add(new CloudFilter());
        filters.add(new CommerceFilter());
        filters.add(new ComplexFilter());
        filters.add(new ContentManagementFilter());
        filters.add(new CoordinationAndManagementTitleFilter());
        filters.add(new DataFocusedTitleFilter());
        filters.add(new DatabaseTitleFilter());
        filters.add(new DataManagementFilter());
        filters.add(new DevSecOpsFilter());
        filters.add(new EducatorTitleFilter());
        filters.add(new FrontEndFilter());
        filters.add(new HardwareFilter());
        filters.add(new HumanAndCustomerManagementAndSalesTechnologyFilter());
        filters.add(new InfrastructureSoftwareFilter());
        filters.add(new IntegrationFilter());
        filters.add(new InventoryManagementFilter());
        filters.add(new LifecycleManagementFilter());
        filters.add(new LoAndNoCodeFilter());
        filters.add(new MessagingTitleFilter());
        filters.add(new MicrosoftStackFilter());
        filters.add(new MobileTitleFilter());
        filters.add(new NotEnglishFilter());
        filters.add(new AboveSeniorTitleFilter());
        filters.add(new SeniorFilter());
        filters.add(new OpenCallTitleFilter());
        filters.add(new PhysicalEngineerTitleFilter());
        filters.add(new ProgrammingLanguageFilter(false));
        filters.add(new QATitleFilter());
        filters.add(new SalesTitleFilter());
        filters.add(new SearchFilter());
        filters.add(new SoftwareDevelopmentProjectManagementTitleFilter());
        filters.add(new SupportTitleFilter());
        filters.add(new WorkLifeBalanceFilter(false));
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
//        filters.add(new AIFilter());
//        filters.add(new BettingFilter());
        filters.add(new BigDataFilter());
//        filters.add(new BusinessProcessManagementFilter());
        filters.add(new CachingFilter());
        filters.add(new CaseManagementFilter());
        filters.add(new CloudFilter());
//        filters.add(new CommerceFilter());
        filters.add(new ComplexFilter());
//        filters.add(new ComputerVisionFilter());
        filters.add(new ContentManagementFilter());
//        filters.add(new CyberSecurityFilter());
//        filters.add(new DataExchangeFilter());
        filters.add(new HumanAndCustomerManagementAndSalesTechnologyFilter());
//        filters.add(new DefenseFilter());
        filters.add(new EnterpriseApplicationsFilter());
//         filters.add(new FullstackFilter());
        filters.add(new IdentityManagementFilter());
        filters.add(new InfrastructureSoftwareFilter());
        filters.add(new IntegrationFilter());
        filters.add(new JeeFilter());
//        filters.add(new LoAndNoCodeFilter());

//            filters.add(new NumApplicantsFilter(false));

//        filters.add(new NumEmployeesFilter());
//        filters.add(new OffshoreFilter());
//        filters.add(new OracleTechFilter());
//        filters.add(new PerformanceFilter());
        filters.add(new ResearcherFilter());
        filters.add(new SearchFilter());
        filters.add(new SupportFilter());
//        filters.add(new YearsExperienceFilter());
        return filters;
    }

    public static List<JobFilter> getDeepFiltersTrusted() {
        List<JobFilter> filters = new ArrayList<>();
        filters.add(new AIFilter());
//        filters.add(new ApplicationEngineerFilter());
        filters.add(new BettingFilter());
//        filters.add(new BigDataFilter());
        filters.add(new BusinessProcessManagementFilter());
//        filters.add(new CachingFilter());
//        filters.add(new CaseManagementFilter());
//        filters.add(new CloudHeavyFilter());
//        filters.add(new ComplexFilter());
        filters.add(new ComputerVisionFilter());
        //        filters.add(new ContentManagementFilter());
          filters.add(new CyberSecurityFilter());
          filters.add(new DataExchangeFilter());
//        filters.add(new HumanAndCustomerManagementAndSalesTechnologyFilter());
//        filters.add(new EnterpriseApplicationsFilter());

//        filters.add(new IdentityManagementFilter());
//        filters.add(new InfrastructureSoftwareFilter());
//        filters.add(new InsuranceFilter());
//        filters.add(new IntegrationFilter());
//        filters.add(new JeeFilter());
        filters.add(new LoAndNoCodeFilter());
//        filters.add(new MarketingFilter());
        filters.add(new NumApplicantsFilter(false,true));
        filters.add(new NumEmployeesFilter());
        filters.add(new OutsourcingAndOffshoreFilter());
        filters.add(new OracleTechFilter());
        filters.add(new PerformanceFilter());

//        filters.add(new ResearcherFilter());
//        filters.add(new SearchFilter());
//        filters.add(new SupportFilter());
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
        filters.add(new LocalFilter());
        filters.add(new ProgrammingFrameworkFilter());
        return filters;
    }


    public static List<JobFilter> getDeepFiltersAlwaysExcludeTrusted() {
        List<JobFilter> filters = new ArrayList<>();

        filters.add(new AcceptingApplicationsFilter());
        filters.add(new AccountingCompanyFilter());
        filters.add(new AdvertisingFilter());
        filters.add(new ApplicationEngineerFilter());
        filters.add(new AppliedFilter());
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
        filters.add(new DefenseFilter());
        filters.add(new DevSecOpsFilter());
        filters.add(new DishonestCompanyFilter());
        filters.add(new EnrollmentRequiredFilter());
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
        filters.add(new JobSecurityFilter(false));

//        filters.add(new LocalFilter());
        filters.add(new MainframeFilter());
        filters.add(new ManufacturingFilter());
        filters.add(new MarketingFilter());
        filters.add(new MegaBrandFilter());
        filters.add(new PoorManagementFilter());
        filters.add(new MSTFilter());
        filters.add(new NightShiftFilter());
        filters.add(new NotEnglishFilter());
        filters.add(new NumApplicantsFilter(false,true));
        filters.add(new OperatingSystemCompanyFilter());
        filters.add(new PharmacyFilter());
        filters.add(new PharmaceuticalFilter());
        filters.add(new PLMFilter());

//        filters.add(new ProgrammingFrameworkFilter());
        filters.add(new ProgrammingLanguageFilter(false));
        filters.add(new PSTFilter());
        filters.add(new RealEstateFilter());
        filters.add(new RegularlyWarringCountryCompanyFilter());
        filters.add(new ReligiousFilter());
        filters.add(new RemoteFilter());
        filters.add(new RetailFilter());
        filters.add(new SeniorFilter());
        filters.add(new StartupFilter());
        filters.add(new TelecomFilter());
        filters.add(new TransportationFilter());
        filters.add(new TravelFilter());
        filters.add(new UnixFilter());
        filters.add(new WorkLifeBalanceFilter(false));

        //lang
        filters.add(new BasicFilter());
        filters.add(new JavascriptFilter());
        filters.add(new MicrosoftStackFilter());
        filters.add(new PythonFilter());
        return filters;
    }
    public static List<JobFilter> getAlwaysIncludeDeepFilters() {
        List<JobFilter> filters = new ArrayList<>();
        filters.add(new FavoringCitizenFilter());
        filters.add(new ModernizingMicroservicesFilter());
        filters.add(new PeopleFocusedFilter(true));
        filters.add(new PublicGoodFilter());
        filters.add(new SpringFilter());
        filters.add(new TenureFilter());
        filters.add(new Year2ToMidFilter());
        return filters;
    }

    /**
     * This will run after all the mandatory excludes so it is not always including
     * @return
     */
    public static List<JobFilter> getAlwaysIncludeShallowFilters() {
        List<JobFilter> filters = new ArrayList<>();
        filters.add(new BackendTitleFilter());
        filters.add(new FavoringCitizenFilter());
        filters.add(new JobSecurityFilter(true));
        filters.add(new PublicGoodFilter());
        filters.add(new RelaxedEnvironmentFilter());
        filters.add(new ProgrammingLanguageFilter(true));
        filters.add(new WorkLifeBalanceFilter(true));
        return filters;
    }
}
