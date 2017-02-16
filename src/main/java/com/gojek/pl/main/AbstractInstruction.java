package com.gojek.pl.main;

import java.util.Arrays;

/**
 * An abstract impl of an instruction
 *
 * @author tckb
 */
public abstract class AbstractInstruction implements Instruction {
    private final int nrReqParams;
    private final String command;
    private String[] params;

    protected AbstractInstruction(String command, int nrParams) {
        this.nrReqParams = nrParams;
        this.command = command;
        this.params = new String[nrParams];
    }

    /**
     * sets the params for the instruction
     *
     * @param params
     *         the params including the command
     */
    protected void setParams(String... params) {
        if (params.length < nrReqParams + 1) {
            throw new IllegalArgumentException("Require " + nrReqParams + " params to execute " + getCommand() + "!");
        }

        this.params = Arrays.copyOfRange(params, 1, nrReqParams + 1);

        if (!validateParams(params)) {
            throw new IllegalArgumentException("Invalid params! check the spec");
        }

    }

    @Override
    public String getCommand() {
        return command;
    }

    public int getNrReqParams() {
        return nrReqParams;
    }

    @Override
    public String[] getCommandParams() {
        return params;
    }

    /**
     * validates the params for this instruction
     *
     * @param params
     *         the params to be validated
     * @return boolean, true - if the params are valid , false - otherwise
     */
    public abstract boolean validateParams(String[] params);


    @Override
    public String toString() {
        return getCommand() + ": " + Arrays.toString(getCommandParams());
    }
}
