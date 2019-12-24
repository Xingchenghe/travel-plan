package com.travel.bo;

import com.travel.pojo.Taxi;

public class TaxiBo extends Taxi {
    String driverName;
    String driverPhone;

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
