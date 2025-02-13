package com.example;

import java.sql.*;
import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/facturaluz";
        String user = "postgres";
        String password = "postgres";

      try (Connection connection = DriverManager.getConnection(url, user, password)) {
            PostgresCrood postgresOps = new PostgresCrood(connection);

            //DUMMY DATA A SACO
            DummyDataGenerator.generateDummyData(connection, 1000, "2025-04");

            postgresOps.insertClient("Jose", "ApellidoJose");

            postgresOps.insertContrato(1, "2025-04-01");

            Integer[] horas = new Integer[24];
            Arrays.fill(horas, new Random().nextInt(2)); 
            postgresOps.insertConsumo(1, "2025-04-01", 1, horas);

            postgresOps.calculateMonthlyBills(1, "2025-04-01");

            postgresOps.deleteClient(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}