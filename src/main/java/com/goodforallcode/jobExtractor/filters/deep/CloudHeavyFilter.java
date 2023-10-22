package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class CloudHeavyFilter implements JobFilter {
    List<String> keywords = List.of("server-less","serverless","SailPoint"
            ,"Informatica","azure","aws","gcp","cloud","Lambda"," SNS"
            ," SQS"," API Gateway"," EventBridge"," DynamoDB"," Redshift"," S3",
            "cloud-native","cloud native","Snowflake","Matillion");

    @Override
    public boolean include(Preferences preferences, Job job) {
        if(!preferences.isExcludeCloudHeavy()){
            return true;
        }
        String text = job.getDescription().toLowerCase();

        long mainCount = keywords.stream().filter(k -> text.contains(k.toLowerCase())).count();
        if (mainCount > 3) {
            System.err.println("cloud count "+mainCount+" ->reject: " + job);
            return false;
        }
        return true;
    }
}
