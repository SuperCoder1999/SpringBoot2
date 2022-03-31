package com.example.boot.controller;


import com.example.boot.bean.Person;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

//测试响应
@Controller
public class ResponseTestController {


    @ResponseBody //--RequestResponseBodyMethodProcessor ---> messageConverter
    @GetMapping("/he11")
    public FileSystemResource file() {

        //文件以这样的方式返回看是谁处理的（messageConverter）。
        /*
        解析器为: RequestResponseBodyMethodProcessor 可以处理返回值标了@ResponseBody 注解的。
        返回值转换器: SourceHttpMessageConverter 将Class类型对象转化为MediaType(是由内容协商决定)
         */
        return null;
    }


    /**
     * 给前端自动返回json数据
     * 1、浏览器发请求直接返回 xml    [application/xml]        jacksonXmlConverter
     * 2、如果是ajax请求 返回 json   [application/json]      jacksonJsonConverter
     * 3、如果硅谷app发请求，返回自定义协议数据  [appliaction/x-guigu]   xxxxConverter
     * 属性值1;属性值2;
     * <p>
     * 步骤：
     * 1、添加自定义的MessageConverter进系统底层
     * 2、系统底层就会统计出所有MessageConverter能操作哪些类型
     * 3、客户端内容协商 [guigu--->guigu]
     * <p>
     * 作业：如何以参数的方式进行内容协商
     *
     * @return
     */
    @ResponseBody  //利用返回值处理器里面的消息转换器进行处理
    @GetMapping(value = "/test/person")
    public Person getPerson() {
        Person person = new Person();
        person.setAge(28);
        person.setBirth(new Date());
        person.setUserName("zhangsan");
        return person;
    }

}
