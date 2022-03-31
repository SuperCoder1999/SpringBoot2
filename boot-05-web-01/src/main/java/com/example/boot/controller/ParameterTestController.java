package com.example.boot.controller;

import com.example.boot.bean.Person;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ParameterTestController {

    /**
     * 自定义对象参数 的处理过程
     * 数据绑定：页面提交的请求数据（GET、POST等方式提交）都可以和对象属性进行绑定
     */
    @PostMapping("/saveuser")
    public Person saveuser(Person person) {

        return person;
    }

    // 这是rest风格 car/2/owner/zhangsan href="car/3/owner/lisi?age=18&inters=basketball&inters=game"
    @GetMapping("/car/{id}/owner/{username}")
    public Map<String, Object> getCar(@PathVariable("id") Integer id,
                                      @PathVariable("username") String name,
                                      @PathVariable Map<String, String> pv,
                                      @RequestHeader("User-Agent") String userAgent,
                                      @RequestHeader Map<String, String> header,//获取所有请求头
                                      @RequestParam("age") Integer age,
                                      @RequestParam("inters") List<String> inters,
                                      @RequestParam Map<String, String> params,
                                      //所有以RequestParam请求参数的名和值都封装到Map.疑问?interests的值只能表示一个呀???
                                      @CookieValue("Idea-ccc9b468") String _ga, //获取某个单独的cookie值(cookie名按照浏览器中的来)
                                      @CookieValue("Idea-ccc9b468") Cookie cookie) { //获取整个cookie信息
        Map<String, Object> map = new HashMap<>();

//        map.put("id",id);
//        map.put("name",name);
//        map.put("pv",pv);
        //@PathVariable Map<String,String> pv,能将所有rest风格传入的参数都以k-v封装到Map中
//        map.put("userAgent",userAgent);
//        map.put("headers",header); //获取所有请求头信息
        map.put("age", age);
        map.put("inters", inters);
        map.put("params", params);
        map.put("_ga", _ga);
        System.out.println(cookie.getName() + "===>" + cookie.getValue());
        return map;
    }


    //获取请求体
    @PostMapping("/save")
    public Map postMethod(@RequestBody String content) {
        Map<String, Object> map = new HashMap<>();
        map.put("content", content);
        return map;
    }


    //矩阵变量
    //1、语法： 请求路径：/cars/sell;low=34;brand=byd,audi,yd
    //2、SpringBoot默认是禁用了矩阵变量的功能
    //      手动开启：原理: 对于路径的处理。UrlPathHelper进行解析。
    //              这个removeSemicolonContent属性（是否移除分号内容）支持矩阵变量的
    //3、矩阵变量必须有url路径变量才能被解析,即{path}
    @GetMapping("/cars/{path}")
    public Map carsSell(@MatrixVariable("low") Integer low, //取出矩阵变量中 low的值
                        @MatrixVariable("brand") List<String> brand,
                        @PathVariable("path") String path) {
        Map<String, Object> map = new HashMap<>();

        map.put("low", low);
        map.put("brand", brand);
        map.put("path", path);
        return map;
    }

    //获取两个同名称参数
    // /boss/1;age=20/2;age=10
    @GetMapping("/boss/{bossId}/{empId}")
    public Map boss(@MatrixVariable(value = "age", pathVar = "bossId") Integer bossAge,//添加pathVar,区别不同路径下的同名称参数
                    @MatrixVariable(value = "age", pathVar = "empId") Integer empAge) {
        Map<String, Object> map = new HashMap<>();

        map.put("bossAge", bossAge);
        map.put("empAge", empAge);
        return map;

    }

}
