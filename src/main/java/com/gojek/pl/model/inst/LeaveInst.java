package com.gojek.pl.model.inst;

/**
 * Created on 16/02/17.
 *
 * @author tckb
 */
public class LeaveInst extends AbstractInstruction {
    @Override
    public boolean validateParams(final String[] params) {
        try {
            Integer.parseInt(params[0]);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public LeaveInst(String... data) {
        super("leave", 1);
        setParams(data);
    }

    public int getSlot() {
        return Integer.parseInt(getCommandParams()[0]);
    }
}
