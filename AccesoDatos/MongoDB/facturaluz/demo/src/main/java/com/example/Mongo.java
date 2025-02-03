package com.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class Mongo {

    private MongoClient mongoClient;
    public MongoDatabase database;

    // Establish connection to MongoDB
    public void mongoConnection() {
        String connectionString = "mongodb://mati:mati@localhost:27017"; 

        if (mongoClient == null) {
            try {
                mongoClient = MongoClients.create(connectionString); // Create MongoClient
                System.out.println("Connected to MongoDB!");
                database = mongoClient.getDatabase("facturaluz");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error connecting to MongoDB!");
            }
        } else {
            System.out.println("Already connected to MongoDB.");
        }
    }

    // Create collection for a specific day (if it doesn't exist)
    public void createCollection(String day) {
        // Dynamically create the collection name based on the day's date
        String collectionName = "contratos_" + day;

        // Check if the collection already exists
        if (database.getCollection(collectionName) == null) {
            database.createCollection(collectionName);
            System.out.println("Collection " + collectionName + " created.");
        } else {
            System.out.println("The collection " + collectionName + " already exists.");
        }
    }

    // Insert dummy data into a collection for the specific day
   public void insertDummyData(int numberOfContracts, String day) {
    String collectionName = "contratos_" + day;

    // Check if collection exists
    boolean collectionExists = database.listCollectionNames()
            .into(new ArrayList<>()) // Convert iterable to a list
            .contains(collectionName);

    if (!collectionExists) {
        database.createCollection(collectionName);
        System.out.println("Created collection: " + collectionName);
    } else {
        System.out.println("Collection " + collectionName + " already exists.");
    }

    // Access the collection
    MongoCollection<Document> collection = database.getCollection(collectionName);

    // Generate dummy data
    Map<String, Map<String, Object>> dailyContracts = DummyDataGenerator.generateDummyData(numberOfContracts, day);

    // Measure time
    long startTime = System.nanoTime();

    // Insert data
    for (Map<String, Object> contractData : dailyContracts.values()) {
        Document contract = new Document(contractData);
        collection.insertOne(contract);
    }

    long endTime = System.nanoTime();
    long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds

    System.out.println(numberOfContracts + " dummy contracts inserted into '" + collectionName + "' collection.");
    System.out.println("Insertion took: " + duration + " ms");
}

    // Helper method to get the current date in a specific format (e.g., "yyyy-MM-dd")
    public String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    // Close the MongoDB connection (if needed)
    public void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("MongoDB connection closed.");
        }
    }
}
