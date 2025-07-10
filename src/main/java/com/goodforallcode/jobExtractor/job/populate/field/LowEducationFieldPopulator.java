package com.goodforallcode.jobExtractor.job.populate.field;

import com.goodforallcode.jobExtractor.filters.ExcludeJobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class LowEducationFieldPopulator implements FieldPopulator {
    public void populateField(Job job, Preferences preferences) {


        ExcludeJobFilter filter = ExcludeJobFilter.build("LowEducationField")
                .titlePhrases(List.of(
                        "Mailroom","Packer", "Packager", "Picker",
                        "Clerk", "Laborer", "Attendant","Scheduler","Scheduling","Clerical",
                        "Groomer",  "Valet","Dispatcher","Order Entry",
                        "Manufacturing", "Assembler","Parts Counterperson","Parts Associate"
                        ,  "Lifeguard", "Installer", "Installation","Barber"
                        , "Operator", "Dock Worker", "Data Entry"))
                .matchingCompanies(List.of("The Container Store","Barnes & Noble", "Hobby Lobby","7-Eleven", "Dollar Tree", "Dollar General", "Family Dollar",
                        "Federal Express", "FedEx", "Fed Ex", "Fedex", "Fed Ex"))
                .descriptionPhrases(List.of("plus tips"));

        if (filter.exclude(job) != null) {
            job.setLowEducationField(true);
        }

    }
}
