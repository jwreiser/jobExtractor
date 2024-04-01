package com.goodforallcode.jobExtractor.controller;

import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.util.StringUtil;
import com.mongodb.MongoTimeoutException;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.TextSearchOptions;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.goodforallcode.jobExtractor.cache.MongoDbCache.*;
import static com.mongodb.client.model.Filters.*;

@RestController()
public class SearchController {
    @PostMapping("/search")
    public List<Job> extractMatchingJobs(@RequestBody String query){
        List<Job> results=new ArrayList<>();
        try (MongoClient mongoClient = MongoClients.create(uri)) {

            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(JOBS_COLLECTION_NAME);

            LocalDate now=LocalDate.now();
            LocalDate daysAgo=now.minusDays(1);
            LocalDate weeksAgo=now.minusWeeks(2);

            TextSearchOptions options = new TextSearchOptions().caseSensitive(false);
            Bson textFilter = Filters.text(query, options);



            FindIterable<Document> documents = collection.find(
                    and(textFilter,gte("searchDate", daysAgo),gte("postingDate", weeksAgo)));
            Job job;

            List<String>urls=new ArrayList<>();
            for(Document doc:documents){
                job=new Job(String.valueOf(doc.get("title")),String.valueOf(doc.get("companyName")));
                job.setDescription(String.valueOf(doc.get("description")));
                job.setUrl(String.valueOf(doc.get("url")));
                if(!urls.contains(StringUtil.trimUrl(job.getUrl()))) {
                    urls.add(StringUtil.trimUrl(job.getUrl()));
                    if (doc.get("industry") != null) {
                        job.getIndustries().add(String.valueOf(doc.get("industry")));
                    }

                    if (doc.get("skills") != null && doc.get("skills") instanceof List) {
                        job.setSkills((List) (doc.get("skills")));
                    }
                    if (doc.get("postingDate") != null && doc.get("postingDate") instanceof Date) {
                        Date postingDate = (Date) doc.get("postingDate");
                        LocalDate convertedDate = postingDate.toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate();
                        job.setPostingDate(convertedDate);
                    }
                    if (doc.get("searchDate") != null && doc.get("searchDate") instanceof Date) {
                        Date searchDate = (Date) doc.get("searchDate");
                        LocalDate convertedDate = searchDate.toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate();
                        job.setSearchDate(convertedDate);
                    }
                    results.add(job);
                }
            }
        } catch (MongoTimeoutException mt) {

        }

        return results;
    }
}
