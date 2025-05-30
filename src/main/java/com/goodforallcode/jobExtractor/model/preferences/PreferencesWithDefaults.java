package com.goodforallcode.jobExtractor.model.preferences;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PreferencesWithDefaults implements Preferences {
    //companies with small fortune ranks are huge
    private Integer maxFortuneRank=760;
    private boolean excludePoorWorkLifeBalance=true;
    private boolean excludeNonRemote=true;

    public boolean excludeContractJobs=true;
    public boolean excludeConsultant=true;
    public  boolean excludeComplexJobs=false;
    public  boolean remoteOnly=true;
    public  String state="";
    public  String seniority="";
    public boolean excludePacific=true;
    public boolean excludeMountain=true;

    List<String> desiredClearancePhrases=List.of("mbi","public trust","citizen");
    public int maxYearsOfExperience=6;
    public int maxYearsOfExperienceForUnlistedSkill=2;

    public List<String>skills= new ArrayList<>();
    int minHourlyRate=50;
    int maxHourlyRate=80;
    Integer minYearlySalary=80_000;
    Integer maxYearlySalary=170_000;
    Integer maxTravelPercentage=10;
    Integer maxEmployees=null;
    Integer maxApplicants=300;
    Float desiredTenure=2.6F;
    Integer maxJobAgeInDays=34;
    Integer minJobAgeInDays=0;

    boolean excludeWeekends=true;
    boolean excludeOnCall=true;
    boolean excludeAggressiveTimelines=true;
    boolean skipTooManyApplicants=false;
    boolean skipUnknownNumberOfApplicants=false;
    Integer amountOfTotalExperience =6;
    Integer maxLevel=3;
    Integer skipFirstPages=null;
    boolean excludeStartups=true;

    boolean excludePromoted=false;
    List<String> badLocationPhrases =new ArrayList<>();
    List<String> timezones =new ArrayList<>();
    List<String> missingSkills=new ArrayList<>();
    List<String> acceptablePositionCategories=new ArrayList<>();
    List<String> programmingLanguages=new ArrayList<>();

    List<String> programmingFrameworks=new ArrayList<>();


}
