package com.goodforallcode.jobExtractor.model.preferences;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface Preferences {
     boolean isExcludeContractJobs();
     boolean isExcludeApplied();

     Optional<Integer> getIncludedContractMinimumDuration();


     boolean isExcludePoorWorkLifeBalance();
     void setExcludePoorWorkLifeBalance(boolean exclude);

     boolean isExcludePromoted();
     void setExcludePromoted(boolean exclude);
     boolean isExcludeRealEstate();
     void setExcludeRealEstate(boolean exclude);
     boolean isExcludeIdentityManagement();
     void setExcludeIdentityManagement(boolean exclude);
     boolean isExcludeComplexJobs();
     void setExcludeComplexJobs(boolean exclude);

     boolean isExcludeFresher();
     void setExcludeFresher(boolean exclude);

     boolean isExcludeFullStack();
     void setExcludeFullStack(boolean exclude);
     boolean isExcludeSenior();
     void setExcludeSenior(boolean exclude);
     boolean isExcludeAboveSenior();
     void setExcludeAboveSenior(boolean exclude);
     boolean isExcludeBlockchain();
     void setExcludeBlockchain(boolean exclude);
     boolean isExcludeBigData();
     void setExcludeBigData(boolean exclude);
     boolean isExcludeCloudHeavy();
     void setExcludeCloudHeavy(boolean exclude);
     boolean isExcludeConsultant();
     void setExcludeConsultant(boolean exclude);
     boolean isExcludeFrontEndJobs();
     void setExcludeFrontEndJobs(boolean exclude);
     boolean isSkipTooManyApplicants();
     void setSkipTooManyApplicants(boolean skip);
     boolean isSkipUnknownNumberOfApplicants();
     void setSkipUnknownNumberOfApplicants(boolean skip);
     boolean isSkipJobsSourcedFromExternalJobBoard();
     void setSkipJobsSourcedFromExternalJobBoard(boolean skip);
     boolean isExcludePacific();
     boolean isExcludeMountain();
     boolean isExcludeMilitary();
     boolean isExcludeStartups();
     boolean isRemoteOnly();
     int getMaxYearsOfExperience();
     void setMaxYearsOfExperience(int value);
     int getMaxYearsOfExperienceForUnlistedSkill();
     void setMaxYearsOfExperienceForUnlistedSkill(int value);
     int getMinContractRate();
     int getMaxContractRate();
     Integer getMinYearlySalary();
     Integer getMaxLevel();
     void setMaxLevel(Integer level);
     Integer getMaxEmployees();
     void setMaxEmployees(Integer employees);
     Integer getMaxTravelPercentage();
     void setMaxTravelPercentage(Integer employees);
     Integer getMinFortuneRank();
     void setMinFortuneRank(Integer fortuneRank);
     Integer getMaxApplicants();
     void setMaxApplicants(Integer applicants);
     Integer getSkipFirstPages();
     void setSkipFirstPages(Integer value);
     Integer getAmountOfTotalExperience();
     void setAmountOfTotalExperience(Integer experienceRequired);
     Float getDesiredTenure();
     void setDesiredTenure(Float applicants);

     Integer getMaxYearlySalary();
     void setMaxYearlySalary(Integer value);
     void setMinYearlySalary(Integer value);
     Integer getMaxJobAgeInDays();
     void setMaxJobAgeInDays(Integer maxJobAgeInDays);
     Integer getMinJobAgeInDays();
     void setMinJobAgeInDays(Integer minJobAgeInDays);
     List<String> getProgrammingLanguages();
     List<String> getSkills();
     void setSkills(List<String> skills);
     List<String>  getProgrammingFrameworks();
     void setProgrammingLanguages(List<String> languages);
     void setProgrammingFrameworks(List<String> languages);
     List<String> getDesiredClearancePhrases();

     List<String> getLocationPhrases();
     void setLocationPhrases(List<String> phrases);


}
