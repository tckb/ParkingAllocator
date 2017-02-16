package com.gojek.pl.main;

import javax.naming.CannotProceedException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Main entry point to the program
 *
 * @author tckb
 */
public class MainRunner {

    private static final Logger logger = Logger.getLogger(MainRunner.class.getName());

    public static void main(String[] args) throws Exception {
        setup();

        if (args.length > 0) {
            List<Instruction> listOfInsts = readInstructionFile(args[0]);
            startNonInteractive(listOfInsts);
        } else {
            startInteractive();
        }
    }


    private static void startNonInteractive(final List<Instruction> listOfInsts) throws CannotProceedException {
        if (!(listOfInsts.get(0) instanceof ParkingSpaceFactory)) {
            throw new CannotProceedException("First instruction should always be a 'create_parking_lot'");
        }
        final ParkingSpace parkingSpace = ((ParkingSpaceFactory) listOfInsts.get(0)).build();
        listOfInsts.stream()
                .skip(1L)
                .forEach(instruction -> logger.info(parkingSpace.execute(instruction)));
    }

    private static void startInteractive() {
        // start the interactive mode
    }

    private static List<Instruction> readInstructionFile(final String fileName) throws IOException {
        Utils.checkMandatory(fileName, "fileName");

        // we expect the file to be rather small, so load all the lines at once.
        return Files.readAllLines(new File(fileName).toPath()).stream()
                .map(InstructionParser::parseString)
                .collect(Collectors.toList());

    }

    private static void setup() {
        // just configuring the log
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("/Users/tckb/Documents/Work/GoJekParking/ParkingAllocator/src/main/resources/app.properties"));
        } catch (SecurityException | IOException e1) {
            e1.printStackTrace();
            System.exit(1);
        }
        logger.setLevel(Level.FINE);
        logger.addHandler(new ConsoleHandler());
    }

}
