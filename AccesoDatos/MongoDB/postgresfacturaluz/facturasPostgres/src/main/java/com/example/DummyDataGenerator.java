package com.example;

import java.sql.*;
import java.sql.Date;
import java.time.YearMonth;
import java.util.*;

public class DummyDataGenerator {
   
    public static void generateDummyData(Connection connection, int numberOfContracts, String date) throws SQLException {
        Random random = new Random();
        int daysInMonth = getDaysInMonth(date);
        RandomNumberGenerator generator = new RandomNumberGenerator(1, 100000);

        long startTime = System.nanoTime();

        for (int i = 0; i < numberOfContracts; i++) {

            String clienteNombre = "Jose" + (generator.getNextID());
            String clienteApellido = "ApellidoJose";
            String insertClienteSQL = "INSERT INTO clientes (nombre, apellido) VALUES (?, ?) RETURNING id";
            PreparedStatement clienteStmt = connection.prepareStatement(insertClienteSQL);
            clienteStmt.setString(1, clienteNombre);
            clienteStmt.setString(2, clienteApellido);
            ResultSet clienteRs = clienteStmt.executeQuery();
            clienteRs.next();
            int clienteId = clienteRs.getInt(1);


            String insertContratoSQL = "INSERT INTO contratos (cliente_id, fecha_renovacion) VALUES (?, ?)";
            PreparedStatement contratoStmt = connection.prepareStatement(insertContratoSQL);
            contratoStmt.setInt(1, clienteId);
            contratoStmt.setDate(2, Date.valueOf(date + "-01")); 
            contratoStmt.executeUpdate();

            // para consumos
            for (int day = 1; day <= daysInMonth; day++) {
                Integer[] horas = new Integer[24];
                for (int hour = 0; hour < 24; hour++) {
                    horas[hour] = random.nextInt(5000);
                }

                // el array dentro de consumos
                String insertConsumoSQL = "INSERT INTO consumos_abril (cliente_id, fecha_renovacion, dia, horas) VALUES (?, ?, ?, ?)";
                PreparedStatement consumoStmt = connection.prepareStatement(insertConsumoSQL);
                consumoStmt.setInt(1, clienteId);
                consumoStmt.setDate(2, Date.valueOf(date + "-01"));
                consumoStmt.setInt(3, day);
                consumoStmt.setArray(4, connection.createArrayOf("INTEGER", horas));
                consumoStmt.executeUpdate();
            }

            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1000000; 
            System.out.println("Total time to generate and insert dummy data: " + duration + " ms");
        
        }
    }

    public static int getDaysInMonth(String date) {
        String[] parts = date.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        return YearMonth.of(year, month).lengthOfMonth();
    }
}