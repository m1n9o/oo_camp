package org.oobootcamp.parkinglot;

import org.oobootcamp.parkinglot.exception.InvalidTicketException;
import org.oobootcamp.parkinglot.exception.ParkingLotFullException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.function.Predicate.not;

public class ParkingManager extends ParkingBoy {
    private final List<ParkingBoy> parkingBoys;

    public ParkingManager(List<ParkingLot> parkingLots, List<ParkingBoy> parkingBoys) {
        super(parkingLots);
        List<ParkingBoy> list = new ArrayList<>();
        list.add(this);
        list.addAll(parkingBoys);
        this.parkingBoys = list;
    }

    public Ticket park(Car car) {
        Parkable parkable = parkingBoys.stream()
                .filter(not(Parkable::isFull))
                .findFirst()
                .orElseThrow(ParkingLotFullException::new);
        return parkable.park(car);
    }

    @Override
    protected Comparator<ParkingLot> parkingLotComparator() {
        return (p1, p2) -> 0;
    }

    public Car pickUp(Ticket ticket) {
        Parkable parkable = parkingBoys.stream()
                .filter(p -> p.containCarWith(ticket))
                .findFirst()
                .orElseThrow(InvalidTicketException::new);
        return parkable.pickUp(ticket);
    }
}
