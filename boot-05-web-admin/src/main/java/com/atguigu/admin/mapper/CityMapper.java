package com.atguigu.admin.mapper;

import com.atguigu.admin.bean.City;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

//@Mapper
@Repository
public interface CityMapper {

    //利用mybatis中的用纯注解方式,代替了CityMapper.xml,所以resources包下面 不需要mybatis包
    @Select("select * from city where id=#{id}")
    public City getById(Long id);


    //使用接口上添加sql语句不满足要求时,必须创建对应Mapper.xml(但是也能用纯注解解决:如下两个注解)
    @Insert("insert into  city(`name`,`state`,`country`) values(#{name},#{state},#{country})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    public void insert(City city);

}
