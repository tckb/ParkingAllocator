package com.gojek.pl.main;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Implementation of Parking space
 *
 * @author tckb
 */
public class ParkingSpace {

    private final int totalAvailableSlots;
    private final ParkingLot[] parkingLots;
    // keep the references of slots that are occupied
    private final SortedSet<Integer> occupiedSlots;


    /**
     * execute some kind of instruction here and, expect a return message
     *
     * @param instruction
     *         instruction to execute
     * @return a return message
     */
    public String execute(Instruction instruction) {
        String returnMessage;
        switch (instruction.getCommand()) {
            case "park":
                returnMessage = parkIfPossible(((ParkInst) instruction).getVehicle());
                break;
            case "leave":
                returnMessage = freeUpSlot(((LeaveInst) instruction).getSlot());
                break;
            case "status":
                returnMessage = toString();
                break;
            default:
                returnMessage = instruction.getCommand() + " not supported, skipping";

        }
        return returnMessage;
    }

    // slot number starts from 1...totalAvailableSlots
    private String freeUpSlot(final int slotNr) {
        if (slotNr > totalAvailableSlots || slotNr < 1) {
            return slotNr + " slot not found";
        } else {
            // free up the slot
            if (occupiedSlots.contains(slotNr)) {
                final Vehicle parkedVehicle = parkingLots[slotNr].freeUp();
                if (parkedVehicle != null) {
                    occupiedSlots.remove(slotNr);
                    return "Slot number " + slotNr + " is free; [" + parkedVehicle + "] ";
                }
            }
            return "Slot number " + slotNr + " is already free";
        }
    }

    private String parkIfPossible(final Vehicle vehicleToPark) {
        if (occupiedSlots.size() < totalAvailableSlots) {
            int availableSlot = findClosestFreeSlot();
            if (availableSlot != -1) {
                if (parkingLots[availableSlot].park(vehicleToPark)) {
                    return "Allocated slot number: " + availableSlot;
                } else {
                    // this technically should not happen!
                }
            }
        }
        return "Sorry, parking lot is full";
    }

    private int findClosestFreeSlot() {
        // main implementation for finding the closest slot#
        

        return -1;
    }


    /**
     * @return a representation of this parking space
     */
    @Override
    public String toString() {
        StringBuilder parkingSpace = new StringBuilder();
        parkingSpace.append("Slot No.").append("\t\t").append("Registration#").append("\t\t").append("Colour").append("\n");
        Arrays.asList(parkingLots).forEach(parkingLot -> parkingSpace.append(parkingLot.toString()).append("\n"));

        parkingSpace.append("\n")
                .append(getTotalSlots() - occupiedSlots.size()).append(" Slots Available.");

        return parkingSpace.toString();
    }

    /**
     * initialize this parking space with some fixed slots
     *
     * @param nrSlots
     */
    public ParkingSpace(final int nrSlots) {
        // create empty lots
        parkingLots = new ParkingLot[nrSlots];
        // initialize the parking space with empty lots
        for (int i = 0; i < nrSlots; i++) {
            parkingLots[i] = new ParkingLot(i + 1);
        }
        // keep a reference of the number of slots, instead of querying the array each time
        this.totalAvailableSlots = nrSlots;
        occupiedSlots = new TreeSet<>();
    }

    public int getTotalSlots() {
        return totalAvailableSlots;
    }
}
