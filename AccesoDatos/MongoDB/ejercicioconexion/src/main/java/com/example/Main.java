package com.example;



public class Main {
    public static void main(String[] args) {
        PostgresConnection postgresConnect = new PostgresConnection();
        postgresConnect.establishConnectionPostgresql();

        MongoConnection mongoConnect = new MongoConnection();
        mongoConnect.mongoConnection();

        mongoConnect.createCollection("addedCollection");
        mongoConnect.addDocument("addedCollection");
    }
    
}