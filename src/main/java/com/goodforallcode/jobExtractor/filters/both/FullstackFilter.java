package com.goodforallcode.jobExtractor.filters.both;

import com.goodforallcode.jobExtractor.extractor.Extractor;
import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class FullstackFilter implements JobFilter {
    List<String> jobTitlePhrases=List.of(
"Full stack engineer", "Full stack developer", "Full stack Java developer. ", "Full stack Java engineer",
            "Full Stack Software Developer","Full Stack Java Developer",
            "Fullstack Software Engineer","Full Stack Software Engineer"
            );

    List<String> descriptionKeywords=List.of("fullstack","full stack","full-stack");
    @Override
    public boolean include(Preferences preferences, Job job) {
        if(!preferences.isExcludeFullStack()){
            return true;
        }
        String title =job.getTitle().toLowerCase();

        if(preferences.getTitlePhrases().stream().anyMatch(t -> title.contains(t.toLowerCase()))){
            return true;
        }
        //if this is a job title we are not qualified for
        if(jobTitlePhrases.stream().anyMatch(t->title.contains(t.toLowerCase()))){
            System.err.println("full stack job title->reject: "+job);
            return false;
        }
        if(job.getDescription()!=null){
            final String descripton=job.getDescription().toLowerCase();
            if(descriptionKeywords.stream().anyMatch(k->descripton.contains(k.toLowerCase()))){
                System.err.println("full stack job description->reject: "+job);
                return false;
            }
        }
        return true;
    }

    private boolean missingRequiredSkill(String skill,String jobTitle,List<String> qualifiedSkills){
        boolean missing=false;
        if(jobTitle.contains(skill)
                /* if there is a skill in the title we don't have*/
                && !qualifiedSkills.stream().anyMatch(qs->qs.toLowerCase().equals(skill))){
            missing=true;
        }
        return missing;
    }
}