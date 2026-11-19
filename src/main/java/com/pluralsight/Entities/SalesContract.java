package com.pluralsight.Entities;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDateTime;

public class SalesContract extends Contract {

    private static final BigDecimal TAX_RATE = new BigDecimal("0.05");

    private static final BigDecimal RECORDING_FEE = new BigDecimal("100.00");

    private static final BigDecimal PRICE_THRESHOLD = new BigDecimal("10000.00");

    private static final BigDecimal LOW_PROCESSING_FEE = new BigDecimal("295.00");

    private static final BigDecimal HIGH_PROCESSING_FEE = new BigDecimal("495.00");

    private boolean financed;

    public SalesContract(LocalDateTime dateTime, String name,
                         String gmail, Vehicle vehicleSold,
                         boolean financed) {

        super(dateTime, name, gmail, vehicleSold);

        this.financed = financed;
    }

    public BigDecimal getSalesTaxAmount() {
        return getVehicleSold().getPrice()
                .multiply(TAX_RATE)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getRecordingFee() {
        return RECORDING_FEE;
    }

    public BigDecimal getProcessingFee() {

        BigDecimal vehiclePrice = getVehicleSold().getPrice();

        if (vehiclePrice.compareTo(PRICE_THRESHOLD) >= 0) {
            return HIGH_PROCESSING_FEE;
        }

        return LOW_PROCESSING_FEE;
    }

    public boolean isFinanced() {
        return financed;
    }

    public void setFinanced(boolean financed) {
        this.financed = financed;
    }

    @Override
    public BigDecimal getTotalPrice() {

        BigDecimal vehiclePrice = getVehicleSold().getPrice();

        return vehiclePrice
                .add(getSalesTaxAmount())
                .add(getRecordingFee())
                .add(getProcessingFee());
    }

    @Override
    public BigDecimal getMonthlyPayment() {

        if (!financed) {
            return new BigDecimal("0.00");
        }

        BigDecimal vehiclePrice = getVehicleSold().getPrice();

        BigDecimal annualRate;
        int months;

        if (vehiclePrice.compareTo(PRICE_THRESHOLD) >= 0) {
            annualRate = new BigDecimal("0.0425");
            months = 48;
        } else {
            annualRate = new BigDecimal("0.0525");
            months = 24;
        }

        MathContext mc = MathContext.DECIMAL128;

        BigDecimal monthlyRate = annualRate.divide(
                BigDecimal.valueOf(12), mc
        );

        BigDecimal factor = BigDecimal.ONE
                .add(monthlyRate)
                .pow(months, mc);

        BigDecimal numerator = getTotalPrice()
                .multiply(monthlyRate)
                .multiply(factor);

        BigDecimal denominator = factor.subtract(BigDecimal.ONE);

        return numerator.divide(
                denominator, 2, RoundingMode.HALF_UP
        );
    }
}