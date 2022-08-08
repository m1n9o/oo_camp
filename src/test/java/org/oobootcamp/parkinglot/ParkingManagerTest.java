package org.oobootcamp.parkinglot;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.oobootcamp.parkinglot.exception.InvalidTicketException;
import org.oobootcamp.parkinglot.exception.ParkingLotFullException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingManagerTest {

    @Nested
    class ParkingMangerShouldParkSuccessfullyIfVacanciesInManagedScopeTest
    {
        @Test
        void should_park_car_in_A_and_get_ticket_when_park_given_3_parking_lots_A_B_C_and_2_left_for_each_while_A_managed_by_manager_and_B_managed_by_graduated_boy_and_C_managed_by_smart_boy() {
            ParkingLot parkingLotA = new ParkingLot(2);
            ParkingLot parkingLotB = new ParkingLot(2);
            ParkingLot parkingLotC = new ParkingLot(2);

            GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotB));
            SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLotC));
            Car car = new Car();

            Ticket ticket = new ParkingManager(List.of(parkingLotA, graduateParkingBoy, smartParkingBoy)).park(car);

            assertEquals(car, parkingLotA.pickUp(ticket));
        }

        @Test
        void should_park_car_in_B_and_get_ticket_when_park_given_3_parking_lots_A_B_C_while_A_is_full_BC_has_two_left_while_A_managed_by_manager_and_B_managed_by_graduated_boy_and_C_managed_by_smart_boy() {
            ParkingLot parkingLotA = new ParkingLot(1);
            parkingLotA.park(new Car());
            ParkingLot parkingLotB = new ParkingLot(2);
            ParkingLot parkingLotC = new ParkingLot(2);

            GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotB));
            SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLotC));
            Car car = new Car();

            Ticket ticket = new ParkingManager(List.of(parkingLotA, graduateParkingBoy, smartParkingBoy)).park(car);

            assertEquals(car, parkingLotB.pickUp(ticket));
        }

        @Test
        void should_park_car_in_C_and_get_ticket_when_park_given_3_parking_lots_A_B_C_while_AB_is_full_while_C_has_2_left_while_A_managed_by_manager_and_B_managed_by_graduated_boy_and_C_managed_by_smart_boy() {
            ParkingLot parkingLotA = new ParkingLot(1);
            parkingLotA.park(new Car());
            ParkingLot parkingLotB = new ParkingLot(1);
            parkingLotB.park(new Car());
            ParkingLot parkingLotC = new ParkingLot(2);

            GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotB));
            SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLotC));
            Car car = new Car();

            Ticket ticket = new ParkingManager(List.of(parkingLotA, graduateParkingBoy, smartParkingBoy)).park(car);

            assertEquals(car, parkingLotC.pickUp(ticket));
        }

    }

    @Nested
    class ParkingMangerShouldParkFailedIfNoVacanciesForAllManagedParkingLotTest
    {
        @Test
        void should_notice_parking_lot_is_full_when_park_given_3_parking_lots_A_B_C_while_ABC_is_full_while_A_managed_by_manager_and_B_managed_by_graduated_boy_and_C_managed_by_smart_boy() {
            ParkingLot parkingLotA = new ParkingLot(1);
            parkingLotA.park(new Car());
            ParkingLot parkingLotB = new ParkingLot(1);
            parkingLotB.park(new Car());
            ParkingLot parkingLotC = new ParkingLot(1);
            parkingLotC.park(new Car());

            GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotB));
            SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLotC));

            ParkingManager parkingManager = new ParkingManager(List.of(parkingLotA, graduateParkingBoy, smartParkingBoy));

            assertThrows(
                    ParkingLotFullException.class,
                    () -> parkingManager.park(new Car())
            );
        }
    }

    @Nested
    class ParkingMangerShouldPickUpSuccessfullyIfCarParkedInManagedParkingLotTest{
        @Test
        void should_pick_up_car_successfully_when_pick_up_given_parking_log_B_C_while_B_managed_by_graduated_boy_and_C_managed_by_smart_boy() {
            ParkingLot parkingLotB = new ParkingLot(1);
            ParkingLot parkingLotC = new ParkingLot(1);

            GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotB));
            SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLotC));

            ParkingManager parkingManager = new ParkingManager(List.of(graduateParkingBoy, smartParkingBoy));
            Car car = new Car();

            Ticket ticket = parkingManager.park(car);
            assertEquals(car, parkingManager.pickUp(ticket));
        }
    }

    @Nested
    class ParkingMangerShouldPickUpFailedIfTicketIsInvalidTest{
        @Test
        void should_notice_invalid_ticket_when_pick_up_given_the_car_is_not_in_the_parking_lot_managed_by_this_manager() {
            ParkingLot parkingLotB = new ParkingLot(1);
            ParkingLot parkingLotC = new ParkingLot(1);

            GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotB));
            SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLotC));

            ParkingManager parkingManager = new ParkingManager(List.of(graduateParkingBoy, smartParkingBoy));

            assertThrows(
                    InvalidTicketException.class,
                    () -> parkingManager.pickUp(new Ticket())
            );
        }

        @Test
        void should_notice_invalid_ticket_when_pick_up_given_the_ticket_already_used_successfully() {
            ParkingLot parkingLotB = new ParkingLot(1);
            ParkingLot parkingLotC = new ParkingLot(1);

            GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(List.of(parkingLotB));
            SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLotC));

            ParkingManager parkingManager = new ParkingManager(List.of(graduateParkingBoy, smartParkingBoy));
            Ticket ticket = parkingManager.park(new Car());
            parkingManager.pickUp(ticket);

            assertThrows(
                    InvalidTicketException.class,
                    () -> parkingManager.pickUp(ticket)
            );
        }
    }
}
