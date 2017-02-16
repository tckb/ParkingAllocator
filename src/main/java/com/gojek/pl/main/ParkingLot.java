package com.gojek.pl.main;

/**
 * represents the physical parking lot
 *
 * @author tckb
 */
public class ParkingLot {
    private final int lotNumber;
    private Vehicle parkedVehicle;

    public ParkingLot(final int lotNumber) {this.lotNumber = lotNumber;}

    public Vehicle visit() {
        return parkedVehicle;
    }

    public void park(final Vehicle allocatedVehicle) {
        this.parkedVehicle = allocatedVehicle;
    }

    public boolean isAvailable() {
        return parkedVehicle == null;
    }

    public Vehicle free() {
        Vehicle outVehicle = this.parkedVehicle;
        this.parkedVehicle = null;
        return outVehicle;
    }

    @Override
    public String toString() {
        return lotNumber + "\t\t" + (parkedVehicle == null ? "[EMPTY]" : parkedVehicle);
    }
}
