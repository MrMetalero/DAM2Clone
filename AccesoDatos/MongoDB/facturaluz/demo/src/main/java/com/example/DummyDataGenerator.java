package com.example;

import java.time.YearMonth;
import java.util.*;

import org.bson.Document;

public class DummyDataGenerator {
    public static List<Document> generateDummyData(int numberOfContracts, String date) {
        RandomNumberGenerator generator = new RandomNumberGenerator(1, 100000);

        List<Document> contractsList = new ArrayList<>();

        int daysInMonth = getDaysInMonth(date);

        for (int i = 0; i < numberOfContracts; i++) {
            Document contract = new Document();

            Document cliente = new Document();
            cliente.append("nombre", "Jose "+ generator.getNextID());
            cliente.append("apellido", "ApellidoJose" );
            contract.append("cliente", cliente);

            Document contrato = new Document();
            contrato.append("id", "CON" + (2300000 + i)); 
            contrato.append("fecha_renovacion", date);
            contract.append("contrato", contrato);



            //Crea las horas con sus valores de gasto
            Document hoursList = new Document();
            Random random = new Random();
            for (int hour = 1; hour <= 24; hour++) {
                hoursList.append("hora"+ hour,random.nextInt(5000)); 
            }


            List<Document> dias = new ArrayList<>();
            List<Document> dayInfo = new ArrayList<>();

            // añade por cada día un documento de las horas con sus valores
            for (int day = 1; day <= daysInMonth; day++) {
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

            contractsList.add(contract);
        }

        return contractsList;
    }

    // Super util para separar fechas y pillar sus longitudes
    public static int getDaysInMonth(String date) {
        int returnValue = 0;
        if (date.contains("-")) {
        String[] parts = date.split("-");
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            returnValue = YearMonth.of(year, month).lengthOfMonth();
    }else if (date.contains("_")) {
        String[] parts = date.split("_");
            int year = Integer.parseInt(parts[1]);
            int month = Integer.parseInt(parts[0]);
            returnValue = YearMonth.of(year, month).lengthOfMonth();

    }
    return returnValue;

        
    }


   
    
    
}
