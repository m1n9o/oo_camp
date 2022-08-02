package org.oobootcamp.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SmartParkingBoyTest {
    @Test
    void should_park_car_in_A_and_get_ticket_when_park_given_two_parking_lots_A_B_and_2_left_in_A_while_1_left_in_B() throws InvalidTicketException, ParkingLotFullException {
        ParkingLot parkingLotA = new ParkingLot(2);
        ParkingLot parkingLotB = new ParkingLot(1);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLotA, parkingLotB));
        Car car = new Car();
        Ticket ticket = smartParkingBoy.park(car);

        assertEquals(car, parkingLotA.pickUp(ticket));
    }

    @Test
    void should_park_car_in_A_and_get_ticket_when_park_given_two_parking_lots_A_B_and_1_left_in_A_while_2_left_in_B() throws InvalidTicketException, ParkingLotFullException {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(2);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLotA, parkingLotB));
        Car car = new Car();
        Ticket ticket = smartParkingBoy.park(car);

        assertEquals(car, parkingLotB.pickUp(ticket));
    }

    @Test
    void should_park_car_in_B_and_get_ticket_when_park_given_two_parking_lots_A_B_and_2_left_in_A_while_2_left_in_B() throws InvalidTicketException, ParkingLotFullException {
        ParkingLot parkingLotA = new ParkingLot(2);
        ParkingLot parkingLotB = new ParkingLot(2);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLotB, parkingLotA));
        Car car = new Car();
        Ticket ticket = smartParkingBoy.park(car);

        assertEquals(car, parkingLotB.pickUp(ticket));
    }


    @Test
    void should_notice_parking_log_is_full_when_park_given_two_parking_lots_A_B_and_0_left_in_A_while_0_left_in_B() throws ParkingLotFullException {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLotA, parkingLotB));
        parkingLotA.park(new Car());
        parkingLotB.park(new Car());

        assertThrows(
                ParkingLotFullException.class,
                () -> smartParkingBoy.park(new Car()),
                "parking lot is full"
        );
    }

    @Test
    void should_pick_up_car_successfully_when_pick_up_given_the_car_of_ticket_in_parking_lot_A() throws ParkingLotFullException, InvalidTicketException {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLotA, parkingLotB));
        Car car = new Car();
        Ticket ticket = smartParkingBoy.park(car);

        assertEquals(car, smartParkingBoy.pickUp(ticket));
    }

    @Test
    void should_notice_invalid_ticket_when_pick_up_given_the_car_is_not_in_the_parking_lot_managed_by_this_smart_boy() throws ParkingLotFullException {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot( 1);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLotA, parkingLotB));
        smartParkingBoy.park(new Car());

        assertThrows(
                InvalidTicketException.class,
                () -> smartParkingBoy.pickUp(new Ticket()),
                "invalid ticket"
        );
    }

    @Test
    void should_notice_invalid_ticket_when_pick_up_given_the_ticket_already_used_successfully() throws ParkingLotFullException, InvalidTicketException {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLotA, parkingLotB));
        Ticket ticket = smartParkingBoy.park(new Car());
        Car car = smartParkingBoy.pickUp(ticket);

        assertNotNull(car);

        assertThrows(
                InvalidTicketException.class,
                () -> smartParkingBoy.pickUp(ticket),
                "invalid ticket"
        );
    }
}
