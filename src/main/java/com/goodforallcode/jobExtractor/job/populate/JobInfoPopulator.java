package com.goodforallcode.jobExtractor.job.populate;

import com.goodforallcode.jobExtractor.extractor.ExcludeJobResults;
import com.goodforallcode.jobExtractor.extractor.Extractor;
import com.goodforallcode.jobExtractor.filters.FilterFactory;
import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.job.includeexclude.IncludeOrExcluder;
import com.goodforallcode.jobExtractor.model.CompanySummary;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.JobInfo;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CacheUtil;
import com.goodforallcode.jobExtractor.util.RESTUtil;
import org.jsoup.nodes.Element;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;



@Component
public class JobInfoPopulator {
    @Autowired
    CacheUtil cacheUtil;
    static List<JobFilter> alwaysExcludeShallowFilters = FilterFactory.getShallowFiltersAlwaysExclude();
    static List<JobFilter> shallowSkipFilters = FilterFactory.getShallowFiltersSkip();

    static DeepJobPopulator deepJobPopulator = new LinkedInDeepJobPopulator();
    @Autowired
    IncludeOrExcluder includeOrExcluder;

    public JobInfo populateJobInfo(List<Job> acceptedJobs, List<Job> rejectedJobs, List<Job> deepCachedJobs, List<Job> shallowCachedJobs,
                                   Element item, int numAttempts, JobInfo currentInfo, int numJobs, String url, WebDriver driver,
                                   Preferences preferences,ShallowJobPopulator shallowPopulator){
        int hiddenJobs = currentInfo.getHiddenJobs(), skippedJobs = currentInfo.getSkippedJobs(), cachedJobs = currentInfo.getCachedJobs();
        int totalHidden=currentInfo.getTotalHidden(),totalCached=currentInfo.getTotalCached(),totalSkipped=currentInfo.getTotalSkipped();
        boolean everyJobHiddenCachedOrSkipped = currentInfo.isEveryJobHiddenCachedOrSkipped();

        try {

            final Job job = shallowPopulator.populateJob(item, driver);

            if (job == null) {
                return currentInfo;
            }

            if (job.isHidden()) {
                hiddenJobs++;
                totalHidden++;
                if (hiddenJobs + skippedJobs == numJobs) {
                    everyJobHiddenCachedOrSkipped = true;
                }
                return new JobInfo(hiddenJobs, skippedJobs, cachedJobs, totalHidden, totalCached,totalSkipped,everyJobHiddenCachedOrSkipped,false,false);
            }
            job.setSourceUrl(url);
            if (cacheUtil.containsJob(job,false)) {
                cachedJobs++;
                shallowCachedJobs.add(job);
                totalCached++;
                includeOrExcluder.excludeJob(driver, job);
                return new JobInfo(hiddenJobs, skippedJobs, cachedJobs, totalHidden, totalCached,totalSkipped,everyJobHiddenCachedOrSkipped,false,false);
            }


            if (shallowSkipFilters.stream().anyMatch(f -> !f.include(preferences, job))) {
                        /*we don't want to cache acceptedJobs that are too new otherwise we may
                        not see them again in other future searches
                         */
                skippedJobs++;
                totalSkipped++;
                if (hiddenJobs + cachedJobs + skippedJobs == numJobs) {
                    everyJobHiddenCachedOrSkipped = true;
                }
                return new JobInfo(hiddenJobs, skippedJobs, cachedJobs, totalHidden, totalCached,totalSkipped,everyJobHiddenCachedOrSkipped,false,false);
            }

            Optional<JobFilter> firstShallowExcludeFilter = alwaysExcludeShallowFilters.stream().filter(f -> !f.include(preferences, job)).findFirst();
            if (firstShallowExcludeFilter.isPresent()) {
                job.setExcludeFilter(firstShallowExcludeFilter.get());
                job.setShallowExclude(true);
                cacheUtil.addJobToCache(job, false);
                includeOrExcluder.excludeJob(driver, job);
                rejectedJobs.add(job);
                return new JobInfo(hiddenJobs, skippedJobs, cachedJobs, totalHidden, totalCached,totalSkipped,everyJobHiddenCachedOrSkipped,false,false);
            }
            deepLoadJob(job, driver);
            CompanySummary summary = CacheUtil.getCompanySummary(job);
            if (summary != null) {
                ExcludeJobResults excludeJobResultsFromCompany = includeOrExcluder.getExcludeJobResultsFromCompany(job, preferences, driver,  summary, true, null);
                if (excludeJobResultsFromCompany != null) {
                    handleNonSkipJobResults(acceptedJobs, rejectedJobs, driver, job, excludeJobResultsFromCompany);
                    //if we are not null we are either including or excluding so we can move on to next job
                    return new JobInfo(hiddenJobs, skippedJobs, cachedJobs, totalHidden, totalCached,totalSkipped,everyJobHiddenCachedOrSkipped,false,false);
                }
            }


            if (cacheUtil.containsJob(job,true)) {
                cachedJobs++;
                deepCachedJobs.add(job);
                totalCached++;
                includeOrExcluder.excludeJob(driver, job);
                return new JobInfo(hiddenJobs, skippedJobs, cachedJobs, totalHidden, totalCached,totalSkipped,everyJobHiddenCachedOrSkipped,false,false);
            }

            ExcludeJobResults excludeJobResults = includeOrExcluder.excludeJob(job, preferences, driver);
            handleNonSkipJobResults(acceptedJobs, rejectedJobs, driver, job, excludeJobResults);

            if (excludeJobResults.skipRemainingJobs()) {
                        /*
                        this should be true when there is an indication that the rest of the acceptedJobs on this page
                         will have issues
                         */
                return new JobInfo(hiddenJobs, skippedJobs, cachedJobs, totalHidden, totalCached,totalSkipped,everyJobHiddenCachedOrSkipped,true,false);
            }

        }catch(NoSuchSessionException nse){
            driver.quit();
            return new JobInfo(hiddenJobs, skippedJobs, cachedJobs, totalHidden, totalCached,totalSkipped,everyJobHiddenCachedOrSkipped,false,true);

        } catch (java.util.concurrent.TimeoutException e) {
            try{
                if(numAttempts<5) {
                    Thread.sleep(60_000);
                    return populateJobInfo(acceptedJobs, rejectedJobs, deepCachedJobs, shallowCachedJobs, item,
                            ++numAttempts, currentInfo, numJobs, url, driver, preferences, shallowPopulator);
                }
            }catch (InterruptedException ie){

            }
        }
        return new JobInfo(hiddenJobs, skippedJobs, cachedJobs, totalHidden, totalCached,totalSkipped,everyJobHiddenCachedOrSkipped,false,false);
    }


    private void handleNonSkipJobResults(List<Job> acceptedJobs, List<Job> rejectedJobs, WebDriver newDriver, Job job, ExcludeJobResults excludeJobResults) {
        if (excludeJobResults.excludeJob()) {
            job.setExcludeFilter(excludeJobResults.excludeFilter());
            cacheUtil.addJobToCache(job, false);
            includeOrExcluder.excludeJob(newDriver, job);
            rejectedJobs.add(job);
        } else if (excludeJobResults.includeJob()) {
            job.setIncludeFilters(excludeJobResults.includeFilters());
            cacheUtil.addJobToCache(job, true);
            includeOrExcluder.includeJob(newDriver, acceptedJobs, job);
        }
    }
    public boolean deepLoadJob(Job currentJob, WebDriver driver) {
        boolean success = false, wentToUrl=false;
        int numAttempts = 0, maxAttempts = 10;

        if (currentJob.getJobDetailsLink() != null) {
            Extractor.doubleClickElement(driver, currentJob.getJobDetailsLink(),false);
        }
        /*
        we need a way to test load pages.
        doing it this way will bring us to a new page which will mess up loading
        so this is only for testing.
         */
        //TODO do away with this path and make other path work with testing (it should be possible temporarily but the same goes for the URL)
        else {
            driver.get(currentJob.getUrl());
            wentToUrl=true;
        }
        try {
            if (driver != null) {
                do {
                    success = deepJobPopulator.populateJob(currentJob, driver);
                    numAttempts++;
                    if (!success && numAttempts < maxAttempts) {
                        if (currentJob.getJobDetailsLink() != null) {
                            Extractor.doubleClickElement(driver, currentJob.getJobDetailsLink(),false);
                            Thread.sleep(5_000);
                        }
                    }
                } while (!success && numAttempts < maxAttempts);
            }

            if(wentToUrl){
                driver.navigate().back();
            }

        } catch (java.util.concurrent.TimeoutException | InterruptedException te) {
            success = false;
        }
        return success;
    }





}
