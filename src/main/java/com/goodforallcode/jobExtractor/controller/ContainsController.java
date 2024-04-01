package com.goodforallcode.jobExtractor.controller;

import com.goodforallcode.jobExtractor.cache.Cache;
import com.goodforallcode.jobExtractor.cache.MongoDbCache;
import com.goodforallcode.jobExtractor.model.Job;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.goodforallcode.jobExtractor.cache.MongoDbCache.uri;

@RestController()
public class ContainsController {
    @PostMapping("/contains/description/none")
    public boolean containsNoDescription(@RequestBody Job job){
        Cache cache = new MongoDbCache();
        boolean result;
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            result=cache.containsJobNoDescription(job,mongoClient);
        }
        return result;
    }

    @PostMapping("/contains/description/with")
    public boolean  contains(@RequestBody Job job){
        Cache cache = new MongoDbCache();
        boolean result;
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            result=cache.containsJob(job,mongoClient);
        }
        return result;
    }
}
