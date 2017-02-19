package com.gojek.pl;

import com.gojek.pl.core.InstructionParser;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created on 16/02/17.
 *
 * @author tckb
 */
@Test
public class InstructionParserTest {

    String[] validInstructions;
    String[] invalidInstructions;

    @BeforeMethod
    public void setUp() throws Exception {
        validInstructions = new String[]{
                "park KA-01-HH-1234 White",
                "leave 4",
                "registration_numbers_for_cars_with_colour color",
                "slot_numbers_for_cars_with_colour color",
                "slot_number_for_registration_number car"
        };

        invalidInstructions = new String[]{
                "park invalid",
                "park",
                "leave",
                null,
                "registration_numbers_for_cars_with_colour",
                "slot_numbers_for_cars_with_colour",
                "slot_number_for_registration_number"

        };

    }

    @Test(testName = "testValidInstruction")
    public void testValidInstruction() {
        try {
            for (String inst : validInstructions) {
                InstructionParser.parseString(inst);
            }
        } catch (Exception ex) {
            Assert.fail("valid instruction parsing failed", ex);
        }
    }


    @Test(testName = "testInvalidInstruction")
    public void testInvalidInstruction() {
        int exceptions = 0;
        for (String inst : invalidInstructions) {
            try {
                InstructionParser.parseString(inst);
            } catch (Exception ex) {
                exceptions++;
            }
        }
        Assert.assertEquals(exceptions, invalidInstructions.length, "invalid instruction parsing count failed!");

    }
}