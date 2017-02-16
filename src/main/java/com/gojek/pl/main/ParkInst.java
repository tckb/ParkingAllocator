package com.gojek.pl.main;

/**
 * Parking instruction, takes two arguments: carNumber, carColor
 *
 * @author tckb
 */
public class ParkInst extends AbstractInstruction {

    public ParkInst(String... data) {
        super("park", 2);
        setParams(data);
    }

    @Override
    public boolean validateParams(final String[] params) {
        return true;
    }

    public Vehicle getVehicle() {
        return new Vehicle(getCommandParams()[0], getCommandParams()[1]);
    }
}
