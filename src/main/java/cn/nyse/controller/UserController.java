package cn.nyse.controller;

import cn.nyse.entity.Result;
import cn.nyse.entity.User;
import cn.nyse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("login")
    public Result login(@RequestParam User user){
        User one = userService.selectOne(user);
        return null;
    }

    @RequestMapping("test")
    public void test(){
        System.out.println("进入");
    }


}
