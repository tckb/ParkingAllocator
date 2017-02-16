package com.gojek.pl.main;

/**
 * A spec defining an instruction to be executed on the parking lot
 *
 * @author tckb
 */
public interface Instruction {
    /**
     * returns the main command for this instruction
     *
     * @return
     */
    String getCommand();

    /**
     * returns the parameters for this instruction
     *
     * @return
     */
    String[] getCommandParams();

    /**
     * returns the number of params needed for this instrucgtion to execute
     *
     * @return
     */
    int getNrReqParams();

}
