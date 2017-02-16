package com.gojek.pl.main;

/**
 * a model to represent a parking vehicle
 *
 * @author tckb
 */
public class Vehicle {
    public final String vehicleNr;
    public final String vehicleColor;

    public Vehicle(final String vehicleNr, final String vehicleColor) {
        this.vehicleNr = vehicleNr;
        this.vehicleColor = vehicleColor;
    }

}
