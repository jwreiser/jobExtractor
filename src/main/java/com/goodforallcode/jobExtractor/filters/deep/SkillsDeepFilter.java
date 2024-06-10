package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.filters.both.ProgrammingLanguageFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class SkillsDeepFilter implements JobFilter {

    //these phrasings will show up in skills section only not title or description
    List<String> programmingLanguagesSkillFormat=List.of("C (Programming Language)","Python (Programming Language)","C#",".NET Framework", "ASP.NET Core"
    ,"ASP.NET","jQuery","Doctrine (PHP)","Laravel","Node.js","Angular","React","AngularJS","Angular 2","Angular 4","Angular 5","Angular 6","Angular 7","Angular 8"
    ,"TypeScript");
    List<String> importantNonLanguageSpecificSkills =List.of("Front-End Development","Cybersecurity","Tableau","Snowflake",
            "Android","Android Development","Actimize","Dreamweaver","Front-end Coding","Cloud-Native Applications");

    @Override
    public boolean include(Preferences preferences, Job job) {
        if(job.getSkills()==null||job.getSkills().isEmpty()){
            return true;
        }

        for(String skillToSplit: job.getSkills()){
            List<String>skillsToSplitAgain=List.of(skillToSplit.split(","));
            for(String skillToSplitAgain:skillsToSplitAgain) {
                List<String>skills=List.of(skillToSplitAgain.split("and"));
                for(String skill:skills) {
                    skill=skill.trim();
                    if (isNecessarySkill(skill) && !preferences.getSkills().contains(skill)) {
                        return false;
                    }
                }

            }
        }
        return true;
    }

    /**
     * I am trying to weed out non-important/critical skills. For example, object oriented programming is not going to limit
     * applicants much
     * @param skill
     * @return
     */
    public boolean isNecessarySkill(String skill) {
        return ProgrammingLanguageFilter.languages.contains(skill)||ProgrammingLanguageFilter.titleOnlyLanguages.contains(skill)
                ||programmingLanguagesSkillFormat.contains(skill)||importantNonLanguageSpecificSkills.contains(skill);
    }
}
