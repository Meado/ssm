package cn.nyse.service.impl;

import cn.nyse.dao.UserMapper;
import cn.nyse.entity.User;
import cn.nyse.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImp extends ServiceImp<User> implements UserService {
    @Autowired
    UserMapper userMapper;

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

    /*
     * 分页查询和搜索模糊查询
     * */
    @Override
    public PageInfo<User> selectByCondition(Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("pageNum"))) {
            params.put("pageNum", 1);
        }

        if (StringUtils.isEmpty(params.get("pageSize"))) {
            params.put("pageSize", 10);
        }

        PageHelper.startPage((Integer) params.get("pageNum"), (Integer) params.get("pageSize"));

        //拼接sql   调用mapper方法
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(params.get("realName"))) {
            criteria.andLike("realName", "%" + params.get("realName") + "%");
//            criteria.andEqualTo("realName", params.get("realName"));
        }

        List<User> qualifications = userMapper.selectByExample(example);
        if (!StringUtils.isEmpty(params.get("id"))) {
            Integer uid = (Integer) params.get("id");
            //设置查到的用户是否为登录账号用户的关注人
            for (User user : qualifications) {
                int i = userMapper.selectFocusCount(uid, user.getId());
                user.setMark(i);
            }
        }

        return new PageInfo<User>(qualifications);
    }

    /*
     * 根据用户ID查询用户关注人数
     * 自连接查询
     * */
    @Override
    public PageInfo<User> selectFocus(Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("pageNum"))) {
            params.put("pageNum", 1);
        }

        if (StringUtils.isEmpty(params.get("pageSize"))) {
            params.put("pageSize", 3);
        }

        PageHelper.startPage((Integer) params.get("pageNum"), (Integer) params.get("pageSize"));

        long uid = 1;
        List<User> users = userMapper.selectFocus(uid);
        return new PageInfo<User>(users);
    }

    /*
     * 添加关注
     * */
    @Override
    public int insertFacus(long uid, long fid) {
        return userMapper.insertFacus(uid, fid);
    }

    /*
       取消关注
     */
    @Override
    public int deleteFacus(long uid, long fid) {
        return userMapper.deleteFocus(uid, fid);
    }
}
