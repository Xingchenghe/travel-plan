package com.travel.service;

import com.travel.bo.HotelBo;
import com.travel.bo.HotelBrandBo;
import com.travel.mapper.HotelBrandMapper;
import com.travel.mapper.HotelMapper;
import com.travel.pojo.City;
import com.travel.pojo.Hotel;
import com.travel.pojo.HotelBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelService {
    @Autowired
    HotelMapper hotelMapper;
    @Autowired
    HotelBrandMapper hotelBrandMapper;
    @Autowired
    FlightService flightService;
    public List<HotelBo> queryAllHotelBos(){
        return this.hotelMapper.queryAllHotelBos();
    }

    public HotelBrand queryHotelBrandByName(String name){
        Example example=new Example(HotelBrand.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("brandName",name);
        List<HotelBrand> hotelBrands = hotelBrandMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(hotelBrands))
            return hotelBrands.get(0);
        return null;
    }

    public List<HotelBrandBo> queryAllHotelBrandBos(){
        List<HotelBrand> hotelBrands = hotelBrandMapper.selectAll();
        List<HotelBrandBo> hotelBrandBos= new ArrayList<>();
        hotelBrands.forEach(brand->{
            HotelBrandBo brandBo=new HotelBrandBo();
            brandBo.setBrandNo(brand.getBrandNo());
            brandBo.setBrandName(brand.getBrandName());
            brandBo.setValue(brand.getBrandName());
            hotelBrandBos.add(brandBo);
        });
        return hotelBrandBos;
    }

    public Hotel queryHotelByName(String name){
        Hotel hotel=new Hotel();
        hotel.setHotelName(name);
        return this.hotelMapper.selectOne(hotel);
    }

    public Hotel queryHotelById(Long id){
        return hotelMapper.selectByPrimaryKey(id);
    }

    public void updateHotel(Hotel hotel){
        this.hotelMapper.updateByPrimaryKey(hotel);
    }

    public List<HotelBo> queryHotelBosByCityNameANdBrandName(String city,String brand){
        List<HotelBo> hotelBos=new ArrayList<>();
        Example example=new Example(Hotel.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(city)){
            City city1 = flightService.queryCityByName(city);
            if (city1==null)
                return null;
            criteria.andEqualTo("cityNo",city1.getCityNo());
        }
        if (!StringUtils.isEmpty(brand)) {
            HotelBrand hotelBrand = this.queryHotelBrandByName(brand);
            if (hotelBrand == null) {
                return null;
            }
            criteria.andEqualTo("brandNo",hotelBrand.getBrandNo());
        }
        List<Hotel> hotels = hotelMapper.selectByExample(example);
        hotels.forEach(hotel -> {
            HotelBo hotelBo=hotelMapper.queryHotelBoById(hotel.getHotelId());
            hotelBos.add(hotelBo);
        });
        return hotelBos;
    }

    public void addHotel(HotelBo hotelBo) {
        hotelMapper.insert(hotelBo);
    }

    public void deleteHotelById(Long id) {
        hotelMapper.deleteByPrimaryKey(id);
    }
}
