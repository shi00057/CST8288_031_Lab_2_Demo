/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.algonquincollege.lab2.DAO;

import com.algonquincollege.lab2.Model.IndyWinner;
import java.util.List;

/**
 * Data Access Object (DAO) interface for managing IndyCar race winners.
 * 
 * <p>This interface defines the contract for accessing and retrieving data related
 * to IndyCar race winners. Implementations of this interface are responsible for
 * interacting with the database to perform operations such as retrieving data.</p>
 * 
 * <p>Provides a method to retrieve race winner details with support for pagination.</p>
 * 
 * @author guokai shi
 */
public interface IndyWinnerDAO {

    /**
     * Retrieves a list of IndyCar race winners with pagination support.
     * 
     * <p>This method fetches a list of {@link IndyWinner} objects containing details
     * such as the year of the win, driver's name, average speed, and the driver's country.
     * Results are limited based on the provided offset and limit parameters.</p>
     * 
     * @param offset the number of rows to skip before starting to return results
     * @param limit the maximum number of rows to return
     * @return a list of {@link IndyWinner} objects representing the race winners
     */
    List<IndyWinner> getIndyWinners(int offset, int limit);
}
