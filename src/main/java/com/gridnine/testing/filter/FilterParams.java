package com.gridnine.testing.filter;

public class FilterParams {
    private Boolean departureBeforeCurrentTime;
    private Boolean segmentsArrivalEarlierDeparture;
    private Long hoursOnEarthGt;


    public Boolean getDepartureBeforeCurrentTime() {
        return departureBeforeCurrentTime;
    }

    public void setDepartureBeforeCurrentTime(Boolean departureBeforeCurrentTime) {
        this.departureBeforeCurrentTime = departureBeforeCurrentTime;
    }

    public Boolean getSegmentsArrivalEarlierDeparture() {
        return segmentsArrivalEarlierDeparture;
    }

    public void setSegmentsArrivalEarlierDeparture(Boolean segmentsArrivalEarlierDeparture) {
        this.segmentsArrivalEarlierDeparture = segmentsArrivalEarlierDeparture;
    }

    public Long getHoursOnEarthGt() {
        return hoursOnEarthGt;
    }

    public void setHoursOnEarthGt(Long hoursOnEarthGt) {
        this.hoursOnEarthGt = hoursOnEarthGt;
    }

}
