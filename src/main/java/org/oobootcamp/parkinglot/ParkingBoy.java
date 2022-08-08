package org.oobootcamp.parkinglot;

import org.oobootcamp.parkinglot.exception.InvalidTicketException;
import org.oobootcamp.parkinglot.exception.ParkingLotFullException;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import static java.util.function.Predicate.not;

public abstract class ParkingBoy implements Parkable{
    private final List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) throws ParkingLotFullException {
        ParkingLot parkingLot = parkingLots.stream()
                .filter(not(ParkingLot::isFull))
                .max(parkingLotComparator())
                .orElseThrow(ParkingLotFullException::new);

        return parkingLot.park(car);
    }

    @Override
    public boolean isFull() {
        return parkingLots.stream()
                .allMatch(ParkingLot::isFull);
    }

    @Override
    public boolean contains(Ticket ticket) {
        return parkingLots.stream()
                .anyMatch(parkingLot -> parkingLot.contains(ticket));
    }

    protected abstract Comparator<ParkingLot> parkingLotComparator();

    public Car pickUp(Ticket ticket) throws InvalidTicketException {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.contains(ticket))
                .map(parkingLot -> parkingLot.pickUp(ticket))
                .findFirst()
                .orElseThrow(InvalidTicketException::new);
    }
}
