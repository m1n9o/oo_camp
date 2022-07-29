package org.oobootcamp.parkinglot;

public class ParkingLotFullException extends Exception{
    public ParkingLotFullException() {
        super("parking lot is full");
    }
}
