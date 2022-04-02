package com.atguigu.admin.bean;

import lombok.Data;

@Data
public class Account {

    private Integer id;
    //这里原数据类型是int(11). Java中用int或long,主要看数字的大小范围.
    //如果传入一个大于Integer的数据,可能就出错了
    private String userId;
    private Long money;

}
