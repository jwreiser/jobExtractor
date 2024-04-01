package com.goodforallcode.jobExtractor.cache;

import com.goodforallcode.jobExtractor.model.CompanySummary;
import com.goodforallcode.jobExtractor.model.Job;
import com.goodforallcode.jobExtractor.util.StringUtil;
import com.mongodb.MongoBulkWriteException;
import com.mongodb.MongoTimeoutException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CountOptions;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.InsertManyOptions;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Filters.*;

public class MongoDbCache implements Cache {
    public static String uri = "mongodb+srv://devUser:fakePass1@cluster0.q2ny9rm.mongodb.net/?retryWrites=true&w=majority";
    private static boolean serverUp = true;
    final public static String DATABASE_NAME = "MyJobSearch";
    final public static String JOBS_COLLECTION_NAME = "Jobs";
    final public static String COMPANY_COLLECTION_NAME = "Companies";
    private HashSet<String> localJobDescriptionCache = new HashSet<>();
    private List<Document> currentJobCache = new ArrayList<>();
    private List<Document> currentCompanyCache = new ArrayList<>();
    private static int CACHE_SIZE = 10;
    private static Bson QUERY_NULL_OP = Filters.ne("_id", 0);
    private LocalTime lastChecked = null;

    public boolean containsJobNoDescription(Job job, MongoClient mongoClient) {
        boolean containsJob = false;
        boolean recheck = false;

        if (lastChecked != null) {
            LocalTime now = LocalTime.now();
            if (lastChecked.until(now, ChronoUnit.MINUTES) > 5) {
                recheck = true;
            }
        }

        if (serverUp || recheck) {

            List<Bson> filters = new ArrayList<>();
            filters.add(eq("title", job.getTitle()));
            filters.add(eq("companyName", job.getCompanyName()));
            filters.add(Filters.exists(("description"), false));
            if (job.getMinYearlySalary() != null) {
                filters.add(eq("minSalary", job.getMinYearlySalary()));
            } else if (job.getMinHourlySalary() != null) {
                filters.add(eq("minSalary", job.getMinHourlySalary()));
            }

            if (containsResultsForFilters(filters, mongoClient)) {
                containsJob = true;
            } else if (containsResultsForFilters(List.of(eq("url", StringUtil.trimUrl(job.getUrl())))
                    , mongoClient)) {
                containsJob = true;
            }

        }

        return containsJob;
    }

    public CompanySummary getCompanySummary(Job job,MongoClient mongoClient) {
        CompanySummary summary = null;
        if (serverUp) {


            Bson query=and(eq("companyName", job.getCompanyName()),eq("jobIndustry", job.getJobIndustry())
                    ,eq("employeeRangeLow", job.getMinimumNumEmployees()),eq("employeeRangeHigh", job.getMaximumNumEmployees())
                    ,eq("location", job.getLocation()));
            try {
                MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
                MongoCollection<Document> collection = database.getCollection(COMPANY_COLLECTION_NAME);
                FindIterable<Document> documents = collection.find(query);
                for(Document document:documents){
                    summary=new CompanySummary(document.getString("companyName"));
                    summary.setFoundingYear(document.getInteger("foundingYear"));
                    summary.setSector(document.getString("sector"));
                    summary.setEmployeeRangeLow(document.getInteger("employeeRangeLow"));
                    try {
                        if (document.getInteger("employeeRangeHigh") != null) {
                            summary.setEmployeeRangeHigh(document.getInteger("employeeRangeHigh"));
                        }
                        if (document.getInteger("fortuneRanking") != null) {
                            summary.setFortuneRanking(document.getInteger("fortuneRanking"));
                        }
                        summary.setRecentLayoffs(document.getBoolean("recentLayoffs"));
                        if (document.getString("example_acquired_company") != null) {
                            summary.setAcquisitions(true);
                        }
                        if (document.getBoolean("jobSecurity") != null) {
                            summary.setJobSecurity(document.getBoolean("jobSecurity"));
                        }
                        if (document.getBoolean("offshores") != null) {
                            summary.setOffshores(document.getBoolean("offshores"));
                        }
                        //software engineer
                        if (document.getBoolean("softwareEngineerHighOvertime") != null) {
                            summary.setSoftwareEngineerHighOvertime(document.getBoolean("softwareEngineerHighOvertime"));
                        }

                        if (document.getBoolean("softwareEngineerAfterHoursSupport") != null) {
                            summary.setSoftwareEngineerAfterHoursSupport(document.getBoolean("softwareEngineerAfterHoursSupport"));
                        }
                        if (document.getBoolean("legacySoftware") != null) {
                            summary.setLegacySoftware(document.getBoolean("legacySoftware"));
                        }
                        if (document.getBoolean("softwareEngineerExternalContractors") != null) {
                            summary.setSoftwareEngineerExternalContractors(document.getBoolean("softwareEngineerExternalContractors"));
                        }
                        if (document.getBoolean("softwareEngineerNightOrWeekendHours") != null) {
                            summary.setSoftwareEngineerNightOrWeekendHours(document.getBoolean("softwareEngineerNightOrWeekendHours"));
                        }
                        //management
                        if (document.getBoolean("negativeManagementCompetenceTone") != null) {
                            summary.setNegativeManagementCompetenceTone(document.getBoolean("negativeManagementCompetenceTone"));
                        }
                        if (document.getBoolean("positiveEmployeeToneTowardsManagement") != null) {
                            summary.setPositiveEmployeeToneTowardsManagement(document.getBoolean("positiveEmployeeToneTowardsManagement"));
                        }
                        if (document.getBoolean("topDownManagement") != null) {
                            summary.setTopDownManagement(document.getBoolean("topDownManagement"));
                        }

                        if (document.getBoolean("workLifeBalance") != null) {
                            summary.setWorkLifeBalance(document.getBoolean("workLifeBalance"));
                        }
                        if (document.getBoolean("fastPaced") != null) {
                            summary.setFastPaced(document.getBoolean("fastPaced"));
                        }
                        summary.setDefense(document.getBoolean("defense"));
                        if (document.getBoolean("stress") != null) {
                            summary.setStress(document.getBoolean("stress"));
                        }
                        if (document.getBoolean("consulting") != null) {
                            summary.setConsulting(document.getBoolean("consulting"));
                        }
                        if (document.getBoolean("contractor") != null) {
                            summary.setContractor(document.getBoolean("contractor"));
                        }
                        if (document.getBoolean("peopleFocused") != null) {
                            summary.setPeopleFocused(document.getBoolean("peopleFocused"));
                        }
                        if (document.getBoolean("publicGood") != null) {
                            summary.setPublicGood(document.getBoolean("publicGood"));
                        }
                        if (document.getBoolean("location") != null) {
                            summary.setLocation(document.getString("location"));
                        }
                        if (document.getBoolean("companyWebsite") != null) {
                            summary.setCompanyWebsite(document.getString("companyWebsite"));
                        }
                    }catch (ClassCastException ex){
                        ex.printStackTrace();
                    }
                    break;
                }
            } catch (MongoTimeoutException mt) {
                serverUp = false;
                lastChecked = LocalTime.now();
            }
        }
        return summary;
    }

    private boolean containsResultsForFilters(List<Bson> filters, MongoClient mongoClient) {
        boolean containsJob = false;
        try {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(JOBS_COLLECTION_NAME);
            Bson query = and(filters);
            CountOptions options = new CountOptions();
            options.limit(1);
            if (collection.countDocuments(query, options) > 0) {
                containsJob = true;
            }
        } catch (MongoTimeoutException mt) {
            serverUp = false;
            lastChecked = LocalTime.now();
        }
        return containsJob;
    }

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
        if (job.getDescription() != null && compressedDescription.length() > 10 && localJobDescriptionCache.contains(compressedDescription)) {
            containsJob = true;
        } else if (serverUp) {
            try {
                MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
                MongoCollection<Document> collection = database.getCollection(JOBS_COLLECTION_NAME);
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
                lastChecked = LocalTime.now();
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
            localJobDescriptionCache.add(compressedDescription);
        }

        if (currentJobCache.size() > CACHE_SIZE) {
            addRemainingJobs(mongoClient);
        }
    }

    public void addCompanySummary(CompanySummary summary, Job job,MongoClient mongoClient) {

        Document doc = getCompanyDocumentToInsert(summary,job);
        currentCompanyCache.add(doc);

        if (currentCompanyCache.size() > CACHE_SIZE) {
            addRemainingCompanySummaries(mongoClient);
        }
    }

    public void addRemainingJobs(MongoClient mongoClient) {
        if ((serverUp && !currentJobCache.isEmpty())
                || (!currentJobCache.isEmpty() && currentJobCache.size() % CACHE_SIZE == 0)
        ) {
            try {
                MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
                MongoCollection<Document> collection = database.getCollection(JOBS_COLLECTION_NAME);
                System.err.println(collection.estimatedDocumentCount() + " cache size PRE Mongo insert");
                InsertManyOptions options = new InsertManyOptions();
                options.ordered(false);
                collection.insertMany(currentJobCache, options);
                System.err.println(collection.estimatedDocumentCount() + " cache size POST Mongo insert");

                currentJobCache.clear();
                serverUp = true;
            } catch (MongoTimeoutException mte) {
                serverUp = false;
                lastChecked = LocalTime.now();

            } catch (MongoBulkWriteException be) {
                try {
                    MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
                    MongoCollection<Document> collection = database.getCollection(JOBS_COLLECTION_NAME);
                    int neededToGo = 0;
                    //at this point all items should be in although we may have some dupes that will still be returned by the web service
                    boolean concurrencyIssue = false;
                    do {
                        try {
                            for (Document doc : currentJobCache) {
                                try {
                                    collection.insertOne(doc);
                                    neededToGo++;
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                                System.out.println("neededToGo" + neededToGo);
                            }
                            currentJobCache.clear();

                        } catch (ConcurrentModificationException e) {
                            concurrencyIssue = true;
                        }


                    } while (concurrencyIssue);


                    serverUp = true;
                } catch (MongoTimeoutException mte) {
                    serverUp = false;
                    lastChecked = LocalTime.now();
                }
            }
        }
    }
    public void addRemainingCompanySummaries(MongoClient mongoClient) {
        if ((serverUp && !currentCompanyCache.isEmpty())
                || (!currentCompanyCache.isEmpty() && currentCompanyCache.size() % CACHE_SIZE == 0)
        ) {
            try {
                MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
                MongoCollection<Document> collection = database.getCollection(COMPANY_COLLECTION_NAME);
                System.err.println(collection.estimatedDocumentCount() + " cache size PRE Mongo insert");
                InsertManyOptions options = new InsertManyOptions();
                options.ordered(false);
                collection.insertMany(currentCompanyCache, options);
                System.err.println(collection.estimatedDocumentCount() + " cache size POST Mongo insert");

                currentCompanyCache.clear();
                serverUp = true;
            } catch (MongoTimeoutException mte) {
                serverUp = false;
                lastChecked = LocalTime.now();

            } catch (MongoBulkWriteException be) {
                try {
                    MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
                    MongoCollection<Document> collection = database.getCollection(COMPANY_COLLECTION_NAME);
                    int neededToGo = 0;
                    //at this point all items should be in although we may have some dupes that will still be returned by the web service
                    boolean concurrencyIssue = false;
                    do {
                        try {
                            for (Document doc : currentCompanyCache) {
                                try {
                                    collection.insertOne(doc);
                                    neededToGo++;
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                                System.out.println("neededToGo" + neededToGo);
                            }
                            currentCompanyCache.clear();

                        } catch (ConcurrentModificationException e) {
                            concurrencyIssue = true;
                        }


                    } while (concurrencyIssue);


                    serverUp = true;
                } catch (MongoTimeoutException mte) {
                    serverUp = false;
                    lastChecked = LocalTime.now();
                }
            }
        }
    }
    private static Document getCompanyDocumentToInsert(CompanySummary summary,Job job) {
        HashMap<String, Object> docValues = new HashMap<>();
        docValues.put("companyName", summary.getName());

        docValues.put("foundingYear", summary.getFoundingYear());
        docValues.put("sector", summary.getSector());

        //these should be inserted even if null
        docValues.put("jobIndustry", job.getJobIndustry());
        docValues.put("jobMinNumEmployees", job.getMinimumNumEmployees());
        docValues.put("jobMaxNumEmployees", job.getMaximumNumEmployees());
        docValues.put("location", job.getLocation());

        docValues.put("employeeRangeLow", summary.getEmployeeRangeLow());
        if(summary.getEmployeeRangeHigh()!=null) {
            docValues.put("employeeRangeHigh", summary.getEmployeeRangeHigh());
        }
        if(summary.getFortuneRanking()!=null) {
            docValues.put("fortuneRanking", summary.getFortuneRanking());
        }

        docValues.put("recentLayoffs", summary.getRecentLayoffs());
        if(summary.getAcquisitions()!=null) {
            docValues.put("acquisitions", summary.getAcquisitions());
        }

        if(summary.getJobSecurity()!=null) {
            docValues.put("jobSecurity", summary.getJobSecurity());
        }
        docValues.put("offshores", summary.getOffshores());

        if(summary.getSoftwareEngineerHighOvertime()!=null) {
            docValues.put("softwareEngineerHighOvertime", summary.getSoftwareEngineerHighOvertime());
        }
        if(summary.getSoftwareEngineerAfterHoursSupport()!=null) {
            docValues.put("softwareEngineerAfterHoursSupport", summary.getSoftwareEngineerAfterHoursSupport());
        }
        if(summary.getLegacySoftware()!=null) {
            docValues.put("legacySoftware", summary.getLegacySoftware());
        }

        if(summary.getSoftwareEngineerExternalContractors()!=null) {
            docValues.put("softwareEngineerExternalContractors", summary.getSoftwareEngineerExternalContractors());
        }
        if(summary.getSoftwareEngineerNightOrWeekendHours()!=null) {
            docValues.put("softwareEngineerNightOrWeekendHours", summary.getSoftwareEngineerNightOrWeekendHours());
        }

        if(summary.getNegativeManagementCompetenceTone()!=null) {
            docValues.put("negativeManagementCompetenceTone", summary.getNegativeManagementCompetenceTone());
        }
        if(summary.getPositiveEmployeeToneTowardsManagement()!=null) {
            docValues.put("positiveEmployeeToneTowardsManagement", summary.getPositiveEmployeeToneTowardsManagement());
        }
        if(summary.getTopDownManagement()!=null) {
            docValues.put("topDownManagement", summary.getTopDownManagement());
        }

        if(summary.getWorkLifeBalance()!=null) {
            docValues.put("workLifeBalance", summary.getWorkLifeBalance());
        }
        if(summary.getFastPaced()!=null) {
            docValues.put("fastPaced", summary.getFastPaced());
        }
        if(summary.getDefense()!=null) {
            docValues.put("defense", summary.getDefense());
        }
        if(summary.getStress()!=null) {
            docValues.put("stress", summary.getStress());
        }

        if(summary.getConsulting()!=null) {
            docValues.put("consulting", summary.getConsulting());
        }
        if(summary.getContractor()!=null) {
            docValues.put("contractor", summary.getContractor());
        }
        if(summary.getPeopleFocused()!=null) {
            docValues.put("peopleFocused", summary.getPeopleFocused());
        }
        if(summary.getPublicGood()!=null) {
            docValues.put("publicGood", summary.getPublicGood());
        }
        if(summary.getCompanyWebsite()!=null) {
            docValues.put("companyWebsite", summary.getCompanyWebsite());
        }
        Document doc = new Document(docValues);
        return doc;
    }

    private static Document getJobDocumentToInsert(Job job, boolean include, String compressedDescription) {
        HashMap<String, Object> docValues = new HashMap<>();
        docValues.put("title", job.getTitle());
        docValues.put("companyName", job.getCompanyName());
        docValues.put("include", include);
        docValues.put("url", StringUtil.trimUrl(job.getUrl()));

        if (!include && job.getExcludeFilter() != null) {
            docValues.put("excludeFilter", job.getExcludeFilter().getName());
        }
        if (include && job.getIncludeFilters() != null) {
            List<String>filterNames=job.getIncludeFilters().stream().map(f->f.getName()).collect(Collectors.toList());
            docValues.put("includeFilters", filterNames);
        }

        if (job.getIndustries() != null) {
            docValues.put("industries", job.getIndustries());
        }
        if (job.getJobIndustry() != null) {
            docValues.put("jobIndustry", job.getJobIndustry());
        }
        if (job.getMinYearlySalary() != null) {
            docValues.put("minSalary", job.getMinYearlySalary());
        }
        if (job.getMinHourlySalary() != null) {
            docValues.put("minSalary", job.getMinHourlySalary());
        }

        if (job.getNumApplicants() != null) {
            docValues.put("numApplicants", job.getNumApplicants());
        }

        if (job.getPostingDate() != null) {
            docValues.put("postingDate", job.getPostingDate());
        }
        docValues.put("searchDate", LocalDate.now());
        if (!job.getSkills().isEmpty()) {
            docValues.put("skills", job.getSkills());
        }

        if (job.isContract()) {
            docValues.put("contract", job.isContract());
        }
        if (compressedDescription != null) {
            docValues.put("description", compressedDescription);
        }


        Document doc = new Document(docValues);
        return doc;
    }

    public static boolean isServerUp() {
        return serverUp;
    }


}
