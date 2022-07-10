package com.gridnine.testing;

import com.gridnine.testing.domain.Flight;
import com.gridnine.testing.domain.FlightBuilder;
import com.gridnine.testing.filter_service.FlightFilter;
import com.gridnine.testing.filter_service_imp.DepartingInPast;
import com.gridnine.testing.filter_service_imp.DepartsBeforeArrives;
import com.gridnine.testing.filter_service_imp.MoreTwoHoursGroundTime;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.List;

public class MainTest {

    List<Flight> flights = FlightBuilder.createFlights();

    @Test
    void flightsCountAfterDepartingInPastFilterShouldBeOneLess() {
        FlightFilter flightFilter = new DepartingInPast();
        long actual = flights.stream().filter(flightFilter::check).count();
        int expected = flights.size() - 1;
        Assert.isTrue(expected == actual,
                "Количество рейсов после вылета в прошлом должно быть на один меньше");
    }
    @Test
    void flightsCountAfterDepartsBeforeArrivesFilterShouldBeOneLess() {
        FlightFilter flightFilter = new DepartsBeforeArrives();
        long actual = flights.stream().filter(flightFilter::check).count();
        int expected = flights.size() - 1;
        Assert.isTrue(expected == actual,
                "Количество рейсов после вылета до прибытия должно быть на один меньше");
    }
    @Test
    void flightsCountAfterMoreTwoHoursGroundTimeFilterShouldBeTwoLess() {
        FlightFilter flightFilter = new MoreTwoHoursGroundTime();
        long actual = flights.stream().filter(flightFilter::check).count();
        int expected = flights.size() - 2;
        Assert.isTrue(expected == actual,
                "Количество рейсов после более чем двух часов прибывания на земле должно быть на два меньше");
    }
}
