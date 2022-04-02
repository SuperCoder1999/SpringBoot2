package com.atguigu.admin.service.impl;

import com.atguigu.admin.bean.City;
import com.atguigu.admin.mapper.CityMapper;
import com.atguigu.admin.service.CityService;
//import io.micrometer.core.instrument.Counter;
//import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    //这里报错,不用担心
    @Autowired
    CityMapper cityMapper;

    public City getById(Long id){
        return cityMapper.getById(id);
    }

    public void insert(City city) {
        cityMapper.insert(city);

    }

    /*Counter counter;

    public CityServiceImpl(MeterRegistry meterRegistry){
        counter = meterRegistry.counter("cityService.saveCity.count");
    }

    public void saveCity(City city) {
        counter.increment();
        cityMapper.insert(city);

    }*/
}
