package com.atguigu.boot.config;


import ch.qos.logback.core.db.DBHelper;
import com.atguigu.boot.bean.Car;
import com.atguigu.boot.bean.Pet;
import com.atguigu.boot.bean.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.CharacterEncodingFilter;


/**
 * 1、配置类里面使用@Bean标注在方法上给容器注册组件，默认(获取的实例)也是单实例的
 * 2、配置类MyConfig本身也是组件
 * 3、proxyBeanMethods：代理bean的方法
 * Full(proxyBeanMethods = true)、【保证每个@Bean方法被调用多少次返回的组件都是单实例的】
 * Lite(proxyBeanMethods = false)【每个@Bean方法被调用多少次返回的组件都是新创建的】
 * 组件依赖必须使用Full模式默认。其他默认是否Lite模式
 * <p>
 * 4、@Import({User.class, DBHelper.class})
 * 给容器中自动创建出这两个类型的组件、默认组件的名字就是全类名:com.atguigu.boot.bean.User
 * <p>
 * <p>
 * 5、@ImportResource("classpath:beans.xml")导入Spring的配置文件，
 */
@Import({User.class, DBHelper.class})
@Configuration(proxyBeanMethods = true) //告诉SpringBoot这是一个配置类 等同于Spring的 配置文件(这是Component注解的扩展.同样的扩展:Service...
//@ConditionalOnBean(name = "tom")//当IOC容器存在指定的bean.备注是的配置类/配置方法才生效
@ConditionalOnMissingBean(name = "tom")//当IOC容器不存在指定的bean.备注是的配置类/配置方法才生效
@ImportResource("classpath:beans.xml")
/*
ImportResource注解:用于添加已经存在的beans.xml到SpringBoot扫描的配置类中.使其中的bean组件放入IOC容器中
使用方法:只需要在某一配置类上添加一次该注解.value设置为:资源路径
 */
@EnableConfigurationProperties(Car.class)
//1、开启Car配置绑定功能(使用ConfigurationProperties)
//2、把这个Car这个组件自动注册到容器中(使用EnableConfigurationProperties)
//3.要求,EnableConfigurationProperties注解需要用在一个配置类中
public class MyConfig {

    /**
     * Full:外部无论对配置类中的这个组件注册方法调用多少次获取的都是之前注册容器中的单实例对象
     *
     * @return
     */
    @Bean //给容器中添加组件。以方法名作为组件的id。返回类型就是组件类型。返回的值，就是组件在容器中的实例
    public User user01() {
        User zhangsan = new User("zhangsan", 18);
        //user组件依赖了Pet组件.在使用代理对象时,配置类中,方法互相调用能保证组件单实例
        zhangsan.setPet(tomcatPet());//proxyBeanMethods这里建议用 true
        return zhangsan;
    }

    @Bean("tom22")//自定义了组件的id
    public Pet tomcatPet() {
        return new Pet("tomcat");
    }

    //如果这里返回了自定义的CharacterEncodingFilter.则SpringBoot根据注解ConditionalOnMissingBean,没有注解所注解的组件时,SpringBoot才会配置
//    @Bean
//    public CharacterEncodingFilter filter(){
//        return null;
//    }
}
