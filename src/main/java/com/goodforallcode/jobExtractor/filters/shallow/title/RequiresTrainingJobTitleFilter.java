package com.goodforallcode.jobExtractor.filters.shallow.title;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class RequiresTrainingJobTitleFilter implements JobFilter {
    List<String> jobTitlePhrases=List.of(
"Teacher","Instructor","Physician","Surgeon","Researcher","Dental","Dentist","Veterinarian","Psychologist",
            "Supervisor","Veterinary","Pathologist","Ophthalmic","Optometric","Optometrist","Optician","Orthopedic",
            "Ophthalmologist","Radiograph","Radiolog","Physiatrist","Nurse Practitioner","Physicist","Phlebotomist",
            "Cardiac","Cardiol","CNA","Physical Therapist"
    );


    @Override
    public boolean include(Preferences preferences, Job job) {
        String title =job.getTitle().toLowerCase();

         if(jobTitlePhrases.stream().anyMatch(t->title.contains(t.toLowerCase()))){
            System.err.println(this.getClass()+" title->reject: "+job);
            return false;
        }
        return true;
    }


}
