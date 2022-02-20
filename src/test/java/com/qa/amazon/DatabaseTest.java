package com.qa.amazon;

import org.bson.Document;
import org.testng.annotations.Test;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DatabaseTest {
	private static final String connectionStringCredential = "mongodb+srv://sushilMongoDb:EpjinMiSmByaKJXL@cluster0.k25ej.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";

	@Test
	void getData() {
		connectToDatabase();
	}
	
	private void connectToDatabase() {
		ConnectionString connectionString = new ConnectionString(connectionStringCredential);
		MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connectionString).build();
		
		MongoClient mongoClient = MongoClients.create(settings);

		MongoDatabase database = mongoClient.getDatabase("base");

		// Creating a collection
		MongoCollection collection = database.getCollection("items");

		// Preparing a document
		Document document = new Document("mobile", "iphone").append("search_Item", "iphone 12");
		
		// Inserting the document into the collection
		collection.insertOne(document);
		
		System.out.println("Document inserted successfully");
	}
}