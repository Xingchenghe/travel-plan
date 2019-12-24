package com.travel.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "driver")
public class Driver {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long driverNo;
  private String driverName;
  private String driverPhone;

  public Long getDriverNo() {
    return driverNo;
  }

  public void setDriverNo(Long driverNo) {
    this.driverNo = driverNo;
  }


  public String getDriverName() {
    return driverName;
  }

  public void setDriverName(String driverName) {
    this.driverName = driverName;
  }


  public String getDriverPhone() {
    return driverPhone;
  }

  public void setDriverPhone(String driverPhone) {
    this.driverPhone = driverPhone;
  }

}
