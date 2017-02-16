package com.gojek.pl.main;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created on 16/02/17.
 *
 * @author tckb
 */
public class MainRunnerTest {

    String fileName;


    @BeforeMethod
    public void setUp() throws Exception {
        fileName = "/Users/tckb/Documents/Work/GoJekParking/ParkingAllocator/src/main/resources/file_inputs_1.txt";
    }

    @Test
    public void testMain() throws Exception {
        MainRunner.main(new String[]{fileName});

    }
}