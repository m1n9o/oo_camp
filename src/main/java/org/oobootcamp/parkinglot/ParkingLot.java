package org.oobootcamp.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private final String parkingLotID;
    private final int maxParkingSpace;
    private final List<Car> parkedCars = new ArrayList<>();

    public ParkingLot(String parkingLotID, int maxParkingSpace) {
        this.parkingLotID = parkingLotID;
        this.maxParkingSpace = maxParkingSpace;
    }

    public Ticket park(Car car) throws ParkingLotFullException {
        if (isFull()) {
            throw new ParkingLotFullException();
        }
        parkedCars.add(car);
        return new Ticket(car.getCarNumber(), parkingLotID);
    }

    public boolean isFull() {
        return parkedCars.size() == maxParkingSpace;
    }

    public Car pickUp(Ticket ticket) throws InvalidTicketException {
        for(Car car : parkedCars) {
            if (ticket.getCarNumber().equals(car.getCarNumber())) {
                parkedCars.remove(car);
                return car;
            }
        }
        throw new InvalidTicketException();
    }

    public String getParkingLotID() {
        return parkingLotID;
    }
}
