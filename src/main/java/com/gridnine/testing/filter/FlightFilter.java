package com.gridnine.testing.filter;

import com.gridnine.testing.models.Flight;
import java.util.List;

@FunctionalInterface
public interface FlightFilter {
    List<Flight> filter(List<Flight> flights);
}
