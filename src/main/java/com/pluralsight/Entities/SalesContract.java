package com.pluralsight.Entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SalesContract extends Contract {
//    A SalesContract will include the following additional information:

//• Recording Fee ($100)
//• Processing fee ($295 for vehicles under $10,000 and $495 for all others)
//• Whether they want to finance (yes/no)
//• Monthly payment (if financed) based on:
//            • All loans are at 4.25% for 48 months if the price is $10,000 or more
//• Otherwise they are at 5.25% for 24 months
    private final BigDecimal taxAmount; //            • Sales Tax Amount (5%)
    private static final BigDecimal recordingFee = new BigDecimal("100.00");


    public SalesContract(LocalDateTime dateTime, String name, String gmail, String vehicleSold, BigDecimal totalPrice, BigDecimal monthlyPayment, BigDecimal taxAmount) {
        super(dateTime, name, gmail, vehicleSold, totalPrice, monthlyPayment);

        this.taxAmount = taxAmount;


    }

    public processingFee(){
        if(//todo if the total price is more than 10,000 tehn $495 else $295)

    }

    public boolean isFinanced(){

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
