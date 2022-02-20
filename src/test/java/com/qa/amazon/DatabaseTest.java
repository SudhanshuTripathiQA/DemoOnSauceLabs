package com.qa.amazon;

import org.bson.Document;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DatabaseTest {
	static String s = "mongodb+srv://sushilMongoDb:djHimXdbLViJ117e@cluster0.k25ej.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";

	static void m() {
//		try {
//			MongoClient client =MongoClients.create(s);
//
//			/**** Get database ****/
//			// if database doesn't exists, MongoDB will create it for you
//			MongoDatabase mydatabase = client.getDatabase("Item_Table");
//			System.out.println(mydatabase.getName());
//
//			/**** Get collection / table from 'testdb' ****/
//			// if collection doesn't exists, MongoDB will create it for you
//			//mydatabase.createCollection("Item_Table");
//			System.out.println("Collextion "+mydatabase.getCollection("Item_Table"));
//			
//			
//			MongoCollection collection =mydatabase.getCollection("Items");
//			Document document = new Document("mobile","iphone 12");
//			collection.insertOne(document);

//			FindIterable<Document> mydatabaserecords = mydatabase.getCollection("Item_Table").find();
//			MongoCursor<Document> iterator = mydatabaserecords.iterator();
//			while (iterator.hasNext()) {
//				Document doc = iterator.next();
//				// do something with document
//				System.out.println("occ is:: "+doc.isEmpty());
//			}
//			System.out.println("complete");

			// Creating a MongoDB client
//			MongoClient mongo = new MongoClient("localhost", 27017);
//			// Connecting to the database
//			MongoDatabase database = mongo.getDatabase("myDatabase");
//			// Creating a collection
//			database.createCollection("students");
//			// Preparing a document
//			Document document = new Document();
//			document.append("name", "Ram");
//			document.append("age", 26);
//			document.append("city", "Hyderabad");
//			// Inserting the document into the collection
//			database.getCollection("students").insertOne(document);
//			System.out.println("Document inserted successfully");

			// ------------------

			ConnectionString connectionString = new ConnectionString(
					"mongodb+srv://sushilMo4goDb:EpjinMiSmByaKJXL@cluster0.k25ej.mongodb.net/base4?retryWrites=true&w=majority");
			MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connectionString)
					.build();
			MongoClient mongoClient = MongoClients.create(settings);
			System.out.println(mongoClient.listDatabaseNames());
//			MongoDatabase database = mongoClient.getDatabase("base");
//			
//			System.out.println("Collextion: " + database.getCollection("items"));
//
//			MongoCollection collection = database.getCollection("items");
//			System.out.println("counts of occ: "+collection.countDocuments());
//			Document document = new Document("mobile", "iphone 12").append("iphone model", "new iphone 12");
//			collection.insertOne(document);
			
			// -------
//		   MongoClient mongoClient = MongoClients.create();
//			 MongoClient mongo = new MongoClient( "223.226.152.224" , 32 );
		      //Connecting to the database
		      MongoDatabase database = mongoClient.getDatabase("base");
		      //Creating a collection
		      database.createCollection("students");
		      //Preparing a document
		      Document document = new Document();
		      document.append("name", "Ram");
		      document.append("age", 26);
		      document.append("city", "Hyderabad");
		      //Inserting the document into the collection
		      database.getCollection("students").insertOne(document);
		      System.out.println("Document inserted successfully");
			
			
			System.out.println("cre successfully");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}

	public static void main(String[] args) {
		m();
	}

}