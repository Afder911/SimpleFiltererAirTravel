package com.gridnine.testing.factory;

import com.gridnine.testing.filter_service.FlightFilter;
import com.gridnine.testing.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FlightFilterFactory {
    private static final String PROPERTIES = "/application.yaml";
    private static final String PACKAGE = "com.gridnine.testing.filter_service_imp.";
    private static final FlightFilterFactory INSTANCE;

    private final List<FlightFilter> flightFilters = new ArrayList<>();

    private FlightFilterFactory() {
        Properties props = new Properties();
        try {
            props.load(this.getClass().getResourceAsStream(PROPERTIES));
            String flightFiltersProp = props.getProperty("flightFilters");

            if (flightFiltersProp == null || flightFiltersProp.equals("")) {
                throw new IllegalArgumentException("Вы не указали фильтры, " +
                        "показаны все рейсы. Проверьте application.yaml");
            }

            for (String flightFilter : flightFiltersProp.split(" ")) {
                addFlightFilter(flightFilter);
            }
        } catch (Exception e) {
            Log.error(e);
        }
    }

    private void addFlightFilter(String flightFilter) {
        try {
            flightFilters.add((FlightFilter)
                    Class.forName(PACKAGE + flightFilter)
                            .getDeclaredConstructor().newInstance());
            Log.info("Filter: " + flightFilter);

        } catch (InstantiationException | IllegalAccessException |
                 IllegalArgumentException | InvocationTargetException |
                 NoSuchMethodException | SecurityException |
                 ClassNotFoundException e) {
            Log.error("Filter: " + flightFilter +
                    " - Недопустимое имя фильтра. " +
                    "Проверьте application.yaml", e);
        }
    }

    static {
        INSTANCE = new FlightFilterFactory();
    }

    public static FlightFilterFactory getInstance() {
        return INSTANCE;
    }

    public List<FlightFilter> getFlightFilters() {
        return flightFilters;
    }
}
