package org.oobootcamp.parkinglot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private final int maxParkingSpace;
    private final Map<Ticket, Car> ticketCarMap = new HashMap<>();
    public ParkingLot(int capacity) {
        this.maxParkingSpace = capacity;
    }

    public Ticket park(Car car) throws ParkingLotFullException {
        if (isFull()) {
            throw new ParkingLotFullException();
        }
        Ticket ticket = new Ticket();
        ticketCarMap.put(ticket, car);
        return ticket;
    }

    public boolean isFull() {
        return ticketCarMap.size() == maxParkingSpace;
    }

    public Car pickUp(Ticket ticket) throws InvalidTicketException {
        if (ticketCarMap.containsKey(ticket)) {
            return ticketCarMap.remove(ticket);
        }
        throw new InvalidTicketException();
    }

    public boolean contains(Ticket ticket) {
        return ticketCarMap.containsKey(ticket);
    }
}
