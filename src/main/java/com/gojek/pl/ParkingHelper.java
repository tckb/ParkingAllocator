package com.gojek.pl;

import com.gojek.pl.core.ParkingLot;
import com.gojek.pl.core.ParkingSpace;
import com.gojek.pl.model.Vehicle;
import com.gojek.pl.model.inst.*;

/**
 * A helper class which actually executes the instruction on a parking space
 *
 * @author tckb
 */
public abstract class ParkingHelper {
    /**
     * @param parkingSpace
     * @param instruction
     * @return
     */
    public static String executeInstruction(ParkingSpace parkingSpace, Instruction instruction) {
        String returnMessage = "Failed to execute instruction";
        switch (instruction.getCommand()) {
            case "park":
                int parkedSlot = parkingSpace.parkVehicle(((ParkInst) instruction).getVehicle());
                if (parkedSlot > 0) {
                    returnMessage = "Allocated slot number: " + parkedSlot;
                } else {
                    returnMessage = "Sorry, parking lot is full";
                }

                break;
            case "leave":
                int slotNr = ((LeaveInst) instruction).getSlot();
                Vehicle parkedVehicle = parkingSpace.unparkVehicle(slotNr);
                if (parkedVehicle != null) {
                    returnMessage = "Slot number " + slotNr + " is free; [" + parkedVehicle + "] ";
                } else {
                    if (!parkingSpace.isValidSlot(slotNr)) {
                        returnMessage = slotNr + " slot not found";
                    } else {
                        if (parkingSpace.getParkingLot(slotNr).isAvailable()) {
                            returnMessage = "Slot number " + slotNr + " is already free";
                        }
                    }
                }
                break;
            case "status":
                returnMessage = parkingSpace.toPrintableString();
                break;
            case "registration_numbers_for_cars_with_colour":
                // trivial search here!
                String searchColour = ((SearchVehicleByColour) instruction).getColour();
                StringBuilder result = new StringBuilder();

                for (slotNr = 1; slotNr < parkingSpace.getTotalSlots(); slotNr++) {
                    ParkingLot parkingLot = parkingSpace.getParkingLot(slotNr);
                    if (!parkingLot.isAvailable()) {
                        final Vehicle vehicle = parkingLot.visit();
                        if (vehicle.vehicleColor.equalsIgnoreCase(searchColour)) {
                            result.append(vehicle.vehicleNr).append(", ");
                        }

                    }
                }
                result.delete(result.length() - 2, result.length());
                returnMessage = result.toString();
                break;
            case "slot_numbers_for_cars_with_colour":
                // trivial search here!
                searchColour = ((SearchSlotByVehicleColour) instruction).getVehicleColour();
                result = new StringBuilder();

                for (slotNr = 1; slotNr < parkingSpace.getTotalSlots(); slotNr++) {
                    ParkingLot parkingLot = parkingSpace.getParkingLot(slotNr);
                    if (!parkingLot.isAvailable()) {
                        final Vehicle vehicle = parkingLot.visit();
                        if (vehicle.vehicleColor.equalsIgnoreCase(searchColour)) {
                            result.append(slotNr).append(", ");
                        }

                    }
                }
                result.delete(result.length() - 2, result.length());
                returnMessage = result.toString();
                break;
            case "slot_number_for_registration_number":
                // trivial search here!
                final String vehicleNr = ((SearchSlotByVehicleNr) instruction).getVehicleNr();
                result = new StringBuilder();

                for (slotNr = 1; slotNr < parkingSpace.getTotalSlots(); slotNr++) {
                    ParkingLot parkingLot = parkingSpace.getParkingLot(slotNr);
                    if (!parkingLot.isAvailable()) {
                        final Vehicle vehicle = parkingLot.visit();
                        if (vehicle.vehicleNr.equalsIgnoreCase(vehicleNr)) {
                            // there should be no other car with the same registration number ;)
                            result.append(slotNr);
                            break;
                        }

                    }
                }
                returnMessage = result.toString();
                break;

            default:
                returnMessage = instruction.getCommand() + " not supported, skipping";

        }
        return returnMessage;
    }
}
