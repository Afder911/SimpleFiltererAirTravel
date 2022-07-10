package com.gridnine.testing.factory;

import com.gridnine.testing.domain.Flight;
import com.gridnine.testing.filter_service.FlightFilter;

import java.util.List;

public class FlightFilters {

    private FlightFilters() {
    }

    public static boolean check(Flight flight) {

        List<FlightFilter> filters = FlightFilterFactory
                .getInstance().getFlightFilters();

        for (FlightFilter flightFilter : filters) {
            if (!flightFilter.check(flight))
                return false;
        }
        return true;
    }
}
