package com.example;

import java.time.YearMonth;
import java.util.*;

public class DummyDataGenerator {
    public static List<Map<String, Object>> generateDummyData(int numberOfContracts, String date) {
        List<Map<String, Object>> contractsList = new ArrayList<>();

        int daysInMonth = getDaysInMonth(date);

        for (int i = 0; i < numberOfContracts; i++) {
            Map<String, Object> contract = new HashMap<>();

            Map<String, String> cliente = new HashMap<>();
            cliente.put("nombre", "Jose");
            cliente.put("apellido", "ApellidoJose");
            contract.put("cliente", cliente);

            Map<String, String> contrato = new HashMap<>();
            contrato.put("id", "CON" + (2300000 + i)); // Unique contract ID
            contrato.put("fecha_renovacion", date);
            contract.put("contrato", contrato);

            Map<String, Map<String, Integer>> dias = new HashMap<>();
            for (int day = 1; day <= daysInMonth; day++) {
                dias.put(String.valueOf(day), generateHours());
            }
            contract.put("consumos", Collections.singletonMap("dias", dias));

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

    // 
    private static Map<String, Integer> generateHours() {
        // Linked hashmap me ayuda a crear los días siempre en orden 
        Map<String, Integer> hoursMap = new LinkedHashMap<>(); 
        Random random = new Random();
        for (int hour = 1; hour <= 24; hour++) {
            hoursMap.put("hora " + hour, random.nextInt(5000)); // numero de consumo random
        }
        return hoursMap;
    }
}
