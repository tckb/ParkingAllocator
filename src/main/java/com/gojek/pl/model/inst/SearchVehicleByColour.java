package com.gojek.pl.model.inst;

/**
 * Created on 19/02/17.
 *
 * @author tckb
 */
public class SearchVehicleByColour extends AbstractInstruction {
    @Override
    public boolean validateParams(final String[] params) {
        return true;
    }

    public SearchVehicleByColour(String... data) {
        super("registration_numbers_for_cars_with_colour", 1);
        setParams(data);
    }

    public String getColour() {
        return getCommandParams()[0];
    }
}
