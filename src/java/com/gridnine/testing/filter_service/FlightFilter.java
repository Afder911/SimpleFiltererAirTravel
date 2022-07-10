package com.gridnine.testing.filter_service;

import com.gridnine.testing.domain.Flight;

public interface FlightFilter {
    public boolean check(Flight flight);
}
