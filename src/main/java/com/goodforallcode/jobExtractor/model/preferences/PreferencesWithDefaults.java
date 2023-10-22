package com.goodforallcode.jobExtractor.model.preferences;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class PreferencesWithDefaults implements Preferences {
    Integer minFortuneRank=null;
    boolean  excludeRecruitersThatDontIncludeClientDetails=true;
    boolean excludePoorWorkLifeBalance=true;

    public boolean excludeContractJobs=true;
    public boolean excludeConsultantCompanies=false;
    Optional<Integer> includedContractMinimumDuration= Optional.of(12);
    public boolean excludeProfitFocusedCompanies=false;
    public float maxRatioOfMissingSkills=0.3F;
    public  boolean excludeComplexJobs=false;
    public  boolean remoteOnly=true;
    public boolean excludePacific=true;
    public boolean excludeMountain=true;
    public boolean excludeFrontEndJobs=false;

    List<String> desiredClearancePhrases=List.of("mbi","public trust","citizen");
     int maxYearsOfExperience=6;
     boolean excludeMilitary=true;
    int minContractRate=50;
    int maxContractRate=80;
    Integer minYearlySalary=80_000;
    Integer maxYearlySalary=170_000;
    Integer maxEmployees=null;
    Integer maxApplicants=300;
    Float desiredTenure=2.2F;
    Integer maxJobAgeInDays=34;
    Integer minJobAgeInDays=null;
    boolean excludeFresher=false;
    boolean excludeSenior=false;
    boolean excludeAboveSenior=false;
    boolean excludeBigData=false;
    boolean excludeCloudHeavy=false;

    boolean excludeFullStack=false;
    boolean excludeIdentityManagement=false;
    boolean excludeBlockchain=false;
    boolean skipTooManyApplicants=false;
    boolean skipJobsSourcedFromExternalJobBoard=false;
    Integer amountOfTotalExperience =6;
    Integer maxLevel=3;
    Integer skipFirstPages=null;
    boolean excludeStartups=true;
    boolean excludePromoted=false;
    boolean excludeRealEstate=false;
    boolean excludeApplied=true;

     List<String> qualifiedSkillPhrases=new ArrayList<>();
    List<String> titlePhrases=new ArrayList<>();
    List<String> locationPhrases=new ArrayList<>();
    List<String> programmingLanguages=new ArrayList<>();


}
