package com.gojek.pl.main;

import java.util.Arrays;

/**
 * Implementation of Parking space
 *
 * @author tckb
 */
public class ParkingSpace {

    private final int nrSlots;
    private final ParkingLot[] parkingLots;
    // message templates
    private final String[] messageTemplates = new String[]{
            "Allocated slot number: %s",
            "Slot number %s is free"
    };


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
            default:
                returnMessage = instruction.getCommand() + " not supported, skipping";

        }
        return returnMessage;
    }

    private String freeUpSlot(final int slot) {
        return slot + " slot not found";
    }

    private String parkIfPossible(final Vehicle vehicleToPark) {
        return "Sorry, parking lot is full";
    }


    /**
     * @return a representation of this parking space
     */
    @Override
    public String toString() {
        StringBuilder parkingSpace = new StringBuilder();
        parkingSpace.append("Slot No.").append("\t\t").append("Registration No").append("\t").append("Colour");
        Arrays.asList(parkingLots).forEach(parkingLot -> parkingSpace.append(parkingLot.toString()));
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
            parkingLots[i] = new ParkingLot(i);
        }
        // keep a reference of the number of slots, instead of querying the array each time
        this.nrSlots = nrSlots;
    }

    public int getSlotsCount() {
        return nrSlots;
    }
}
