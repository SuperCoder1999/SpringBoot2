package com.atguigu.admin.controller;


/*import com.atguigu.admin.exception.UserTooManyException;
import com.atguigu.admin.service.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;*/
import com.atguigu.admin.bean.User;

import com.atguigu.admin.service.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class TableController {

    /**
     * ============= 使用MyBatis-Plus  ===============
     */
    @Autowired
    UserService userService;


    /** 测试Mybatis-Plus不用Page输出数据库记录  || 简化前端页面(遍历域数据,形成表格)
     */
    @GetMapping("/dynamic_table02")
    public String dynamic_table(Model model){

        //==  简化前端页面(遍历域数据,形成表格) ===
        //表格内容的遍历 视频P46
        List<User> users = Arrays.asList(new User("zhangsan", "123456",null, null, null, null),
                new User("lisi", "123444",null, null, null, null),
                new User("haha", "aaaaa",null, null, null, null),
                new User("hehe ", "aaddd",null, null, null, null));
        model.addAttribute("users2",users);


        //===== 从数据库中查出user表中的用户进行展示 ========

        //方法一:
        List<User> list = userService.list();
        model.addAttribute("users", list);

        return "table/dynamic_table01";
    }

    @GetMapping("/dynamic_table")
    public String dynamic_table(@RequestParam(value="pn",defaultValue = "1") Integer pn, Model model){

     List<User> users = Arrays.asList(new User("zhangsan", "123456",null, null, null, null),
                new User("lisi", "123444",null, null, null, null),
                new User("haha", "aaaaa",null, null, null, null),
                new User("hehe ", "aaddd",null, null, null, null));
        model.addAttribute("users2",users);

//        //方法二:
//        //构造分页参数
//        Page<User> page = new Page<>(pn, 2);
//        //调用page进行分页
//        Page<User> userPage = userService.page(page, null);
//
//        userPage.getRecords();
//        userPage.getCurrent();
//        userPage.getPages();
//
//        //model.addAttribute("users",userPage);

        return "table/dynamic_table";
    }

    /*@GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id,
                             @RequestParam(value = "pn",defaultValue = "1")Integer pn,
                             RedirectAttributes ra){

        userService.removeById(id);

        ra.addAttribute("pn",pn);
        return "redirect:/dynamic_table";
    }*/


    /*
    //======================== 测试异常处理机制 2 ======================
    //  测试错误: 不带请求参数或者参数类型不对. "400；Bad Request":一般都是浏览器的参数没有传递正确
    @GetMapping("/basic_table")
    //1.@RequestParam("a") int a引出手动配置错误响应:因为没有a的值会报错400,error下没有这个相应页面
    public String basic_table(@RequestParam("a") int a){

        //2.用于测试默认错误响应页面:error/5xx.html
        //int i = 10/0;
        return "table/basic_table";
    }
    //测试异常处理机制 1
    @GetMapping("/dynamic_table")
    public String dynamic_table(){
        //1.如果在程序中任何位置,调用这个方法,就会停止当前请求,转发出去/error请求,进行处理异常
//        response.sendError;

        //2.通过ResponseStatus,定义异常处理器
//        if(users.size()>3){
//            throw new UserTooManyException();
//        }
        return "table/dynamic_table";
    }*/

    /**
     * ================ 普通的页面跳转请求 ===================
     */
    @GetMapping("/basic_table")
    public String basic_table(){
        return "table/basic_table";
    }
    @GetMapping("/responsive_table")
    public String responsive_table(){
        return "table/responsive_table";
    }

    @GetMapping("/editable_table")
    public String editable_table(){
        return "table/editable_table";
    }
}
