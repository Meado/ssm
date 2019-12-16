package cn.nyse.service.impl;

import cn.nyse.entity.Dept;
import cn.nyse.service.DeptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeptServiceImpl extends ServiceImp<Dept> implements DeptService {
}
