package com.goodforallcode.jobExtractor.filters.deep;

import com.goodforallcode.jobExtractor.filters.JobFilter;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.preferences.Preferences;

import java.util.List;

public class CloudFilter implements JobFilter {
    List<String> descriptionKeywords = List.of("server-less","serverless"," SNS"
            ," SQS"," API Gateway"," EventBridge"," DynamoDB"," Redshift"," S3",
            "cloud-native","cloud native","Matillion", " aws ");
    List<String> bothKeywords = List.of("SailPoint","Apigee",
            "Informatica","azure ","gcp","cloud","Lambda", "Snowflake",
            "Matillion","Apigee","FlashStack");


    List<String> jobTitlePhrases=List.of(
            "AWS ","Epic "
    );

    @Override
    public boolean include(Preferences preferences, Job job) {
        if(!preferences.isExcludeCloudHeavy()){
            return true;
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
