package com.goodforallcode.jobExtractor.job.populate;

import com.goodforallcode.jobExtractor.model.Job;
import org.apache.commons.lang3.math.NumberUtils;
import org.openqa.selenium.WebDriver;

import java.util.Optional;
import java.util.concurrent.TimeoutException;

public interface DeepJobPopulator {
    boolean populateJob(Job job, WebDriver driver) throws TimeoutException;

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
                    result = Optional.of(Integer.parseInt(experience));
                }
            }

        }
        return result;
    }

    default public Optional<Integer> getContractDuration(String description) {
        int start;
        Optional result = Optional.empty();
        int end = description.indexOf("+ months");

        if (end > 0) {
            start = description.indexOf(" ", end - 5);
            String base = description.substring(start, end + 1);
            String experience = base.replaceAll("\\+", "")
                    .replaceAll("<!---->", "").replaceAll(":", "").trim();
            if (NumberUtils.isCreatable(experience)) {
                result = Optional.of(Integer.parseInt(experience));
            }
        }
        return result;
    }

}
