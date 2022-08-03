package org.oobootcamp.parkinglot.exception;

public class ParkingLotFullException extends RuntimeException{
    public ParkingLotFullException() {
        super("parking lot is full");
    }
}
