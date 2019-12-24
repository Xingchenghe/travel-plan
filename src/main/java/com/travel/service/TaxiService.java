package com.travel.service;

import com.travel.bo.TaxiBo;
import com.travel.mapper.DriverMapper;
import com.travel.mapper.TaxiMapper;
import com.travel.pojo.Driver;
import com.travel.pojo.Taxi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaxiService {
    @Autowired
    TaxiMapper taxiMapper;
    @Autowired
    DriverMapper driverMapper;

    public List<TaxiBo> queryAllTaxiBos(){
        return taxiMapper.queryAllTaxiBos();
    }

    public void updateTaxi(Taxi taxi){
        this.taxiMapper.updateByPrimaryKey(taxi);
    }

    public Taxi queryTaxiById(Long id){
        return taxiMapper.selectByPrimaryKey(id);
    }
    public Taxi queryTaxiByCarNo(String carNo){
        return this.taxiMapper.queryTaxiByCarNo(carNo);
    }

    public Driver queryDriverByDriverNo(Long driverNo){
        Driver driver=new Driver();
        driver.setDriverNo(driverNo);
        return driverMapper.selectByPrimaryKey(driverNo);
    }

    public Driver queryDriverByName(String name){
        Driver driver=new Driver();
        driver.setDriverName(name);
        return driverMapper.selectOne(driver);
    }

    public void updateDriver(Driver driver){
        this.driverMapper.updateByPrimaryKey(driver);
    }

    public void addDriver(Driver driver){
        driverMapper.insert(driver);
    }

    public List<TaxiBo> queryTaxiBosByCityName(String name){
        return taxiMapper.queryTaxiBosByCityName(name);
    }

    public void addTaxi(TaxiBo taxiBo) {
        taxiMapper.insert(taxiBo);
    }

    public void deleteTaxiById(Long id) {
        taxiMapper.deleteByPrimaryKey(id);
    }
}
