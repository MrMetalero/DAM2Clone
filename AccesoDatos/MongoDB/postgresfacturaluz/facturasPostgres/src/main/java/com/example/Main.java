package com.example;

import java.sql.*;
import java.util.Arrays;
import java.util.Random;

public class Main {
    public enum Consumos {
        consumos_enero,

        consumos_febrero,

        consumos_marzo,

        consumos_abril,

        consumos_mayo,

        consumos_junio,

        consumos_julio,

        consumos_agosto,

        consumos_septiembre,

        consumos_octubre,

        consumos_noviembre,

        consumos_diciembre,
    }

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/facturaluz";
        String user = "mati";
        String password = "mati";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            PostgresCrood postgresOps = new PostgresCrood(connection);

            // DUMMY DATA A SACO
            //DummyDataGenerator.generateDummyData(connection, 1000, "2025-04");

            //postgresOps.insertClient("Jose", "ApellidoJose");

            //postgresOps.insertContrato(1, "2025-04-01");

            Double[] horas = new Double[24];
            Arrays.fill(horas, new Random().nextDouble(0,2));
            //postgresOps.insertConsumo(1231314121, "2025-04-01", 1, horas);

            //postgresOps.calculateMonthlyBills(1, "2025-04-01");

            //postgresOps.deleteClient(1);

            postgresOps.insertConsumoModular(1231561421,"2025-04-01", 1, horas,Consumos.consumos_abril);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}