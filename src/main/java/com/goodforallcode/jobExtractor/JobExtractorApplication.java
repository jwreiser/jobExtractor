package com.goodforallcode.jobExtractor;

import com.mongodb.MongoTimeoutException;
import com.mongodb.client.*;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import org.bson.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.TimeUnit;

import static com.goodforallcode.jobExtractor.cache.MongoDbJobCache.*;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

@SpringBootApplication
public class JobExtractorApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobExtractorApplication.class, args);
		try (MongoClient mongoClient = MongoClients.create(uri)) {

			MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
 			MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

			/*
			collection.dropIndexes();


			collection.dropIndex("hashedDescription_1");


			IndexOptions options=new IndexOptions();
			IndexOptions expireOptions = options.expireAfter(30L, TimeUnit.DAYS);
			collection.createIndex(Indexes.descending("postingDate"), expireOptions);

			collection.createIndex(Indexes.compoundIndex(
					Indexes.text("description"),Indexes.text("industry"),
					Indexes.text("title"),Indexes.text("companyName")
			));
			collection.createIndex(Indexes.ascending("description"));
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
