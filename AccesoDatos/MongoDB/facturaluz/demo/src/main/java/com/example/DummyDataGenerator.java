package com.example;

import java.time.YearMonth;
import java.util.*;

import org.bson.Document;

public class DummyDataGenerator {
    public static List<Document> generateDummyData(int numberOfContracts, String date) {
        List<Document> contractsList = new ArrayList<>();

        int daysInMonth = getDaysInMonth(date);

        for (int i = 0; i < numberOfContracts; i++) {
            Document contract = new Document();

            // Client information
            Document cliente = new Document();
            cliente.append("nombre", "Jose");
            cliente.append("apellido", "ApellidoJose");
            contract.append("cliente", cliente);

            // Contract information
            Document contrato = new Document();
            contrato.append("id", "CON" + (2300000 + i)); // Unique contract ID
            contrato.append("fecha_renovacion", date);
            contract.append("contrato", contrato);

            // Consumption information (dias as array of documents)
            List<Document> dias = new ArrayList<>();
            for (int day = 1; day <= daysInMonth; day++) {
                Document dayInfo = new Document();
                dayInfo.append("hora", generateHours());
                dias.add(dayInfo);
            }

            Document consumos = new Document();
            consumos.append("dias", dias);
            contract.append("consumos", consumos);

            contractsList.add(contract);
        }

        return contractsList;
    }

    // Super util para separar fechas y pillar sus longitudes
    private static int getDaysInMonth(String date) {
        String[] parts = date.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        return YearMonth.of(year, month).lengthOfMonth();
    }

    
    private static List<Integer> generateHours() {
        List<Integer> hoursList = new ArrayList<>();
        Random random = new Random();
        for (int hour = 1; hour <= 24; hour++) {
            hoursList.add(random.nextInt(5000)); // Random consumption value
        }
        return hoursList;
    }
    
    
}
