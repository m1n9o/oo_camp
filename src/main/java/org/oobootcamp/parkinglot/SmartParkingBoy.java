package org.oobootcamp.parkinglot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class SmartParkingBoy extends GraduateParkingBoy{

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Comparator<ParkingLot> parkingLotComparator() {
        return Comparator.comparingInt(ParkingLot::vacancies);
    }
}
