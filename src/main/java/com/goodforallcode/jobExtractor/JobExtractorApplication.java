package com.goodforallcode.jobExtractor;

import com.mongodb.MongoSecurityException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@SpringBootApplication
public class JobExtractorApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobExtractorApplication.class, args);
	}

}
