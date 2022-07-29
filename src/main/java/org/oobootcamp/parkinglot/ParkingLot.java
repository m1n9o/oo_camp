package org.oobootcamp.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private final int maxParkingSpace;
    private final List<Car> parkedCars = new ArrayList<>();

    public ParkingLot(int maxParkingSpace) {
        this.maxParkingSpace = maxParkingSpace;
    }

    public Ticket park(Car car) throws ParkingLotFullException {
        if (parkedCars.size() == maxParkingSpace) {
            throw new ParkingLotFullException();
        }
        parkedCars.add(car);
        return new Ticket(car.getCarNumber());
    }

    public Car pickUpCar(Ticket ticket) {
        if (ticket == null)
        {
            return null;
        }
        for(Car car : parkedCars) {
            if (ticket.getCarNumber().equals(car.getCarNumber())) {
                return car;
            }
        }
        return null;
    }
}
