package com.goodforallcode.jobExtractor;

import com.mongodb.MongoTimeoutException;
import com.mongodb.client.*;
import org.bson.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.goodforallcode.jobExtractor.cache.MongoDbCache.*;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

@SpringBootApplication
public class JobExtractorApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobExtractorApplication.class, args);
		try (MongoClient mongoClient = MongoClients.create(uri)) {

			MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
 			MongoCollection<Document> collection = database.getCollection(JOBS_COLLECTION_NAME);

			 //			collection.dropIndex("hashedDescription_1");
/*
			collection.dropIndexes();


			IndexOptions options=new IndexOptions();
			IndexOptions expireOptions = options.expireAfter(14L, TimeUnit.DAYS);
			collection.createIndex(Indexes.descending("searchDate"), expireOptions);

			collection.createIndex(Indexes.compoundIndex(
					Indexes.text("description"),Indexes.text("industry"),
					Indexes.text("title"),Indexes.text("companyName"),
					Indexes.text("skills"))
			);

			//should be unique and sparse
			IndexOptions uniqueSparseOptions=new IndexOptions().unique(true).sparse(true);
			collection.createIndex(Indexes.ascending("description"),uniqueSparseOptions);

			collection.createIndex(Indexes.ascending("url"),uniqueSparseOptions);


			collection.createIndex(Indexes.compoundIndex(
					Indexes.ascending("industry"),
					Indexes.ascending("title"),Indexes.ascending("minSalary")
			));
*/
			ListIndexesIterable<Document> documents = collection.listIndexes();
			for(Document document:documents) {
				System.err.println(document);
			}
		} catch (MongoTimeoutException mt) {

		}
	}

}
