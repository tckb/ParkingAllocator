package com.gojek.pl.main;

/**
 * A util class to build an instruction from a raw string
 *
 * @author tckb
 */
public class InstructionParser {
    /**
     * Parses the raw string and returns the instruction
     *
     * @param rawString
     * @return instruction
     */
    public static Instruction parseString(String rawString) {
        // check the mandatory param
        Utils.checkMandatory(rawString, "rawString");
        //
        Instruction instruction = null;
        final String[] commandData = rawString.split(" ");
        // first data is command name
        switch (commandData[0]) {
            case "park":
                instruction = new ParkInst(commandData);
                break;
            case "leave":
                instruction = new LeaveInst(commandData);
                break;
            case "status":
                break;
            case "registration_numbers_for_cars_with_colour":
                break;
            case "slot_numbers_for_cars_with_colour":
                break;
            case "slot_number_for_registration_number":
                break;
            case "create_parking_lot":
                instruction = new ParkingSpaceFactory(commandData);
                break;
            default:
                throw new IllegalArgumentException(commandData[0] + " - invalid instruction");

        }
        return instruction;

    }
}
