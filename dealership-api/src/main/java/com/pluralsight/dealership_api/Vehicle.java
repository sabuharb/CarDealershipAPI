/*
Vehicle will hold information about a specific vehicle
 */

package com.pluralsight.dealership_api;

public class Vehicle {
    // Data fields
    private int year;
    private String vin, make, model, vehicleType, color;
    private float odometer, price;


    // Constructor
    public Vehicle(String vin, int year, String make, String model, String vehicleType, String color, float odometer, float price) {
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.vehicleType = vehicleType;
        this.color = color;
        this.odometer = odometer;
        this.price = price;
    }

    // Getters and Setters
    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getOdometer() {
        return odometer;
    }

    public void setOdometer(float odometer) {
        this.odometer = odometer;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


    @Override
    public String toString(){
        return String.format("%-19s|%-6s|%-15s|%-10s|%-14s|%-8s|%-10.1f|%-10.2f",
                this.vin,this.year, this.make, this.model, this.vehicleType, this.color,
                this.odometer, this.price);
    }

}