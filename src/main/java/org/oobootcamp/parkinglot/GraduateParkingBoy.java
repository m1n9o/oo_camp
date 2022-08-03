package org.oobootcamp.parkinglot;

import java.util.Comparator;
import java.util.List;

public class GraduateParkingBoy extends ParkingBoy {

    public GraduateParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Comparator<ParkingLot> parkingLotComparator() {
        return (p1, p2) -> 0;
    }
}
