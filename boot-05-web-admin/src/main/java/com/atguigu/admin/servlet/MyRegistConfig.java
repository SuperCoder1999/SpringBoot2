package com.atguigu.admin.servlet;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * 第二种方法,Web原生组件注入（Servlet、Filter、Listener）
 *
 * 目前项目中包含了两个servlet
 * 1、自己定义的MyServlet --> /my
 * 2、SpringMVC的DispatcherServlet --> /    应该是检测所有进来的请求路径,看是否在SpringMVC中能处理
 * 访问 /css/bootstrap.min时,老师演示正常,而我的就经过DispatcherServlet.转发了/error请求
 */
// (proxyBeanMethods = true)：保证依赖的组件始终是单实例的
//@Configuration(proxyBeanMethods = true)
public class MyRegistConfig {

    @Bean
    public ServletRegistrationBean myServlet(){
        MyServlet myServlet = new MyServlet();

        return new ServletRegistrationBean(myServlet,"/my","/my/02");
        //如果MyServlet拦截了css.则会导致DispatcherServlet访问的请求中需要用到css时,就到
    }


    @Bean
    public FilterRegistrationBean myFilter(){

        MyFilter myFilter = new MyFilter();
//        return new FilterRegistrationBean(myFilter,myServlet()); //只拦截servlet请求路径
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(myFilter);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/my","/css/*"));
        return filterRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean myListener(){
        MySwervletContextListener mySwervletContextListener = new MySwervletContextListener();
        return new ServletListenerRegistrationBean(mySwervletContextListener);
    }
}
