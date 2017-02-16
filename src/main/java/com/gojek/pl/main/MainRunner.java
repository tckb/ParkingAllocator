package com.gojek.pl.main;

import java.util.List;

/**
 * Main entry point to the program
 *
 * @author tckb
 */
public class MainRunner {

    public static void main(String[] args) throws Exception {
        if (args.length > 1) {
            List<Instruction> listOfInsts = readInstructionFile(args[0]);
            // use these instruction

        } else {
            startInteractive();
        }
    }

    private static void startInteractive() {
        // start the interactive mode
    }

    private static List<Instruction> readInstructionFile(final String fileName) {
        Utils.checkMandatory(fileName, "fileName");
        return null;
    }
}
