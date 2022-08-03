package org.oobootcamp.parkinglot;

import org.oobootcamp.parkinglot.exception.InvalidTicketException;
import org.oobootcamp.parkinglot.exception.ParkingLotFullException;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public abstract class ParkingBoy {
    private final List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) throws ParkingLotFullException {
        ParkingLot parkingLot = parkingLots.stream()
                .filter(Predicate.not(ParkingLot::isFull))
                .max(parkingLotComparator())
                .orElseThrow(ParkingLotFullException::new);

        return parkingLot.park(car);
    }

    public abstract Comparator<ParkingLot> parkingLotComparator();

    public Car pickUp(Ticket ticket) throws InvalidTicketException {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.contains(ticket))
                .map(parkingLot -> parkingLot.pickUp(ticket))
                .findFirst()
                .orElseThrow(InvalidTicketException::new);
    }
}
