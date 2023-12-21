package com.goodforallcode.jobExtractor.controller;

import com.goodforallcode.jobExtractor.extractor.Extractor;
import com.goodforallcode.jobExtractor.extractor.ExtractorFactory;
import com.goodforallcode.jobExtractor.extractor.JobResult;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.model.QueryInput;
import com.mongodb.MongoTimeoutException;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.TextSearchOptions;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.goodforallcode.jobExtractor.cache.MongoDbJobCache.*;

@RestController()
public class SearchController {
    @PostMapping("/search")
    public List<Job> extractMatchingJobs(@RequestBody String query){
        List<Job> results=new ArrayList<>();
        try (MongoClient mongoClient = MongoClients.create(uri)) {

            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            TextSearchOptions options = new TextSearchOptions().caseSensitive(false);
            Bson filter = Filters.text(query, options);
            FindIterable<Document> documents = collection.find(filter);
            Job job;
            for(Document doc:documents){
                job=new Job(String.valueOf(doc.get("title")),String.valueOf(doc.get("companyName")));
                job.setDescription(String.valueOf(doc.get("description")));
                job.setUrl(String.valueOf(doc.get("url")));
                results.add(job);
            }
        } catch (MongoTimeoutException mt) {

        }

        return results;
    }
}
