package com.goodforallcode.jobExtractor.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CompanySummary {
    String name;

    public CompanySummary(String name) {
        this.name = name;
    }

    @JsonProperty("founding-year")
    private Integer foundingYear;
    private String sector;

    @JsonProperty("company_website")
    private String companyWebsite;
    @JsonProperty("location")
    private String location;
    @JsonProperty ("employee-range-low")
    private Integer employeeRangeLow;
    @JsonProperty ("employee-range-high")
    private Integer employeeRangeHigh;
    @JsonProperty ("fortune-ranking")
    private Integer fortuneRanking;
    @JsonProperty ("recent-layoffs")
    private Boolean recentLayoffs;

    public void setExampleAcquiredCompany(String exampleAcquiredCompany) {
        if(exampleAcquiredCompany!=null) {
            this.exampleAcquiredCompany = exampleAcquiredCompany;
            setAcquisitions(true);
        }
    }

    @JsonProperty ("example_acquired_company")
    private String exampleAcquiredCompany;
    @JsonProperty ("acquisitions")
    private Boolean acquisitions;

    @JsonProperty ("job_security")
    @JsonAlias("job-security")
    private Boolean jobSecurity;
    private Boolean offshores;
    //software engineer
    @JsonProperty ("software-engineer-night-or-weekend-hours")
    private Boolean softwareEngineerNightOrWeekendHours;
    @JsonProperty ("software-engineer-high-overtime")
    private Boolean softwareEngineerHighOvertime;
    @JsonProperty ("software-engineer-external-contractors")
    private Boolean softwareEngineerExternalContractors;
    @JsonProperty ("software-engineer-after-hours-support")
    private Boolean softwareEngineerAfterHoursSupport;


    @JsonProperty ("software-engineer-legacy_technology")
    private Boolean legacySoftware;
    //management
    @JsonProperty ("negative_management_competence_and_execution_tone")
    @JsonAlias("negative-management-competence-tone")
    private Boolean negativeManagementCompetenceTone;
    @JsonProperty ("positive_employee_tone_towards_management")
    @JsonAlias({"positive-employee-tone-towards-management","positive_employee-tone_towards_management"})
    private Boolean positiveEmployeeToneTowardsManagement;
    @JsonProperty ("top-down-management")
    private Boolean topDownManagement;
    @JsonProperty ("work-life-balance")
    private Boolean workLifeBalance;
    @JsonProperty ("fast-paced")
    private Boolean fastPaced;
    private Boolean defense;
    private Boolean stress;
    private Boolean consulting;

    @JsonProperty ("operates_as_a_contractor")
    @JsonAlias({"contractor"})
    private Boolean contractor;

    @JsonProperty ("people-focused")
    private Boolean peopleFocused;
    @JsonProperty ("public-good")
    private Boolean publicGood;
}
