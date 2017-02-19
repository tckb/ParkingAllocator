package com.gojek.pl;

import com.gojek.pl.core.ParkingSpace;
import com.gojek.pl.model.Vehicle;
import com.gojek.pl.model.inst.Instruction;
import com.gojek.pl.model.inst.LeaveInst;
import com.gojek.pl.model.inst.ParkInst;

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
            default:
                returnMessage = instruction.getCommand() + " not supported, skipping";

        }
        return returnMessage;
    }
}
