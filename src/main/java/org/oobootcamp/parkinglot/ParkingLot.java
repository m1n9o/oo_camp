package org.oobootcamp.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private int vacancies;
    private List<Car> parkedCars = new ArrayList<>();

    public ParkingLot(int vacants) {
        this.vacancies = vacants;
    }

    public Ticket park(Car car) {
        if (vacancies == 0) {
            return null;
        }
        parkedCars.add(car);
        vacancies -= 1;
        return new Ticket(car.getCarNumber());
    }

    public int getVacancies() {
        return vacancies;
    }

    public Car pickUpCar(Ticket ticket) {
        if (ticket == null)
        {
            return null;
        }
        for(Car car : parkedCars) {
            if (ticket.getCarNumber().equals(car.getCarNumber())) {
                vacancies += 1;
                return car;
            }
        }
        return null;
    }
}
