package com.pluralsight.Entities;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDateTime;

public class LeaseContract extends Contract {

    private static final BigDecimal ENDING_VALUE_RATE =
            new BigDecimal("0.50");

    private static final BigDecimal LEASE_FEE_RATE =
            new BigDecimal("0.07");

    private static final BigDecimal ANNUAL_INTEREST_RATE =
            new BigDecimal("0.04");

    private static final int LEASE_MONTHS = 36;


    public LeaseContract(LocalDateTime dateTime, String name,
                         String gmail, Vehicle vehicleSold) {

        super(dateTime, name, gmail, vehicleSold);

        // A vehicle cannot be leased if it is over 3 years old.
        int vehicleAge = dateTime.getYear() - vehicleSold.getYear();

        if (vehicleAge > 3) {
            throw new IllegalArgumentException(
                    "Vehicles older than 3 years cannot be leased."
            );
        }
    }


    public BigDecimal getExpectedEndingValue() {

        return getVehicleSold()
                .getPrice()
                .multiply(ENDING_VALUE_RATE)
                .setScale(2, RoundingMode.HALF_UP);
    }


    public BigDecimal getLeaseFee() {

        return getVehicleSold()
                .getPrice()
                .multiply(LEASE_FEE_RATE)
                .setScale(2, RoundingMode.HALF_UP);
    }


    @Override
    public BigDecimal getTotalPrice() {

        return getExpectedEndingValue()
                .add(getLeaseFee());
    }


    @Override
    public BigDecimal getMonthlyPayment() {

        MathContext mc = MathContext.DECIMAL128;

        BigDecimal monthlyRate = ANNUAL_INTEREST_RATE.divide(
                BigDecimal.valueOf(12),
                mc
        );

        BigDecimal factor = BigDecimal.ONE
                .add(monthlyRate)
                .pow(LEASE_MONTHS, mc);

        BigDecimal numerator = getTotalPrice()
                .multiply(monthlyRate)
                .multiply(factor);

        BigDecimal denominator = factor.subtract(BigDecimal.ONE);

        return numerator.divide(
                denominator,
                2,
                RoundingMode.HALF_UP
        );
    }
}