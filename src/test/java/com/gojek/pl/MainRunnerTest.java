package com.gojek.pl;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created on 16/02/17.
 *
 * @author tckb
 */
@Test
public class MainRunnerTest {

    private String inputInstFile;

    @BeforeMethod
    public void setUp() throws Exception {
        inputInstFile = MainRunnerTest.class.getResource("/file_test_inputs.txt").getPath();

    }

    @Test(testName = "non-interative mode")
    public void testInteractive() throws Exception {
        MainRunner.main(new String[]{inputInstFile});

    }

    @Test(testName = "interative mode")
    public void testNonInteractive() throws Exception {


    }
}