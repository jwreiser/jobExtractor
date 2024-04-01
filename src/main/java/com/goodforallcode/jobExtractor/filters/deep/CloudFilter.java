package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class CloudFilter implements JobFilter {
    List<String> descriptionKeywords = List.of("server-less","serverless"," SNS"
            ," SQS"," API Gateway"," EventBridge"," DynamoDB"," Redshift"," S3","EC2"," AWS ",
            "cloud-native","cloud native","Matillion", " aws "," Epic Client "," Epic ancillary "," Epic system "
            ," Epic environment","Hyperspace","Interconnect");

    List<String> bothKeywords = List.of("SailPoint","Apigee",
            "Informatica","azure ","gcp","cloud","Lambda", "Snowflake",
            "Matillion","Apigee","FlashStack","SFCC","PCF","Flashstack","ECSA ");

    @Override
    public boolean include(Preferences preferences, Job job) {
        if(!preferences.isExcludeCloudHeavy()){
            return true;
        }

        if(job.getDescription()!=null) {
            String text = job.getDescription().toLowerCase();

            long descriptionCount = descriptionKeywords.stream().filter(k -> text.contains(k.toLowerCase())).count();
            long bothCount = bothKeywords.stream().filter(k -> text.contains(k.toLowerCase())).count();
            if ((bothCount+descriptionCount) > 3) {
                System.err.println("cloud count " + bothCount+"-"+descriptionCount + " ->reject: " + job);
                return false;
            }
        }
        return true;
    }
}
