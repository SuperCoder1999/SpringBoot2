package com.atguigu.boot.bean;


import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * 只有在容器中的组件，才会拥有SpringBoot提供的强大功能
 */
@ToString
@Data
@Component//将Car添加到IOC容器中,作为组件
@ConfigurationProperties(prefix = "mycar")//ConfigurationProperties,读取到properties文件中的内容，并且把它封装到JavaBean中
public class Car {

    private String brand;
    private Integer price;

}
