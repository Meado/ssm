package cn.nyse.service.impl;

import cn.nyse.dao.UserMapper;
import cn.nyse.entity.User;
import cn.nyse.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImp extends ServiceImp<User> implements UserService {

    @Override
    public PageInfo<User> selectAllUser(Map<String, Object> params) {
        //默认值设置
        if(StringUtils.isEmpty(params.get("pageNum"))){
            params.put("pageNum",1);
        }
        if(StringUtils.isEmpty(params.get("pageSize"))){
            params.put("pageSize",3);
        }
        PageHelper.startPage((Integer) params.get("pageNum"),(Integer) params.get("pageSize"));
        List<User> userList = mapper.selectAll();
        PageInfo<User> pageInfo = new PageInfo<>(userList);//生成分页对象

        return pageInfo;
    }
}
