package com.example;

public class Main {
    public static void main(String[] args) {

        // Create Mongo object and connect to MongoDB
        Mongo mongoDataBase = new Mongo();
        mongoDataBase.mongoConnection();  // Establish connection to MongoDB
        
        // Get the current date as the collection identifier
        String currentDate = "2025-02-3";

        // Create the collection and insert dummy data for today
        mongoDataBase.insertDummyData(500000, currentDate);  // Insert 10 contracts for today
    }
}
