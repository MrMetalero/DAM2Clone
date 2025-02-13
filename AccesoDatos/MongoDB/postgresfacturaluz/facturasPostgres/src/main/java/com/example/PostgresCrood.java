package com.example;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Random;

public class PostgresCrood {
    
    private Connection connection;
    
        public PostgresCrood(Connection connection) {
            this.connection = connection;
    }


    
    public void insertClient(String nombre, String apellido) throws SQLException {
        String insertClienteSQL = "INSERT INTO clientes (nombre, apellido) VALUES (?, ?) RETURNING id";
        try (PreparedStatement clienteStmt = connection.prepareStatement(insertClienteSQL)) {
            clienteStmt.setString(1, nombre);
            clienteStmt.setString(2, apellido);
            ResultSet clienteRs = clienteStmt.executeQuery();
            clienteRs.next();
            int clienteId = clienteRs.getInt(1);
            System.out.println("Inserted client: " + nombre + " " + apellido + " with ID: " + clienteId);
        }
    }

    public void insertContrato(int clienteId, String fechaRenovacion) throws SQLException {
        String insertContratoSQL = "INSERT INTO contratos (cliente_id, fecha_renovacion) VALUES (?, ?)";
        try (PreparedStatement contratoStmt = connection.prepareStatement(insertContratoSQL)) {
            contratoStmt.setInt(1, clienteId);
            contratoStmt.setDate(2, Date.valueOf(fechaRenovacion));
            contratoStmt.executeUpdate();
            System.out.println("Inserted contract for client ID: " + clienteId);
        }
    }

    public void insertConsumo(int clienteId, String fechaRenovacion, int dia, Integer[] horas) throws SQLException {
        String insertConsumoSQL = "INSERT INTO consumos (cliente_id, fecha_renovacion, dia, horas) VALUES (?, ?, ?, ?)";
        try (PreparedStatement consumoStmt = connection.prepareStatement(insertConsumoSQL)) {
            consumoStmt.setInt(1, clienteId);
            consumoStmt.setDate(2, Date.valueOf(fechaRenovacion));
            consumoStmt.setInt(3, dia);
            consumoStmt.setArray(4, connection.createArrayOf("INTEGER", horas));
            consumoStmt.executeUpdate();
            System.out.println("Inserted consumption data for client ID: " + clienteId + ", day: " + dia);
        }
    }

    public void deleteClient(int clienteId) throws SQLException {
        String deleteClientSQL = "DELETE FROM clientes WHERE id = ?";
        try (PreparedStatement deleteStmt = connection.prepareStatement(deleteClientSQL)) {
            deleteStmt.setInt(1, clienteId);
            deleteStmt.executeUpdate();
            System.out.println("Deleted client with ID: " + clienteId);
        }
    }

    public void calculateMonthlyBills(int clienteId, String fechaRenovacion) throws SQLException {
        String calculateBillsSQL = "SELECT dia, horas FROM consumos WHERE cliente_id = ? AND fecha_renovacion = ?";
        try (PreparedStatement billsStmt = connection.prepareStatement(calculateBillsSQL)) {
            billsStmt.setInt(1, clienteId);
            billsStmt.setDate(2, Date.valueOf(fechaRenovacion));
            ResultSet rs = billsStmt.executeQuery();

            double totalBill = 0;
            while (rs.next()) {
                int dia = rs.getInt("dia");
                Array horasArray = rs.getArray("horas");
                Integer[] horas = (Integer[]) horasArray.getArray();

                double dailyBill = Arrays.stream(horas).mapToInt(Integer::intValue).sum();
                totalBill += dailyBill;

                System.out.println("Day: " + dia + ", Daily Bill: " + dailyBill);
            }
            System.out.println("Total Monthly Bill: " + totalBill);
        }
    }
}
