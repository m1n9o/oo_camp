package org.oobootcamp.parkinglot;

import org.oobootcamp.parkinglot.exception.InvalidTicketException;
import org.oobootcamp.parkinglot.exception.ParkingLotFullException;

import java.util.List;

import static java.util.function.Predicate.not;

public class ParkingManager {
    private final List<Parkable> parkables;

    public ParkingManager(List<Parkable> parkables) {
        this.parkables = parkables;
    }

    public Ticket park(Car car) {
        Parkable parkable = parkables.stream()
                .filter(not(Parkable::isFull))
                .findFirst()
                .orElseThrow(ParkingLotFullException::new);
        return parkable.park(car);
    }

    public Car pickUp(Ticket ticket) {
        Parkable parkable = parkables.stream()
                .filter(p -> p.containCarWith(ticket))
                .findFirst()
                .orElseThrow(InvalidTicketException::new);
        return parkable.pickUp(ticket);
    }
}
