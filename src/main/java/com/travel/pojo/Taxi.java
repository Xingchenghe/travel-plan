package com.travel.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "taxi")
public class Taxi {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long taxiId;
  private String carNo;
  private Long cityNo;
  private String carModel;
  private Long driverNo;
  private Long ordered;

  public Long getOrdered() {
    return ordered;
  }

  public void setOrdered(Long ordered) {
    this.ordered = ordered;
  }

  public Long getTaxiId() {
    return taxiId;
  }

  public void setTaxiId(Long taxiId) {
    this.taxiId = taxiId;
  }


  public String getCarNo() {
    return carNo;
  }

  public void setCarNo(String carNo) {
    this.carNo = carNo;
  }


  public Long getCityNo() {
    return cityNo;
  }

  public void setCityNo(Long cityNo) {
    this.cityNo = cityNo;
  }


  public String getCarModel() {
    return carModel;
  }

  public void setCarModel(String carModel) {
    this.carModel = carModel;
  }


  public Long getDriverNo() {
    return driverNo;
  }

  public void setDriverNo(Long driverNo) {
    this.driverNo = driverNo;
  }

}
