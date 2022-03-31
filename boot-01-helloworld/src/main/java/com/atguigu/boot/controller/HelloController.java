package com.atguigu.boot.controller;


import com.atguigu.boot.bean.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@ResponseBody (表示return是返回浏览器字符串,而不是转到某个页面)
//@Controller(Body和Controller写在一起,可以被RestController代替)

// JRebel 相比于dev-tools,它热更新(只更新变化的项目内容)
//lombok简化日志开发
@Slf4j
@RestController
public class HelloController {

    //测试ConfigurationProperties,读取到properties文件中的内容，并且把它封装到JavaBean中
    @Autowired
    Car car;

    @RequestMapping("/car")
    public Car car() {
        return car;
    }

    //创建一个简单的Spring工程.
    //测试中文编码问题
    @RequestMapping("/hello")
    public String handle01(@RequestParam("name") String name) {
        //测试HttpEncodingAutoConfiguration是否配置

        log.info("请求进来了....");
        return "Hello, Spring Boot 2!" + "你好：" + name;
    }
}
