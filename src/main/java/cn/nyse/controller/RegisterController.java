package cn.nyse.controller;

import cn.nyse.entity.Result;
import cn.nyse.entity.User;
import cn.nyse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RegisterController {
    @Autowired
    private UserService userService;

    @RequestMapping("/toRegister")
    public Result toRegister(@RequestBody Map<String,Object> params){
        Result result = new Result();
        //将传入的数据添加到User对象中
        User user = new User();
        user.setEmail((String) params.get("email"));
        user.setUsername((String) params.get("username"));
        user.setPassword((String)params.get("password"));

        result.setObj(user);

        //查询用户是否存在
        User check = new User();
        check.setUsername(user.getUsername());
        User reUser = userService.selectOne(check);

        if (reUser == null) {

            //添加数据到数据库中
            int i = userService.insertSelective(user);
            //将数据返回

            if (i == 1) {//添加成功
                result.setMsg("注册成功");
                result.setSuccess(true);
            } else {//添加失败
                result.setMsg("服务器繁忙，注册失败，请稍后再试！！！");
            }
        }else{
            result.setMsg("用户名已存在！！！");
        }

        return result;
    }
}
