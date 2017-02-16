package com.gojek.pl.main;

/**
 * Created on 16/02/17.
 *
 * @author tckb
 */
public class LeaveInst extends AbstractInstruction {
    @Override
    public boolean validateParams(final String[] params) {
        return true;
    }

    public LeaveInst(String... data) {
        super("leave", 1);
        setParams(data);
    }
}
