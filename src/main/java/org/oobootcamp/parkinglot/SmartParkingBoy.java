package org.oobootcamp.parkinglot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class SmartParkingBoy {
    private final List<ParkingLot> parkingLots;

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = new ArrayList<>(parkingLots);
    }

    public Ticket park(Car car) throws ParkingLotFullException {
        parkingLots.sort(Comparator.comparingInt(ParkingLot::vacancies).reversed());

        ParkingLot parkingLot = parkingLots.stream()
                .filter(Predicate.not(ParkingLot::isFull))
                .findFirst()
                .orElseThrow(ParkingLotFullException::new);

        return parkingLot.park(car);
    }

    public Car pickUp(Ticket ticket) throws InvalidTicketException {
        for (ParkingLot parkinglot : parkingLots) {
            if (parkinglot.contains(ticket)) {
                return parkinglot.pickUp(ticket);
            }
        }
        throw new InvalidTicketException();
    }
}
