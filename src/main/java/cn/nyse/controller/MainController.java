package cn.nyse.controller;

import cn.nyse.entity.Result;
import cn.nyse.entity.User;
import cn.nyse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MainController {

    @Autowired
    private UserService userService;

    @RequestMapping("login")
    public Result login(@RequestBody Map<String,Object> params, HttpSession session){
        System.out.println(params);
        System.out.println(session.getAttribute("checkCode"));
        Result result = new Result();
        if(params.containsKey("code")&& !StringUtils.isEmpty(params.get("code"))){
            result.setMsg("");
            if(session.getAttribute("checkCode").equals(params.get("code"))){
                if(params.containsKey("username")&&!StringUtils.isEmpty(params.get("username"))
                        &&params.containsKey("password")&&!StringUtils.isEmpty(params.get("password"))){
                    //校验登录账号密码
                    User user = new User();
                    String username = (String) params.get("username");
                    String password = (String) params.get("password");
                    user.setUsername(username);
                    user.setPassword(password);
                    User loginUser = userService.selectOne(user);
                    if(loginUser!=null){//登录成功
                        result.setSuccess(true);
                        result.setMsg("登录成功");
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("username",username);
                        map.put("id",loginUser.getId());

                        result.setObj(map);//放入响应数据用于前端保存状态

                        //将用户信息放入session
                        session.setAttribute("user",user);
                    }else{
                        result.setMsg("用户名或者密码错误！！！");
                    }
                }
            }
        }

        return result;
    }
}
