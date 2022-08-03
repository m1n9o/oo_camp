package org.oobootcamp.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Comparator<ParkingLot> parkingLotComparator() {
        return Comparator.comparingInt(ParkingLot::vacancies);
    }
}
