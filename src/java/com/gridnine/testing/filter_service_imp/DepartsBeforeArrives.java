package com.gridnine.testing.filter_service_imp;

import com.gridnine.testing.domain.Flight;
import com.gridnine.testing.domain.Segment;
import com.gridnine.testing.filter_service.FlightFilter;

import java.time.LocalDateTime;
import java.util.List;

public class DepartsBeforeArrives implements FlightFilter {

    @Override
    public boolean check(Flight flight) {
        if (flight.getSegments().size() == 1) {
            // Полёт с одним сегментом
            LocalDateTime dep = flight.getSegments().get(0).getDepartureDate();
            LocalDateTime arr = flight.getSegments().get(0).getArrivalDate();
            if (dep.isAfter(arr)) {
                return false;
            }
        } else {
            // Полёт с множеством сегментов
            List<Segment> segmentList = flight.getSegments();
            for (int i = 0; i < segmentList.size() - 1; i++) {
                LocalDateTime dep = segmentList.get(i).getDepartureDate();
                LocalDateTime arr = segmentList.get(i).getArrivalDate();
                LocalDateTime depNext = segmentList.get(i + 1).getDepartureDate();
                if (dep.isAfter(arr) || arr.isAfter(depNext)) {
                    return false;
                }
            }
        }
        return true;
    }
}
