package com.travel.bo;

import com.travel.pojo.Flight;

public class FlightBo extends Flight {
    private String airlineName;
    private String startAirportName;
    private String arrivalAirportName;

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getStartAirportName() {
        return startAirportName;
    }

    public void setStartAirportName(String startAirportName) {
        this.startAirportName = startAirportName;
    }

    public String getArrivalAirportName() {
        return arrivalAirportName;
    }

    public void setArrivalAirportName(String arrivalAirportName) {
        this.arrivalAirportName = arrivalAirportName;
    }

    public FlightBo() {}
}
