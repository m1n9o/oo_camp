package org.oobootcamp.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingLotTest {

    @Test
    void should_get_parking_ticket_when_park_given_1_vacancy_left() throws ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot("A", 2);
        parkingLot.park(new Car("陕A 88888"));

        String carNumber = "陕A 99999";
        Car car = new Car(carNumber);
        Ticket ticket = parkingLot.park(car);

        assertEquals(carNumber, ticket.getCarNumber());
    }

    @Test()
    void should_notice_parking_lot_is_full_when_park_given_0_vacancy_left() throws ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot("A", 2);
        parkingLot.park(new Car("陕A 88888"));
        parkingLot.park(new Car("陕A 99999"));

        String carNumber = "陕A 66666";
        Car car = new Car(carNumber);

        assertThrows(
                ParkingLotFullException.class,
                () -> parkingLot.park(car),
                "parking lot is full"
        );

    }

    @Test
    void should_pick_up_car_successfully_when_pick_up_given_valid_ticket() throws ParkingLotFullException, InvalidTicketException {
        ParkingLot parkingLot = new ParkingLot("A", 2);

        Car car = new Car("陕A 88888");
        Ticket ticket = parkingLot.park(car);

        assertEquals(car, parkingLot.pickUp(ticket));
    }

    @Test
    void should_notice_invalid_ticket_when_pickUp_given_the_car_of_ticket_not_in_this_parking_lot() throws ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot("A", 2);
        parkingLot.park(new Car("陕A 88888"));

        assertThrows(
                InvalidTicketException.class,
                () -> parkingLot.pickUp(new Ticket("陕A 99999", "A")),
                "invalid ticket"
        );
    }

    @Test
    void should_notice_invalid_ticket_when_pick_up_given_the_ticket_already_used_successfully() throws ParkingLotFullException, InvalidTicketException {
        ParkingLot parkingLot = new ParkingLot("A", 2);
        String carNumber = "陕A 88888";
        Car car = new Car(carNumber);
        Ticket ticket = parkingLot.park(car);

        parkingLot.pickUp(ticket);

        assertThrows(
                InvalidTicketException.class,
                () -> parkingLot.pickUp(ticket),
                "invalid ticket"
        );
    }
}
