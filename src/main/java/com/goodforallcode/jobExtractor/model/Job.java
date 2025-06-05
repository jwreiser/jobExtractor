package com.goodforallcode.jobExtractor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.goodforallcode.jobExtractor.filters.ExcludeJobFilter;
import com.goodforallcode.jobExtractor.filters.IncludeOrSkipJobFilter;
import com.goodforallcode.jobExtractor.filters.JobFilter;
import lombok.Data;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class Job {
    public Job() {


    }

    public Job(String title) {
        this.title = title;
    }
    public Job(String title, String companyName) {
        this(title);
        this.companyName = companyName;
    }

    public Job(String title, String companyName,String internalUrl,String url) {
        this(title,companyName);

        this.internalUrl = internalUrl;
        this.url = url;
    }

    public Job(String title, String companyName,String internalUrl,String url,String description) {
        this(title,companyName,internalUrl,url);
        this.description = description;
    }
    //Once the driver is gone the fields can't be marshalled
    @JsonIgnore
    WebElement hideButton;
    @JsonIgnore
    WebElement saveButton;
    @JsonIgnore
    WebElement jobDetailsLink;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String url;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String municipality;//this should not include states

    /*
    this is used to click to a job while crawling.
    It may be missing information to access outside of the driver
     */
    @JsonIgnore
    String internalUrl;
    @JsonIgnore
    String sourceUrl;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    String recruiterClient;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    Boolean consultant;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String seniority;
    boolean aboveSenior=false;
    boolean senior=false;
    boolean midCareer=false;
    boolean noExperience=false;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String state;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer minYearlySalary;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer maxYearlySalary;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer minHourlySalary;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer maxHourlySalary;
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    Integer level;
    @JsonIgnore
    Long jobAgeInDays=null;
    @JsonIgnore
    LocalDate postingDate=null;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    LocalDate searchDate=null;

    @JsonIgnore
    boolean sourcedFromJobBoard=false;
    @JsonIgnore
    boolean requiresActiveSecurityClearance=false;
    @JsonIgnore
    boolean requiresPolygraph=false;
    @JsonIgnore
    boolean promoted=false;
    @JsonIgnore
    boolean hidden=false;
    @JsonIgnore
    boolean easyApply=false;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Boolean contract=null;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Boolean startUp=null;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Boolean willTrain=null;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Boolean onCall=null;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Boolean ai =null;

    @JsonIgnore
    boolean acceptingApplications=true;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Boolean fullyRemote =null;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Boolean credentialed =null;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Boolean lowEducationField =null;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Boolean nightShift =null;
    @JsonIgnore
    boolean reposted=false;
    @JsonIgnore
    private boolean applied=false;
    @JsonIgnore
    boolean topApplicant=false;
    @JsonIgnore
    Integer contractMonths=null;
    @JsonIgnore
    Integer maxExperienceRequired=null;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer minimumNumEmployees;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer maximumNumEmployees;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer numApplicants=null;
    @JsonIgnore
    Float tenure=null;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String reason;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String timeZone;
    @JsonIgnore
    String compressedDescription;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<String> skills=new ArrayList<>();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<String> positionCategories=new ArrayList<>();


    String title;
    String companyName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<String> industries=new ArrayList<>();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    //this is a subset of industries seperated so that we can differentiate it from the sector from company summaries when we search the cache
    String jobIndustry;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer travelPercent;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    CompanySummary company;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    IncludeOrSkipJobFilter includeFilter;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    ExcludeJobFilter excludeFilter;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    JobFilter customExcludeFilter;
    @Override
    public String toString() {
        return "Job{" +
                "title='" +title + '\'' +
                ",companyName= ='" + companyName + '\'' +
                ", description'" +  description + '\'' +
                '}';
    }
}
