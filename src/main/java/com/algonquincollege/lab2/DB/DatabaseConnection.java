/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.algonquincollege.lab2.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Provides a singleton implementation for establishing and managing a database connection.
 * 
 * <p>This class uses the Singleton design pattern to ensure only one instance of the database 
 * connection exists throughout the application. It provides methods to get the connection 
 * instance and to close the connection when no longer needed.</p>
 * 
 * <p>Database URL, user, and password are specified as constants. Make sure to update these 
 * values to match your database configuration.</p>
 * 
 * @author guokai shi
 */
public class DatabaseConnection {

    // Database connection details
    private static final String DB_URL = 
        "jdbc:mysql://localhost:3306/IndyWinners?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "********";

    // Singleton instance of the database connection
    private static Connection connection = null;

    // Private constructor to prevent instantiation
    private DatabaseConnection() { }

    /**
     * Retrieves the singleton instance of the database connection.
     * 
     * <p>If the connection does not already exist, this method initializes it 
     * by loading the JDBC driver and connecting to the database using the specified
     * URL, username, and password.</p>
     * 
     * @return the singleton instance of the database connection
     */
    public static Connection getInstance() {
        if (connection == null) {
            synchronized (DatabaseConnection.class) {
                if (connection == null) {
                    try {
                        // Load the MySQL JDBC driver
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        // Establish the database connection
                        connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                        System.out.println("Database connection established.");
                    } catch (ClassNotFoundException e) {
                        System.err.println("MySQL JDBC Driver not found.");
                        e.printStackTrace();
                    } catch (SQLException e) {
                        System.err.println("Failed to connect to database.");
                        e.printStackTrace();
                    }
                }
            }
        }
        return connection;
    }

    /**
     * Closes the database connection if it exists.
     * 
     * <p>This method should be called when the application no longer needs the database connection
     * to ensure proper resource management.</p>
     */
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.err.println("Failed to close database connection.");
                e.printStackTrace();
            }
        }
    }
}
