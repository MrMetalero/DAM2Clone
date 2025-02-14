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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.time.LocalDate;

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

        List<Document> dummyContracts = DummyDataGenerator.generateDummyData(numberOfContracts, date);

        long startTime = System.nanoTime();

        collection.insertMany(dummyContracts);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;

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

    public <T> void updateOneDocumentByFieldName(String collectionName, String nameOfClient, String fieldName, T modifiedData) {
        MongoCollection<Document> collection = database.getCollection(collectionName);

        collection.updateOne(
                Filters.eq("cliente.nombre", nameOfClient),
                Updates.set(fieldName, modifiedData));
    }

    public void deleteHourOneByName(String collectionName,String nameOfClient) {
        MongoCollection<Document> collection = database.getCollection(collectionName);

        collection.updateMany(
                Filters.and(
                        Filters.eq("cliente.nombre", nameOfClient) // Filtra por el nombre del cliente
                ),
                Updates.unset("consumos.dias.0.hora1") // Elimina la "hora 9" en el día 1
        );
    }

    public void deleteDocumentByName(String collectionName,String nameOfClient) {
        MongoCollection<Document> collection = database.getCollection(collectionName);

        collection.deleteMany(
                Filters.and(
                        Filters.eq("cliente.nombre", nameOfClient) // Filtra por el nombre del cliente
                ));
    }

    public void printDocumentsByName(String collectionName, String nameOfClient) {
        MongoCollection<Document> collection = database.getCollection(collectionName);

        System.out.println("\nCOINCIDENCIAS:");
        for (Document document : collection.find(
                Filters.or(
                        Filters.eq("cliente.nombre", nameOfClient) // Filtra por el nombre del cliente
                ))) {
            System.out.println(document.toString() + "\n");
        }

    }

    public void printDocumentsByNameRegex(String collectionName, String nameOfClientRegex) {
        MongoCollection<Document> collection = database.getCollection("contratos_04_2025");

        System.out.println("\nCOINCIDENCIAS:");
        for (Document document : collection.find(
                Filters.or(
                        Filters.regex("cliente.nombre", nameOfClientRegex)))) {
            System.out.println(document.toString() + "\n");
        }

    }

    
    //TODO: todavia no sumo los valores de cada día
    public void calculateMonthlyBills(String collectionName) {
        
        MongoCollection<Document> collection = database.getCollection(collectionName);
    
        for (Document document : collection.find()) {
            Object consumosObj = document.get("consumos");
    
            if (consumosObj instanceof Document consumos) {
                Object diasObj = consumos.get("dias");
    
                //Esto lo he tenido que buscar porque no me queria pillar el parse a document
                if (diasObj instanceof List<?> dias) {
                    System.out.println("Document ID: " + document.getObjectId("_id"));

                    for (int dayIndex = 0; dayIndex < dias.size(); dayIndex++) {
                        Object diaObj = dias.get(dayIndex);
                        Double monthlyTotal = 0.0;
    
                        if (diaObj instanceof Document dia) {
                            Double dailyTotal = 0.0;
    
                            for (int i = 1; i <= 24; i++) {
                                String key = "hora" + i;
                                Double value = Double.parseDouble(dia.getString(key).replace(",", ".")) ; 
                                dailyTotal += value;
                            }
    
                            // formateado a 2 decimales mejor
                            //System.out.println("Total consumption for day " + (dayIndex + 1) + ": " + String.format("%.2f", dailyTotal) );
                            monthlyTotal += dailyTotal;
                        }
                        
                        System.out.println("Total consumption for day " + (dayIndex + 1) + ": " + String.format("%.2f", monthlyTotal) );

                    }
                }
            }
        }
    }
    
    
    

    public void insertClient(String collectionName, String nombre, String apellido) {
        MongoCollection<Document> collection = database.getCollection(collectionName);

        Document contract = new Document();

        Document cliente = new Document();
        cliente.append("nombre", nombre);
        cliente.append("apellido", apellido);
        contract.append("cliente", cliente);

        Document contrato = new Document();
        contrato.append("id", "CON" + (2300000 + new Random().nextInt(3000)));
        contrato.append("fecha_renovacion", LocalDate.now());
        contract.append("contrato", contrato);

        // Crea las horas con sus valores de gasto
        Document hoursList = new Document();
        Random random = new Random();
        for (int hour = 1; hour <= 24; hour++) {
            hoursList.append("hora" + hour, random.nextInt(3));
        }

        List<Document> dias = new ArrayList<>();
        List<Document> dayInfo = new ArrayList<>();

        // añade por cada día un documento de las horas con sus valores
        for (int day = 1; day <= DummyDataGenerator.getDaysInMonth(collectionName.substring(11)); day++) {
            dayInfo.add(hoursList);

        }

        // añade por cada día un documento de las horas con sus valores
        for (Document document : dayInfo) {
            dias.add(document);
        }

        // añade a consumos los dias
        Document consumos = new Document();
        consumos.append("dias", dias);
        contract.append("consumos", consumos);

        collection.insertOne(contract);

        System.out.println("client Inserted to collection '" + collectionName + "' with: nombre: " + nombre
                + "apellido: " + apellido);

    }

}
