package com.goodforallcode.jobExtractor.cache;

import com.goodforallcode.jobExtractor.model.Job;
import com.mongodb.MongoTimeoutException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.CountOptions;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class MongoDbJobCache implements JobCache {
    public static String uri = "mongodb+srv://devUser:fakePass1@cluster0.q2ny9rm.mongodb.net/?retryWrites=true&w=majority";
    private static boolean serverUp=true;
    final private static String databaseName="MyJobSearch";
    final private static String collectionName="Jobs";
    private HashSet<String> currentJobIndexCache =new HashSet<>();
    private List<Document> currentJobCache =new ArrayList<>();
    private static int CACHE_SIZE=20220;

    public boolean containsJob(Job job, MongoClient mongoClient) {
        boolean containsJob=false;

        /*
        look at current run first
        should be quicker than going to mongo and there seems to be some issue
        where calling mongo misses recently added documents
        this is even true within the same extractor(thread)
         */
        if(currentJobIndexCache.contains(job.getTitle()+"_"+job.getCompanyName())){
            containsJob=true;
        }else if(serverUp){
            try {
                MongoDatabase database = mongoClient.getDatabase(databaseName);
                MongoCollection<Document> collection = database.getCollection(collectionName);
                int numDocs = 0;
                Bson query = and(eq("title", job.getTitle()), eq("companyName", job.getCompanyName()));
                CountOptions options = new CountOptions();
                options.limit(1);
                if (collection.countDocuments(query, options) > 0) {
                    containsJob = true;
                }
            }catch (MongoTimeoutException mt){
                serverUp=false;
            }
        }
        return containsJob;
    }


    public void addJob(Job job, boolean include, MongoClient mongoClient) {
        Document doc = getQueryDocument(job, include);
        currentJobCache.add(doc);
        currentJobIndexCache.add(job.getTitle()+"_"+job.getCompanyName());
        if(currentJobCache.size()>CACHE_SIZE){
            addRemainingJobs(mongoClient);
        }
    }

    public void addRemainingJobs(MongoClient mongoClient){
        if(!currentJobCache.isEmpty()) {
            try {
                MongoDatabase database = mongoClient.getDatabase(databaseName);
                MongoCollection<Document> collection = database.getCollection(collectionName);
                System.err.println(collection.estimatedDocumentCount() + "PRE");
                collection.insertMany(currentJobCache);
                System.err.println(collection.estimatedDocumentCount() + "POST");

                currentJobCache.clear();
                serverUp=true;
            }catch (MongoTimeoutException mte){
                serverUp=false;
            }
        }
    }

    private static Document getQueryDocument(Job job, boolean include) {
        HashMap<String,Object>docValues=new HashMap<>();
        docValues.put("title", job.getTitle());
        docValues.put("companyName", job.getCompanyName());
        docValues.put("include", include);
        docValues.put("url", job.getUrl());
        if(job.isHidden()) {
            docValues.put("hidden", true);
        }else if(job.isApplied()) {
            docValues.put("applied", true);
        }else if(!job.isAcceptingApplications()) {
            docValues.put("notAcceptingApplications", true);
        }else{
            if (!include && job.getExcludeFilter() != null) {
                docValues.put("excludeFilter", job.getExcludeFilter().getName());
            }
            if (include && job.getIncludeFilter() != null) {
                docValues.put("includeFilter", job.getIncludeFilter().getName());
            }
            if (job.getIndustry() != null) {
                docValues.put("industry", job.getIndustry());
            }
            if(job.getNumApplicants()!=null) {
                docValues.put("numApplicants", job.getNumApplicants());
            }
            if (job.getMinYearlySalary() != null) {
                docValues.put("minYearlySalary", job.getMinYearlySalary());
            }
            if (job.getMaxYearlySalary() != null) {
                docValues.put("maxYearlySalary", job.getMaxYearlySalary());
            }
            if(job.isContract()) {
                docValues.put("contract", job.isContract());
            }
            docValues.put("jobAge", job.getJobAgeInDays());
        }

        LocalDate now=LocalDate.now();
        LocalDate beginningOfYear=LocalDate.of(now.getYear(),1,1);
        Period elapsed=beginningOfYear.until(now);
        int daysPassed=elapsed.getDays()+ (elapsed.getMonths()*30);
        docValues.put("daysSinceYear", daysPassed);


        Document doc =new Document(docValues);
        return doc;
    }
}
