package com.goodforallcode.jobExtractor.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CompanySummary {
    String name;
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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer minimumNumEmployees;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer maximumNumEmployees;
    String jobIndustry;
    public CompanySummary(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFoundingYear() {
        return foundingYear;
    }

    public void setFoundingYear(Integer foundingYear) {
        this.foundingYear = foundingYear;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getEmployeeRangeLow() {
        return employeeRangeLow;
    }

    public void setEmployeeRangeLow(Integer employeeRangeLow) {
        this.employeeRangeLow = employeeRangeLow;
    }

    public Integer getEmployeeRangeHigh() {
        return employeeRangeHigh;
    }

    public void setEmployeeRangeHigh(Integer employeeRangeHigh) {
        this.employeeRangeHigh = employeeRangeHigh;
    }

    public Integer getFortuneRanking() {
        return fortuneRanking;
    }

    public void setFortuneRanking(Integer fortuneRanking) {
        this.fortuneRanking = fortuneRanking;
    }

    public Boolean getRecentLayoffs() {
        return recentLayoffs;
    }

    public void setRecentLayoffs(Boolean recentLayoffs) {
        this.recentLayoffs = recentLayoffs;
    }

    public String getExampleAcquiredCompany() {
        return exampleAcquiredCompany;
    }

    public Boolean getAcquisitions() {
        return acquisitions;
    }

    public void setAcquisitions(Boolean acquisitions) {
        this.acquisitions = acquisitions;
    }

    public Boolean getJobSecurity() {
        return jobSecurity;
    }

    public void setJobSecurity(Boolean jobSecurity) {
        this.jobSecurity = jobSecurity;
    }

    public Boolean getOffshores() {
        return offshores;
    }

    public void setOffshores(Boolean offshores) {
        this.offshores = offshores;
    }

    public Boolean getSoftwareEngineerNightOrWeekendHours() {
        return softwareEngineerNightOrWeekendHours;
    }

    public void setSoftwareEngineerNightOrWeekendHours(Boolean softwareEngineerNightOrWeekendHours) {
        this.softwareEngineerNightOrWeekendHours = softwareEngineerNightOrWeekendHours;
    }

    public Boolean getSoftwareEngineerHighOvertime() {
        return softwareEngineerHighOvertime;
    }

    public void setSoftwareEngineerHighOvertime(Boolean softwareEngineerHighOvertime) {
        this.softwareEngineerHighOvertime = softwareEngineerHighOvertime;
    }

    public Boolean getSoftwareEngineerExternalContractors() {
        return softwareEngineerExternalContractors;
    }

    public void setSoftwareEngineerExternalContractors(Boolean softwareEngineerExternalContractors) {
        this.softwareEngineerExternalContractors = softwareEngineerExternalContractors;
    }

    public Boolean getSoftwareEngineerAfterHoursSupport() {
        return softwareEngineerAfterHoursSupport;
    }

    public void setSoftwareEngineerAfterHoursSupport(Boolean softwareEngineerAfterHoursSupport) {
        this.softwareEngineerAfterHoursSupport = softwareEngineerAfterHoursSupport;
    }

    public Boolean getLegacySoftware() {
        return legacySoftware;
    }

    public void setLegacySoftware(Boolean legacySoftware) {
        this.legacySoftware = legacySoftware;
    }

    public Boolean getNegativeManagementCompetenceTone() {
        return negativeManagementCompetenceTone;
    }

    public void setNegativeManagementCompetenceTone(Boolean negativeManagementCompetenceTone) {
        this.negativeManagementCompetenceTone = negativeManagementCompetenceTone;
    }

    public Boolean getPositiveEmployeeToneTowardsManagement() {
        return positiveEmployeeToneTowardsManagement;
    }

    public void setPositiveEmployeeToneTowardsManagement(Boolean positiveEmployeeToneTowardsManagement) {
        this.positiveEmployeeToneTowardsManagement = positiveEmployeeToneTowardsManagement;
    }

    public Boolean getTopDownManagement() {
        return topDownManagement;
    }

    public void setTopDownManagement(Boolean topDownManagement) {
        this.topDownManagement = topDownManagement;
    }

    public Boolean getWorkLifeBalance() {
        return workLifeBalance;
    }

    public void setWorkLifeBalance(Boolean workLifeBalance) {
        this.workLifeBalance = workLifeBalance;
    }

    public Boolean getFastPaced() {
        return fastPaced;
    }

    public void setFastPaced(Boolean fastPaced) {
        this.fastPaced = fastPaced;
    }

    public Boolean getDefense() {
        return defense;
    }

    public void setDefense(Boolean defense) {
        this.defense = defense;
    }

    public Boolean getStress() {
        return stress;
    }

    public void setStress(Boolean stress) {
        this.stress = stress;
    }

    public Boolean getConsulting() {
        return consulting;
    }

    public void setConsulting(Boolean consulting) {
        this.consulting = consulting;
    }

    public Boolean getContractor() {
        return contractor;
    }

    public void setContractor(Boolean contractor) {
        this.contractor = contractor;
    }

    public Boolean getPeopleFocused() {
        return peopleFocused;
    }

    public void setPeopleFocused(Boolean peopleFocused) {
        this.peopleFocused = peopleFocused;
    }

    public Boolean getPublicGood() {
        return publicGood;
    }

    public void setPublicGood(Boolean publicGood) {
        this.publicGood = publicGood;
    }



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

    public void setMinimumNumEmployees(Integer minimumNumEmployees) {
        this.minimumNumEmployees = minimumNumEmployees;
    }

    public void setMaximumNumEmployees(Integer maximumNumEmployees) {
        this.maximumNumEmployees = maximumNumEmployees;
    }

    public void setJobIndustry(String jobIndustry) {
        this.jobIndustry = jobIndustry;
    }

    public Integer getMinimumNumEmployees() {
        return minimumNumEmployees;
    }

    public Integer getMaximumNumEmployees() {
        return maximumNumEmployees;
    }

    public String getJobIndustry() {
        return jobIndustry;
    }

}
