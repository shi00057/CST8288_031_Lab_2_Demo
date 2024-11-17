/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.algonquincollege.lab2.DAO.impl;

import com.algonquincollege.lab2.DAO.IndyWinnerDAO;
import com.algonquincollege.lab2.DB.DatabaseConnection;
import com.algonquincollege.lab2.Model.IndyWinner;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the {@link IndyWinnerDAO} interface for managing IndyCar race winners.
 * 
 * <p>This class interacts with the database to retrieve data related to IndyCar winners.
 * It uses JDBC for database operations and relies on the {@link DatabaseConnection}
 * singleton for database connectivity.</p>
 * 
 * <p>The data retrieved includes details about the year of the win, driver's name,
 * average speed, and the driver's country.</p>
 * 
 * @author guokai shi
 */
public class IndyWinnerDAOImpl implements IndyWinnerDAO {

    // Database connection constants (used for demonstration)
    private static final String DB_URL = "jdbc:mysql://localhost:3306/IndyWinners";
    private static final String USER = "root";
    private static final String PASSWORD = "*********";

    /**
     * Retrieves a list of IndyCar race winners from the database with pagination support.
     * 
     * <p>This method executes a SQL query to fetch race winners, ordered by the year
     * of the win in descending order. The results are limited by the specified limit
     * and offset parameters.</p>
     * 
     * @param offset the number of rows to skip before starting to return results
     * @param limit the maximum number of rows to return
     * @return a list of {@link IndyWinner} objects containing race winner details
     */
    @Override
    public List<IndyWinner> getIndyWinners(int offset, int limit) {
        List<IndyWinner> winners = new ArrayList<>();
        String query = "SELECT * FROM IndyWinners ORDER BY year DESC LIMIT ? OFFSET ?";

        try {
            // Get a database connection from the singleton instance
            Connection con = DatabaseConnection.getInstance();
            // Prepare the SQL statement with the specified limit and offset
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, limit);
            stmt.setInt(2, offset);

            // Execute the query and process the result set
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Create an IndyWinner object for each row and add it to the list
                    winners.add(new IndyWinner(
                        rs.getInt("YEAR"),
                        rs.getString("DRIVER"),
                        rs.getDouble("AVERAGESPEED"),
                        rs.getString("COUNTRY")
                    ));
                }
            }
        } catch (SQLException e) {
            // Print stack trace for any SQL exception encountered
            e.printStackTrace();
        }
        return winners;
    }
}

