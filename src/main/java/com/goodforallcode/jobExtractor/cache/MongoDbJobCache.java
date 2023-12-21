package com.goodforallcode.jobExtractor.cache;

import com.goodforallcode.jobExtractor.model.Job;
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
    private static List<String> stopWords=List.of("a","ability","about",
            "across","advancement","afterwards","all","also",
            "an","analytical","and","any","application","applications","apply",
            "are", "area","areas","as","at",
            "be","been","being","between","both", "building","by",
            "can","career","careers",  "chance","change","check","code","could",
            "collaborate","collaborative",
            "communication","competitive","complex","consider",
            "contact","correcting","cross","customers","cycle",
            "data-entry","description","destination","details",
            "develop","developer","developing","development",
            "dice","didn't","diligent","direction",
            "do","does","doesn't","doing",
            "domains","don't","drive",
            "each","effective","email","employer","engineer","engineering","engineers",
            "enhance",
            "enterprise","environment","environments","ensure",
            "errors","execute","excellent",
            "exemplary", "expect","experience","experts",
            "extension","externally","every",
            "features","focus","following","for","from","functional",
            "get","go","growth","had","hands-on",
            "hasn't","haven't","hadn't","has","have",
            "having","helping","how",
            "identifying","if","immediate","implement","implements","implementing",
            "in","inc","including","independent",
            "industry","influence",
            "influential","innovation", "internally","interview","into",
            "is","isn't","issues","it","it's","its",
            "job","join",
            "key","kindly","knowledge",
            "languages","llc","life","like","location",
            "make","many",
            "managers","may","members","might", "minimum",
            "must","my",
            "needed","new","no","not",
            "of","old","on","only","opportunity","or","organization","other","ought",
            "our","ours","over",
            "partner","partnering","performance","please",
            "proactive","problem","product","production","profile","programming","problem-solving",
            "problems","provide","proven",
            "quality","quickly",
            "related","relationships","research","roadmap","role",
            "seeking","services","should","skilled","skills","so","software",
            "solve","solutions","solving","some",
            "stage","stakeholders","stand","strategic","strategy","strengthen","strong",
            "talented","team","teams","tech","technical","technology","the","than","that","this",
            "then","these","those","thought","thrive","title","to","today",
            "too","tooling","tools",
            "track-record","transforming",
            "transferring",
            "understand","up","us","usability","utilize",
            "variety","verbal","very","via",
            "was","we","we'll","we've","were","what","which","why",
            "will","with","within",
            "without",
            "work","worker","working","would","written",
            "you'd","you'll","you're","you've");

    private static List<String>startOfDescription=List.of("required","requirements",
            "responsibilities","the role","you bring","qualifications",
            "what you will do","what you'll do");
    private static List<String>endOfDescription=List.of("pay transparency","compensation","benefits");
    public static String uri = "mongodb+srv://devUser:fakePass1@cluster0.q2ny9rm.mongodb.net/?retryWrites=true&w=majority";
    private static boolean serverUp = true;
    final public static String DATABASE_NAME = "MyJobSearch";
    final public static String COLLECTION_NAME = "Jobs";
    private HashSet<String> localJobCache = new HashSet<>();
    private List<Document> currentJobCache = new ArrayList<>();
    private static int CACHE_SIZE = 20220;
    private static Bson QUERY_NULL_OP =Filters.ne("_id",0);
    public boolean containsJob(Job job, MongoClient mongoClient) {
        boolean containsJob = false;
        Bson baseQuery = and(eq("title", job.getTitle()), eq("companyName", job.getCompanyName()));
        ;
        String compressedDescription=null;
        if(job.getDescription()!=null){
            compressedDescription=compressDescription(job.getDescription(),job.getTitle());
            job.setCompressedDescription(compressedDescription);
        }

        /*
        look at current run first
        should be quicker than going to mongo and there seems to be some issue
        where calling mongo misses recently added documents
        this is even true within the same extractor(thread)
         */
        if (job.getDescription()!=null && compressedDescription.length()>10 && localJobCache.contains(compressedDescription)){
            containsJob = true;
        } else if (serverUp) {
            try {
                MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
                MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);
                int numDocs = 0;
                List<Bson> filters=new ArrayList<>();
                if(job.getDescription()!=null && compressedDescription.length()>10 ){
                    filters.add(eq("description", compressedDescription));
                }else {
                    filters.add(eq("title", job.getTitle()));
                    filters.add(eq("companyName", job.getCompanyName()));

                    if (job.getMinYearlySalary() != null) {
                        filters.add(eq("minSalary", job.getMinYearlySalary()));
                    }else  if (job.getMinHourlySalary() != null) {
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
        String compressedDescription=null;
        if(job.getDescription()!=null){
            compressedDescription=compressDescription(job.getDescription(),job.getTitle());
        }
        Document doc = getJobDocumentToInsert(job, include,compressedDescription);
        currentJobCache.add(doc);
        if(job.getDescription()!=null) {
            localJobCache.add(compressedDescription);
        }
        if (currentJobCache.size() > CACHE_SIZE) {
            addRemainingJobs(mongoClient);
        }
    }

    public void addRemainingJobs(MongoClient mongoClient) {
        if ((serverUp && !currentJobCache.isEmpty())
        ||(!currentJobCache.isEmpty()&&currentJobCache.size()%50==0)
        ){
            try {
                MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
                MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);
                System.err.println(collection.estimatedDocumentCount() + "PRE");
                collection.insertMany(currentJobCache);
                System.err.println(collection.estimatedDocumentCount() + "POST");

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
        if (job.isContract()) {
            docValues.put("contract", job.isContract());
        }
        if(job.getDescription()!=null){
            docValues.put("description", compressedDescription);
        }


        Document doc = new Document(docValues);
        return doc;
    }

    public static boolean isServerUp() {
        return serverUp;
    }

    private  String compressDescription(String description,String title){
        final String editedDescription=description=description.toLowerCase().
                replaceAll(" - ","").replaceAll("\\("," ").
                replaceAll("\\)"," ").
                replaceAll(",","").replaceAll(":","")
                .replaceAll("!","").replaceAll("\\.","")
                .replaceAll("/"," ").replaceAll(title.toLowerCase()," ").
                replaceAll("dice is the leading career destination for tech experts at every stage of their careers","");
        OptionalInt lastReqtsText=startOfDescription.stream().mapToInt(p->editedDescription.lastIndexOf(p.toLowerCase())).max();
        final int startingLoc=lastReqtsText.orElse(0);
        OptionalInt endOfRelevantText=endOfDescription.stream().mapToInt(p->editedDescription.lastIndexOf(p.toLowerCase())).filter(cv->cv>startingLoc).min();
        String truncatedDesc=editedDescription;
        if(endOfRelevantText.isPresent()){
            truncatedDesc=editedDescription.substring(0,endOfRelevantText.getAsInt());
        }

        ArrayList<String> allWords;
        allWords = Stream.of(truncatedDesc.toLowerCase().split(" "))
                .collect(Collectors.toCollection(ArrayList<String>::new));
        allWords.removeAll(stopWords);
        return  allWords.stream().collect(Collectors.joining(" "));
    }
}
