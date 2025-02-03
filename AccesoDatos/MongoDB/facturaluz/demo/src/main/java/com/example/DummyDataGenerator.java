package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DummyDataGenerator {

    public static Map<String, Map<String, Object>> generateDummyData(int numberOfContracts, String day) {
        Map<String, Map<String, Object>> dailyContracts = new HashMap<>();

        // Generate contracts for the given day
        for (int i = 1; i <= numberOfContracts; i++) {
            Map<String, Object> contract = new HashMap<>();

            // Add 'hora' (random time for the contract)
            String time = generateRandomTime();
            contract.put("hora", time);

            // Add 'contador' with some data
            Map<String, Object> contador = generateContadorData();
            contract.put("contador", contador);

            // Add 'cliente' (static data for now)
            Map<String, String> cliente = new HashMap<>();
            cliente.put("nombre", "Jose");
            cliente.put("apellido", "ApellidoJose");
            contract.put("cliente", cliente);

            // Add contract to the map with a key "contrato" + i
            dailyContracts.put("contrato" + i, contract);
        }

        return dailyContracts;
    }

    // Helper method to generate a random time in the format HH:mm
    private static String generateRandomTime() {
        Random random = new Random();
        int hour = random.nextInt(24); // Random hour (0 to 23)
        int minute = random.nextInt(60); // Random minute (0 to 59)

        // Format time as HH:mm
        return String.format("%02d:%02d", hour, minute);
    }

    // Helper method to generate some dummy data for "contador"
    private static Map<String, Object> generateContadorData() {
        Map<String, Object> contador = new HashMap<>();
        
        // Some dummy data inside "contador" (random values for demonstration)
        contador.put("lectura", new Random().nextInt(1000));  // Random reading value
        contador.put("estado", "activo");  // Fixed state
        contador.put("tipo", "digital");  // Fixed type

        return contador;
    }
}
