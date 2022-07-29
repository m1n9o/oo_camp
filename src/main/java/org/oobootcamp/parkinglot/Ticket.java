package org.oobootcamp.parkinglot;

public class Ticket {
    private final String carNumber;
    private final String parkingLotID;

    public Ticket(String carNumber, String parkingLotID) {

        this.carNumber = carNumber;
        this.parkingLotID = parkingLotID;
    }

    public String getCarNumber() {
        return this.carNumber;
    }

    public String getParkingLotID() {
        return parkingLotID;
    }
}
