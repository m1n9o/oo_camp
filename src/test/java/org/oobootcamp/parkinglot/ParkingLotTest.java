package org.oobootcamp.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    @Test
    void should_get_parking_ticket_when_park_given_1_vacancy_left() throws ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.park(new Car("陕A 88888"));

        String carNumber = "陕A 99999";
        Car car = new Car(carNumber);
        Ticket ticket = parkingLot.park(car);

        assertEquals(carNumber, ticket.getCarNumber());
    }

    @Test()
    void should_notice_parking_lot_is_full_when_park_given_0_vacancy_left() throws ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(2);
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
    void should_pick_up_car_and_11_left_when_pickUpCar_given_ticket_and_10_vacants() throws ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(11);
        String carNumber = "陕A 88888";
        Car car = new Car(carNumber);
        Ticket ticket = parkingLot.park(car);

        assertEquals(car, parkingLot.pickUpCar(ticket));
    }

    @Test
    void should_pick_up_nothing_and_10_left_when_pickUpCar_given_ticket_and_10_vacants() throws ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(11);
        String carNumber = "陕A 88888";
        Car car = new Car(carNumber);

        parkingLot.park(car);

        assertNull(parkingLot.pickUpCar(new Ticket("陕U 88888")));
    }

    @Test
    void should_pick_up_nothing_and_10_left_when_pickUpCar_given_no_ticket_and_10_vacants() throws ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(11);
        String carNumber = "陕A 88888";
        Car car = new Car(carNumber);
        parkingLot.park(car);

        assertNull(parkingLot.pickUpCar(null));
    }
}
