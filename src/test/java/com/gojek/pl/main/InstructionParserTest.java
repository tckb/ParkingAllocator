package com.gojek.pl.main;

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
                "leave 4"
        };

        invalidInstructions = new String[]{
                "park invalid",
                "park",
                "leave",
                null,
                ""
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
                System.out.println(ex.getMessage());
            }
        }
        Assert.assertEquals(exceptions, invalidInstructions.length, "invalid instruction parsing count failed!");

    }
}