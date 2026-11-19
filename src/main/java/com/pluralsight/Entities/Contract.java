package com.pluralsight.Entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
public abstract class Contract {

    private LocalDateTime dateTime;
    private String name;
    private String gmail;
    private Vehicle vehicleSold;

    public Contract(LocalDateTime dateTime, String name,
                    String gmail, Vehicle vehicleSold) {

        this.dateTime = dateTime;
        this.name = name;
        this.gmail = gmail;
        this.vehicleSold = vehicleSold;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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

    public Vehicle getVehicleSold() {
        return vehicleSold;
    }

    public void setVehicleSold(Vehicle vehicleSold) {
        this.vehicleSold = vehicleSold;
    }

    public abstract BigDecimal getTotalPrice();

    public abstract BigDecimal getMonthlyPayment();
}