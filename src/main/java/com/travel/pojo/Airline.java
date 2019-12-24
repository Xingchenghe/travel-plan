package com.travel.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "airline")
public class Airline {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long airlineNo;
  private String airlineName;


  public Long getAirlineNo() {
    return airlineNo;
  }

  public void setAirlineNo(long airlineNo) {
    this.airlineNo = airlineNo;
  }


  public String getAirlineName() {
    return airlineName;
  }

  public void setAirlineName(String airlineName) {
    this.airlineName = airlineName;
  }

}
