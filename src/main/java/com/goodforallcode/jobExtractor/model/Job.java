package com.goodforallcode.jobExtractor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.openqa.selenium.WebElement;

@Data
public class Job {

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

    String url;
    @JsonIgnore
    String location;
    /*
    this is used to click to a job while crawling.
    It may be missing information to access outside of the driver
     */
    @JsonIgnore
    String internalUrl;
    String sourceUrl;
    Integer minYearlySalary;
    Integer maxYearlySalary;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer minHourlySalary;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer maxHourlySalary;
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    Integer level;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Long jobAgeInDays=null;
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
    @JsonIgnore
    boolean contract=false;
    @JsonIgnore
    boolean remote=false;
    @JsonIgnore
    boolean reposted=false;
    @JsonIgnore
    private boolean applied=false;
    @JsonIgnore
    boolean topApplicant=false;
    @JsonIgnore
    int contractMonths;
    @JsonIgnore
    Integer maxExperienceRequired=null;
    @JsonIgnore
    int numEmployees;
    int numApplicants;
    @JsonIgnore
    Float tenure=null;
    String description;
    String title;
    String companyName;
    String industry;
    Integer travelPercent;
    @JsonIgnore
    Company company;

    @Override
    public String toString() {
        return "Job{" +
                "title='" +title + '\'' +
                ",companyName= ='" + companyName + '\'' +
                ", description'" +  description + '\'' +
                '}';
    }
}
