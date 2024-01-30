package com.gridnine.testing;

import com.gridnine.testing.filter.FilterParams;
import com.gridnine.testing.filter.FlightSpecification;
import com.gridnine.testing.models.Flight;
import com.gridnine.testing.models.Segment;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;

public class FiltersTest {
    private final LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);

    @Test
    public void testFilterDepartureBeforeCurrentTime() {
        Flight testFlight = new Flight(List.of(new Segment(threeDaysFromNow.minusDays(6), threeDaysFromNow)));
        Flight testFlight2 = new Flight(List.of(new Segment(threeDaysFromNow, threeDaysFromNow)));

        FilterParams params = new FilterParams();
        params.setDepartureBeforeCurrentTime(true);
        FlightSpecification spec = FlightSpecification.build(params);

        List<Flight> filteredFlights = FlightManager.findFlights(spec, List.of(testFlight));
        assert !filteredFlights.isEmpty();

        filteredFlights = FlightManager.findFlights(spec, List.of(testFlight2));
        assert filteredFlights.isEmpty();
    }

    @Test
    public void testFilterSegmentsArrivalEarlierDeparture() {
        Flight testFlight = new Flight(List.of(new Segment(threeDaysFromNow, threeDaysFromNow.minusHours(6))));
        Flight testFlight2 = new Flight(List.of(new Segment(threeDaysFromNow, threeDaysFromNow)));

        FilterParams params = new FilterParams();
        params.setSegmentsArrivalEarlierDeparture(true);
        FlightSpecification spec = FlightSpecification.build(params);

        List<Flight> filteredFlights = FlightManager.findFlights(spec, List.of(testFlight));
        assert !filteredFlights.isEmpty();

        filteredFlights = FlightManager.findFlights(spec, List.of(testFlight2));
        assert filteredFlights.isEmpty();
    }

    @Test
    public void testFilterHoursOnEarthGt() {
        Flight testFlight = new Flight(List.of(new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                new Segment(threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(7))));

        Flight testFlight2 = new Flight(List.of(new Segment(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                new Segment(threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6))));

        FilterParams params = new FilterParams();
        params.setHoursOnEarthGt(3L);
        FlightSpecification spec = FlightSpecification.build(params);

        List<Flight> filteredFlights = FlightManager.findFlights(spec, List.of(testFlight));
        assert !filteredFlights.isEmpty();

        filteredFlights = FlightManager.findFlights(spec, List.of(testFlight2));
        assert filteredFlights.isEmpty();
    }

}
