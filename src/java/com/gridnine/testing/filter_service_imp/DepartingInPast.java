package com.gridnine.testing.filter_service_imp;

import com.gridnine.testing.domain.Flight;
import com.gridnine.testing.domain.Segment;
import com.gridnine.testing.filter_service.FlightFilter;

import java.time.LocalDateTime;

public class DepartingInPast implements FlightFilter {

    @Override
    public boolean check(Flight flight) {
        for (Segment seg : flight.getSegments()) {
            if (seg.getDepartureDate().isBefore(LocalDateTime.now())) {
                return false;
            }
        }
        return true;
    }
}
