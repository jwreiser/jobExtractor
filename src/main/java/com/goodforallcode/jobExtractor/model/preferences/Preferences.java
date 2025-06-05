package com.goodforallcode.jobExtractor.model.preferences;

import java.util.List;

public interface Preferences {
     boolean isExcludeContractJobs();

     boolean isExcludeNonRemote();
     void setExcludeNonRemote(boolean exclude);
     boolean isExcludeHiddenJobs();
     void setExcludeHiddenJobs(boolean exclude);

     boolean isExcludeWeekends();
     void setExcludeWeekends(boolean exclude);
     boolean isExcludeOnCall();
     void setExcludeOnCall(boolean exclude);
     boolean isExcludeAggressiveTimelines();
     void setExcludeAggressiveTimelines(boolean exclude);

     boolean isExcludePromoted();
     void setExcludePromoted(boolean exclude);
     boolean isExcludeComplexJobs();
     void setExcludeComplexJobs(boolean exclude);


     boolean isExcludeConsultant();
     void setExcludeConsultant(boolean exclude);
     boolean isSkipTooManyApplicants();
     void setSkipTooManyApplicants(boolean skip);
     boolean isSkipUnknownNumberOfApplicants();
     void setSkipUnknownNumberOfApplicants(boolean skip);
     boolean isExcludeStartups();
     boolean isRemoteOnly();
     int getMaxYearsOfExperience();
     void setMaxYearsOfExperience(int value);
     int getMaxYearsOfExperienceForUnlistedSkill();
     void setMaxYearsOfExperienceForUnlistedSkill(int value);
     int getMinHourlyRate();
     int getMaxHourlyRate();
     Integer getMinYearlySalary();
     Integer getMaxLevel();
     void setMaxLevel(Integer level);
     Integer getMaxEmployees();
     void setMaxEmployees(Integer employees);
     Integer getMaxTravelPercentage();
     void setMaxTravelPercentage(Integer employees);
     Integer getMaxFortuneRank();
     void setMaxFortuneRank(Integer fortuneRank);
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

     List<String> getSkills();
     void setSkills(List<String> skills);

     List<String> getAcceptablePositionCategories();
     void setAcceptablePositionCategories(List<String> categories);

     List<String>  getProgrammingFrameworks();
     void setProgrammingFrameworks(List<String> languages);
     List<String> getDesiredClearancePhrases();

     List<String> getBadLocationPhrases();
     void setBadLocationPhrases(List<String> phrases);
     List<String> getTimezones();
     void setTimezones(List<String> timezones);

     public String getState();
     public void setState(String state);
     public String getSeniority();
     public void setSeniority(String value);

}
