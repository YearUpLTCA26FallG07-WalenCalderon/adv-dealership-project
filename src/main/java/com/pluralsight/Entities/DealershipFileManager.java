package com.pluralsight.Entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

public class DealershipFileManager {

    private static final String file =
            "src/main/resources/inventory.csv";

    public Dealership getDealership() {

        try {
            FileReader file = new FileReader(DealershipFileManager.file);
            BufferedReader reader = new BufferedReader(file);

            String line = reader.readLine();

            if (line == null) {
                reader.close();
                throw new IOException("Inventory file is empty.");
            }

            String[] info = line.split("\\|", -1);

            if (info.length != 3) {
                reader.close();
                throw new IOException("Invalid dealership information.");
            }

            Dealership dealership = new Dealership(
                    info[0],
                    info[1],
                    info[2]
            );

            while ((line = reader.readLine()) != null) {

                if (line.isBlank()) {
                    continue;
                }

                String[] parts = line.split("\\|", -1);

                if (parts.length != 8) {
                    throw new IOException("Invalid vehicle record: " + line);
                }

                Vehicle vehicle = new Vehicle(
                        Integer.parseInt(parts[0]),
                        Integer.parseInt(parts[1]),
                        parts[2],
                        parts[3],
                        parts[4],
                        parts[5],
                        Integer.parseInt(parts[6]),
                        new BigDecimal(parts[7])
                );

                dealership.addVehicle(vehicle);
            }

            reader.close();

            return dealership;

        } catch (IOException | NumberFormatException e) {

            throw new IllegalStateException(
                    "Could not load inventory: " + e.getMessage(), e
            );
        }
    }

    public void saveDealership(Dealership dealership) {

        try {
            FileWriter file = new FileWriter(DealershipFileManager.file);
            BufferedWriter writer = new BufferedWriter(file);

            writer.write(
                    dealership.getName() + "|" +
                            dealership.getAddress() + "|" +
                            dealership.getPhone()
            );

            writer.newLine();

            for (Vehicle vehicle : dealership.getAllVehicles()) {

                writer.write(
                        vehicle.getVin() + "|" +
                                vehicle.getYear() + "|" +
                                vehicle.getMake() + "|" +
                                vehicle.getModel() + "|" +
                                vehicle.getVehicleType() + "|" +
                                vehicle.getColor() + "|" +
                                vehicle.getOdometer() + "|" +
                                vehicle.getPrice().toPlainString()
                );

                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {

            throw new IllegalStateException(
                    "Could not save inventory: " + e.getMessage(), e
            );
        }
    }
}