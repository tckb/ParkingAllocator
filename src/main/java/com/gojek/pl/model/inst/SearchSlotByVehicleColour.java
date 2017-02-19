package com.gojek.pl.model.inst;

/**
 * Created on 19/02/17.
 *
 * @author tckb
 */
public class SearchSlotByVehicleColour extends AbstractInstruction {
    @Override
    public boolean validateParams(final String[] params) {
        return true;
    }

    public SearchSlotByVehicleColour(String... data) {
        super("slot_numbers_for_cars_with_colour", 1);
        setParams(data);
    }

    public String getVehicleColour() {
        return getCommandParams()[0];
    }
}
