package com.atguigu.admin.controller;

import com.atguigu.admin.bean.Account;
import com.atguigu.admin.bean.City;
import com.atguigu.admin.service.AccountService;
import com.atguigu.admin.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class DataSource_MybatisController {

    //测试druid查询数据.  并且测试druid监控组件
    @Autowired
    JdbcTemplate jdbcTemplate;

    @ResponseBody
    @GetMapping("/sql")
    public String queryFromDb(){
        Long aLong = jdbcTemplate.queryForObject("select count(*) from account_tbl", Long.class);
        return aLong.toString();
    }




    @Autowired
    CityService cityService;

    //3.测试使用注解+Mapper.xml配置文件的方式,使用mybatis
    @ResponseBody
    @PostMapping("/insertCity") //这里需要使用Postman发送请求
    public City insertCity(City city) {
        cityService.insert(city);
        return city;
    }

    //2.测试使用纯注解的方式,使用mybatis
    @ResponseBody
    @GetMapping("/city")
    public City getCityById(@RequestParam("id") Long id){
        return cityService.getById(id);
    }

    //1.测试使用配置文件的方式,使用mybatis
    @Autowired
    AccountService accountService;

    @ResponseBody
    @GetMapping("/acct")
    public Account getById(@RequestParam("id") Long id){

        return accountService.getAcctByid(id);
    }


/*
    //    @Autowired
    StringRedisTemplate redisTemplate;

    @ResponseBody
    @PostMapping("/city")
    public City saveCity(City city){

        cityService.saveCity(city);
        return city;
    }*/

    

}
