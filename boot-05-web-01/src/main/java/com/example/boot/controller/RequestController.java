package com.example.boot.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class RequestController {

    //查看复杂请求参数 处理原理
    @GetMapping("/params")
    public String testParam(Map<String, Object> map,
                            Model model,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        map.put("hello", "world666");
        model.addAttribute("world", "hello666");
        //上面两个操作,看似添加到map和model中,实际上还是存在了request域中
        request.setAttribute("message", "HelloWorld");

        Cookie cookie = new Cookie("c1", "v1");
        response.addCookie(cookie);
        return "forward:/success";
    }

    //演示RequestAttribute的用法
    //追源码,看到ServletRequestMethodArgumentResolver解析HttpServletRequest参数
    @GetMapping("/goto")
    public String goToPage(HttpServletRequest request) {
        //在request域中存数据
        request.setAttribute("msg", "成功了...");
        request.setAttribute("code", 200);
        return "forward:/success";  //转发到  /success请求
    }

    //RequestAttribute获取请求域中的属性值(一般用于页面转发时,转发到jsp,获取当前请求的数据)
    @ResponseBody
    @GetMapping("/success")
    //required=false:允许请求域中没有指定参数
    public Map success(@RequestAttribute(value = "msg", required = false) String msg,//用RequestAttribute获取两个controller里的同一个request域中的数据
                       @RequestAttribute(value = "code", required = false) Integer code,
                       HttpServletRequest request) {
        //因为转发request是同一个,所以调用request方法也能获取域中的数据
        Object msg1 = request.getAttribute("msg");

        //输出域中的数据
        Map<String, Object> map = new HashMap<>();
        Object hello = request.getAttribute("hello");//这个是放在map中的数据,实际上在request中
        Object world = request.getAttribute("world");
        Object message = request.getAttribute("message");

        map.put("reqMethod_msg", msg1);
        map.put("annotation_msg", msg);
        map.put("hello", hello);
        map.put("world", world);
        map.put("message", message);

        return map;

    }


}
