package com.gojek.pl;

import com.gojek.pl.core.InstructionParser;
import com.gojek.pl.core.ParkingSpace;
import com.gojek.pl.model.inst.Instruction;
import com.gojek.pl.model.inst.ParkingSpaceFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 16/02/17.
 *
 * @author tckb
 */
@Test
public class MainRunnerTest {

    private String inputInstFile;
    List<Instruction> instructions;
    List<String> instructionsString;
    ParkingSpace parkingSpace;

    @BeforeMethod
    public void setUp() throws Exception {
//        inputInstFile = MainRunnerTest.class.getResource("/file_test_inputs.txt").getPath();
//        instruction = readInstructionFile(inputInstFile);
        instructionsString = Arrays.asList(
                "create_parking_lot 6",
                "park KA-01-BB-0001 Black",
                "park KA-01-HH-7777 Red",
                "park KA-01-HH-2701 Blue",
                "park KA-01-HH-3141 Black",
                "leave 4",
                "leave 1",
                "registration_numbers_for_cars_with_colour White",
                "slot_numbers_for_cars_with_colour black",
                "slot_numbers_for_cars_with_colour red",
                "park KA-01-BB-0001 Black",
                "slot_number_for_registration_number KA-01-HH-3141",
                "slot_number_for_registration_number KA-01-BB-0001"
        );

        instructions = parseInstructions(instructionsString);
    }


    @Test
    public void setUpParkingSpace() {
        parkingSpace = ((ParkingSpaceFactory) instructions.get(0)).build();
    }


    @Test(testName = "non-interative mode", dependsOnMethods = "setUpParkingSpace")
    public void testNonInteractive() throws Exception {
        System.out.println("Running the following instructions ...\n" + instructionsString);
        final List<String> messages = runInstructions(instructions);
        System.out.println("Output: \n" + messages);


    }


    private List<String> runInstructions(List<Instruction> instructions) {
        return instructions.stream()
                .skip(1L)
                .map(instruction -> ParkingHelper.executeInstruction(parkingSpace, instruction))
                .collect(Collectors.toList());
    }

    private List<Instruction> readInstructionFile(final String fileName) throws IOException {
        return parseInstructions(Files.readAllLines(new File(fileName).toPath(), Charset.forName("UTF-8")));
    }

    private List<Instruction> parseInstructions(List<String> instructions) throws IOException {
        return instructions.stream()
                .map(InstructionParser::parseString)
                .collect(Collectors.toList());
    }


}