package com.example;

public class Main {
    public static void main(String[] args) {

        Mongo mongoDataBase = new Mongo();
        mongoDataBase.mongoConnection();  
        
        //Nombre de la colección a insertar
        String currentDate = "2025-02-5";

        mongoDataBase.insertDummyData(500000, currentDate);  
    }
}
