package cn.nyse.controller;

import cn.nyse.entity.Result;
import cn.nyse.entity.User;
import cn.nyse.service.UserService;
import cn.nyse.util.KeyUtil;
import cn.nyse.util.SendMailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MainController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param params
     * @param session
     * @return
     */
    @RequestMapping("/login/toLogin")
    public Result toLogin(@RequestBody Map<String,Object> params, HttpSession session){
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

                        //添加登录时间
                        loginUser.setLoginTime(new Date());
                        userService.updateByPrimaryKeySelective(loginUser);

                        result.setSuccess(true);
                        result.setMsg("登录成功");
                        result.setObj(loginUser);//放入响应数据用于前端保存状态

                        //将用户信息放入session
                        session.setAttribute("user",loginUser);
                    }else{
                        result.setMsg("用户名或者密码错误！！！");
                    }
                }else {
                    result.setMsg("验证码错误！！！");
                }
            }
        }

        return result;
    }

    /**
     * 注册
     * @param params
     * @return
     */
    @RequestMapping("/login/toRegister")
    public Result toRegister(@RequestBody Map<String,Object> params){
        Result result = new Result();
        //将传入的数据添加到User对象中
        User user = new User();
        user.setEmail((String) params.get("email"));

        result.setObj(user);

        //查询邮箱是否存在
        if (userService.selectOne(user) == null) {

            //查询用户名是否存在
            user.setEmail(null);
            user.setUsername((String) params.get("username"));
            if (userService.selectOne(user) == null) {

                user.setEmail((String) params.get("email"));
                user.setPassword((String) params.get("password"));
                user.setRegisterTime(new Date());

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
        }else{
            result.setMsg("邮箱已注册！！！");
        }


        return result;
    }
    /**
     * 发送邮件
     */
    @RequestMapping("/login/sendMail")
    public Result sendMail(String email,HttpSession session){
        Result result = new Result();
        //判断邮箱是否有相关的账号
        User user = new User();
        user.setEmail(email);

        User checkEmail = userService.selectOne(user);
        if (checkEmail!=null) {
            System.out.println(email);
            //调用KeyUtil工具类产生验证码
            String keyCode = KeyUtil.keyUtil();
            //调用SendMailUtil工具类
            Boolean sendResult = SendMailUtil.sendLoginMail(email, keyCode);
            if (sendResult) {
                result.setSuccess(true);
                result.setMsg("邮件发送成功");
                session.setAttribute("keyCode",keyCode);
            }else{
                result.setMsg("发送失败！！！");
            }
        }else{
            result.setMsg("该邮箱还未注册账号，请先注册！！！");
        }
        return result;
    }

    @RequestMapping("/login/forget")
    public Result forget(@RequestBody Map<String,Object> params, HttpSession session){
        Result result = new Result();
        //获得session域中的验证码
        String keyCode = (String) session.getAttribute("keyCode");
        //获取前端传过来的参数
        String email = (String) params.get("email");
        String identifyCode = (String) params.get("identifyCode");
        String newPassword = (String) params.get("newPassword");

        User user = new User();
        user.setEmail(email);

        User updateUser = userService.selectOne(user);

        //判断该邮箱是否存在
        if (userService.selectOne(user)!=null) {
            if (keyCode != null) {
                //判断验证码是否正确
                if (identifyCode.equals(keyCode)){
                    //更新密码
                    updateUser.setPassword(newPassword);
                    int i = userService.updateByPrimaryKeySelective(updateUser);
                    if (i==1){
                        result.setMsg("修改成功");
                    }else {
                        result.setMsg("修改失败");
                    }
                }else{
                    result.setMsg("验证码错误！！！");
                }
            } else {
                result.setMsg("您还未发送验证码，请先发送验证码！！！");
            }
        }else{
            result.setMsg("该邮箱还未注册账号，请先注册！！！");
        }
        return result;
    }
}
