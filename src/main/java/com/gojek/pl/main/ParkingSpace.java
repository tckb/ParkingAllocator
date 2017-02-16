package com.gojek.pl.main;

import java.util.Collection;

/**
 * Implementation of Parking space
 *
 * @author tckb
 */
public class ParkingSpace {

    private final int nrSlots;
    // some datastructure to hold parking lots
    private Collection<ParkingLot> parkingLots;
    // message templates
    private String[] messageTemplates = new String[]{
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
                returnMessage = "skipping, " + instruction.getCommand();

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
     * returns a representation of this parking space
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder parkingSpace = new StringBuilder();
        parkingSpace.append("Slot No.").append("\t\t").append("Registration No").append("\t").append("Colour");
        parkingLots.forEach(parkingLot -> parkingSpace.append(parkingLot.toString()));
        return parkingSpace.toString();
    }

    /**
     * initialize this parking space with some fixed slots
     *
     * @param nrSlots
     */
    public ParkingSpace(final int nrSlots) {this.nrSlots = nrSlots;}
}
