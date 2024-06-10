package com.goodforallcode.jobExtractor.job.includeexclude;

import com.goodforallcode.jobExtractor.extractor.ExcludeJobResults;
import com.goodforallcode.jobExtractor.extractor.Extractor;
import com.goodforallcode.jobExtractor.filters.FilterFactory;
import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.filters.both.SeniorFilter;
import com.goodforallcode.jobExtractor.model.CompanySummary;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CacheUtil;
import com.goodforallcode.jobExtractor.util.CompanyUtil;
import com.goodforallcode.jobExtractor.util.RESTUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.goodforallcode.jobExtractor.extractor.Extractor.doubleClickElement;

@Component
public class IncludeOrExcluder {
    @Autowired
    CacheUtil cacheUtil;

    static List<JobFilter> deepSkipFilters = FilterFactory.getDeepFiltersSkip();

    static List<JobFilter> alwaysIncludeDeepFilters = FilterFactory.getAlwaysIncludeDeepFilters();
    static List<JobFilter> alwaysIncludeShallowFilters = FilterFactory.getAlwaysIncludeShallowFilters();
    static List<JobFilter> alwaysExcludeDeepFilters = FilterFactory.getDeepFiltersAlwaysExclude();
    static List<JobFilter> lowPriorityDeepFilters = FilterFactory.getDeepFilters();
    static List<JobFilter> companySummaryExcludeFilters = FilterFactory.getCompanySummaryExcludeFilters();
    static List<JobFilter> companySummaryAlwaysIncludeFilters = FilterFactory.getCompanySummaryAlwaysIncludeFilters();
    static List<JobFilter> lowPriorityDeepFiltersTrusted = FilterFactory.getDeepFiltersTrusted();

    static List<JobFilter> alwaysExcludeDeepFiltersTrusted = FilterFactory.getDeepFiltersAlwaysExcludeTrusted();
    static List<JobFilter> deepExcludeEvenIfAlwaysIncludeFilters = FilterFactory.getDeepFiltersExcludeEvenIfAlwaysInclude();

    static List<JobFilter> alwaysExcludeShallowFilters = FilterFactory.getShallowFiltersAlwaysExclude();
    static List<JobFilter> shallowSkipFilters = FilterFactory.getShallowFiltersSkip();
    static List<JobFilter> includeFilters = FilterFactory.getIncludeFilters();

    public void excludeJob(WebDriver driver, Job currentJob) {
        try {
            doubleClickElement(driver, currentJob.getHideButton(),true);
        } catch (MoveTargetOutOfBoundsException ex) {
            //swallow this so we continue to remaining work
            //it is not a big deal if we don't hide one job
            System.err.println("out of bounds: "+currentJob);
            /*
            System.err.println(STR."out of bounds: \{currentJob}");
            String name = "Duke";
            String info = STR."My name is \{name}";
            System.out.println(info);

             */
        }
    }

    public void includeJob(WebDriver driver, List<Job> jobs, Job currentJob) {
        //I had been clicking the save button here but it gives stale element exceptions
        jobs.add(currentJob);
    }

    public void addCompanySummary(CompanySummary summary, Job job) {
        summary.setLocation(job.getLocation());
        summary.setMaximumNumEmployees(job.getMaximumNumEmployees());
        summary.setMinimumNumEmployees(job.getMinimumNumEmployees());
        summary.setJobIndustry(job.getJobIndustry());
        cacheUtil.addSummary(summary);
    }
    public ExcludeJobResults getExcludeJobResultsFromCompany(Job job, Preferences preferences, WebDriver driver, CompanySummary summary
            , boolean alreadyInCache
            , List<JobFilter> externalMatchingIncludeFilters) {
        Optional<JobFilter> firstExcludeFilter;
        List<JobFilter> matchingIncludeFilters;
        incorporateSummary(job, summary);
        if (!alreadyInCache) {
            addCompanySummary(summary, job);

        }
        matchingIncludeFilters = companySummaryAlwaysIncludeFilters.stream().filter(f -> f.include(preferences, job)).collect(Collectors.toList());
        if (!matchingIncludeFilters.isEmpty()) {
            if (externalMatchingIncludeFilters != null) {
                externalMatchingIncludeFilters.addAll(matchingIncludeFilters);
            }
            return new ExcludeJobResults(true, false, false, matchingIncludeFilters, null);
        }

        firstExcludeFilter = companySummaryExcludeFilters.stream().filter(f -> !f.include(preferences, job)).findFirst();

        /*
        if we have include filters already that means we have found a reason to always include and should use the job,company summary,
        etc. to determine whether to manually accept
         */
        if (externalMatchingIncludeFilters == null) {
            if (firstExcludeFilter.isPresent()) {
                SeniorFilter seniorFilter=new SeniorFilter();
                if(seniorFilter.isNotSenior(job)) {//TODO AFTER verify that AI works turn this back on
                    return new ExcludeJobResults(false, false, false, null, firstExcludeFilter.get());
                }else{
                    Extractor.doubleClickElement(driver, job.getHideButton(),true);
                    return new ExcludeJobResults(false, true, false, null, firstExcludeFilter.get());
                }

            }
        }

        return null;
    }

    private void incorporateSummary(Job job, CompanySummary summary) {
        if (summary.getSector() != null) {
            job.getIndustries().add(summary.getSector());
        }
        if (!CompanyUtil.isRecruiting(job.getCompanyName(),job.getIndustries())){
            summary.setName(job.getCompanyName());
        } else if (job.getRecruiterClient() != null) {
            summary.setName(job.getRecruiterClient());
        }
        job.setCompany(summary);
    }

    public ExcludeJobResults excludeJob(Job job, Preferences preferences, WebDriver driver) {
        String url = buildCompanySummaryURL(job);
        boolean alreadyInCache = false;

        Optional<JobFilter> firstExcludeFilter = deepExcludeEvenIfAlwaysIncludeFilters.stream().filter(f -> !f.include(preferences, job)).findFirst();
        if (firstExcludeFilter.isPresent()) {
            return new ExcludeJobResults(false, true, false, null, firstExcludeFilter.get());
        }

        List<JobFilter> matchingIncludeFilters = alwaysIncludeShallowFilters.stream().filter(f -> f.include(preferences, job)).collect(Collectors.toList());
        if (!matchingIncludeFilters.isEmpty()) {
            //we are doing this so we can see all the reasons for including so we can make a good decision
            if (job.getCompany() == null && url != null) {
                CompanySummary summary = CacheUtil.getCompanySummary(job);
                if (summary == null) {
                    summary = RESTUtil.callUrl(url);
                } else {
                    alreadyInCache = true;
                }
                if (summary != null) {
                    getExcludeJobResultsFromCompany(job, preferences, driver, summary, alreadyInCache, matchingIncludeFilters);
                } else {
                    System.err.println("Could not get summary for " + job.getCompanyName());
                }
            }
            return new ExcludeJobResults(true, false, false, matchingIncludeFilters, null);
        }

        if (deepSkipFilters.stream().anyMatch(f -> !f.include(preferences, job))) {
            return new ExcludeJobResults(false, false, false, null, null);
        }

        firstExcludeFilter = alwaysExcludeDeepFiltersTrusted.stream().filter(f -> !f.include(preferences, job)).findFirst();
        if (firstExcludeFilter.isPresent()) {
            return new ExcludeJobResults(false, true, false, null, firstExcludeFilter.get());
        }

        //TODO MOVE ALL FILTERS TO TRUSTED AND REMOVE THIS BLOCK
        firstExcludeFilter = alwaysExcludeDeepFilters.stream().filter(f -> !f.include(preferences, job)).findFirst();
        if (firstExcludeFilter.isPresent()) {
            return new ExcludeJobResults(false, true, false, null, firstExcludeFilter.get());
        }


        matchingIncludeFilters = alwaysIncludeDeepFilters.stream().filter(f -> f.include(preferences, job)).collect(Collectors.toList());
        if (!matchingIncludeFilters.isEmpty()) {
            //we are doing this so we can see all the reasons for including so we can make a good decision
            if (job.getCompany() == null && url != null) {
                CompanySummary summary = CacheUtil.getCompanySummary(job);
                if (summary == null) {
                    summary = RESTUtil.callUrl(url);
                } else {
                    alreadyInCache = true;
                }
                if (summary != null) {
                    getExcludeJobResultsFromCompany(job, preferences, driver, summary, alreadyInCache, matchingIncludeFilters);
                } else {
                    System.err.println("Could not get summary for " + job.getCompanyName());
                }
            }
            return new ExcludeJobResults(true, false, false, matchingIncludeFilters, null);
        }

        firstExcludeFilter = lowPriorityDeepFiltersTrusted.stream().filter(f -> !f.include(preferences, job)).findFirst();
        if (firstExcludeFilter.isPresent()) {
            Extractor.doubleClickElement(driver, job.getHideButton(),true);
            return new ExcludeJobResults(false, true, false, null, firstExcludeFilter.get());
        } else {
            firstExcludeFilter = lowPriorityDeepFilters.stream().filter(f -> !f.include(preferences, job)).findFirst();
            if (firstExcludeFilter.isPresent()) {
                Extractor.doubleClickElement(driver, job.getHideButton(),true);
                return new ExcludeJobResults(false, true, false, null, firstExcludeFilter.get());
            }
        }

        if (job.getCompany() == null && url != null) {
            CompanySummary summary = CacheUtil.getCompanySummary(job);
            if (summary == null) {
                summary = RESTUtil.callUrl(url);
            }
            if (summary != null) {
                ExcludeJobResults excludeJobResultsFromCompany = getExcludeJobResultsFromCompany(job, preferences, driver, summary, false, null);
                if (excludeJobResultsFromCompany != null) {
                    if (excludeJobResultsFromCompany.includeFilters() != null) {
                        //we want more include filters so that we can make a better decision (since there is only a few filters to look at this should not waste much time)
                        matchingIncludeFilters = includeFilters.stream().filter(f -> f.include(preferences, job)).collect(Collectors.toList());
                        if (matchingIncludeFilters != null) {
                            excludeJobResultsFromCompany.includeFilters().addAll(matchingIncludeFilters);
                        }
                    }
                    return excludeJobResultsFromCompany;
                }
            } else {
                System.err.println("Could not get summary for " + job.getCompanyName());
            }
        }

        matchingIncludeFilters = includeFilters.stream().filter(f -> f.include(preferences, job)).collect(Collectors.toList());
        return new ExcludeJobResults(true, false, false, matchingIncludeFilters, null);
    }

    private static String buildCompanySummaryURL(Job job) {

        String url =  null ;

        if (CompanyUtil.isRecruiting(job.getCompanyName(),job.getIndustries()) && job.getRecruiterClient() != null) {
            url="http://summary:9999/company/summarize/"+addValueToUrl(url,null,job.getRecruiterClient(),true);
        } else if (!CompanyUtil.isRecruiting(job.getCompanyName(),job.getIndustries())) {
            url="http://summary:9999/company/summarize/"+addValueToUrl(url,null,job.getCompanyName(),true);
        }

        //if this is a recruiter don't use the number of employees, industry as they don't apply to recruiting client
        if (!CompanyUtil.isRecruiting(job.getCompanyName(),job.getIndustries())) {
            url=addValueToUrl(url,"industry",job.getJobIndustry(),true);
            url=addValueToUrl(url,"location",job.getLocation(),true);
            url=addValueToUrl(url,"minEmployees",job.getMinimumNumEmployees(),false);
            if(job.getMaximumNumEmployees()!=null && job.getMinimumNumEmployees()!=null && !job.getMaximumNumEmployees().equals(job.getMinimumNumEmployees())) {
                url = addValueToUrl(url, "maxEmployees", job.getMaximumNumEmployees(), false);
            }
        }
        return url;
    }

    private static String addValueToUrl(String url,String newValueName,Object newValue,boolean isString){
        StringBuilder builder=new StringBuilder();
        if(newValue!=null) {
            if (newValueName != null) {
                if (url.contains("?")) {
                    builder.append("&");
                } else {
                    builder.append("?");
                }
                builder.append(newValueName + "=");
            }
            if (isString) {
                try {
                    builder.append(URLEncoder.encode((String) newValue, StandardCharsets.UTF_8.toString()).replaceAll("\\+", "%20"));
                } catch (UnsupportedEncodingException e) {
                    builder.append(newValue.toString());
                }
            } else {
                builder.append(newValue.toString());
            }
        }
        if(url==null){
            url=builder.toString();
        }else {
            url += builder.toString();
        }
        return url;
    }

}
