package org.oobootcamp.parkinglot;

import java.util.List;

public class GraduateParkingBoy {
    private final List<ParkingLot> parkingLots;

    public GraduateParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) throws ParkingLotFullException {
        for (ParkingLot parkinglot: parkingLots) {
            if (!parkinglot.isFull()) {
                return parkinglot.park(car);
            }
        }
        throw new ParkingLotFullException();
    }

    public Car pickUp(Ticket ticket) throws InvalidTicketException {
        for (ParkingLot parkinglot: parkingLots) {
            if (parkinglot.contains(ticket)){
                return parkinglot.pickUp(ticket);
            }
        }
        throw new InvalidTicketException();
    }
}
