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

                "JavaScript", "React", "Angular", "Vue.js", "Node.js","VueJS", "TypeScript","React Native","NextJS","Node",
                "Spring Cloud","Spring Framework", "Spring Boot","Spring MVC","Spring Batch",
                "Python","PHP","Django","Flask",
                "NoSQL","SQL","MongoDB","Redis","MySQL","PostgreSQL",
                "Unix", "OpenShift", "RHEL", "Linux",
                "AWS", "Azure", "GCP", "Google Cloud Platform",
                 "Tailwind CSS", "Bootstrap", "Material UI",

                "Kubernetes", "Docker",
                "Data Science",
                "Java",
                "Golang",
                "GraphQL",
                "Kotlin","Laravel",
                "Microservices",
                "Machine Learning",
                "RESTful APIs",  "Ruby",
                "Swift","OneStream","Hogan","Elixir"
                );
        for(String skill : skills) {
            if (job.getDescription()!=null){
                String description=job.getDescription().toLowerCase().replace("/"," ");
                if(valueContainsSkill(description, skill)){
                    job.getSkills().add(skill);
                    continue;
                }
            }else if(valueContainsSkill(job.getTitle().toLowerCase(), skill)){
                job.getSkills().add(skill);
            }
        }

    }


    /**
     *
     * @param value this is the job title,description,etc.
     * @param skill
     */
    private static boolean valueContainsSkill(String value, String skill) {
        if (value.contains(","+ skill.toLowerCase()+" ")) {
            return true;
        }else if (value.contains(","+ skill.toLowerCase()+",")) {
            return true;
        }else if (value.contains(", "+ skill.toLowerCase()+",")) {
            return true;
        }else if (value.contains(", "+ skill.toLowerCase()+" ")) {
            return true;
        }else if (value.contains(" "+ skill.toLowerCase()+" ")) {
            return true;
        }else if (value.contains(" "+ skill.toLowerCase()+",")) {
            return true;
        }
        return false;
    }
}

