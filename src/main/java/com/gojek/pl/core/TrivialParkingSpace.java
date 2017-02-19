package com.gojek.pl.core;

import com.gojek.pl.model.Vehicle;

import java.util.Arrays;
import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * A trivial implementation of parking space. Note, this is just sequential implementation,
 * not be used in multi-threaded scenario.
 *
 * @author tckb
 */
public class TrivialParkingSpace implements ParkingSpace {

    private final int totalAvailableSlots;
    private final ParkingLot[] parkingLots;
    // keep the references of slots that are occupied
    private final Set<Integer> occupiedSlots;
    private final NavigableSet<Integer> availableSlots;


    @Override
    public Vehicle unparkVehicle(final int slotNr) {
        if (isValidSlot(slotNr)) {
            if (occupiedSlots.contains(slotNr)) {
                final Vehicle parkedVehicle = parkingLots[slotNr - 1].freeUp();
                if (parkedVehicle != null) {
                    occupiedSlots.remove(slotNr);
                    availableSlots.add(slotNr);
                    return parkedVehicle;
                }
            }
        }
        return null;
    }

    @Override
    public int parkVehicle(final Vehicle vehicleToPark) {
        if (!availableSlots.isEmpty()) {
            int availableSlot = findClosestFreeSlot();
            if (availableSlot != -1) {
                if (parkingLots[availableSlot - 1].park(vehicleToPark)) {
                    return availableSlot;
                }
            }
        }
        return -1;
    }

    private int findClosestFreeSlot() {
        // return the first available slot!
        final Integer availableSlot = availableSlots.pollFirst();
        occupiedSlots.add(availableSlot);
        return availableSlot;
    }


    /**
     * initialize this parking space with some fixed slots
     *
     * @param nrSlots
     */
    public TrivialParkingSpace(final int nrSlots) {
        // we need faster access
        occupiedSlots = new HashSet<>();
        // we need the slots to be ordered
        availableSlots = new TreeSet<>();
        // create empty lots
        parkingLots = new ParkingLot[nrSlots];
        // initialize the parking space with empty lots
        for (int i = 0; i < nrSlots; i++) {
            parkingLots[i] = new ParkingLot(i + 1);
            availableSlots.add(i + 1);
        }
        // keep a reference of the number of slots, instead of querying the array each time
        this.totalAvailableSlots = nrSlots;

    }

    @Override
    public int getTotalSlots() {
        return totalAvailableSlots;
    }

    @Override
    public boolean isValidSlot(final int slotNr) {
        return (slotNr >= 1 && slotNr <= totalAvailableSlots);
    }

    @Override
    public ParkingLot getParkingLot(final int slotNr) {
        if (isValidSlot(slotNr)) {
            return parkingLots[slotNr - 1];
        }
        return null;
    }

    @Override
    public int getFreeSlots() {
        return availableSlots.size();
    }

    @Override
    public String toPrintableString() {
        return toString();
    }

    @Override
    public String toString() {
        StringBuilder parkingSpace = new StringBuilder();
        parkingSpace.append("Slot#").append("\t").append("Registration#").append("\t\t").append("Colour").append("\n");
        Arrays.asList(parkingLots).forEach(parkingLot -> parkingSpace.append(parkingLot.toString()).append("\n"));

        parkingSpace.append("\n")
                .append(getTotalSlots() - occupiedSlots.size()).append(" Slot(s) Available.");

        return parkingSpace.toString();
    }
}
