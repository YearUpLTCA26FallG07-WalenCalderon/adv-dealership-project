package com.pluralsight.Entities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractDataManager {

    private static final String file =
            "src/main/resources/contracts.csv";

    public void saveContract(Contract contract) {

        Vehicle vehicle = contract.getVehicleSold();

        String contractData =
                contract.getDateTime() + "|" +
                        contract.getName() + "|" +
                        contract.getGmail() + "|" +
                        vehicle.getVin() + "|" +
                        vehicle.getYear() + "|" +
                        vehicle.getMake() + "|" +
                        vehicle.getModel() + "|" +
                        vehicle.getVehicleType() + "|" +
                        vehicle.getColor() + "|" +
                        vehicle.getOdometer() + "|" +
                        vehicle.getPrice().toPlainString();

        String record;

        if (contract instanceof SalesContract) {

            SalesContract salesContract = (SalesContract) contract;

            record =
                    "SALE|" +
                            contractData + "|" +
                            salesContract.getSalesTaxAmount().toPlainString() + "|" +
                            salesContract.getRecordingFee().toPlainString() + "|" +
                            salesContract.getProcessingFee().toPlainString() + "|" +
                            salesContract.getTotalPrice().toPlainString() + "|" +
                            salesContract.isFinanced() + "|" +
                            salesContract.getMonthlyPayment().toPlainString();

        } else if (contract instanceof LeaseContract) {

            LeaseContract leaseContract = (LeaseContract) contract;

            record =
                    "LEASE|" +
                            contractData + "|" +
                            leaseContract.getExpectedEndingValue().toPlainString() + "|" +
                            leaseContract.getLeaseFee().toPlainString() + "|" +
                            leaseContract.getTotalPrice().toPlainString() + "|" +
                            leaseContract.getMonthlyPayment().toPlainString();

        } else {

            throw new IllegalArgumentException(
                    "Unsupported contract type."
            );
        }

        try {
            FileWriter file = new FileWriter(ContractDataManager.file, true);
            BufferedWriter writer = new BufferedWriter(file);

            writer.write(record);
            writer.newLine();

            writer.close();

        } catch (IOException e) {

            throw new IllegalStateException(
                    "Could not save contract: " + e.getMessage(), e
            );
        }
    }
}