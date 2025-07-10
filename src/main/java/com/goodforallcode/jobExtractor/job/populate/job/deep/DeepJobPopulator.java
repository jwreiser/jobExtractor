package com.goodforallcode.jobExtractor.job.populate.job.deep;

import com.goodforallcode.jobExtractor.job.populate.field.*;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import org.apache.commons.lang3.math.NumberUtils;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

public interface DeepJobPopulator {
    boolean populateJob(Job job, WebDriver driver, Preferences preferences) throws TimeoutException;

    public static List<FieldPopulator> fieldPopulators = List.of(new AggressiveTimeLinesPopulator(),new AIPopulator(),new ConsultantPopulator(),new ContractPopulator(),
            new CredentialedPopulator(),new FullyRemotePopulator(),new LowEducationFieldPopulator()
            ,new MunicipalityPopulator(),new OnCallPopulator(),new OutsourcingPopulator(),new PartTimePopulator(),
            new PositionCategoryPopulator(),new SeniorityPopulator(),new ShiftFieldPopulator(),new SkillsPopulator(), new StartupPopulator(),new StatePopulator()
            ,new TimeZonePopulator(),new WillTrainPopulator(),new WorklifeBalancePopulator());

    default public Optional<Integer> getMaxExperienceNeeded(String description) {
        int start;
        Optional result = Optional.empty();
        int end = description.indexOf("years' experience");
        if (end < 0) {
            end = description.indexOf("years experience");
        }
        if (end < 0) {
            end = description.indexOf("years of professional experience");
        }
        if (end < 0) {
            end = description.indexOf("years of experience");
        }
        if (end < 0) {
            end = description.indexOf("yrs of experience");
        }
        if (end < 0) {
            end = description.indexOf("yrs of exp");
        }
        if (end < 0) {
            end = description.indexOf("yrs exp");
        }
        if (end > 0) {
            start = description.indexOf(" ", end - 5);
            if (start < end) {
                String base = description.substring(start, end - 1);
                if (base.trim().equals("+")) {
                    start = description.indexOf(" ", end - 8);
                    base = description.substring(start, end - 1);

                }
                String experience = base.replaceAll("\\+", "").replaceAll("<!---->", "").trim();
                if (NumberUtils.isCreatable(experience)) {
                    result = Optional.of((int)Float.parseFloat(experience));
                }
            }

        }
        return result;
    }



}
