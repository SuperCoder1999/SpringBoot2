package com.atguigu.admin.controller;



import com.atguigu.admin.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class IndexController {
    /**
     * 来登录页
     * @return
     */
    @GetMapping(value = {"/","/login"})
    public String loginPage(){

        return "login";
    }

    @PostMapping("/login")
    public String main(User user, HttpSession session, Model model){ //重定向携带数据可以用:RedirectAttributes

        if(StringUtils.hasLength(user.getUserName()) && "123456".equals(user.getPassword())){
            //把登陆成功的用户保存到session中,保持登陆状态
            session.setAttribute("loginUser",user);
            //System.out.println(user);
            //登录成功重定向到main.html;  重定向防止表单重复提交
            /*
            重定向: 浏览器发送localhost:8080/main.html请求
            请求转发: 浏览器地址还是第一次请求(localhost:8080/login?name=adc&password=123)的内容,容易造成表单重复提交
             */
            return "redirect:/main.html";
        }else {
            model.addAttribute("msg","账号密码错误");
            //回到登录页面
            return "login";
        }

    }

    /**
     * 去main页面
     * @return
     */
    @GetMapping("/main.html")
    public String mainPage(HttpSession session, Model model){ //model是返回到html提示信息的

        log.info("当前方法是：{}","mainPage");
        //从session中判断是否登录。  这样写不太好,因为需要在多个请求控制器中判断登录,所以建议采用拦截器/过滤器机制
        /*Object loginUser = session.getAttribute("loginUser");
        if(loginUser != null){
            return "main";
        }else {
            //回到登录页面
            model.addAttribute("msg","请重新登录");
            return "login";
        }*/
//        ValueOperations<String, String> opsForValue =
//                redisTemplate.opsForValue();
//
//        String s = opsForValue.get("/main.html");
//        String s1 = opsForValue.get("/sql");
//
//
//        model.addAttribute("mainCount",s);
//        model.addAttribute("sqlCount",s1);

        return "main";

    }
}
