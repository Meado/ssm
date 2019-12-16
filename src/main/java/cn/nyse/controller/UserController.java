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
    public PageInfo<User> selectAllUser(@RequestBody Map<String,Object> params){
        PageInfo<User> pageInfo = userService.selectAllUser(params);
        return pageInfo;
    }

    @RequestMapping("/user/addFocus")
    public Result addFocus(@RequestBody String userId,@RequestBody String focusId){
        Result result = new Result();

        System.out.println(userId);
        System.out.println(focusId);

        return result;
    }

}
