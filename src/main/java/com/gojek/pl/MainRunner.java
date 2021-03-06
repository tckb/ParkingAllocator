package com.gojek.pl;

import com.gojek.pl.core.InstructionParser;
import com.gojek.pl.core.ParkingSpace;
import com.gojek.pl.core.Utils;
import com.gojek.pl.model.inst.Instruction;
import com.gojek.pl.model.inst.ParkingSpaceFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Main entry point to the program
 *
 * @author tckb
 */
public class MainRunner {
    private static ParkingSpace parkingSpace;
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


    private static void startNonInteractive(final List<Instruction> listOfInsts) {
        if (!(listOfInsts.get(0) instanceof ParkingSpaceFactory)) {
            throw new IllegalArgumentException("First instruction should always be a 'create_parking_lot'");
        }
        parkingSpace = ((ParkingSpaceFactory) listOfInsts.get(0)).build();
        logger.info("Created a parking lot with " + parkingSpace.getTotalSlots() + " slots");

        listOfInsts.stream()
                .skip(1L)
                .forEach(instruction -> logger.info(ParkingHelper.executeInstruction(parkingSpace, instruction)));
    }

    // a simple REPL for simulating interactive mode
    private static void startInteractive() {
        System.out.println("Type `exit` to quit.");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(">> ");
            String line = scanner.nextLine();
            if (!line.equals("exit")) {
                if (!line.isEmpty()) { processInstruction(line); }
            } else {
                System.out.println("tschüss!! adiós!!");
                System.exit(0);
            }
        }
    }

    private static void processInstruction(final String line) {
        try {

            final Instruction instruction = InstructionParser.parseString(line);
            if (parkingSpace != null) {
                System.out.println(ParkingHelper.executeInstruction(parkingSpace, instruction));
            } else {
                if (instruction instanceof ParkingSpaceFactory) {
                    parkingSpace = ((ParkingSpaceFactory) instruction).build();
                    System.out.println("Created a parking lot with " + parkingSpace.getTotalSlots() + " slots");
                } else {
                    System.out.println("Err: Parking space is not created yet, please create a parking space first!");
                }
            }
        } catch (IllegalArgumentException exception) {
            System.out.println("Err: " + exception.getMessage());
        }


    }

    private static List<Instruction> readInstructionFile(final String fileName) throws IOException {
        Utils.checkMandatory(fileName, "fileName");

        // we expect the file to be rather small, so load all the lines at once.
        return Files.readAllLines(new File(fileName).toPath(), Charset.forName("UTF-8")).stream()
                .map(InstructionParser::parseString)
                .collect(Collectors.toList());

    }

    private static void setup() {
        // just configuring the log
        try {
            LogManager.getLogManager().readConfiguration(MainRunner.class.getResourceAsStream("/app.properties"));
        } catch (SecurityException | IOException e1) {
            e1.printStackTrace();
            System.exit(1);
        }
    }

}
