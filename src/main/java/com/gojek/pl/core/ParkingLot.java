package com.gojek.pl.core;

import com.gojek.pl.model.Vehicle;

/**
 * represents the physical parking lot
 *
 * @author tckb
 */
public class ParkingLot {
    private final int lotNumber;
    private Vehicle parkedVehicle;

    ParkingLot(final int lotNumber) {
        this.lotNumber = lotNumber;
        parkedVehicle = null;
    }

    /**
     * returns the currently parked vehicle
     *
     * @return vehicle that is parked, null - if no vehicle is parked
     */
    public Vehicle visit() {
        return parkedVehicle;
    }


    /**
     * parks the vehicle if possible
     *
     * @param allocatedVehicle
     *         the vehicle to be parked
     * @return true - if vehicle is parked, false - if parking not possible, i.e.,another vehicle is parked
     */
    boolean park(final Vehicle allocatedVehicle) {
        if (isAvailable()) {
            this.parkedVehicle = allocatedVehicle;
            return true;
        }
        return false;
    }

    /**
     * check if this slot is available for parking
     *
     * @return true if available for parking, false if occipied
     */
    public boolean isAvailable() {
        return parkedVehicle == null;
    }

    /**
     * free's this lot by un-parking the vehicle
     *
     * @return vehicle which was parked, returns `null` if there's no vehicle parked!
     */
    Vehicle freeUp() {
        // no null check required here
        Vehicle outVehicle = this.parkedVehicle;
        this.parkedVehicle = null;
        return outVehicle;
    }

    @Override
    public String toString() {
        return lotNumber + "\t\t" + (parkedVehicle == null ? "- Available -" : parkedVehicle);
    }
}
