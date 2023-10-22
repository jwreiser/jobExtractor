package com.goodforallcode.jobExtractor.model;

import lombok.Data;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebElement;
import org.springframework.data.relational.core.sql.In;

@Data
public class Job {

    public Job(String title) {
        this.title = title;
    }
    public Job(String title, String companyName) {
        this(title);
        this.companyName = companyName;
    }

    public Job(String title, String companyName,String url) {
        this(title,companyName);

        this.url = url;
    }
    public Job(String title, String companyName,String url, String location) {
        this(title,companyName,url);

        this.location = location;
    }

    public Job(String title, String companyName,String url, String location, Integer minYearlySalary,Integer maxYearlySalary, long jobAgeInDays) {
        this(title,companyName,url,location);
        this.minYearlySalary = minYearlySalary;
        this.maxYearlySalary = maxYearlySalary;
        this.jobAgeInDays = jobAgeInDays;
    }

    WebElement hideButton;
    WebElement saveButton;
    WebElement jobDetailsLink;
    String url;
    String location;
    Integer minYearlySalary;
    Integer maxYearlySalary;
    Integer minHourlySalary;
    Integer maxHourlySalary;
    Integer level;
    Long jobAgeInDays=null;
    boolean sourcedFromJobBoard=false;
    boolean requiresActiveSecurityClearance=false;
    boolean requiresPolygraph=false;
    boolean promoted=false;
    boolean hidden=false;
    boolean easyApply=false;
    boolean contract=false;
    boolean remote=false;
    boolean reposted=false;
    private boolean applied=false;
    boolean topApplicant=false;
    int contractMonths;
    Integer maxExperienceRequired=null;
    int numEmployees;
    int numApplicants;
    Float tenure=null;
    String description;
    String title;
    String companyName;
    String industry;

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
