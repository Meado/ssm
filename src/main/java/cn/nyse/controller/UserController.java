package cn.nyse.controller;

import cn.nyse.entity.Dept;
import cn.nyse.entity.Result;
import cn.nyse.entity.User;
import cn.nyse.service.DeptService;
import cn.nyse.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private DeptService deptService;

    @RequestMapping("/user/saveUser")
    @ResponseBody
    public Result saveUser(@RequestBody User user) {
        Result result = new Result();
        System.out.println(user);

        //保存用户数据
        int i = userService.updateByPrimaryKeySelective(user);
        //保存成功
        if (i==1){
            result.setSuccess(true);
            result.setMsg("保存成功");
            result.setObj(user);
        }else {
            result.setMsg("保存失败，请刷新页面重试！！！");
        }

        return result;
    }

    @RequestMapping("/user/searchDept")
    @ResponseBody
    public List<Dept> searchDept() {
        List<Dept> depts = deptService.selectAll();
        return depts;
    }

    @RequestMapping("/user/selectAllUser")
    @ResponseBody
    public PageInfo<User> selectAllUser(@RequestBody Map<String,Object> params,HttpSession session){
        User user = (User) session.getAttribute("user");
        params.put("id", user.getId());
        return userService.selectByCondition(params);
    }

    @RequestMapping("/user/selectFocus")
    @ResponseBody
    public PageInfo<User> selectFocus(@RequestBody Map<String, Object> focus){
        return userService.selectFocus(focus);
    }

    @RequestMapping("/user/chooseFocus")
    @ResponseBody
    public Result chooseFocus(long fid, String checked, HttpSession session){
        User user = (User) session.getAttribute("user");
        Result result  = new Result();
        if ("true".equals(checked)){
            int i = userService.insertFacus(user.getId(), fid);
            if (i>0)
            {
                result.setSuccess(true);
                result.setMsg("关注成功！");
            }
        }else if ("false".equals(checked)){
            int i = userService.deleteFacus(user.getId(), fid);
            if (i>0)
            {
                result.setSuccess(true);
                result.setMsg("取消关注成功！");
            }
        }

        return result;
    }

}
