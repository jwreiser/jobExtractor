package com.goodforallcode.jobExtractor.model.preferences;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class PreferencesWithDefaults implements Preferences {
    //companies with small fortune ranks are huge
    private Integer maxFortuneRank=760;
    private boolean excludePoorWorkLifeBalance=true;
    private boolean excludeNonRemote=true;

    public boolean excludeContractJobs=true;
    public boolean excludeConsultant=true;
    private Optional<Integer> includedContractMinimumDuration= Optional.of(12);
    public  boolean excludeComplexJobs=false;
    public  boolean remoteOnly=true;
    public boolean excludePacific=true;
    public boolean excludeMountain=true;
    public boolean excludeFrontEndJobs=false;

    private List<String> desiredClearancePhrases=List.of("mbi","public trust","citizen");
    public int maxYearsOfExperience=6;
    public int maxYearsOfExperienceForUnlistedSkill=2;

    public List<String>skills= new ArrayList<>();
    private boolean excludeMilitary=true;
    private int minHourlyRate=50;
    private int maxHourlyRate=80;
    private Integer minYearlySalary=80_000;
    private Integer maxYearlySalary=170_000;
    private Integer maxTravelPercentage=10;
    private Integer maxEmployees=null;
    private Integer maxApplicants=300;
    private Float desiredTenure=2.6F;
    private Integer maxJobAgeInDays=34;
    private Integer minJobAgeInDays=null;
    private boolean excludeFresher=false;
    private boolean excludeSenior=false;
    private boolean excludeAboveSenior=false;
    private boolean excludeBigData=false;
    private boolean excludeCloudHeavy=false;

    private boolean excludeFullStack=false;
    private boolean excludeIdentityManagement=false;
    private boolean excludeBlockchain=false;
    private boolean skipTooManyApplicants=false;
    private boolean skipUnknownNumberOfApplicants=false;
    private boolean skipJobsSourcedFromExternalJobBoard=false;
    private Integer amountOfTotalExperience =6;
    private Integer maxLevel=3;
    Integer skipFirstPages=null;
    boolean excludeStartups=true;
    boolean excludePromoted=false;
    boolean excludeRealEstate=false;
    boolean excludeApplied=true;
    List<String> locationPhrases=new ArrayList<>();
    List<String> missingSkills=new ArrayList<>();
    List<String> programmingLanguages=new ArrayList<>();

    List<String> programmingFrameworks=new ArrayList<>();


}
