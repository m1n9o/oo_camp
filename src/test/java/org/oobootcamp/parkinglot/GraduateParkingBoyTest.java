package org.oobootcamp.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GraduateParkingBoyTest {
    @Test
    void should_get_ticket_when_park_given_1_parking_lot_A_and_1_left_in_A() throws ParkingLotFullException, InvalidTicketException {
        ParkingLot parkingLotA = new ParkingLot(1);

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotA));

        Car car = new Car();
        Ticket ticket = graduateParkingBoy.park(car);

        assertEquals(car, parkingLotA.pickUp(ticket));
    }

    @Test
    void should_get_ticket_when_park_given_2_parking_lots_A_B_in_order_and_1_left_in_A_and_1_left_in_B() throws ParkingLotFullException, InvalidTicketException {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotA, parkingLotB));

        Car car = new Car();
        Ticket ticket = graduateParkingBoy.park(car);

        assertEquals(car, parkingLotA.pickUp(ticket));
    }

    @Test
    void should_get_ticket_when_park_given_2_parking_lots_A_B_in_order_and_0_left_in_A_and_1_left_in_B() throws ParkingLotFullException, InvalidTicketException {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        parkingLotA.park(new Car());
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotA, parkingLotB));

        Car car = new Car();
        Ticket ticket = graduateParkingBoy.park(car);

        assertEquals(car, parkingLotB.pickUp(ticket));
    }

    @Test
    void should_get_ticket_when_park_given_2_parking_lots_A_B_in_order_and_1_left_in_A_and_0_left_in_B() throws ParkingLotFullException, InvalidTicketException {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot( 1);
        parkingLotB.park(new Car());
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotA, parkingLotB));

        Car car = new Car();
        Ticket ticket = graduateParkingBoy.park(car);

        assertEquals(car, parkingLotA.pickUp(ticket));
    }

    @Test
    void should_notice_parking_lot_is_full_when_park_given_2_parking_lots_A_B_in_order_and_0_left_in_A_and_0_left_in_B() throws ParkingLotFullException {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        parkingLotA.park(new Car());
        parkingLotB.park(new Car());

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotA, parkingLotB));


        assertThrows(
                ParkingLotFullException.class,
                () -> graduateParkingBoy.park(new Car()),
                "parking lot is full"
        );
    }

    @Test
    void should_pick_up_car_successfully_when_pick_up_given_the_car_of_ticket_in_parking_lot_A() throws ParkingLotFullException, InvalidTicketException {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotA, parkingLotB));
        Car car = new Car();
        Ticket ticket = graduateParkingBoy.park(car);

        assertEquals(car, graduateParkingBoy.pickUp(ticket));
    }

    @Test
    void should_notice_invalid_ticket_when_pick_up_given_the_car_is_not_in_this_parking_lot() throws ParkingLotFullException {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot( 1);

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotA, parkingLotB));
        graduateParkingBoy.park(new Car());

        assertThrows(
                InvalidTicketException.class,
                () -> graduateParkingBoy.pickUp(new Ticket()),
                "invalid ticket"
        );
    }

    @Test
    void should_notice_invalid_ticket_when_pick_up_given_the_ticket_already_used_successfully() throws ParkingLotFullException, InvalidTicketException {
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotA, parkingLotB));
        Ticket ticket = graduateParkingBoy.park(new Car());
        Car car = graduateParkingBoy.pickUp(ticket);

        assertNotNull(car);

        assertThrows(
                InvalidTicketException.class,
                () -> graduateParkingBoy.pickUp(ticket),
                "invalid ticket"
        );
    }
}
