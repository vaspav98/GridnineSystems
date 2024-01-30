package com.gridnine.testing;

import com.gridnine.testing.filter.FilterParams;
import com.gridnine.testing.filter.FlightSpecification;
import com.gridnine.testing.models.Flight;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> inputData = FlightBuilder.createFlights();

        FilterParams params1 = new FilterParams();
        params1.setDepartureBeforeCurrentTime(true);
        FlightSpecification spec1 = FlightSpecification.build(params1);
        List<Flight> filteredFlights1 = FlightManager.findFlights(spec1, inputData);
        List<Flight> excludeFlights1 = new ArrayList<>(inputData);
        excludeFlights1.removeAll(filteredFlights1);

        FilterParams params2 = new FilterParams();
        params2.setSegmentsArrivalEarlierDeparture(true);
        FlightSpecification spec2 = FlightSpecification.build(params2);
        List<Flight> filteredFlights2 = FlightManager.findFlights(spec2, inputData);
        List<Flight> excludeFlights2 = new ArrayList<>(inputData);
        excludeFlights2.removeAll(filteredFlights2);

        FilterParams params3 = new FilterParams();
        params3.setHoursOnEarthGt(2L);
        FlightSpecification spec3 = FlightSpecification.build(params3);
        List<Flight> filteredFlights3 = FlightManager.findFlights(spec3, inputData);
        List<Flight> excludeFlights3 = new ArrayList<>(inputData);
        excludeFlights3.removeAll(filteredFlights3);

        System.out.println(
                "Исключить перелёты, где вылет до текущего момента времени: " + excludeFlights1);
        System.out.println(
                "Исключить перелёты, где имеются сегменты с датой прилёта раньше даты вылета: " + excludeFlights2);
        System.out.println(
                "Исключить перелёты, где общее время, проведённое на земле превышает два часа: " + excludeFlights3);
    }

}
