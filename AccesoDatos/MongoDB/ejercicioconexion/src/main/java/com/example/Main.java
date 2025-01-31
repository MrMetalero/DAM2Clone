package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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