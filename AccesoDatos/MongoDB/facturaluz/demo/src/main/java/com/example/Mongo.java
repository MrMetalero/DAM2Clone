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

    public void mongoConnection() {
        String connectionString = "mongodb://mati:mati@localhost:27017"; 

        if (mongoClient == null) {
            try {
                mongoClient = MongoClients.create(connectionString); 
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

    public void createCollection(String day) {
        String collectionName = "contratos_" + day;

        if (database.getCollection(collectionName) == null) {
            database.createCollection(collectionName);
            System.out.println("Collection " + collectionName + " created.");
        } else {
            System.out.println("The collection " + collectionName + " already exists.");
        }
    }

   public void insertDummyData(int numberOfContracts, String day) {
    String collectionName = "contratos_" + day;

    boolean collectionExists = database.listCollectionNames().into(new ArrayList<>()).contains(collectionName);

    if (!collectionExists) {
        database.createCollection(collectionName);
        System.out.println("Created collection: " + collectionName);
    } else {
        System.out.println("Collection " + collectionName + " already exists.");
    }

    MongoCollection<Document> collection = database.getCollection(collectionName);

    Map<String, Map<String, Object>> dailyContracts = DummyDataGenerator.generateDummyData(numberOfContracts, day);

    long startTime = System.nanoTime();

    for (Map<String, Object> contractData : dailyContracts.values()) {
        Document contract = new Document(contractData);
        collection.insertOne(contract);
    }

    long endTime = System.nanoTime();
    long duration = (endTime - startTime) / 1_000_000; 

    System.out.println(numberOfContracts + " dummy contracts inserted into '" + collectionName + "' collection.");
    System.out.println("Insertion took: " + duration + " ms");
}

    public String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    public void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("MongoDB connection closed.");
        }
    }
}
