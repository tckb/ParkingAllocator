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


    /**
     * execute some kind of instruction here, and expect a return message
     *
     * @param instruction
     *         instrunction to execute
     * @return a return message
     */
    public String execute(Instruction instruction) {
        return "some return message";
    }


    /**
     * returns a representation of this parking space
     *
     * @return
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * initialize this parking space with some fixed slots
     *
     * @param nrSlots
     */
    public ParkingSpace(final int nrSlots) {this.nrSlots = nrSlots;}
}
