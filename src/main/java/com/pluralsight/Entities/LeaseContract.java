package com.pluralsight.Entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LeaseContract extends Contract {

    public LeaseContract(LocalDateTime dateTime, String name, String gmail, String vehicleSold, BigDecimal totalPrice, BigDecimal monthlyPayment) {
        super(dateTime, name, gmail, vehicleSold, totalPrice, monthlyPayment);



    }

    @Override
    public BigDecimal getTotalPrice() {
        return null;
    }

    @Override
    public BigDecimal getMonthlyPayment() {
        return null;
    }


}
