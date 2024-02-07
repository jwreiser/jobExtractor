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
    boolean excludePoorWorkLifeBalance=true;

    public boolean excludeContractJobs=true;
    public boolean excludeConsultant=false;
    Optional<Integer> includedContractMinimumDuration= Optional.of(12);
    public  boolean excludeComplexJobs=false;
    public  boolean remoteOnly=true;
    public boolean excludePacific=true;
    public boolean excludeMountain=true;
    public boolean excludeFrontEndJobs=false;

    List<String> desiredClearancePhrases=List.of("mbi","public trust","citizen");
    public int maxYearsOfExperience=6;
    public int maxYearsOfExperienceForUnlistedSkill=2;

    public List<String>skills= new ArrayList<>();
     boolean excludeMilitary=true;
    int minContractRate=50;
    int maxContractRate=80;
    Integer minYearlySalary=80_000;
    Integer maxYearlySalary=170_000;
    Integer maxTravelPercentage=10;
    Integer maxEmployees=null;
    Integer maxApplicants=300;
    Float desiredTenure=2.6F;
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
    boolean skipUnknownNumberOfApplicants=false;
    boolean skipJobsSourcedFromExternalJobBoard=false;
    Integer amountOfTotalExperience =6;
    Integer maxLevel=3;
    Integer skipFirstPages=null;
    boolean excludeStartups=true;
    boolean excludePromoted=false;
    boolean excludeRealEstate=false;
    boolean excludeApplied=true;
    List<String> locationPhrases=new ArrayList<>();
    List<String> programmingLanguages=new ArrayList<>();

    List<String> programmingFrameworks=new ArrayList<>();


}
