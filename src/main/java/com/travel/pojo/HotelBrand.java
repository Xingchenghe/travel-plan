package com.travel.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "hotel_brand")
public class HotelBrand {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long brandNo;
  private String brandName;


  public Long getBrandNo() {
    return brandNo;
  }

  public void setBrandNo(Long brandNo) {
    this.brandNo = brandNo;
  }


  public String getBrandName() {
    return brandName;
  }

  public void setBrandName(String brandName) {
    this.brandName = brandName;
  }

}
