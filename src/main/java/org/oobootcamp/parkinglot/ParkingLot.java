package org.oobootcamp.parkinglot;

import org.oobootcamp.parkinglot.exception.InvalidTicketException;
import org.oobootcamp.parkinglot.exception.ParkingLotFullException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot implements Parkable{
    private final int capacity;
    private final Map<Ticket, Car> ticketCarMap = new HashMap<>();
    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public Ticket park(Car car) throws ParkingLotFullException {
        if (isFull()) {
            throw new ParkingLotFullException();
        }
        Ticket ticket = new Ticket();
        ticketCarMap.put(ticket, car);
        return ticket;
    }

    public int vacancies() {return capacity - ticketCarMap.size();}

    public boolean isFull() {
        return ticketCarMap.size() == capacity;
    }

    public Car pickUp(Ticket ticket) throws InvalidTicketException {
        if (ticketCarMap.containsKey(ticket)) {
            return ticketCarMap.remove(ticket);
        }
        throw new InvalidTicketException();
    }

    public boolean containCarWith(Ticket ticket) {
        return ticketCarMap.containsKey(ticket);
    }
}
