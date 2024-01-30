package com.gridnine.testing.filter;

import com.gridnine.testing.models.Flight;
import com.gridnine.testing.models.Segment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightSpecification {

    private List<FlightFilter> filters = new ArrayList<>();

    public static FlightSpecification build(FilterParams params) {
        FlightSpecification spec = new FlightSpecification();
        spec.departureBeforeCurrentTime(params.getDepartureBeforeCurrentTime())
                .segmentsArrivalEarlierDeparture(params.getSegmentsArrivalEarlierDeparture())
                .hoursOnEarthGt(params.getHoursOnEarthGt());

        return spec;
    }

    private FlightSpecification departureBeforeCurrentTime(Boolean param) {
        if (param == null) {
            return this;
        }

        this.addFilter(flights -> {
            return flights.stream()
                    .filter(f -> f.getSegments().get(0).getDepartureDate().isBefore(LocalDateTime.now()))
                    .collect(Collectors.toList());
        });
        return this;
    }

    private FlightSpecification segmentsArrivalEarlierDeparture(Boolean param) {
        if (param == null) {
            return this;
        }

        this.addFilter(flights -> {
            List<Flight> filteredFlights = new ArrayList<>();
            for (Flight flight : flights) {
                for (Segment segment : flight.getSegments()) {
                    if (segment.getArrivalDate().isBefore(segment.getDepartureDate())) {
                        filteredFlights.add(flight);
                    }
                }
            }
            return filteredFlights;
        });
        return this;
    }

    private FlightSpecification hoursOnEarthGt(Long param) {
        if (param == null) {
            return this;
        }

        this.addFilter(flights -> {
            List<Flight> filteredFlights = new ArrayList<>();
            for (Flight flight : flights) {
                List<Segment> segments = flight.getSegments();
                long hoursOnEarth = 0;

                for (int i = 0; i < segments.size(); i++) {
                    if (i == segments.size() - 1) {
                        break;
                    }
                    Duration dur = Duration.between(segments.get(i).getArrivalDate(),
                            segments.get(i + 1).getDepartureDate());
                    hoursOnEarth = dur.toHours();
                }

                if (hoursOnEarth > param) {
                    filteredFlights.add(flight);
                }
            }
            return filteredFlights;
        });

        return this;
    }

    private void addFilter(FlightFilter filter) {
        filters.add(filter);
    }

    public List<FlightFilter> getFilters() {
        return this.filters;
    }

}
