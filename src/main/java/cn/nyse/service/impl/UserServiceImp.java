package cn.nyse.service.impl;

import cn.nyse.entity.User;
import cn.nyse.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImp extends ServiceImp<User> implements UserService {

}
