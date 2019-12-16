package cn.nyse.service;

import cn.nyse.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface UserService extends IService<User> {
    PageInfo<User> selectAllUser(Map<String,Object> params);
}
