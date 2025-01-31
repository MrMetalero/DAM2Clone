package com.example;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class MongoConnection {
    
    public MongoDatabase database;

    public  void mongoConnection(){
        String connectionString = "mongodb://mati:mati@localhost:27017"; 

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            System.out.println("Connected to MongoDB!");

            database = mongoClient.getDatabase("basedatos1");
            
    
    
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error conectando a mongoDB!");
        }
    }

    public void createCollection(String documentName){
        database.createCollection(documentName);
        
    }

    public void addDocument(String collectionTargetName){
        var collection = database.getCollection(collectionTargetName);
        collection.insertOne(
            new Document("Nombre","Jose")
        );
        
    }

}
