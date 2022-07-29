package org.oobootcamp.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GraduateParkingBoyTest {
    @Test
    void should_get_ticket_when_park_given_2_parking_lots_and_1_left_in_A_and_1_left_in_B() throws ParkingLotFullException {
        ParkingLot parkingLotA = new ParkingLot("A", 1);
        ParkingLot parkingLotB = new ParkingLot("B", 1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotA, parkingLotB));

        Car car = new Car("陕A 88888");
        Ticket ticket = graduateParkingBoy.park(car);

        assertEquals(ticket.getCarNumber(), car.getCarNumber());
        assertEquals(parkingLotA.getParkingLotID(), ticket.getParkingLotID());
    }

    @Test
    void should_get_ticket_when_park_given_2_parking_lots_and_0_left_in_A_and_1_left_in_B() throws ParkingLotFullException {
        ParkingLot parkingLotA = new ParkingLot("A", 1);
        ParkingLot parkingLotB = new ParkingLot("B", 1);

        parkingLotA.park(new Car("陕U 88888"));

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotA, parkingLotB));

        Car car = new Car("陕A 88888");
        Ticket ticket = graduateParkingBoy.park(car);

        assertEquals(ticket.getCarNumber(), car.getCarNumber());
        assertEquals(parkingLotB.getParkingLotID(), ticket.getParkingLotID());
    }

    @Test
    void should_get_ticket_when_park_given_2_parking_lots_and_1_left_in_A_and_0_left_in_B() throws ParkingLotFullException {
        ParkingLot parkingLotA = new ParkingLot("A", 1);
        ParkingLot parkingLotB = new ParkingLot("B", 1);

        parkingLotB.park(new Car("陕U 88888"));

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotA, parkingLotB));

        Car car = new Car("陕A 88888");
        Ticket ticket = graduateParkingBoy.park(car);

        assertEquals(ticket.getCarNumber(), car.getCarNumber());
        assertEquals(parkingLotA.getParkingLotID(), ticket.getParkingLotID());
    }

    @Test
    void should_notice_parking_lot_is_full_when_park_given_2_parking_lots_and_0_left_in_A_and_0_left_in_B() throws ParkingLotFullException {
        ParkingLot parkingLotA = new ParkingLot("A", 1);
        ParkingLot parkingLotB = new ParkingLot("B", 1);
        parkingLotA.park(new Car("陕A 88888"));
        parkingLotB.park(new Car("陕U 88888"));

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotA, parkingLotB));


        assertThrows(
                ParkingLotFullException.class,
                () -> graduateParkingBoy.park(new Car("陕B 88888")),
                "parking lot is full"
        );
    }

    @Test
    void should_pick_up_car_successfully_when_pick_up_given_the_car_of_ticket_in_parking_lot_A() throws ParkingLotFullException, InvalidTicketException {
        ParkingLot parkingLotA = new ParkingLot("A", 1);
        ParkingLot parkingLotB = new ParkingLot("B", 1);

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotA, parkingLotB));
        Car car = new Car("陕B 88888");
        Ticket ticket = graduateParkingBoy.park(car);

        assertEquals(car, graduateParkingBoy.pickUp(ticket));
    }

    @Test
    void should_notice_invalid_ticket_when_pick_up_given_the_car_is_not_in_this_parking_lot() throws ParkingLotFullException {
        ParkingLot parkingLotA = new ParkingLot("A", 1);
        ParkingLot parkingLotB = new ParkingLot("B", 1);

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotA, parkingLotB));
        graduateParkingBoy.park(new Car("陕B 88888"));

        assertThrows(
                InvalidTicketException.class,
                () -> graduateParkingBoy.pickUp(new Ticket("陕A 88888", "A")),
                "invalid ticket"
        );
    }

    @Test
    void should_notice_invalid_ticket_when_pick_up_given_the_ticket_already_used_successfully() throws ParkingLotFullException, InvalidTicketException {
        ParkingLot parkingLotA = new ParkingLot("A", 1);
        ParkingLot parkingLotB = new ParkingLot("B", 1);

        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotA, parkingLotB));
        Ticket ticket = graduateParkingBoy.park(new Car("陕B 88888"));
        graduateParkingBoy.pickUp(ticket);

        assertThrows(
                InvalidTicketException.class,
                () -> graduateParkingBoy.pickUp(ticket),
                "invalid ticket"
        );
    }
}
