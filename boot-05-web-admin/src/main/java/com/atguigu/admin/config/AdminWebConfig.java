package com.atguigu.admin.config;

import com.atguigu.admin.interceptor.LoginInterceptor;
//import com.atguigu.admin.interceptor.RedisUrlCountInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;




/**
 * 和前面 自定义Filter很像. WebMvcConfigurer是包含MVC功能的类,其中的方法可以重写.或者实现(Filter中写了)
 * 1、编写一个拦截器实现HandlerInterceptor接口
 * 2、拦截器注册到容器中（实现WebMvcConfigurer的addInterceptors）
 * 3、指定拦截规则【如果是拦截所有，静态资源也会被拦截】
 *
 * @EnableWebMvc:全面接管
 *      1、静态资源？视图解析器？欢迎页.....全部失效
 */
@Slf4j
//@EnableWebMvc
@Configuration
public class AdminWebConfig implements WebMvcConfigurer{


    /**
     * Filter、Interceptor 几乎拥有相同的功能？
     * 1、Filter是Servlet定义的原生组件。好处，脱离Spring应用也能使用
     * 2、Interceptor是Spring定义的接口。可以使用Spring的自动装配等功能
     *
     */
//    @Autowired
    //RedisUrlCountInterceptor redisUrlCountInterceptor;

    /**
     * 定义静态资源行为
     * @param registry
     */
/*    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        *//**
         * 访问  /aa/** 所有请求都去 classpath:/static/ 下面进行匹配
         *//*
        registry.addResourceHandler("/aa/**")
                .addResourceLocations("classpath:/static/");
    }*/

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")  //所有请求都被拦截包括静态资源
                .excludePathPatterns("/","/login","/css/**","/fonts/**","/images/**",
                        "/js/**","/aa/**", "/sql","/insertCity"); //放行的请求
        //疑问: 这里的css等静态资源,也会因为没有session被返回到主页面.并且还请求了 /error请求
           /*也可以采用在application.properties中设置spring.mvc.static-path-pattern=/static
           此时,页面中访问静态资源的连接,都要添加:/static(/static/css/...).此时这里就可以只放开/static/**
            */

//        registry.addInterceptor(redisUrlCountInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/","/login","/css/**","/fonts/**","/images/**",
//                        "/js/**","/aa/**");
    }

/*
    //通过WebMvcRegistrations修改底层组件
    @Bean
    public WebMvcRegistrations webMvcRegistrations(){
        return new WebMvcRegistrations(){
            @Override
            public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
                return null;
            }
        };
    }

*/

}
