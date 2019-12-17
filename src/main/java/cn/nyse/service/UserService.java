package cn.nyse.service;

import cn.nyse.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface UserService extends IService<User> {
    PageInfo<User> selectAllUser(Map<String,Object> params);

    PageInfo<User> selectByCondition(Map<String, Object> params);

    PageInfo<User> selectFocus(Map<String, Object> params);

    /*
     * 添加关注
     * */
    int insertFacus(long uid, long fid);
    /*
  取消关注
 */
    int deleteFacus(long uid, long fid);
}
