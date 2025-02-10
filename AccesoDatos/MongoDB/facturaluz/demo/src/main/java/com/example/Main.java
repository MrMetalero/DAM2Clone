package com.example;

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
        
        //Nombre de la colección a insertar
        String currentDate = "2025-04";

        //mongoDataBase.insertDummyData(1000, currentDate);  

        //mongoDataBase.deleteHourOneByName("Jose2266");
        //mongoDataBase.deleteDocumentByName("Jose1718");
        //mongoDataBase.insertClient("contratos_04_2025","Javi","Maceda");
        mongoDataBase.closeConnection();
    }
}
