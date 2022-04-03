package com.atguigu.admin.controller;


/*import com.atguigu.admin.exception.UserTooManyException;
import com.atguigu.admin.service.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;*/
import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TableController {


    /*@Autowired
    UserService userService;

    *//*
     *
     * @param a  不带请求参数或者参数类型不对  400；Bad Request  一般都是浏览器的参数没有传递正确
     * @return
     */
    @GetMapping("/basic_table")
    //@RequestParam("a") int a引出手动配置错误响应:因为没有a的值会报错400,error下没有这个相应页面
    public String basic_table(/*@RequestParam("a") int a*/){

        //用于测试默认错误响应页面:error/5xx.html
         //int i = 10/0;
        return "table/basic_table";
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
    //用于测试 异常处理
    @GetMapping("/dynamic_table")
    public String dynamic_table(){
//        1.如果在程序中任何位置,调用这个方法,就会停止当前请求,转发出去/error请求,进行处理异常
//        response.sendError;

        //2.通过ResponseStatus,定义异常处理器
//        if(users.size()>3){
//            throw new UserTooManyException();
//        }

        return "table/dynamic_table";
    }*/

    /**
     * 使用MyBatis-Plus
     */
    @GetMapping("/dynamic_table")
    public String dynamic_table(/*@RequestParam(value="pn",defaultValue = "1") Integer pn,Model model*/){
        //表格内容的遍历

//     List<User> users = Arrays.asList(new User("zhangsan", "123456"),
//                new User("lisi", "123444"),
//                new User("haha", "aaaaa"),
//                new User("hehe ", "aaddd"));
//        model.addAttribute("users",users);
//

        //从数据库中查出user表中的用户进行展示

       /* //构造分页参数
        Page<User> page = new Page<>(pn, 2);
        //调用page进行分页
        Page<User> userPage = userService.page(page, null);
*/

//        userPage.getRecords()
//        userPage.getCurrent()
//        userPage.getPages()

       // model.addAttribute("users",userPage);

        return "table/dynamic_table";
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
