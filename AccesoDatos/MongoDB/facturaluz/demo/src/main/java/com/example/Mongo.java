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

    public void deleteHourOneByName(String nameOfClient) {
        MongoCollection<Document> collection = database.getCollection("contratos_04_2025");

        collection.updateMany(
            Filters.and(
                Filters.eq("cliente.nombre", nameOfClient) // Filtra por el nombre del cliente
            ),
            Updates.unset("consumos.dias.0.hora1") // Elimina la "hora 9" en el día 1
        );
    }

    public void deleteDocumentByName(String nameOfClient) {
        MongoCollection<Document> collection = database.getCollection("contratos_04_2025");

        collection.deleteMany(
            Filters.and(
                Filters.eq("cliente.nombre", nameOfClient) // Filtra por el nombre del cliente
            )
        );
    }

    public void insertClient(String collectionName, String nombre, String apellido){
        MongoCollection<Document> collection = database.getCollection(collectionName);

        Document contract = new Document();

        Document cliente = new Document();
        cliente.append("nombre", nombre);
        cliente.append("apellido", apellido);
        contract.append("cliente", cliente);

        Document contrato = new Document();
        contrato.append("id", "CON" + (2300000 + new Random().nextInt(3000))); 
        contrato.append("fecha_renovacion", LocalDate.now() );
        contract.append("contrato", contrato);



        //Crea las horas con sus valores de gasto
        Document hoursList = new Document();
        Random random = new Random();
        for (int hour = 1; hour <= 24; hour++) {
            hoursList.append("hora"+ hour,random.nextInt(3)); 
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

        //Document documentToInsert = new Document();
        
    
    
    
        collection.insertOne(contract);

        System.out.println("client Inserted to collection '"+ collectionName +"' with: nombre: " + nombre + "apellido: " + apellido);

    }

}
