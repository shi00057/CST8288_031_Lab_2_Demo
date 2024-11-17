/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.algonquincollege.lab2.Model;

/**
 * Represents an IndyCar race winner with details about the year of the win,
 * driver's name, average speed, and country.
 * 
 * <p>This class provides getters and setters for all the fields to allow access 
 * and modification of the data.</p>
 * 
 * @author guokai shi
 */
public class IndyWinner {
    private int year; // The year of the race win
    private String driver; // The name of the winning driver
    private double averageSpeed; // The average speed during the race in mph or kph
    private String country; // The country of the winning driver

    /**
     * Constructs an IndyWinner object with the specified details.
     * 
     * @param year the year of the win
     * @param driver the name of the winning driver
     * @param averageSpeed the average speed during the race
     * @param country the country of the winning driver
     */
    public IndyWinner(int year, String driver, double averageSpeed, String country) {
        this.year = year;
        this.driver = driver;
        this.averageSpeed = averageSpeed;
        this.country = country;
    }

    /**
     * Gets the year of the race win.
     * 
     * @return the year of the win
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the year of the race win.
     * 
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Gets the name of the winning driver.
     * 
     * @return the name of the driver
     */
    public String getDriver() {
        return driver;
    }

    /**
     * Sets the name of the winning driver.
     * 
     * @param driver the name of the driver to set
     */
    public void setDriver(String driver) {
        this.driver = driver;
    }

    /**
     * Gets the average speed during the race.
     * 
     * @return the average speed of the race
     */
    public double getAverageSpeed() {
        return averageSpeed;
    }

    /**
     * Sets the average speed during the race.
     * 
     * @param averageSpeed the average speed to set
     */
    public void setAverageSpeed(double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    /**
     * Gets the country of the winning driver.
     * 
     * @return the country of the driver
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country of the winning driver.
     * 
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }
}
