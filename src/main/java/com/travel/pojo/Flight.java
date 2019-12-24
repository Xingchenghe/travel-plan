package com.travel.pojo;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;

@Table(name = "flight")
public class Flight {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long flightId;
  private String flightNo;
  private Long airlineNo;
  private Long startAirportNo;
  private Long arrivalAirportNo;
//  private java.sql.Date startDate;
  private LocalDate startDate;
  @DateTimeFormat(pattern = "HH:mm:ss")
  private LocalTime startTime;
  private LocalDate arrivalDate;
  @DateTimeFormat(pattern = "HH:mm:ss")
  private LocalTime arrivalTime;
  private Long price;

  public Long getFlightId() {
    return flightId;
  }

  public void setFlightId(Long flightId) {
    this.flightId = flightId;
  }

  public String getFlightNo() {
    return flightNo;
  }

  public void setFlightNo(String flightNo) {
    this.flightNo = flightNo;
  }

  public Long getAirlineNo() {
    return airlineNo;
  }

  public void setAirlineNo(Long airlineNo) {
    this.airlineNo = airlineNo;
  }

  public Long getStartAirportNo() {
    return startAirportNo;
  }

  public void setStartAirportNo(Long startAirportNo) {
    this.startAirportNo = startAirportNo;
  }

  public Long getArrivalAirportNo() {
    return arrivalAirportNo;
  }

  public void setArrivalAirportNo(Long arrivalAirportNo) {
    this.arrivalAirportNo = arrivalAirportNo;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalTime getStartTime() {
    return startTime;
  }

  public void setStartTime(LocalTime startTime) {
    this.startTime = startTime;
  }

  public LocalDate getArrivalDate() {
    return arrivalDate;
  }

  public void setArrivalDate(LocalDate arrivalDate) {
    this.arrivalDate = arrivalDate;
  }

  public LocalTime getArrivalTime() {
    return arrivalTime;
  }

  public void setArrivalTime(LocalTime arrivalTime) {
    this.arrivalTime = arrivalTime;
  }

  public Long getPrice() {
    return price;
  }

  public void setPrice(Long price) {
    this.price = price;
  }
}
