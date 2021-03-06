package com.gojek.pl.model.inst;

import com.gojek.pl.core.ParkingSpace;
import com.gojek.pl.core.TrivialParkingSpace;

/**
 * a parking space factory is a type of instruction to create parking space
 *
 * @author tckb
 */
public class ParkingSpaceFactory extends AbstractInstruction {
    @Override
    public boolean validateParams(final String[] params) {
        try {
            Integer.parseInt(params[0]);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public ParkingSpaceFactory(final String... params) {
        super("create_parking_lot", 1);
        setParams(params);
    }

    public ParkingSpace build() {
        return new TrivialParkingSpace(Integer.parseInt(getCommandParams()[0]));
    }

}
