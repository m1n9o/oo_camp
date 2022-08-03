package org.oobootcamp.parkinglot;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.oobootcamp.parkinglot.exception.InvalidTicketException;
import org.oobootcamp.parkinglot.exception.ParkingLotFullException;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    @Test
    void should_get_parking_ticket_when_park_given_1_vacancy_left() {
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.park(new Car());

        Car car = new Car();
        Ticket ticket = parkingLot.park(car);

        assertNotNull(ticket);
    }

    @Test()
    void should_notice_parking_lot_is_full_when_park_given_0_vacancy_left() {
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.park(new Car());
        parkingLot.park(new Car());

        Car car = new Car();

        assertThrows(
                ParkingLotFullException.class,
                () -> parkingLot.park(car)
        );

    }

    @Test
    void should_pick_up_car_successfully_when_pick_up_given_valid_ticket() {
        ParkingLot parkingLot = new ParkingLot(2);

        Car car = new Car();
        Ticket ticket = parkingLot.park(car);

        assertEquals(car, parkingLot.pickUp(ticket));
    }

    @Test
    void should_notice_invalid_ticket_when_pickUp_given_the_car_of_ticket_not_in_this_parking_lot() {
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.park(new Car());

        assertThrows(
                InvalidTicketException.class,
                () -> parkingLot.pickUp(new Ticket())
        );
    }

    @Test
    void should_notice_invalid_ticket_when_pick_up_given_the_ticket_already_used_successfully() throws ParkingLotFullException, InvalidTicketException {
        ParkingLot parkingLot = new ParkingLot(2);
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);

        parkingLot.pickUp(ticket);

        assertThrows(
                InvalidTicketException.class,
                () -> parkingLot.pickUp(ticket)
        );
    }
}
