package com.atguigu.boot.controller;


import org.springframework.web.bind.annotation.*;

//@ResponseBody (表示return是返回浏览器字符串,而不是转到某个页面)
//@Controller(Body和Controller写在一起,可以被RestController代替)
// JRebel

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String handle01(){
        return "Hello, Spring Boot 2!" + "你好";
    }

}
