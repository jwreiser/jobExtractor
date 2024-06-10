package com.goodforallcode.jobExtractor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.openqa.selenium.WebDriver;

@Data
@AllArgsConstructor
public class JobInfo {
    int hiddenJobs;
    int skippedJobs;
    int cachedJobs;
    int totalHidden;
    int totalCached;
    int totalSkipped;
    boolean everyJobHiddenCachedOrSkipped;
    boolean skipRemainingJobs;
    boolean staleState;
}
