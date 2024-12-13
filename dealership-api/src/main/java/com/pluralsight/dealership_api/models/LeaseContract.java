package com.pluralsight.dealership_api.models;

public class LeaseContract extends Contract {
    // Data fields
    private float expectedEndingValue, leaseFee;
    private double originalPrice;

    public LeaseContract(String dateOfContract, String customerName, String customerEmail, boolean vehicleSold) {
        super(dateOfContract, customerName, customerEmail, vehicleSold);
    }

    // Override parent method
    @Override
    public double getTotalPrice(){
        this.totalPrice = this.originalPrice + (this.originalPrice * 0.50) +
                (this.originalPrice * 0.07);
        return this.totalPrice;
    }

    @Override
    public double getMonthlyPayment(){
        return (this.totalPrice * 0.04) / 36;
    }
}
