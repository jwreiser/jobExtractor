package com.goodforallcode.jobExtractor.filters.shallow.title;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class HighSchoolTypeJobTitleFilter implements JobFilter {
    List<String> jobTitlePhrases=List.of(
"Cashier","Receptionist","Concierge","Porter","Customer Service","Office Assistant","Customer Specialist","Housekeeping","Housekeeper",
            "Custodian","Janitor","Maintenance","Maintenance Worker","Dishwasher","Food Service","Food Runner","Barista","Waiter","Waitress",
            "Administrative Assistant","Hairstylist","Warehouse","Customer Success","Customer Support","Front Desk",
            "Clerk","Laborer","Attendant","Call Center","Delivery","Driver","Groomer","Membership Experience","Member Experience",
            "Froster","Fish Market","Support Associate","Construction","Manufacturing","Assembler"
    );

    @Override
    public boolean include(Preferences preferences, Job job) {
        String title =job.getTitle().toLowerCase();

         if(jobTitlePhrases.stream().anyMatch(t->title.contains(t.toLowerCase()))){
            System.err.println("Automation job title->reject: "+job);
            return false;
        }
        return true;
    }


}
