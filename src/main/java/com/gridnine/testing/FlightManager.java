package com.gridnine.testing;

import com.gridnine.testing.filter.FlightFilter;
import com.gridnine.testing.filter.FlightSpecification;
import com.gridnine.testing.models.Flight;
import java.util.List;

public class FlightManager {

    public static List<Flight> findFlights(FlightSpecification spec, List<Flight> flights) {

        List<Flight> filteredFlights = flights;
        for (FlightFilter filter : spec.getFilters()) {
            if (filteredFlights.isEmpty()) {
                return filteredFlights;
            }
            filteredFlights = filter.filter(filteredFlights);
        }
        return filteredFlights;
    }

}
