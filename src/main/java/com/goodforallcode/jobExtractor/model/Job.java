package com.goodforallcode.jobExtractor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
    String location;
    /*
    this is used to click to a job while crawling.
    It may be missing information to access outside of the driver
     */
    @JsonIgnore
    String internalUrl;
    String sourceUrl;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    String recruiterClient;
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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Long jobAgeInDays=null;
    @JsonInclude(JsonInclude.Include.NON_NULL)
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
    boolean shallowExclude=false;
    boolean easyApply=false;
    boolean contract=false;
    @JsonIgnore
    boolean acceptingApplications=true;
    @JsonIgnore
    boolean remote=false;
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
    String compressedDescription;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<String> skills=new ArrayList<>();
    String title;
    String companyName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<String> industries=new ArrayList<>();
    //this is a subset of industries seperated so that we can differentiate it from the sector from company summaries when we search the cache
    String jobIndustry;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer travelPercent;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    CompanySummary company;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<JobFilter> includeFilters;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    JobFilter excludeFilter;
    @Override
    public String toString() {
        return "Job{" +
                "title='" +title + '\'' +
                ",companyName= ='" + companyName + '\'' +
                ", description'" +  description + '\'' +
                '}';
    }
}
