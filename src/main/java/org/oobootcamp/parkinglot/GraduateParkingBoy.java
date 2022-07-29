package org.oobootcamp.parkinglot;

import java.util.List;

public class GraduateParkingBoy {
    private final List<ParkingLot> parkingLots;

    public GraduateParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) throws ParkingLotFullException {
        for (ParkingLot parkinglot: parkingLots) {
            if (!parkinglot.isParkingLotFull()) {
                return parkinglot.park(car);
            }
        }
        throw new ParkingLotFullException();
    }

    public Car pickUp(Ticket ticket) throws InvalidTicketException {
        for (ParkingLot parkinglot: parkingLots) {
            try {
                return parkinglot.pickUp(ticket);
            } catch (InvalidTicketException e) {
                e.printStackTrace();
            }
        }
        throw new InvalidTicketException();
    }
}
