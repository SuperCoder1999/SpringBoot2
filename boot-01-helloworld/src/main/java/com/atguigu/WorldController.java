package com.atguigu;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//这个controller放在主程序包外面.默认扫描不到
//可以在主程序中设置:@SpringBootApplication("com.atguigu")//设置扫描的基础包
/*或者:@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan("com.atguigu")//设置扫描的基础包*/
@RestController
public class WorldController {

    @RequestMapping("/w")
    public String world66() {
        return "World";
    }
}
