package com.goodforallcode.jobExtractor.job.populate.field;
import com.goodforallcode.jobExtractor.filters.ExcludeJobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.ArrayList;
import java.util.List;

public class SkillsPopulator implements FieldPopulator{

    public void populateField(Job job, Preferences preferences) {
        List<String> skills = List.of(
                "HTML", "CSS",

                "C++", "C#","Dot-NET",".NET",

                "JavaScript", "React", "Angular", "Vue.js", "Node.js","VueJS", "TypeScript","React Native","NextJS",
                "Spring Cloud","Spring Framework", "Spring Boot","Spring MVC","Spring Batch",
                "Python","PHP","Django","Flask",
                "NoSQL","SQL","MongoDB","Redis","MySQL","PostgreSQL",

                "Data Science",
                "Java",
                "Go","GraphQL",
                "Kotlin","Laravel",
                "Microservices",
                "Machine Learning",
                "RESTful APIs",  "Ruby",
                "Swift"
                );
        for(String skill : skills) {
            if (job.getDescription()!=null){
                String description=job.getDescription().toLowerCase().replace("/"," ");
                if(description.contains(" "+skill.toLowerCase()+" ")) {
                    job.getSkills().add(skill);
                }
            }else if (job.getTitle().toLowerCase().contains(","+skill.toLowerCase()+" ")) {
                job.getSkills().add(skill);
            }else if (job.getTitle().toLowerCase().contains(","+skill.toLowerCase()+",")) {
                job.getSkills().add(skill);
            }else if (job.getTitle().toLowerCase().contains(", "+skill.toLowerCase()+",")) {
                job.getSkills().add(skill);
            }else if (job.getTitle().toLowerCase().contains(", "+skill.toLowerCase()+" ")) {
                job.getSkills().add(skill);
            }
        }

    }
    }

