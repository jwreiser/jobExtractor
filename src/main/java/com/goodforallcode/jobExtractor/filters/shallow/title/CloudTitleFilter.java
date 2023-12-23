package com.goodforallcode.jobExtractor.filters.shallow.title;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyNameUtil;

import java.util.List;

public class CloudTitleFilter implements JobFilter {
    List<String> descriptionKeywords = List.of("server-less","serverless"," SNS"
            ," SQS"," API Gateway"," EventBridge"," DynamoDB"," Redshift"," S3","EC2"," AWS ",
            "cloud-native","cloud native","Matillion", " aws "," Epic Client "," Epic ancillary "," Epic system "
            ," Epic environment","Hyperspace","Interconnect");

    List<String> bothKeywords = List.of("SailPoint","Apigee",
            "Informatica","azure ","gcp","cloud","Lambda", "Snowflake",
            "Matillion","Apigee","FlashStack","SFCC","PCF","Flashstack","ECSA ");


    List<String>  companyNames = List.of("Cloudflare","Render","Bowman Williams","Concourse Labs");


    List<String> jobTitlePhrases=List.of(
            "AWS ","Epic ","OpenShift"
    );

    @Override
    public boolean include(Preferences preferences, Job job) {
        if(!preferences.isExcludeCloudHeavy()){
            return true;
        }
        if(companyNames.stream().anyMatch(cn-> CompanyNameUtil.containsCompanyName(cn,job))){
            System.err.println("cloud company name ->reject: " + job);
            return false;
        }
        String title = job.getTitle().toLowerCase();
        if(jobTitlePhrases.stream().anyMatch(p->title.contains(p.toLowerCase()))){
            System.err.println("cloud title only ->reject: " + job);
            return false;
        }
        if(bothKeywords.stream().anyMatch(p->title.contains(p.toLowerCase()))){
            System.err.println("cloud title both ->reject: " + job);
            return false;
        }

        return true;
    }
}
