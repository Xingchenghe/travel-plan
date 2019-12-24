package com.travel.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "airport")
public class Airport {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long airportNo;
  private Long cityNo;
  private String airportName;

  public Long getAirportNo() {
    return airportNo;
  }

  public void setAirportNo(Long airportNo) {
    this.airportNo = airportNo;
  }

  public Long getCityNo() {
    return cityNo;
  }

  public void setCityNo(Long cityNo) {
    this.cityNo = cityNo;
  }

  public String getAirportName() {
    return airportName;
  }

  public void setAirportName(String airportName) {
    this.airportName = airportName;
  }
}
