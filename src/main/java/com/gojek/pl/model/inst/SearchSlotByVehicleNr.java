package com.gojek.pl.model.inst;

/**
 * Created on 19/02/17.
 *
 * @author tckb
 */
public class SearchSlotByVehicleNr extends AbstractInstruction {
    @Override
    public boolean validateParams(final String[] params) {
        return true;
    }

    public SearchSlotByVehicleNr(String... data) {
        super("slot_number_for_registration_number", 1);
        setParams(data);
    }

    public String getVehicleNr() {
        return getCommandParams()[0];
    }
}
