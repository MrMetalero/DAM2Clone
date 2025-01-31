package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgresConnection {
    
    // Database URL, username, and password
    String url = "jdbc:postgresql://localhost:5432/ciclismo"; 
    String username = "mati";
    String password = "mati"; 

    // Connection object
    Connection connection = null;

     public void establishConnectionPostgresql(){
             try {
                 // Establish the connection
                 connection = DriverManager.getConnection(url, username, password);
                 System.out.println("Connection established successfully!");
                 // Perform database operations (e.g., queries)
                 String line ;
                 Statement st = connection.createStatement();
                 ResultSet rs = st.executeQuery("SELECT * FROM maillot");
                 while (rs.next()) {  
                    line = rs.getString(1); 
                    System.out.println(line); 
                }
                 
             } catch (SQLException e) {
                 e.printStackTrace();
             } finally {
                 // Close the connection in the 'finally' block to ensure it's always closed
                 try {
                     if (connection != null) {
                         connection.close();
                         System.out.println("Connection closed.");
                     }
                 } catch (SQLException e) {
                     e.printStackTrace();
                 }
             }
     }
        
}

