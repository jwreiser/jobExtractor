package com.goodforallcode.jobExtractor.controller;

import com.goodforallcode.jobExtractor.cache.JobCache;
import com.goodforallcode.jobExtractor.cache.MongoDbJobCache;
import com.goodforallcode.jobExtractor.extractor.Extractor;
import com.goodforallcode.jobExtractor.extractor.ExtractorFactory;
import com.goodforallcode.jobExtractor.extractor.JobResult;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.QueryInput;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static com.goodforallcode.jobExtractor.cache.MongoDbJobCache.uri;

@RestController()
public class ContainsController {
    @PostMapping("/contains/description/none")
    public boolean containsNoDescription(@RequestBody Job job){
        JobCache cache = new MongoDbJobCache();
        boolean result;
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            result=cache.containsJobNoDescription(job,mongoClient);
        }
        return result;
    }

    @PostMapping("/contains/description/with")
    public boolean  contains(@RequestBody Job job){
        JobCache cache = new MongoDbJobCache();
        boolean result;
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            result=cache.containsJob(job,mongoClient);
        }
        return result;
    }
}
