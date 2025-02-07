package com.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public void insertDummyData(int numberOfContracts, String date) {
        String collectionName = "contratos_" + date.substring(5, 7) + "_" + date.substring(0, 4); // MM_YYYY format

        boolean collectionExists = database.listCollectionNames().into(new ArrayList<>()).contains(collectionName);

        if (!collectionExists) {
            database.createCollection(collectionName);
            System.out.println("Created collection: " + collectionName);
        } else {
            System.out.println("Collection " + collectionName + " already exists.");
        }

        MongoCollection<Document> collection = database.getCollection(collectionName);

        // Generate dummy data with Documents instead of Map
        List<Document> dummyContracts = DummyDataGenerator.generateDummyData(numberOfContracts, date);

        long startTime = System.nanoTime();

        collection.insertMany(dummyContracts);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;

        System.out.println(numberOfContracts + " dummy contracts inserted into '" + collectionName + "' collection.");
        System.out.println("Insertion took: " + duration + " ms");
    }

    public void deleteDocument() {
        MongoCollection<Document> collection = database.getCollection("contratos_04_2025");

        // To remove the "09" hour consumption for a specific client and date
        collection.updateMany(
            Filters.and(
                Filters.eq("cliente.nombre", "Jose"), // Filtra por el nombre del cliente
                Filters.eq("consumos.dias.1", new Document()) // Asegura que estamos trabajando con el día 1
            ),
            Updates.unset("consumos.dias.1.9") // Elimina la "hora 9" en el día 1
        );
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
