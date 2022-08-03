package org.oobootcamp.parkinglot;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class GraduateParkingBoy {
    private final List<ParkingLot> parkingLots;

    public GraduateParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) throws ParkingLotFullException {
        ParkingLot parkingLot = parkingLots.stream()
                .filter(Predicate.not(ParkingLot::isFull))
                .max(parkingLotComparator())
                .orElseThrow(ParkingLotFullException::new);

        return parkingLot.park(car);
    }

    public Comparator<ParkingLot> parkingLotComparator() {
        return (p1, p2) -> 0;
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
