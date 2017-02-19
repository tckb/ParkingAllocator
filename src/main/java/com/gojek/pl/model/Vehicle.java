package com.gojek.pl.model;

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

    @Override
    public String toString() {
        return vehicleNr + "\t\t" + vehicleColor;
    }
}
