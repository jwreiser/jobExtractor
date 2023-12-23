package com.goodforallcode.jobExtractor.cache;

import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.util.StringUtil;
import com.mongodb.MongoTimeoutException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CountOptions;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.mongodb.client.model.Filters.*;

public class MongoDbJobCache implements JobCache {
    public static String uri = "mongodb+srv://devUser:fakePass1@cluster0.q2ny9rm.mongodb.net/?retryWrites=true&w=majority";
    private static boolean serverUp = true;
    final public static String DATABASE_NAME = "MyJobSearch";
    final public static String COLLECTION_NAME = "Jobs";
    private HashSet<String> localJobCache = new HashSet<>();
    private List<Document> currentJobCache = new ArrayList<>();
    private static int CACHE_SIZE = 20220;
    private static Bson QUERY_NULL_OP = Filters.ne("_id", 0);

    public boolean containsJob(Job job, MongoClient mongoClient) {
        boolean containsJob = false;
        String compressedDescription = null;
        if (job.getDescription() != null) {
            compressedDescription = StringUtil.compressDescription(job.getDescription(), job.getTitle());
            job.setCompressedDescription(compressedDescription);
        }

        /*
        look at current run first
        should be quicker than going to mongo and there seems to be some issue
        where calling mongo misses recently added documents
        this is even true within the same extractor(thread)
         */
        if (job.getDescription() != null && compressedDescription.length() > 10 && localJobCache.contains(compressedDescription)) {
            containsJob = true;
        } else if (serverUp) {
            try {
                MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
                MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);
                int numDocs = 0;
                List<Bson> filters = new ArrayList<>();
                if (job.getDescription() != null && compressedDescription.length() > 10) {
                    filters.add(eq("description", compressedDescription));
                } else {
                    filters.add(eq("title", job.getTitle()));
                    filters.add(eq("companyName", job.getCompanyName()));

                    if (job.getMinYearlySalary() != null) {
                        filters.add(eq("minSalary", job.getMinYearlySalary()));
                    } else if (job.getMinHourlySalary() != null) {
                        filters.add(eq("minSalary", job.getMinHourlySalary()));
                    }
                }
                Bson query = and(filters);
                CountOptions options = new CountOptions();
                options.limit(1);
                if (collection.countDocuments(query, options) > 0) {
                    containsJob = true;
                }
            } catch (MongoTimeoutException mt) {
                serverUp = false;
            }
        }

        return containsJob;
    }


    public void addJob(Job job, boolean include, MongoClient mongoClient) {
        String compressedDescription = null;
        if (job.getDescription() != null) {
            compressedDescription = StringUtil.compressDescription(job.getDescription(), job.getTitle());
        }
        Document doc = getJobDocumentToInsert(job, include, compressedDescription);
        currentJobCache.add(doc);
        if (job.getDescription() != null) {
            localJobCache.add(compressedDescription);
        }
        if (currentJobCache.size() > CACHE_SIZE) {
            addRemainingJobs(mongoClient);
        }
    }

    public void addRemainingJobs(MongoClient mongoClient) {
        if ((serverUp && !currentJobCache.isEmpty())
                || (!currentJobCache.isEmpty() && currentJobCache.size() % 25 == 0)
        ) {
            try {
                MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
                MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);
                System.err.println(collection.estimatedDocumentCount() + " cache size PRE Mongo insert");
                collection.insertMany(currentJobCache);
                System.err.println(collection.estimatedDocumentCount() + " cache size POST Mongo insert");

                currentJobCache.clear();
                serverUp = true;
            } catch (MongoTimeoutException mte) {
                serverUp = false;
            }
        }
    }

    private static Document getJobDocumentToInsert(Job job, boolean include, String compressedDescription) {
        HashMap<String, Object> docValues = new HashMap<>();
        docValues.put("title", job.getTitle());
        docValues.put("companyName", job.getCompanyName());
        docValues.put("include", include);
        docValues.put("url", job.getUrl());

        if (!include && job.getExcludeFilter() != null) {
            docValues.put("excludeFilter", job.getExcludeFilter().getName());
        }
        if (include && job.getIncludeFilter() != null) {
            docValues.put("includeFilter", job.getIncludeFilter().getName());
        }

        if (job.getIndustry() != null) {
            docValues.put("industry", job.getIndustry());
        }
        if (job.getMinYearlySalary() != null) {
            docValues.put("minSalary", job.getMinYearlySalary());
        }
        if (job.getMinHourlySalary() != null) {
            docValues.put("minSalary", job.getMinHourlySalary());
        }
        if (job.getPostingDate() != null) {
            docValues.put("postingDate", job.getPostingDate());
        }
        if (!job.getSkills().isEmpty()) {
            docValues.put("skills", job.getSkills());
        }

        if (job.isContract()) {
            docValues.put("contract", job.isContract());
        }
        if (job.getDescription() != null) {
            docValues.put("description", compressedDescription);
        }


        Document doc = new Document(docValues);
        return doc;
    }

    public static boolean isServerUp() {
        return serverUp;
    }


}
