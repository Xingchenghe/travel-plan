package com.travel.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "city")
public class City {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long cityNo;
  private String cityName;


  public Long getCityNo() {
    return cityNo;
  }

  public void setCityNo(long cityNo) {
    this.cityNo = cityNo;
  }


  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

}
