package com.travel.bo;

import com.travel.pojo.Hotel;

public class HotelBo extends Hotel {
    String brandName;
    String cityName;

    public HotelBo() {
    }

    public HotelBo(Hotel hotel) {
        super(hotel.getBrandNo(), hotel.getHotelName(), hotel.getCityNo(), hotel.getHotelAddr(), hotel.getHotelPhone(), hotel.getHotelPrice(), hotel.getHotelRoomsLeft());
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
