package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DummyDataGenerator {

    public static Map<String, Map<String, Object>> generateDummyData(int numberOfContracts, String day) {
        Map<String, Map<String, Object>> dailyContracts = new HashMap<>();

        for (int i = 1; i <= numberOfContracts; i++) {
            Map<String, Object> contract = new HashMap<>();

            String time = generateRandomTime();
            contract.put("hora", time);

            Map<String, Object> contador = generateContadorData();
            contract.put("contador", contador);

            Map<String, String> cliente = new HashMap<>();
            cliente.put("nombre", "Jose");
            cliente.put("apellido", "ApellidoJose");
            contract.put("cliente", cliente);

            dailyContracts.put("contrato" + i, contract);
        }

        return dailyContracts;
    }

    private static String generateRandomTime() {
        Random random = new Random();
        int hour = random.nextInt(24); 
        int minute = random.nextInt(60); 

        return String.format("%02d:%02d", hour, minute);
    }

    private static Map<String, Object> generateContadorData() {
        Map<String, Object> contador = new HashMap<>();
        
        contador.put("lectura", new Random().nextInt(1000));  
        contador.put("estado", "activo");  
        contador.put("tipo", "digital");  

        return contador;
    }
}
