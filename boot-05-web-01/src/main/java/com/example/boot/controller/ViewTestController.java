package com.example.boot.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewTestController {

    @GetMapping("/atguigu")
    public String atguigu(Model model) {//这个model不是从客户端传入的.SpringBoot根据控制器方法所需传入的

        //model中的数据会被放在请求域中 request.setAttribute("a",aa)
        model.addAttribute("msg", "你好 guigu");
        model.addAttribute("link", "http://www.baidu.com");

        return "success";
    }
}
