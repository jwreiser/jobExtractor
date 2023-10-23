package com.goodforallcode.jobExtractor.filters;

import com.goodforallcode.jobExtractor.filters.both.*;
import com.goodforallcode.jobExtractor.filters.deep.*;
import com.goodforallcode.jobExtractor.filters.deep.MarketingFilter;
import com.goodforallcode.jobExtractor.filters.deep.always.*;
import com.goodforallcode.jobExtractor.filters.deep.always.lang.BasicFilter;
import com.goodforallcode.jobExtractor.filters.deep.always.lang.JavascriptFilter;
import com.goodforallcode.jobExtractor.filters.deep.always.lang.MicrosoftStackFilter;
import com.goodforallcode.jobExtractor.filters.deep.always.lang.PythonFilter;
import com.goodforallcode.jobExtractor.filters.include.*;
import com.goodforallcode.jobExtractor.filters.include.shallow.RelaxedEnvironmentFilter;
import com.goodforallcode.jobExtractor.filters.shallow.*;
import com.goodforallcode.jobExtractor.filters.shallow.company.*;
import com.goodforallcode.jobExtractor.filters.both.OffshoreFilter;
import com.goodforallcode.jobExtractor.filters.shallow.company.CommerceFilter;
import com.goodforallcode.jobExtractor.filters.shallow.title.*;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.ArrayList;
import java.util.List;

public class FilterFactory {
    public static List<JobFilter> getShallowFilters(String url, Preferences preferences) {
        List<JobFilter> filters = new ArrayList<>();
        filters.add(new CyberSecurityFilter());
        filters.add(new DefenseFilter());
        filters.add(new GamingFilter());
        filters.add(new FinanceFilter());
        filters.add(new FortuneRankFilter());
        filters.add(new FreelanceFilter());
        filters.add(new FullstackFilter());
        filters.add(new HardwareFilter());
        filters.add(new IdentityManagementFilter());
        filters.add(new InsuranceFilter());
        filters.add(new MarketingFilter());
        filters.add(new MegaBrandFilter());
        filters.add(new NotFresherAndInternLevelFilter());
        filters.add(new OffshoreFilter());
        filters.add(new OldAgeFilter());
        filters.add(new OperatingSystemFilter());
        filters.add(new PromotedFilter());
        filters.add(new RealEstateFilter());
        filters.add(new ReligiousFilter());
        filters.add(new SalaryFilter());
        filters.add(new StartupFilter());
        filters.add(new PeopleFocusedFilter(false));

        if (preferences.getMaxLevel() != null) {
            filters.add(new LevelFilter());
        }
        filters.add(new HiddenFilter());

        return filters;
    }

    public static List<JobFilter> getShallowFiltersAlwaysExclude(String url, Preferences preferences) {
        List<JobFilter> filters = new ArrayList<>();
        filters.add(new ConsultantFilter());
        filters.add(new ContractBasedEmployerFilter());
        filters.add(new ERPFilter());
        filters.add(new PoorJobSecurityFilter());
        filters.add(new LocalFilter());
        filters.add(new PeopleFocusedFilter(false));
        filters.add(new RemoteFilter());
        filters.add(new UpskillingRecruitmentCompanyFilter());

        //company name
        filters.add(new AccountingCompanyFilter());
        filters.add(new PharmacyFilter());

        //company name filters
        filters.add(new InternetTelevisionAndMobileCompanyFilter());

        //title filters
        filters.add(new AgileTitleFilter());
        filters.add(new AITitleFilter());
        filters.add(new BusinessIntelligenceFilter());//if it is in the title exclude it
        filters.add(new ClearanceFilter());
        filters.add(new CloudTitleFilter());
        filters.add(new CommerceFilter());
        filters.add(new ContentManagementFilter());
        filters.add(new CoordinationAndManagementTitleFilter());
        filters.add(new DataFocusedTitleFilter());
        filters.add(new DatabaseTitleFilter());
        filters.add(new EducatorTitleFilter());
        filters.add(new FrontEndTitleFilter());
        filters.add(new HardwareKnowledgeableProgrammingFilter());
        filters.add(new HumanAndCustomerManagementAndSalesTechnologyFilter());
        filters.add(new IntegrationFilter());
        filters.add(new LoAndNoCodeFilter());
        filters.add(new MedicalCoderTitleFilter());
        filters.add(new MobileTitleFilter());
        filters.add(new NotEnglishFilter());
        filters.add(new NotAboveSeniorTitleFilter());
        filters.add(new NotSeniorFilter());
        filters.add(new PhysicalEngineerTitleFilter());
        filters.add(new ProgrammingLanguageTitleFilter(false));
        filters.add(new QATitleFilter());
        filters.add(new SalesTitleFilter());
        filters.add(new SecOpsTitleFilter());
        filters.add(new SupportTitleFilter());
        filters.add(new UnixTitleFilter());
        return filters;
    }

    public static List<JobFilter> getShallowFiltersSkip(String url, Preferences preferences) {
        List<JobFilter> filters = new ArrayList<>();
        if (preferences.isSkipTooManyApplicants()) {
            filters.add(new NumApplicantsFilter());
        }

        filters.add(new YoungAgeFilter());
        return filters;
    }

    public static List<JobFilter> getDeepFilters(String url, Preferences preferences) {
        List<JobFilter> filters = new ArrayList<>();
        filters.add(new AIFilter());
        filters.add(new BettingFilter());
        filters.add(new BigDataFilter());
        filters.add(new BusinessProcessManagementFilter());
        filters.add(new CachingFilter());
        filters.add(new CaseManagementFilter());
        filters.add(new CloudHeavyFilter());
        filters.add(new CommerceFilter());
        filters.add(new ComplexFilter());
        filters.add(new ContentManagementFilter());
        filters.add(new HumanAndCustomerManagementAndSalesTechnologyFilter());
        filters.add(new DefenseFilter());
        filters.add(new HardwareKnowledgeableProgrammingFilter());
        filters.add(new EnterpriseApplicationsFilter());
        filters.add(new FinanceFilter());
        filters.add(new FullstackFilter());
        filters.add(new IdentityManagementFilter());
        filters.add(new InfrastructureSoftwareFilter());
        filters.add(new InsuranceFilter());
        filters.add(new IntegrationFilter());
        filters.add(new JeeFilter());
        filters.add(new LoAndNoCodeFilter());
        filters.add(new MarketingFilter());
        //don't reject just skip
        if(!preferences.isSkipTooManyApplicants()) {
            filters.add(new NumApplicantsFilter());
        }
        filters.add(new NumEmployeesFilter());
        filters.add(new OffshoreFilter());
        filters.add(new OracleTechFilter());
        filters.add(new PerformanceFilter());
        filters.add(new RedHatFilter());
        filters.add(new SearchFilter());
        filters.add(new YearsExperienceFilter());
        return filters;
    }

    public static List<JobFilter> getDeepFiltersSkip(String url, Preferences preferences) {
        List<JobFilter> filters = new ArrayList<>();
        if (preferences.isSkipJobsSourcedFromExternalJobBoard()) {
            filters.add(new SourcedFromJobBoardFilter());
        }
        if (preferences.isSkipTooManyApplicants()) {
            filters.add(new NumApplicantsFilter());
        }
        filters.add(new YoungAgeFilter());
        return filters;
    }

    public static List<JobFilter> getDeepFiltersAlwaysExclude(String url, Preferences preferences) {
        List<JobFilter> filters = new ArrayList<>();
        filters.add(new AppliedFilter());
        filters.add(new BlockChainFilter());
        filters.add(new ClearanceFilter());
        filters.add(new DevSecOpsFilter());
        filters.add(new ERPFilter());
        filters.add(new FreelanceFilter());
        filters.add(new FrontEndFilter());
        filters.add(new GamingFilter());
        filters.add(new BusinessIntelligenceFilter());
        filters.add(new LocalFilter());
        filters.add(new MainframeFilter());
        filters.add(new MSTFilter());
        filters.add(new NotEnglishFilter());
        filters.add(new NotFresherAndInternLevelFilter());
        filters.add(new NotSeniorFilter());
        filters.add(new PSTFilter());
        filters.add(new RemoteFilter());
        filters.add(new StartupFilter());
        filters.add(new WorkLifeBalanceFilter(false));

        //lang
        filters.add(new BasicFilter());
        filters.add(new JavascriptFilter());
        filters.add(new MicrosoftStackFilter());
        filters.add(new PythonFilter());
        return filters;
    }

    public static List<JobFilter> getAlwaysIncludeDeepFilters(String url, Preferences preferences) {
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

    public static List<JobFilter> getAlwaysIncludeShallowFilters(String url, Preferences preferences) {
        List<JobFilter> filters = new ArrayList<>();
        filters.add(new BackendTitleFilter());
        filters.add(new PublicGoodFilter());
        filters.add(new RelaxedEnvironmentFilter());
        filters.add(new ProgrammingLanguageTitleFilter(true));
        filters.add(new WorkLifeBalanceFilter(true));
        return filters;
    }
}
