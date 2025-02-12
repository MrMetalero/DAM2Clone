package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

public class Main {

    // [{
    //     "_id": {
    //       "$oid": "67a0cad073f94346df6cec6b"
    //     },
    //     "cliente": {
    //       "apellido": "ApellidoJose",
    //       "nombre": "Jose"
    //     },
    //     "contrato":{
    //       "id": "CON2313123",
    //       "fecha_renovacion": "12-15-2025"
    //     },
    //     "consumos": {
    //       "dias":[
    //         {
    //           "1": [
    //             "hora 0": 0,
    //             "hora 1": 1
    //           ],
    //           "2": [
    //             "hora 0": 0,
    //             "hora 1": 1
    //           ]
    //         },
    //       ]
    //     },
    //   },

    public static void main(String[] args) {

        Mongo mongoDataBase = new Mongo();
        mongoDataBase.mongoConnection();
        List<Integer> integerList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        
        //Nombre de la colección a insertar
        String currentDate = "2025-04";

        //mongoDataBase.insertDummyData(1000, currentDate);  

        //mongoDataBase.deleteHourOneByName("contratos_04_2025","Jose2266");
        //mongoDataBase.deleteDocumentByName("contratos_04_2025","Jose1718");
        //mongoDataBase.insertClient("contratos_04_2025","Javi","Maceda");
        //mongoDataBase.printDocumentsByName("contratos_04_2025","Jose 20290");
        //mongoDataBase.printDocumentsByNameRegex("contratos_04_2025","Jose [1]");
     

        //mongoDataBase.updateOneDocumentByFieldName("contratos_04_2025","Jose 20290","consumos.dias",integerList);

        //mongoDataBase.updateOneDocumentByFieldName("contratos_04_2025","Jose 20290","consumos.dias","UPDATEDSTRING");

        //mongoDataBase.updateOneDocumentByFieldName("contratos_04_2025","Jose 20290","consumos.dias",new Document("UPDATED",new Document("UPDATED SUBDOC","UPDATED VALUE")));
        mongoDataBase.calculateMonthlyBills("contratos_04_2025");
        mongoDataBase.closeConnection();

      
    }
}
