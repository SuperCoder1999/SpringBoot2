package com.atguigu.boot;


import ch.qos.logback.classic.db.DBHelper;
import com.atguigu.boot.bean.Pet;
import com.atguigu.boot.bean.User;
import com.atguigu.boot.config.MyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.cache.interceptor.CacheAspectSupport;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.SpringVersion;

import javax.swing.*;

/**
 * 主程序类;主配置类(所有启动的入口)
 *
 * @SpringBootApplication：这是一个SpringBoot应用
 */

//@SpringBootApplication//这一个注解,替代了下面三个注解
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan("com.atguigu.boot")//设置扫描的基础包
public class MainApplication {


    public static void main(String[] args) {
        //1、返回我们IOC容器
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);

        //2、查看容器里面的组件
        /*String[] names = run.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }*/

        //查询组件个数.
        int beanDefinitionCount = run.getBeanDefinitionCount();
        System.out.println(beanDefinitionCount);

        //从ConditionalOnClass条件判断某个包下面类是否导入容器中
        //从容器中获取组件(用于判断cache包下的某个类下的组件有没有导入到容器内.从ConditionalOnClass条件判断)
        String[] beanNamesForType = run.getBeanNamesForType(CacheAspectSupport.class);
        System.out.println("======" + beanNamesForType.length);

        //从容器中获取组件(用于判断Web下DispatcherServletAutoConfiguration类下的组件有没有导入到容器内)
        String[] beanNamesForType1 = run.getBeanNamesForType(WebMvcProperties.class);
        System.out.println("======" + beanNamesForType1.length);

        //IOC组件:
        //这里是从IOC容器中获取 组件实例.和下面的代理对象调用方法(方法本身被@Bean注解为组件)获取方式不一样.
//        Pet tom01 = run.getBean("tom", Pet.class);
//        Pet tom02 = run.getBean("tom", Pet.class);
//        System.out.println("组件："+(tom01 == tom02));

        //4、配置类也是一个组件
        //是一个被SpringMVC增强的代理对象com.atguigu.boot.config.MyConfig$$EnhancerBySpringCGLIB$$51f1e1ca@1654a892
        MyConfig bean = run.getBean(MyConfig.class);
        System.out.println(bean);

        //如果@Configuration(proxyBeanMethods = true),并且用代理对象调用方法。SpringBoot总会检查这个组件是否在容器中有实例。
        //保持组件单实例
        User user = bean.user01();
        User user1 = bean.user01();
        System.out.println(user == user1);

        //这是使用代理对象的好处:可以让配置类MyConfig中,user组件依赖了Pet组件时,保证Pet组件单实例.
        User user01 = run.getBean("user01", User.class);
        Pet tom = run.getBean("tom22", Pet.class);

        System.out.println("用户的宠物(proxyBeanMethods)：" + (user01.getPet() == tom));


        /*//5、获取组件(测试import注解给配置类添加的组件)
        String[] beanNamesForType2 = run.getBeanNamesForType(User.class);//根据组件类型获取组件的名字
        System.out.println("======");
        for (String s : beanNamesForType2) {
            System.out.println(s);
        }

        DBHelper bean1 = run.getBean(DBHelper.class);
        System.out.println(bean1);*/

        boolean tom001 = run.containsBean("tom");
        System.out.println("容器中Tom组件：" + tom001);

        boolean user001 = run.containsBean("user01");
        System.out.println("容器中user01组件：" + user001);

        boolean tom22 = run.containsBean("tom22");
        System.out.println("容器中tom22组件：" + tom22);


        boolean haha = run.containsBean("haha");
        boolean hehe = run.containsBean("hehe");
        System.out.println("haha：" + haha);
        System.out.println("hehe：" + hehe);

        //查看当前Spring所用的版本
        System.out.println("SpringVersion" + SpringVersion.getVersion());

    }
}
