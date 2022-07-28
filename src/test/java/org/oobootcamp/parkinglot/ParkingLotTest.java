package org.oobootcamp.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ParkingLotTest {
    @Test
    void should_get_parking_ticket_and_9_left_when_park_given_10_vacants() {
        ParkingLot parkingLot = new ParkingLot(10);
        String carNumber = "陕A 88888";
        Car car = new Car(carNumber);

        Ticket ticket = parkingLot.park(car);

        assertEquals(carNumber, ticket.getCarNumber());
        assertEquals(9, parkingLot.getVacancies());
    }

    @Test
    void should_not_get_parking_ticket_and_0_left_when_park_given_0_vacants() {
        ParkingLot parkingLot = new ParkingLot(0);
        String carNumber = "陕A 88888";
        Car car = new Car(carNumber);

        Ticket ticket = parkingLot.park(car);

        assertNull(ticket);
        assertEquals(0, parkingLot.getVacancies());
    }

    @Test
    void should_pick_up_car_and_11_left_when_pickUpCar_given_ticket_and_10_vacants() {
        ParkingLot parkingLot = new ParkingLot(11);
        String carNumber = "陕A 88888";
        Car car = new Car(carNumber);
        Ticket ticket = parkingLot.park(car);

        assertEquals(car, parkingLot.pickUpCar(ticket));
        assertEquals(11, parkingLot.getVacancies());
    }

    @Test
    void should_pick_up_nothing_and_10_left_when_pickUpCar_given_ticket_and_10_vacants() {
        ParkingLot parkingLot = new ParkingLot(11);
        String carNumber = "陕A 88888";
        Car car = new Car(carNumber);
        parkingLot.park(car);

        assertNull(parkingLot.pickUpCar(new Ticket("陕U 88888")));
        assertEquals(10, parkingLot.getVacancies());
    }

    @Test
    void should_pick_up_nothing_and_10_left_when_pickUpCar_given_no_ticket_and_10_vacants() {
        ParkingLot parkingLot = new ParkingLot(11);
        String carNumber = "陕A 88888";
        Car car = new Car(carNumber);
        parkingLot.park(car);

        assertNull(parkingLot.pickUpCar(null));
        assertEquals(10, parkingLot.getVacancies());
    }
}
