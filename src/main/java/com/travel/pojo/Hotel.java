package com.travel.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "hotel")
public class Hotel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long hotelId;
  private Long brandNo;
  private String hotelName;
  private Long cityNo;
  private String hotelAddr;
  private String hotelPhone;
  private Long hotelPrice;
  private Long hotelRoomsLeft;

  public Hotel() {
  }

  public Hotel(Long brandNo, String hotelName, Long cityNo, String hotelAddr, String hotelPhone, Long hotelPrice, Long hotelRoomsLeft) {
    this.brandNo = brandNo;
    this.hotelName = hotelName;
    this.cityNo = cityNo;
    this.hotelAddr = hotelAddr;
    this.hotelPhone = hotelPhone;
    this.hotelPrice = hotelPrice;
    this.hotelRoomsLeft = hotelRoomsLeft;
  }

  public Long getHotelId() {
    return hotelId;
  }

  public void setHotelId(Long hotelId) {
    this.hotelId = hotelId;
  }


  public Long getBrandNo() {
    return brandNo;
  }

  public void setBrandNo(Long brandNo) {
    this.brandNo = brandNo;
  }


  public String getHotelName() {
    return hotelName;
  }

  public void setHotelName(String hotelName) {
    this.hotelName = hotelName;
  }


  public Long getCityNo() {
    return cityNo;
  }

  public void setCityNo(Long cityNo) {
    this.cityNo = cityNo;
  }


  public String getHotelAddr() {
    return hotelAddr;
  }

  public void setHotelAddr(String hotelAddr) {
    this.hotelAddr = hotelAddr;
  }


  public String getHotelPhone() {
    return hotelPhone;
  }

  public void setHotelPhone(String hotelPhone) {
    this.hotelPhone = hotelPhone;
  }


  public Long getHotelPrice() {
    return hotelPrice;
  }

  public void setHotelPrice(Long hotelPrice) {
    this.hotelPrice = hotelPrice;
  }


  public Long getHotelRoomsLeft() {
    return hotelRoomsLeft;
  }

  public void setHotelRoomsLeft(Long hotelRoomsLeft) {
    this.hotelRoomsLeft = hotelRoomsLeft;
  }

}
