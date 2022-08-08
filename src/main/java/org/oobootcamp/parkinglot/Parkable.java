package org.oobootcamp.parkinglot;

public interface Parkable {
    Ticket park(Car car);

    boolean isFull();

    Car pickUp(Ticket ticket);

    boolean containCarWith(Ticket ticket);
}
