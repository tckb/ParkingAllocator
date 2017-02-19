package com.gojek.pl.core;

import com.gojek.pl.model.Vehicle;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 19/02/17.
 *
 * @author tckb
 */
public class ParkingSpaceTest {

    ParkingSpace parkingSpace;
    int nrParkingSlots = 10;
    List<Vehicle> vehiclesToPark;


    @BeforeMethod
    public void setUp() throws Exception {
        vehiclesToPark = new ArrayList<>(nrParkingSlots);
        parkingSpace = new TrivialParkingSpace(nrParkingSlots);
        for (int i = 0; i < nrParkingSlots; i++) {
            vehiclesToPark.add(new Vehicle("TEST_VEHICLE_" + i, "COLOUR_" + i));
        }
    }

    @Test(testName = "park")
    public void testParkUnParkScenario() throws Exception {
        // fill the whole parking lot
        vehiclesToPark.forEach(vehicle -> parkingSpace.parkVehicle(vehicle));
        System.out.println(parkingSpace);

        for (int i = 1; i <= nrParkingSlots; i++) {
            final ParkingLot parkingLot = parkingSpace.getParkingLot(i);
            Assert.assertTrue(!parkingLot.isAvailable(), "Slot#" + i + " is available but is already full!");
        }


        int slot = parkingSpace.parkVehicle(new Vehicle("some vehicle", "color"));
        Assert.assertTrue((slot < 1), "Slot Nr allocated for all full parking space");

        // remove slot 3,5,1
        parkingSpace.unparkVehicle(3);
        parkingSpace.unparkVehicle(1);
        parkingSpace.unparkVehicle(5);


        Assert.assertEquals(parkingSpace.getFreeSlots(), 3, " available slots do not match!");

        // now park the vehicle
        slot = parkingSpace.parkVehicle(new Vehicle("some vehicle", "color"));
        Assert.assertTrue(slot == 1, "Slot#1 is the closest to parking space, this is not allocated!");
        Assert.assertEquals(parkingSpace.getFreeSlots(), 2, " available slots do not match!");
        Assert.assertTrue(!parkingSpace.isValidSlot(0), "0 is not a valid slot!");
        Assert.assertTrue(!parkingSpace.isValidSlot(nrParkingSlots + 1), (nrParkingSlots + 1) + " is not a valid slot!");

        System.out.println(parkingSpace);
    }

}