package com.atguigu.admin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.FORBIDDEN,reason = "用户数量太多")
public class UserTooManyException extends RuntimeException {

    //构造器,用于程序中创建该异常
    public  UserTooManyException(){

    }

    public  UserTooManyException(String message){
        super(message);
    }
}
