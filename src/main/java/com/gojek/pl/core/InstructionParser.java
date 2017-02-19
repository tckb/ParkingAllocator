package com.gojek.pl.core;

import com.gojek.pl.model.inst.Instruction;
import com.gojek.pl.model.inst.LeaveInst;
import com.gojek.pl.model.inst.ParkInst;
import com.gojek.pl.model.inst.ParkingSpaceFactory;

/**
 * A util class to build an instruction from a raw string
 *
 * @author tckb
 */
public abstract class InstructionParser {
    /**
     * Parses the raw string and returns the instruction
     *
     * @param rawString
     * @return instruction
     */
    public static Instruction parseString(String rawString) {
        // check the mandatory param
        Utils.checkMandatory(rawString, "command_name");
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
                instruction = new Instruction() {
                    @Override
                    public String getCommand() {
                        return "status";
                    }

                    @Override
                    public String[] getCommandParams() {
                        return new String[]{};
                    }

                    @Override
                    public int getNrReqParams() {
                        return 0;
                    }
                };
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
                throw new IllegalArgumentException(commandData[0] + " - invalid instruction. List of supported Instructions:\n" + listOfSupportedInstructions());

        }
        return instruction;

    }

    public static String listOfSupportedInstructions() {
        return
                "create_parking_lot <nr_lots>\n" +
                        "park <vehicle_nr> <vehicle_color>\n" +
                        "leave <slot_nr>\n" +
                        "status\n" +
                        "registration_numbers_for_cars_with_colour <vehicle_color>\n" +
                        "slot_numbers_for_cars_with_colour <vehicle_color>\n" +
                        "slot_number_for_registration_number <vehicle_nr>\n" +
                        "exit";

    }
}
