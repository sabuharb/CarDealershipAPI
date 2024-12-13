package com.pluralsight.dealership_api.models;

public abstract class Contract {
    // Data fields
    protected String dateOfContract, customerName, customerEmail;
    protected boolean vehicleSold;
    protected  double totalPrice, monthlyPayment;

    // Constructor
    public Contract(String dateOfContract, String customerName, String customerEmail, boolean vehicleSold) {
        this.dateOfContract = dateOfContract;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
    }

    // Abstract methods
    public abstract double getTotalPrice();

    public abstract double getMonthlyPayment();

    // Getters and Setters
    public String getDateOfContract() {
        return dateOfContract;
    }

    public void setDateOfContract(String dateOfContract) {
        this.dateOfContract = dateOfContract;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public boolean isVehicleSold() {
        return vehicleSold;
    }

    public void setVehicleSold(boolean vehicleSold) {
        this.vehicleSold = vehicleSold;
    }
}
