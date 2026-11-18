package com.pluralsight.Entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public abstract class Contract {

//    Contract will hold information common to all contracts. It should be an abstract
//    class as you can't create a generic contract.
//            • Date (as string) of contract
//• Customer name
//• Customer email
//• Vehicle sold
//• Total price
//• Monthly payment
//    Methods will include a constructor and getters and setters for all fields except total
//    price and monthly payment
    private LocalDateTime dateTime;
    private String name;
    private String gmail;
    private String vehicleSold;
    private final BigDecimal totalPrice;
    private final BigDecimal monthlyPayment;


    public Contract(LocalDateTime dateTime, String name, String gmail, String vehicleSold, BigDecimal totalPrice, BigDecimal monthlyPayment) {
        this.dateTime = this.dateTime = LocalDateTime.now();;
        this.name = name;
        this.gmail = gmail;
        this.vehicleSold = vehicleSold;
        this.totalPrice = totalPrice;
        this.monthlyPayment = monthlyPayment;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getVehicleSold() {
        return vehicleSold;
    }

    public void setVehicleSold(String vehicleSold) {
        this.vehicleSold = vehicleSold;
    }

    public abstract BigDecimal getTotalPrice();

    public abstract BigDecimal getMonthlyPayment();
}
