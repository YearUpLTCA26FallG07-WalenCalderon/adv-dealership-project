package com.pluralsight.Entities;

import java.time.format.DateTimeFormatter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.StringJoiner;

public class ContractDataManager {

    private static final FileReader reader =
            new FileReader("src/main/resources/contracts.csv");

    public void saveContract(Contract contract) {

        Vehicle vehicle = contract.getVehicleSold();

        StringJoiner data = new StringJoiner("|");

        // Identify contract type
        if (contract instanceof SalesContract) {
            data.add("SALE");
        } else if (contract instanceof LeaseContract) {
            data.add("LEASE");
        } else {
            throw new IllegalArgumentException("Unknown contract type.");
        }

        // Common contract information
        data.add(contract.getDateTime()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd")));

        data.add(contract.getName());
        data.add(contract.getGmail());

        // Vehicle information
        data.add(String.valueOf(vehicle.getVin()));
        data.add(String.valueOf(vehicle.getYear()));
        data.add(vehicle.getMake());
        data.add(vehicle.getModel());
        data.add(String.valueOf(vehicle.getVehicleType()));
        data.add(vehicle.getColor());
        data.add(String.valueOf(vehicle.getOdometer()));
        data.add(String.valueOf(vehicle.getPrice()));

        // Specific contract information
        if (contract instanceof SalesContract sales) {

            data.add(String.valueOf(sales.getSalesTaxAmount()));
            data.add(String.valueOf(sales.getRecordingFee()));
            data.add(String.valueOf(sales.getProcessingFee()));
            data.add(String.valueOf(sales.isFinanced()));

        } else if (contract instanceof LeaseContract lease) {

            data.add(String.valueOf(lease.getExpectedEndingValue()));
            data.add(String.valueOf(lease.getLeaseFee()));
        }

        // Final calculations
        data.add(String.valueOf(contract.getTotalPrice()));
        data.add(String.valueOf(contract.getMonthlyPayment()));

//        // Save contract
//        try {
//
//            Files.createDirectories(FILE_PATH.getParent());
//
//            try (BufferedWriter writer = Files.newBufferedWriter(
//                    FILE_PATH,
//                    StandardCharsets.UTF_8,
//                    StandardOpenOption.CREATE,
//                    StandardOpenOption.APPEND)) {
//
//                writer.write(data.toString());
//                writer.newLine();
//            }
//
//        } catch (IOException e) {
//
//            throw new IllegalStateException(
//                    "Could not save contracts.csv: " + e.getMessage(), e);
//        }
//    }
}