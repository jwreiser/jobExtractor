package com.goodforallcode.jobExtractor.filters.shallow.title;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;
import com.goodforallcode.jobExtractor.util.CompanyUtil;

import java.util.List;

public class CloudTitleFilter implements JobFilter {
    List<String> descriptionKeywords = List.of("server-less","serverless"," SNS"
            ," SQS"," API Gateway"," EventBridge"," DynamoDB"," Redshift"," S3","EC2"," AWS ",
            "cloud-native","cloud native","Matillion", " Epic Client "," Epic ancillary "," Epic system "
            ," Epic environment","Hyperspace","Interconnect");

    List<String> bothKeywords = List.of("SailPoint","Apigee","Sail point",
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
        if(companyNames.stream().anyMatch(cn-> CompanyUtil.containsCompanyName(cn,job))){
            return false;
        }
        String title = job.getTitle().toLowerCase();
        if(jobTitlePhrases.stream().anyMatch(p->title.contains(p.toLowerCase()))){
            return false;
        }
        if(bothKeywords.stream().anyMatch(p->title.contains(p.toLowerCase()))){
            return false;
        }

        return true;
    }
}
