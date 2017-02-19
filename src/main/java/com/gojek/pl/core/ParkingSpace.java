package com.gojek.pl.core;

import com.gojek.pl.model.Vehicle;

/**
 * a spec for representing a parking space
 *
 * @author tckb
 */
public interface ParkingSpace {


    /**
     * UnParks the vehicle from given parking lot number
     *
     * @param slotNr
     *         the slot number where where vehicle is parked
     * @return the vehicle which is parked at the table, null - if the slot number is invalid or the slot is already free
     */
    Vehicle unparkVehicle(int slotNr);

    /**
     * Parks the vehicle in this space
     *
     * @param vehicleToPark
     *         vehicle to be parked
     * @return allocated parking lot number.<br/>
     * </br> Note: the returned value is : (> 1 )  - if parking space can accommodate the vehicle, (< 1) iff parking space is full
     */
    int parkVehicle(Vehicle vehicleToPark);

    /**
     * @return the total number of available slots
     */
    int getTotalSlots();

    /**
     * checks if the provided slot number is a valid slot
     *
     * @param slotNr
     *         slot number to be checked
     * @return true - if valid, false - otherwise
     */
    boolean isValidSlot(int slotNr);

    /**
     * returns the parking lot corresponding to the slot number
     *
     * @param slotNr
     *         the slot number
     * @return the parking lot. null - if slotnumber is invalid
     */
    ParkingLot getParkingLot(int slotNr);

    /**
     * @return a representation of this parking space
     */
    String toPrintableString();
}
